<?php

$mysqlConexion = new mysqli("localhost","root","","android_mysql");

if($mysqlConexion->connect_errno){

    die ("Error de conexion");

}