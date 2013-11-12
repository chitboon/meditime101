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
<select name="abc">
<option value ="am" name="period">AM</option>
<option value ="pm" name ="period" >PM</option>
</select>
<% request.setAttribute("period", request.getParameter("period")); %>
<% request.setAttribute("alarm",request.getParameter("alarmTime")); %>
</td>
</tr>
<tr>
<td>Occuring : </td>
<td><input type ="radio" name="recurring" value="once">once
<input type ="radio" name="recurring" value="recurring">recurring</td>
<% request.setAttribute("occuring",request.getParameter("recurring")); %>
</tr>
</table>
<button>Cancel</button>
<button>Save</button>

</form>
</body>

</html>