<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Alarm</title>

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
        <h1>Delete Alarm</h1>
        <p class="lead">Delete the alarm that wasn't in used by the elderly would prevent from passing wrong information to the elderly!</p>
      
      </div>

           <% List<Alarm> alarmJson = (List<Alarm>) request.getAttribute("alarms"); %>

      <form action="/deleteAlarm" method="post">
<table class="table table-striped">
    
    <tr>
        <!-- <td><b>Alarm Id</b></td> -->
        <td><b>Alarm Time</b></td>
        <td><b>Synced to Device</b></td>
		<td><b>Delete Alarm</b></td>
        </tr>

    <% for(int i = 0; i < alarmJson.size(); i+=1) { %>
        <tr>      
           <%--  <td><%=alarmJson.get(i).getAlarmId()%></td> --%>
            <td><%=alarmJson.get(i).getTime()%></td>
            <td><%=alarmJson.get(i).isSyncedToDevice()%></td>
            <td><input type="checkbox" name="alarmId" value="<%=alarmJson.get(i).getAlarmId()%>"></td>
        </tr>
    <% } %>
</table>

<button type="submit" class="btn btn-default">Submit</button>
      </form>
      

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    
      <!-- Site footer -->
      <div class="footer">
        <p>&copy; Nanyang Polytechnic FYPJ 2013</p>
      </div>

    </div> <!-- /container -->

</body>
</html>