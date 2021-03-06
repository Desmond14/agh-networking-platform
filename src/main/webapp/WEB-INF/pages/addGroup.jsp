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

    <title>Add Group</title>
    <link href="/css/signin.css" rel="stylesheet">
    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/dropdownCheckbox.css" rel="stylesheet">
    <link href="/css/button3d.css" rel="stylesheet">
    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.css" rel="stylesheet">
    <script src="<c:url value="/js/jquery-2.0.2js" />"></script>
    <style type="text/css">
        .btn-primary {
            box-shadow:0 0 0 1px #428bca inset, 0 0 0 2px rgba(255,255,255,0.15) inset, 0 8px 0 0 #357ebd, 0 8px 0 1px rgba(0,0,0,0.4), 0 8px 8px 1px rgba(0,0,0,0.5);
            background-color:#428bca;
        }
        .btn-success {
            box-shadow:0 0 0 1px #5cb85c inset, 0 0 0 2px rgba(255,255,255,0.15) inset, 0 8px 0 0 #4cae4c, 0 8px 0 1px rgba(0,0,0,0.4), 0 8px 8px 1px rgba(0,0,0,0.5);
            background-color:#5cb85c;
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
<<div class="jumbotron">
    <div class="container">
       <h2>Create New Group</h2>
    </div>
</div>

<div class="container">
        <ol class="breadcrumb">
            <li><a href="/groups">Groups</a></li>
            <li class="active">Create New Group</li>
        </ol>
        <sf:form method="POST" modelAttribute="group">
            <div class="form-group">

                <label for="groupName" class="col-md-2">
                    Group name:
                </label>
                <div class="col-md-10">
                    <sf:input path="groupName" class="form-control" id="groupName" placeholder="Group name"/>
                    <sf:errors path="groupName" cssStyle="color: #ff0000"/>
                </div>
            </div>

            <div class="form-group">
                <label for="description" class="col-md-2">
                    Description:
                </label>

                <div class="col-md-10">
                    <sf:textarea path="description" class="form-control" id="description" placeholder="Description"/>
                    <sf:errors path="description" cssStyle="color: #ff0000"/>
                </div>
            </div>

            <div class="row">
                <div class="col-md-2">
                </div>
                <div class="col-md-10">
                    <div class="btn-group">
                        <button type="submit" class="btn btn-lg btn-success btn3d" style="margin: 10px">Submit</button>
                        <a class="btn btn-lg btn-primary btn3d" href="/groups/" role="button" style="margin: 10px">Cancel</a>
                    </div>
                </div>
            </div>

        </sf:form>
    <div class="row">
        <div class="col-md-4">

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