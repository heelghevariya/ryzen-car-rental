<?php
include 'includes/config.php';
include 'includes/session.php';
error_reporting(0);

	if(isset($_POST['submit']))
	  {
	$fullname=$_POST['fullname'];
	$mobile_no=$_POST['mobile_no'];
	$emailid=$_POST['emailid'];
	$aadhaarcard_no=$_POST['aadhaarcard_no'];
	$license_no=$_POST['license_no'];
	$driver_fees=$_POST['driver_fees'];
	$experience=$_POST['experience'];	
	$id=intval($_GET['id']);

				
	$sql=mysqli_query($conn,"update tbl_driver set fullname='".$fullname."',emailid='".$emailid."',mobile_no='".$mobile_no."',aadhaarcard_no='".$aadhaarcard_no."',license_no='".$license_no."',experience='".$experience."',driver_fees='".$driver_fees."' where tbl_driver.d_id='".$id."'") or die(mysqli_error($conn));
	if ($sql==true)
			{
			$msg="Driver Updated Successfully";
				header("location:manage-driver.php");
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
	
	<title>Car Rental Portal | Admin Add driver</title>

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
					
						<h2 class="page-title">Edit Driver</h2>

						<div class="row">
							<div class="col-md-12">
								<div class="panel panel-default">
									<div class="panel-heading" style="background-color:#001662; color:white;">Driver's Information</div>
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

<form method="post" class="form-horizontal" id="frm" enctype="multipart/form-data">
<div class="form-group">
<label class="col-sm-2 control-label">Driver's Fullname<span style="color:red">*</span></label>
<div class="col-sm-4">
<input type="text" name="fullname" id="fullname" maxlength="60" class="form-control" value="<?php echo $row["fullname"];?>" autocomplete="off">
</div>
<label class="col-sm-2 control-label">Mobile No<span style="color:red">*</span></label>
<div class="col-sm-4">
<input type="text" name="mobile_no" id="mobile_no" maxlength="10" class="form-control" value="<?php echo $row["mobile_no"];?>" autocomplete="off">
<label id="mno"></label>
</div>
</div>

<div class="form-group">
<label class="col-sm-2 control-label">Email-ID<span style="color:red">*</span></label>
<div class="col-sm-4">
<input type="text" name="emailid" id="emailid" maxlength="40" class="form-control" value="<?php echo $row["emailid"];?>" autocomplete="off">
<label id="mail"></label>
</div>
</div>
											
<div class="hr-dashed"></div>

<div class="form-group">
<div class="col-sm-12">
<h4><b>Upload Driver's Image</b></h4>
</div>
		<?php if($response["type"]=="error") { ?><div class="errorWrap">ERROR</strong>:<?php echo $response["message"]; ?> </div>   <?php }?>
										
</div>

<div class="form-group">
<div class="col-sm-4">
<div>Image</div>
<?php 
if($row["photo"]=="")
{
	echo "image unavailable";
} 
	else {?>
	<img src="img/driverimg/<?php echo $row["photo"];?>" width="137" height="160" style="border:solid 1px #000">
	<div><a href="changedriverimg.php?imgid=<?php echo $row["d_id"];?>">Change Driver Image</a></div>
	<?php } ?>
</div>
</div>

<div class="hr-dashed"></div>

<div class="form-group">
<label class="col-sm-2 control-label">Aadhaar-card Number<span style="color:red">*</span></label>
<div class="col-sm-4">
<input type="text" name="aadhaarcard_no" id="aadhaarcard_no" maxlength="12" class="form-control" value="<?php echo $row["aadhaarcard_no"];?>" autocomplete="off">
<label id="a"></label>
</div>
<label class="col-sm-2 control-label">License Number<span style="color:red">*</span></label>
<div class="col-sm-4">
<input type="text" name="license_no" id="license_no" maxlength="15" class="form-control" value="<?php echo $row["license_no"];?>" autocomplete="off">
<label id="l"></label>
</div>
</div>


<div class="form-group">
<label class="col-sm-2 control-label">Fees Per Day<span style="color:red">*</span></label>
<div class="col-sm-4">
<input type="text" name="driver_fees" maxlength="7" class="form-control" value="<?php echo $row["driver_fees"];?>" autocomplete="off">
</div>
<label class="col-sm-2 control-label">Experience<span style="color:red">*</span></label>
<div class="col-sm-4">
<input type="text" name="experience" maxlength="2" class="form-control" value="<?php echo $row["experience"];?>" autocomplete="off">
</div>
</div>
<?php }?>
<div class="hr-dashed"></div>

											<div class="form-group">											
												<div class="col-sm-8 col-lg-12 col-sm-offset-2">													
													<button class="btn btn-primary" name="submit" id="submit" type="submit">Submit</button>																									
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
<script type="text/javascript">
$(function()	
{	
	$('#mobile_no').keyup(function(){
		var mno = $(this).val();
		$.ajax({
			url:'check.php',
			method:"POST",
			data:{mno:mno},
			success:function(data)
			{
				if(data != '0')
				{
					$('#mno').html('<label class="error">Mobile Number Already Exists</label>');
					
				}	
				else
				{
					$('#mno').html('<span class="text-success"></span>');
					
				}
			}
		})
		
	});
	
	$('#emailid').keyup(function(){
		var em = $(this).val();
		$.ajax({
			url:'check.php',
			method:"POST",
			data:{mail:em},
			success:function(data)
			{
				if(data != '0')
				{
					$('#mail').html('<label class="error">Email-ID Already Exists.</label>');
					
				}	
				else
				{
					$('#mail').html('<span class="text-success"></span>');
					
				}
			}
		})
		
	});
	
	$('#aadhaarcard_no').keyup(function(){
		var aa = $(this).val();
		$.ajax({
			url:'check.php',
			method:"POST",
			data:{acard:aa},
			success:function(data)
			{
				if(data != '0')
				{
					$('#a').html('<label class="error">Aadhaar-card Number Already Exists.</label>');
					
				}	
				else
				{
					$('#a').html('<span class="text-success"></span>');
					
				}
			}
		})
		
	});
	
	$('#license_no').keyup(function(){
		var lno = $(this).val();
		$.ajax({
			url:'check.php',
			method:"POST",
			data:{lcard:lno},
			success:function(data)
			{
				if(data != '0')
				{
					$('#l').html('<label class="error">License Number Already Exists.</label>');
					
				}	
				else
				{
					$('#l').html('<span class="text-success"></span>');
					
				}
			}
		})
		
	});
	

});
</script>
		
<script type="text/javascript">
	$.validator.addMethod("alpha", function(value, element) {
		return this.optional(element) || value == value.match(/^[a-zA-Z ]*$/);
	},'Enter Valid Number');
		
	$.validator.addMethod("mno", function(value, element) {
		return this.optional(element) || value == value.match(/^[6-9]{1}[0-9]{9}$/);
	},'Enter Valid Number');
	
	$.validator.addMethod("acard", function(value, element) {
	return this.optional(element) || value == value.match(/^[2-9]{1}[0-9]{11}$/);
	},'Enter Valid Number');
			
	$.validator.addMethod("lcard", function(value, element) {
	return this.optional(element) || value == value.match(/^[A-Z]{2}[0-9]{2}[0-9]{11}$/);
	},'Enter Valid Number');
	
	$('#frm').validate({
		rules:{				
			fullname:{
				required:true,
				alpha:true
			},
			mobile_no:{
				required:true,
				mno:true	
			},
			emailid:{
				required:true,
				email: true	
			},
			aadhaarcard_no:{
				required:true,
				acard: true	
			},
			license_no:{
				required:true,
				lcard: true	
			},
			driver_fees:{
				required:true,
				digits:true,
				min:100
				
			},
			experience:{
				required:true,
				digits:true,
				range: [1,40]
				
			}
		
		},messages:{			
			fullname:{
				required:"Please Enter Driver's Fullname.",
				alpha:"Enter Valid Name."
			},	
			mobile_no:{
				required:"Please Enter Mobile Number.",
				mno:"Please Enter Valid Mobile Number."
			},
			emailid:{
				required:"Please Enter Email-ID.",
				email:"Please Enter Valid Email-ID."
			},
			aadhaarcard_no:{
				required:"Please Enter Aadhaar-card Number.",
				acard:"Please Enter Valid Aadhaar-card Number."
			},	
			license_no:{
				required:"Please Enter License Number.",
				lcard:"Please Enter Valid license Number (ex:GJ05XXXXXXXXXXX)."
			},
			driver_fees:{
				required:"Please Enter Driver's Fees.",
				digits:"Please Enter Digits Only.",
				min:"Driver's Fees Must Be Atleast 100 Rupees."
				
			},	
			experience:{
				required:"Please Enter Driver's Driving Experience.",
				digits:"Please Enter Digits Only",
				range:"Driving Experience Must Be Between 1 To 40."
				
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
