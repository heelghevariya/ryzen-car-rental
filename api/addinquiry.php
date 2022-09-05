<?php

include 'config.php';

$uname = $_POST["username"];
$mail = $_POST["email"];
$mno = $_POST["mno"];
$mess = $_POST["message"];
$status=0;


$result = mysqli_query($conn,"INSERT INTO tbl_inquiry(name,email_id,contact_no,message,status) VALUES ('".$uname."','".$mail."','".$mno."','".$mess."','".$status."')") or die(mysqli_error($conn));
$response=array();
if($result==true)
{
	$subject="Thanks For Your Inquiry";
	$body="Your Inquiry Has Been Successfully Registered, You will get response within 24 hours on your email.";
	$headers = "From: ryzencarrental@gmail.com";
	$to_email=$mail;

			if (mail($to_email, $subject, $body, $headers)) 
					{
						
					} 
$response["status"]=1;	
}else
{
		$response["status"]=0;		
}
//JSON
echo json_encode($response);
?>

