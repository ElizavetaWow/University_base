package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int row_id;
    private String name;
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TestList> testList = new ArrayList<>();

    public Subject(){}

    public Subject(String name){
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

    @Override
    public String toString() {
        return "Subject{" +
                "row_id=" + row_id +
                ", name='" + name + '\'' +
                '}';
    }

    public void setTestList(List<TestList> testList) {
        this.testList = testList;
    }

    public List<TestList> getTestList() {
        return testList;
    }
    public void addTest(TestList test){
        test.setSubject(this);
        testList.add(test);
    }

    public void removeTest(TestList test){
        testList.remove(test);
    }
}
