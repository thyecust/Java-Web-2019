<%@ page language="java" contentType="text/html; charset=gbk"
	pageEncoding="gbk"%>
<%@ page import="com.entity.*,com.dao.*"%>

<%
	UserInfo showLogin = (UserInfo) session.getAttribute("users");
	if (showLogin == null) {
%> 
����δ��¼ <a href="login.jsp">��¼</a>&nbsp;| &nbsp;<A href="reg.jsp">ע��</A>|
<%
	} else {
%>
	��ӭ:&nbsp;<%=showLogin.getUname()%>&nbsp;<a href="ServletManager?action=exit">[�˳�]</a>
<%
	}
	if(showLogin != null && showLogin.getUtype()==2){//�û����� ��ͨ��Ա��0������Ա��2
%>
<a href="manager\login.jsp">[����]</a>
<%
	}
%>
