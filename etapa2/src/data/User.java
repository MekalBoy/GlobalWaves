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

    public User() {
    }

    public User(final String username, final String city, final int age) {
        this.username = username;
        this.city = city;
        this.age = age;
    }

    public User(final UserInput input) {
        this.username = input.getUsername();
        this.city = input.getCity();
        this.age = input.getAge();
    }
}
