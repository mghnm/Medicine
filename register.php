<?php
require "connection.php";

$user_email = $_POST["identifier_email"];
$user_password = $_POST["identifier_password"];
$user_repeat_password = $_POST["identifier_repeat_password"];

$query = 
"INSERT INTO doctor(social_security_number , name, last_name) 
VALUES 
('$user_email','$user_password','$user_repeat_password')";

if(mysqli_query($conn,$query)){
	echo "<h2>Data Successfully Inserted!</h2>";
}
else{
	echo "<h2>Data was unable to be inserted into database :(.</h2>";
}
?>