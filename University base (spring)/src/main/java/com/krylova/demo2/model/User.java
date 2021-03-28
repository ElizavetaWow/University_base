package com.krylova.demo2.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(generator = "question_generator")
    @SequenceGenerator(
            name = "question_generator",
            sequenceName = "question_sequence",
            initialValue =  1000
    )
    private Long id;

    private String first_name, last_name, middle_name, login;
    private String password_hash;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Group group;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Role role;

    public Long getId() {
        return id;
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
                "row_id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", middle_name='" + middle_name + '\'' +
                ", login='" + login + '\'' +
                ", password_hash='" + password_hash + '\'' +
                '}';
    }
}
