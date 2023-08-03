<html>
<head>
    <title>Ticket Session Admin View</title>
</head>
<body>
  <a href="<c:url value='/logout'/>">Logout</a>
  <h2>Sessions</h2>
      Total Active Sessions: <c:out value="${numSessions}"/>
      <ul>
        <c:forEach items="${sessionList}" var="s">
          <li><c:out value="${s.id} - ${s.getAttribute('username')} -
                        last active ${(now-s.getLastAccessedTime())/1000} second ago"/></li>
        </c:forEach>
      </ul>
</body>
</html>
