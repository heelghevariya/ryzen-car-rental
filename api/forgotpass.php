<?php

include 'config.php';

$mail = $_POST["email"];
$pass = md5($_POST["password"]);


$result = mysqli_query($conn,"update tbl_user set password='".$pass."' where email_id='".$mail."'") or die(mysqli_error($conn));
$response=array();

if($result)
{
	$response["status"]=1;
}
else{
	$response["status"]=0;
}
//JSON
echo json_encode($response);
?>
