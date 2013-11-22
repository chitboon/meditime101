<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Medicine History</title>

        <link rel="stylesheet" href="../css/bootstrap.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="../css/sbootstrap-theme.css" type="text/css" media="screen" />
        <script src="../js/bootstrap.js"></script>
        <%@page import = "model.*"  %>
<%@page import = "java.util.*"  %>
<%@page import = "controller.*" %>
</head>
<body>

<body>

<div class="container">

      <div class="masthead">
        <h3 class="text-muted">MediTime</h3>
        <ul class="nav nav-justified">
          <li class="active"><a href="index.html">Home</a></li>
          <li><a href="fitbitApiAuthExample">Personal Information</a></li>
          <li><a href="#">Alarm</a></li>
          <li><a href="medicine.jsp">Medicine</a></li>
        </ul>
      </div>

      <!-- Jumbotron -->
      <div class="jumbotron">
        <h1>History Record of Medicine</h1>
        <p class="lead">By keep track of the past medication, it would allow the caregiver to understand the elderly better. </p>
        <!-- <p><a class="btn btn-lg btn-success" href="#" role="button">Get started today</a></p> -->
      </div>

      <!-- Example row of columns -->
      <% List<MedicineData> medicineJson = (List<MedicineData>) request.getAttribute("medicine"); %> 

      <form action="/medicine.jsp" method="get">
<table class="table table-striped">
    <tr>
        <td><b>Medicine Name</b></td>
        <td><b>Start Date</b></td>
        <td><b>Finish Date</b></td>
        <td><b>Tablet</b></td>
        <td><b>Consume Per Day</b></td>
        </tr>

        <% for(int i = 0; i < medicineJson.size(); i+=1) { %>
        <tr>      
            <td><%=medicineJson.get(i).getName()%></td>
            <td><%=medicineJson.get(i).getStartDate()%></td>
            <td><%=medicineJson.get(i).getEndDate()%></td>
            <td><%=medicineJson.get(i).getTablet()%></td>
            <td><%=medicineJson.get(i).getDuration()%></td>
        </tr>
    <% } %>
</table>

  <button type="submit" class="btn btn-default">Home</button>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    
      <!-- Site footer -->
      <div class="footer">
        <p>&copy; Nanyang Polytechnic FYPJ 2013</p>
      </div>
</form>
    </div> <!-- /container -->


</body>
</html>