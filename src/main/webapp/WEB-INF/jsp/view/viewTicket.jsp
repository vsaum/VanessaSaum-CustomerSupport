<%@ page import="com.example.vanessasaumcustomersupport.Ticket" %>

<html>
<head>
    <title>Ticket #${ticketId}</title>
</head>
<body>
<h2>Ticket Details</h2>
<b>ID:</b> ${ticketId}<br>
<b>Customer Name:</b> ${ticket.customerName}<br>
<b>Subject:</b> ${ticket.subject}<br>
<b>Description:</b> ${ticket.ticketBody}<br>
<b>Attachment:</b> ${ticket.getAttachment(1).getName()}<br>

<c:if test="${ticket.getNumberOfAttachments() > 0}">
    <a href="<c:url value='/ticket'>
                    <c:param name='action' value='downloadAttachment' />
                    <c:param name='ticketId' value='${ticketId}' />
                    <c:param name='attachment' value='${ticket.getAttachment(1).getName()}'/>
                </c:url>">${ticket.getAttachment(1).getName()}</a>
</c:if>
<br>
<br>
<a href="ticket">Back to Ticket List</a>
</body>
</html>
