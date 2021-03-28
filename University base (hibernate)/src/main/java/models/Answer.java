package models;

import javax.persistence.*;

@Entity
@Table(name="answers")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int row_id;
    private String text;
    private boolean correct;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    public Answer(){}
    public Answer(String text, boolean correct){
        this.text = text;
        this.correct = correct;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public int getRow_id() {
        return row_id;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "row_id=" + row_id +
                ", text='" + text + '\'' +
                ", correct=" + correct +
                '}';
    }
}
