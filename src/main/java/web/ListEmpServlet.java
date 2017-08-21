package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeDAO;
import entity.Employee;

public class ListEmpServlet extends HttpServlet{
	public void service(
			HttpServletRequest request,
			HttpServletResponse response)
	throws ServletException,IOException{
		response.setContentType(
				"text/html;charset=utf-8");
		PrintWriter out = 
				response.getWriter();
		//访问数据库，获取所有员工信息
		EmployeeDAO dao = 
				new EmployeeDAO();
		try{
			List<Employee> employees = 
					dao.findAll();
			//依据查询到的员工信息，输出表格
			out.println(
				"<table border='1' "
				+ "width='60%' cellpadding='0' "
				+ "cellspacing='0'>");
			out.println("<tr><td>ID</td>"
					+ "<td>姓名</td><td>薪水</td>"
					+ "<td>年龄</td><td>操作</td></tr>");
			for(int i = 0; i < employees.size(); i ++){
				Employee e = employees.get(i);
				out.println(
				"<tr><td>" + e.getId() + "</td>"
				+ "<td>" + e.getName() + "</td>"
				+ "<td>" + e.getSalary() + "</td>"
				+ "<td>" + e.getAge() + "</td>"
				+ "<td><a href='del?id=" + e.getId() + "'>删除</a></td></tr>");
			}
			out.println("</table>");
			out.println("<h1><a href='addEmp.html'>添加员工</a></h1>");
		}catch(Exception e){
			e.printStackTrace();
			out.println("系统繁忙，稍后重试");
		}
		//可以不调用close方法，容器会自动调用close方法。
		out.close();
		
		
	}
}







