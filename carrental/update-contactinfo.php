<?php
include 'includes/config.php';
include 'includes/session.php';
error_reporting(0);

if(isset($_POST['submit']))
{
$address=$_POST['address'];
$email=$_POST['email'];	
$contactno=$_POST['contactno'];
$result=mysqli_query($conn,"update tbl_contactusinfo set address='".$address."',email_id='".$email."',contact_no='".$contactno."'") or die(mysqli_error($conn));
if($result==true)
		{
		$msg="Information Updated Successfully";
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
	
	<title>Car Rental Portal | Admin Create Brand</title>

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
					
						<h2 class="page-title">Update Contact Info</h2>

						<div class="row">
							<div class="col-md-10 col-lg-12">
								<div class="panel panel-default">
									<div class="panel-heading" style="background-color:#001662; color:white;">Contact infromation</div>
									<div class="panel-body">
										<form method="post" name="chngpwd" id="frm" class="form-horizontal" onSubmit="return valid();">
										
											
  	        	  <?php if(isset($error)){?><div class="errorWrap"><strong>ERROR</strong>:<?php echo htmlentities($error); ?> </div><?php } 
				else if(isset($msg)){?><div class="succWrap"><strong>SUCCESS</strong>:<?php echo htmlentities($msg); ?> </div><?php }?>
				<?php $sql =mysqli_query($conn,"SELECT * from  tbl_contactusinfo") or die(mysqli_error($conn));
$cnt=1;
			while($row=mysqli_fetch_assoc($sql))
			{
	?>

				<div class="form-group">
												<label class="col-sm-4 control-label"> Address</label>
												<div class="col-sm-8">
													<textarea class="form-control" name="address" id="address" maxlength="70" autocomplete="off"><?php echo $row["address"];?></textarea>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-4 control-label"> Email Id</label>
												<div class="col-sm-8">
													<input type="email" class="form-control" name="email" id="email" maxlength="40" value="<?php echo $row["email_id"];?>" autocomplete="off">
												</div>
											</div>
<div class="form-group">
												<label class="col-sm-4 control-label"> Mobile Number </label>
												<div class="col-sm-8">
													<input type="text" class="form-control" value="<?php echo $row["contact_no"];?>" maxlength="10" name="contactno" id="contactno" autocomplete="off">
												</div>
											</div>
<?php } ?>
											<div class="hr-dashed"></div>
											
										
								
											
											<div class="form-group">
												<div class="col-sm-8 col-sm-offset-4">
								
													<button class="btn btn-primary" name="submit" id="submit" type="submit">Update</button>
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

	$.validator.addMethod("mno", function(value, element) {
		return this.optional(element) || value == value.match(/^[6-9]{1}[0-9]{9}$/);
	},'Enter Valid Number');
	
	$('#frm').validate({
		rules:{
			address:{
				required:true,
				minlength:10							
				},
			email:{
				required:true,
				email:true
			},
			contactno:{
				required:true,
				mno:true
			},
			
		},messages:{
			address:{
				required:"Please Enter Company's Address.",
				minlength:"Enter Minimum 10 Character."
			},	
			email:{
				required:"Please Enter Email-ID.",
				minlength:"Please Enter Valid Email-ID."
			},	
			contactno:{
				required:"Please Enter Mobile Number.",
				mno:"Please Enter Valid Mobile Number."
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
