<?php

include 'config.php';

$uid = $_POST["uid"];
$mno = $_POST["mobileno"];
$dob = $_POST["dob"];
$add = $_POST["address"];
$country = $_POST["country"];
$state = $_POST["state"];
$city = $_POST["city"];

	$sql = mysqli_query($conn,"update tbl_user set mobile_no='".$mno."',
		dob='".$dob."',
		address='".$add."',
		country='".$country."',state='".$state."',city='".$city."' where user_id='".$uid."'") or die(mysqli_error($conn));
$response=array();

if($sql==true){
	$response["status"]=1;
}else
{
	$response["status"]=0;
}
//JSON
echo json_encode($response);
?>

