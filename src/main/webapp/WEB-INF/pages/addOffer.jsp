<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Sign in to TraderBook</title>
    <link href="/css/signout.css" rel="stylesheet">
    <link href="/css/style.css" rel="stylesheet">
    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.css" rel="stylesheet">
    <script src="/js/jquery-2.0.2js"></script>
</head>

<body onload='document.f.j_username.focus();'>
<div class="navbar navbar-fixed-top navbar-inverse" role="navigation">
    <!-- gorny pasek -->
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">TraderB00k</a>
        </div>
        <!-- przycisk log out oraz informacja o zalogowanym uzytkowniku -->
        <div class="navbar-collapse collapse">
            <form class="navbar-form navbar-right" action="/j_spring_security_logout" method="POST">
                <div class="form-group text-muted">
                    <strong>Logged as: ${username} &nbsp</strong>
                </div>
                <button type="submit" class="btn btn-danger">Log out</button>
            </form>
        </div>
    </div>
    <!-- /.container -->
</div>
<!-- /.navbar -->
<div class="container">
    <sf:form method="POST" modelAttribute="offer">
        <h2>Add offer</h2>
        <br>
        <div class="form-group">
            <label for="title" class="col-md-2">
                Title:
            </label>
            <div class="col-md-10">
                <sf:input path="title" class="form-control" id="title" placeholder="Offer title"/>
                <sf:errors path="title" cssStyle="color: #ff0000"/>
            </div>
        </div>

        <div class="form-group">
            <label for="price" class="col-md-2">
                Price:
            </label>

            <div class="col-md-10">
                <sf:input path="price" class="form-control" id="price" placeholder="Offer price"/>
                <sf:errors path="price" cssStyle="color: #ff0000"/>
            </div>
        </div>

        <div class="form-group">
            <label for="content" class="col-md-2">
                Content:
            </label>

            <div class="col-md-10">
                <sf:textarea path="content" class="form-control" id="content" placeholder="Offer content"/>
                <sf:errors path="content" cssStyle="color: #ff0000"/>
            </div>
        </div>

        <%--<div class="form-group">
            <label for="uploadimage" class="col-md-2">
                Upload Image:
            </label>
            <div class="col-md-10">
                <sf:input path="file" name="uploadimage" id="uploadimage"/>
                <p class="help-block">
                    Allowed formats: jpeg, jpg, gif, png
                </p>
            </div>
        </div>--%>

        <div class="row">
            <div class="col-md-2">
            </div>
            <div class="col-md-10">
                <button type="submit" class="btn btn-lg btn-success">
                    Submit
                </button>
            </div>
        </div>

    </sf:form>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>
