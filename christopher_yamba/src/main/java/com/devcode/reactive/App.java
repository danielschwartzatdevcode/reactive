package com.devcode.reactive;

import rx.Observable;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author Christopher Yamba
 */
public class App {

    private List<Author> authors;

    public App(Author...authors) {
        this.authors = Arrays.asList(authors);
    }



    public class Author{
        String name;
        List<String> post;


        public Author(String name, String...post) {
            this.name = name;
            this.post = Arrays.asList(post);
        }
    }

    public static void main(String... args) {

    }
}
