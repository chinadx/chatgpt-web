<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
<%@ page import = "asia.chatme.chatgpt.dto.DialogDTO"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>ChatMe采蜜</title>
</head>
<body>
    <div style="height: 60px; width: 100%; border: 1px solid green; display: flex; align-items: center; justify-content: center;">
      <span>Welcome to ChatMe采蜜  --由ChatGPT提供会话服务</span>
    </div>

	<h3>全部用户的问题列表，按提文时间倒序排列</h3>
	<table border="1">
		<tr>
			<th>问题</th>
			<th>回答</th>
			<th>时间</th>
			<th>资源消耗tokens</th>
		</tr>
		<%
			List<DialogDTO> dialogs = (List<DialogDTO>) request.getAttribute("dialogs");
			for (DialogDTO data : dialogs) {
		%>
		<tr>
			<td><%= data.getAsk() %></td>
			<td><%= data.getAnswer() %></td>
			<td><%= data.getCreateTime() %></td>
			<td><%= data.getTokens() %></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>
