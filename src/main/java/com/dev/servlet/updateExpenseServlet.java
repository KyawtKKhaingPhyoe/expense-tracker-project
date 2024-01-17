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

@WebServlet("/updateExpense")
public class updateExpenseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Long id = Long.parseLong(req.getParameter("id"));
		
		String title = req.getParameter("title");
		String date = req.getParameter("date");
		String description = req.getParameter("description");
		String amount = req.getParameter("amount");
		
		HttpSession session = req.getSession();
		
		User user = (User) session.getAttribute("loginUser");
		
		Expense expense = new Expense(title,date,description,amount,user);
		expense.setId(id);
		
		ExpenseDao expenseDao = new ExpenseDao(HibernateUtil.getSessionFactory());
		boolean f = expenseDao.updateExpense(expense);
		
		if(f) {
			
			session.setAttribute("msg", "Updated Expense Successfully!");
			resp.sendRedirect("user/view_expense.jsp");
			
		} else {
			
			session.setAttribute("msg", "Something wrong!");
			resp.sendRedirect("user/edit_expense.jsp");
			
		}
		
	}

}
