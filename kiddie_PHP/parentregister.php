<?php
include_once("common.php"); 
if(isset($_REQUEST["dnm"]))
{
$dnm=$_REQUEST["dnm"];
$dmob=$_REQUEST["mob"];
$dps=md5($_REQUEST["dps"]);
$ut="Parent";
$dad=$_REQUEST["dad"];

$q="select * from tblLogin where strMobileNo ='".$dmob."'";

$qr=mysqli_query($con,$q) or die(mysqli_error($con));

if($res=mysqli_fetch_assoc($qr))
{
echo "Mobile No Already Exists";
}
else
{
$q="Insert into tblLogin(strMobileNo,strPassword,strUserType) values ('$dmob','$dps','$ut')";

$qr=mysqli_query($con,$q) or die(mysqli_error());

$lid=mysqli_insert_id($con);

$q="Insert into tblParent (intLoginID,strFullName,strAddress,strPhoneNo) values ('$lid','$dnm','$dad','$dmob')";

//echo $q;
$qr=mysqli_query($con,$q) or die(mysqli_error());
$lid=mysqli_insert_id($con);

echo "Regitration Success ID is :$lid";
}

}
else
echo "Invalid Request";
?>