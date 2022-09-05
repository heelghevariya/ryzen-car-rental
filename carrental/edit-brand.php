<?php
include 'includes/config.php';
include 'includes/session.php';

	if(isset($_POST['submit']))
	{
	$brand=$_POST['brand'];
	$id=$_GET['id'];
	$sql=mysqli_query($conn,"update tbl_brand set brand_name='$brand' where brand_id='$id'") or die(mysqli_error($conn));
		if($sql==true)
				{
				$msg="brand updated successfully";
					header("location:manage-brands.php");
				}
				else
				{
					$error="Somwthing went wrong";
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
	
	<title>Car Rental Portal | Admin Update Brand</title>

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
					
						<h2 class="page-title">Update Brand</h2>

						<div class="row">
							<div class="col-md-10 col-lg-12">
								<div class="panel panel-default">
									<div class="panel-heading" style="background-color:#001662; color:white;">Update Brand</div>
									<div class="panel-body">
										<form method="post" name="chngpwd" id="frm" class="form-horizontal" onSubmit="return valid();">
										
											
  	        	  <?php if(isset($error)){?><div class="errorWrap"><strong>ERROR</strong>:<?php echo htmlentities($error); ?> </div><?php } 
				else if(isset($msg)){?><div class="succWrap"><strong>SUCCESS</strong>:<?php echo htmlentities($msg); ?> </div><?php }?>

<?php	
	$id=$_GET['id'];
	$result=mysqli_query($conn,"select * from tbl_brand WHERE brand_id=$id") or die(mysqli_error($conn));
	$cnt=1;
	while($row=mysqli_fetch_assoc($result))
			{
?>

											<div class="form-group">
												<label class="col-sm-4 control-label">Brand Name</label>
												<div class="col-sm-8">
													<input type="text" class="form-control" value="<?php echo $row["brand_name"];?>" name="brand" id="brand" maxlength="20" autocomplete="off">
													<label id="aa"></label>
												</div>
											</div>
											<div class="hr-dashed"></div>
											
										<?php } ?>								
																			
											<div class="form-group">
												<div class="col-sm-8 col-sm-offset-4">
								
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
$('#brand').keyup(function(){
		var brand = $(this).val();
		$.ajax({
			url:'check.php',
			method:"POST",
			data:{brand_name:brand},
			success:function(data)
			{
				if(data != '0')
				{
					$('#aa').html('<label class="error">Brand Already Exists.</label>');
					
				}	
				else
				{
					$('#aa').html('<span class="text-success"></span>');
					
				}
			}
		})
		
	});

</script>
		
<script type="text/javascript">	
	
	$.validator.addMethod("alpha", function(value, element) {
		return this.optional(element) || value == value.match(/^[a-zA-Z\s]+$/);
	},'Enter Alphabets Only');


$('#frm').validate({
		rules:{																					
			brand:{
				required:true,
				alpha:true
			},
						
		},messages:{			
			brand:{
				required:"Please Enter Brand Name.",
				alpha:"Enter Alphabets Only."
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
