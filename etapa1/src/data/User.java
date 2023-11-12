package data;

import fileio.input.UserInput;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class User {
    private String username, city;
    private int age;

    private ISelectable currentSelection;
    private List<ISelectable> searchResults;

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
                ", currentSelection=" + currentSelection +
                ", searchResults=" + searchResults +
                '}';
    }
}
