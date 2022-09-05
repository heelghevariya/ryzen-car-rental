<?php
include 'includes/config.php';
include 'includes/session.php';
error_reporting(0);

if(isset($_POST['inactive']))
{

$status="0";
$result = mysqli_query($conn,"UPDATE tbl_reviewrating SET status='".$status."' WHERE  r_id='".$_POST["inact"]."'") or die(mysqli_error($conn));
if($result==true)
			{
			$msg="Testimonial Successfully Inactive";
			}
			else
			{
				$error="something went wrong try again later";
			}
}


if(isset($_POST['active']))
{
$status=1;
$result = mysqli_query($conn,"UPDATE tbl_reviewrating SET status='".$status."' WHERE  r_id='".$_POST["act"]."'") or die(mysqli_error($conn));
if($result==true)
			{
			$msg="Testimonial Successfully Active";
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
	
	<title>Car Rental Portal |Admin Manage review\rating  </title>

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

						<h2 class="page-title">Manage reviews&rating </h2>

						<!-- Zero Configuration Table -->
						<div class="panel panel-default">
							<div class="panel-heading"  style="background-color:#001662; color:white;">User reviews & rating</div>
							<div class="panel-body">
							<?php if($error){?><div class="errorWrap"><strong>ERROR</strong>:<?php echo htmlentities($error); ?> </div><?php } 
				else if($msg){?><div class="succWrap"><strong>SUCCESS</strong>:<?php echo htmlentities($msg); ?> </div><?php }?>
								<table id="zctb" class="display table table-striped table-bordered table-hover" cellspacing="0" width="100%">
									<thead>
										<tr>
										<th>#</th>
											<th>Name</th>
											<th>Email</th>
											<th>Reviews</th>
											<th>Rating</th>
											<th>Posting Date</th>
											<th>Action</th>
										</tr>
									</thead>
									<tfoot>
										<tr>
										<th>#</th>
											<th>Name</th>
											<th>Email</th>
											<th>Reviews</th>
											<th>Rating</th>
											<th>Posting Date</th>
											<th>Action</th>
										</tr>
									</tfoot>
									<tbody>

									<?php 
$sql = mysqli_query($conn,"SELECT tbl_user.user_name,tbl_user.email_id,tbl_reviewrating.review,tbl_reviewrating.rating,tbl_reviewrating.posting_date,tbl_reviewrating.status,tbl_reviewrating.r_id from tbl_reviewrating join tbl_user on tbl_user.user_id=tbl_reviewrating.u_id") or die(mysqli_error($conn));
$cnt=1;
		while($row=mysqli_fetch_assoc($sql))
		{
?>

										<tr>
											<td><?php echo htmlentities($cnt);?></td>
											<td><?php echo $row['user_name'];?></td>
											<td><?php echo $row['email_id'];?></td>
											<td><?php echo $row['review'];?></td>
											<td>
											<?php 
											if(is_null($row['rating'])) 
												{
												?><center>-</center>
												<?php
												}
												else
												{
													if($row['rating']>0) 
													{ $j=$row['rating'];
														for($i=1;$i<=$j;$i++)
														{
															?> <i class="fa fa-star">
															<?php
														}
														$x=5;
														$y=$j;
														$a=$x-$j;
														if($a!=0)
															{
																for($n=1;$n <= $a;$n++)
																{
																	?> <i class="fa fa-star-o">
																	<?php
																}													
															}																								
													}
													else
													{ 
														for($i=1;$i <= 5;$i++)
														{
															?> <i class="fa fa-star-o">
															<?php
														}										
													}
												} ?>
											</td>
												
											<td><?php $d=$row["posting_date"];
							$nd=substr($d,0,10);
							echo $nd; ?></td>
										<td><?php if($row['status']=="" || $row['status']==0)

 { ?>  <button type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#activemodal" onclick='activedata(<?php echo $row["r_id"]; ?>)'>Inactive</button>
           <?php } else {?>
 <button type="button" class="btn btn-sm btn-success" data-toggle="modal" data-target="#inactivemodal" onclick='inactivedata(<?php echo $row["r_id"]; ?>)'>Active</button>     
</td>
<?php } ?></td>
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
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h2>Alert !</h2>					
				</div>
				<form method="post">
				<div class="modal-body">
				   Are you sure want To Active This Review & Rating ?
				   <input type="hidden" name="act" id="act">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" id="op" data-dismiss="modal">No</button>
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
				   Are you sure want To Inactive This Review & Rating ?
				   <input type="hidden" name="inact" id="inact">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" id="op"  data-dismiss="modal">No</button>
					<button type="submit" name="inactive" class="btn btn-primary">Yes</button>
				</div>
			</form>
			</div>
		</div>
	</div>

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

