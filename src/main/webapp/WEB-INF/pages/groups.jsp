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

    <title>Groups</title>
    <link href="/css/groups.css" rel="stylesheet">
    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.css" rel="stylesheet">
    <!-- Awesome fonts -->
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
    <link href="/css/jquery.growl.css" rel="stylesheet" type="text/css" />

    <script src="<c:url value="/js/jquery-2.0.2js" />"></script>
    <script src="<c:url value="/js/bootstrap.js" />"></script>
    <script src="<c:url value="/js/jquery.growl.js" />"></script>
    <script type="text/javascript">
        function displayInformation() {
            $.growl.notice({ message: "Successfully created new group!" });
        }
        function displayInformationAboutJoinToGroup() {
            $.growl.notice({ message: "Successfully joined to group!" });
        }
        function displayInformationAboutLeaveGroup() {
            $.growl.notice({ message: "Successfully left group!" });
        }
    </script>
    <style type="text/css">
        .btn3d {
            transition:all .08s linear;
            position:relative;
            outline:medium none;
            -moz-outline-style:none;
            border:0px;
            margin-right:10px;
            margin-top:15px;
        }
        .btn3d:focus {
            outline:medium none;
            -moz-outline-style:none;
        }
        .btn3d:active {
            top:9px;
        }
        .btn-primary {
            box-shadow:0 0 0 1px #428bca inset, 0 0 0 2px rgba(255,255,255,0.15) inset, 0 8px 0 0 #357ebd, 0 8px 0 1px rgba(0,0,0,0.4), 0 8px 8px 1px rgba(0,0,0,0.5);
            background-color:#428bca;
        }
    </style>

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
                <li><a href="/messages"><i class="fa fa-envelope"></i> Messages</a></li>
                <li><a href="/welcome#profile"><i class="fa fa-user"></i> Profile</a></li>
                <li><a href="/offers"><i class="fa fa-book"></i> Offers</a></li>
                <li class="active"><a href="/groups"><i class="fa fa-users"></i> Groups</a></li>
                <li><a href="/friends"><i class="fa fa-user"></i> Friends</a></li>
                <li>
                    <c:url value="/j_spring_security_logout" var="logout"/>
                    <a href="${logout}"><i class="fa fa-sign-out"></i> Logout</a>
                </li>
            </ul>
        </div>
        <div class="col-md-10">
            <div class="tab-content">
                <div class="jumbotron">
                    <div class="container">
                        <h1>Groups</h1>
                        <c:if test="${not empty addedGroup}">
                            <script>
                                displayInformation();
                            </script>
                        </c:if>
                        <c:if test="${not empty joinedGroup}">
                            <script>
                                displayInformationAboutJoinToGroup();
                            </script>
                        </c:if>
                        <c:if test="${not empty leftGroup}">
                            <script>
                                displayInformationAboutLeaveGroup();
                            </script>
                        </c:if>
                    </div>
                </div>
                <p><a class="btn btn-primary btn-lg btn3d" href="/groups/create" role="button">Add new Group</a></p>
                <p><a class="btn btn-primary btn-lg btn3d" href="/groups/join" role="button">Join to group</a></p>
                <p><a class="btn btn-primary btn-lg btn3d" href="/groups/leave" role="button">Leave group</a></p>
                <p><a class="btn btn-primary btn-lg btn3d" href="/groups/mygroups" role="button">Display my groups</a></p>
            </div>
        </div>
    </div>
    <br><br><br>
</div>
</body>
</html>
