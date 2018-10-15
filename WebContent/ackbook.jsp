<%@ page language="java" import="java.util.*" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
  <!-- daterange picker -->
  <link rel="stylesheet" href="bower_components/bootstrap-daterangepicker/daterangepicker.css">
  <!-- bootstrap datepicker -->
  <link rel="stylesheet" href="bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">

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
            <li><a href="boxFly.html">Home</a></li>
            <li class="active"><a href="book.html">Order Shipment</a></li>
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
                <li><a href="#">My profile</a></li>
                <li><a>
                <form action="HistoryController" method="post">
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
     <!-- /.box -->
      <!-- general form elements disabled -->
      <div class="box box-info">
        <div class="box-header with-border">
          <h3 class="box-title">Acknowledge a shipment</h3>
        </div>
        <!-- /.box-header -->
        <div class="box-body">
          <form action="AckSubmitController" method="post">
            <div class="form-group">
              <label>Status</label>
              <select class="form-control" name="status" disabled>
                <option value=${order.status}>Request Accepted</option>
                <option value="To be Approved">To be Approved</option>
                <option value="Request Accepted">Request Accepted</option>
                <option value="Pick-up Scheduled">Pick-up Scheduled</option>
                <option value="To be Shipped">To be Shipped</option>
                <option value="Shipped">Shipped</option>
                <option value="Arrived at Destination">Arrived at Destination</option>
                <option value="Delivered">Delivered</option>
                <option value="Delivery Delayed">Delivery Delayed</option>
              </select>
            </div>
            <div class="form-group">
              <label>Pickup Date:</label>
              <div class="input-group date">
                <div class="input-group-addon">
                  <i class="fa fa-calendar"></i>
                </div>
                <input type="date" name="pickupdate" class="form-control pull-right" id="datepicker1" required>
              </div>
              <!-- /.input group -->
            </div>
            <div class="form-group">
              <label>Pickup Time:</label>
              <div class="input-group date">
                <div class="input-group-addon">
                  <i class="fa fa-calendar"></i>
                </div>
                <input type="time" name="pickuptime" class="form-control pull-right" id="datepicker2" required>
              </div>
              <!-- /.input group -->
            </div>
            <!-- text input -->
            <div class="form-group">
              <label>Cost</label>
              <input type="number" name="cost" class="form-control" value=${order.cost} disabled>
            </div>
            <div class="form-group">
              <label>HBL number</label>
              <input type="text" name="HBL" class="form-control" value=${order.hblNumber} disabled>
            </div>
            <div class="row row-check">
                <div class="col-xs-9">
                </div>
        <!-- /.col -->
                <div class="col-xs-3">
                	<input type="hidden" id="orderId" name="orderId" value=${order.orderId}>
                    <button type="submit" class="btn btn-primary btn-block btn-flat">Submit</button>
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
<!-- Select2 -->
<script src="bower_components/select2/dist/js/select2.full.min.js"></script>
<!-- date-range-picker -->
<script src="bower_components/moment/min/moment.min.js"></script>
<script src="bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
<!-- bootstrap datepicker -->
<script src="bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
<!-- InputMask -->
<script src="plugins/input-mask/jquery.inputmask.js"></script>
<script src="plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="plugins/input-mask/jquery.inputmask.extensions.js"></script>
<!-- date-range-picker -->
<script src="bower_components/moment/min/moment.min.js"></script>
<script src="bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
<!-- AdminLTE App -->
<script src="dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="dist/js/demo.js"></script>

<script>
  $(function(){
    //Initialize Select2 Elements
    $('.select2').select2();
    $('#eservation').daterangepicker();
    //Date picker
    $('#datepicker1').datepicker({
      autoclose: true
    })
    //Date picker
    $('#datepicker2').datepicker({
      autoclose: true
    })
  });
</script>
</body>
</html>
