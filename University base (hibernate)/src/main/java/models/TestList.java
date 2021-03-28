package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="test_lists")
public class TestList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int row_id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private User teacher;
    @OneToMany(mappedBy = "testList", cascade = CascadeType.ALL)
    private List<Test> testList = new ArrayList<>();

    public TestList(){}
    public TestList(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRow_id() {
        return row_id;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "TestList{" +
                "row_id=" + row_id +
                ", name='" + name + '\'' +
                ", subject=" + subject.getName() +
                ", teacher=" + teacher.getLast_name() +
                '}';
    }

    public void setTestList(List<Test> testList) {
        this.testList = testList;
    }

    public List<Test> getTestList() {
        return testList;
    }
    public void addTest(Test test){
        test.setTestList(this);
        testList.add(test);
    }

    public void removeTest(Test test){
        testList.remove(test);
    }
}
