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
		passWord1 = request.getParameter("uPass1");// �ظ�����
		// ��֤�û��������볤��
		if (userName == null || passWord == null || passWord1 == null) {
			out.print("<script>" + "alert('�û����������벻��Ϊ��');"
					+ "window.history.back();" + "</script>");
			return;
		}
		if (!check_dao.checkUserName(userName)) {
			out.print("<script>" + "alert('�û���ֻ���������ġ����֡���ĸ������Ϊ2-10');"
					+ "window.history.back();" + "</script>");
			return;
		}
		if (check_dao.checkUserNameIsExist(userName)) {
			out.print("<script>" + "alert('�û����Ѿ�����');"
					+ "window.history.back();" + "</script>");
			return;
		}
		if (!check_dao.CheckUserPassWord(passWord)) {
			out.print("<script>" + "alert('����ֻ���������֡���ĸ������Ϊ3-20');"
					+ "window.history.back();" + "</script>");
			return;
		}
		if (!passWord.equals(passWord1)) {
			out.print("<script>" + "alert('2�����벻һ��');"
					+ "window.history.back();" + "</script>");
			return;
		}
		sex = request.getParameter("gender");
		if (sex.equals("1")) {// Ů
			sexType = true;
		} else {// ��
			sexType = false;
		}
		
		type=request.getParameter("uType");
		if (type.equals("1")) {// ����Ա
			userType =2;
		} else {// ��ͨ�û�
			userType = 0;
		}
		userFace = request.getParameter("head");
		//
		if (udao.checkAddUser(userName, passWord, sexType, userFace, userType)) {
			//
			out.print("<script>" + "alert('��ӳɹ���');"
					+ "window.history.back();" + "</script>");
		} else {
			//
			out.println("���ʧ�ܣ�");
		}
	}

	public void init(ServletConfig config) throws ServletException {
		this.config = config;
	}
}
