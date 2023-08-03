<head>
    <title>New ticket</title>
</head>
<body>
  <h2>Create a new ticket</h2>

  <form:form method="POST" action="create" modelAttribute="ticket" enctype="multipart/form-data">
    <form:label path="customerName">Customer Name: </form:label><br>
    <form:input path="customerName"/><br><br>
    <form:label path="subject">Subject: </form:label><br>
    <form:input path="subject"/><br><br>
    <form:label path="ticketBody">Ticket Body: </form:label><br>
    <form:textarea path="ticketBody" rows="5" cols="15"/><br><br>
    <b>Attachment:</b><br>
    <form:input path="attachment" type="file"/><br><br>
    <input type="submit" value="Submit"><br><br>
    <a href="<c:url value='/logout'/>">Logout</a>
  </form:form>

</body>
