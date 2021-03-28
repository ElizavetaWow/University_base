package com.krylova.demo2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name="tests")
public class Test {
    @Id
    @GeneratedValue(generator = "question_generator")
    @SequenceGenerator(
            name = "question_generator",
            sequenceName = "question_sequence",
            initialValue =  1000
    )
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private TestList testList;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Question question;

    public Long getId() {
        return id;
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
                "id=" + id +
                ", testList=" + testList +
                ", question=" + question +
                '}';
    }
}
