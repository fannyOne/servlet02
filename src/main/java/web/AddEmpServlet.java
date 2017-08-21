package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeDAO;
import entity.Employee;

public class AddEmpServlet extends HttpServlet{
	
	public void service(
			HttpServletRequest request,
			HttpServletResponse response)
	throws ServletException,IOException{
		//设置解码所使用的字符集，注意要与客户端保持一致
		request.setCharacterEncoding("utf-8");
		
		//告诉浏览器，服务器返回的数据类型
		//(通过设置content-type消息头的值)
		response.setContentType(
				"text/html;charset=utf-8");
		//通过response(响应对象)获得一个输出流
		PrintWriter out = 
				response.getWriter();
		//调用request对象提供的方法来读取请求参数值
		String name = 
				request.getParameter("name");
		String salary = 
				request.getParameter("salary");
		String age = 
				request.getParameter("age");
		
		//一般来说，服务器端应该对请求参数值做一些
		//合法性检查，比如检查姓名是否为空，这儿
		//暂时不做。
		
		//将员工信息插入到数据库
		EmployeeDAO dao = new EmployeeDAO();
		Employee e = new Employee();
		e.setName(name);
		e.setSalary(Double.parseDouble(salary));
		e.setAge(Integer.parseInt(age));
		try{
			dao.save(e);
			//重定向
			out.println("插入成功");
			response.sendRedirect("list");
			System.out.println("重定向之后的代码...");
			
		}catch(Exception e1){
			e1.printStackTrace();
			out.println("<h1>系统繁忙，稍后重试</h1>");
		}
		//关闭流。
		out.close();
		
	}
}





