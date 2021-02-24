package com.nazarov.javadeveloper.chapter23.repository;

import com.nazarov.javadeveloper.chapter23.entity.Post;

public interface PostRepository extends GenericRepository<Post, Long> {

    Post findByContent(String content);
}
