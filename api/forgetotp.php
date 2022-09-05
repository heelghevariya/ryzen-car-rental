<?php

include 'config.php';

$mail = $_POST["email"];


$result = mysqli_query($conn,"select * from tbl_user where email_id ='".$mail."'") or die(mysqli_error($conn));
$response=array();
$ct=mysqli_num_rows($result);

if($ct>0)
{
	$mailotp=mt_rand(1000, 9999);

	$subject="Verify Your Email";
	$body="Your Otp Is $mailotp";
	$headers = "From: ryzencarrental@gmail.com";
	$to_email=$mail;
	$response=array();

					if (mail($to_email, $subject, $body, $headers)) 
						{
							$response["status"]=$mailotp;
						} 
						

}else
{
		$response["status"]=0;
	}
//JSON
echo json_encode($response);
?>

