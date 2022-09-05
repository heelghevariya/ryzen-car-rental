<?php

include 'config.php';

$a=$_POST['t1'];
$b=$_POST['t2'];


//addharimg1

$imgnm=$_FILES['img']['name'];
$imgtmp=$_FILES['img']['tmp_name'];
$imgdn="C:/xampp/htdocs/carrental/img/kycimages/".$imgnm;
if(move_uploaded_file($imgtmp, $imgdn))
{
echo $imgtmp;
}


$imgnm2=$_FILES['img2']['name'];
$imgtmp2=$_FILES['img2']['tmp_name'];
$imgdn2="C:/xampp/htdocs/carrental/img/kycimages/".$imgnm2;
if(move_uploaded_file($imgtmp2, $imgdn2))
{
echo $imgtmp2;
}

$result = mysqli_query($conn,"INSERT INTO test (t1,t2) values ('".$imgnm."','".$imgnm2."')") or die(mysqli_error($conn));

?>

