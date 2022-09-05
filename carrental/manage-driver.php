<?php
include 'includes/config.php';
include 'includes/session.php';

if(isset($_POST['inactive']))
{

$status="0";
$result = mysqli_query($conn,"UPDATE tbl_driver SET status='".$status."' WHERE  d_id='".$_POST["inact"]."'") or die(mysqli_error($conn));
if($result==true)
			{
			$msg="Driver is now Inactive";
			}
			else
			{
				$error="something went wrong try again later";
			}
}


if(isset($_POST['active']))
{

$status=1;
$result = mysqli_query($conn,"UPDATE tbl_driver SET status='".$status."' WHERE  d_id='".$_POST["act"]."'") or die(mysqli_error($conn));
if($result==true)
			{
			$msg="Driver is Now Successfully Active";
			}
			else
			{
				$error="something went wrong try again later";
			}
}

	if(isset($_GET['delete']))
	{
	$id=$_GET['dt'];
	$sql = mysqli_query($conn,"delete from tbl_driver WHERE d_id=$id") or die(mysqli_error($conn));
	if($sql==true)
			{
			$msg="Driver Deleted Successfully";
			}
			else
			{
				$error="something went wrong try again later";

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
	
	<title>Car Rental Portal |Admin Manage Driver   </title>

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

						<h2 class="page-title">Manage Driver</h2>

						<!-- Zero Configuration Table -->
						<div class="panel panel-default">
							<div class="panel-heading" style="background-color:#001662; color:white;">Drivers detail</div>
							<div class="panel-body">
							<?php if(isset($error)){?><div class="errorWrap"><strong>ERROR</strong>:<?php echo htmlentities($error); ?> </div><?php } 
				else if(isset($msg)){?><div class="succWrap"><strong>SUCCESS</strong>:<?php echo htmlentities($msg); ?> </div><?php }?>
								<table id="zctb" class="display table table-striped table-bordered table-hover" cellspacing="0" width="100%">
									<thead>
										<tr>
										<th>#</th>
											
											<th>Driver Image</th>
											<th>Driver Name</th>
											<th>Email-Id</th>
											<th>Mobile No</th>
											<th>Fees Per Day</th>
											<th>Joining Date</th>											
											<th>Action</th>
										</tr>
									</thead>
									<tfoot>
										<tr>
										<th>#</th>
											<th>Driver Image</th>
											<th>Driver Name</th>
											<th>Email-Id</th>
											<th>Mobile No</th>
											<th>Fees Per Day</th>
											<th>Joining Date</th>											
											<th>Action</th>
										</tr>
										</tr>
									</tfoot>
									<tbody>

<?php
 $result =mysqli_query($conn,"SELECT * from tbl_driver")  or die(mysqli_error($conn));
$cnt=1;
		while($row=mysqli_fetch_assoc($result))
		{
?>

										<tr>
											<td><?php echo htmlentities($cnt);?></td>
											<td><?php if($row["photo"]=="")
													{
													echo "image unavailable";
													} 
														else {?>
														<img src="img/driverimg/<?php echo $row["photo"];?>" width="87" height="100" style="border:solid 1px #000">
														
														<?php } ?>
											</td>
											<td><?php echo $row["fullname"];?></td>
											<td><?php echo $row["emailid"];?></td>
											<td><?php echo $row["mobile_no"];?></td>
											<td><?php echo $row["driver_fees"];?></td>
											<td><?php $d=$row["joining_date"];
							$nd=substr($d,0,10);
							echo $nd; ?></td>
												
<td>
<?php if($row['status']=="" || $row['status']==0)

 { ?><button type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#activemodal" onclick='activedata(<?php echo $row["d_id"]; ?>)'>Inactive</button>
            
<?php } else {?>

<button type="button" class="btn btn-sm btn-success" data-toggle="modal" data-target="#inactivemodal" onclick='inactivedata(<?php echo $row["d_id"]; ?>)'>Active</button>
     

<?php } ?>
&nbsp;<a href="view-driver.php?id=<?php echo $row["d_id"];?>"><i class="fa fa-eye"></i></a>&nbsp;
&nbsp;<a href="edit-driver.php?id=<?php echo $row["d_id"];?>"><i class="fa fa-edit"></i></a>&nbsp;
<a data-toggle="modal" data-target="#deletemodal" onclick='deletedata(<?php echo $row["d_id"]; ?>)'><i class="fa fa-close"></i></a>

</td>
										</tr>
										<?php $cnt=$cnt+1; } ?>
										
									</tbody>
								</table>

						

							</div>
						</div>

					

					</div>
				</div>

			</div>
		</div>
	</div>
	
	   <div class="modal fade" id="activemodal" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog modal-md-4" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h2>Alert !</h2>				
				</div>
				<form method="post">
				<div class="modal-body">
				   Are You Sure Want To Active This Driver ?
				   <input type="hidden" name="act" id="act">
				</div>
				<div class="modal-footer">
					<button type="button" id="op" class="btn btn-secondary" data-dismiss="modal">No</button>
					<button type="submit" name="active" class="btn btn-primary">Yes</button>
				</div>
			</form>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="inactivemodal" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h2>Alert !</h2>			
				</div>
				<form method="post">
				<div class="modal-body">
				   Are You Sure Want To Inactive This Driver ?
				   <input type="hidden" name="inact" id="inact">
				</div>
				<div class="modal-footer">
					<button type="button" id="op" class="btn btn-md btn-secondary" data-dismiss="modal">No</button>
					<button type="submit" name="inactive" class="btn btn-md btn-primary">Yes</button>
				</div>
			</form>
			</div>
		</div>
	</div>
	
	<form method="GET">	
<div class="modal fade" id="deletemodal" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h2>Alert !</h2>			
				</div>
				<form method="post">
				<div class="modal-body">
				   Are You Sure Want To Remove This Driver ?
				   <input type="hidden" name="dt" id="dt">
				</div>
				<div class="modal-footer">
					<button type="button" id="op" class="btn btn-md btn-secondary" data-dismiss="modal">No</button>
					<button type="submit" name="delete" class="btn btn-md btn-primary">Yes</button>
				</div>
			</form>
			</div>
		</div>
	</div>
</form>

	<!-- Loading Scripts -->
	 <script type="text/javascript">
            function activedata(val)
            {
                
                $("#act").val(val);
            }

        </script>
		  <script type="text/javascript">
            function inactivedata(val)
            {
                
                $("#inact").val(val);
            }
			</script>
			 <script type="text/javascript">
            function deletedata(val)
            {
                
                $("#dt").val(val);
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
</body>
</html>

