package com.nazarov.javadeveloper.chapter23;

import com.nazarov.javadeveloper.chapter23.entity.Post;
import com.nazarov.javadeveloper.chapter23.entity.Region;
import com.nazarov.javadeveloper.chapter23.entity.Writer;
import com.nazarov.javadeveloper.chapter23.repository.WriterRepository;
import com.nazarov.javadeveloper.chapter23.repository.impl.WriterRepositoryImpl;
import com.nazarov.javadeveloper.chapter23.service.UserService;
import com.nazarov.javadeveloper.chapter23.service.impl.UserServiceImpl;
import com.nazarov.javadeveloper.chapter23.view.UserMenu;

import java.util.ArrayList;
import java.util.List;

public class ApplicationRunner {

    public static void main(String[] args) {
        Context context = ApplicationContext.init();
        UserMenu userMenu = UserMenu.getInstance();
        userMenu.showMainMenu();

//        UserService userService = new UserServiceImpl();
//
//        Writer result = userService.getByLastName("Nazarov");
//        System.out.println(result);


//        Post post = new Post("Simple post");
//
//        Region region = new Region("Perm");
//        Writer writer = new Writer("Anton", "Nazarov", region);
//        List<Post> posts = new ArrayList<>();
//        posts.add(new Post("Some content #1"));
//        posts.add(new Post("Some content #2"));
//        writer.setPosts(posts);
//
//        Writer result = writerRepository.save(writer);
//        System.out.println(result);
    }
}