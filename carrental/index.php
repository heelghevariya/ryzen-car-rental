<?php
session_start();
include('includes/config.php');
error_reporting(0);
if(isset($_POST["login"]))
{
	   $user=$_POST["username"];
	   $pass=md5($_POST["password"]); 

	   $res=mysqli_query($conn,"select * from tbl_admin where username='$user' and password='$pass'");

	   if(mysqli_num_rows($res)>0)
	   {
			 
			while($row=mysqli_fetch_assoc($res))
			{
				$username=$row["username"];
				$password=$row["password"];
				$adminid=$row["admin_id"];
			}
			
			if($user === $username && $pass=== $password)
			{
				  $_SESSION["adminname"]=$username;
				  $_SESSION["adminid"]=$adminid;  

				 echo "<script>window.location='dashboard.php';</script>";
				 
			} 

	   }
}


?>

<!doctype html>
<html lang="en" class="no-js">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">


<title>Car Rental Portal | Admin Login</title>
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/dataTables.bootstrap.min.css">
	<link rel="stylesheet" href="css/bootstrap-social.css">
	<link rel="stylesheet" href="css/bootstrap-select.css">
	<link rel="stylesheet" href="css/fileinput.min.css">
	<link rel="stylesheet" href="css/awesome-bootstrap-checkbox.css">
	<link rel="stylesheet" href="css/style.css">
<style>
.error{

  color:red;
  width:100%;
  font-size:13px;
  font-family:Corbel;
  margin-bottom:10px;
   
}
</style>
</head>

<body>

	<div class="login-page bk-img" style="background-image: url(img/login-bg.jpg);">
		<div class="form-content">
			<div class="container">
				<div class="row">
				<div class="col-sm-2 col-md-6 col-lg-6 col-md-offset-3">
						<h1 class="text-center text-bold mt-4x" style="color:#fff">Admin | Sign in</h1>
						<div class="well row pt-2x pb-3x bk-light">
							<div class="col-md-8 col-md-offset-2">
								<form method="post" id="frm">
								
									
									<label  class="text-uppercase text-sm">Username </label>
									<input type="text" placeholder="Username" name="username" class="form-control" autocomplete="off" maxlength="20">
									<div></br></div>

									<label  class="text-uppercase text-sm">Password</label>
									<input style="margin-bottom:16px;" type="password" placeholder="Password" name="password" class="form-control" autocomplete="off" maxlength="20">															
									<?php
									   $user=$_POST["username"];
										$pass=md5($_POST["password"]); 
										  $res=mysqli_query($conn,"select * from tbl_admin where username='$user' and password='$pass'");

										if($user != $username && $pass != $password)
								{
						

									?><label class="error">Invalid Username Or Password.</label><?php
				 
								} 
									?>																		
									<button class="btn btn-primary btn-block" name="login" id="submit" type="submit">LOGIN</button>

								</form>

		
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
		

<script>		
	$('#frm').validate({
		rules:{
			username:"required",												
		    password:"required",
				
		},messages:{
			username:"Please Enter Admin Username",
			password:"Please Enter Password",
			
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