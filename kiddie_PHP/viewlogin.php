<h2>Driver Details</h2>
<?php
include_once("common.php");


$q="select * from tblLogin";

$qr=mysqli_query($con,$q) or die(mysqli_error($con));
?>


<table border='1'>
<tr>
<th>Login ID</th>
<th>Phone No</th>
<th>Password</th>
<th>User Type</th>

<?php
while($res=mysqli_fetch_assoc($qr))
{
?>
<tr>
	<td><?php echo $res["intLoginID"]; ?></td>
	<td><?php echo $res["strMobileNo"]; ?></td>
	<td><?php echo $res["strPassword"]; ?></td>
	<td><?php echo $res["strUserType"]; ?></td>

</tr>
<?php	
}

?>
<table>