<?php
require "connection.php";

$user_email = $_POST["identifier_login_email"];
$user_pass = $_POST["identifier_login_password"];

$mysql_query = 
"SELECT * 
FROM doctor 
WHERE name = '$user_email'
AND last_name = '$user_pass'";

$result = mysqli_query($conn,$mysql_query);

//check the result
if(mysqli_num_rows($result)>0){
	$row = mysqli_fetch_assoc($result);
	$name = $row["name"];
	$email = $row["last_name"];
	
	//Here is the specially formatted string response.. ie: "ServerResponse".
	//It is of the form: "boolean,email,name"
	echo "true,".$email.",".$name;
}
else{
	echo "Login was not successful... :(";
}
?>