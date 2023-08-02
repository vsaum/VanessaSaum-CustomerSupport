<%@taglib prefix="c" uri="jakarta.tags.core"%>

<html>
<head>
    <title>Ticket Posts</title>
</head>
<body>
    <h2>Ticket Posts</h2>
    <a href="<c:url value='/ticket/create'/>">Create Ticket</a><br><br>
        <c:choose>
            <c:when test="${ticketDatabase.size() == 0}">
                <p>There are no ticket posts yet...</p>
                <a href="<c:url value='/login'>
                 <c:param name='logout'/>
                 </c:url>">Logout</a>
            </c:when>
            <c:otherwise>
                <c:forEach var="ticket" items="${ticketDatabase}">
                    Ticket#: <c:out value="${ticket.key}"/>
                    <a href="<c:url value='/ticket/view/${ticket.key}'/>">
                        <c:out value="${ticket.value.subject}"/></a><br>
                    <br>
                    <a href="<c:url value='/login'>
                 <c:param name='logout'/>
                 </c:url>">Logout</a>
                </c:forEach>
            </c:otherwise>
        </c:choose>
</body>
</html>
