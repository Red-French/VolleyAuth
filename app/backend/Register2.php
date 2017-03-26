<?php
    require("password.php");
    // $connect = mysqli_connect("my_host", "my_user", "my_password", "my_database");
    $connect = mysqli_connect("localhost", "id1157462_myunclefatty", "000webhost2017", "id1157462_authentication");

    $name = $_POST["name"];
    $username = $_POST["username"];
    $password = $_POST["password"];
    $age = $_POST["age"];

     function registerUser() {
        global $connect, $name, $username, $password, $age;
        $passwordHash = password_hash($password, PASSWORD_DEFAULT);
        $statement = mysqli_prepare($connect, "INSERT INTO user (name, username, password, age) VALUES (?, ?, ?, ?)");
        mysqli_stmt_bind_param($statement, "sssi", $name, $username, $passwordHash, $age);
        mysqli_stmt_execute($statement);
        mysqli_stmt_close($statement);
    }
    function usernameAvailable() {
        global $connect, $username;
        $statement = mysqli_prepare($connect, "SELECT * FROM user WHERE username = ?");
        mysqli_stmt_bind_param($statement, "s", $username);
        mysqli_stmt_execute($statement);
        mysqli_stmt_store_result($statement);
        $count = mysqli_stmt_num_rows($statement);
        mysqli_stmt_close($statement);
        if ($count < 1){
            return true;
        }else {
            return false;
        }
    }
    $response = array();
    $response["success"] = false;
    if (usernameAvailable()){
        registerUser();
        $response["success"] = true;
    }

    echo json_encode($response);
?>
