<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>BoxFly Shipment</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="bower_components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="bower_components/Ionicons/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">

  <link rel="stylesheet" type="text/css" href="style.css">
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

  <!-- Google Font -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<!-- ADD THE CLASS layout-top-nav TO REMOVE THE SIDEBAR. -->
<body class="hold-transition skin-blue layout-top-nav">

<div class="wrapper">

  <header class="main-header">
    <nav class="navbar navbar-static-top">
      <div class="container">
        <div class="navbar-header">
          <a href="boxFly.html" class="navbar-brand"><b>BoxFly</b></a>
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse">
            <i class="fa fa-bars"></i>
          </button>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse pull-left" id="navbar-collapse">
          <ul class="nav navbar-nav">
            <li><a href="index.jsp">Home</a></li>
            <li><a href="order.jsp">Order Shipment</a></li>
            <li><a href="#">Track Order</a></li>
            <li><a href="#">Contact Us</a></li>
          </ul>
        </div>

        <!-- Navbar Right Menu -->
        <div class="navbar-custom-menu">
          <ul class="nav navbar-nav">
            <!-- Notifications Menu -->
            <li class="dropdown notifications-menu">
              <!-- Menu toggle button -->
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                <i class="fa fa-bell-o"></i>
                <span class="label label-warning">10</span>
              </a>
              <ul class="dropdown-menu">
                <li class="header">You have 10 notifications</li>
                <li>
                  <!-- Inner Menu: contains the notifications -->
                  <ul class="menu">
                    <li><!-- start notification -->
                      <a href="#">
                        <i class="fa fa-users text-aqua"></i> 5 new members joined today
                      </a>
                    </li>
                    <!-- end notification -->
                  </ul>
                </li>
                <li class="footer"><a href="#">View all</a></li>
              </ul>
            </li>
            <!-- User Account Menu -->
            <li class="dropdown user user-menu">
              <!-- Menu Toggle Button -->
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                <!-- The user image in the navbar-->
                <img src="dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
                <!-- hidden-xs hides the username on small devices so only the image appears. -->
                <span class="hidden-xs">Alexander Pierce</span>
              </a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="profile.html">My profile</a></li>
                <li><a>
                <form action="OrderListController" method="post">
                <button type="submit" style="border: none; background: none">My bookings</button>
                </form>
                </a>
                </li>
              </ul>
            </li>
          </ul>
        </div>
        <!-- /.navbar-custom-menu -->
      </div>
      <!-- /.container-fluid -->
    </nav>
  </header>
  <!-- Full Width Column -->
  <div class="content-wrapper">
    <div class="container">
      <!-- general form elements disabled -->
      <div class="box box-info">
        <div class="box-header with-border">
          <h3 class="box-title">Profile</h3>
        </div>
        <!-- /.box-header -->
        <div class="box-body">
          <form role="form">
            <!-- text input -->
            <div class="form-group">
              <label>Full Name</label>
              <input type="text" class="form-control" placeholder="Alexander">
            </div>
            <div class="form-group">
              <label>Email address</label>
              <input type="text" class="form-control" placeholder="alenander@gmail.com" disabled>
            </div>
            <!-- phone mask -->
            <div class="form-group">
              <label>Phone number:</label>
              <div class="input-group">
                <div class="input-group-addon">
                  <i class="fa fa-phone"></i>
                </div>
                <input type="text" class="form-control"
                       data-inputmask="'mask': '999-9999-999 [x99999]'"  placeholder="045XXXXXXX" data-mask>
              </div>
              <!-- /.input group -->
            </div>
            <!-- /.form group -->
            <div class="form-group">
              <label>Password</label>
              <input type="text" class="form-control" placeholder="*******">
            </div>
            <!-- select -->
            <div class="form-group">
              <label>Country</label>
              <select class="form-control" disabled>
                <option>Australia</option>
              </select>
            </div>
            <div class="form-group">
              <label>State</label>
              <select class="form-control" disabled>
                <option>VIC</option>
              </select>
            </div>
            <div class="form-group">
              <label>City</label>
              <select class="form-control" disabled>
                <option>Melbourne</option>
              </select>
            </div>
            <!-- textarea -->
            <div class="form-group">
              <label>Address</label>
              <textarea class="form-control" rows="3" placeholder="Enter ..."></textarea>
            </div>
            <div class="form-group">
              <label>Suburb</label>
              <select class="form-control">
                <option>Carlton</option>
                <option>CBD</option>
                <option>North Melbourne</option>
              </select>
            </div>
            <div class="row row-check">
              <div class="col-xs-9">
              </div>
      <!-- /.col -->
              <div class="col-xs-3">
                  <button type="submit" class="btn btn-primary btn-block btn-flat">Submit</button>
                  <button type="submit" class="btn btn-primary btn-block btn-flat" onClick="showChangepwd()">Change password</button>
              </div>
        <!-- /.col -->
            </div>
          </form>
        </div>
      <!-- /.box-body -->
      </div>
          <!-- /.box -->
    </div>
    <!-- /.container -->
  </div>
  <!-- /.content-wrapper -->

  <footer class="main-footer">
    <div class="container">
      <div class="pull-right hidden-xs">
        <b>Version</b> 1.0
      </div>
      <strong>Copyright &copy; 2018 <a href="#">SPM team, semester 2</a>.</strong> All rights
      reserved.
    </div>
    <!-- /.container -->
  </footer>
</div>
<!-- ./wrapper -->

<!-- jQuery 3 -->
<script src="bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="dist/js/demo.js"></script>

</body>
</html>
