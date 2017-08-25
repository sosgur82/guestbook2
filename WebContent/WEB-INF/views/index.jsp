<%@page import="com.bigdata2017.guestbook.vo.GuestbookVo"%>
<%@page import="com.bigdata2017.guestbook.dao.GuestbookDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% 
List<GuestbookVo> list = (List<GuestbookVo>)request.getAttribute("guest");
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/gb?a=insert" method="post">
	<table border="1" width="500">
		<tr>
			<td>이름</td><td><input type="text" name="name"></td>
			<td>비밀번호</td><td><input type="password" name="pass"></td>
		</tr>
		<tr>
			<td colspan="4"><textarea name="content" cols="60" rows="5"></textarea></td>
		</tr>
		<tr>
			<td colspan="4" align="right"><input type="submit" VALUE=" 확인 "></td>
		</tr>
	</table>
	</form>
	<br>
	<% int count = list.size();
	for(GuestbookVo vo : list){%>
	<table width="510" border="1">
		<tr>
			<td>[<%=count%>]</td>
			<td><%=vo.getName() %></td>
			<td><%=vo.getReg_date() %></td>
			<td><a href="<%=request.getContextPath()%>/gb?a=form&no=<%=vo.getNo()%>">삭제</a></td>
		</tr>
		<tr>
			<td colspan="4"><%=vo.getContent() %></td>
		</tr>
	</table>
	<br>
	<% count--;
	} %>
</body>
</html>