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
    <script type="text/javascript">
        function submitData() {
            var inputValue = document.getElementById("inputData").value;
            var submitBtn = document.getElementById("submitBtn");
            submitBtn.setAttribute("disabled", "disabled");
            submitBtn.style.backgroundColor = "gray";
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "saveData", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.onreadystatechange = function() {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    submitBtn.removeAttribute("disabled");
                    submitBtn.style.backgroundColor = "navy";
                    location.reload();
                }
            };
            xhr.send("ask=" + inputValue);
        }
    </script>
</head>
<body>
    <div style="height: 60px; width: 100%; border: 1px solid green; display: flex; align-items: center; justify-content: center;">
      <span>Welcome to ChatMe采蜜  --由ChatGPT提供会话服务</span>
    </div>

	<h3>输入您的问题，然后点击右侧提问按钮</h3>
    <div style="display: flex; width: 100%;">
      <textarea id="inputData" style="flex: 1;"></textarea>
      <input type="button" id="submitBtn" value="提问" onclick="submitData()" style="background-color: navy; color: white;" />
    </div>
    <h4 style="color:red;">AI回答速度有些慢，请耐心等待...</h3>

	<h3>问题列表，按提文时间倒序排列</h3>
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
	<div>
        <div> 温馨提示：ChatGPT服务也是收费的，收费标准是：（其中token可以理解为字母，1token大致等于4个字母）</div><br/>
        <div> Ada模型--0.0004/1K tokens </div><br/>
        <div> Babbage模型--0.005/1K tokens </div><br/>
        <div> Curie模型--0.002/1K tokens </div><br/>
        <div> Davinci模型--0.02/1K tokens </div><br/>
        <div> 目前是免费试用期内有$18.0元的免费额度，我们用的是Davinci模型，这个模型最贵也最强大，但查询也最慢。请耐心等待以及节省使用 ^-^。</div>
    </div>
</body>
</html>
