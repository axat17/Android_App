<?php
include_once("common.php");
$err="Not A Proper Request";
if(isset($_REQUEST["Submit"]))
{
$lid=$_REQUEST["txtLoginID"];
$ops=md5($_REQUEST["txtPassword"]);
$nps=md5($_REQUEST["txtNewPass"]);

$q="select * from tblLogin where intLoginID ='".$lid."' and strPassword= '".$ops."' ";

$qr=mysqli_query($con,$q) or die(mysqli_error($con));
if($res=mysqli_fetch_assoc($qr))
{
$q="update tblLogin  set strPassword ='".$nps."' where intLoginID ='".$lid."' and strPassword= '".$ops."' ";

if(mysqli_query($con,$q) or die(mysqli_error($con)))
{
echo "Password Changed";
}
else
{
echo "Change Failed";
}

}
else
{
echo "Change Failed";
}

}

?>