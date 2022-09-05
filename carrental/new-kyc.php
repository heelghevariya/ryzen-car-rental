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
	
	<title>Car Rental Portal | Manage Users KYC   </title>

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

						<h2 class="page-title">New KYC Request</h2>

						<!-- Zero Configuration Table -->
						<div class="panel panel-default">
							<div class="panel-heading" style="background-color:#001662; color:white;">User's KYC information</div>
							<div class="panel-body">

								<table id="zctb" class="display table table-striped table-bordered table-hover" cellspacing="0" width="100%">
									<thead>
										<tr>
										<th>#</th>
											<th>Name</th>
											<th>Email</th>
											<th>Contact No</th>										
											<th>Status</th>
											<th>KYC Request-Date</th>
											<th>Action</th>
										</tr>
									</thead>
									<tfoot>
										<tr>
											<th>#</th>										
											<th>Name</th>
											<th>Email</th>
											<th>Contact No</th>										
											<th>Status</th>
											<th>KYC Request-Date</th>
											<th>Action</th>
										</tr>
									</tfoot>
									<tbody>

									<?php 
$status=0;
									$sql = mysqli_query($conn,"SELECT tbl_user.user_name,tbl_user.email_id,tbl_user.mobile_no,tbl_kyc.status,tbl_kyc.kycrequest_date,tbl_kyc.k_id from tbl_kyc join tbl_user on tbl_user.user_id=tbl_kyc.u_id where tbl_kyc.Status='".$status."'") or die(mysqli_error($conn));
$cnt=1;
		while($row=mysqli_fetch_assoc($sql))
		{
?>
										<tr>
											<td><?php echo htmlentities($cnt);?></td>
											<td><?php echo $row['user_name'];?></td>
											<td><?php echo $row['email_id'];?></td>
											<td><?php echo $row['mobile_no'];?></td>
											<td>
											<?php 
												if($row['status']==0)
												{
												?><span class="label label-warning">Pending</span>
												<?php
												} else if ($row['status']==1) {
												?><span class="label label-success">Approved</span>
												<?php
												}
												 else{
												?><span type="lable" class="btn btn-danger">Danger</span>
												<?php
												 }
										?>
											</td>
											<td>
											<?php $d=$row['kycrequest_date'];
							$nd=substr($d,0,10);
							echo $nd; ?></td>																				
										<td>
										<a href="manage-kyc.php?kid=<?php echo $row['k_id'];?>"> View</a>
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

