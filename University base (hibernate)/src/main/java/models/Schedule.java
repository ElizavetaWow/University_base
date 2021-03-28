package models;

import javax.persistence.*;

@Entity
@Table(name="schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int row_id;
    private int duration;
    private String start_dt, start_time, end_dt, end_time;
    private boolean active;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private TestList testList;

    public Schedule(){}
    public Schedule(int duration, String start_dt, String start_time, String end_dt, String end_time, boolean active){
        this.duration = duration;
        this.start_dt = start_dt;
        this.start_time = start_time;
        this.end_dt = end_dt;
        this.end_time = end_time;
        this.active = active;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "row_id=" + row_id +
                ", duration=" + duration +
                ", start_dt=" + start_dt +
                ", start_time=" + start_time +
                ", end_dt=" + end_dt +
                ", end_time=" + end_time +
                ", active=" + active +
                '}';
    }

    public TestList getTestList() {
        return testList;
    }

    public void setTestList(TestList testList) {
        this.testList = testList;
    }

    public int getRow_id() {
        return row_id;
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
}
