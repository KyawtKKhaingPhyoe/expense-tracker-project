<%@page import="com.dev.entity.Expense"%>
<%@page import="com.dev.db.HibernateUtil"%>
<%@page import="com.dev.dao.ExpenseDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>edit expense</title>
<%@ include file="../components/all_css.jsp"%>
<style type="text/css">
	.card-sh{
		box-shadow: 0 0 6px 0 rgba(0,0,0,0.3);
	}
</style>
</head>
<body class="bg-light">

<c:if test="${empty loginUser }">
	<c:redirect url="../login.jsp"></c:redirect>
</c:if>

<%
Long id=Long.parseLong(request.getParameter("id"));

ExpenseDao expenseDao = new ExpenseDao(HibernateUtil.getSessionFactory());
Expense expense = expenseDao.getExpenseById(id);

%>

	<%@ include file="../components/navbar.jsp"%>

	<div class="container p-5">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="card card-sh">
					<div class="card-header text-center">
						<h1 class="text-center">Edit Expense</h1>
					</div>
					<div class="card-body">
						<form action="../updateExpense" method="post">
							<div class="mb-3">
								<label>Title</label> <input type="text" name="title"
									class="form-control" value="<%= expense.getTitle() %>" />
							</div>
							<div class="mb-3">
								<label>Date</label> <input type="date" name="date"
									class="form-control" value="<%= expense.getDate() %>" />
							</div>
							<div class="mb-3">
								<label>Description</label> <input type="text" name="description"
									class="form-control" value="<%= expense.getDescription() %>" />
							</div>
							<div class="mb-3">
								<label>Amount</label> <input type="text" name="amount"
									class="form-control" value="<%= expense.getAmount() %>" />
							</div>
							
							<input type="hidden" name="id" value="<%= expense.getId() %>" />
							
							<button class="btn btn-primary bg-dark col-md-12" type="submit">Update</button>
							
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>