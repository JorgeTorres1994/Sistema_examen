package Proyecto;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    Connection con;
    String url = "jdbc:mysql://localhost:3306/SistemaExamen"; // Cambia "nombre_basedatos" por el nombre de tu base de datos MySQL
    String user = "root"; // Cambia "usuario_mysql" por el nombre de usuario de tu MySQL
    String pass = ""; // Cambia "contraseña_mysql" por la contraseña de tu MySQL
    
    public Connection conectar(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Para MySQL 8.x y versiones posteriores, se utiliza "com.mysql.cj.jdbc.Driver"
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexión exitosa");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
