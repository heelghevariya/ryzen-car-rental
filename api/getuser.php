<?php

include 'config.php';

$uid = $_POST["uid"];

	$sql = mysqli_query($conn,"select * from tbl_user where user_id='".$uid."'") or die(mysqli_error($conn));
$response=array();

if($sql==true){
	$output = mysqli_fetch_all($sql, MYSQLI_ASSOC);
	echo json_encode($output);
}else
{
	$response["status"]=0;
	echo json_encode($response);
}
//JSON

?>

