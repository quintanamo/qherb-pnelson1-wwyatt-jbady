<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>EntreLink - New Message</title>
	<link rel="stylesheet" type="text/css" href="_view/css/style.css">
</head>
<body>

	<div id="navbar">
		<c:choose>
			<c:when test="${empty loggedInName}">
				<form action="${pageContext.servletContext.contextPath}/login" method="get">
					<input type="Submit" name="submit" value="Log In" class="navLink">
				</form>
			</c:when>
			<c:otherwise>
					<div class="navLink" style="padding:0;" id="profileLink">
						<img id="userPic" src="${loggedInImg}" style="border-radius: 100%;width: 40px;display: inline-block;float: left;margin-top:14px;margin-left:10px;">
						<input type="Submit" name="submit" value="${loggedInName}" class="navLink">
						<div id="profileDropdown">
							<form action="${pageContext.servletContext.contextPath}/profile" method="openProfile">
								<input type="Submit" name="profile" value="Profile" class="navLink">
							</form>
							<form action="${pageContext.servletContext.contextPath}/messages" method="get">
								<input type="Submit" name="messages" value="Messages" class="navLink">
							</form>
							<form action="${pageContext.servletContext.contextPath}/logout" method="get">
								<input type="Submit" name="logout" value="Log Out" class="navLink">
							</form>
						</div>
					</div>
			</c:otherwise>
		</c:choose>

		<form action="${pageContext.servletContext.contextPath}/index" method="openHome">
			<input type="Submit" name="viewIndex" value="Home" class="navLink">
		</form>
		<form action="${pageContext.servletContext.contextPath}/projects" method="openProjects">
			<input type="Submit" name="viewProjects" value="Projects" class="navLink">
		</form>
		<form action="${pageContext.servletContext.contextPath}/search" method="post">
			<input type="Submit" name="viewSearch" value="Search" class="navLink" style="float: right;">
			<input type="text" name="search" id="searchBox" value="${search}">
		</form>
	</div>

	<!--THIS IS WHERE THE PAGE CONTENT BEGINS-->

	<div class="content">

		<c:choose>
		
		
			<c:when test="${! empty loggedInName}">
				<h2>Compose New Message</h2>
				<form action="${pageContext.servletContext.contextPath}/newmessage" method="post" id="newMessageForm">
				<br/>
				<br/>
				<c:choose>
					<c:when test="${! empty errorMessage}">
						<div class="errorMessage">${errorMessage}</div>
					</c:when>
					<c:otherwise>
						<br/>
					</c:otherwise>
				</c:choose>
					<table id="newPostTable">
						<tr>
							<td class="leftTable">Recipient<font style="font-size:8pt;"> (username or email)</font></td>
							<td class="rightTable"><input type="text" name="msgRecipient" size="30" value="${msgRecipient}" class="newPostBox" style="width:400px;" maxlength="20"><input hidden type="text" name="loggedInId" size="30" value="${loggedInId}" class="newPostBox"></td>
						</tr>
						<tr>
							<td class="leftTable">Subject</td>
							<td class="rightTable"><input type="text" name="msgSubject" size="30" value="${msgSubject}" class="newPostBox" style="width:400px;" maxlength="50"></td>
						</tr>
						<tr>
							<td class="leftTable">Body Text</td>
							<td class="rightTable"><textarea name="msgBody" size="70" value="${msgBody}" id="msgBody" style="width:400px; max-width:400px; max-height: 300px; min-width:400px;" maxlength="500">${msgBody}</textarea></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="Submit" name="sendMessage" value="Submit"style="float: right;"></td>
						</tr>
					</table>
				</form>
			</c:when>
			
			
			<c:otherwise>
				<div style="font-size: 20px; text-align: center; width: 100%; font-family: sans-serif; margin-top: 100px;">You must be logged in to send a message.</div>
				<form action="${pageContext.servletContext.contextPath}/login" method="get">
					<input type="Submit" name="submit" value="Log In" style="display: block; margin: 10px auto;">
				</form>
			</c:otherwise>
		</c:choose>
	</div>


</body>
</html>
