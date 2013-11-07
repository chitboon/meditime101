<html>
<head><title>Simple jsp page</title></head>
<body>
<table>
    <tr>
        <td>Full Name:</td>
        <td>${userInfo.fullName}</td>
    </tr>
    <tr>
        <td>Display Name:</td>
        <td>${userInfo.displayName}</td>
    </tr>
    <tr>
        <td>Nickname:</td>
        <td>${userInfo.nickname}</td>
    </tr>
    <tr>
        <td>ResourceCredentials:</td>
        <td>${rc.text}</td>
    </tr>

</table>