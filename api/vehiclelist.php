<?php

include 'config.php';



$cdate=$_POST["date"];
$a=1;


$result = mysqli_query($conn,"SELECT tbl_vehicles.*,tbl_brand.brand_name,tbl_brand.brand_id as bid from tbl_vehicles join tbl_brand on tbl_brand.brand_id=tbl_vehicles.brand_id where tbl_vehicles.voccupied_date = 0 OR tbl_vehicles.voccupied_date < '".$cdate."'") or die(mysqli_error($conn));
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

