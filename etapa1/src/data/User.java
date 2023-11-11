package data;

import fileio.input.UserInput;

public class User {
    String username, city;
    int age;

    public User() {}

    public User(String username, String city, int age) {
        this.username = username;
        this.city = city;
        this.age = age;
    }

    public User(UserInput input) {
        this.username = input.getUsername();
        this.city = input.getCity();
        this.age = input.getAge();
    }
}
