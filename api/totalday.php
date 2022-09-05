
<?php
include 'config.php';

$fdate = date_create($_POST["fdate"]);
$tdate = date_create($_POST["tdate"]);

$timeDiff = date_diff($fdate,$tdate);
$tday= $timeDiff;
$response=array();
$response["tday"]=$tday->days+1;
echo json_encode($response);
?>
