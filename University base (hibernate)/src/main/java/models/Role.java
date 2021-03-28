package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int row_id;
    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> userList = new ArrayList<>();

    public Role(){}
    public Role(String name){
        this.name = name;
    }

    public int getRow_id() {
        return row_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public void addUser(User user){
        user.setRole(this);
        userList.add(user);
    }

    public void removeUser(User user){
        userList.remove(user);
    }

    @Override
    public String toString() {
        return "Role{" +
                "row_id=" + row_id +
                ", name='" + name + '\'' +
                '}';
    }
}
