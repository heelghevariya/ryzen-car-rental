<?php

include 'config.php';

$mail = $_POST["submail"];


$result = mysqli_query($conn,"INSERT INTO tbl_subscribers(subscriber_email) VALUES ('".$mail."')") or die(mysqli_error($conn));
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

