package com.krylova.demo2.model;

import javax.persistence.*;

@Entity
@Table(name="questions")
public class Question extends AuditModel{
    @Id
    @GeneratedValue(generator = "question_generator")
    @SequenceGenerator(
            name = "question_generator",
            sequenceName = "question_sequence",
            initialValue =  1000
    )
    private Long id;
    private String text;
    private int score;
    private boolean active;

    public Long getId() {
        return id;
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

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", score=" + score +
                ", active=" + active +
                '}';
    }
}
