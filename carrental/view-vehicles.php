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
	
	<title>Car Rental Portal | Admin View Vehicle Info</title>

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
					
						<h2 class="page-title">View Vehicle</h2>

						<div class="row">
							<div class="col-md-12">
								<div class="panel panel-default">
									<div class="panel-heading" style="background-color:#001662; color:white;">Basic Info</div>
									<div class="panel-body">
<?php if(isset($msg)){?><div class="succWrap"><strong>SUCCESS</strong>:<?php echo htmlentities($msg); ?> </div><?php } ?>
<?php 
$id=intval($_GET['id']);
$result =mysqli_query($conn,"SELECT tbl_vehicles.*,tbl_brand.brand_name,tbl_brand.brand_id as bid from tbl_vehicles join tbl_brand on tbl_brand.brand_id=tbl_vehicles.brand_id where tbl_vehicles.vehicle_id=$id") or die(mysqli_error($conn));
$cnt=1;
while($row=mysqli_fetch_assoc($result))
{
?>

<form method="post" class="form-horizontal" enctype="multipart/form-data">
<div class="form-group">
<label class="col-sm-2 control-label">Vehicle Title<span style="color:red">*</span></label>
<div class="col-sm-4">
<input type="text" name="vehicletitle" class="form-control" value="<?php echo $row["vehicle_title"];?>" readonly>
</div>
<label class="col-sm-2 control-label">Select Brand<span style="color:red">*</span></label>
<div class="col-sm-4">

<input type="text " class="form-control" value="<?php echo $row["brand_name"];?>" readonly>

</div>
</div>

<div class="form-group">
<label class="col-sm-2 control-label">Vehicle number<span style="color:red">*</span></label>
<div class="col-sm-4">
<input type="text" name="vehicleno" class="form-control"  value="<?php echo $row["vehicle_no"];?>" readonly>
</div>
</div>
											
<div class="hr-dashed"></div>
<div class="form-group">
<label class="col-sm-2 control-label">Vehicle Details<span style="color:red">*</span></label>
<div class="col-sm-10">
<textarea class="form-control" name="vehicledetail" rows="3" readonly><?php echo $row["vehicle_detail"];?></textarea>
</div>
</div>

<div class="form-group">
<label class="col-sm-2 control-label">Price Per Day(in INR)<span style="color:red">*</span></label>
<div class="col-sm-4">
<input type="text" name="priceperday" class="form-control" value="<?php echo $row["priceperday"];?>" readonly>
</div>
<label class="col-sm-2 control-label">Select Fuel Type<span style="color:red">*</span></label>
<div class="col-sm-4">

<input type="text"  class="form-control" value="<?php echo $row["fuel_type"];?>" readonly> 


</div>
</div>


<div class="form-group">
<label class="col-sm-2 control-label">Model Year<span style="color:red">*</span></label>
<div class="col-sm-4">
<input type="text" name="modelyear" class="form-control" value="<?php echo $row["model_year"];?>" readonly>
</div>
<label class="col-sm-2 control-label">Seating Capacity<span style="color:red">*</span></label>
<div class="col-sm-4">
<input type="text" name="seatingcapacity" class="form-control" value="<?php echo $row["seating_capacity"];?>" readonly>
</div>
</div>
<div class="hr-dashed"></div>								
<div class="form-group">
<div class="col-sm-12">
<h4><b>Vehicle Images</b></h4>
</div>
</div>


<div class="form-group">
<div class="col-sm-4">
Image 1 <img src="img/vehicleimages/<?php echo $row["vimage1"];?>" width="300" height="200" style="border:solid 1px #000">
</div>
<div class="col-sm-4">
Image 2<img src="img/vehicleimages/<?php echo $row["vimage2"];?>" width="300" height="200" style="border:solid 1px #000">
</div>
<div class="col-sm-4">
Image 3<img src="img/vehicleimages/<?php echo $row["vimage3"];?>" width="300" height="200" style="border:solid 1px #000">
</div>
</div>


<div class="form-group">
<div class="col-sm-4">
Image 4
<?php 
if($row["vimage4"]=="")
{
	echo "image unavailable";
} 
	else {?>
	<img src="img/vehicleimages/<?php echo $row["vimage4"];?>" width="300" height="200" style="border:solid 1px #000">
	
	<?php } ?>
</div>

<div class="col-sm-4">
Image 5
<?php if($row["vimage5"]=="")
{
echo "image unavailable";
} 
	else {?>
	<img src="img/vehicleimages/<?php echo $row["vimage5"];?>" width="300" height="200" style="border:solid 1px #000">

	<?php } ?>
</div>

</div>
<div class="hr-dashed"></div>									
</div>
</div>
</div>
</div>
	
							

<div class="row">
<div class="col-md-12">
<div class="panel panel-default">
<div class="panel-heading" style="background-color:#001662; color:white;">Accessories</div>
<div class="panel-body">


<div class="form-group">
<div class="col-sm-3">
<?php if($row["Airconditioner"]==1)
{?>
<div class="checkbox checkbox-inline">
<input type="checkbox" id="inlineCheckbox1" name="Airconditioner" checked value="1" disabled>
<label for="inlineCheckbox1"> Air Conditioner </label>
</div>
<?php } else { ?>
<div class="checkbox checkbox-inline">
<input type="checkbox" id="inlineCheckbox1" name="Airconditioner" value="1" disabled>
<label for="inlineCheckbox1"> Air Conditioner </label>
</div>
<?php } ?>
</div>
<div class="col-sm-3">
<?php if($row["Childdoorlock"]==1)
{?>
<div class="checkbox checkbox-inline">
<input type="checkbox" id="inlineCheckbox1" name="Childdoorlock" checked value="1" disabled>
<label for="inlineCheckbox2"> Childdoor lock </label>
</div>
<?php } else {?>
<div class="checkbox checkbox-success checkbox-inline">
<input type="checkbox" id="inlineCheckbox1" name="Childdoorlock" value="1" disabled>
<label for="inlineCheckbox2"> Childdoor lock </label>
</div>
<?php }?>
</div>
<div class="col-sm-3">
<?php if($row["Brakeassiste"]==1)
{?>
<div class="checkbox checkbox-inline">
<input type="checkbox" id="inlineCheckbox1" name="Brakeassiste" checked value="1" disabled>
<label for="inlineCheckbox3"> Brakeassiste </label>
</div>
<?php } else {?>
<div class="checkbox checkbox-inline">
<input type="checkbox" id="inlineCheckbox1" name="Brakeassiste" value="1" disabled>
<label for="inlineCheckbox3"> Brakeassiste </label>
</div>
<?php } ?>
</div>
<div class="col-sm-3">
<?php if($row["Driverairbage"]==1)
{
	?>
<div class="checkbox checkbox-inline">
<input type="checkbox" id="inlineCheckbox1" name="Driverairbage" checked value="1" disabled>
<label for="inlineCheckbox3"> Driver airbage </label>
</div>
<?php } else {?>
<div class="checkbox checkbox-inline">
<input type="checkbox" id="inlineCheckbox1" name="Driverairbage" value="1" disabled>
<label  for="inlineCheckbox3"> Driver airbage </label>
</div>
<?php } ?>
</div>
</div>
<div><br></div>

<div class="form-group">
<div class="col-sm-3">
<?php if($row["Passengerairbage"]==1)
{
	?>
<div class="checkbox checkbox-inline">
<input type="checkbox" id="inlineCheckbox1" name="Passengerairbage" checked value="1" disabled>
<label for="inlineCheckbox1"> Passenger airbage </label>
</div>
<?php } else {?>
<div class="checkbox checkbox-inline">
<input type="checkbox" id="inlineCheckbox1" name="Passengerairbage" value="1" disabled>
<label for="inlineCheckbox1"> Passenger airbage </label>
</div>
<?php } ?>
</div>


<div class="col-sm-3">
<?php if($row["Powerwindow"]==1)
{
?>
<div class="checkbox checkbox-inline">
<input type="checkbox" id="inlineCheckbox1" name="Powerwindow" checked value="1" disabled>
<label for="inlineCheckbox2">Power window</label>
</div>
<?php } else { ?>
<div class="checkbox checkbox-inline">
<input type="checkbox" id="inlineCheckbox1" name="Powerwindow" value="1" disabled>
<label for="inlineCheckbox2">Power window</label>
</div>
<?php } ?>
</div>

<div class="col-sm-3">
<?php if($row["Smartgps"]==1)
{
?>
<div class="checkbox checkbox-inline">
<input type="checkbox" id="inlineCheckbox1" name="Smartgps" checked value="1" disabled>
<label for="inlineCheckbox3"> SmartGPS </label>
</div>
<?php } else { ?>
<div class="checkbox checkbox-inline">
<input type="checkbox" id="inlineCheckbox1" name="Smartgps" value="1" disabled>
<label for="inlineCheckbox3"> SmartGPS </label>
</div>
<?php } ?>
</div>

<div class="col-sm-3">
<?php if($row["LEDdisplay"]==1)
{
?>
<div class="checkbox checkbox-inline">
<input type="checkbox" id="inlineCheckbox1" name="LEDdisplay" checked value="1" disabled>
<label for="inlineCheckbox3">LED display </label>
</div>
<?php } else { ?>
<div class="checkbox checkbox-inline">
<input type="checkbox" id="inlineCheckbox1" name="LEDdisplay" value="1" disabled>
<label for="inlineCheckbox3"> LED display </label>
</div>
<?php } ?>
</div>
</div>
<div><br></div>



<div class="form-group">
<div class="col-sm-3">
<?php if($row["Airfreshner"]==1)
{
?>
<div class="checkbox checkbox-inline">
<input type="checkbox" id="inlineCheckbox1" name="Airfreshner" checked value="1" disabled>
<label for="inlineCheckbox1"> Airfreshner </label>
</div>
<?php } else {?>
<div class="checkbox checkbox-inline">
<input type="checkbox" id="inlineCheckbox1" name="Airfreshner" value="1" disabled>
<label for="inlineCheckbox1"> Airfreshner </label>
</div>
<?php } ?>
</div>

<div class="col-sm-3">
<?php if($row["Auxcable"]==1)
{
?>
<div class="checkbox  checkbox-inline">
<input type="checkbox" id="inlineCheckbox1" name="Auxcable" checked value="1" disabled>
<label for="inlineCheckbox2">Auxcable</label>
</div>
<?php } else { ?>
<div class="checkbox checkbox-success checkbox-inline">
<input type="checkbox" id="inlineCheckbox1" name="Auxcable" value="1" disabled>
<label for="inlineCheckbox2">Auxcable</label>
</div>
<?php } ?>
</div>


<div class="col-sm-3">
<?php if($row["Dashcam"]==1)
{
?>
<div class="checkbox checkbox-inline">
<input type="checkbox" id="inlineCheckbox1" name="Dashcam" checked value="1" disabled>
<label for="inlineCheckbox3"> Dashcam </label>
</div>
<?php } else {?>
<div class="checkbox checkbox-inline">
<input type="checkbox" id="inlineCheckbox1" name="Dashcam" value="1" disabled>
<label for="inlineCheckbox3"> Dashcam </label>
</div>
<?php } ?>
</div>
</div>



<?php } ?>


										

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
