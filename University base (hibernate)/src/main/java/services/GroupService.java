package services;

import dao.GroupDao;
import models.Group;
import models.Role;

import java.util.List;

public class GroupService {
    private GroupDao groupDao = new GroupDao();

    public GroupService(){}

    public Group findGroup(int id){
        return groupDao.findById(id);
    }

    public Group findGroup(String name){
        return groupDao.findByName(name);
    }

    public void saveGroup(Group group){
        groupDao.save(group);
    }

    public void updateGroup(Group group){
        groupDao.update(group);
    }

    public void deleteGroup(Group group){
        groupDao.delete(group);
    }

    public List<Group> findAllGroups(){
        return groupDao.getAll();
    }
}