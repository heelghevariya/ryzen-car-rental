<?php

include 'config.php';

$uid = $_POST["uid"];
$isbk=1;


$result = mysqli_query($conn,"select * from tbl_user where isbooked ='".$isbk."' and 
	(user_id='".$uid."')") or die(mysqli_error($conn));
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

