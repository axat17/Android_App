<?php
include_once("common.php");
$err="Not A Proper Request";
if(isset($_REQUEST["Submit"]))
{
$un=$_REQUEST["txtUserName"];
$ps=md5($_REQUEST["txtPassword"]);

$q="update tblLogin  set strPassword ='".$ps."' where strMobileNo ='".$un."' ";

if(mysqli_query($con,$q) or die(mysqli_error($con)))
{
echo "Password Changed";
}
else
{
echo "Change Failed";
}


}

?>