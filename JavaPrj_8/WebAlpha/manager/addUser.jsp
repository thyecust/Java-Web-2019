<%@ page language="java" contentType="text/html; charset=gbk"
	pageEncoding="gbk"%>
<%@ page import="java.util.*,com.dao.*,com.entity.*"%>
<%@ include file="checkManagerLogin.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title></title>
</head>
<link href="manager.css" type=text/css rel=stylesheet>


<body>
<DIV style="FONT-SIZE: 14px; PADDING-TOP: 60px; FONT-FAMILY: Arial" align=center>
<DIV align="left">当前操作是：用户添加</DIV>
<HR style="WIDTH: 99%; COLOR: #cccccc; HEIGHT: 1px">
</DIV>
<DIV  class="t" style="MARGIN-TOP: 15px" align="center">
	<FORM name="regForm" onSubmit="return check()" action="../ServletAddUser" method="post">
	<br/>用&nbsp;&nbsp;户&nbsp;&nbsp;名&nbsp;&nbsp;<INPUT class="input" tabIndex="1" type="text" maxLength="20" size="35" name="uName">
		<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码&nbsp;&nbsp;<INPUT class="input" tabIndex="2" type="password" maxLength="20" size="40" name="uPass">
		<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;重复&nbsp;&nbsp;密码&nbsp;&nbsp;<INPUT class="input" tabIndex="3" type="password" maxLength="20" size="40" name="uPass1">
		<br/>性别 &nbsp;
			男<input type="radio" name="gender" value="1">
			女<input type="radio" name="gender" value="2" checked="checked" />
			<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;设置权限 &nbsp;
			管理员<input type="radio" name="uType" value="1">
			普通用户<input type="radio" name="uType" value="2" checked="checked" />
		<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请选择头像 <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<img src="image/head/1.gif"/><input type="radio" name="head" value="1.gif" checked="checked">
		<img src="image/head/2.gif"/><input type="radio" name="head" value="2.gif">
		<img src="image/head/3.gif"/><input type="radio" name="head" value="3.gif">
		<img src="image/head/4.gif"/><input type="radio" name="head" value="4.gif">
		<img src="image/head/5.gif"/><input type="radio" name="head" value="5.gif">
		<BR/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<img src="image/head/6.gif"/><input type="radio" name="head" value="6.gif">
		<img src="image/head/7.gif"/><input type="radio" name="head" value="7.gif">
		<img src="image/head/8.gif"/><input type="radio" name="head" value="8.gif">
		<img src="image/head/9.gif"/><input type="radio" name="head" value="9.gif">
		<img src="image/head/10.gif"/><input type="radio" name="head" value="10.gif">
		<BR/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<img src="image/head/11.gif"/><input type="radio" name="head" value="11.gif">
		<img src="image/head/12.gif"/><input type="radio" name="head" value="12.gif">
		<img src="image/head/13.gif"/><input type="radio" name="head" value="13.gif">
		<img src="image/head/14.gif"/><input type="radio" name="head" value="14.gif">
		<img src="image/head/15.gif"/><input type="radio" name="head" value="15.gif">
		<br/>
			<INPUT class="btn" tabIndex="4" type="submit" value="添加" >
</FORM>
</DIV>
	  <p></p>
		<DIV style="FONT-SIZE: 11px; PADDING-TOP: 60px; FONT-FAMILY: Arial" align=center>
<HR style="WIDTH: 600px; COLOR: #cccccc; HEIGHT: 1px">
版权信息
</DIV>
</body>
</html>
