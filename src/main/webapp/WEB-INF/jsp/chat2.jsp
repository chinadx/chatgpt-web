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

        function deleteRow(id, btn) {
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "deleteData", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.onreadystatechange = function() {
              if (xhr.readyState == 4 && xhr.status == 200) {
                  var row = btn.parentNode.parentNode;
                  row.parentNode.removeChild(row);
                  location.reload();
              }
            };
            xhr.send("id=" + id);
        }

    </script>
</head>
<body>
    <div id="image-container">
      <img src="static/img/bee.png" alt="bee">
    </div>

    <div id="intro">
      <span id="intro1">采蜜 ChatMe</span> &nbsp;&nbsp;&nbsp;&nbsp; <span id="intro2">--由OpenAI提供会话服务</span>
    </div>

    <br/>
    <br/>

    <div style="display: flex; width: 100%;">
      <textarea id="inputData" style="flex: 1; border-radius: 10px;"></textarea>
      <input type="button" id="submitBtn" value="提问" onclick="submitData()" style="background-color: navy; color: white;" />
    </div>
    <h4 style="color:red;">AI回答速度有些慢，请耐心等待...</h3>

	<h3>问题列表，按提问时间倒序排列</h3>
	<table border="1">
        <thead>
            <tr>
                <th>问题</th>
                <th>回答</th>
                <th>时间</th>
                <th>消耗</th>
                <th>操作</th>
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
            <td><button onclick="deleteRow(<%= data.getId() %>, this)">删除</button></td>
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

    <%
    //只有页面超过1页时，才展示下面的分页信息
    if (dialogs.size() > pageSize) {
    %>
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
    <%
    }
    %>
	<br/>
	<br/>
	<!--
	<div id="tips">
	    <div> 温馨提示1：目前ChatGPT尚未开放API访问，采蜜封装的为OpenAI的上一代AI能力即GPT-3，而ChatGPT实际为GPT的第3.5代。但两者的AI能力相差不是很大，只是ChatGPT针对聊天功能做了深度优化。如想体验ChatGPT的完整功能，请访问其官方网站<a href="https://chat.openai.com/chat">ChatGPT</a>，（需科学上网，并选择欧美日韩地区） </div>
        <br/>
        <div> 温馨提示2：尽管ChatGPT官网直接向AI提问是免费的，但通过GPT-3的API接口调用需要按调用量收费，收费标准为：$0.02/1K tokens（其中token可以理解为字母，1token大致等于4个字母）</div>
        <a href = "https://openai.com/api/pricing/">官方价目说明</a>
        <br/>
        <div> 目前每个账号免费试用期内有$18.0元的免费额度，请节省使用 ^-^。</div>
    </div>
    <br/>
    <div id="tips"> ↓↓↓本账号截至02/14账单，供参考 </div>
    <div style="text-align: left;">
    	<img src="static/img/bill.png" alt="截至02/14账单"  style="width: 50%;">
    </div>
    -->

    <div style="text-align: center; font-size: 14px;">
        <p>
            <a href="https://beian.miit.gov.cn" target="_blank">京ICP备2023002811号</a>
            | © 2023 采蜜. All rights reserved.
        </p>
    </div>


</body>
</html>
