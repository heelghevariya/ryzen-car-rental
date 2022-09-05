<?php

include 'config.php';

$mailotp=mt_rand(1000, 9999);


$subject="Verify Your Email";
$body="Your Otp Is $mailotp";
$headers = "From: ryzencarrental@gmail.com";
$to_email=$_POST['email'];
$response=array();

				if (mail($to_email, $subject, $body, $headers)) 
					{
						$response["status"]=$mailotp;
					} 
					else 
					{
						$response["status"]=0;
					}

echo json_encode($response);
?>
