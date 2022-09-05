<?php

include 'config.php';

$uid = $_POST["uid"];
$mail = $_POST["mail"];


	$sql = mysqli_query($conn,"update tbl_user set email_id='".$mail."' where user_id='".$uid."'") or die(mysqli_error($conn));
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

