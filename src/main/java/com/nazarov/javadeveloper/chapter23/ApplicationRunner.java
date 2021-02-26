package com.nazarov.javadeveloper.chapter23;

import com.nazarov.javadeveloper.chapter23.view.UserMenu;

public class ApplicationRunner {

    public static void main(String[] args) {
        Context context = ApplicationContext.init();
        UserMenu userMenu = UserMenu.getInstance();
        userMenu.showMainMenu();
    }
}