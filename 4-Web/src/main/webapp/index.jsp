<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Program</title>
</head>
<body>
<div style="text-align: center;">
    <h1>TEXT ANALYZER</h1>
    <p>Type in text to be analyzed and press run!</p>
    <form action="${pageContext.request.contextPath}/input" method="POST">
        <label>
            <input type="text" name="inputText" value="">
        </label>
        <input type="submit" name="button" value="RUN">
    </form>
</div>
</body>
</html>