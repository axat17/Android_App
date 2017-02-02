<h2>Parent Details</h2>
<?php
include_once("common.php");

if(isset($_REQUEST["delid"]))
{
	$did=$_REQUEST["delid"];


$q="delete from tblLogin where intLoginID = (Select intLoginID from tblParent where intParentID=$did)";
mysqli_query($con,$q);

	$q="delete from tblParent where intParentID=$did";
	mysqli_query($con,$q);



}


$q="select * from tblParent";

$qr=mysqli_query($con,$q) or die(mysqli_error($con));
?>


<table border='1'>
<tr>
<th>Name</th>
<th>Address</th>
<th>Phone No </th>

<?php
while($res=mysqli_fetch_assoc($qr))
{
?>
<tr>
	<td><?php echo $res["strFullName"]; ?></td>
	<td><?php echo $res["strAddress"]; ?></td>
	<td><?php echo $res["strPhoneNo"]; ?></td>
	<td><a href='viewparents.php?delid=<?php echo $res["intParentID"]; ?>' onclick="return confirm('Sure Want to Delete?')">Remove</a></td>

</tr>
<?php	
}

?>
<table>