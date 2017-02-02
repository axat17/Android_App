<?php
include_once("common.php");
$err="Not A Proper Request";
if(isset($_REQUEST["Submit"]))
{
	$lid=$_REQUEST["intLoginID"];
	
	$q="select * from tblDriver where intLoginID=".$lid;
	$qr=mysqli_query($con,$q) or die(mysqli_error($con));
	if($res=mysqli_fetch_assoc($qr))
	{
	print(json_encode($res));
	}
	
}
else
echo $err;
?>