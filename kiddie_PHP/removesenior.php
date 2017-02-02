<?php
include_once("common.php"); 

if(isset($_REQUEST["delid"]))
{
$id=$_REQUEST["delid"];
$q="delete from tblSenior where intSeniorID =$id ";


$qr=mysqli_query($con,$q) or die(mysqli_error($con));

print(json_encode("Senior Deleted"));
}
?>