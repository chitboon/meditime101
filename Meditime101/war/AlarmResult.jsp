<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table>
        <tr>
        <td>alarm :</td>
        <td> <%= request.getAttribute("alarm") %></td>
    </tr>     
        <tr>
        <td>recurring :</td>
        <td> <%= request.getAttribute("recurring") %></td>
    </tr>  
        <tr>
        <td>occuring :</td>
        <td> <%= request.getAttribute("occuring") %></td>
    </tr>   
        <tr>
        <td>weekDays :</td>
        <td> <%= request.getAttribute("weekDays") %></td>
    </tr>    
<tr>
<td>
<input type="image" src="file:///Users/Guest/Desktop/ + <%= request.getAttribute("imageUrl") %>">
</td>
</tr>
</table>
</body>
</html>