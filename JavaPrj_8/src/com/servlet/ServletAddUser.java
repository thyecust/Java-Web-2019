package com.servlet;

import com.dao.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.match.*;

public class ServletAddUser extends HttpServlet {

	private UserInfoDAO udao = new UserInfoDAO();
	private ServletConfig config;
	private CheckDAO check_dao = new CheckDAO();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = "";
		String passWord = "";
		String passWord1 = "";
		String sex = "";
		String type= "";
		boolean sexType = false;
		Integer userType = 0 ;
		String userFace = "";
		String s = config.getInitParameter("character");
		//
		response.setContentType("text/html");
		request.setCharacterEncoding(s);
		response.setCharacterEncoding(s);
		PrintWriter out = response.getWriter();
		//
		userName = request.getParameter("uName");
		passWord = request.getParameter("uPass");
		passWord1 = request.getParameter("uPass1");// 重复密码
		// 验证用户名和密码长度
		if (userName == null || passWord == null || passWord1 == null) {
			out.print("<script>" + "alert('用户名或者密码不能为空');"
					+ "window.history.back();" + "</script>");
			return;
		}
		if (!check_dao.checkUserName(userName)) {
			out.print("<script>" + "alert('用户名只能输入中文、数字、字母，长度为2-10');"
					+ "window.history.back();" + "</script>");
			return;
		}
		if (check_dao.checkUserNameIsExist(userName)) {
			out.print("<script>" + "alert('用户名已经存在');"
					+ "window.history.back();" + "</script>");
			return;
		}
		if (!check_dao.CheckUserPassWord(passWord)) {
			out.print("<script>" + "alert('密码只能输入数字、字母，长度为3-20');"
					+ "window.history.back();" + "</script>");
			return;
		}
		if (!passWord.equals(passWord1)) {
			out.print("<script>" + "alert('2次密码不一样');"
					+ "window.history.back();" + "</script>");
			return;
		}
		sex = request.getParameter("gender");
		if (sex.equals("1")) {// 女
			sexType = true;
		} else {// 男
			sexType = false;
		}
		
		type=request.getParameter("uType");
		if (type.equals("1")) {// 管理员
			userType =2;
		} else {// 普通用户
			userType = 0;
		}
		userFace = request.getParameter("head");
		//
		if (udao.checkAddUser(userName, passWord, sexType, userFace, userType)) {
			//
			out.print("<script>" + "alert('添加成功！');"
					+ "window.history.back();" + "</script>");
		} else {
			//
			out.println("添加失败！");
		}
	}

	public void init(ServletConfig config) throws ServletException {
		this.config = config;
	}
}
