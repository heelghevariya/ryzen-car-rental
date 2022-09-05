<?php
include 'includes/config.php';
include 'includes/session.php';
error_reporting(0);

if(isset($_POST['update']))
{
		  $allowed_image_extension = array(
        "png",
        "jpg",
        "jpeg",
		"JPG",
		"JPEG",
		"PNG"
		);
	$id=intval($_GET['imgid']);
	$exvimage4=$_FILES["img4"]["name"];
		$file_extension = pathinfo($_FILES["img4"]["name"], PATHINFO_EXTENSION);
		if (! in_array($file_extension, $allowed_image_extension)) 
		{
        $response = array(
            "type" => "error",
            "message" => "Upload valid images. Only PNG and JPEG are allowed."
        );
		}   
		else if (($_FILES["img4"]["size"] > 2000000)) 
		{
			$response = array(
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
					} else 
						{
						$response = array(
							"type" => "error",
							"message" => "Problem in uploading image files."
						);
						}
				}
				
if($response["type"]=="success")
{
	$result=mysqli_query($conn,"update tbl_vehicles set vimage4='".$vimage4."' where vehicle_id=$id") or die(mysqli_error($conn));

			$msg="Image Updated Successfully";
			}
			else
			{
				$error="Something went wrong. Please try again";

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
	
	<title>Car Rental Portal | Admin Update Image 4</title>

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
					
						<h2 class="page-title">Vehicle Image 4 </h2>

						<div class="row">
							<div class="col-md-10 col-lg-12">
								<div class="panel panel-default">
									<div class="panel-heading" style="background-color:#001662; color:white;">Vehicle Image 1 Details</div>
									<div class="panel-body">
										<form method="post" class="form-horizontal" enctype="multipart/form-data">
										
											
  	        	  <?php if(isset($error)){?><div class="errorWrap"><strong>ERROR</strong>:<?php echo htmlentities($error); ?> </div><?php } 
				else if(isset($msg)){?><div class="succWrap"><strong>SUCCESS</strong>:<?php echo htmlentities($msg); ?> </div><?php }?>



<div class="form-group">
												<label class="col-sm-4 control-label">Current Image4</label>
<?php 
$id=intval($_GET['imgid']);
$sql=mysqli_query($conn,"SELECT vimage4 from tbl_vehicles where tbl_vehicles.vehicle_id=$id") or die(mysqli_error($conn));
$cnt=1;
while($row=mysqli_fetch_assoc($sql))
{
?>

<div class="col-sm-8">
<img src="img/vehicleimages/<?php echo $row["vimage4"];?>" width="300" height="200" style="border:solid 1px #000">
</div>
<?php }?>
</div>

											<div class="form-group">
												<label class="col-sm-4 control-label">Upload New Image 4<span style="color:red">*</span></label>
												<div class="col-sm-8">
											<input type="file" name="img4" required>
												<?php if($response["type"]=="error") { ?><div class="errorWrap">ERROR</strong>:<?php echo $response["message"]; ?> </div>   <?php }?>
												</div>
											</div>
											<div class="hr-dashed"></div>
											
										
								
											
											<div class="form-group">
												<div class="col-sm-8 col-sm-offset-4">
								
													<button class="btn btn-primary" name="update" type="submit">Update</button>
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