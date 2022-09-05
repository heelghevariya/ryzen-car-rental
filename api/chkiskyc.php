<?php

include 'config.php';

$uid = $_POST["uid"];
$a=1;

$result = mysqli_query($conn,"select status from tbl_kyc where u_id ='".$uid."' and status='".$a."'") or die(mysqli_error($conn));
$response=array();
$ct=mysqli_num_rows($result);

if($ct>0){
	$response["status"]=1;
}else
{
	
	$response["status"]=0;					
	}
//JSON
echo json_encode($response);
?>

