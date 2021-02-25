package com.nazarov.javadeveloper.chapter23.service.impl;

import com.nazarov.javadeveloper.chapter23.ApplicationContext;
import com.nazarov.javadeveloper.chapter23.entity.Post;
import com.nazarov.javadeveloper.chapter23.repository.PostRepository;
import com.nazarov.javadeveloper.chapter23.repository.impl.PostRepositoryImpl;
import com.nazarov.javadeveloper.chapter23.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class PostServiceImpl implements PostService {
    private final Logger log = LoggerFactory.getLogger(PostServiceImpl.class);

    private PostRepository postRepository;

    public PostServiceImpl() {
        this.postRepository = (PostRepositoryImpl) ApplicationContext.init().getBean("PostRepositoryImpl");
    }

    /**
     * This construction is test only
     * @param postRepository is Mock
     */
    private PostServiceImpl(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @Override
    public Post save(Post post) {
        if(post == null){
            log.warn("IN - save - region is null.");
            return null;
        }
        if(post.getWriter() == null){
            log.warn("IN - save - region.writer is null.");
            return post;
        }

        post = postRepository.save(post);

        return post;
    }

    @Override
    public Post update(Post post) {
        post = postRepository.update(post);

        return post;
    }

    @Override
    public Post getById(Long id) {
        Post result = postRepository.getById(id);

        return result;
    }

    @Override
    public Post getByContent(String content) {
        Post result = postRepository.getByContent(content);

        return result;
    }

    public List<Post> getByWriterId(Long writerId) {
        return postRepository.getByWriterId(writerId);
    }

    @Override
    public List<Post> getAll() {
        return postRepository.getAll();
    }

    @Override
    public boolean remove(Post post) {
        postRepository.remove(post);
        Post result = getById(post.getId());
        if(result == null){
            return true;
        }
        return false;
    }
}
