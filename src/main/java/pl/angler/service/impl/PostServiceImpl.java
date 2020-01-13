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
import pl.angler.repository.PostRepository;
import pl.angler.service.FriendService;
import pl.angler.service.PostService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private FriendService friendService;

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
                        result.setFishery(new FisheryDto(fishery.getId(), fishery.getName(), fishery.getAltitude(), fishery.getLatitude(), fishery.getDescription()));
                    }else if (post.getFishingTrip() != null) {
                        FishingTrip fishingTrip = post.getFishingTrip();
                        Fishery fishery = fishingTrip.getFishery();
                        result.setFishingTrip(new FishingTripDto(fishingTrip.getId(), fishingTrip.getTitle(), fishingTrip.getTripDate(), fishingTrip.getDescription(), new FisheryDto(fishery.getId(), fishery.getName(), fishery.getAltitude(), fishery.getLatitude(), fishery.getDescription()), fishingTrip.getTrophies()));
                    }
                    return result;
                }).collect(Collectors.toList());
    }
}
