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
		request.setCharacterEncoding("gb2312");			//������������ʽ
		response.setCharacterEncoding("gb2312");		//������Ӧ��Ÿ�ʽ
		response.setContentType("text/html");			//������Ӧ�ı�����
		PrintWriter out = response.getWriter();			//�����Ӧ�����
		Integer uid = Integer.parseInt(request.getParameter("uid"));
		Boolean flag = udao.delUser(uid);
		if (!flag) {// ɾ��ʧ��
			out.print("<script>" + "alert(\'��ֻ��ɾ��û�з������顢��������۵��û���\');" + "window.history.back();"+ "</script>");
			
			} else {// ɾ���ɹ�
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
