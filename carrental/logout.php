<?php 
session_start();
unset($_SESSION["adminname"]);
unset($_SESSION["adminid"]);
session_destroy(); 

echo "<script>window.location='index.php';</script>";

?>