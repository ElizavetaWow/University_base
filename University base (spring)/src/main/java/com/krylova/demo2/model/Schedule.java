package com.krylova.demo2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name="schedules")
public class Schedule {
    @Id
    @GeneratedValue(generator = "question_generator")
    @SequenceGenerator(
            name = "question_generator",
            sequenceName = "question_sequence",
            initialValue =  1000
    )
    private Long id;
    private int duration;
    private String start_dt, start_time, end_dt, end_time;
    private boolean active;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Group group;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private TestList testList;

    public TestList getTestList() {
        return testList;
    }

    public void setTestList(TestList testList) {
        this.testList = testList;
    }

    public Long getId() {
        return id;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getEnd_dt() {
        return end_dt;
    }

    public void setEnd_dt(String end_dt) {
        this.end_dt = end_dt;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getStart_dt() {
        return start_dt;
    }

    public void setStart_dt(String start_dt) {
        this.start_dt = start_dt;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", duration=" + duration +
                ", start_dt='" + start_dt + '\'' +
                ", start_time='" + start_time + '\'' +
                ", end_dt='" + end_dt + '\'' +
                ", end_time='" + end_time + '\'' +
                ", active=" + active +
                ", group=" + group +
                ", testList=" + testList +
                '}';
    }
}
