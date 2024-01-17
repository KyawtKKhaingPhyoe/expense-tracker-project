<%@page import="com.dev.entity.User"%>
<%@page import="com.dev.entity.Expense"%>
<%@page import="java.util.List"%>
<%@page import="com.dev.db.HibernateUtil"%>
<%@page import="com.dev.dao.ExpenseDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>view expenses</title><%@ include file="../components/all_css.jsp"%>
</head>
<body class="bg-light">

	<%@ include file="../components/navbar.jsp"%>

	<div class="container p-5">
		<div class="row">
			<div class="col-md-8 offset-md-2">
				<div class="card">
					<div class="card-header text-center">
						<h1 class="text-center">All Expenses</h1>
						
						<c:if test="${not empty msg }">
							<p class = "text-center text-success fs-4">${msg}</p>
							<c:remove var="msg"/>
						</c:if>
					</div>
					<div class="card-body">
						<table class="table">
							<thead>
								<tr>
									<th scope="col">Title</th>
									<th scope="col">Date</th>
									<th scope="col">Description</th>
									<th scope="col">Amount</th>
									<th scope="col">Action</th>
								</tr>
							</thead>
							<tbody>

								<%
								User user = (User) session.getAttribute("loginUser");

								ExpenseDao expenseDao = new ExpenseDao(HibernateUtil.getSessionFactory());
								List<Expense> list = expenseDao.getAllExpenseByUser(user);

								for (Expense expense : list) {
								%>

								<tr>
									<td scope="row"><%=expense.getTitle() %></td>
									<td><%=expense.getDate() %></td>
									<td><%=expense.getDescription() %></td>
									<td><%=expense.getAmount() %></td>
									<td>
										<a href="edit_expense.jsp?id=<%=expense.getId()%>" class="btn btn-sm btn-primary me-1">Edit</a>
										<a href="../deleteExpense?id=<%=expense.getId() %>" class="btn btn-sm btn-danger me-1">Delete</a>
									</td>
								</tr>

								<%
								}
								%>


							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>