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
    </div>
    <!-- /.container -->
</div>
<!-- /.navbar -->
<div class="container">
    <sf:form class="form-signout" method="POST" modelAttribute="user">
        <c:if test="${not empty addUserError}">
            <div class="alert alert-danger">
                This login currently exists in database! Choose another one.<br/>
            </div>
        </c:if>
        <h2 class="form-signout-heading">Sign Up</h2>
        <br>
        <div class="form-group">
            <label for="login" class="col-md-2">
                Login:
            </label>
            <div class="col-md-10">
                <sf:input path="username" class="form-control" id="login" placeholder="Login"/>
                <sf:errors path="username" cssStyle="color: #ff0000" />
            </div>
        </div>

        <div class="form-group">
            <label for="password" class="col-md-2">
                Password:
            </label>

            <div class="col-md-10">
                <sf:password path="password" class="form-control" id="password" placeholder="Enter Password"/>
                <sf:errors path="password" cssStyle="color: #ff0000"/>
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

        <div class="checkbox">
            <div class="col-md-2">
            </div>
            <div class="col-md-10">
                <label>
                    <input type="checkbox">Terms and Conditions</label>
            </div>
        </div>

        <div class="row">
            <div class="col-md-2">
            </div>
            <div class="col-md-10">
                <button type="submit" class="btn btn-lg btn-success">
                    Register
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
