<?php

include 'config.php';

$pstatus = 0;
$bno = $_POST["bno"];
$apaid = $_POST["apaid"];

$result=  mysqli_query($conn,"select b_id from tbl_booking where booking_no ='".$bno."'") or die(mysqli_error($conn));
while($row=mysqli_fetch_assoc($result)){
	$bid=$row["b_id"];
}


	$sql = mysqli_query($conn,"INSERT INTO tbl_payment(booking_id,p_status,amount_paid) VALUES ($bid,$pstatus,$apaid)") or die(mysqli_error($conn));

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

