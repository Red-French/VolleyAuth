<?php
    $con = mysqli_connect("my_host", "my_user", "my_password", "my_database");
    $con = mysqli_connect("localhost", "id1157462_myunclefatty", "000webhost2017", "id1157462_authentication");

    $name = $_POST["name"];  // Android wll pass name, age, username, and password
    $age = $_POST["age"];
    $username = $_POST["username"];
    $password = $_POST["password"];

    // insert info above into the table
    $statement = mysqli_prepare($con, "INSERT INTO user (name, username, age, password) VALUES (?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "siss", $name, $username, $age, $password);
    mysqli_stmt_execute($statement);

    $response = array();
    $response["success"] = true;

    echo json_encode($response);  // gets turn into JSONobject in RegisterActivity.java
?>
