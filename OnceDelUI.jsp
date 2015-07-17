<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="exTwitter.OnceBean"%>
<%@ page import="exTwitter.Once"%>
<%@page import="java.util.ArrayList"%>
<% request.setCharacterEncoding("UTF-8"); %>

<html>
	<link rel="stylesheet" type="text/css" href="style.css">
	<head>
		<title>単発ツイート削除</title>
	</head>
	
	<jsp:include page="header.jsp" />
	
	<div id="a">
		<div id="label">
			単発ツイート削除
		</div>
		<form method="post" action="OnceDel" onSubmit="return delBtn()">
			<center>
				<table border="2" style="font-size:20">
					<tr>
						<th>　　　</th>
						<th>　ツイート　</th>
						<th>　予約日　</th>
						<th>　予約時間　</th>
					</tr>
					
					<%
						if(Once.onceList != null){
							for(int i=0;i<Once.onceList.size();i++){
								out.println("<tr>");
								out.println("<td align=\"center\">　<input type=\"submit\" style=\"width:50px;height:30px;font-size:15\" value=\"削除\" name=\"" + Once.onceList.get(i).getOnceId() + "\">　</td>");
								out.println("<td align=\"center\">" + Once.onceList.get(i).getText() + "</td>");
								out.println("<td align=\"center\">" + Once.onceList.get(i).getDate() + "</td>");
								out.println("<td align=\"center\">" + Once.onceList.get(i).getTime() + "</td>");
								out.print("</tr>");
							}
						}
					%>
				
				</table>
			</form>
			<br>
			<form method="get" action="Once">
				<input type="submit" style="width:100px;height:40px;font-size:20" value="戻る">
			</form>
		</center>
	</div>
