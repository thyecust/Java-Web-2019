<%@ page language="java" contentType="text/html; charset=gbk"
	pageEncoding="gbk"%>
<%@ page import="java.util.*,com.dao.*,com.page.*,com.entity.*"%>
<%@ include file="checkManagerLogin.jsp"%>
<%
	//��ʼ������
	List<UserInfo> listUser = null;
	UserInfoDAO user_dao = new UserInfoDAO();
//	ManagerDAO manager_dao = new ManagerDAO();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk"/>
<title>�û�����</title>
</head>

<style>
body{
	font-size:12px;
}

a{
	color:#000000;
	text-decoration: none;
}

a:hover{
	color:#FF0000;
	text-decoration:underline;
}
</style>

<script type="text/javascript">
	function check(){
		return false;
	}
</script>

<body>
<DIV style="FONT-SIZE: 14px; PADDING-TOP: 60px; FONT-FAMILY: Arial" align=center>
<DIV align="left">��ǰ�����ǣ��û�����</DIV>
<HR style="WIDTH: 99%; COLOR: #cccccc; HEIGHT: 1px;">
</DIV>
<p></p><p></p>
	<div>
	<table width="95%" border="1" cellspacing="1" cellpadding="5" align="center">
        <tr>
         <th>�û�ID</th>
         <th>�û���</th>
         <th>Ȩ��</th>
         <th>����</th>
         <th>�Ա�</th>
         <th>ע��ʱ��</th>
         <th>��������</th>
         <th>����������</th>
         <th>����������</th>
         <th>����</th>
        </tr>
<% 
        listUser=user_dao.getUserById();
      // out.println("<h3>"+listUser.size()+"</h3>");
       
		for(int i=0;i<listUser.size();i++){
			//out.println("<h3>"+listUser.get(i).getUname()+"</h3>");
%>

        <tr>
                <td><% out.println("<h3>"+listUser.get(i).getUid()+"</h3>"); %></td>
                <td><% out.println("<h3>"+listUser.get(i).getUname()+"</h3>"); %></td>
                <td><% out.println("<h3>"+user_dao.getUserTypeName(listUser.get(i).getUtype())+"</h3>"); %></td>
                <td><% out.println("<h3>"+listUser.get(i).getUpassword()+"</h3>"); %></td>
                <td><% out.println("<h3>"+user_dao.getSexName(listUser.get(i).getUsex())+"</h3>"); %></td>
                <td><% out.println("<h3>"+listUser.get(i).getUregtime()+"</h3>"); %></td>
                <td><% out.println("<h3>"+user_dao.CountSection(listUser.get(i).getUid())+"</h3>"); %></td>
                <td><% out.println("<h3>"+user_dao.CountTopic(listUser.get(i).getUid())+"</h3>"); %></td>
                <td><% out.println("<h3>"+user_dao.CountReply(listUser.get(i).getUid())+"</h3>"); %></td>
                <td><a href="../ServletUser?uid=<%=listUser.get(i).getUid()%>">ɾ��</a></td>
        </tr>
        <% }%>
</table>
	</div>
	<DIV style="FONT-SIZE: 11px; PADDING-TOP: 60px; FONT-FAMILY: Arial" align=center>
<HR style="WIDTH: 600px; COLOR: #cccccc; HEIGHT: 1px;">��Ȩ��Ϣ
</DIV>
</body>
</html>