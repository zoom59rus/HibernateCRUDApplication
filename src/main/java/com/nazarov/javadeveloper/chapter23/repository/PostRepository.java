package com.nazarov.javadeveloper.chapter23.repository;

import com.nazarov.javadeveloper.chapter23.entity.Post;

import java.util.List;

public interface PostRepository extends GenericRepository<Post, Long> {

    Post getByContent(String content);
    List<Post> getByWriterId(Long writerId);
}
