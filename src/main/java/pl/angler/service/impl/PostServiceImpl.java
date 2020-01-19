package pl.angler.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.angler.dto.FisheryDto;
import pl.angler.dto.FishingTripDto;
import pl.angler.dto.FriendDto;
import pl.angler.dto.PostDto;
import pl.angler.entity.Fishery;
import pl.angler.entity.FishingTrip;
import pl.angler.entity.Post;
import pl.angler.entity.User;
import pl.angler.exception.NotFoundException;
import pl.angler.repository.FisheryRepository;
import pl.angler.repository.FishingTripRepository;
import pl.angler.repository.PostRepository;
import pl.angler.repository.UserRepository;
import pl.angler.service.FisheryService;
import pl.angler.service.FriendService;
import pl.angler.service.NotificationService;
import pl.angler.service.PostService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FishingTripRepository fishingTripRepository;
    @Autowired
    private FisheryRepository fisheryRepository;

    @Autowired
    private FriendService friendService;
    @Autowired
    private FisheryService fisheryService;
    @Autowired
    private NotificationService notificationService;

    @Override
    public List<PostDto> getAllPosts(String userEmail) {
        List<String> friendsNicks = this.friendService.getOnlyUserFriends(userEmail).stream().map(FriendDto::getUserNick).collect(Collectors.toList());
        return this.convertPosts(this.postRepository.findAllByUser_NickInOrUser_EmailOrderByReleaseDateDescReleaseTimeDesc(friendsNicks, userEmail), userEmail);
    }

    @Override
    public List<PostDto> getAllUserPosts(String userEmail, String nick) {
        return this.convertPosts(this.postRepository.findAllByUser_NickOrderByReleaseDateDescReleaseTimeDesc(nick), userEmail);
    }

    private List<PostDto> convertPosts(List<Post> posts, String userEmail) {
        return posts.stream()
                .map(post -> {
                    PostDto result = new PostDto();
                    result.setId(post.getId());
                    result.setUserNick(post.getUser().getNick());
                    result.setDescription(post.getDescription());
                    result.setReleaseDate(post.getReleaseDate());
                    result.setReleaseTime(post.getReleaseTime());
                    result.setStatus(post.getUser().getEmail().equals(userEmail) ? 0 : 1);
                    if (post.getFishery() != null){
                        Fishery fishery = post.getFishery();
                        result.setFishery(new FisheryDto(fishery.getId(), fishery.getName(), fishery.getAltitude(), fishery.getLatitude(), fishery.getDescription(), fishery.getPost() != null));
                    }else if (post.getFishingTrip() != null) {
                        FishingTrip fishingTrip = post.getFishingTrip();
                        Fishery fishery = fishingTrip.getFishery();
                        result.setFishingTrip(new FishingTripDto(
                                fishingTrip.getId(),
                                fishingTrip.getTitle(),
                                fishingTrip.getTripDate(),
                                fishingTrip.getDescription(),
                                new FisheryDto(
                                        fishery.getId(),
                                        fishery.getName(),
                                        fishery.getAltitude(),
                                        fishery.getLatitude(),
                                        fishery.getDescription(),
                                        fishery.getPost() != null),
                                fishingTrip.getTrophies(),
                                true));
                    }
                    return result;
                }).collect(Collectors.toList());
    }

    @Override
    public Post addNewPost(String userEmail, PostDto newPost) {
        String notificationMessage = "";

        Optional<User> findUser = this.userRepository.findByEmail(userEmail);
        if (!findUser.isPresent()) {
            throw new NotFoundException("The user with this email not exists.");
        }

        User user = findUser.get();
        Post post = new Post();
        post.setUser(user);
        post.setReleaseDate(LocalDate.now());
        post.setReleaseTime(LocalTime.now());

        if(newPost.getFishingTrip() != null) {
            Optional<FishingTrip> findFishingTrip = this.fishingTripRepository.findById(newPost.getFishingTrip().getId());
            if (!findFishingTrip.isPresent())
                throw new NotFoundException("Not found fishing trip.");

            post.setFishingTrip(findFishingTrip.get());
            post.setDescription("Hello everybody.\n" +
                    "I would like to share you my trip and all the trophies I have caught. I hope you will use this information in a good way. :)");
            notificationMessage = user.getNick() + " shared a fishing expedition on his news board.";
        } else if (newPost.getFishery() != null) {
            Optional<Fishery> findFishery = this.fisheryRepository.findById(newPost.getFishery().getId());
            if (!findFishery.isPresent())
                throw new NotFoundException("Not found fishery.");

            Fishery fishery = findFishery.get();
            this.fisheryService.setFisheryPublic(fishery);
            post.setFishery(fishery);
            post.setDescription("Hello everybody.\n" +
                    "I would like to share you my fishery. I hope you will use this information in a good way. :)");
            notificationMessage = user.getNick() + " shared a fishery on his news board.";
        } else {
            post.setDescription(newPost.getDescription());

            notificationMessage = user.getNick() + " has posted a new post. Check your news board.";
        }

        this.postRepository.save(post);

        this.notificationService.sendNotificationToUsers(notificationMessage, userEmail);

        return post;
    }

    @Override
    public void updateUserPost(String userEmail, PostDto updatedPost) {
        Optional<Post> findPost = this.postRepository.findByIdAndUser_Email(updatedPost.getId(), userEmail);
        if (!findPost.isPresent())
            throw new NotFoundException("The user with this email can't update this post.");

        Post post = findPost.get();
        post.setDescription(updatedPost.getDescription());
        post.setReleaseDate(LocalDate.now());
        post.setReleaseTime(LocalTime.now());

        System.out.println("update post" + updatedPost);
        this.postRepository.save(post);
    }

    @Override
    public void removeUserPost(String userEmail, Long id) {
        Optional<Post> findPost = this.postRepository.findByIdAndUser_Email(id, userEmail);
        if (!findPost.isPresent()) {
            throw new NotFoundException("The user with this email can't update this post.");
        }

        this.postRepository.delete(findPost.get());
    }
}
