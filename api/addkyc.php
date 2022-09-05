<?php

include 'config.php';

$uid = $_POST["uid"];
$mail = $_POST["mail"];
$fname = $_POST["fname"];
$ano = $_POST["ano"];

$pno = $_POST["pno"];

$lno = $_POST["lno"];


$status=0;


// //addharimg1
$a1nm=$_FILES["aimg1"]["name"];
$a1tnm=$_FILES["aimg1"]["tmp_name"];
$a1dn="C:/xampp/htdocs/carrental/img/kycimages/".$a1nm;
if(move_uploaded_file($a1tnm, $a1dn))
{
	
}


// //addharimg2
$a2nm=$_FILES["aimg2"]["name"];
$a2tnm=$_FILES["aimg2"]["tmp_name"];
$a2dn="C:/xampp/htdocs/carrental/img/kycimages/".$a2nm;
if(move_uploaded_file($a2tnm, $a2dn))
{}

// // //panimg
$pnm=$_FILES["pimg"]["name"];
$ptnm=$_FILES["pimg"]["tmp_name"];
$pdn="C:/xampp/htdocs/carrental/img/kycimages/".$pnm;
if(move_uploaded_file($ptnm, $pdn))
{}

// // //licenseimg1
$l1nm=$_FILES["limg1"]["name"];
$l1tnm=$_FILES["limg1"]["tmp_name"];
$l1dn="C:/xampp/htdocs/carrental/img/kycimages/".$l1nm;
if(move_uploaded_file($l1tnm, $l1dn))
{}

// // //licenseimg2
$l2nm=$_FILES["limg2"]["name"];
$l2tnm=$_FILES["limg2"]["tmp_name"];
$l2dn="C:/xampp/htdocs/carrental/img/kycimages/".$l2nm;
if(move_uploaded_file($l2tnm, $l2dn))
{}







$result = mysqli_query($conn,"INSERT INTO tbl_kyc(u_id,full_name,aadhaarcard_no,aadhaarimg1,aadhaarimg2,pancard_no,pancardimg1,license_no,licenseimg1,licenseimg2,status) VALUES ('".$uid."','".$fname."','".$ano."','".$a1nm."','".$a2nm."','".$pno."','".$pnm."','".$lno."','".$l1nm."','".$l2nm."','".$status."')") or die(mysqli_error($conn));
// $result = mysqli_query($conn,"INSERT INTO tbl_kyc(u_id,full_name,aadhaarcard_no,aadhaarimg1,pancard_no,license_no
// ,status) VALUES ('".$uid."','".$fname."','".$ano."','".$a1nm."','".$pno."', '".$lno."', '".$status."')") or die(mysqli_error($conn));
$response=array();
if($result==true)
{
	$subject="Regarding KYC Request";
	$body="Dear $fname , Your KYC Request Has Been Successfully Registered, We will inform you as soon as status get updated, kindly go-through Our app for more information.";
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

