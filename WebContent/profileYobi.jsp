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
    <%@ page import="model.Midifile" %>
    <%@ page import="model.MidifileManager" %>
    <%@ page import="java.util.*" %>

    <%
    User user = (User)session.getAttribute("user");
    User otherUser = (User)request.getAttribute("showuser");
    List<Midifile> midifiles = null;
    if(request.getAttribute("midifiles") != null){
    	midifiles = (List<Midifile>)request.getAttribute("midifiles");
    }
    request.setAttribute("showUser",otherUser);
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
                <a class="navbar-brand" href="MemberTopWindow" name="midicloud" value="MidiCloud">MidiCloud</a>
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
      <input type="text" class="form-control" placeholder="Search for..." name="search">
      <span class="input-group-btn">
        <button class="btn btn-default" type="submit" name="goSearch" value="検索">Search</button>
      </span>
 </div>
</form>
</li>

<li role="presentation" class="dropdown">

    <input type="hidden" name="action">
    <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false" name="username">
     <img alt="icon" src="OutputFile" height=10px width=10px> <%= user.getUsername() %> <span class="caret"></span>
    </a>

    <ul class="dropdown-menu">
	<li>
		<input type="hidden" name="otherUser">
		<a href="Profile?UserID=<%= user.getUserID() %>~" name="detail" value="ユーザー詳細">Detail</a>
	</li>
	<li role="separator" class="divider"></li>
	<li>
		<a href="" name="logout" value="ログアウト">Logout</a>
	</li>

    </ul>
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

                <img class="img-responsive img-center" src="OutputUserImg" alt="" height=200px width=200px>
                <h2><%= otherUser.getUsername() %>
                    <small><%= otherUser.getUsername() %></small>
                </h2>


			<form action="AccountInfoWindow" method="post">
			<input type="hidden" name="action">

			<% if(otherUser.getUserID() == user.getUserID()){ %>
				<button type="button" class="btn btn-default btn-sm" onClick="goSubmit(this.form, this)" name="edit" value="ユーザーの編集">
				 <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>Edit
				</button>
			<% } %>
			<% if(otherUser.getUserID() != user.getUserID()){ %>
				<button type="button" class="btn btn-default btn-sm btn-warning" onClick="goSubmit(this.form, this)" name="report" value="不適切なユーザーを報告">
				 <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>report
				</button>
			<% } %>
			<% if(otherUser.getUserID() == user.getUserID() || user.isManager() ) { %>
				<button type="button" class="btn btn-default btn-sm" onClick="goSubmit(this.form, this)" name="delete" value="ユーザーの削除">
				 <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>delete
				</button>
			<% } %>
			<% if(otherUser.getUserID() == user.getUserID() ) { %>
				<button type="button" class="btn btn-default btn-sm" onClick="goSubmit(this.form, this)" name="upload" value="Midiのアップロード">
				 <span class="glyphicon glyphicon-open" aria-hidden="true"></span>Upload Midi
				</button>
			<% } %>
			</form>


<br><br>


<div class="panel panel-default">
<div class="panel-body">
                <p><%= otherUser.getProfile() %></p>
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
		  <input type="hidden" name="action">

		<%
		if(midifiles != null){

		int n=0;
		for(Midifile midifile : midifiles){
		%>

		<button type="button" class="list-group-item"><%= n+1 %><span lang="ja">&nbsp;&nbsp;&nbsp;
		</span>&nbsp;<a href="" onClick="goSubmit2(this.form, this)" name="midi" value=<%= midifile %> ><%= midifile.getTitle() %></a></button>

		<%
		n++;
		}

		}
		%>

		</form>
			</div>
		  </div>






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
	formObj.midi.value=btnObj.value;
	formObj.submit();
	}
	 -->
	</script>


    <!-- Contact Form JavaScript -->


    <!-- Custom Theme JavaScript -->
    <script src="js/freelancer.js"></script>

</body>

</html>