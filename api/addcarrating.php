<?php

include 'config.php';



$bno=$_POST["bno"];
$rating=$_POST["rating"];

$a=1;

$result = mysqli_query($conn,"update tbl_booking set vrating ='".$rating."' where booking_no='".$bno."'") or die(mysqli_error($conn));
if($result==true)
{
$response["status"]=1;	
echo json_encode($response);	
}else
{
		$response["status"]=0;		
        echo json_encode($response);	
}
//JSON


?>

