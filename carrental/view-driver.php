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
	
	<title>Car Rental Portal | Admin View driver</title>

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
					
						<h2 class="page-title">Driver's Detail</h2>

						<div class="row">
							<div class="col-md-12">
								<div class="panel panel-default">
									<div class="panel-heading" style="background-color:#001662; color:white;">driver's Information</div>
<?php if(isset($error)){?><div class="errorWrap"><strong>ERROR</strong>:<?php echo htmlentities($error); ?> </div><?php } 
				else if(isset($msg)){?><div class="succWrap"><strong>SUCCESS</strong>:<?php echo htmlentities($msg); ?> </div><?php }?>

									<div class="panel-body">
<?php
$id=intval($_GET['id']);
$result =mysqli_query($conn,"SELECT * from tbl_driver where tbl_driver.d_id=$id") or die(mysqli_error($conn));
$cnt=1;
while($row=mysqli_fetch_assoc($result))
{
?>

<form method="post" class="form-horizontal" enctype="multipart/form-data">
<div class="form-group">
<label class="col-sm-2 control-label">Driver's Fullname<span style="color:red">*</span></label>
<div class="col-sm-4">
<input type="text" name="fullname" class="form-control" value="<?php echo $row["fullname"];?>" readonly>
</div>
<label class="col-sm-2 control-label">Mobile No<span style="color:red">*</span></label>
<div class="col-sm-4">
<input type="text" name="mobile_no" class="form-control" value="<?php echo $row["mobile_no"];?>" readonly>
</div>
</div>

<div class="form-group">
<label class="col-sm-2 control-label">Email-ID<span style="color:red">*</span></label>
<div class="col-sm-4">
<input type="text" name="emailid" class="form-control" value="<?php echo $row["emailid"];?>" readonly>
</div>
</div>
											
<div class="hr-dashed"></div>

<div class="form-group">
<div class="col-sm-12">
<h4><b>Upload Driver's Image</b></h4>
<div class="form-group">

<label class="col-sm-2 col-lg-2 control-label">Image<span style="color:red">*</span></label>
<?php 
if($row["photo"]=="")
{
	echo "image unavailable";
} 
	else {?>
	<div class="col-sm-2 col-lg-2 control-label"><img src="img/driverimg/<?php echo $row["photo"];?>" width="137" height="160" style="border:solid 1px #000" readonly></div>
	<?php } ?>

</div>
</div>
</div>

<div class="hr-dashed"></div>

<div class="form-group">
<label class="col-sm-2 control-label">Aadhaar-card Number<span style="color:red">*</span></label>
<div class="col-sm-4">
<input type="text" name="aadhaarcard_no" class="form-control" value="<?php echo $row["aadhaarcard_no"];?>" readonly>
</div>
<label class="col-sm-2 control-label">License Number<span style="color:red">*</span></label>
<div class="col-sm-4">
<input type="text" name="license_no" class="form-control" value="<?php echo $row["license_no"];?>" readonly>
</div>
</div>


<div class="form-group">
<label class="col-sm-2 control-label">Fees Per Day<span style="color:red">*</span></label>
<div class="col-sm-4">
<input type="text" name="driver_fees" class="form-control" value="<?php echo $row["driver_fees"];?>" readonly>
</div>
<label class="col-sm-2 control-label">Experience<span style="color:red">*</span></label>
<div class="col-sm-4">
<input type="text" name="experience" class="form-control" value="<?php echo $row["experience"];?>" readonly>
</div>
</div>
<?php }?>
<div class="hr-dashed"></div>


											
										</form>

									</div>
								</div>
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
