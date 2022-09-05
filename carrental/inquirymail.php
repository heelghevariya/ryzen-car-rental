<?php
include 'includes/config.php';
include 'includes/session.php';
error_reporting(0);

if(isset($_GET['submit']))
{
$subject=$_GET["subject"];
$body=$_GET['body'];
$headers = "From: ryzencarrental@gmail.com";
$to_email=$_GET['to'];
$status=1;

				if (mail($to_email, $subject, $body, $headers)) 
					{
						$msg="Email sent successfully";	
						$result=mysqli_query($conn,"update tbl_inquiry set status='$status' where email_id='$to_email'") or die(mysqli_error($conn));
						header("location:manage-conactusquery.php");
					} 
					else 
					{
						$error="Email sending failed...";
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
	
	<title>Car Rental Portal |Admin Manage Queries   </title>

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

						<h2 class="page-title">Inquiry Reply</h2>

						<!-- Zero Configuration Table -->
						<div class="panel panel-default">
							<div class="panel-heading" style="background-color:#001662; color:white;">Send mail</div>
								  <?php if($error){?><div class="errorWrap"><strong>ERROR</strong>:<?php echo htmlentities($error); ?> </div><?php } 
				else if($msg){?><div class="succWrap"><strong>SUCCESS</strong>:<?php echo htmlentities($msg); ?> </div><?php }?>

							<div class="panel-body">
							
					
<form method="GET" name="mail" id="frm" class="form-horizontal" onSubmit="return valid();">																		 	       
			<div class="form-group">
			<?php $id=($_GET['eid']); ?>
					<label class="col-sm-4 control-label"> To</label>
					
					<div class="col-sm-8">
					<input type="text" class="form-control" name="to" value="<?php echo $id;?>" readonly>
					</div>
			</div>
			<div class="form-group">
							<label class="col-sm-4 control-label"> subject</label>
							<div class="col-sm-8">
								<?php
							 $eid=($_GET['eid']);
							$sql =mysqli_query($conn,"Select message from tbl_inquiry where email_id='".$eid."'") or die(mysqli_error($conn));
	while($row=mysqli_fetch_assoc($sql))
			{
			
					?>		
							
			<input type="text" class="form-control" name="subject" value="<?php echo 'Regarding...'.$row['message'];?>" readonly> <?php } ?>
							</div>
			</div>
	
			<div class="form-group">
							<label class="col-sm-4 control-label"> Message </label>
							<div class="col-sm-8">
						<textarea class="form-control" name="body" id="body" maxlength="250" placeholder="Message..."></textarea>
						<span id="msg"></span>
							</div>
		
			</div>
			<div class="hr-dashed"></div>
		
			<div class="form-group">
			<div class="col-sm-8 col-sm-offset-4">
				<button class="btn btn-primary" name="submit" type="submit" id="submit" href="manage-subscribers.php">Send</button>				
			
		  	</div>
				
        </div>
</form>
					

					</div>
				</div>

			</div>
		</div>
	</div>

	<!-- Loading Scripts -->
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/jquery.validate.min.js"></script>
		
<script>		
	jQuery('#frm').validate({
		rules:{
			body:{
				required:true,
				minlength:5							
				},
			
		},messages:{
			body:{
				required:"Please Enter Message.",
				minlength:"Enter Minimum 5 Character."
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

