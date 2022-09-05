<?php
include 'includes/config.php';
	if(isset($_POST["brand_name"]))
	{
		$brandname= mysqli_real_escape_string($conn,$_POST["brand_name"]);
		$query ="select * from tbl_brand  where brand_name ='".$brandname."'";
		$result = mysqli_query($conn,$query);
		echo mysqli_num_rows($result);
	}
	
	if(isset($_POST["vehiclen"]))
	{
		$vehicleno= mysqli_real_escape_string($conn,$_POST["vehiclen"]);
		$query ="select * from tbl_vehicles  where vehicle_no='".$vehicleno."'";
		$result = mysqli_query($conn,$query);
		echo mysqli_num_rows($result);
	}
	
		if(isset($_POST["mno"]))
	{
		$m= mysqli_real_escape_string($conn,$_POST["mno"]);
		$query ="select * from tbl_driver  where mobile_no='".$m."'";
		$result = mysqli_query($conn,$query);
		echo mysqli_num_rows($result);
	}
	
		if(isset($_POST["mail"]))
	{
		$m= mysqli_real_escape_string($conn,$_POST["mail"]);
		$query ="select * from tbl_driver  where emailid='".$m."'";
		$result = mysqli_query($conn,$query);
		echo mysqli_num_rows($result);
	}
	
		if(isset($_POST["acard"]))
	{
		$m= mysqli_real_escape_string($conn,$_POST["acard"]);
		$query ="select * from tbl_driver  where aadhaarcard_no='".$m."'";
		$result = mysqli_query($conn,$query);
		echo mysqli_num_rows($result);
	}
	
		if(isset($_POST["lcard"]))
	{
		$m= mysqli_real_escape_string($conn,$_POST["lcard"]);
		$query ="select * from tbl_driver  where license_no='".$m."'";
		$result = mysqli_query($conn,$query);
		echo mysqli_num_rows($result);
	}
	
?>
