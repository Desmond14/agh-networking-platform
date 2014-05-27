<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <meta name="description" content="">
	    <meta name="author" content="">
	
	    <title>Offers list - TraderBook</title>
	    <link href="/css/signin.css" rel="stylesheet">
	    <link href="/css/style.css" rel="stylesheet">
	    <link href="/css/dropdownCheckbox.css" rel="stylesheet">
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
		                    <strong>Logged as: <a href="#profile">${username}</a> &nbsp</strong>
		                </div>
		                <button type="submit" class="btn btn-danger">Log out</button>
		            </form>
		        </div>
		    </div>
		</div>
		<!-- Content -->
		<<div class="jumbotron">
		    <div class="container">
		
		        <p><a class="btn btn-primary btn-lg" href="/addOffer" role="button">Add new offer</a></p>
		        <p><a class="btn btn-success btn-lg" href="/offers/search" role="button">Search offers by keywords</a></p>
		        <p><a class="btn btn-warning btn-lg" href="/offers/searchByLocation" role="button">Search offers by location</a></p>
		    </div>
		</div>
		
		<div class="container">
			<div class="row">
				<label for="filter">Filter:</label>
				<form method="GET" action="/offers">
	        		<div class="form-group">
	        			<div class="col-md-4">
	                    	<input type="number" class="form-control" name="minPrice" placeholder="Minimal Price">
	                    </div>
	        			<div class="col-md-4">
	                    	<input type="number" class="form-control" name="maxPrice" placeholder="Maximal Price">
	                    </div>
	                    <span class="input-group-btn">
	                        <button class="btn btn-default" type="submit">Filter</button>
	                    </span>
	                </div>
            	</form>
			</div>
		    <div class="row">
		        <div class="col-md-4">
		
		            <c:if test="${not empty offers}">
		                <c:forEach var="offer" items="${offers}">
                    		<c:if test="${((empty minPrice) and (empty maxPrice)) or ((empty maxPrice) and (offer.price > minPrice)) or ((empty minPrice) and (offer.price < maxPrice)) or ((offer.price > minPrice) and (offer.price < maxPrice))}">
			                    <h2>${offer.title}</h2>
			                    <p><b>${offer.price}</b></p>
			                    <p><b>${offer.location}</b></p>
			                    <p>${offer.content} </p>
			                    <p><i>${offer.seller.username}</i></p>
			
			                    <p><a class="btn btn-default" href="/offers/${offer.id}" role="button">View details &raquo;</a></p>
                    		</c:if>
		                </c:forEach>
		            </c:if>
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