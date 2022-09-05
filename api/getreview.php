<?php

include 'config.php';

$a=1;


$result = mysqli_query($conn,"select tbl_user.user_name,tbl_reviewrating.rating,tbl_reviewrating.review from tbl_reviewrating join tbl_user on tbl_user.user_id=tbl_reviewrating.u_id where tbl_reviewrating.status='".$a."'") or die(mysqli_error($conn));
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

