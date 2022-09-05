<?php

include 'includes/config.php';
include 'includes/session.php';
error_reporting(0);

if(isset($_POST['approve']))
	{

$status="1";
$sql =mysqli_query($conn,"UPDATE tbl_kyc SET status='".$status."' WHERE  k_id='".$_POST["apv"]."'")or die(mysqli_error($conn));

$data =mysqli_query($conn,"select tbl_user.email_id,tbl_kyc.u_id from tbl_kyc join tbl_user on tbl_user.user_id=tbl_kyc.u_id where tbl_kyc.k_id='".$_POST["apv"]."'")or die(mysqli_error($conn));
		while($row=mysqli_fetch_assoc($data))
		{			
			$subject='Regarding KYC Request';
			$body='Your KYC documents are Approved successfully, Now you can rent any car from us, Kindly go through App for more information';
			$headers = "From: ryzencarrental@gmail.com";
			$to_email=$row['email_id'];
			$status=1;

				if (mail($to_email, $subject, $body, $headers)) 
					{
						
							header("location:Approved-kyc.php");
						
					} 
					else 
					{
						echo "<script>alert('Something wenrt wrong, try again later');</script>";
					}
		}
	}
	
if(isset($_POST['disapprove']))
	{
$status="2";
$sql =mysqli_query($conn,"UPDATE tbl_kyc SET status='".$status."' WHERE  k_id='".$_POST["dis"]."'")or die(mysqli_error($conn));

$data =mysqli_query($conn,"select tbl_user.email_id,tbl_kyc.u_id from tbl_kyc join tbl_user on tbl_user.user_id=tbl_kyc.u_id where tbl_kyc.k_id='".$_POST["dis"]."'")or die(mysqli_error($conn));
		while($row=mysqli_fetch_assoc($data))
		{			
			$subject='Regarding KYC Request';
			$body='Your Request for KYC has been disapproved, Re-upload All the Documents Carefully As Requested , Kindly go through App for more information, we are sorry for your Inconvenience';
			$headers = "From: ryzencarrental@gmail.com";
			$to_email=$row['email_id'];
			$status=1;

				if (mail($to_email, $subject, $body, $headers)) 
					{						
							header("location:disapproved-kyc.php");
						
					} 
					else 
					{
						echo "<script>alert('Something wenrt wrong, try again later');</script>";
					}
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
	
	<title>Car Rental Portal | Manage KYC   </title>

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
	<link rel="stylesheet" href="css/custom.css">
	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/magnific-popup.min.css" />
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

						<h2 class="page-title">Manage KYC</h2>

						<!-- Zero Configuration Table -->
						<div class="panel panel-default">
							<div class="panel-heading" style="background-color:#001662; color:white;">User Details</div>
							<div class="panel-body">

								<table border="1"  class="display table table-striped table-bordered table-hover" cellspacing="0" width="100%"  >
				
									<tbody>

									<?php 
$kid=intval($_GET['kid']);
									$sql = mysqli_query($conn,"SELECT tbl_user.*,tbl_kyc.* from tbl_kyc join tbl_user on tbl_user.user_id=tbl_kyc.u_id where tbl_kyc.k_id='".$kid."'") or die(mysqli_error($conn));
$cnt=1;
		while($row=mysqli_fetch_assoc($sql))
		{
?>
		<tr>
											<th colspan="4" style="text-align:center;color:blue">User Details</th>
										</tr>
										<tr>
											<th>User Name</th>
											<td><?php echo $row['user_name'];?></td>
											<th>Full Name</th>
											<td><?php echo $row['full_name'];?></td>
										</tr>
										<tr>											
											<th>Email Id</th>
											<td><?php echo $row['email_id'];?></td>
											<th>Contact No</th>
											<td><?php echo $row['mobile_no'];?></td>
										</tr>
											<tr>											
											<th>Address</th>
											<td><?php echo $row['address'];?></td>
											<th>City</th>
											<td><?php echo $row['city'];?></td>
										</tr>
											<tr>											
											<th>Country</th>
											<td colspan="3"><?php echo $row['country'];?></td>
										</tr>
										<div>.</br>
										</div>
										
										</tbody>
										</table>
<div class="row">
<div class="col-md-12">
<div class="panel panel-default">
<div class="panel-heading" style="text-align:center;color:blue">KYC Details</div>
<div class="panel-body">

<div class="form-group">
<label class="col-sm-2 control-label">Name As Per Aadhaar-card<span style="color:red">*</span></label>
<div class="col-sm-4">
<input type="text" name="modelyear" class="form-control" value="<?php echo $row['full_name'];?>" readonly>
</div>
<label class="col-sm-2 control-label">Aadhaar-card Number<span style="color:red">*</span></label>
<div class="col-sm-4">
<input type="text" name="seatingcapacity" class="form-control" value="<?php echo $row['aadhaarcard_no'];?>" readonly>
</div>
</div>

<div>&nbsp;</br></div>
<div class="row form-group gallerys">
<div class="col-lg-6" align='center'>
<div><label class="control-label">Aadhaar-card Front Image<span style="color:red">*</span></label></div>
</div>
<div class="col-lg-6" align='center'>
<div><label class="control-label">Aadhaar-card Back Image <span style="color:red">*</span></label> </div>
</div>
<div class="col-lg-6" align='center'>
<a href="img/kycimages/<?php echo $row['aadhaarimg1'];?>" target="_blank">
<img src="img/kycimages/<?php echo $row['aadhaarimg1'];?>" width="300" height="200" style="border:solid 1px #000" class="img-fluid">
</div>
</a>
<div class="col-lg-6" align='center'>
<a href="img/kycimages/<?php echo $row['aadhaarimg2'];?>" target="_blank">
<img src="img/kycimages/<?php echo $row['aadhaarimg2'];?>" width="300" height="200" style="border:solid 1px #000" class="img-fluid">
</a>
</div>
</div>

<div>&nbsp;</br></div>
<hr style="width:100%;border-top: 1px dashed grey;">

<div class="form-group">
<label class="col-sm-12 col-lg-4 control-label">license Number<span style="color:red">*</span></label>
<div class="col-sm-12 col-lg-6">
<input type="text" name="seatingcapacity" class="form-control" value="<?php echo $row['license_no'];?>" readonly>
</div>
</div>

<div>&nbsp;</br></div>
<div class="row form-group gallerys">
<div class="col-lg-6" align='center'>
<div><label class="control-label">License Front Image<span style="color:red">*</span></label></div>
</div>
<div class="col-lg-6" align='center'>
<div><label class="control-label">License Back image<span style="color:red">*</span> </label> </div>
</div>
<div class="col-lg-6" align='center'>
<a href="img/kycimages/<?php echo $row['licenseimg1'];?>" target="_blank">
<img src="img/kycimages/<?php echo $row['licenseimg1'];?>" width="300" height="200" style="border:solid 1px #000" class="img-fluid">
</a>
</div>
<div class="col-lg-6" align='center'>
<a href="img/kycimages/<?php echo $row['licenseimg2'];?>" target="_blank">
<img src="img/kycimages/<?php echo $row['licenseimg2'];?>" width="300" height="200" style="border:solid 1px #000" class="img-fluid">
</a>
</div>
</div>

<div>&nbsp;</br></div>
<hr style="width:100%;border-top: 1px dashed grey;">

<div class="form-group">
<label class="col-sm-2 col-lg-6control-label">Pan-card No<span style="color:red">*</span></label>
<div class="col-sm-4 col-lg-6"><input type="text" name="seatingcapacity" class="form-control" value="<?php echo $row['pancard_no'];?>" readonly></div>
</div>

<div class="row form-group gallerys">
<div class="col-sm-12 col-lg-12" align='center'>
<div>&nbsp;</br></div>
<div><label class="control-label">Pan-card Image<span style="color:red">*</span></label></div>
</div>

<div class="col-sm-12 col-lg-12" align='center'>
<div>
<a href="img/kycimages/<?php echo $row['pancardimg1'];?>" target="_blank">
<img src="img/kycimages/<?php echo $row['pancardimg1'];?>" width="300" height="200" style="border:solid 1px #000" class="img-fluid"></div>
</a>
</div>
</div>
	

										
							</div>
							</div>
							<?php if($row['status']==0){ ?>
																						
	<div class="form-group">
		<div class="col-sm-12 col-lg-12" style="text-align:center">

 <button type="button" class="btn btn-md btn-primary" data-toggle="modal" data-target="#approvemodal" onclick='approvedata(<?php echo $row["k_id"]; ?>)'>Approve</button>
        <button type="button" class="btn btn-md btn-danger" data-toggle="modal" data-target="#disapprovemodal" onclick='disapprovedata(<?php echo $row["k_id"]; ?>)'>Disapprove</button>
             	</div>
		</div>
							<?php } ?>
							<?php $cnt=$cnt+1; } ?>	
							</div>
						</div>					
					</div>
				</div>

			</div>
		</div>
	</div>
	
	<div class="modal fade" id="approvemodal" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h2>Alert !</h2>					
				</div>
				<form method="post">
				<div class="modal-body">
				   Are you sure want To Approve This KYC Request ?
				   <input type="hidden" name="apv" id="apv">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" id="op" data-dismiss="modal">No</button>
					<button type="submit" name="approve" class="btn btn-primary">Yes</button>
				</div>
			</form>
			</div>
			</div>
		</div>
	
	
	   <div class="modal fade" id="disapprovemodal" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h2>Alert !</h2>					
				</div>
				<form method="post">
				<form method="post">
				<div class="modal-body">
				   Are you sure want To Disapprove This KYC Request ?
				   <input type="hidden" name="dis" id="dis">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" id="op" data-dismiss="modal">No</button>
					<button type="submit" name="disapprove" class="btn btn-primary">Yes</button>
				</div>
			</form>
			</div>
		</div>
	</div>

	<!-- Loading Scripts -->
	 <script type="text/javascript">
            function approvedata(val)
            {
                
                $("#apv").val(val);
            }
	</script>
	 <script type="text/javascript">
            function disapprovedata(val)
            {
                
                $("#dis").val(val);
            }

      </script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>

	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap-select.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.dataTables.min.js"></script>
	<script src="js/dataTables.bootstrap.min.js"></script>
	<script src="js/Chart.min.js"></script>
	<script src="js/fileinput.js"></script>
	<script src="js/chartData.js"></script>
	<script src="js/main.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/jquery.magnific-popup.min.js"></script>
	<script>
	$(document).ready(function(){
			$('.gallerys').magnificPopup({
				type:'image',
				delegate:'a'				
			});
	});
	</script>
</body>
</html>

