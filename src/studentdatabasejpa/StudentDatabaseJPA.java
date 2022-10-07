/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentdatabasejpa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author prosf
 */
public class StudentDatabaseJPA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Student stu1 = new Student(1,"Porapipat",3.14);
        Student stu2 = new Student(2,"Teddy",2.98);
        Student stu3 = new Student(3,"Snow",1.43);
        Student stu4 = new Student(4,"Biden",4.00);
        
        //Insert
//        StudentTable.insertStudent(stu1);
//        StudentTable.insertStudent(stu2);
//        StudentTable.insertStudent(stu3);
//        StudentTable.insertStudent(stu4);
        
        //Delete
//        StudentTable.removeStudent(stu3);
//        StudentTable.removeStudent(stu4);

        //Update
        updateName(stu1, "Dilan");
        updateGpa(stu1, 4.00);
        
        updateNameByID(2,"Donka");
        
        
        List<Student> stuList = StudentTable.findAllStudent();
        printAllstudent(stuList);
    }
    
    public static void updateName(Student stu, String newName){
       stu = StudentTable.findStudentById(stu.getId());
       if (stu != null) {
           stu.setName(newName);
           StudentTable.updateStudent(stu);
       }
    }
    
    public static void updateGpa(Student stu, Double gpa){
       stu = StudentTable.findStudentById(stu.getId());
       if (stu != null) {
           stu.setGpa(gpa);
           StudentTable.updateStudent(stu);
       }
    }
    
    public static void updateNameByID(Integer id, String newName){
      Student stu = StudentTable.findStudentById(id);
       if (stu != null) {
           stu.setName(newName);
           StudentTable.updateStudent(stu);
       }
       else System.out.println("Not found ID");
    }
    
    public static void updateGPAByID(Integer id, Double gpa){
      Student stu = StudentTable.findStudentById(id);
       if (stu != null) {
           stu.setGpa(gpa);
           StudentTable.updateStudent(stu);
       }
       else System.out.println("Not found ID");
    }
    
    public static void printAllstudent(List<Student> studentList) {
        for(Student stu : studentList) {
           System.out.print(stu.getId() + " ");
           System.out.print(stu.getName() + " ");
           System.out.println(stu.getGpa() + " ");
       }
    }

    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentDatabaseJPAPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
}
