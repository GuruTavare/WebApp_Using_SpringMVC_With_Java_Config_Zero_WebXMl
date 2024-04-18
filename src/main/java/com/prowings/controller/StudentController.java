package com.prowings.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prowings.DAO.StudentDao;
import com.prowings.entity.Student;

@Controller
@RequestMapping("/students")
public class StudentController {
	@Autowired
	private StudentDao studentDao;

	// @RequestMapping(value = "/save",method = RequestMethod.POST)
	@PostMapping("/save")
	public String addStudent(HttpServletRequest request) {
		System.out.println("inside addStudent method");
		String name = request.getParameter("name");
		String addr = request.getParameter("address");
		Student student = new Student(name, addr);
//		Student student = new Student(id, name, addr);

		System.out.println(student);
		// call to DAO logic to save incoming student
		studentDao.saveStudent(student);

		return "savedSuccess";

	}

	@GetMapping("/list")
	public String getStudentsList(Model model) {
		System.out.println("inside getStudentList() method of StudentController!!");
		List<Student> stdList = studentDao.getStudentsList();
		System.out.println(stdList);

		model.addAttribute("students", stdList);

		return "listStudent";
	}

	@PostMapping("/delete")
	public String deleteStudentById(@RequestParam("id") int id,
			/*
			 * RedirectAttributes redirectAttributes HttpServletRequest req,
			 * HttpServletResponse resp
			 */ Model model) /* throws ServletException, IOException */ {
		System.out.println("Inside deleteStudentById() method of StudentController!!");
		// System.out.println(id);

		// Delete the student from the database
		Student deletedStudent = studentDao.deleteStudent(id);
		System.out.println(deletedStudent);

		// Add the deleted student to flash attributes
		// redirectAttributes.addFlashAttribute("deletedStudent", deletedStudent);

		/*
		 * req.setAttribute("deletedStudent", deletedStudent); // Forward the request to
		 * the JSP req.getRequestDispatcher("/delete.jsp").forward(req, resp);
		 */
		model.addAttribute("deletedStudent", deletedStudent);
		return "delete"; // Forward to a different view (views/delete.jsp) after deletion
	}

}
