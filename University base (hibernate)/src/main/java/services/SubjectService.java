package services;

import dao.SubjectDao;
import models.Subject;

import java.util.List;

public class SubjectService {
    private SubjectDao subjectDao = new SubjectDao();

    public SubjectService(){}

    public Subject findSubject(int id){
        return subjectDao.findById(id);
    }

    public void saveSubject(Subject subject){
        subjectDao.save(subject);
    }

    public void updateSubject(Subject subject){
        subjectDao.update(subject);
    }

    public void deleteSubject(Subject subject){
        subjectDao.delete(subject);
    }

    public List<Subject> findAllSubjects(){
        return subjectDao.getAll();
    }
}