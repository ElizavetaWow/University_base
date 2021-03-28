package services;

import dao.QuestionDao;
import models.Question;

import java.util.List;

public class QuestionService {
    private QuestionDao questionDao = new QuestionDao();

    public QuestionService(){}

    public Question findQuestion(int id){
        return questionDao.findById(id);
    }

    public void saveQuestion(Question question){
        questionDao.save(question);
    }

    public void updateQuestion(Question question){
        questionDao.update(question);
    }

    public void deleteQuestion(Question question){
        questionDao.delete(question);
    }

    public List<Question> findAllQuestions(){
        return questionDao.getAll();
    }
}