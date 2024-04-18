package com.prowings.DAO;

import java.util.List;

import com.prowings.entity.Student;

public interface StudentDao {
	
	public boolean saveStudent(Student student);
	
	public List<Student> getStudentsList();
	
	public Student deleteStudent(int id);

}
