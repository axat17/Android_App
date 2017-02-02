<?php
include_once("common.php");
$err="Not A Proper Request";
if(isset($_REQUEST["Submit"]))
{
$un=$_REQUEST["txtUserName"];

$q="select * from tblLogin where strMobileNo ='".$un."'";

$qr=mysqli_query($con,$q) or die(mysqli_error($con));

if($res=mysqli_fetch_assoc($qr))
{
	$ut=$res["strUserType"];
	$lid=$res["intLoginID"];
	if($ut=="Driver")
	{
	$q="select intDriverID from tblDriver where intLoginID=".$lid;
	$qr=mysqli_query($con,$q) or die(mysqli_error($con));
	if($res=mysqli_fetch_assoc($qr))
	{
	$err="Driver:$lid";
	}
	else
	{
	$err="Invalid Details, No Driver Founded";
	}
	//header("location:adminhome.php");
	}
	else if($ut=="Parent")
	{
		$err="Parent:$lid";
	//header("location:branchhome.php");
	}
	else
	{
	$err="Invalid Details";
	}
}
else
{
	$err="Invalid Details";
}

}
echo $err;
?>