<?php

include 'config.php';

$apaid = $_POST["apaid"];
$pstatus = 1;
$bno = $_POST["bno"];
$tid = mt_rand(100000000, 999999999);

$result=  mysqli_query($conn,"select b_id from tbl_booking where booking_no ='".$bno."'") or die(mysqli_error($conn));
while($row=mysqli_fetch_assoc($result)){
	$bid=$row["b_id"];
}

$sql = mysqli_query($conn,"INSERT INTO tbl_payment(booking_id,transaction_id,amount_paid,p_status) VALUES ($bid,$tid,$apaid,$pstatus)") or die(mysqli_error($conn));

$response=array();

if($sql==true)
{
	$response["status"]=1;
	
}else
{
    $response["status"]=0;
}
//JSON
echo json_encode($response);
?>

