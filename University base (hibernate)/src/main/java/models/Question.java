package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int row_id;
    private String text;
    private int score;
    private boolean active;
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answerList = new ArrayList<>();
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Test> testList = new ArrayList<>();

    public Question(){}
    public Question(String text, int score, boolean active){
        this.text = text;
        this.score = score;
        this.active =  active;
    }

    public int getRow_id() {
        return row_id;
    }

    public int getScore() {
        return score;
    }

    public String getText() {
        return text;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    @Override
    public String toString() {
        return "Question{" +
                "row_id=" + row_id +
                ", text='" + text + '\'' +
                ", score=" + score +
                ", active=" + active +
                '}';
    }
    public void addAnswer(Answer answer){
        answer.setQuestion(this);
        answerList.add(answer);
    }

    public void removeUser(Answer answer){
        answerList.remove(answer);
    }

    public void setTestList(List<Test> testList) {
        this.testList = testList;
    }

    public List<Test> getTestList() {
        return testList;
    }
    public void addTest(Test test){
        test.setQuestion(this);
        testList.add(test);
    }

    public void removeTest(Test test){
        testList.remove(test);
    }
}
