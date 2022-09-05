<?php
include 'includes/config.php';
include 'includes/session.php';

                
	if(isset($_POST["submit"]))
	{
		 $pass=md5($_POST["password"]); 
		 $user=$_SESSION["adminname"];
		 $newpass=md5($_POST["newpassword"]); 

		 $res=mysqli_query($conn,"select * from tbl_admin where password='$pass' and username='$user'");

		 if(mysqli_num_rows($res)>0)
		 {
			 
			$result=mysqli_query($conn,"update tbl_admin set password='$newpass' where  username='$user'");
			if($result)
			{
				$msg="Your Password succesfully changed";
				unset($_SESSION["adminname"]);
				unset($_SESSION["adminid"]);
				session_destroy(); 

				echo "<script>window.location='index.php';</script>";

			} 

		 } else {
			$error="Your current password is not valid.";	
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
	
	<title>Car Rental Portal | Admin Change Password</title>

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
	 
	
<script type="text/javascript">
function valid()
{
if(document.chngpwd.newpassword.value!= document.chngpwd.confirmpassword.value)
{
$("#mymodal").modal('show');
document.chngpwd.confirmpassword.focus();
return false;
}
return true;
}
</script>
  <style>
		.errorWrap {
    padding: 10px;
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
					
						<h2 class="page-title">Change Password</h2>

						<div class="row">
							<div class="col-md-12">
								<div class="panel panel-default">
									<div class="panel-heading" style="background-color:#001662; color:white;">Form fields</div>
									<div class="panel-body">
										<form method="post" name="chngpwd" id="frm" class="form-horizontal" onSubmit="return valid();">
										
			   <?php if(isset($error)){?><div class="errorWrap"><strong>ERROR</strong>:<?php echo htmlentities($error); ?> </div>
			   <?php } else if(isset ($msg)){?><div class="succWrap"><strong>SUCCESS</strong>:<?php echo htmlentities($msg); ?> </div><?php }?>											        	
											<div class="form-group">
												<label class="col-sm-4 control-label">Current Password</label>
												<div class="col-sm-8">
													
													<input type="text" class="form-control" name="password" id="password" maxlength="20" autocomplete="off">
													<i class="fa fa-eye" class="form-control" id="togglePassword" style="cursor: pointer;"></i>
												</div>
											</div>
											<div class="hr-dashed"></div>											
											<div class="form-group">
												<label class="col-sm-4 control-label">New Password</label>
												<div class="col-sm-8">
													<input type="text" class="form-control" name="newpassword" id="tpassword" maxlength="20" autocomplete="off">
													<i class="fa fa-eye" class="form-control" id="ttogglePassword" style="cursor: pointer;"></i>
												</div>
											</div>
											<div class="hr-dashed"></div>
											<div class="form-group">
												<label class="col-sm-4 control-label">Confirm Password</label>
												<div class="col-sm-8">
													<input type="text" class="form-control" name="confirmpassword" id="ttpassword" maxlength="20" autocomplete="off">
														<i class="fa fa-eye" class="form-control" id="tttogglePassword" style="cursor: pointer;"></i>												
												</div>
											</div>
											<div class="hr-dashed"></div>																													
											<div class="form-group">
												<div class="col-sm-8 col-sm-offset-4">
								
													<button class="btn btn-primary" name="submit" id="submit" type="submit">Save changes</button>
												</div>
											</div>
		
<!-- Modal -->
<div class="modal fade" id="mymodal" data-backdrop="static" data-keyboard="false">
  <div class="modal-dialog modal-md-4">
    <div class="modal-content">
      <div class="modal-header">
        <h2>Alert !</h2>	
      </div>
      <div class="modal-body" style="font-family:Cambria;">
     New Password And Confirm Password Must Be Same.
      </div>
      <div class="modal-footer">
        <button type="submit" name="ok" class="btn btn-primary" data-dismiss="modal">OK</button>
      </div>
    </div>
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
<script>		
	$('#frm').validate({
		rules:{
			password:{
				required:true,
				minlength:8						
				},
			newpassword:{
				required:true,
				minlength:8						
				},	
			confirmpassword:{
				required:true,
				minlength:8						
				},	
			
		},messages:{
			password:{
				required:"Please Enter Current Password.",
				minlength:"Password Must Be Atleast 8 Character Long."
			},		
			newpassword:{
				required:"Please Enter New Password.",
				minlength:"Password Must Be Atleast 8 Character Long."
			},
			confirmpassword:{
				required:"Please Enter Confirm Password.",
				minlength:"Password Must Be Atleast 8 Character Long."
			},
		},
		submitHandler:function(form){
			form.submit();
		}	
		});
</script>
		
	<script>
	 const togglePassword = document.querySelector('#togglePassword');
  const password = document.querySelector('#password');
 
  togglePassword.addEventListener('click', function (e) {
    // toggle the type attribute
    const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
    password.setAttribute('type', type);
    // toggle the eye slash icon
    this.classList.toggle('fa-eye-slash');
});</script>
	<script>
	 const ttogglePassword = document.querySelector('#ttogglePassword');
  const tpassword = document.querySelector('#tpassword');
 
  ttogglePassword.addEventListener('click', function (e) {
    // toggle the type attribute
    const type = tpassword.getAttribute('type') === 'password' ? 'text' : 'password';
    tpassword.setAttribute('type', type);
    // toggle the eye slash icon
    this.classList.toggle('fa-eye-slash');
});</script>
<script>
	 const tttogglePassword = document.querySelector('#tttogglePassword');
  const ttpassword = document.querySelector('#ttpassword');
 
  tttogglePassword.addEventListener('click', function (e) {
    // toggle the type attribute
    const type = ttpassword.getAttribute('type') === 'password' ? 'text' : 'password';
    ttpassword.setAttribute('type', type);
    // toggle the eye slash icon
    this.classList.toggle('fa-eye-slash');
});</script>

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

</body>

</html>
