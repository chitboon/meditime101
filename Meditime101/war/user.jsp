<%--@elvariable id="userInfo" type="com.fitbit.api.common.model.user.UserInfo"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Personal Information</title>

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
        <h1>Personal Information</h1>
        <p class="lead">This would be the information regarding the elderly and would allow the caregiver to know about the information when needed.</p>
        <!-- <p><a class="btn btn-lg btn-success" href="#" role="button">Get started today</a></p> -->
      </div>

      <!-- Example row of columns -->

<table class="table table-striped">
    <tr>
        <td>Full Name:</td>
        <td>${userInfo.fullName}</td>
    </tr>
    <tr>
        <td>Display Name:</td>
        <td>${userInfo.displayName}</td>
    </tr>
    <tr>
        <td>Gender:</td>
        <td>${userInfo.gender}</td>
    </tr>
    <tr>
        <td>Date of Birth:</td>
        <td>${userInfo.dateOfBirth}</td>
    </tr>
    <tr>
        <td>Height:</td>
        <td>${userInfo.height}</td>
    </tr>
    <tr>
        <td>Weight:</td>
        <td>${userInfo.weight}</td>
    </tr>
    <tr>
        <td>Country:</td>
        <td>${userInfo.country}</td>
    </tr>
</table>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    
      <!-- Site footer -->
      <div class="footer">
        <p>&copy; Nanyang Polytechnic FYPJ 2013</p>
      </div>

    </div> <!-- /container -->


</body>
</html>