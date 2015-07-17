<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>MiDi Cloud</title>

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

    <script type="text/javascript">
	<!--
	function goSubmit(formObj, btnObj) {
	formObj.action.value=btnObj.name;
	formObj.submit();
	}
	 -->
	</script>



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
                        <a href=""></a>
                    </li>
                    <li>
                    <button type="button" class="btn btn-default btn-sm" name="attention" value="注意" data-toggle="modal" data-target="#myModal">
 				 Attention
				</button>
                    </li>
                    <li>

                    <!-- href="遷移先?hiddenのname=送信したい値&hiddenのname2=送信したい値2~"  -->
                    	<a href="CreateAccountWindow" name="GoMakeAccountPage" value="新規登録">新規登録</a>
                    	<input type="hidden" name="action">
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>



<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel"><span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span> 注意！</h4>
      </div>
      <div class="modal-body">
      <p>This web site is English Only.</p>
      <p>※このサイトにはクロスサイトスクリプティング(XSS)等の脆弱性があります。</p>
      </div>
    </div>
  </div>
</div>






 <header>




        <div class="container">
            <div class="intro-text">


 <div class="col-md-4-original">

 <div class="col-lg-12 text-center">
                    <h2>MiDi Cloud</h2>
                    <div class="intro-heading">新しい音楽の世界をあなたに</div>

                                    </div>
            </div>




  </div>


    <div class="col-md-4-original">


        <div class="container" style="margin-top: 30px">
            <div class="col-md-4-original2">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h1 class="panel-title">Sign In</h1>
                    </div>
                    <div class="panel-body">


<%
	if(request.getAttribute("error") != null){
%>
<%= request.getAttribute("error") %>
<%
	}
%>



                                            <form action="Login" method="post">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="E-mail" name="email" type="email" required="required" autofocus="autofocus"/>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="password" name="pass" type="password" required="required" />
                                </div>

                                <button type="submit" class="btn btn-sm btn-info" name="login" value="ログイン">Sign in</button>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-4"></div>





            </div>
    </header>














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


    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
    <script src="js/cbpAnimatedHeader.js"></script>

    <!-- Contact Form JavaScript -->
    <script src="js/jqBootstrapValidation.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="js/freelancer.js"></script>

</body>

</html>
