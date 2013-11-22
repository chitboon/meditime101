<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pending Alarm</title>

         <link rel="stylesheet" href="../css/bootstrap.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="../css/sbootstrap-theme.css" type="text/css" media="screen" />
        <script src="../js/bootstrap.js"></script>
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
        <h1>Set Alarm</h1>
        <p class="lead">By providing the time and interval between the medicines, it would result in vibrate on the fitbit flex which would then be worn by the elderly to remind them to eat their medicine. </p>
        <!-- <p><a class="btn btn-lg btn-success" href="#" role="button">Get started today</a></p> -->
      </div>

      <!-- Example row of columns -->

<form class="form-horizontal" role="form" action="/setAlarm" method="post">

<table class="table table-striped">
<tr>
<td><b>Time :</b></td>
<td> <%= request.getParameter("alarmTime") %></td>
</tr>

<tr>
<td><b>Name of Medicine :</b></td>
<td> <%= request.getParameter("name") %></td>
</tr>

<tr>
<td><b>Number of Tablets :</b></td>
<td> <%= request.getParameter("tablet") %></td>
</tr>

<tr>
<td><b>Starting Date :</b></td>
<td> <%= request.getParameter("date") %></td>
</tr>

<tr>
<td><b>Interval :</b></td>
<td>     <% if (request.getParameter("duration1").equals("once")) { %>
<div>Once per Day</div>
<% } else if(request.getParameter("duration1").equals("twice")){ %>
<div>Every 12 hours</div>
<% } else if(request.getParameter("duration1").equals("thrice")){ %>
<div>Every 8 hours</div>
<% } else if(request.getParameter("duration1").equals("quad")){ %>
<div>Every 6 hours</div>
<%} %></td>
</tr>
</table> 
      <button type="submit" class="btn btn-default">Submit</button>

<input type="hidden" class="form-control" id="alarmTime" name="alarmTime" value="<%= request.getParameter("alarmTime") %>">
<input type="hidden" class="form-control" id="alarmTime" name="name" value="<%= request.getParameter("name") %>">
<input type="hidden" class="form-control" id="tablets"  name="tablet" value ="<%= request.getParameter("tablet") %>">
<input type="hidden" class="form-control" id="date"  name="date" value="<%= request.getParameter("date") %>">
<input type="hidden" class="form-control" id="date"  name="duration1" value="<% if (request.getParameter("duration1").equals("once")) { %>
once
<% } else if(request.getParameter("duration1").equals("twice")){ %>twice
<% } else if(request.getParameter("duration1").equals("thrice")){ %>thrice
<% } else if(request.getParameter("duration1").equals("quad")){ %>quad
<%} %>">
 

</form>
  <%-- <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">Time :</label>
    <div class="col-sm-10">
    <%= request.getParameter("alarmTime") %>
      <input type="hidden" class="form-control" id="alarmTime" name="alarmTime" value="<%= request.getParameter("alarmTime") %>">
    </div>
  </div>
  
 <!-- MEDICINE -->
 <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">Name of Medicine : </label>
    <div class="col-sm-10">
    <%= request.getParameter("name") %>
      <input type="hidden" class="form-control" id="alarmTime" name="name" value="<%= request.getParameter("name") %>">
    </div>
  </div>
  
 <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">Number of Tablets : </label>
    <div class="col-sm-10">
    <%= request.getParameter("tablet") %>
      <input type="hidden" class="form-control" id="tablets"  name="tablet" value ="<%= request.getParameter("tablet") %>">
    </div>
  </div>
  
 <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">Starting Date : </label>
    <div class="col-sm-10">
    <%= request.getParameter("date") %>
      <input type="hidden" class="form-control" id="date"  name="date" value="<%= request.getParameter("date") %>">
    </div>
  </div> 
  
    <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">Interval : </label>
    <div class="col-sm-10">
    <% if (request.getParameter("duration1").equals("once")) { %>
<div>Once per Day</div>
<% } else if(request.getParameter("duration1").equals("twice")){ %>
<div>Every 12 hours</div>
<% } else if(request.getParameter("duration1").equals("thrice")){ %>
<div>Every 8 hours</div>
<% } else if(request.getParameter("duration1").equals("quad")){ %>
<div>Every 6 hours</div>
<%} %>
      <input type="hidden" class="form-control" id="date"  name="duration1" value="<% if (request.getParameter("duration1").equals("once")) { %>
once
<% } else if(request.getParameter("duration1").equals("twice")){ %>twice
<% } else if(request.getParameter("duration1").equals("thrice")){ %>thrice
<% } else if(request.getParameter("duration1").equals("quad")){ %>quad
<%} %>">
    </div>
  </div>

  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">Submit</button>
    </div>
  </div>
</form>
 --%>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    
      <!-- Site footer -->
      <div class="footer">
        <p>&copy; Nanyang Polytechnic FYPJ 2013</p>
      </div>

    </div> <!-- /container -->

</body>
</html>

<%-- <form>
<table class="table table-hover">
<tr>
<td><b>Time :</b></td>
<td><%= request.getParameter("alarmTime") %></td>
</tr>
<tr>
<td><b>Name of Medicine :</b></td>
<td><%= request.getParameter("name") %></td>
</tr>
<tr>
<td><b>Number of Tablets :</b></td>
<td><%= request.getParameter("tablet") %></td>
</tr>
<tr>
<td><b>Starting Date :</b></td>
<td><%= request.getParameter("date") %></td>
</tr>
<tr>
<td><b>Interval :</b></td>
<td><% if (request.getParameter("duration1").equals("once")) { %>
<div>Once per Day</div>
<% } else if(request.getParameter("duration1").equals("twice")){ %>
<div>Every 6 hours</div>
<% } else if(request.getParameter("duration1").equals("thrice")){ %>
<div>Every 8 hours</div>
<% } else if(request.getParameter("duration1").equals("quad")){ %>
<div>Every 12 hours</div>
<%} %></td>
</tr>
</table>
</form>
 --%>