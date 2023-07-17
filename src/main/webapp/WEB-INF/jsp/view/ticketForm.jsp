<head>
    <title>New ticket</title>
</head>
<body>
  <h2>Create a new ticket</h2>
  <form method="post" action="ticket" enctype="multipart/form-data">
    <input type="hidden" name="action" value="createTicket">
    <label for="customerName">Customer Name:</label><br>
    <input type="text" id="customerName" name="customerName" required><br><br>
    <label for="subject">Subject:</label><br>
    <input type="text" id="subject" name="subject" required><br><br>
    <label for="ticketBody">Ticket Body:</label><br>
    <textarea id="ticketBody" name="ticketBody" required></textarea><br><br>
    <label for="attachment">Attachment:</label><br>
    <input type="file" id="attachment" name="attachment"><br><br>
    <input type="submit" value="Submit">
  </form>
</body>
</html>
