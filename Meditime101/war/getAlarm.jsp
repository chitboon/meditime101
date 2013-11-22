<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Alarm</title>

        <link rel="stylesheet" href="../css/bootstrap.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="../css/sbootstrap-theme.css" type="text/css" media="screen" />
        <script src="../js/bootstrap.js"></script>
        <%@page import = "model.*"  %>
<%@page import = "java.util.*"  %>
<%@page import ="controller.*" %>

</head>
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
        <h1>View Alarm</h1>
        <p class="lead">By providing the time and interval between the medicines, it would result in vibrate on the fitbit flex which would then be worn by the elderly to remind them to eat their medicine. </p>
        <!-- <p><a class="btn btn-lg btn-success" href="#" role="button">Get started today</a></p> -->
      </div>


      <!-- Example row of columns -->
      	
           <% List<Alarm> alarmJson = (List<Alarm>) request.getAttribute("alarms"); %>
           <% List<MedicineData> medicineJson = (List<MedicineData>) request.getAttribute("medicine"); %>
      <form action="/index.html" method="get">
<table class="table table-striped">
    <tr>
        <!-- <td><b>Full Name</b></td> -->
        <td><b>Alarm Time</b></td>
        <td><b>Synced to Device</b></td>
        </tr>

        <% for(int i = 0; i < alarmJson.size(); i+=1) { %>
        <tr>      
           <%--  <td><%=alarmJson.get(i).getAlarmId()%></td> --%>
            <td><%=alarmJson.get(i).getTime()%></td>
            <td><%=alarmJson.get(i).isSyncedToDevice()%></td>
        </tr>
    <% } %>
</table>
  <button type="submit" class="btn btn-default">Home</button>
      </form>

<%-- <table class="table table-striped">
       <tr>
        <td>Alarm Id :</td>
        <td> <%= request.getAttribute("alarmId") %></td>
    </tr> 
         <tr>
        <td>Time :</td>
        <td> <%= request.getAttribute("time") %></td>
    </tr> 
            <tr>
        <td>Recurring :</td>
        <td> <%= request.getAttribute("recurring") %></td>
    </tr>   
        <tr>
        <td>Deleted :</td>
        <td> <%= request.getAttribute("deleted") %></td>
    </tr>  
        <tr>
        <td>Enabled :</td>
        <td> <%= request.getAttribute("enabled") %></td>
    </tr>   
 
        <tr>
        <td>Synced To Device :</td>
        <td> <%= request.getAttribute("syncedToDevice") %></td>
    </tr> 

       <tr>
        <td>Snooze Count :</td>
        <td> <%= request.getAttribute("snoozeCount") %></td>
    </tr>
       <tr>
        <td>Snooze Length :</td>
        <td> <%= request.getAttribute("snoozeLength") %></td>
    </tr>
</table> --%>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    
      <!-- Site footer -->
      <div class="footer">
        <p>&copy; Nanyang Polytechnic FYPJ 2013</p>
      </div>

    </div> <!-- /container -->


</body>
</html>