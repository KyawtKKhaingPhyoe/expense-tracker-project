package com.dev.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dev.dao.ExpenseDao;
import com.dev.db.HibernateUtil;
import com.dev.entity.Expense;
import com.dev.entity.User;

@WebServlet("/saveExpense")
public class saveExpenseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String title = req.getParameter("title");
		String date = req.getParameter("date");
		String description = req.getParameter("description");
		String amount = req.getParameter("amount");
		
		HttpSession session = req.getSession();
		
		User user = (User) session.getAttribute("loginUser");
		
		Expense expense = new Expense(title,date,description,amount,user);
		
		ExpenseDao expenseDao = new ExpenseDao(HibernateUtil.getSessionFactory());
		boolean f = expenseDao.saveExpense(expense);
		
		if(f) {
			
			session.setAttribute("msg", "Added Expense Successfully!");
			resp.sendRedirect("user/add_expense.jsp");
			
		} else {
			
			session.setAttribute("msg", "Something wrong!");
			resp.sendRedirect("user/add_expense.jsp");
			
		}
		
	}

}
