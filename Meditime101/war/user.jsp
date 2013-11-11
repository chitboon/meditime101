<%--@elvariable id="userInfo" type="com.fitbit.api.common.model.user.UserInfo"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>User Page with stored tokens</title></head>
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
    <tr>
        <td>Timezone:</td>
        <td>${userInfo.timezone}</td>
    </tr>
    <tr>
        <td>Offset From UTC (Millis):</td>
        <td>${userInfo.offsetFromUTCMillis}</td>
    </tr>    
        <tr>
        <td>Vibe :</td>
        <td> <%= request.getAttribute("vibe") %></td>
    </tr>     
        <tr>
        <td>deleted :</td>
        <td> <%= request.getAttribute("deleted") %></td>
    </tr>  
        <tr>
        <td>enabled :</td>
        <td> <%= request.getAttribute("enabled") %></td>
    </tr>   
        <tr>
        <td>recurring :</td>
        <td> <%= request.getAttribute("recurring") %></td>
    </tr>    
        <tr>
        <td>syncedToDevice :</td>
        <td> <%= request.getAttribute("syncedToDevice") %></td>
    </tr> 
       <tr>
        <td>time :</td>
        <td> <%= request.getAttribute("time") %></td>
    </tr> 
    <tr>
        <td>alarmId :</td>
        <td> <%= request.getAttribute("alarmId") %></td>
    </tr>
       <tr>
        <td>snoozeCount :</td>
        <td> <%= request.getAttribute("snoozeCount") %></td>
    </tr>
       <tr>
        <td>snoozeLength :</td>
        <td> <%= request.getAttribute("snoozeLength") %></td>
    </tr>
</table>

</body>
</html>