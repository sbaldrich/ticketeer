<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    
    <title>Ticketeer</title>

    <!-- Custom styles for this template -->
    <link href="/resource/css/jumbotron-narrow.css" rel="stylesheet">
   
  </head>

  <body>

    <div class="container">
      <div class="header clearfix">
        <nav>
          <ul class="nav nav-pills pull-right">
            <li role="presentation" class="active"><a href="#">Home</a></li>
            <li role="presentation"><a href="#">About</a></li>
            <li role="presentation"><a href="#">Contact</a></li>
          </ul>
        </nav>
        <h3 class="text-muted">Ticketeer</h3>
      </div>

      <div class="jumbotron">
        <h1><c:out value="${highlight.name}"/></h1>
        <p class="lead">${highlight.description}.</p>
        <p><a class="btn btn-lg btn-success" href="#" role="button">Buy Tickets</a></p>
      </div>

      <div class="row marketing">
        <div class="col-lg-6">
          <c:forEach items="${events}" var="event">
          	<h4><c:out value = "${event.name}"/></h4>
          	<p>${event.description}</p>
          </c:forEach>
        </div>
        
        <div class="col-lg-6">
          <c:forEach items="${events}" var="event">
          	<h4><c:out value = "${event.name}"/></h4>
          	<p>${event.description}</p>
          </c:forEach>
        </div>
      </div>

      <footer class="footer">
        <p>&copy; Baldrich Corporation 2015</p>
      </footer>

    </div> <!-- /container -->
  </body>
</html>
