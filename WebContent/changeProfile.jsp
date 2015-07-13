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

    <title>Edit User</title>

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
	<%@ page import="model.Translate" %>
	<%@ page import="java.lang.*" %>

    <% //User user = (User)session.getAttribute("user");
    
    
       User user = new User();
       user.setUsername("k-t");
       user.setProfile("俺の名前はk-t（工藤 ティンイティ）。大学生DTMerだ。"
				+ "ある日、俺は幼馴染で同級生のm-t（毛利 ティン）と遊園地に、せやかて工藤！！");
       //Translate t = new Translate();
       //user.setIcon(t.fileLoad("C:/Users/shigetoshi.n/Desktop/ソフ研/mudai.png"));
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
                    <h2>Edit User Profile</h2>
                    <hr class="star-primary">
                </div>
            </div>
            </div>
      </section>

<form action="AccountInformationChangeWindow" method="post">

<div class="container">
<div class="row">
            <div class="col-lg-12-original">
            <div class="text-left">
                <img class="img-responsive img-center" src="http://placehold.it/200x200" alt="">


<p>
<div class="form-group">
    <label for="exampleInputFile">User Icon</label>
    <input type="file" id="exampleInputFile" name="icon">
  </div>
</p>

<p>
<div class="input-group">
<label for="InputUserName">User Name</label>
<%-- <input type="text" class="form-control" placeholder="" aria-describedby="basic-addon1">--%>
<input type="text" class="form-control" value="<%= user.getUsername() %>" aria-describedby="basic-addon1" name="name">
</div>
</p>




<p>
<div class="form-group">
<label for="InputProfile">User Profile</label>
<textarea class="form-control" id="UserProfile" name="profile" placeholder="" rows="7">
<%= user.getProfile() %>
</textarea>

</div>
</p>

<div class="form-group">
<div class="col-md-12 text-center">
<button type="submit" class="btn btn-info btn-lg" name="change">プロフィールを変更する</button>
</form>
</div>
</div>


</div>



</div>
</div>
</div>


<div class="row">
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
