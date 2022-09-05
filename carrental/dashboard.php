<?php
include 'includes/config.php';
include 'includes/session.php';
error_reporting(0);

 ?>
<!doctype html>
<html lang="en" class="no-js">

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">
	<meta name="theme-color" content="#3e454c">
	
	<title>Car Rental Portal | Admin Dashboard</title>

	<!-- Font awesome -->
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<!-- Sandstone Bootstrap CSS -->
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<!-- Bootstrap Datatables -->
	<link rel="stylesheet" href="css/dataTables.bootstrap.min.css">
	<!-- Bootstrap social button library -->
	<link rel="stylesheet" href="css/bootstrap-social.css">
	<!-- Bootstrap select -->
	<link rel="stylesheet" href="css/bootstrap-select.css">
	<!-- Bootstrap file input -->
	<link rel="stylesheet" href="css/fileinput.min.css">
	<!-- Awesome Bootstrap checkbox -->
	<link rel="stylesheet" href="css/awesome-bootstrap-checkbox.css">
	<!-- Admin Stye -->
	<link rel="stylesheet" href="css/style.css">
</head>

<body>
	<?php include('includes/header.php');?>

	<div class="ts-main-content">
<?php include('includes/leftbar.php');?>
		<div class="content-wrapper">
			<div class="container-fluid">

				<div class="row">
					<div class="col-md-12">

						<h2 class="page-title">Dashboard</h2>
						
						<div class="row">
							<div class="col-md-12">
								<div class="row">
									<div class="col-md-3">
										<div class="panel panel-default">
											<div class="panel-body bk-primary text-light">
												<div class="stat-panel text-center">
<?php 
$sql =mysqli_query($conn,"SELECT user_id from tbl_user ") or die(mysqli_error($conn));
$cnt=0;

		while($row=mysqli_fetch_assoc($sql))
			{
				$cnt=$cnt=$cnt+1;
			}
			$regusers=$cnt;
	
?>
													<div class="stat-panel-number h1 "><?php echo $regusers ?></div>
													<div class="stat-panel-title text-uppercase">Registered Users</div>
												</div>
											</div>
											<a href="reg-users.php" class="block-anchor panel-footer text-center" style="background-color:#001662; color:white;">Full Detail <i class="fa fa-arrow-right"></i></a>
										</div>
									</div>	
				<div class="col-md-3">
										<div class="panel panel-default">
											<div class="panel-body bk-success text-light">
												<div class="stat-panel text-center">
<?php 
$sql =mysqli_query($conn,"SELECT vehicle_id from tbl_vehicles ") or die(mysqli_error($conn));
$cnt=0;

		while($row=mysqli_fetch_assoc($sql))
			{
				$cnt=$cnt=$cnt+1;
			}
			$totalvehicle=$cnt;
	
?>
													<div class="stat-panel-number h1 "><?php echo htmlentities($totalvehicle);?></div>
													<div class="stat-panel-title text-uppercase">Vehicles</div>
												</div>
											</div>
											<a href="manage-vehicles.php" class="block-anchor panel-footer text-center" style="background-color:#001662; color:white;">Full Detail &nbsp; <i class="fa fa-arrow-right"></i></a>
										</div>
									</div>
									<div class="col-md-3">
										<div class="panel panel-default">
											<div class="panel-body bk-info text-light">
												<div class="stat-panel text-center">
<?php 
$status=0;
$sql =mysqli_query($conn,"SELECT b_id from tbl_booking where b_status=$status ") or die(mysqli_error($conn));
$cnt=0;

		while($row=mysqli_fetch_assoc($sql))
			{
				$cnt=$cnt=$cnt+1;
			}
			$newb=$cnt;
	
?>

													<div class="stat-panel-number h1 "><?php echo htmlentities($newb);?></div>
													<div class="stat-panel-title text-uppercase">New Bookings</div>
												</div>
											</div>
											<a href="new-bookings.php" class="block-anchor panel-footer text-center" style="background-color:#001662; color:white;">Full Detail &nbsp; <i class="fa fa-arrow-right"></i></a>
										</div>
									</div>
									<div class="col-md-3">
										<div class="panel panel-default">
											<div class="panel-body bk-warning text-light">
												<div class="stat-panel text-center">
<?php 
$sql =mysqli_query($conn,"SELECT brand_id from tbl_brand ") or die(mysqli_error($conn));
$cnt=0;

		while($row=mysqli_fetch_assoc($sql))
			{
				$cnt=$cnt=$cnt+1;
			}
			$brand=$cnt;
	
?>										
													<div class="stat-panel-number h1 "><?php echo htmlentities($brand);?></div>
													<div class="stat-panel-title text-uppercase">Brands</div>
												</div>
											</div>
											<a href="manage-brands.php" class="block-anchor panel-footer text-center" style="background-color:#001662; color:white;">Full Detail &nbsp; <i class="fa fa-arrow-right"></i></a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-12">

						
						<div class="row">
							<div class="col-md-12">
								<div class="row">
									<div class="col-md-3">
										<div class="panel panel-default">
											<div class="panel-body bk-primary text-light">
												<div class="stat-panel text-center">
<?php 
$sql =mysqli_query($conn,"SELECT d_id from tbl_driver ") or die(mysqli_error($conn));
$cnt=0;

		while($row=mysqli_fetch_assoc($sql))
			{
				$cnt=$cnt=$cnt+1;
			}
			$driver=$cnt;
	
?>
													<div class="stat-panel-number h1 "><?php echo htmlentities($driver);?></div>
													<div class="stat-panel-title text-uppercase">Driver's</div>
												</div>
											</div>
											<a href="manage-driver.php" class="block-anchor panel-footer text-center" style="background-color:#001662; color:white;">Full Detail <i class="fa fa-arrow-right"></i></a>
										</div>
									</div>
									<div class="col-md-3">
										<div class="panel panel-default">
											<div class="panel-body bk-success text-light">
												<div class="stat-panel text-center">
<?php 
$s=0;
$sql =mysqli_query($conn,"SELECT k_id from tbl_kyc where status=$s") or die(mysqli_error($conn));
$cnt=0;

		while($row=mysqli_fetch_assoc($sql))
			{
				$cnt=$cnt=$cnt+1;
			}
			$kyc=$cnt;
	
?>
													<div class="stat-panel-number h1 "><?php echo htmlentities($kyc);?></div>
													<div class="stat-panel-title text-uppercase">New KYC</div>
												</div>
											</div>
											<a href="manage-conactusquery.php" class="block-anchor panel-footer text-center" style="background-color:#001662; color:white;">Full Detail &nbsp; <i class="fa fa-arrow-right"></i></a>
										</div>
									</div>
									<div class="col-md-3">
										<div class="panel panel-default">
											<div class="panel-body bk-info text-light">
												<div class="stat-panel text-center">
<?php 
$sql =mysqli_query($conn,"SELECT id from tbl_inquiry") or die(mysqli_error($conn));
$cnt=0;

		while($row=mysqli_fetch_assoc($sql))
			{
				$cnt=$cnt=$cnt+1;
			}
			$iny=$cnt;
	
?>

													<div class="stat-panel-number h1 "><?php echo htmlentities($iny);?></div>
													<div class="stat-panel-title text-uppercase">Inquiries</div>
												</div>
											</div>
											<a href="manage-conactusquery.php" class="block-anchor panel-footer text-center" style="background-color:#001662; color:white;">Full Detail &nbsp; <i class="fa fa-arrow-right"></i></a>
										</div>
									</div>
										<div class="col-md-3">
										<div class="panel panel-default">
											<div class="panel-body bk-info text-light">
												<div class="stat-panel text-center">
<?php 
$sql =mysqli_query($conn,"SELECT r_id from tbl_reviewrating") or die(mysqli_error($conn));
$cnt=0;

		while($row=mysqli_fetch_assoc($sql))
			{
				$cnt=$cnt=$cnt+1;
			}
			$rr=$cnt;
	
?>

													<div class="stat-panel-number h1 "><?php echo htmlentities($rr);?></div>
													<div class="stat-panel-title text-uppercase">Reviews & Rating</div>
												</div>
											</div>
											<a href="manage-reviewrating.php" class="block-anchor panel-footer text-center" style="background-color:#001662; color:white;">Full Detail &nbsp; <i class="fa fa-arrow-right"></i></a>
										</div>
									</div>
								
								</div>
							</div>
						</div>
					</div>
				</div>
<div class="row">
	<div class="col-md-12">
			<div class="row">
				<div class="col-md-3">
				<div class="panel panel-default">
											<div class="panel-body bk-info text-light">
												<div class="stat-panel text-center">
<?php 
$sql =mysqli_query($conn,"SELECT Sid from  tbl_subscribers") or die(mysqli_error($conn));
$cnt=0;

		while($row=mysqli_fetch_assoc($sql))
			{
				$cnt=$cnt=$cnt+1;
			}
			$s=$cnt;
	
?>

													<div class="stat-panel-number h1 "><?php echo htmlentities($s);?></div>
													<div class="stat-panel-title text-uppercase">Subscribers</div>
												</div>
											</div>
											<a href="manage-subscribers.php" class="block-anchor panel-footer text-center" style="background-color:#001662; color:white;">Full Detail &nbsp; <i class="fa fa-arrow-right"></i></a>
										</div>
				
									
								
								
							</div>
						</div>
				</div>

									
				
				
			</div>
		</div>
	</div>
					
	
	
	<!-- Loading Scripts -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap-select.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.dataTables.min.js"></script>
	<script src="js/dataTables.bootstrap.min.js"></script>
	<script src="js/Chart.min.js"></script>
	<script src="js/fileinput.js"></script>
	<script src="js/chartData.js"></script>
	<script src="js/main.js"></script>
	
	<script>
		
	window.onload = function(){
    
		// Line chart from swirlData for dashReport
		var ctx = document.getElementById("dashReport").getContext("2d");
		window.myLine = new Chart(ctx).Line(swirlData, {
			responsive: true,
			scaleShowVerticalLines: false,
			scaleBeginAtZero : true,
			multiTooltipTemplate: "<%if (label){%><%=label%>: <%}%><%= value %>",
		}); 
		
		// Pie Chart from doughutData
		var doctx = document.getElementById("chart-area3").getContext("2d");
		window.myDoughnut = new Chart(doctx).Pie(doughnutData, {responsive : true});

		// Dougnut Chart from doughnutData
		var doctx = document.getElementById("chart-area4").getContext("2d");
		window.myDoughnut = new Chart(doctx).Doughnut(doughnutData, {responsive : true});

	}
	</script>
</body>
</html>

