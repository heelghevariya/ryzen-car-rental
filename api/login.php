<?php

include 'config.php';

$mail = $_POST["email"];
$pass = md5($_POST["password"]);


$result = mysqli_query($conn,"select user_id,user_name,email_id from tbl_user where email_id='".$mail."' and password='".$pass."'") or die(mysqli_error($conn));
$response=array();
$ct=mysqli_num_rows($result);

if($ct>0){
	while($row=mysqli_fetch_assoc($result)){
	$response["status"]=1;
	$response["userid"]=$row['user_id'];
	$response["username"]=$row['user_name'];
	$response["usermail"]=$row['email_id'];
          }

}else{
	$response["status"]=0;
}
//JSON
echo json_encode($response);
?>
