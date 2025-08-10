<html>
  <head>
    <title>New Booking</title>
  </head>
  <body>
    <h2>New Booking</h2>
    <form action="${pageContext.request.contextPath}/bookings" method="post">
      <label>Customer Name:</label>
      <input type="text" name="customerName" required /><br />

      <label>Vehicle Number:</label>
      <input type="text" name="vehicleNumber" required /><br />

      <label>Service Type:</label>
      <input type="text" name="serviceType" required /><br />

      <button type="submit">Save</button>
    </form>
  </body>
</html>
