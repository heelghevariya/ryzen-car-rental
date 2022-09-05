<?php

include 'config.php';

$uid = $_POST["uid"];

$result = mysqli_query($conn,"select status from tbl_kyc where u_id ='".$uid."'") or die(mysqli_error($conn));
$response=array();
while($row=mysqli_fetch_assoc($result))
{
    $status1=$row['status'];
}
if($status1==0){
	$response["status"]=0;
}elseif($status1==1)
{	
	$response["status"]=1;					
}
else
{
    $response["status"]=2;   
}
//JSON
echo json_encode($response);
?>

