package com.nazarov.javadeveloper.chapter23;

import org.hibernate.SessionFactory;

public interface Context {

    Object getBean(String name);
    SessionFactory getSessionFactory();
}