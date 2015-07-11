<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>User Name</title>

    <!-- Bootstrap Core CSS - Uses Bootswatch Flatly Theme: http://bootswatch.com/flatly/ -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="freelancer.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="font-awesome/css/montserrat.css" rel="stylesheet" type="text/css">
    <link href="font-awesome/css/lato.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <%@ page import="model.User" %>

    <% User user = (User)session.getAttribute("user"); %>



</head>

<body id="page-top" class="index">

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#page-top">MidiCloud</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li class="hidden">
                        <a href="#page-top"></a>
                    </li>
                    		    <li>


<form class="form-inline" action="AccountInfoWindow" method="post">
 <div class="input-group">
      <input type="text" class="form-control" placeholder="Search for...">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button">Search</button>
      </span>
 </div>
</form>
</li>

<li role="presentation" class="dropdown">
    <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
     <img alt="icon" src="OutputFile" height=10px width=10px> <%= user.getUsername() %> <span class="caret"></span>
    </a>
    <from action="AccountInfoWindow" method="post">
    <ul class="dropdown-menu">
	<li>
		<a href="#">Detail</a>
	</li>
	<li role="separator" class="divider"></li>
	<li>
		<a href="#">Logout</a>
	</li>

    </ul>
    </from>
  </li>




                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>

<div class="container">
	<div class="row"><br><br>
	</div>
</div>



    <!-- User Top Section -->
    <section id="UserTop">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2>User Profile</h2>
                    <hr class="star-primary">
                </div>
            </div>
            </div>
      </section>

<div class="container">
<div class="row">
            <div class="col-lg-12-original">
            <div class="text-left">

                <img class="img-responsive img-center" src="OutputFile" alt="" height=200px width=200px>
                <h2><%= user.getUsername() %>
                    <small><%= user.getUsername() %></small>
                </h2>


<button type="button" class="btn btn-default btn-sm">
 				 <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>Edit
				</button>
			<button type="button" class="btn btn-default btn-sm btn-warning">
 				 <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>report
				</button>
				<button type="button" class="btn btn-default btn-sm">
 				 <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>delete
				</button>
<br><br>

<div class="panel panel-default">
<div class="panel-body">
                <p><%= user.getProfile() %></p>
            </div>

</div>
</div>



</div>
</div>
</div>


      <section id="UserMidi">
        <div class="container">


            <div class="row">

		  <div class="panel panel-default">
		  <div class="panel-heading"><h2>User MIDI</h2></div>
		  <div class="list-group">
		  <form action="AccountInfoWindow" method="post">
			  <button type="button" class="list-group-item">1<span lang="ja">&nbsp;&nbsp;&nbsp;
			  </span>&nbsp;<a href="#"><%= request.getAttribute("midifile") %></a></button>
			  <button type="button" class="list-group-item">2<span lang="ja">&nbsp;&nbsp;&nbsp;
			  </span>&nbsp;<a href="#"><%= request.getAttribute("midifile") %></a></button>
			  <button type="button" class="list-group-item">3<span lang="ja">&nbsp;&nbsp;&nbsp;
			  </span>&nbsp;<a href="#"><%= request.getAttribute("midifile") %></a></button>
			  <button type="button" class="list-group-item">4<span lang="ja">&nbsp;&nbsp;&nbsp;
			  </span>&nbsp;<a href="#"><%= request.getAttribute("midifile") %></a></button>
			  <button type="button" class="list-group-item">5<span lang="ja">&nbsp;&nbsp;&nbsp;
			  </span>&nbsp;<a href="#"><%= request.getAttribute("midifile") %></a></button>
		</form>
			</div>
		  </div>






		</div>

		<form action="AccountInformation" method="post">
		<div class="text-center">
			<nav>
			<form action="AccountInfoWindow" method="post">
			  <ul class="pagination">
			    <li class="disabled">
			      <a href="#" aria-label="Previous" name="previous">
			        <span aria-hidden="true">&laquo;</span>
			      </a>
			    </li>
			    <li class="active"><a href="#">1</a></li>
			    <li><a href="#" name="2" onClick="goSubmit(this.form, this)">2</a></li>
			    <li><a href="#" name="3" onClick="goSubmit(this.form, this)">3</a></li>
			    <li><a href="#" name="4" onClick="goSubmit(this.form, this)">4</a></li>
			    <li><a href="#" name="5" onClick="goSubmit(this.form, this)">5</a></li>
			    <li>
			      <a href="#" aria-label="Next" name="next">
			        <span aria-hidden="true">&raquo;</span>
			      </a>
			    </li>
			  </ul>
			  </form>
			</nav>
			</div>
		</form>
		 </div>



    </section>













        <!-- Footer -->
    <footer class="text-center">
              <div class="footer-below">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        Copyright &copy; Midi Cloud Team 2015
                    </div>
                </div>
            </div>
        </div>
    </footer>

    <!-- Scroll to Top Button (Only visible on small and extra-small screen sizes) -->
    <div class="scroll-top page-scroll visible-xs visible-sm">
        <a class="btn btn-primary" href="#page-top">
            <i class="fa fa-chevron-up"></i>
        </a>
    </div>


    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Plugin JavaScript -->
    <script type="text/javascript">
	<!--
	function goSubmit(formObj, btnObj) {
	formObj.action.value=btnObj.name;
	formObj.submit();
	}
	 -->
	</script>


    <!-- Contact Form JavaScript -->


    <!-- Custom Theme JavaScript -->
    <script src="js/freelancer.js"></script>

</body>

</html>
