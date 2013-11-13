<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Set Alarm</title>

</head>

<body>

<form action="/setAlarm" method="post">
<table>
<thead>Set an Alarm
</thead>
<tr>
<td>Time : </td>
<td><input type ='textbox' name="alarmTime" id="alarmTime">
</td>
</tr>
<tr>
<td>Duration :
</td>
<td>
<select id="duration" name="duration">
<option value ="0">Once per Day</option>
<option value ="6">Every 6 hours</option>
<option value ="8">Every 8 hours</option>
<option value ="12">Every 12 hours</option>

</select>
</td>
</tr>

</table>


<button>Cancel</button>
<button>Save</button>

</form>
</body>

</html>