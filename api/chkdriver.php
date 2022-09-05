<?php

include 'config.php';

$cdate=date("Y-m-d");
$status=1;

$result = mysqli_query($conn,"SELECT * FROM tbl_driver WHERE (status ='".$status."') AND (occupied_date = 0 OR (occupied_date < '".$cdate."'))") or die(mysqli_error($conn));
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


?>

