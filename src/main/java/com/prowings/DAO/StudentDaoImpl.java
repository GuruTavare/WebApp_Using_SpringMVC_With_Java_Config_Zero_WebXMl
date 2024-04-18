package com.prowings.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prowings.entity.Student;

@Repository
public class StudentDaoImpl implements StudentDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean saveStudent(Student student) {
		System.out.println("inside saveStudent() of StudentDaoImpl");
		// code to interact with DB and saves the incoming student object

		Session session = sessionFactory.openSession();
		Transaction txn = session.beginTransaction();
		session.save(student);
		txn.commit();
		session.close();

		return true;
	}

	@Override
	public List<Student> getStudentsList() {
		System.out.println("inside getStudentsList() of StudentDaoImpl");

		Session session = null;
		Transaction txn = null;
		List<Student> res = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			txn = session.beginTransaction();
			String hql = "FROM Student";
			Query query = session.createQuery(hql);
			res = query.getResultList();
			txn.commit();
		} catch (Exception e) {
			System.out.println("Error while fetching the student list!!");
			e.printStackTrace();
			if (txn != null)
				txn.rollback();
		}

		return res;
	}

	@Override
	public Student deleteStudent(int id) {
	    Session session = null;
	    Transaction txn = null;
	    Student student=null;
	    try {
	        session = sessionFactory.openSession();
	        txn = session.beginTransaction();
	        
	        // Load the student entity by its ID
	         student = session.load(Student.class, id);
	        
	        // Check if the student exists
	        if (student != null) {
	            session.delete(student);
	            txn.commit();
	            System.out.println("Student with ID " + id + " deleted successfully.");
	        } else {
	            System.out.println("Student with ID " + id + " not found.");
	        }
	    } catch (Exception e) {
	        System.out.println("Error while deleting the student with ID " + id);
	        e.printStackTrace();
	        if (txn != null) {
	            txn.rollback();
	        }
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }
	    return student;
	}

}
