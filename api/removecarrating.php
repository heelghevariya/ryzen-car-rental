<?php

include 'config.php';

$bno=$_POST["bno"];


$result = mysqli_query($conn,"update tbl_booking set vrating=null where booking_no ='".$bno."'") or die(mysqli_error($conn));
if($result==true)
{
$response["status"]=1;	
echo json_encode($response);	
}else
{
		$response["status"]=0;	
        echo json_encode($response);		
}


?>

