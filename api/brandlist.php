<?php

include 'config.php';

$a=1;


$result = mysqli_query($conn,"SELECT brand_name from tbl_brand") or die(mysqli_error($conn));
$response=array();

$ct=mysqli_num_rows($result);

if($ct>0)
{
	$output = mysqli_fetch_all($result, MYSQLI_ASSOC);
	echo json_encode($output);
}else
{

		$response["status"]=0;	
		echo json_encode($response);	
}
//JSON

?>

