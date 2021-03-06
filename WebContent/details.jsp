<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            <li><a href="#">Order Shipment</a></li>
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
              <ul class="dropdown-menu" role="menu" style="display: none">
                <li><a href="#">My profile</a></li>
                <li><a href="#">My bookings</a></li>
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
      <!-- Content Header (Page header) -->
      <section class="content-header">
        <h1>
          Order details
        </h1>
      </section>

      <!-- Main content -->
      <section class="content" id="customerinfo">
        <div class="callout callout-info">
          <h4>Booking information</h4>
          <ul>
            <li><span>Ordered time</span>: <span>${order.bookingTime}</span></li>
            <li><span>Acknowledged time</span>: <span>${order.acknowledgeTime}</span></li>
            <li><span>Number of boxes</span>: <span>${order.quantity}</span></li>
            <li><span>Destination Address in Jakarta</span>: <span>${order.address}</span></li>
            <li><span>Pickup Address</span>: <span>${order.pickupAddress}</span></li>
            <li><span>Pickup Time</span>: <span>${order.pickupTime}</span></li>
            <li><span>Preferred Departure Date</span>: <span>${order.preferredDeparture}</span></li>
            <li><span>Preferred Arrival Date</span>: <span>2018-10-25</span></li>
            <li><span>Cost</span>: <span>${order.cost}</span></li>
            <li><span>Status</span>: <span>${order.status}</span></li>
            <li><span>Message to shipper</span>: <span>${order.message}</span></li>
          </ul>
        </div>
        <div class="callout callout-warning">
          <h4>Tracking information</h4>

          <table class="table">
            <tr>
              <th>Date/Time</th>
              <th>Description</th>
            </tr>
            <c:if test="${not empty order.deliveryDate}">
	            <tr>
	              <td>${order.deliveryDate}</td>
	              <td>Arrived at ${order.address}.</td>
	            </tr>
            </c:if>
            <c:if test="${not empty order.arrivalDate}">
            <tr>
              <td>${order.arrivalDate}</td>
              <td>Arrived at ${order.address}.</td>
            </tr>
            </c:if>
            <c:if test="${not empty order.departureDate}">
            <tr>
              <td>${order.departureDate}</td>
              <td>Shipped.</td>
            </tr>
            </c:if>
            <c:if test="${not empty order.acknowledgeTime}">
            <tr>
              <td>${order.acknowledgeTime}</td>
              <td>Shipment acknowledged.</td>
            </tr>
            </c:if>
            <tr>
              <td>${order.bookingTime}</td>
              <td>Shipment successfully booked.</td>
            </tr>
          </table>
        </div>
      </section>

      <!-- Main content -->
      <section class="content" id="managerinfo" style="display: none">
        <div class="callout callout-info">
          <h4>Booking information</h4>
          <ul>
            <li><span>Ordered time</span>: <span>15/09/2018</span></li>
            <li><span>Acknowledged time</span>: <span>15/09/2018</span></li>
            <li><span>Number of boxes</span>: <span>2</span></li>
            <li><span>Destination Address in Jakarta</span>: <span>XXXXXXX</span></li>
            <li><span>Pickup Address</span>: <span>XXXXXXXX</span></li>
            <li><span>Preferred Departure Date</span>: <span>25/09/2018</span></li>
            <li><span>Estimated Arrival Date</span>: <span>25/10/2018</span></li>
            <li><span>Cost</span>: <span>$70</span></li>
            <li><span>Status</span>: <span>Shipped</span></li>
            <li><span>Message to shipper</span>: <span></span></li>
          </ul>
        </div>
        <div class="callout callout-warning">
          <h4>Tracking information</h4>

          <table class="table">
            <tr>
              <th>Date</th>
              <th>Description</th>
            </tr>
            <tr>
              <td>25/09/2018</td>
              <td>Shipped at XXX</td>
            </tr>
            <tr>
              <td>15/09/2018</td>
              <td>Picked up from XXX</td>
            </tr>
          </table>
        </div>
      </section>

      <!-- /.content -->
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
