package com.dev.moments.service;

import com.dev.moments.dto.PostDTO;
import com.dev.moments.model.Post;
import com.dev.moments.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post createPost(PostDTO postDTO) {
        Post newPost = new Post(postDTO.getTitle(), postDTO.getDescription(), postDTO.getTags());
        return postRepository.save(newPost);
    }

    public PostDTO getPostById(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        if (post != null) {
            PostDTO postDTO = new PostDTO();
            postDTO.setId(post.getId());
            postDTO.setTitle(post.getTitle());
            postDTO.setDescription(post.getDescription());
            postDTO.setTags(post.getTags());
            return postDTO;
        } else {
            return null;
        }
    }

    public Post updatePost(Long id, PostDTO postDTO) {
        Optional<Post> optionalPost = postRepository.findById(id);

        if(optionalPost.isPresent()) {
            Post existingPost = optionalPost.get();

            existingPost.setTitle(postDTO.getTitle());
            existingPost.setDescription(postDTO.getDescription());
            existingPost.setTags(postDTO.getTags());

            return postRepository.save(existingPost);
        } else {
            return null;
        }

    }

    public Post patchPost(Long id, PostDTO postDTO) {
        Optional<Post> optionalPost = postRepository.findById(id);

        if(optionalPost.isPresent()) {
            Post existingPost = optionalPost.get();

            if(postDTO.getTitle() != null) {
                existingPost.setTitle(postDTO.getTitle());
            }

            if(postDTO.getDescription() != null) {
                existingPost.setDescription(postDTO.getDescription());
            }

            if(postDTO.getTags() != null) {
                existingPost.setTags(postDTO.getTags());
            }

            return postRepository.save(existingPost);

        } else {
            return null;
        }
    }

    public ResponseEntity deletePost(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);

        if(optionalPost.isPresent()) {
            postRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
