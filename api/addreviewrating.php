<?php

include 'config.php';

$uid = $_POST["uid"];
$review = $_POST["review"];
$rating = $_POST["rating"];
$status=0;


$result = mysqli_query($conn,"INSERT INTO tbl_reviewrating(u_id,review,rating,status) VALUES 
	('".$uid."','".$review."','".$rating."','".$status."')") or die(mysqli_error($conn));
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

