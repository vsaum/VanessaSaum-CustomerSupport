<html>
<head>
    <title>Ticket Posts</title>
</head>
<body>
    <h2>Ticket Posts</h2>
        <a href="<c:url value='/ticket'>
            <c:param name='action' value='createTicket' />
        </c:url>">Create Ticket</a><br><br>
        <c:choose>
            <c:when test="${ticketDatabase.size() == 0}">
                <p>There are no ticket posts yet...</p>
            </c:when>
            <c:otherwise>
                <c:forEach var="ticket" items="${ticketDatabase}">
                    Ticket#: <c:out value="${ticket.key}"/><br>
                    <a href="<c:url value='/ticket' >
                            <c:param name='action' value='view' />
                            <c:param name='ticketId' value='${ticket.key}' />
                        </c:url>"><c:out value="${ticket.value.subject}"/></a><br>
                    <br>
                </c:forEach>
            </c:otherwise>
        </c:choose>
</body>
</html>
