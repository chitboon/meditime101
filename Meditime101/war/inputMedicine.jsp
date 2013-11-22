<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Input medicine</title>

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
        <h1>Input Medicine</h1>
        <p class="lead">By providing the time and interval between the medicines, it would result in vibrate on the fitbit flex which would then be worn by the elderly to remind them to eat their medicine. </p>
        <!-- <p><a class="btn btn-lg btn-success" href="#" role="button">Get started today</a></p> -->
      </div>

      <!-- Example row of columns -->

<form class="form-horizontal" role="form" action="/medicine" method="post">

  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">Name of Medicine : </label>
    <div class="col-sm-10">
      <input type='textbox' class="form-control" id="alarmTime" placeholder="Medicine Name" name="name">
    </div>
  </div>
  
 <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">Number of Tablets : </label>
    <div class="col-sm-10">
      <input type='textbox' class="form-control" id="tablets" placeholder="Number of Tablets" name="tablet">
    </div>
  </div>
  
 <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">Starting Date : </label>
    <div class="col-sm-10">
      <input type='textbox' class="form-control" id="date" placeholder="DD/MM/YYYY" name="date">
    </div>
  </div> 
  
    <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">Interval</label>
    <div class="col-sm-10">
      <select id="duration1" name="duration1" class="form-control">
<option value ="once">Once per Day</option>
<option value ="quad">Every 6 hours</option>
<option value ="thrice">Every 8 hours</option>
<option value ="twice">Every 12 hours</option>
</select>
    </div>
  </div>
  
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">Submit</button>
    </div>
  </div>
</form>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    
      <!-- Site footer -->
      <div class="footer">
        <p>&copy; Nanyang Polytechnic FYPJ 2013</p>
      </div>

    </div> <!-- /container -->
</body>
</html>