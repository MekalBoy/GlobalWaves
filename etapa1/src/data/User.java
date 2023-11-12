package data;

import fileio.input.UserInput;
import functionality.MusicPlayer;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class User {
    private String username, city;
    private int age;

    private MusicPlayer player = new MusicPlayer();

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

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", city='" + city + '\'' +
                ", age=" + age +
                '}';
    }
}
