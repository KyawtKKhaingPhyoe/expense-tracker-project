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

@WebServlet("/deleteExpense")
public class DeleteExpenseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Long id = Long.parseLong(req.getParameter("id"));
		
		ExpenseDao expenseDao = new ExpenseDao(HibernateUtil.getSessionFactory());
		boolean f = expenseDao.deleteExpense(id);
		
		HttpSession session = req.getSession();
		
		if(f) {
			session.setAttribute("msg", "Deleted Successfully");
			resp.sendRedirect("user/view_expense.jsp");
		}else {
			session.setAttribute("msg", "Something Wrong!");
			resp.sendRedirect("user/view_expense.jsp");
		}
		
	}

}
