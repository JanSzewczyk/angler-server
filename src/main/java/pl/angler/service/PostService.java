package pl.angler.service;

import pl.angler.dto.PostDto;
import pl.angler.entity.Post;

import java.util.List;

public interface PostService {
    List<PostDto> getAllPosts(String userEmail);
    List<PostDto> getAllUserPosts(String userEmail, String nick);
    Post addNewPost(String userEmail, PostDto newPost);
    void updateUserPost(String userEmail, PostDto updatedPost);
    void removeUserPost(String userEmail, Long id);
}
