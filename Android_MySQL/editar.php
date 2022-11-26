<?php

if($_SERVER["REQUEST_METHOD"] == "POST"){

    require_once 'conexion.php';

    $id = $_POST['id'];
    $nombre = $_POST["nombre"];
    $email = $_POST["email"];
    $telefono = $_POST["telefono"];
    $pass = $_POST["pass"];


    $consulta = "UPDATE usuarios SET 
    nombre='$nombre',
    email='$email',
    telefono='$telefono',
    pass='$pass' WHERE id = '$id'";
    $resultado = $mysqlConexion -> query($consulta);

    if($mysqlConexion-> affected_rows > 0){

       echo "exitp";
    }else{
        echo "No hay registro";
    }

    



}