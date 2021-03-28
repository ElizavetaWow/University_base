import models.*;
import services.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args){
        //creating groups
        GroupService groupService = new GroupService();
        Group group1 = new Group("ПИ19-2", 2019, 2);
        groupService.saveGroup(group1);
        groupService.saveGroup(new Group("ПИ19-1", 2019, 2));
        groupService.saveGroup(new Group("ПИ19-3", 2019, 2));
        groupService.saveGroup(new Group("ПИ18-5", 2018, 4));
        groupService.updateGroup(group1);
        //creating roles
        RoleService roleService = new RoleService();
        roleService.saveRole(new Role("Студент"));
        roleService.saveRole(new Role("Администаратор"));
        roleService.saveRole(new Role("Преподаватель"));
        //creating people
        UserService userService = new UserService();
        User user1 = new User("Елизавета", "Крылова", "Алексеевна", "kea", "123");
        user1.setRole(roleService.findRole("Студент"));
        user1.setGroup(groupService.findGroup("ПИ19-2"));
        userService.saveUser(user1);
        User user3 = new User("Елизавета", "Лисенкова", "Александровна", "lea", "123");
        user3.setRole(roleService.findRole("Студент"));
        user3.setGroup(groupService.findGroup("ПИ19-2"));
        userService.saveUser(user3);
        User user2 = new User("Даниил", "Милованов", "Михайлович", "mdm", "123");
        user2.setRole(roleService.findRole("Преподаватель"));
        userService.saveUser(user2);
        //creating subjects
        SubjectService subjectService = new SubjectService();
        Subject sub1 = new Subject("Алгоритмы и структуры данных");
        subjectService.saveSubject(sub1);
        //creating questions
        QuestionService questionService = new QuestionService();
        for (int i = 1; i < 26; i++){
            questionService.saveQuestion(new Question("Вопрос "+i, 2, true));
        }
        //creating answers
        AnswerService answerService = new AnswerService();
        for (int i = 1; i < 26; i++){
            for (int j = 1; j < 4; j++){
                boolean type;
                if (j == 1) {
                    type = true;
                }
                else {
                    type = false;
                }
                Answer ans = new Answer("Ответ " + j, type);
                ans.setQuestion(questionService.findQuestion(i));
                answerService.saveAnswer(ans);
            }
        }
        //creating tests lists
        TestListService testListService = new TestListService();
        TestList tl1 = new TestList("Экзамен");
        tl1.setSubject(subjectService.findSubject(1));
        tl1.setTeacher(userService.findUser(3));
        testListService.saveTestList(tl1);
        TestList tl2 = new TestList("Контрольная");
        tl2.setSubject(subjectService.findSubject(1));
        tl2.setTeacher(userService.findUser(3));
        testListService.saveTestList(tl2);
        //creating tests
        TestService testService = new TestService();
        for (int i = 1; i < 10; i++) {
            Test test1 = new Test();
            Test test2 = new Test();
            test1.setTestList(tl2);
            test1.setQuestion(questionService.findQuestion(i));
            test2.setTestList(tl1);
            test2.setQuestion(questionService.findQuestion(i+10));
            testService.saveTest(test1);
            testService.saveTest(test2);
        }
        //creating schedules
        ScheduleService scheduleService = new ScheduleService();
        Schedule sch = new Schedule(20, "2020-11-19", "08:30:00",
                "2020-11-19", "12:00:00", true);
        sch.setGroup(groupService.findGroup("ПИ19-2"));
        sch.setTestList(testListService.findTestList(2));
        scheduleService.saveSchedule(sch);
        Schedule sch2 = new Schedule(90, "2021-01-19", "10:30:00",
                "2021-01-19", "12:00:00", true);
        sch2.setGroup(groupService.findGroup("ПИ19-2"));
        sch2.setTestList(testListService.findTestList(1));
        scheduleService.saveSchedule(sch2);
        sch.setGroup(groupService.findGroup("ПИ19-1"));
        scheduleService.saveSchedule(sch);
        //creating student answers
        StudentAnswerService studentAnswerService = new StudentAnswerService();
        StudentAnswer sta = new StudentAnswer();
        sta.setUser(userService.findUser(1));
        sta.setTestlist(testListService.findTestList(1));
        StudentAnswer sta2 = new StudentAnswer();
        sta2.setUser(userService.findUser(2));
        sta2.setTestlist(testListService.findTestList(1));
        for (int i = 11; i < 20; i++) {
            sta.setAnswer(answerService.findAnswer((i - 1)*3));
            studentAnswerService.saveStudentAnswer(sta);
            sta2.setAnswer(answerService.findAnswer((i - 1)*3 + 2));
            studentAnswerService.saveStudentAnswer(sta2);
        }
        sta2.setAnswer(answerService.findAnswer(37));
        studentAnswerService.updateStudentAnswer(sta2);
        sta.setTestlist(testListService.findTestList(2));
        for (int i = 1; i < 10; i++) {
            sta.setAnswer(answerService.findAnswer((i - 1)*3 + 1));
            studentAnswerService.saveStudentAnswer(sta);
        }
        //tasks output
        List<User> userList = userService.findAllUsers();
        //only faculties
        System.out.println("Only faculties");
        for (User user : userList) {
            if ( user.getRole().getName().equals("Преподаватель")){
                System.out.println(user);
            }
        }
        System.out.println("-----------------------------");
        //only students
        System.out.println("Only students");
        for (User user : userList) {
            if (user.getRole().getName().equals("Студент")){
                System.out.println(user);
            }
        }
        System.out.println("-----------------------------");
        //concrete student tests
        User user = userService.findUser(2);
        try {
            if (user.getRole().getName().equals("Студент")) {
                try {
                    List<Schedule> scheduleList = scheduleService.findScheduleByGroupName(user.getGroup().getName());
                    System.out.println("Concrete student tests");
                    System.out.println(user);
                    System.out.println("Tests:");
                    for (Schedule schedule : scheduleList) {
                        System.out.println(schedule.getTestList());
                    }
                } catch (Exception e) {
                    System.out.println("Dont have a group");
                }
            } else {
                System.out.println("Not a student");
            }
        }
        catch (Exception e) {
            System.out.println("Dont have a role");
        }
        System.out.println("-----------------------------");
        // all students results
        System.out.println("All students results");
        for (User userr : userList) {
            if (userr.getRole().getName().equals("Студент")){
               List<StudentAnswer> saList = studentAnswerService.findConcreteStudentAnswers(userr.getRow_id());
               HashMap<Integer, Integer> saMap = new HashMap();
               for (StudentAnswer sa : saList) {
                   int id = sa.getTestlist().getRow_id();
                   if (!saMap.keySet().contains(id)){
                       saMap.put(id, 0);
                   }
                   if (sa.getAnswer().isCorrect()){
                       int score = sa.getAnswer().getQuestion().getScore() + saMap.get(id);
                       saMap.put(id, score);
                   }
               }
               System.out.println(userr);
               for (Map.Entry entry: saMap.entrySet()){
                   System.out.println("Test list: " + entry.getKey() + " Score: " + entry.getValue());
               }
                System.out.println("~~~~~~~~~~~~~");
            }
        }
        System.out.println("-----------------------------");

    }
}
