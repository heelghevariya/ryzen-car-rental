<?php

include 'config.php';

$uid = $_POST["uid"];
$vid = $_POST["vid"];
$did = $_POST["did"];
$bno = mt_rand(100000000, 999999999);
$fdate = $_POST["fdate"];
$tdate = $_POST["tdate"];
$msg = $_POST["msg"];
$bstatus = 0;
$isbk=1;

$sql = mysqli_query($conn,"INSERT INTO tbl_booking(u_id,v_id,d_id,booking_no,from_date,to_date,message,b_status) VALUES ('$uid','$vid','$did','$bno','$fdate','$tdate','$msg','$bstatus')") or die(mysqli_error($conn));

$up= mysqli_query($conn,"update tbl_user set isbooked='".$isbk."' where user_id='".$uid."'") or die(mysqli_error($conn));

$response=array();

if($sql==true)
{

	if($did>0)
	{
	$sql = mysqli_query($conn,"update tbl_driver set occupied_date='".$tdate."' where d_id='".$did."'") or die(mysqli_error($conn));	
	}
	$sqli = mysqli_query($conn,"update tbl_vehicles set voccupied_date='".$tdate."' where vehicle_id='".$vid."'") or die(mysqli_error($conn));	
	
	$response["status"]=$bno;
	
}else
{
    $response["status"]=0;
}
//JSON
echo json_encode($response);
?>

