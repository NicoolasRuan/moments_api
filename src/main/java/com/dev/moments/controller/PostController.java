package com.dev.moments.controller;

import com.dev.moments.dto.PostDTO;
import com.dev.moments.model.Post;
import com.dev.moments.service.PostService;
import org.aspectj.apache.bcel.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/posts")
public class PostController {


    @Autowired
    private PostService postService;

    // GET all
    @GetMapping
    public List<Post> getPosts() {
        return postService.getAllPosts();
    }

    // GET one by id
    @GetMapping("/{id}")
    public PostDTO getById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    // POST a post
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostDTO postDTO, UriComponentsBuilder uriBuilder) {
        Post createdPost = postService.createPost(postDTO);

        // Construa a URI para o novo recurso
        URI location = uriBuilder.path("/api/posts/{id}").buildAndExpand(createdPost.getId()).toUri();

        return ResponseEntity.created(location).body(createdPost);
    }

    // UPDATE a post
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody PostDTO postDTO) {
        Post updatedPost = postService.updatePost(id, postDTO);

        if(updatedPost != null) {
            return ResponseEntity.ok(updatedPost);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // PATCH a post
    @PatchMapping("/{id}")
    public ResponseEntity<Post> patchPost(@PathVariable Long id, @RequestBody PostDTO postDTO) {
        Post updatedPost = postService.patchPost(id, postDTO);

        if(updatedPost != null) {
            return ResponseEntity.ok(updatedPost);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    // DELETE a post

    @DeleteMapping("/{id}")
    public ResponseEntity deletePost(@PathVariable Long id) {
        return postService.deletePost(id);
    }

}
