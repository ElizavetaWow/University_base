package models;

import javax.persistence.*;

@Entity
@Table(name="tests")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int row_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private TestList testList;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;
    public Test(){}

    public int getRow_id() {
        return row_id;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }

    public void setTestList(TestList testList) {
        this.testList = testList;
    }

    public TestList getTestList() {
        return testList;
    }

    @Override
    public String toString() {
        return "Test{" +
                "row_id=" + row_id +
                ", testList=" + testList +
                ", question=" + question +
                '}';
    }
}
