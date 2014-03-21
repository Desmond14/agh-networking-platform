<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Sign in to TraderBook</title>
    <link href="/css/signin.css" rel="stylesheet">
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
    <!-- formularz logowania -->
    <form name='f' action="/j_spring_security_check"
          method='POST' class="form-signin">
        <br/>
        <!-- informacja o bledzie wynikajacym z niepodania loginu i/lub hasla -->
        <c:if test="${not empty error}">
            <div class="alert alert-danger">
                Your login attempt was not successful, try again.<br/> Caused :
                    ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
            </div>
        </c:if>
        <!-- informacja o pomyslnym wylogowaniu sie -->
        <c:if test="${not empty logout}">
            <div class="alert alert-success">
                Successfully logged out.
            </div>
        </c:if>
        <h2 class="form-signin-heading">Please log in</h2>
        <br/>
        <input type='text' class="form-control" name='j_username' placeholder="Login">
        <input type='password' class="form-control" name='j_password' placeholder="Password">
        <label class="checkbox">
            <input type="checkbox" value="remember-me"> Remember me
        </label>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Log in</button>
        <br/>
        Don't have an account yet? <a href="#">Sign up now!</a>
    </form>
</div>
</body>
</html>
