<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Search Offers - TraderBook</title>
    <link href="/css/signin.css" rel="stylesheet">
    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/searchOffers.css" rel="stylesheet">
    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.css" rel="stylesheet">

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
                    <strong>Logged as: ${username} &nbsp</strong>
                </div>
                <button type="submit" class="btn btn-danger">Log out</button>
            </form>
        </div>
    </div>
</div>

<!-- Content -->
<
<div class="jumbotron">
    <div class="container">
        <h2>Search offers</h2>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-md-4">
            <form method="POST" action="/offers/search">
                <div class="input-group custom-search-form">
                    <input type="text" class="form-control" name="searchString">
                    <span class="input-group-btn">
                        <button class="btn btn-default" type="submit">
                            <span class="glyphicon glyphicon-search"></span>
                        </button>
                    </span>
                </div>
            </form>
            Search results for "${requestScope.searchString}" :
            <c:if test="${not empty foundedOffers}">
                <c:forEach var="offer" items="${foundedOffers}">
                    <h2>${offer.title}</h2>

                    <p><b>${offer.price}</b></p>

                    <p><b>${offer.location}</b></p>

                    <p>${offer.content} </p>

                    <p><i>${offer.seller.username}</i></p>

                    <p><a class="btn btn-default" href="/offers/${offer.id}" role="button">View details &raquo;</a>
                    </p>
                </c:forEach>
            </c:if>
        </div>
    </div>

    <hr>
    <footer>
        <p>&copy; Company 2014</p>
    </footer>
</div>

</body>
</html>
