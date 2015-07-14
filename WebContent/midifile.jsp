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

    <title>MIDI File Name</title>

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

    <%@ page import="model.Midifile" %>
    <%@ page import="model.User" %>
    <%@ page import="model.UserManager" %>
    <%@ page import="model.Comment" %>
    <%@ page import="model.CommentManager" %>
	<%@ page import="model.Translate" %>
	<%@ page import="java.io.*" %>
	<%@ page import="javax.sound.midi.*" %>
	<%@ page import="java.util.*" %>
	<%@ page import="model.MidifileManager" %>

	<%
	User user = (User)session.getAttribute("user");
	Midifile midifile = (Midifile)session.getAttribute("midifile");

	Translate t = new Translate();
	CommentManager commentManager = new CommentManager();
	UserManager userManager = new UserManager();
	MidifileManager m = new MidifileManager();

	ArrayList<Comment> commentList;
	try{
		commentList=commentManager.returnComments(midifile.getMidiID());
	}catch(NullPointerException e){
		commentList = new ArrayList<Comment>();
	}


	List<Midifile> midifiles;

	try{
	midifiles = m.searchList(user.getUserID());
	request.setAttribute("midifiles",midifiles);
	}catch(NullPointerException e){
		System.out.println(e);
	}


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
                <a class="navbar-brand" href="memberTop.jsp">MidiCloud</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li class="hidden">
                        <a href="#page-top"></a>
                    </li>
                    		    <li>

<form class="form-inline" action="MidiInformationWindow" method="post">
 <div class="input-group">
      <input type="text" class="form-control" placeholder="Search for..." name="search">
      <span class="input-group-btn">
        <button class="btn btn-default" type="submit" name="goSearch" value="検索">Search</button>
      </span>
 </div>
</form>
</li>

<li role="presentation" class="dropdown">

    <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false" name="username">
     <img alt="icon" src="OutputImg" height=40px width=40px> <%= user.getUsername() %> <span class="caret"></span>
	</a>

    <ul class="dropdown-menu">
	<li>
		<a href="Profile?UserID=<%= user.getUserID() %>~" name="detail" value="ユーザー詳細">Detail</a>
	</li>
	<li role="separator" class="divider"></li>
	<li>
		<a href="Login" name="logout" value="ログアウト">Logout</a>
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
                    <h2>Midi File</h2>
                    <hr class="star-primary">
                </div>
            </div>
            </div>
      </section>

<div class="container">
<div class="row">
            <div class="col-lg-12-original">

<div class="text-right">
<img alt="icon" src="OutputMidiImg" width=60px height=60px>  <%= userManager.returnUser( midifile.getUserID() ).getUsername() %>
</div>

            <div class="text-left">
                <h2><%= midifile.getTitle() %></h2>



<br>

<br><br>

<form action="MidiInformationWindow" method="post">
<input type="hidden" name="action">

<button type="submit" class="btn btn-info  btn-sm" name="favo" value="<%= midifile.getFavorite() +1 %>">
  <span class="glyphicon glyphicon-star" aria-hidden="true"></span> Favorite <span class="badge">
  <%= midifile.getFavorite() %></span>
</button>

<button type="submit" class="btn btn-default btn-sm" name="download" value="DL">
  <span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span> Download
</button>




            <% if(midifile.getUserID() == user.getUserID()) { %>
				<button type="submit" class="btn btn-default btn-sm" name="edit" value="編集">
 				 <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Edit
				</button>
			<% } %>
            <%
            if(user.isManager() || user.getUserID() == midifile.getUserID()) { %>
				<button type="submit" class="btn btn-default btn-sm" name="delete" value="削除">
 				 <span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Delete
				</button>
			<% } else { %>
			<button type="submit" class="btn btn-default btn-sm btn-warning" name="report" value="報告">
 				 <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span> Report
				</button>
			<% } %>
<br><br>

</form>

<div class="panel panel-default">
<div class="panel-body">

                <p><%= midifile.getExplanation() %></p>

</div>
</div>



            </div>



</div>
</div>
</div>
<br><br>

<div class="container">
<div class="row">

<div class="panel panel-default">

<div class="well">

<form action="MidiInformationWindow" method="post">

<div class="row">
<div class="form-group">
<label for="InputProfile">Comment</label>
<textarea class="form-control" id="UserProfile" name="userComment" placeholder="コメント" rows="5"></textarea>
</div>

<div class="form-group">
<div class="col-md-12 text-center">
<button type="submit" class="btn btn-info btn-sm" name="commentUpload">投稿</button>
</div>
</div>
</div>

</form>
</div>


<!-- Timeline - START -->
<div class="container">
    <ul class="timeline">
    	<% for(Comment comment : commentList){ %>
        <li><!---Time Line Element--->
          <div class="timeline-badge blue"></div>
          <div class="timeline-panel">
            <div class="timeline-heading">
              <h4 class="timeline-title"> <%= userManager.returnUser(comment.getUserID()).getUsername() %> </h4>
            </div>
            <div class="timeline-body"><!---Time Line Body&Content--->
              <p><%= comment.getComment() %></p>
            </div>
          </div>
        </li>
        <% } %>
    </ul>
</div>








</div>
</div>



</div>




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


    <!-- Custom Theme JavaScript -->
    <script src="js/freelancer.js"></script>

</body>

</html>
