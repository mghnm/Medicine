<?
$servername = "ns1.gear.host";
$username = "da216agroup15";
$dbname = "da216agroup15";
$password = "<da216a>";
//Create connection
$conn = mysqli_connect($servername, $username, $password, $dbname);
//Check connection
if(!$conn){
	die("Connectioned failed!" . mysqli_connect_error());
}
else{
	echo "Connect success!";
}
?>
