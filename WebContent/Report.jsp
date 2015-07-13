<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Report</title>

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

    <%@ page import="model.Report" %>
    <%@ page import="model.ReportManager" %>
    <%@ page import="java.util.*" %>

    <% ReportManager manager = new ReportManager();
       ArrayList<Report> reportList = new ArrayList<Report>();//manager.getReportList();

       Report r = new Report();
       Report r1 = new Report();
       Report r2 = new Report();
       r.setReportID(1);
       r.setReportedmidiID(1);
       r1.setReportID(2);
       r1.setReportedmidiID(2);
       r2.setReportID(3);
       r2.setReportedUserID(1);
       reportList.add(r);
       reportList.add(r1);
       reportList.add(r2);
    %>

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
                <form action="AccountInfoWindow" method="post">
                <input type="hidden" name="action">
                <a class="navbar-brand" href="#page-top" onClick="goSubmit(this.form, this)" name="midicloud" value="MidiCloud">MidiCloud</a>
                </form>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li class="hidden">
                        <a href="#page-top"></a>
                    </li>
                    		    <li>
<!--
		<form class="form-inline">
  <div class="form-group">
    <label class="sr-only" for="exampleInputPassword3">Search word</label>
    <input type="password" class="form-control" id="exampleInputPassword3" placeholder="Password">
  </div>
  <button type="submit" class="btn btn-default">Search</button>
</form>
-->
<form class="form-inline">
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
     <img alt="icon" src="..."> UserName <span class="caret"></span>
    </a>
    <ul class="dropdown-menu">
	<li>
		<a href="#">Detail</a>
	</li>
	<li role="separator" class="divider"></li>
	<li>
		<a href="#">Logout</a>
	</li>

    </ul>
  </li>




                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>

<!-- header
    <header>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <img class="img-responsive" src="img/profile.png" alt="">
                    <div class="intro-text">
                        <span class="name">Start Bootstrap</span>
                        <hr class="star-light">
                        <span class="skills">Web Developer - Graphic Artist - User Experience Designer</span>
                    </div>
                </div>
            </div>
        </div>
    </header>
-->

<div class="container">
	<div class="row"><br><br>
	</div>
</div>



    <!-- User Top Section -->
    <section id="UserTop">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2>Report</h2>
                    <hr class="star-primary">
                </div>
            </div>
            </div>
      </section>


      <section id="Ranking">
        <div class="container">


            <div class="row">

		  <div class="panel panel-default">
		  <div class="panel-heading"><h2>報告一覧</h2></div>

		  <form action="ReportListDisplayWindow" method="post">
		  <input type="hidden" name="action">

		  <div class="list-group">
		  	<% int n = 1,m = 1,u = 1;
    		   for(Report report : reportList) { %>
			  <button type="button" class="list-group-item"><%= n %><span lang="ja">&nbsp;&nbsp;&nbsp;
			  </span>&nbsp;<a href="#" onClick="goSubmit(this.form, this)" name="report<%= report.getReportID() %>" value="レポート">
			 						   <% if(report.getReportedmidiID() != 0) { %>midi file <%= m %>
			  						   <% m++; } else { %>user <%= u %>
			  						   <% u++; } %></a></button>
			<% n++; } %>
			</div>

		  </form>

			</div>
		  </div>






		</div>
		<div class="text-center">
			<nav>
			  <ul class="pagination">
			    <li class="disabled">
			      <a href="#" aria-label="Previous">
			        <span aria-hidden="true">&laquo;</span>
			      </a>
			    </li>
			    <li class="active"><a href="#">1</a></li>
			    <li><a href="#">2</a></li>
			    <li><a href="#">3</a></li>
			    <li><a href="#">4</a></li>
			    <li><a href="#">5</a></li>
			    <li>
			      <a href="#" aria-label="Next">
			        <span aria-hidden="true">&raquo;</span>
			      </a>
			    </li>
			  </ul>
			</nav>
			</div>
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

	<script type="text/javascript">
	<!--
	function goSubmit2(formObj, btnObj) {
	formObj.report.value=btnObj.value;
	formObj.report=btnObj.name;
	formObj.submit();
	}
	 -->
	</script>

    <!-- Plugin JavaScript -->

    <!-- Contact Form JavaScript -->

    <!-- Custom Theme JavaScript -->
    <script src="js/freelancer.js"></script>

</body>

</html>
