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
    <link rel="icon" type="image/x-icon" href="static/img/icon-1.png" />
    <link rel="stylesheet" type="text/css" href="static/css/common.css">
</head>
<body>
    <div id="intro">
          <span>Welcome to ChatMe采蜜  --由OpenAI提供会话服务，基于GPT-3模型</span>
    </div>

	<h3>全部用户的问题列表，按提问时间倒序排列</h3>
	<table border="1">
	    <thead>
            <tr>
                <th>问题</th>
                <th>回答</th>
                <th>时间</th>
                <th>消耗</th>
            </tr>
    	</thead>
    	<%
    		List<DialogDTO> dialogs = (List<DialogDTO>) request.getAttribute("dialogs");
    		int pageSize = 10;  // 设置每页显示的数据条数
    		int currentPage = 1; // 设置当前页数，默认为1
    		if (request.getParameter("page") != null) {
    			currentPage = Integer.parseInt(request.getParameter("page"));
    		}
    		int startIndex = (currentPage - 1) * pageSize;
    		int endIndex = Math.min(startIndex + pageSize, dialogs.size());
    		for (int i = startIndex; i < endIndex; i++) {
    			DialogDTO data = dialogs.get(i);
    	%>
    	<tr>
    		<td><%= data.getAsk() %></td>
    		<td><%= data.getAnswer() %></td>
    		<td><%= data.getCreateTimeStr() %></td>
    		<td><%= data.getTokens() %></td>
    	</tr>
    	<%
    		}
    	%>
    </table>

    <style>
    	.current-page {
    		background-color: lightblue;
    		padding: 5px 10px;
    	}
    </style>

    <div style="text-align: center;">
    	<%
    		int pageCount = (int) Math.ceil(dialogs.size() * 1.0 / pageSize);
    		if (currentPage > 1) {
    	%>
    		<a href="?page=<%= currentPage - 1 %>">上一页</a>
    	<%
    		}
    		for (int i = 1; i <= pageCount; i++) {
    			String pageClass = "";
    			if (i == currentPage) {
    				pageClass = "current-page";
    			}
    	%>
    		<a href="?page=<%= i %>" class="<%= pageClass %>" style="margin: 0 5px;"><%= i %></a>
    	<%
    		}
    		if (currentPage < pageCount) {
    	%>
    		<a href="?page=<%= currentPage + 1 %>">下一页</a>
    	<%
    		}
    	%>
    </div>
    <br/><br/>

</body>
</html>
