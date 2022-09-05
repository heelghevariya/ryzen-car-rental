<?php

include 'config.php';

$uname = $_POST["username"];
$mail = $_POST["email"];
$pass = md5($_POST["password"]);
$mno = $_POST["mobileno"];
$dob = $_POST["dob"];
$add = $_POST["address"];
$country = $_POST["country"];
$state = $_POST["state"];
$city = $_POST["city"];
$isbk=0;

$result = mysqli_query($conn,"select user_id,user_name,email_id from tbl_user where mobile_no ='".$mno."'") or die(mysqli_error($conn));
$ct=mysqli_num_rows($result);
$response=array();

if($ct>0){
	$response["status"]=0;
}else
{
    
	$sql = mysqli_query($conn,"INSERT INTO tbl_user(user_name,email_id,password,mobile_no,dob,address,country,state,city,isbooked) VALUES ('$uname','$mail','$pass','$mno','$dob','$add','$country','$state','$city',$isbk)") or die(mysqli_error($conn));
	if($sql==true){
					$subject="Dear $uname";
					$body="Congratulation!! You Are Successfully Registered in Ryzen Car Rental.";
					$headers = "From: ryzencarrental@gmail.com";
					$to_email=$mail;
					

					if (mail($to_email, $subject, $body, $headers)) 
						{
							
						} 
		

	              }
 $mmm = mysqli_query($conn,"select user_id,user_name,email_id from tbl_user where mobile_no ='".$mno."'") or die(mysqli_error($conn));
	while($row=mysqli_fetch_assoc($mmm))
	{
	$response["status"]=1;
	$response["userid"]=$row['user_id'];
	$response["username"]=$row['user_name'];
	$response["usermail"]=$row['email_id'];
     }

}
//JSON
echo json_encode($response);
?>

