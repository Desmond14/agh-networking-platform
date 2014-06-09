<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Welcome to TradeBook: ${username}</title>
    <link href="/css/loggedIn.css" rel="stylesheet">
    <link href="/css/style.css" rel="stylesheet">
    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.css" rel="stylesheet">
    <!-- Awesome fonts -->
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
    <script src="<c:url value="/js/jquery-2.0.2js" />"></script>
    <script src="<c:url value="/js/bootstrap.js" />"></script>
</head>
<body>
<div class="navbar navbar-fixed-top navbar-inverse" role="navigation">
    <div class="container">
        <!-- gorny pasek -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/welcome">TraderB00k</a>
        </div>
        <!-- przycisk log out oraz informacja o zalogowanym uzytkowniku -->
        <div class="navbar-collapse collapse">
            <form class="navbar-form navbar-right" action="/j_spring_security_logout" method="POST">
                <div class="form-group text-muted">
                    <strong>Logged as: <a href="/welcome#profile"><sec:authentication
                            property="principal.username"/></a> &nbsp</strong>
                </div>
                <button type="submit" class="btn btn-danger">Log out</button>
            </form>
        </div>
    </div>
</div>
<!-- Content -->
<div class="container">
    <div class="row well">
        <div class="col-md-2">
            <ul class="nav nav-pills nav-stacked well" id="tabs">
                <li><a href="/welcome"><i class="fa fa-home"></i> Home</a></li>
                <li class="active"><a href="/messages"><i class="fa fa-envelope"></i> Messages</a></li>
                <li><a href="/welcome#profile"><i class="fa fa-user"></i> Profile</a></li>
                <li><a href="/offers"><i class="fa fa-book"></i> Offers</a></li>
                <li><a href="/groups"><i class="fa fa-users"></i> Groups</a></li>
                <li>
                    <c:url value="/j_spring_security_logout" var="logout"/>
                    <a href="${logout}"><i class="fa fa-sign-out"></i> Logout</a>
                </li>
            </ul>
        </div>
        <div class="col-md-10">
            <div class="tab-content">
                <!-- User's private messages -->
                <div class="tab-pane active" id="messages">
                    <ul class="nav nav-tabs" id="myTab">
                        <li><a href="#send" data-toggle="tab"><i class="fa fa-reply-all"></i> Send message</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="send">
                            <sf:form modelAttribute="messageForm" action="sendMessage" method="post" >
                                <sf:select path="receiverUsername" class="form-control">
                                    <sf:option value=""></sf:option>
                                    <sf:options items="${usernames}"></sf:options>
                                </sf:select>
                                <sf:errors path="receiverUsername" id="error"></sf:errors>

                                <sf:input path="topic" placeholder="Topic" class="form-control"></sf:input>
                                <sf:errors path="topic" id="error"></sf:errors>

                                <sf:textarea path="messageContent" placeholder="Enter your message here" rows="3" class="form-control"></sf:textarea>
                                <sf:errors path="messageContent" id="error"></sf:errors>
                                <br>
                                <div class="col-md-10">
                                    <button type="submit" class="btn btn-lg btn-success">
                                        Submit
                                    </button>
                                </div>
                            </sf:form>
                            <div>

                            </div>
                        </div>
                    </div>
                    <c:if test="${not empty message}">
                        <h4 id="success">${message}</h4>
                    </c:if>
                </div>


            </div>
        </div>
    </div>
    <br><br><br>
</div>
</div>
</div>
</body>
</html>
