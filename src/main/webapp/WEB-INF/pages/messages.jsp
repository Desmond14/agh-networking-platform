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
                <li><a href="/friends"><i class="fa fa-user"></i>Friends</a></li>
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
                        <li class="active"><a href="#inbox" data-toggle="tab"><i class="fa fa-envelope-o"></i> Inbox</a>
                        </li>
                        <li><a href="#sent" data-toggle="tab"><i class="fa fa-reply-all"></i> Sent</a></li>
                        <li><a href="/sendMessage"><i class="fa fa-reply-all"></i> Send message</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="inbox">
                            <c:if test="${not empty messagesReceived}">
                                <c:set var="count" value="0" scope="page"/>
                                <c:forEach var="message" items="${messagesReceived}">
                                    <c:set var="count" value="${count + 1}" scope="page"/>
                                    <a type="button" data-toggle="collapse" data-target="#a${count}">
                                        <div class="btn-toolbar well well-sm" role="toolbar" style="margin:0px;">
                                            <div class="btn-group"><input type="checkbox"></div>
                                            <div class="btn-group col-md-3">${message.sender.username}</div>
                                            <div class="btn-group col-md-8"><b>${message.topic}</b>

                                                    <%--<div class="pull-right"><i class="glyphicon glyphicon-time"></i>--%>
                                                    <%--&lt;%&ndash;<button class="btn btn-primary btn-xs" data-toggle="modal"&ndash;%&gt;--%>
                                                    <%--&lt;%&ndash;data-target=".bs-example-modal-lg"><i class="fa fa-share-square-o">&ndash;%&gt;--%>
                                                    <%--&lt;%&ndash;Reply</i></button>&ndash;%&gt;--%>
                                                    <%--</div>--%>
                                            </div>
                                        </div>
                                    </a>

                                    <div id="a${count}" class="collapse out well">${message.content}</div>
                                </c:forEach>
                            </c:if>


                            <br>
                            <button class="btn btn-primary btn-xs"><i class="fa fa-check-square-o"></i> Delete Checked
                                Item's
                            </button>
                        </div>


                        <div class="tab-pane" id="sent">
                            <c:if test="${not empty messagesSent}">
                                <c:set var="count" value="0" scope="page"/>
                                <c:forEach var="message" items="${messagesSent}">
                                    <c:set var="count" value="${count + 1}" scope="page"/>
                                    <a type="button" data-toggle="collapse" data-target="#b${count}">
                                        <div class="btn-toolbar well well-sm" role="toolbar" style="margin:0px;">
                                            <div class="btn-group"><input type="checkbox"></div>
                                            <div class="btn-group col-md-3">${message.receiver.username}</div>
                                            <div class="btn-group col-md-8"><b>${message.topic}</b>

                                                    <%--<div class="pull-right"><i class="glyphicon glyphicon-time"></i>--%>
                                                    <%--&lt;%&ndash;<button class="btn btn-primary btn-xs" data-toggle="modal"&ndash;%&gt;--%>
                                                    <%--&lt;%&ndash;data-target=".bs-example-modal-lg"><i class="fa fa-share-square-o">&ndash;%&gt;--%>
                                                    <%--&lt;%&ndash;Reply</i></button>&ndash;%&gt;--%>
                                                    <%--</div>--%>
                                            </div>
                                        </div>
                                    </a>

                                    <div id="b${count}" class="collapse out well">${message.content}</div>
                                </c:forEach>
                            </c:if>
                        </div>


                    </div>
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
