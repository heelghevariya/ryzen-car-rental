<?php
include 'includes/config.php';
include 'includes/session.php';
error_reporting(0);

	if(isset($_POST['submit']))
{
	
if($_POST['brandname']!='0' && $_POST['fueltype']!='0')
{
	$vehicletitle=$_POST['vehicletitle'];
	$brand=$_POST['brandname'];
	$vehicleno=$_POST['vehicleno'];
	$vehicledetails=$_POST['vehicledetails'];
	$priceperday=$_POST['priceperday'];
	$fueltype=$_POST['fueltype'];
	$modelyear=$_POST['modelyear'];
	$seatingcapacity=$_POST['seatingcapacity'];
	$voccdate=0;

		  $allowed_image_extension = array(
        "png",
        "jpg",
        "jpeg",
		"JPG",
		"JPEG",
		"PNG"
		);
		
		
	$exvimage1=$_FILES["img1"]["name"];
		$file_extension = pathinfo($_FILES["img1"]["name"], PATHINFO_EXTENSION);
		if (! in_array($file_extension, $allowed_image_extension)) 
		{
        $response1 = array(
            "type" => "error",
            "message" => "Upload valid images. Only PNG and JPEG are allowed."
        );
		}   
		else if (($_FILES["img1"]["size"] > 2000000)) 
		{
			$response1 = array(
				"type" => "error",
				"message" => "Image size exceeds 2MB"
				);
		}   
		   else {
			  $vimage1= $exvimage1;
				if (move_uploaded_file($_FILES["img1"]["tmp_name"],"img/vehicleimages/".$_FILES["img1"]["name"])) 
					{
						$response = array(
							"type" => "success",
							"message" => "Image uploaded successfully."
						);
					}
				}
				//vimg1
		
		
	$exvimage2=$_FILES["img2"]["name"];
		$file_extension = pathinfo($_FILES["img2"]["name"], PATHINFO_EXTENSION);
		if (! in_array($file_extension, $allowed_image_extension)) 
		{
			$response2 = array(
				"type" => "error",
				"message" => "Upload valid images. Only PNG and JPEG are allowed."
			);
		}   
		else if (($_FILES["img2"]["size"] > 2000000)) 
		{
			$response2 = array(
				"type" => "error",
				"message" => "Image size exceeds 2MB"
			);
		}   
		   else {
			   $vimage2= $exvimage2;
				if (move_uploaded_file($_FILES["img2"]["tmp_name"],"img/vehicleimages/".$_FILES["img2"]["name"])) 
					{
						$response = array(
							"type" => "success",
							"message" => "Image uploaded successfully."
						);
					}
				}
				//vimg2
		
		
	$exvimage3=$_FILES["img3"]["name"];
		$file_extension = pathinfo($_FILES["img3"]["name"], PATHINFO_EXTENSION);
		if (! in_array($file_extension, $allowed_image_extension)) 
		{
        $response3 = array(
            "type" => "error",
            "message" => "Upload valid images. Only PNG and JPEG are allowed."
        );
		}   
		else if (($_FILES["img3"]["size"] > 2000000)) 
		{
			$response3 = array(
				"type" => "error",
				"message" => "Image size exceeds 2MB"
			);
		}  
		   else {
			   $vimage3= $exvimage3;
				if (move_uploaded_file($_FILES["img3"]["tmp_name"],"img/vehicleimages/".$_FILES["img3"]["name"])) 
					{
						$response = array(
							"type" => "success",
							"message" => "Image uploaded successfully."
						);
					} 
					
				}
				//vimg3
		
		if($_FILES["img4"]["name"] != NULL)
		{	$exvimage4=$_FILES["img4"]["name"];
		$file_extension = pathinfo($_FILES["img4"]["name"], PATHINFO_EXTENSION);	
		if (! in_array($file_extension, $allowed_image_extension)) 
		{
        $response4 = array(
            "type" => "error",
            "message" => "Upload valid images. Only PNG and JPEG are allowed."
        );
		}   
		else if (($_FILES["img4"]["size"] > 2000000)) {
			$response4 = array(
				"type" => "error",
				"message" => "Image size exceeds 2MB"
			);
		}   
		   else {
			   $vimage4= $exvimage4;
				if (move_uploaded_file($_FILES["img4"]["tmp_name"],"img/vehicleimages/".$_FILES["img4"]["name"])) 
					{
						$response = array(
							"type" => "success",
							"message" => "Image uploaded successfully."
						);
					} 
				}
		}
				//vimg4
		
		if($_FILES["img5"]["name"] != NULL)
		{
			$exvimage5=$_FILES["img5"]["name"];
		$file_extension = pathinfo($_FILES["img5"]["name"], PATHINFO_EXTENSION);
		if (! in_array($file_extension, $allowed_image_extension)) 
		{
        $response5 = array(
            "type" => "error",
            "message" => "Upload Valid image. Only PNG and JPEG are allowed."
        );
		}    
		else if (($_FILES["img5"]["size"] > 2000000)) 
		{
			$response5 = array(
				"type" => "error",
				"message" => "Image size exceeds 2MB"
			);
		}   
		   else {
				$vimage5= $exvimage5;
					if (move_uploaded_file($_FILES["img5"]["tmp_name"],"img/vehicleimages/".$_FILES["img5"]["name"])) 
					{
						$response = array(
							"type" => "success",
							"message" => "Image uploaded successfully."
						);
					} 
				}
		}

	if($response1["type"]!="error" && $response2["type"]!="error" && $response3["type"]!="error" && $response4["type"]!="error" && $response5["error"]!="success")
			{
	$sql=mysqli_query($conn,"INSERT INTO tbl_vehicles(brand_id,vehicle_title,vehicle_no,vehicle_detail,priceperday,fuel_type,model_year,seating_capacity,vimage1,vimage2,vimage3,vimage4,vimage5,Airconditioner,Childdoorlock,Brakeassiste,Driverairbage,Passengerairbage,Powerwindow,Smartgps,LEDdisplay,Airfreshner,Auxcable,Dashcam,voccupied_date) VALUES('".$brand."','".$vehicletitle."','".$vehicleno."','".$vehicledetails."','".$priceperday."','".$fueltype."','".$modelyear."','".$seatingcapacity."','".$vimage1."','".$vimage2."','".$vimage3."','".$vimage4."','".$vimage5."','".$_POST["Airconditioner"]."','".$_POST["Childdoorlock"]."','".$_POST["Brakeassiste"]."','".$_POST["Driverairbage"]."','".$_POST["Passengerairbage"]."','".$_POST["Powerwindow"]."','".$_POST["Smartgps"]."','".$_POST["LEDdisplay"]."','".$_POST["Airfreshner"]."','".$_POST["Auxcable"]."','".$_POST["Dashcam"]."','".$voccdate."')") or die(mysqli_error($conn));

			$msg="Vehicle posted successfully";
			header("location:manage-vehicles.php");
		
			}
			else
			{
				$error="Something went wrong. Please try again";

			}
	}
	else
	{
		$error="Select Brand Name Or Fuel-Type";
	}
}
	
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
	
	<title>Car Rental Portal | Admin Post Vehicle</title>

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
.error{

  color:red;
  width:100%;
  margin-top:10px;
  font-size:13px;
  font-family:Corbel;
   
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
					
						<h2 class="page-title">Post A Vehicle</h2>

						<div class="row">
							<div class="col-md-12">
								<div class="panel panel-default">
									<div class="panel-heading" style="background-color:#001662; color:white;">Basic Info</div>
<?php if(isset($error)){?><div class="errorWrap"><strong>ERROR</strong>:<?php echo htmlentities($error); ?> </div><?php } 
				else if(isset($msg)){?><div class="succWrap"><strong>SUCCESS</strong>:<?php echo htmlentities($msg); ?> </div><?php }?>

									<div class="panel-body">
<form method="post" id="frm" name="frm" class="form-horizontal" enctype="multipart/form-data">
<div class="form-group">
<label class="col-sm-2 control-label">Vehicle Title<span style="color:red">*</span></label>
<div class="col-sm-4">
<input type="text" name="vehicletitle" id="vehicletitle" class="form-control" maxlength="20" autocomplete="off">
</div>
<label class="col-sm-2 control-label">Select Brand<span style="color:red">*</span></label>
<div class="col-sm-4">
<select class="selectpicker" name="brandname" class="form-control">
<option value="0" >Select</option>

	<?php 
	$result=mysqli_query($conn,"select brand_id,brand_name from tbl_brand") or die(mysqli_error($conn));

	while($row=mysqli_fetch_assoc($result))
				{
		?>		
	<option value="<?php echo $row['brand_id'];?>"><?php echo $row['brand_name'];?></option>
	<?php } ?>
	
	
</select>
</div>
</div>

<div class="form-group">
<label class="col-sm-2 control-label">Vehicle number<span style="color:red">*</span></label>
<div class="col-sm-4">
<input type="text" name="vehicleno" id="vehicleno" class="form-control" maxlength="10" autocomplete="off">
<label id="aa"></label>
</div>
</div>
											
<div class="hr-dashed"></div>
<div class="form-group">
<label class="col-sm-2 control-label">Vehicle details<span style="color:red">*</span></label>
<div class="col-sm-10">
<textarea class="form-control" name="vehicledetails" rows="3" maxlength="350"></textarea>
</div>
</div>

<div class="form-group">
<label class="col-sm-2 control-label">Price Per Day(in INR)<span style="color:red">*</span></label>
<div class="col-sm-4">
<input type="text" name="priceperday" class="form-control" maxlength="7" autocomplete="off">
</div>
<label class="col-sm-2 control-label">Select Fuel Type<span style="color:red">*</span></label>
<div class="col-sm-4">
<select class="selectpicker" name="fueltype" class="form-control" Required>
<option value="0">Select</option>

<option value="Petrol">Petrol</option>
<option value="Diesel">Diesel</option>
<option value="CNG">CNG</option>
</select>
</div>
</div>


<div class="form-group">
<label class="col-sm-2 control-label">Model Year<span style="color:red">*</span></label>
<div class="col-sm-4">
<input type="text" name="modelyear" class="form-control" maxlength="4"  autocomplete="off">
</div>
<label class="col-sm-2 control-label">Seating Capacity<span style="color:red">*</span></label>
<div class="col-sm-4">
<input type="text" name="seatingcapacity" class="form-control" autocomplete="off">
</div>
</div>
<div class="hr-dashed"></div>


<div class="form-group">
<div class="col-sm-12">
<h4><b>Upload Images</b></h4>
<div>
<?php if($response1["type"]=="error") { ?><div class="errorWrap">ERROR</strong>:<?php echo $response1["message"]; ?> </div>   <?php }?>	
<?php if($response2["type"]=="error") { ?><div class="errorWrap">ERROR</strong>:<?php echo $response2["message"]; ?> </div>   <?php }?>	
<?php if($response3["type"]=="error") { ?><div class="errorWrap">ERROR</strong>:<?php echo $response3["message"]; ?> </div>   <?php }?>	
<?php if($response4["type"]=="error") { ?><div class="errorWrap">ERROR</strong>:<?php echo $response4["message"]; ?> </div>   <?php }?>
<?php if($response5["type"]=="error") { ?><div class="errorWrap">ERROR</strong>:<?php echo $response5["message"]; ?> </div>   <?php }?>	
</div>
<div class="form-group">
	
<div class="col-sm-4">
Image 1 <span style="color:red">*</span><input type="file" accept=".jpeg,.png,.jpg" name="img1" required>	
</div>
<div class="col-sm-4">
Image 2<span style="color:red">*</span><input type="file" accept=".jpeg,.png,.jpg" name="img2" required>
</div>
<div class="col-sm-4">
Image 3<span style="color:red">*</span><input type="file" accept=".jpeg,.png,.jpg" name="img3" required>
</div>
</div>

<div class="form-group">	
<div class="col-sm-4">
Image 4<input type="file" accept=".jpeg,.png,.jpg" name="img4">
</div>
<div class="col-sm-4">
Image 5<input type="file" accept=".jpeg,.png,.jpg" name="img5">
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
<div class="checkbox checkbox-inline">
<input type="checkbox" id="Airconditioner" name="Airconditioner" value="1">
<label for="Airconditioner"> Air Conditioner </label>
</div>
</div>
<div class="col-sm-3">
<div class="checkbox checkbox-inline">
<input type="checkbox" id="Childdoorlock" name="Childdoorlock" value="1">
<label for="Childdoorlock"> Child door lock </label>
</div></div>
<div class="col-sm-3">
<div class="checkbox checkbox-inline">
<input type="checkbox" id="Brakeassiste" name="Brakeassiste" value="1">
<label for="Brakeassiste"> Brakeassiste </label>
</div></div>
<div class="checkbox checkbox-inline">
<input type="checkbox" id="Driverairbage" name="Driverairbage" value="1">
<label for="Driverairbage"> Driver airbage </label>
</div>
</div>



<div class="form-group">
<div class="col-sm-3">
<div class="checkbox checkbox-inline">
<input type="checkbox" id="Passengerairbage" name="Passengerairbage" value="1">
<label for="Passengerairbage"> Passenger airbage </label>
</div>
</div>
<div class="col-sm-3">
<div class="checkbox checkbox-inline">
<input type="checkbox" id="Powerwindow" name="Powerwindow" value="1">
<label for="Powerwindow">Powerwindow</label>
</div>
</div>
<div class="col-sm-3">
<div class="checkbox checkbox-inline">
<input type="checkbox" id="Smartgps" name="Smartgps" value="1">
<label for="Smartgps"> SmartGPS </label>
</div></div>
<div class="checkbox checkbox-inline">
<input type="checkbox" id="LEDdisplay" name="LEDdisplay" value="1">
<label for="LEDdisplay"> LED display </label>
</div>
</div>


<div class="form-group">
<div class="col-sm-3">
<div class="checkbox checkbox-inline">
<input type="checkbox" id="Airfreshner" name="Airfreshner" value="1">
<label for="Airfreshner"> Airfreshner </label>
</div>
</div>
<div class="col-sm-3">
<div class="checkbox h checkbox-inline">
<input type="checkbox" id="Auxcable" name="Auxcable" value="1">
<label for="Auxcable">Auxcable</label>
</div></div>
<div class="col-sm-3">
<div class="checkbox checkbox-inline">
<input type="checkbox" id="Dashcam" name="Dashcam" value="1">
<label for="Dashcam"> Dashcam </label>
</div>
</div>
</div>





											<div class="form-group">
											<div>.<br><br></div>
												<div class="col-sm-8 col-sm-offset-2">
													
													<button class="btn btn-primary" name="submit" id="submit" type="submit">Submit</button>
													<button class="btn btn-default" type="reset">Cancel</button>													
												</div>
											</div>
											
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
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/jquery.validate.min.js"></script>
	
	<!--<script src="js/jquery.validate.min.js"></script> -->
<script type="text/javascript">
$(function()	
{	
	$('#vehicleno').mouseleave(function(){
		var vehicleno = $(this).val();
		$.ajax({
			url:'check.php',
			method:"POST",
			data:{vehiclen:vehicleno},
			success:function(data)
			{
				if(data != '0')
				{
					$('#aa').html('<label class="error">Vehicle Number Already Exists</label>');
					$('#submit').attr("disabled", true);
				}	
				else
				{
					$('#aa').html('<span class="text-success"></span>');
					$('#submit').attr("disabled", false);
				}
			}
		})
		
	});
	

});
</script>

<script type="text/javascript">
	$.validator.addMethod("gj", function(value, element) {
		return this.optional(element) || value == value.match(/^[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{4}$/);
	},'Enter Valid Number');

	$('#frm').validate({
		rules:{	
			vehicletitle:"required",
			vehicleno:{
				required:true,
				gj:true
			},	
			vehicledetails:{
				required:true,
				minlength:20
				
			},	
			priceperday:{
				required:true,
				digits:true,
				min:100
			},
			modelyear:{
				required:true,
				digits:true,
				range: [1950,2022]				
			},
			seatingcapacity:{
				required:true,
				digits:true,
				range: [2,7]				
			},
			
		},messages:{
			vehicletitle:"Please Enter Vehicle Title",
			vehicleno:{
				required:"Please Enter Vehicle Number.",
				gj:"Enter Vehicle Number In Valid Format (Ex:GJ05MHXXXX)"
			},	
			vehicledetails:{
				required:"Please Enter Vehicle Details.",
				minlength:"Atleast 20 Character Required"
			},		
			priceperday:{
				required:"Please Enter Price-Per-Day.",
				min:"Price-Per-Day Must Be Atleast 100",
				digits:"Please Enter Digits Only"
			},
			modelyear:{
				required:"Please Enter Model-Year Of  Vehicle.",
				range:"Please Enter Model-Year In Between 1950 To 2022.",
				digits:"Please Enter Digits Only."
			},
			seatingcapacity:{
				required:"Please Enter Seating Capacity OF Vehicle.",
				range:"Please Enter Seating Capacity Between 2 To 7.",
				digits:"Please Enter Digits Only."
			},
			
		},
		submitHandler:function(form){
			form.submit();
		}	
	});
</script>
	
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
