package com.dev.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dev.dao.UserDao;
import com.dev.db.HibernateUtil;
import com.dev.entity.User;

@WebServlet("/userRegister")
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		User user = new User(name, email, password);
		
		UserDao userDao = new UserDao(HibernateUtil.getSessionFactory());
		boolean f = userDao.saveUser(user);
		
		HttpSession session = req.getSession();
		
		if(f) {
			session.setAttribute("msg", "Register Successfully!");
			
			resp.sendRedirect("register.jsp");
			//System.out.println("Register Successfully!");
		}
		else {
			session.setAttribute("msg", "Something wrong! Try Again!");
			//System.out.println("Something wrong! Try Again!");
			resp.sendRedirect("register.jsp");
		}
	}

}
