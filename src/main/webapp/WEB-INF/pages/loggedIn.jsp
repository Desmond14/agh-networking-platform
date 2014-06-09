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

    <title>Welcome to TradeBook: ${username}</title>
    <link href="/css/loggedIn.css" rel="stylesheet">
    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.css" rel="stylesheet">
    <!-- Awesome fonts -->
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
    <script src="<c:url value="/js/jquery-2.0.2js" />"></script>
    <script src="<c:url value="/js/bootstrap.js" />"></script>
    <script src="<c:url value="/js/loggedIn.js" />"></script>
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
                    <strong>Logged as: <a href="#profile">${username}</a> &nbsp</strong>
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
                <li class="active"><a data-toggle="tab" href="#home"><i class="fa fa-home"></i> Home</a></li>
                <li><a href="/messages"><i class="fa fa-envelope"></i> Messages</a></li>
                <li><a data-toggle="tab" href="#profile"><i class="fa fa-user"></i> Profile</a></li>
                <li><a href="/offers"><i class="fa fa-book"></i> Offers</a></li>
                <li><a href="/groups"><i class="fa fa-users"></i> Groups</a></li>
                <li>
                    <c:url value="/j_spring_security_logout" var="logout" />
                    <a href="${logout}"><i class="fa fa-sign-out"></i> Logout</a>
                </li>
            </ul>
        </div>
        <div class="col-md-10">
            <div class="tab-content">
                <!-- Homepage -->
                <div class="tab-pane fade in active" id="home">
                    <h3>Section A</h3>
                    <p>Aliquip placeat salvia cillum iphone. Seitan aliquip quis cardigan american apparel, butcher voluptate nisi qui. Raw denim you probably haven't heard of them jean shorts Austin. Nesciunt tofu stumptown aliqua, retro synth master cleanse. Mustache cliche tempor, williamsburg carles vegan helvetica. Reprehenderit butcher retro keffiyeh dreamcatcher synth.</p>
                </div>
                <!-- User's private messages -->
                <div class="tab-pane fade" id="messages">
                    <ul class="nav nav-tabs" id="myTab">
                        <li class="active"><a href="#inbox" data-toggle="tab"><i class="fa fa-envelope-o"></i> Inbox</a></li>
                        <li><a href="#sent" data-toggle="tab"><i class="fa fa-reply-all"></i> Sent</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="inbox">
                            <a type="button" data-toggle="collapse" data-target="#a1">
                                <div class="btn-toolbar well well-sm" role="toolbar"  style="margin:0px;">
                                    <div class="btn-group"><input type="checkbox"></div>
                                    <div class="btn-group col-md-3">Admin Kumar</div>
                                    <div class="btn-group col-md-8"><b>Hi Check this new Bootstrap plugin</b> <div class="pull-right"><i class="glyphicon glyphicon-time"></i> 12:10 PM <button class="btn btn-primary btn-xs" data-toggle="modal" data-target=".bs-example-modal-lg"><i class="fa fa-share-square-o"> Reply</i></button></div> </div>
                                </div>
                            </a>
                            <div id="a1" class="collapse out well">This is the message body1</div>
                            <br>
                            <button class="btn btn-primary btn-xs"><i class="fa fa-check-square-o"></i> Delete Checked Item's</button>
                        </div>


                        <div class="tab-pane" id="sent">
                            <a type="button" data-toggle="collapse" data-target="#s1">
                                <div class="btn-toolbar well well-sm" role="toolbar"  style="margin:0px;">
                                    <div class="btn-group"><input type="checkbox"></div>
                                    <div class="btn-group col-md-3">Kumar</div>
                                    <div class="btn-group col-md-8"><b>This is reply from Bootstrap plugin</b> <div class="pull-right"><i class="glyphicon glyphicon-time"></i> 12:30 AM</div> </div>
                                </div>
                            </a>
                            <div id="s1" class="collapse out well">This is the message body1</div>
                            <br>
                            <button class="btn btn-primary btn-xs"><i class="fa fa-check-square-o"></i> Delete Checked Item's</button>
                        </div>
                    </div>
                </div>
                <!-- User Profile -->
                <div class="panel tab-pane fade" id="profile">
                    <div class="tab-content">
                        <img class="pic img-circle" src="http://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/twDq00QDud4/s120-c/photo.jpg" alt="...">
                        <div class="name"><small>${username}</small></div>
                        <a href="#" class="btn btn-xs btn-primary pull-right" style="margin:10px;"><span class="glyphicon glyphicon-picture"></span> Change cover</a>
                        <div class="profileContent well">
                            <b>Login: &nbsp</b><input type="text" size="25" disabled="true" value="${username}"><br><br>
                            <b>Password: &nbsp</b><input type="password" size="25" disabled="true" value="${password}"><br><br>
                            <b>Email: &nbsp</b><input type="text" size="25" disabled="true" value="${email}"><br><br>
                            <b>Country: &nbsp</b><input type="text" size="25" disabled="true" value="${country}"><br><br>
                            <b>Current City: &nbsp</b><input type="text" size="25" disabled="true" value="${city}"><br><br>
                            <a href="#editProfile" data-toggle="modal" data-target="#myModal" class="editProfileButton">Edit Profile</a>


                            <!-- Modal -->
                            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                            <h4 class="modal-title" id="myModalLabel">Edit Profile</h4>
                                        </div>
                                        <div class="modal-body">
                                            <center>
                                                <img src="http://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/twDq00QDud4/s120-c/photo.jpg" name="aboutme" width="140" height="140" border="0" class="img-circle"></a>
                                                <h3 class="media-heading">${username} <small>${country}</small></h3>

                                            </center>
                                            <hr>
                                            <center>
                                                <p class="text-left">
                                                <sf:form method="POST" modelAttribute="user">
                                                    <strong>New password: </strong><br>
                                                    <sf:password path="password" class="form-control" id="password" placeholder="Enter Password"/>
                                                    <sf:errors path="password" cssStyle="color: #ff0000"/>
                                                    <strong>New email: </strong><br>
                                                    <sf:input path="email" class="form-control" id="email" placeholder="Enter Email"/>
                                                    <sf:errors path="email" cssStyle="color: #ff0000"/>
                                                    <strong>New country: </strong><br>
                                                    <sf:input path="country" class="form-control" id="country" placeholder="Enter Country"/>
                                                    <sf:errors path="country" cssStyle="color: #ff0000"/>
                                                    <strong>New current city: </strong><br>
                                                    <sf:input path="city" class="form-control" id="city" placeholder="Enter City"/>
                                                    <sf:errors path="city" cssStyle="color: #ff0000"/>
                                                </sf:form>
                                                </p>
                                                <br>
                                            </center>
                                        </div>
                                        <div class="modal-footer">
                                            <center>
                                                <button type="submit" class="btn btn-success" data-dismiss="modal">Save changes</button>
                                            </center>
                                        </div>
                                    </div>
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
