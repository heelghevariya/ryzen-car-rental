<?php

include 'config.php';

$ptype = $_POST["ptype"];

$result = mysqli_query($conn,"select detail from tbl_pages where type='".$ptype."'") or die(mysqli_error($conn));
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

