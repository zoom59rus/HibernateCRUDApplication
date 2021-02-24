package com.nazarov.javadeveloper.chapter23.repository.impl;

import com.nazarov.javadeveloper.chapter23.annotations.MyRepository;
import com.nazarov.javadeveloper.chapter23.entity.Post;
import com.nazarov.javadeveloper.chapter23.repository.PostRepository;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

@MyRepository
public class PostRepositoryImpl extends GenericRepositoryImp<Post, Long> implements PostRepository {

    public PostRepositoryImpl() {
        super(Post.class);
    }

    @Override
    public Post findByName(String name) {
        Post post = null;
        Session session = null;
        String sqlQuery = String.format("SELECT * FROM posts WHERE content='%s'", name);
        try{
            session = super.getSession();
            post = (Post) session.createSQLQuery(sqlQuery).addEntity(Post.class).uniqueResult();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if(session != null){
                session.close();
            }
        }

        return post;
    }

    @Override
    public Post findByContent(String content) {
        return findByName(content);
    }

    @Override
    public Post findById(Long id) {
        Session session = null;
        Post post = null;
        try{
            session = super.getSession();
            post = session.find(Post.class, id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if(session != null){
                session.close();
            }
        }
        return post;
    }

    @Override
    public List<Post> findAll() {
        Session session = null;
        List<Post> posts = new ArrayList<>();
        try{
            session = super.getSession();
            posts = session.createSQLQuery("SELECT * FROM posts")
                    .addEntity(Post.class)
                    .list();
            session.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if(session != null){
                session.close();
            }
        }

        return posts;
    }

    @Override
    public Post save(Post post) {
        Session session = null;
        try{
            session = super.getSession();
            session.getTransaction().begin();
            session.save(post);
            session.getTransaction().commit();
            session.close();
        }catch (Exception e){
            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        }finally {
            if(session != null){
                session.close();
            }
        }

        return post;
    }

    @Override
    public Post update(Post post){
        Session session = null;
        try{
            session = super.getSession();
            session.getTransaction().begin();
            session.update(post);
            session.getTransaction().commit();
            session.close();
        }catch (Exception e){
            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        }finally {
            if(session != null){
                session.close();
            }
        }
        return post;
    }

    @Override
    public void remove(Post post) {
        Session session = null;
        try{
            session = super.getSession();
            session.getTransaction().begin();
            session.delete(post);
            session.getTransaction().commit();
            session.close();
        }catch (Exception e){
            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        }finally {
            if(session != null){
                session.close();
            }
        }
    }
}
