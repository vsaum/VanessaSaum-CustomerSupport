<head>
    <title>New ticket</title>
</head>
<body>
  <h2>Create a new ticket</h2>
  <form method="POST" action="ticket" enctype="multipart/form-data">
    <input type="hidden" name="action" value="create">
    Customer Name:<br>
    <input type="text" name="customerName" required><br><br>
    Subject:<br>
    <input type="text" name="subject" required><br><br>
    Ticket Body:<br>
    <textarea name="ticketBody" rows="5" cols="15" required></textarea><br><br>
    <b>Attachment:</b><br>
    <input type="file" name="file1"><br><br>
    <input type="submit" value="Submit">
  </form>
</body>
</html>
