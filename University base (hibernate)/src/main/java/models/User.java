package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int row_id;

    private String first_name, last_name, middle_name, login;
    private String password_hash;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;


    public User(){}

    public User(String first_name, String last_name, String middle_name,
                String login, String password_hash){
        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_name = middle_name;
        this.login = login;
        this.password_hash = password_hash;
    }

    public int getRow_id() {
        return row_id;
    }

    public Group getGroup() {
        return group;
    }

    public Role getRole() {
        return role;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "row_id=" + row_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", middle_name='" + middle_name + '\'' +
                ", login='" + login + '\'' +
                ", password_hash='" + password_hash + '\'' +
                '}';
    }
}
