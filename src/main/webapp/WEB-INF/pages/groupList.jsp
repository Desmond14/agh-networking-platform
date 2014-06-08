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

    <title>Groups List</title>
    <link href="/css/signin.css" rel="stylesheet">
    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/dropdownCheckbox.css" rel="stylesheet">
    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.css" rel="stylesheet">
    <script src="<c:url value="/js/jquery-2.0.2js" />"></script>
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
        <h2>All groups</h2>
    </div>
</div>

<div class="container">
    <div class="row">
        <ol class="breadcrumb">
            <li><a href="/groups">Groups</a></li>
            <li class="active">All groups</li>
        </ol>
        <div class="well">
            <h1 class="text-center">All groups:</h1>
            <div class="list-group">
                <c:if test="${not empty groups}">
                    <c:forEach var="group" items="${groups}">
                        <a href="#" class="list-group-item">
                            <div class="media col-md-3">
                                <figure class="pull-left">
                                    <img class="media-object img-rounded img-responsive" src="http://placehold.it/350x250" alt="placehold.it/350x250" >
                                </figure>
                            </div>
                            <div class="col-md-6">
                                <h2 class="list-group-item-heading"> ${group.groupName} </h2>
                                <p class="list-group-item-text">${group.description}
                                </p>
                            </div>
                            <div class="col-md-3 text-center">
                                <h2> 120 <small> members </small></h2>
                                <button type="button" class="btn btn-success btn-lg">Join!</button>
                                <button type="button" class="btn btn-warning btn-lg">Leave!</button>
                            </div>
                        </a>
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </div>

    <hr>

    <footer>
        <p>&copy; Company 2014</p>
    </footer>
</div>


<!-- /container -->

<script src="js/dropdown-checkbox.js"></script>


</body>
</html>