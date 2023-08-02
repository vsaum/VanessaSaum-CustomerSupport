
<html>
<head>
    <title>Ticket #<c:out value="${ticketId}"/></title>
</head>
<body>
    <h2>Ticket Details</h2>
    <h3>ID: <c:out value="${ticketId}"/></h3>
    <p>Customer Name: <c:out value="${ticket.customerName}"/></p>
    <p>Subject: <c:out value="${ticket.subject}"/></p>
    <p>Description: <c:out value="${ticket.ticketBody}"/></p>
    <c:if test="${ticket.hasAttachment()}">
        <a href="<c:url value='/ticket/${ticketId}/attachment/${ticket.attachment.name}' />">
        <c:out value="${ticket.attachment.name}"/></a><br>
    </c:if>
    <br><a href="<c:url value='/ticket/list'/>">Return to ticket list</a><br><br>
        <a href="<c:url value='/login'>
                 <c:param name='logout'/>
                 </c:url>">Logout</a>

</body>
</html>