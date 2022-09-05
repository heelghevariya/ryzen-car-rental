<?php

include 'config.php';

$mail = $_POST["umail"];


$result = mysqli_query($conn,"select * from tbl_subscribers where subscriber_email ='".$mail."'") or die(mysqli_error($conn));
$response=array();
$ct=mysqli_num_rows($result);

if($ct>0)
{
	$response["status"]=1;
}else
{
		$response["status"]=0;		
}
//JSON
echo json_encode($response);
?>

