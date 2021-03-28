package models;

import javax.persistence.*;

@Entity
@Table(name="student_answers")
public class StudentAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int row_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private TestList testList;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id")
    private Answer answer;

    public StudentAnswer(){}

    public int getRow_id() {
        return row_id;
    }

    public Answer getAnswer() {
        return answer;
    }

    public User getUser() {
        return user;
    }

    public TestList getTestlist() {
        return testList;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public void setTestlist(TestList testlist) {
        this.testList = testlist;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "StudentAnswer{" +
                "row_id=" + row_id +
                ", user=" + user.getRow_id() +
                ", testlist=" + testList.getRow_id() +
                ", answer=" + answer +
                '}';
    }
}
