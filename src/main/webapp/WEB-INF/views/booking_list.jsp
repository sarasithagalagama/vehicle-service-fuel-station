<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Bookings</title>
  </head>
  <body>
    <h2>Bookings</h2>
    <a href="${pageContext.request.contextPath}/bookings/new">Add Booking</a>
    <table border="1">
      <tr>
        <th>ID</th>
        <th>Customer</th>
        <th>Vehicle</th>
        <th>Service Type</th>
        <th>Date</th>
        <th>Actions</th>
      </tr>
      <c:forEach var="b" items="${bookings}">
        <tr>
          <td>${b.id}</td>
          <td>${b.customerName}</td>
          <td>${b.vehicleNumber}</td>
          <td>${b.serviceType}</td>
          <td>${b.bookingDate}</td>
          <td>
            <a href="${pageContext.request.contextPath}/bookings/delete/${b.id}"
              >Delete</a
            >
          </td>
        </tr>
      </c:forEach>
    </table>
  </body>
</html>
