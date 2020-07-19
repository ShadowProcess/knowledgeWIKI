<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/8/13
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h4>El表达式的运算</h4>
<%
    request.setAttribute("n1", 10);
    request.setAttribute("n2", 20);
    request.setAttribute("n3", 30);
    request.setAttribute("n4", 40);
%>

//加法运算
${n1 + n2}

//关系运算,大于
${n1 > n2} ${n1 gt n2}

//关系运算,小于
${n1 < n2} ${n1 lt n2}

//等于
${n1 == n2} ${n1 eq n2}

//不等于
${n1 != n2} ${n1 ne n2}


//大于等于
${n1 >= n2} ${n1 ge n2}

//小于等于
${n1 <= n2} ${n1 le n2}


//逻辑运算
${n1 > n2 && n3 > n4}
${n1 > n2 and n3 > n4}

${n1 > n2 || n3 > n4}
${n1 > n2 or n3 > n4}

${!(n1 > n2)}
${ not (n1>n2)}

</body>
</html>
