package com.krylova.demo2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name="student_answers")
public class StudentAnswer {
    @Id
    @GeneratedValue(generator = "question_generator")
    @SequenceGenerator(
            name = "question_generator",
            sequenceName = "question_sequence",
            initialValue =  1000
    )
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private TestList testList;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Answer answer;

    public Long getId() {
        return id;
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
                "id=" + id +
                ", user=" + user +
                ", testList=" + testList +
                ", answer=" + answer +
                '}';
    }
}
