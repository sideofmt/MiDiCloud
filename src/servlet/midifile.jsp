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

	<% //Midifile midifile = (Midifile)session.getAttribute("midifile");
	   //User user = (User)session.getAttribute("user");
	   Translate t = new Translate();
	   CommentManager commentManager = new CommentManager();
	   //ArrayList<Comment> commentList = commentManager.returnComments(midifile.getMidiID());
	   UserManager userManager = new UserManager();
	   
	   
	   User user = new User();
	   /*Midifile midifile = new Midifile();
	   try {
			midifile.setMidifile(t.fileLoad("C:/Users/shigetoshi.n/Desktop/ソフ研/曲/e.t.c/Argento/a.mid"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	   midifile.setFavorite(8);
	   midifile.setTitle("midiファイルだお♪");
	   midifile.setUserID(1);
	   midifile.setExplanation("俺の名前はk-t（工藤 ティンイティ）。大学生DTMerだ。"
				+ "ある日、俺は同級生で幼馴染のm-t（毛利 ティン）とYAMAHAに遊びに来ていた。"
				+ "その途中、黒づくめの組織の怪しげな取引現場に遭遇した。"
				+ "取引現場に夢中になっていた俺は、背後から近付いてくるもう一人の男の気配に気づかず、"
				+ "目が覚めたら・・・DAWが異常終了してしまっていた！！");
	   */
	   user.setUserID(2);
	   MidifileManager ma = new MidifileManager();
	   Midifile midifile = ma.search(1);
	   request.setAttribute("midifile", midifile);
	   user.setManager(false);
	   ArrayList<Comment> commentList = new  ArrayList<Comment>();
	   Comment c = new Comment();
	   c.setUserID(3);
	   c.setComment("黒ずくめの組織のボスはわしじゃよ。");
	   commentList.add(c);
	   
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
                <a class="navbar-brand" href="#page-top">MidiCloud</a>
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
<img alt="icon" src="..."> UserName

</div>

            <div class="text-left">
                <h2><%= midifile.getTitle() %></h2>
                


<br>
<%
	
%>
<form action="OutputFile" method="get">
<embed src="OutputFile" type="application/x-mplayer2" height="40" autostart="true" autoplay="true" loop="false">
</embed>
</form>
<br><br>

<form action="MidiInformationWindow" method="post">
<input type="hidden" name="action">

<button type="button" class="btn btn-info  btn-sm" onClick="goSubmit(this.form, this)" name="favo">
  <span class="glyphicon glyphicon-star" aria-hidden="true"></span> Favorite <span class="badge">
  <%= midifile.getFavorite() %></span>
</button>

<button type="button" class="btn btn-default btn-sm">
  <span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span> Download
</button>




            <% if(midifile.getUserID() == user.getUserID()) { %>
				<button type="button" class="btn btn-default btn-sm">
 				 <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>変更する
				</button>
				<button type="button" class="btn btn-default btn-sm">
 				 <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>削除する
				</button>
			<% } else if(user.isManager()) { %>
				<button type="button" class="btn btn-default btn-sm">
 				 <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>削除する
				</button>
			<% } else {%>
			<button type="button" class="btn btn-default btn-sm btn-warning">
 				 <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>報告する
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
<textarea class="form-control" id="UserProfile" name="UserProfile" placeholder="コメント" rows="5"></textarea>
</div>

<div class="form-group">
<div class="col-md-12 text-center">
<button type="submit" class="btn btn-info btn-sm">投稿</button>
</div>
</div>
</div>

</form>
</div>


<!-- Timeline - START -->
<div class="container">
    <ul class="timeline">
    	<% for(Comment comment :commentList) {%>
    	<% User commentUser = userManager.returnUser(comment.getUserID()); %>
        <li><!---Time Line Element--->
          <div class="timeline-badge blue"><img alt="icon" src="<%-- commentUser.getIcon() --%>"></div>
          <div class="timeline-panel">
            <div class="timeline-heading">
              <h4 class="timeline-title"><%--<%= commentUser.getUsername() %>--%></h4>
            </div>
            <div class="timeline-body"><!---Time Line Body&Content--->
              <p><%= comment.getComment() %></p>
            </div>
          </div>
        </li>
        <% } %>
    </ul>
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
