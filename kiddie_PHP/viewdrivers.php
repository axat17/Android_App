<h2>Driver Details</h2>
<?php
include_once("common.php");

if(isset($_REQUEST["delid"]))
{
	$did=$_REQUEST["delid"];


$q="delete from tblLogin where intLoginID = (Select intLoginID from tblDriver where intDriverID =$did)";
mysqli_query($con,$q);

	$q="delete from tblDriver where intDriverID =$did";
	mysqli_query($con,$q);



}

$q="select * from tblDriver";

$qr=mysqli_query($con,$q) or die(mysqli_error($con));
?>


<table border='1'>
<tr>
<th>Driver Name</th>
<th>School Name</th>
<th>Licence No </th>
<th>Phone No </th>

<?php
while($res=mysqli_fetch_assoc($qr))
{
?>
<tr>
	<td><?php echo $res["strDriverName"]; ?></td>
	<td><?php echo $res["strSchoolName"]; ?></td>
	<td><?php echo $res["strLicenceNo"]; ?></td>
	<td><?php echo $res["strPhoneNo"]; ?></td>
	<td><a href='viewdrivers.php?delid=<?php echo $res["intDriverID"]; ?>' onclick="return confirm('Sure Want to Delete?')">Remove</a></td>
</tr>
<?php	
}

?>
<table>