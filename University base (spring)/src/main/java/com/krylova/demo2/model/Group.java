package com.krylova.demo2.model;

import javax.persistence.*;

@Entity
@Table(name="groups")
public class Group {
    @Id
    @GeneratedValue(generator = "question_generator")
    @SequenceGenerator(
            name = "question_generator",
            sequenceName = "question_sequence",
            initialValue =  1000
    )
    private Long id;
    private String name;
    private int year, semester;

    public Long getRow_id() {
        return id;
    }

    public int getSemester() {
        return semester;
    }

    public int getYear() {
        return year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", semester=" + semester +
                '}';
    }
}
