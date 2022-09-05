<?php
include 'includes/config.php';
include 'includes/session.php';
error_reporting(0);

if(isset($_GET['delete']))
{
$id=$_GET["dt"];
$result = mysqli_query($conn,"delete from tbl_brand  WHERE brand_id=$id") or die(mysqli_error($conn));
if($result==true)
		{
		$msg="Page Data Updated Successfully";
		}
		else
		{
			$error="Not updated";

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
	
	<title>Car Rental Portal |Admin Manage Brands   </title>

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

						<h2 class="page-title">Manage Brands</h2>

						<!-- Zero Configuration Table -->
						<div class="panel panel-default">
							<div class="panel-heading" style="background-color:#001662; color:white;">Listed  Brands</div>
							<div class="panel-body">
				<?php if(isset($error)){?><div class="errorWrap"><strong>ERROR</strong>:<?php echo htmlentities($error); ?> </div><?php } 
				else if(isset($msg)){?><div class="succWrap"><strong>SUCCESS</strong>:<?php echo htmlentities($msg); ?> </div><?php }?>
								<table id="zctb" class="display table table-striped table-bordered table-hover" cellspacing="0" width="100%">
									<thead>
										<tr>
										<th>#</th>
												<th>Brand Name</th>
											<th>Creation Date</th>
											<th>Updation Date</th>
										
											<th>Action</th>
										</tr>
									</thead>
									<tfoot>
										<tr>
										<th>#</th>
											<th>Brand Name</th>
											<th>Creation Date</th>
											<th>Updation Date</th>
										
											<th>Action</th>
										</tr>
										</tr>
									</tfoot>
									<tbody>
	<?php
		$result=mysqli_query($conn,"select * from tbl_brand") or die(mysqli_error($conn));
		$cnt=1;
			while($row=mysqli_fetch_assoc($result))
			{
	?>
			<tr>
				<td><?php echo htmlentities($cnt) ?></td>
				<td><?php echo $row["brand_name"]; ?></td>
				<td><?php $d=$row["listing_date"];
							$nd=substr($d,0,10);
							echo $nd; ?></td>
				<td><?php $d=$row["Updation_date"];
							$nd=substr($d,0,10);
							echo $nd; ?></td>
				<td><a href="edit-brand.php?id=<?php echo $row["brand_id"];?>"><i class="fa fa-edit"></i></a>&nbsp;&nbsp;
				<a data-toggle="modal" data-target="#deletemodal" onclick="deletedata(<?php echo $row['brand_id']; ?>)"><i class="fa fa-close"></i></a></td>	
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
	
	<form method="GET">
	<div class="modal fade" id="deletemodal" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h2>Alert !</h2>			
				</div>
				<form method="post">
				<div class="modal-body">
				   Are You Sure Want To Remove This Brand ?
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
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
	
	 <script type="text/javascript">
            function deletedata(val)
            {
                
                $("#dt").val(val);
            }

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

