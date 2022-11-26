<?php

if($_SERVER["REQUEST_METHOD"] == "POST"){

    require_once 'conexion.php';

    $id = $_POST['id'];

    $consulta = "DELETE FROM usuarios WHERE id = '".$id."'";
    $resultado = $mysqlConexion -> query($consulta);

    if($mysqlConexion-> affected_rows > 0){

       echo "exitp";
    }else{
        echo "No hay registro";
    }

    



}