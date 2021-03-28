package services;

import dao.TestDao;
import models.Test;

import java.util.List;

public class TestService {
    private TestDao testDao = new TestDao();

    public TestService(){}

    public Test findTest(int id){
        return testDao.findById(id);
    }

    public void saveTest(Test test){
        testDao.save(test);
    }

    public void updateTest(Test test){
        testDao.update(test);
    }

    public void deleteTest(Test test){
        testDao.delete(test);
    }

    public List<Test> findAllTests(){
        return testDao.getAll();
    }
}