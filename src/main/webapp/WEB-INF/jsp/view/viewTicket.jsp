<%@ taglib prefix="c" uri="jakarta.tags.core"%>

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
    <c:if test="${attachment.hasAttachment()}">
        <a href="<c:url value='/ticket' >
            <c:param name='action' value='download' />
            <c:param name='ticketId' value='${ticketId}' />
            <c:param name='attachment' value='${ticket.attachment.name}'/>
        </c:url>"><c:out value="${ticket.attachment.name}"/></a>
    </c:if>
    <br><a href="ticket">Return to ticket list</a>

</body>
</html>