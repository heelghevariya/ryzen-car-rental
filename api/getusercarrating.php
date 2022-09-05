<?php

include 'config.php';

$bno= $_POST["bno"];



$result = mysqli_query($conn,"select vrating from tbl_booking where booking_no='".$bno."' and(vrating !='null')") or die(mysqli_error($conn));
$response=array();
$ct=mysqli_num_rows($result);

if($ct>0){
	while($row=mysqli_fetch_assoc($result))
    {
        $rate=$row['vrating'];
	
    }
	$response["vrating"]=$rate;
	echo json_encode($response);
}else
{
	
	$response["vrating"]=0;		
    echo json_encode($response);			
	}
//JSON
?>

