<?php
include_once("common.php");
$err="Not A Proper Request";
if(isset($_REQUEST["Submit"]))
{

$lid= $_REQUEST["intLoginID"];
$nm =$_REQUEST["strName"];

$sch=$_REQUEST["strSchool"];
$lic=$_REQUEST["strLic"];
$adr=$_REQUEST["strAddress"];
$phn=$_REQUEST["strPhone"];

$q="update tblLogin set strMobileNo ='".$phn."' where intLoginID =".$lid;
mysqli_query($con,$q) or die(mysqli_error($con));


$q="update tblDriver set  strDriverName ='".$nm."', strAddress ='".$adr."', strPhoneNo ='".$phn."', strLicenceNo ='".$lic."', strSchoolName ='".$sch."' where intLoginID =".$lid;


mysqli_query($con,$q) or die(mysqli_error($con));

echo "Profile Updated";
}

?>