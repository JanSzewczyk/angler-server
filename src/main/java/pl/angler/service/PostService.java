package pl.angler.service;

import pl.angler.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> getAllPosts(String userEmail);
    List<PostDto> getAllUserPosts(String userEmail, String nick);
}
