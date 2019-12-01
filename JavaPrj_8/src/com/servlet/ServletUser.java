package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.ManagerDAO;
import com.dao.UserInfoDAO;
import com.entity.UserInfo;
import com.match.CheckDAO;

public class ServletUser extends HttpServlet {
	
	private ServletConfig config = null;
	private UserInfo mUser = null;
	private UserInfoDAO udao = new UserInfoDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("gb2312");			//设置请求编码格式
		response.setCharacterEncoding("gb2312");		//设置响应编号格式
		response.setContentType("text/html");			//设置响应文本类型
		PrintWriter out = response.getWriter();			//获得响应输出流
		Integer uid = Integer.parseInt(request.getParameter("uid"));
		Boolean flag = udao.delUser(uid);
		if (!flag) {// 删除失败
			out.print("<script>" + "alert(\'你只能删除没有发表过板块、主题和评论的用户！\');" + "window.history.back();"+ "</script>");
			
			} else {// 删除成功
				response.sendRedirect("manager/managerUser.jsp");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	public void init(ServletConfig config) throws ServletException {
		this.config = config;
	}

}
