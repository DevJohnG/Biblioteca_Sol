package BaseDatosConexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 *      DataBaseConnection : Class
 *
 *
 *      La clase DataBaseConnection se encarga se establecer la conexion entre la aplicacion de Java y el gesto de
 *      Base de Datos SQL Server.
 *
 * */

public class DataBaseConnection {


    private Connection connection;

    private final String USER_BD = "des";
    private final String PASS_BD = "1234";

    public static DataBaseConnection getConnection() {
        return null;
    }


    public Connection connectionStablish() throws Exception { //Método que conecta a la Base de Datos

        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost;database=BibliotecaDesarollo;TrustServerCertificate=true;logintimeout=1"
                    ,"des","1234");
            return connection;
        }

        catch (ClassNotFoundException e) {
            throw new Exception("\nError....Por favor revise el driver introducido. || Driver : [" + e.getCause() + "]");
        }

        catch (SQLException e) {
            throw new Exception("\nError....No se pudo establecer la conexion. || Conexion : [" + e.getMessage() + "]");
        }
    }
    public boolean Administrar(String u,String c){ //Método que comprueba si el usuario y contraseña están correctos

        if(u.equals(USER_BD) && c.equals(PASS_BD))
            return true;
        else
            return false;
    }
}

