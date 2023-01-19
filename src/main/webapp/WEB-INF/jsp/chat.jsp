<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>ChatMe采蜜</title>
    </head>
    <body>

    <div style="width: 800px;height: 60px;line-height: 60px;border: 1px solid green;text-align:center;">
      <span>Welcome to ChatMe采蜜  --由ChatGPT提供会话服务</span>
    </div>

        <c:url var="chat_url" value="/chat"/>
        <form:form action="${chat_url}" method="post" modelAttribute="dialog">
            <form:label path="ask">有事您说话: </form:label>
            <form:textarea path="ask" rows="2" cols="60" />
            <!--form:input type="text" path="ask"/-->
            <input type="submit" value="点我提问"/>
        </form:form>

        <div style="width: 800px;border: 1px solid green;">
            <div style="color:red">Question: </div>
            <div>${result.ask}</div>

            <br/><br/>

            <div style="color:red">Answer: </div>
            <div>${result.answer}</div>
        </div>
        <br/><br/>

    <div> -----------------本次问题+回答共消耗 ${result.tokens} tokens----------------- </div>
    <br/><br/>
    <div>
        <div> 温馨提示：ChatGPT服务也是收费的，收费标准是：（其中token可以理解为字母，1token大致等于4个字母）</div><br/>
        <div> Ada模型--0.0004/1K tokens </div><br/>
        <div> Babbage模型--0.005/1K tokens </div><br/>
        <div> Curie模型--0.002/1K tokens </div><br/>
        <div> Davinci模型--0.02/1K tokens </div><br/>
        <div> 目前是免费试用期内有$18.0元的免费额度，我们用的是Davinci模型，这个模型最强大，但还挺贵哦。省着点用吧 ^-^。</div>
    </div>
    </body>
</html>