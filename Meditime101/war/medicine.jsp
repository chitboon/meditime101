<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Medicine</title>

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
        <h1>Medication Application</h1>
        <p class="lead">This is the application which would used to help the communication between the caregiver and the elderly. It would be serve as a reminder for the elderly to consume their medicine at the respective timing.</p>
        <!-- <p><a class="btn btn-lg btn-success" href="#" role="button">Get started today</a></p> -->
      </div>

      <!-- Example row of columns -->
      <div class="row">
        <div class="col-lg-4">
          <h2>Input Medicine</h2>
          <p>Input the medicine that the elderly are required to take for the period of time</p>
          <p><a class="btn btn-primary" href="inputMedicine.jsp" role="button">Input Medicine &raquo;</a></p>
        </div>
        <div class="col-lg-4">
          <h2>Check Medicine</h2>
          <p>Check the amount of medicine left for the elderly. This would remind them to go clinic to get more medicine or time for reconsultation.</p>
          <p><a class="btn btn-primary" href="medicine" role="button">Check Medicine details &raquo;</a></p>
       </div>
          <div class="col-lg-4">
          <h2>Medicine History</h2>
          <p>Check the past medication for the elderly to understand them better.</p>
          <p><a class="btn btn-primary" href="medicineHistory" role="button">View Medicine History &raquo;</a></p>
       </div>
      </div>
<br>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
      <!-- Site footer -->
      <div class="footer">
        <p>&copy; Nanyang Polytechnic FYPJ 2013</p>
      </div>

    </div> <!-- /container -->

</body>
</html>