package com.nazarov.javadeveloper.chapter23.service;

import com.nazarov.javadeveloper.chapter23.entity.Post;

import java.util.List;

public interface PostService {
    Post save(Post post);
    Post update(Post post);
    Post getById(Long id);
    Post getByContent(String content);
    List<Post> getByWriterId(Long writerId);
    List<Post> getAll();
    boolean remove(Post post);
}
