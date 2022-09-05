<?php

include 'config.php';

$uid = $_POST["uid"];


$result = mysqli_query($conn,"delete from tbl_reviewrating where u_id='".$uid."'") or die(mysqli_error($conn));
$response=array();
if($result==true)
{
	$response["status"]=1;
}else
{
		$response["status"]=0;		
}
//JSON
echo json_encode($response);
?>

