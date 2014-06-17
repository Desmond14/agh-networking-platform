<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Group ${group.groupName} - TraderBook</title>
    <link href="/css/signin.css" rel="stylesheet">
    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/button3d.css" rel="stylesheet">
    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.css" rel="stylesheet">
    <script src="/js/jquery-2.0.2js"></script>
    <script>
        function addComment() {
            $.ajax({
                type: "POST",
                url: "./" + ${group.id},
                data: JSON.stringify({ postContent: $('textarea[name=comment-form]').val() }),
                contentType: 'application/json',
                success: function() {
                    addNewComentToDOM($('textarea[name=comment-form]').val());
                }
            });
        };

        function addNewComentToDOM(postContent) {
            var $rowDiv = $("<div>", {class: "row"});
            var $insideDiv = $("<div>", {class: "col-md-8"});
            $rowDiv.append($insideDiv);

            var $authorSpan = $("<span>", {class: "author"});
            $authorSpan.append("<sec:authentication property="principal.username"/>");
            $insideDiv.append($authorSpan);
            $insideDiv.append("<p>" + postContent + "</p>");

            $( "#comments").append($rowDiv);
        };
    </script>
    <style type="text/css">
        .btn-warning {
            box-shadow:0 0 0 1px #f0ad4e inset, 0 0 0 2px rgba(255,255,255,0.15) inset, 0 8px 0 0 #eea236, 0 8px 0 1px rgba(0,0,0,0.4), 0 8px 8px 1px rgba(0,0,0,0.5);
            background-color:#f0ad4e;
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
                    <strong>Logged as: <a href="/welcome#profile"><sec:authentication property="principal.username"/></a> &nbsp</strong>
                </div>
                <button type="submit" class="btn btn-danger">Log out</button>
            </form>
        </div>
    </div>
</div>
<!-- Content -->
<div class="jumbotron">
    <div class="container">
        <c:choose>
            <c:when test="${not empty notFound}">
                <h2>Group not found!</h2>
            </c:when>
            <c:otherwise>
                <h2>${group.groupName}</h2>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<div class="container" id="comments">
    <ol class="breadcrumb">
        <li><a href="/groups">Groups</a></li>
        <li><a href="/groups/mygroups">My groups</a></li>
        <li class="active">${group.groupName}</li>
    </ol>
    <c:if test="${empty notFound}">
        <div class="row">
            <span>
                <textarea placeholder="Write Post..." class="form-control input-sm" name="comment-form" rows="3"></textarea>
            </span>
            <span><button type="button" class="btn btn-warning btn3d" onclick="addComment()">Submit Post</button></span>
        </div>
        <h3>Posts:</h3>
        <c:if test="${not empty posts}">
            <c:forEach var="post" items="${posts}">
                <div class="row">

                    <div class="col-md-8">

                        <a href="/user/${post.postAuthor.id}"><span class="author">${post.postAuthor.username}</span></a>

                        <p>${post.postContent}</p>

                    </div>
                </div>

            </c:forEach>
        </c:if>
    </c:if>
</div>
<hr>
<div class="container">
    <footer>
        <p>&copy; Company 2014</p>
    </footer>
</div>
<!-- /container -->


</body>
</html>
