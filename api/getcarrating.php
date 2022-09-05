<?php

include 'config.php';



$vid=$_POST["vid"];
$a=1;


$result = mysqli_query($conn,"SELECT tbl_booking.vrating from tbl_booking where tbl_booking.v_id='".$vid."' and (tbl_booking.b_status='".$a."') and (tbl_booking.vrating !='null')") or die(mysqli_error($conn));
$response=array();

$ct=mysqli_num_rows($result);

if($ct>0)
{
    $cnt=0;
    $rate=0;
    $vrate=0;
    while($row=mysqli_fetch_assoc($result))
    {
        $rate=$vrate + $row['vrating'];
        $vrate=$rate;
        $cnt++;
    }
   
    $rr= $vrate / $cnt;
    $response["status"]=$rr;	
		echo json_encode($response);	


	// $output = mysqli_fetch_all($result, MYSQLI_ASSOC);
	// echo json_encode($output);
}else
{

		$response["status"]=0;	
		echo json_encode($response);	
}
//JSON

?>

