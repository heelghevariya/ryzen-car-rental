<?php

include 'config.php';

$uid = $_POST["uid"];
$pass = md5($_POST["currentpass"]);
$newpass = md5($_POST["newpass"]);

	$sql = mysqli_query($conn,"select password from tbl_user where user_id='".$uid."'") or die(mysqli_error($conn));
	while ($row=mysqli_fetch_assoc($sql)) {
			$cpass=$row['password'];
			}
$response=array();

if($pass==$cpass){

	$sql = mysqli_query($conn,"update tbl_user set password='".$newpass."' where user_id='".$uid."'") or die(mysqli_error($conn));
	$response["status"]=1;
}else
{
	$response["status"]=0;
}
//JSON
echo json_encode($response);
?>

