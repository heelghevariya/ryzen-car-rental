<?php

include 'config.php';

$uid = $_POST["uid"];

$result = mysqli_query($conn,"SELECT 
					tbl_user.user_name,
					tbl_user.email_id,
					tbl_user.mobile_no,
					tbl_user.address,
					tbl_user.state,
					tbl_user.country,
					tbl_contactusinfo.address as cadd,
					tbl_contactusinfo.contact_no as cno,
					tbl_contactusinfo.email_id as cmail,
					tbl_booking.booking_no,
					tbl_booking.booking_date,
					tbl_booking.b_status,
					tbl_booking.from_date,
					tbl_booking.to_date,
					DATEDIFF(tbl_booking.to_date,tbl_booking.from_date)+1 as totalnodays,
					tbl_payment.p_status,
					tbl_payment.amount_paid,
					tbl_brand.brand_name,
					tbl_vehicles.vehicle_title,
					tbl_vehicles.priceperday,
					tbl_vehicles.model_year,
					tbl_vehicles.fuel_type,
					tbl_vehicles.seating_capacity,
					tbl_vehicles.vimage1,
					tbl_driver.driver_fees 
					from tbl_booking
					join tbl_contactusinfo
					join tbl_user on tbl_user.user_id=tbl_booking.u_id
					join tbl_vehicles on tbl_vehicles.vehicle_id=tbl_booking.v_id
					join tbl_brand on tbl_vehicles.brand_id=tbl_brand.brand_id 
					join tbl_payment on tbl_booking.b_id=tbl_payment.booking_id
					join tbl_driver on tbl_driver.d_id=tbl_booking.d_id 
					where tbl_booking.u_id='".$uid."'" )  or die(mysqli_error($conn) );
				
$dresult = mysqli_query($conn,"SELECT 
					tbl_user.user_name,
					tbl_user.email_id,
					tbl_user.mobile_no,
					tbl_user.address,
					tbl_user.state,
					tbl_user.country,
					tbl_contactusinfo.address as cadd,
					tbl_contactusinfo.contact_no as cno,
					tbl_contactusinfo.email_id as cmail,
					tbl_booking.booking_no,
					tbl_booking.booking_date,
					tbl_booking.b_status,
					tbl_booking.from_date,
					tbl_booking.to_date,
					DATEDIFF(tbl_booking.to_date,tbl_booking.from_date)+1 as totalnodays,
					tbl_payment.p_status,
					tbl_payment.amount_paid,
					tbl_brand.brand_name,
					tbl_vehicles.vehicle_title,
					tbl_vehicles.priceperday,
					tbl_vehicles.model_year,
					tbl_vehicles.fuel_type,
					tbl_vehicles.seating_capacity,
					tbl_vehicles.vimage1					
					from tbl_booking
					join tbl_contactusinfo
					join tbl_user on tbl_user.user_id=tbl_booking.u_id
					join tbl_vehicles on tbl_vehicles.vehicle_id=tbl_booking.v_id
					join tbl_brand on tbl_vehicles.brand_id=tbl_brand.brand_id 
					join tbl_payment on tbl_booking.b_id=tbl_payment.booking_id 	
					where tbl_booking.u_id='".$uid."' and(tbl_booking.d_id=0)") 	or die(mysqli_error($conn));


 
$output = mysqli_fetch_all($result,MYSQLI_ASSOC);
$output1 = mysqli_fetch_all($dresult,MYSQLI_ASSOC);
$arr = array_merge($output, $output1);

echo json_encode($arr);


?>

