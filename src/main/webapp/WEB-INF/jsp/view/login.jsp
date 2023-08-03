<html>
<head>
    <title>Ticket Login</title>
</head>
<body>
  <h2>Login</h2>
  You must log in to create a ticket.<br><br>
  <c:if test="${loginFailed == true}">
      <b><c:out value="The username or password are incorrect. Try again"></c:out></b>
  </c:if>
  <form:form method="POST" action="login" modelAttribute="loginForm">
      <form:label path="username">Username:&nbsp;</form:label><br>
      <form:input path="username"/><br><br>
      <form:label path="password">Password:&nbsp;</form:label><br>
      <form:input path="password"/><br><br>
      <input type="submit" value="Login">
  </form:form>
</body>
</html>
