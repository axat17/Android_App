<?php
include_once("common.php"); 
if(isset($_REQUEST["dnm"]))
{
$dnm=$_REQUEST["dnm"];
$dlic=$_REQUEST["lic"];
$sage=$_REQUEST["age"];
$mid =$_REQUEST["mid"];
$gen= $_REQUEST["gen"];
$pic=$_REQUEST["pic"];

$q="select * from tblLogin where intLoginID ='".$mid."'";

$qr=mysqli_query($con,$q) or die(mysqli_error($con));
$res=mysqli_fetch_assoc($qr);
$mob= $res["strMobileNo"];


$q="Insert into tblSenior(strSeniorName,strGender,intAge,strPickupStand,strLicenceNo,strMobileNo) values ('$dnm','$gen','$sage','$pic','$dlic','$mob')";
echo $q;
$qr=mysqli_query($con,$q) or die(mysqli_error());


echo "Senior Successfully Inserted";


}
else
echo "Invalid Request";
?>