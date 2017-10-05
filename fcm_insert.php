<?php
require 'int.php';


$devd = $_POST['devid']; 
if($devd==null){
	echo "value is null in variable";
}else{
$sql="INSERT INTO fcm_info (device_id) VALUES ('$devd')";
 
   if (mysqli_query($con,$sql)) {
	   
      echo "Values have been inserted successfully";
   } 	
}
 
?>

