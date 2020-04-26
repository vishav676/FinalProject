package com.udacity.gradle.builditbigger.backend;
import com.vishavlakhtia.jokes.MyClass;

/** The object model for the data we are sending through endpoints */
public class fetchJokes {

    private MyClass jokes;
    public fetchJokes() {
        jokes = new MyClass();
    }
    public String getData() {
        return jokes.getRandomJoke();
    }
}