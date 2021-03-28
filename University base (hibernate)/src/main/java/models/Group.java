package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int row_id;
    private String name;
    private int year, semester;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> userList = new ArrayList<>();
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Schedule> scheduleList = new ArrayList<>();

    public Group(){}
    public Group(String name, int year, int semester){
        this.name = name;
        this.year = year;
        this.semester = semester;
    }

    public int getRow_id() {
        return row_id;
    }

    public int getSemester() {
        return semester;
    }

    public int getYear() {
        return year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void addUser(User user){
        user.setGroup(this);
        userList.add(user);
    }

    public void removeUser(User user){
        userList.remove(user);
    }

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    public void addSchedule(Schedule schedule){
        schedule.setGroup(this);
        scheduleList.add(schedule);
    }

    public void removeSchedule(Schedule schedule){
        scheduleList.remove(schedule);
    }

    @Override
    public String toString() {
        return "Group{" +
                "row_id=" + row_id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", semester=" + semester +
                '}';
    }
}
