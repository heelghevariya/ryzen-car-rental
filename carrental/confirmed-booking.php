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
	
	<title>Car Rental Portal | Manage New Bookings   </title>

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
  <style>
		.errorWrap {
    padding: 10px;
    margin: 0 0 20px 0;
    background: #fff;
    border-left: 4px solid #dd3d36;
    -webkit-box-shadow: 0 1px 1px 0 rgba(0,0,0,.1);
    box-shadow: 0 1px 1px 0 rgba(0,0,0,.1);
}
.succWrap{
    padding: 10px;
    margin: 0 0 20px 0;
    background: #fff;
    border-left: 4px solid #5cb85c;
    -webkit-box-shadow: 0 1px 1px 0 rgba(0,0,0,.1);
    box-shadow: 0 1px 1px 0 rgba(0,0,0,.1);
}
		</style>

</head>

<body>
	<?php include('includes/header.php');?>

	<div class="ts-main-content">
		<?php include('includes/leftbar.php');?>
		<div class="content-wrapper">
			<div class="container-fluid">

				<div class="row">
					<div class="col-md-12">

						<h2 class="page-title">Confirmed Booking</h2>

						<!-- Zero Configuration Table -->
						<div class="panel panel-default">
							<div class="panel-heading" style="background-color:#001662; color:white;">Booking information</div>
							<div class="panel-body">

								<table id="zctb" class="display table table-striped table-bordered table-hover" cellspacing="0" width="100%">
									<thead>
										<tr>
										<th>#</th>
											<th>Name</th>
											<th>Booking No.</th>
											<th>Vehicle</th>
											<th>From Date</th>
											<th>To Date</th>																																
											<th>Booking Status</th>
											<th>Booking Date</th>
											<th>Action</th>
										</tr>
									</thead>
									<tfoot>
										<tr>
											<th>#</th>										
											<th>Name</th>
											<th>Booking No.</th>
											<th>Vehicle</th>
											<th>From Date</th>
											<th>To Date</th>																																
											<th>Booking Status</th>
											<th>Booking Date</th>
											<th>Action</th>
										</tr>
									</tfoot>
									<tbody>

									<?php 
$status=1;
									$sql = mysqli_query($conn,"SELECT tbl_user.user_name,tbl_booking.b_id,tbl_brand.brand_name,tbl_booking.v_id,tbl_booking.booking_no,tbl_vehicles.vehicle_title,tbl_booking.from_date,tbl_booking.to_date,tbl_booking.b_status,tbl_booking.booking_date from tbl_booking join tbl_user on tbl_user.user_id=tbl_booking.u_id join tbl_vehicles on tbl_vehicles.vehicle_id=tbl_booking.v_id join tbl_brand on tbl_brand.brand_id=tbl_vehicles.brand_id where tbl_booking.b_status='".$status."'") or die(mysqli_error($conn));
$cnt=1;
		while($row=mysqli_fetch_assoc($sql))
		{
?>
										<tr>
											<td><?php echo htmlentities($cnt);?></td>
											<td><?php echo $row['user_name'];?></td>
											<td><?php echo $row['booking_no'];?></td>
											<td><a href="view-vehicles.php?id=<?php echo $row['v_id'];?>"><?php echo $row['brand_name'];?> , <?php echo $row['vehicle_title'];?></td>	
											<td><?php echo $row['from_date'];?></td>
											<td><?php echo $row['to_date'];?></td>
											<td>
											<?php 
												if($row['b_status']==0)
												{
												?><span class="label label-warning">Pending</span>
												<?php
												} else if ($row['b_status']==1) {
												?><span class="label label-success">Confirmed</span>
												<?php
												}
												 else{
												?><span class="label label-danger">Canceled</span>
												<?php
												 }
										?>
											</td>
											<td><?php $d=$row["booking_date"];
							$nd=substr($d,0,10);
							echo $nd; ?></td>																				
										<td>
										<a href="manage-booking.php?bid=<?php echo $row['b_id'];?>"> View</a>
										</td>

										</tr>
										<?php $cnt=$cnt+1; } ?>
										
									</tbody>
								</table>

						

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
</body>
</html>

