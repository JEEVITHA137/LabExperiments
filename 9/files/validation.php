<?php
$value = $_GET['query'];
$formfield = $_GET['field'];
if ($formfield == "username") {
if (strlen($value) < 7) {
echo "must be 6 characters";
} else {
echo "<span>Valid Name</span>";
}
}
if ($formfield == "password") {
if (strlen($value) < 7) {
echo "must be 6 characters";
} else {
echo "<span>Valid password</span>";
}
}
?>