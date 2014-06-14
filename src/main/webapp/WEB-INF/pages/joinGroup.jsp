<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Join Group</title>
    <link href="/css/signin.css" rel="stylesheet">
    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/dropdownCheckbox.css" rel="stylesheet">
    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.css" rel="stylesheet">
    <link href="/css/jquery.growl.css" rel="stylesheet" type="text/css" />
    <script src="<c:url value="/js/jquery-2.0.2js" />"></script>
    <script src="<c:url value="/js/bootstrap.js" />"></script>
    <script src="<c:url value="/js/jquery.growl.js" />"></script>
    <style type="text/css">
        a.list-group-item {
            height:auto;
            min-height:200px;
            background: #ffffff;
        }
        a.list-group-item.active small {
            color: #2f49ff;
        }
        .stars {
            margin:20px auto 1px;
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
<div class="jumbotron">
    <div class="container">
        <h2>Join Group</h2>
    </div>
</div>

<div class="container">
        <ol class="breadcrumb">
            <li><a href="/groups">Groups</a></li>
            <li class="active">Join Group</li>
        </ol>

    <h4>Choose group from list:</h4>
    <sf:form modelAttribute="joinGroupForm" action="join" method="post" >
        <sf:select path="groupName" class="form-control">
            <sf:option value=""></sf:option>
            <sf:options items="${otherGroups}"></sf:options>
        </sf:select>

        <sf:errors path="groupName" id="error"></sf:errors>
        <br>
        <div class="col-md-10">
            <button type="submit" class="btn btn-lg btn-success">
                Join!
            </button>
        </div>
    </sf:form>
</div>

    <hr>

    <footer>
        <p>&copy; Company 2014</p>
    </footer>

<!-- /container -->

<script src="js/dropdown-checkbox.js"></script>


</body>
</html>