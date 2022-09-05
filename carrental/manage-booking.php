<?php
include 'includes/config.php';
include 'includes/session.php';
error_reporting(0);

if(isset($_POST["confirm"]))
{

$status=1;

$sql = mysqli_query($conn,"UPDATE tbl_booking SET b_status='".$status."' WHERE  b_id='".$_POST["con"]."'")or die(mysqli_error($conn));
if($sql==true)
	{
			$data = mysqli_query($conn,"select tbl_booking.to_date,tbl_booking.from_date,tbl_booking.d_id,tbl_booking.booking_no,tbl_driver.emailid,tbl_user.email_id from tbl_booking join tbl_driver on tbl_driver.d_id=tbl_booking.d_id join tbl_user on tbl_user.user_id=tbl_booking.u_id where b_id='".$_POST["con"]."'") or die(mysqli_error($conn));
			if(mysqli_num_rows($data)>0)
			{
			while($row=mysqli_fetch_assoc($data))
			{
	
			$did=$row['d_id'];
			$fdate=$row['from_date'];
			$tdate=$row['to_date'];
			$bno=$row['booking_no'];
			$dmail=$row['emailid'];
			$umail=$row['email_id'];
			$result = mysqli_query($conn,"UPDATE tbl_driver SET occupied_date ='".$tdate."' where d_id='".$did."'") or die(mysqli_error($conn));
			
			$subject="You Are Assigned As Driver For #$bno" ;
			$body="Congratulations , You Are Assigned As A Driver From $fdate to $tdate , Visit At Branch For More Inforemation";
			$headers = "From: ryzencarrental@gmail.com";
			$to_email=$dmail;			
			
				if (mail($to_email, $subject, $body, $headers)) 
					{
																			
					} 
			
			$subject="Your Ryzencarrent.com Booking For #$bno" ;
			$body="Your Booking Request For Booking-ID # $bno Has Been Confirmed , Kindly Go-Through Our App For More Inforemation";
			$headers = "From: ryzencarrental@gmail.com";
			$to_email=$umail;			
			
				if (mail($to_email, $subject, $body, $headers)) 
					{
																			
					} 
					
			}
			}
			else
			{
					$udata = mysqli_query($conn,"select tbl_booking.booking_no,tbl_user.email_id from tbl_booking join tbl_user on tbl_user.user_id=tbl_booking.u_id where b_id='".$_POST["con"]."'") or die(mysqli_error($conn));
					while($wow=mysqli_fetch_assoc($udata))
					{
			$bno=$wow['booking_no'];
			$umail=$wow['email_id'];
			$subject="Your Ryzencarrent.com Booking For #$bno" ;
			$body="Your Booking Request For Booking-ID # $bno Has Been Confirmed , Kindly Go-Through Our App For More Inforemation";
			$headers = "From: ryzencarrental@gmail.com";
			$to_email=$umail;	
					
							if (mail($to_email, $subject, $body, $headers)) 
									{
																							
									} 
				
					}
			}
	
	}header("location:confirmed-booking.php");
}	

if(isset($_POST["cancel"]))
{
$status=2;

$sql = mysqli_query($conn,"UPDATE tbl_booking SET b_status='".$status."' WHERE  b_id='".$_POST["can"]."'")or die(mysqli_error($conn));
if($sql==true)
	{
	$data = mysqli_query($conn,"select tbl_booking.to_date,tbl_booking.from_date,tbl_booking.v_id,tbl_booking.d_id,tbl_booking.booking_no,tbl_driver.emailid,tbl_user.email_id from tbl_booking join tbl_driver on tbl_driver.d_id=tbl_booking.d_id join tbl_user on tbl_user.user_id=tbl_booking.u_id where b_id='".$_POST["can"]."'") or die(mysqli_error($conn));
			
				if(mysqli_num_rows($data)>0)
			{
			
			while($row=mysqli_fetch_assoc($data))
			{
	
			$did=$row['d_id'];	
			$vid=$row['v_id'];	
			$bno=$row['booking_no'];			
			$umail=$row['email_id'];
			$a=0;
			$v=0;
			$result = mysqli_query($conn,"UPDATE tbl_driver SET occupied_date='".$a."' where d_id='".$did."'") or die(mysqli_error($conn));
			$vdate = mysqli_query($conn,"UPDATE tbl_vehicles SET voccupied_date='".$v."' where vehicle_id='".$vid."'") or die("$vid");
	
						
			$subject="Your Ryzencarrent.com Booking For #$bno" ;
			$body="Your Booking Request For Booking-ID #$bno Has Been Cancelled, Kindly Go-Through Our App For More Inforemation ,Sorry For Your Inconvenience";
			$headers = "From: ryzencarrental@gmail.com";
			$to_email=$umail;			
			
				if (mail($to_email, $subject, $body, $headers)) 
					{
																			
					} 
					
			}
			}
			else{
					$udata = mysqli_query($conn,"select tbl_booking.booking_no,tbl_booking.v_id,tbl_user.email_id from tbl_booking join tbl_user on tbl_user.user_id=tbl_booking.u_id where b_id='".$_POST["can"]."'") or die(mysqli_error($conn));
					while($wow=mysqli_fetch_assoc($udata))
					{
			$vid=$wow['v_id'];	
			$bno=$wow['booking_no'];
			$umail=$wow['email_id'];
			$v=0;

			$vdate3 = mysqli_query($conn,"UPDATE tbl_vehicles SET voccupied_date='".$v."' WHERE vehicle_id='".$vid."'") or die(mysqli_error($conn));
	
			
			$subject="Your Ryzencarrent.com Booking For #$bno" ;
			$body="Your Booking Request For Booking-ID #$bno Has Been Cancelled, Kindly Go-Through Our App For More Inforemation ,Sorry For Your Inconvenience";
			$headers = "From: ryzencarrental@gmail.com";
			$to_email=$umail;
					
							if (mail($to_email, $subject, $body, $headers)) 
									{
																							
									} 
				
					}
				
			}
	}header("location:canceled-booking.php");
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
	
	<title>Car Rental Portal | New Bookings   </title>

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

						<h2 class="page-title">Booking Details</h2>

						<!-- Zero Configuration Table -->
						<div class="panel panel-default">
							<div class="panel-heading" style="background-color:#001662; color:white;">Bookings Info</div>
							<div class="panel-body">



								<table border="1"  class="display table table-striped table-bordered table-hover" cellspacing="0" width="100%"  >
				
									<tbody>

									<?php 
$bid=intval($_GET['bid']);

$sql = mysqli_query($conn,"SELECT tbl_user.*,tbl_payment.transaction_id,tbl_payment.amount_paid,tbl_payment.p_status,tbl_brand.brand_name,tbl_vehicles.vehicle_title,tbl_booking.*,
DATEDIFF(tbl_booking.to_date,tbl_booking.from_date) as totalnodays,tbl_vehicles.priceperday
from tbl_booking join tbl_vehicles on tbl_vehicles.vehicle_id=tbl_booking.v_id join tbl_user on tbl_user.user_id=tbl_booking.u_id join tbl_brand on tbl_vehicles.brand_id=tbl_brand.brand_id join tbl_payment on tbl_booking.b_id=tbl_payment.booking_id where tbl_booking.b_id='".$bid."'") or die(mysqli_error($conn));

		while($row=mysqli_fetch_assoc($sql))
		{
?>

	<h3 style="text-align:center; color:red">#<?php echo $row['booking_no'];?> Booking Details </h3>

		<tr>
											<th colspan="4" style="text-align:center;color:blue">User's Details</th>
										</tr>
										<tr>
										<div id="print">
											<th>Booking No.</th>
											<td>#<?php echo $row['booking_no'];?></td>
											<th>Name</th>
											<td><?php echo $row['user_name'];?></td>
										</tr>
										<tr>											
											<th>Email Id</th>
											<td><?php echo $row['email_id'];?></td>
											<th>Contact No</th>
											<td><?php echo $row['mobile_no'];;?></td>
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
		<?php } ?>
										
				<?php 
				
				$dd=mysqli_query($conn,"select tbl_booking.d_id	from tbl_booking where tbl_booking.b_id='".$bid."'") or die(mysqli_error($conn));				
				while($row=mysqli_fetch_assoc($dd))		
				{
					$bdid=$row['d_id'];
				}
				
				$driver="select tbl_driver.fullname,tbl_driver.emailid,tbl_driver.status,tbl_driver.driver_fees from tbl_driver where tbl_driver.d_id='".$bdid."'";
				$dresult = mysqli_query($conn,$driver);
				if(mysqli_num_rows($dresult)>0)
				{
						$mm=mysqli_query($conn,"select tbl_driver.fullname,tbl_driver.d_id,tbl_driver.emailid,tbl_driver.status,tbl_driver.driver_fees from tbl_driver where tbl_driver.d_id='".$bdid."'")or die(mysqli_error($conn));
				
						while($dow=mysqli_fetch_assoc($mm))
					{
					?>					<tr>
											<th colspan="4" style="text-align:center;color:blue">Driver's Details</th>
										</tr>										
											<tr>											
												<th>Driver Name</th>
												<td><a href="view-Driver.php?id=<?php echo $dow['d_id'];?>"><?php echo $dow['fullname'];?></td>
												<th>Email</th>
												<td><?php echo  $dow['emailid'];?></td>
											</tr>
											<tr>
											<th>Driver Fees (Per Day)</th>
												<td><?php echo $dow['driver_fees'];?></td>
												<th>Staus</th>
												<td><?php 
												if($dow['status']==0)
												{
												?><span class="label label-warning">Unavailable</span>
												<?php
												} else{
												?><span class="label label-success">Available</span>
												<?php
												}
												
										?></td>
											</tr>
				<?php	}} ?>
		<?php
		$book=mysqli_query($conn,"select tbl_brand.brand_name,tbl_booking.*,tbl_vehicles.vehicle_title from tbl_booking join tbl_vehicles on tbl_vehicles.vehicle_id=tbl_booking.v_id join tbl_brand on tbl_vehicles.brand_id=tbl_brand.brand_id where tbl_booking.b_id='".$bid."'") or die(mysqli_error($conn));	
			while($bbow=mysqli_fetch_assoc($book))
					{
					?>	
										<tr>
											<th colspan="4" style="text-align:center;color:blue">Booking Details</th>
										</tr>
											<tr>											
											<th>Vehicle Name</th>
											<td><a href="view-vehicles.php?id=<?php echo $bbow['v_id'];?>"><?php echo $bbow['brand_name'];?> , <?php echo $bbow['vehicle_title'];?></td>
											<th>Booking Date</th>
											<td><?php echo $bbow['booking_date'];?></td>
										</tr>
										<tr>
											<th>From Date</th>
											<td><?php echo $bbow['from_date'];?></td>
											<th>To Date</th>
											<td><?php echo $bbow['to_date'];?></td>
										</tr>
					<?php } ?>
					
					
					<?php
					$thirdb=mysqli_query($conn,"select tbl_vehicles.priceperday,tbl_booking.*,tbl_payment.transaction_id,tbl_payment.amount_paid,tbl_payment.p_status,DATEDIFF(tbl_booking.to_date,tbl_booking.from_date) as totalnodays from tbl_booking join tbl_vehicles on tbl_vehicles.vehicle_id=tbl_booking.v_id join tbl_payment on tbl_booking.b_id=tbl_payment.booking_id where tbl_booking.b_id='".$bid."'") or die(mysqli_error($conn));
						while($ttb=mysqli_fetch_assoc($thirdb))
					{
					?>	
<tr>
	<th>Total Days</th>
	<td><?php 
		$a='1';
		echo $tdays=$ttb['totalnodays']+$a;?></td>
	<th>Rent Per Days</th>
	<td><?php echo $ppdays=$ttb['priceperday'];?></td>
</tr>
<tr>
	<th colspan="3" style="text-align:center">Grand Total</th>	
	<td><?php 	
	
		$countdriver="select tbl_driver.driver_fees from tbl_driver where tbl_driver.d_id='".$bdid."'";
		$secondresult = mysqli_query($conn,$driver);
				if(mysqli_num_rows($secondresult )>0)
				{
						$gg=mysqli_query($conn,"select tbl_driver.driver_fees from tbl_driver where tbl_driver.d_id='".$bdid."'")or die(mysqli_error($conn));
						while($rdow=mysqli_fetch_assoc($gg))
					{
						$kok=$rdow['driver_fees'];
					}
					
				}
				else
				{
					$kok='0';
				}
			
	

		$tdppdays=$kok*$tdays;
		$tppdays=$tdays*$ppdays;
		echo $grandtotal=$tdppdays+$tppdays;
		?></td>
</tr>
<tr>
<th>Payment Status</th>
			<?php
				if($ttb['p_status']==0 || $ttb['p_status']==null)
				{
				?><td><?php echo 'Pay At Branch';?></td><?php
				}
				else
				{
				?>
				<td><?php echo 'Payment Done (Through UPI)';?></td><?php
				}			
			?>

<?php
				if($ttb['p_status']==0 || $ttb['p_status']==null)
				{
					?>
					<th>Booking Status</th>
					<td><?php 
					if($ttb['b_status']==0)
					{
					echo htmlentities('Not Confirmed yet');
					} else if ($ttb['b_status']==1) {
					echo htmlentities('Confirmed');
					}
					else{
						echo htmlentities('Cancelled');
					}
					?></td>
					<?php
				}
				else
				{
				?>
				<th>Transaction-ID</th>
				<td><?php echo $ttb['transaction_id'];?></td><?php
				}			
			?>	

</tr>

<tr>
	<?php
if($ttb['p_status']==1)
				{
					?>
					<th>Booking Status</th>
					<td><?php 
					if($ttb['b_status']==0)
					{
					echo htmlentities('Not Confirmed yet');
					} else if ($ttb['b_status']==1) {
					echo htmlentities('Confirmed');
					}
					else{
						echo htmlentities('Cancelled');
					}
					?></td>
					<?php
				}?>
									<?php
										if($ttb['b_status']>=1)
										{
										?><th>Booked Date</th>
										<td><?php  $d=$ttb['action_date'];
							$nd=substr($d,0,10);
							echo $nd; ?></td></td>
							<tr><td style="text-align:center" colspan="4">
											<form method="post">
	   <input name="Submit2" type="submit" class="txtbox4" value="Print" onClick="return f3();" style="cursor: pointer;"  />
	</form></td></tr>
							<?php } ?>
									</tr>
									
									</div>

									<?php if($ttb['b_status']==0){ ?>
										<tr>	
										<td style="text-align:center" colspan="4">
				  <button type="button" class="btn btn-md btn-primary" data-toggle="modal" data-target="#confirmmodal" onclick='confirmdata(<?php echo $ttb["b_id"]; ?>)'>Confirm </button>
    <button type="button" class="btn btn-md btn-danger" data-toggle="modal" data-target="#cancelmodal" onclick='canceldata(<?php echo $ttb["b_id"]; ?>)'>Cancel</button>
       </td>
</tr>
<?php } ?>
										<?php } ?>
										
									</tbody>
								</table>
			

							
						</div>

					

					</div>
				</div>

			</div>
		</div>
	</div>
	
	<div class="modal fade" id="confirmmodal" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					 <h2>Alert !</h2>					
				</div>
				<form method="post">
				<div class="modal-body">
				   Are you sure want To Confirm This Booking ?
				   <input type="hidden" name="con" id="con">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" id="op" data-dismiss="modal">No</button>
					<button type="submit" name="confirm" class="btn btn-primary">Yes</button>
				</div>
			</form>
			</div>
		</div>
	</div>
	
	 <div class="modal fade" id="cancelmodal" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					 <h2>Alert !</h2>					
				</div>
				<form method="post">
				<div class="modal-body">
				   Are you sure want To Cancel This Booking ?
				   <input type="hidden" name="can" id="can">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" id="op" data-dismiss="modal">No</button>
					<button type="submit" name="cancel" class="btn btn-primary">Yes</button>
				</div>
			</form>
			</div>
		</div>
	</div>

	<!-- Loading Scripts -->
	 <script type="text/javascript">
            function confirmdata(val)
            {
                
                $("#con").val(val);
            }

        </script>
		  <script type="text/javascript">
            function canceldata(val)
            {
                
                $("#can").val(val);
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
	<script language="javascript" type="text/javascript">
function f3()
{
window.print(); 
}
</script>
</body>
</html>

