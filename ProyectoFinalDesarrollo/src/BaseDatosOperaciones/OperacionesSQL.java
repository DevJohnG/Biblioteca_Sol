package BaseDatosOperaciones;


import BaseDatosConexion.DataBaseConnection;
import TablasBaseDatos.TablaLibros;
import TablasBaseDatos.TablaUsuarios;
import TablasBaseDatos.TablaPrestamos;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.LinkedList;


/*
*
*
*       OperacionesSQL : Class
*
*
*       Esta clase contiene toda las funcionalidades del programa. Contiene los metodos de consulta que utilizara el
*       programador y el usuario.
*
* */

public class OperacionesSQL {

    private Statement statement;
    private ResultSet resultSet;


    public boolean verificarUsuario(DataBaseConnection dataBaseConnection, int id) throws Exception { //Método que verifica si el usuario existe

        Connection connection;

        try {

            connection = dataBaseConnection.connectionStablish();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Usuario where idUsuario =" + id);

            if (resultSet.next()) {

                TablaUsuarios tu = new TablaUsuarios();

                tu.setIdUsuario(resultSet.getInt("idUsuario"));
                tu.setNombre(resultSet.getString("nombre"));
                tu.setApellido(resultSet.getString("apellido"));
                tu.setDireccion(resultSet.getString("direccion"));
                tu.setTelefono(resultSet.getString("telefono"));
                tu.setCorreoElectronico(resultSet.getString("correoElectronico"));

            } else {
                connection.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("\nError....Ocurrio un problema al momento de realizar la consulta en la tabla Usuarios." +
                    " Consulta : [" + e.getCause() + "]");
        }

        return false;
    }

    public boolean verificarLibro(DataBaseConnection dataBaseConnection, int id) throws Exception { //Método que verifica que los Libro exista en la Base de Datos

        Connection connection;

        try {

            connection = dataBaseConnection.connectionStablish();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Libros where idLibro =" + id);

            if (resultSet.next()) {

                TablaLibros tl = new TablaLibros();

                tl.setIdLibro(resultSet.getInt("idLibro"));
                tl.setTitulo(resultSet.getString("titulo"));
                tl.setAutor(resultSet.getString("autor"));
                tl.setGenero(resultSet.getString("genero"));
                tl.setISBN(resultSet.getString("ISBN"));
                tl.setFechaPublicacion(resultSet.getString("fechaPublicacion"));
                tl.setEditorial(resultSet.getString("editorial"));

            } else {
                connection.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("\nError....Ocurrio un problema al momento de realizar la consulta en la tabla Usuarios." +
                    " Consulta : [" + e.getCause() + "]");
        }

        return false;
    }

    public void insertarTablaUsuarios(DataBaseConnection dataBaseConnection, int id, String nom, String apellido
            , String direccion, String tel, String correo) throws Exception { //Método que inserta los Usuarios en la base de datos

        Connection connection;

        try {

            connection = dataBaseConnection.connectionStablish();
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO Usuario (idUsuario,nombre,apellido,direccion,telefono,correoElectronico)" +
                    " VALUES(" + id + ",'" + nom + "','" + apellido + "','" + direccion + "','" + tel + "','" + correo + "')");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("\nError....Ocurrio un problema al momento de realizar la consulta en la tabla Usuarios." +
                    " Consulta : [" + e.getCause() + "]");
        }
    }

    public void insertarTablaLibros(DataBaseConnection dataBaseConnection, int id, String titulo, String autor
            , String genero, String ISBN, String fecha, String editorial) throws Exception { //Método que inserta los libros en la Base de Datos

        Connection connection;

        try {

            connection = dataBaseConnection.connectionStablish();
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO Libros (idLibro,titulo,autor,genero,ISBN,fechaPublicacion,editorial)" +
                    " VALUES(" + id + ",'" + titulo + "','" + autor + "','" + genero + "','" + ISBN + "','" + fecha + "','" + editorial + "')");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("\nError....Ocurrio un problema al momento de realizar la consulta en la tabla Usuarios." +
                    " Consulta : [" + e.getCause() + "]");
        }
    }

    public void insertarTablaPrestamos(DataBaseConnection dataBaseConnection, int id, String fechaPrest, int idLibro, int idUser
            , String fechaDev) throws Exception { //Método que inserta datos a la tabla de los Préstamos

        Connection connection;

        try {

            connection = dataBaseConnection.connectionStablish();
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO Prestamos (idPrestamo,fechaPrestamo,idLibro,idUsuario,fechaDevolucion)" +
                    " VALUES(" + id + ",'" + fechaPrest + "'," + idLibro + "," + idUser + ",'" + fechaDev + "')");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("\nError....Ocurrio un problema al momento de realizar la consulta en la tabla Usuarios." +
                    " Consulta : [" + e.getCause() + "]");
        }
    }

    public boolean verificarDisponibilidad(DataBaseConnection dataBaseConnection, int id) throws Exception { //Método que verifica si los libros no están prestados a otros usuarios

        Connection connection;

        try {

            connection = dataBaseConnection.connectionStablish();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Prestamos where idLibro =" + id);

            if (resultSet.next()) {

                TablaPrestamos tp = new TablaPrestamos();

                tp.setIdPrestamo(resultSet.getInt("idPrestamo"));
                tp.setFechaPrestamo(resultSet.getString("fechaPrestamo"));
                tp.setIdLibro(resultSet.getInt("idLibro"));
                tp.setIdUsuario(resultSet.getInt("idUsuario"));
                tp.setFechaDevolucion(resultSet.getString("fechaDevolucion"));

            } else {
                connection.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("\nError....Ocurrio un problema al momento de realizar la consulta en la tabla Usuarios." +
                    " Consulta : [" + e.getCause() + "]");
        }

        return false;
    }

    public boolean verificarPrestamo(DataBaseConnection dataBaseConnection, int id) throws Exception { //Método que verifica si un usuario tiene un préstamo activo

        Connection connection;

        try {

            connection = dataBaseConnection.connectionStablish();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Prestamos where idUsuario =" + id);

            if (resultSet.next()) {

                TablaPrestamos tp = new TablaPrestamos();

                tp.setIdPrestamo(resultSet.getInt("idPrestamo"));
                tp.setFechaPrestamo(resultSet.getString("fechaPrestamo"));
                tp.setIdLibro(resultSet.getInt("idLibro"));
                tp.setIdUsuario(resultSet.getInt("idUsuario"));
                tp.setFechaDevolucion(resultSet.getString("fechaDevolucion"));

            } else {
                connection.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("\nError....Ocurrio un problema al momento de realizar la consulta en la tabla Usuarios." +
                    " Consulta : [" + e.getCause() + "]");
        }

        return false;
    }

    public boolean verificarPrestamo(DataBaseConnection dataBaseConnection, int id, int idLibro) throws Exception { //Método Sobrecargado que verifica si el usuario tiene en posesión un libro en específico

        Connection connection;

        try {

            connection = dataBaseConnection.connectionStablish();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Prestamos where idUsuario =" + id + " and idLibro = " + idLibro);

            if (resultSet.next()) {

                TablaPrestamos tp = new TablaPrestamos();

                tp.setIdPrestamo(resultSet.getInt("idPrestamo"));
                tp.setFechaPrestamo(resultSet.getString("fechaPrestamo"));
                tp.setIdLibro(resultSet.getInt("idLibro"));
                tp.setIdUsuario(resultSet.getInt("idUsuario"));
                tp.setFechaDevolucion(resultSet.getString("fechaDevolucion"));

            } else {
                connection.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("\nError....Ocurrio un problema al momento de realizar la consulta en la tabla Usuarios." +
                    " Consulta : [" + e.getCause() + "]");
        }

        return false;
    }

    public void ActualizarDevolucion(DataBaseConnection dataBaseConnection, int id, int idBook) throws Exception { //Método que actualiza la tabla prestamos, eliminando el prestamo anterior

        Connection connection;

        try {

            connection = dataBaseConnection.connectionStablish();
            statement = connection.createStatement();
            statement.executeUpdate("Delete from Prestamos where idUsuario = " + id + " and idLibro = " + idBook);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("\nError....Ocurrio un problema al momento de realizar la consulta en la tabla Usuarios." +
                    " Consulta : [" + e.getCause() + "]");
        }
    }

    public int ContarPrestamos(DataBaseConnection dataBaseConnection) throws Exception { //Método que cuenta los prestamos totales en la tabla Préstamos
        int n = 0;
        Connection connection;

        try {

            connection = dataBaseConnection.connectionStablish();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT count(idPrestamo) as 'Préstamos Totales' FROM Prestamos");

            resultSet.next();
            n = (resultSet.getInt("Préstamos Totales"));

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("\nError....Ocurrio un problema al momento de realizar la consulta en la tabla Usuarios." +
                    " Consulta : [" + e.getCause() + "]");
        }
        return n;
    }

    public LinkedList<TablaLibros> AbstraerPopulares(DataBaseConnection dataBaseConnection) throws Exception { //Método que ordena los libros según su popularidad

        Connection connection;
        LinkedList<TablaLibros> tablaLibros = new LinkedList<>();

        try {

            connection = dataBaseConnection.connectionStablish();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT top (10) * FROM Libros");

            while (resultSet.next()) {

                TablaLibros tl = new TablaLibros();

                tl.setIdLibro(resultSet.getInt("idLibro"));
                tl.setTitulo(resultSet.getString("titulo"));
                tl.setAutor(resultSet.getString("autor"));
                tl.setGenero(resultSet.getString("genero"));
                tl.setISBN(resultSet.getString("ISBN"));
                tl.setFechaPublicacion(resultSet.getString("fechaPublicacion"));
                tl.setEditorial(resultSet.getString("editorial"));
                tablaLibros.add(tl);

            }

            connection.close();
            return tablaLibros;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("\nError....Ocurrio un problema al momento de realizar la consulta en la tabla Libros." +
                    " Consulta : [" + e.getCause() + "]");
        }

    }

    public LinkedList<TablaUsuarios> contarUsuarios(DataBaseConnection dataBaseConnection) throws Exception { //Método que returna los usuarios con más préstamos

        Connection connection;
        try {
            connection = dataBaseConnection.connectionStablish();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT top (5) U.idUsuario, U.nombre, U.apellido, COUNT(*) AS NoPrestamos FROM Usuario U JOIN Prestamos P ON U.idUsuario = P.idUsuario GROUP BY U.idUsuario, U.nombre, U.apellido ORDER BY COUNT(*) DESC");

            LinkedList<TablaUsuarios> topUsers = new LinkedList<>();
            int count = 0;
            while (resultSet.next() && count < 5) {
                TablaUsuarios topusers = new TablaUsuarios();
                topusers.setIdUsuario(resultSet.getInt("idUsuario"));
                topusers.setNombre(resultSet.getString("nombre"));
                topusers.setApellido(resultSet.getString("apellido"));
                topUsers.add(topusers);
                count++;
            }
            statement.close();
            connection.close();
            return topUsers;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new Exception("A ocurrido un error... ", sqle);
        }
    }

    public LinkedList<TablaLibros> consultaLibroAutor(DataBaseConnection dataBaseConnection, String autor) throws Exception { //Método que returna una lista enlazada con los datos de los libros según su autor

        Connection connection;
        LinkedList<TablaLibros> tablaLibros = new LinkedList<>();

        try {

            connection = dataBaseConnection.connectionStablish();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT* FROM Libros where autor like '" + autor + "%'");

            while (resultSet.next()) {

                TablaLibros tl = new TablaLibros();

                tl.setIdLibro(resultSet.getInt("idLibro"));
                tl.setTitulo(resultSet.getString("titulo"));
                tl.setAutor(resultSet.getString("autor"));
                tl.setGenero(resultSet.getString("genero"));
                tl.setISBN(resultSet.getString("ISBN"));
                tl.setFechaPublicacion(resultSet.getString("fechaPublicacion"));
                tl.setEditorial(resultSet.getString("editorial"));
                tablaLibros.add(tl);

            }

            connection.close();
            return tablaLibros;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("\nError....Ocurrio un problema al momento de realizar la consulta en la tabla Libros." +
                    " Consulta : [" + e.getCause() + "]");
        }
    }

    public LinkedList<TablaLibros> consultaLibroTitulo(DataBaseConnection dataBaseConnection, String titulo) throws Exception { //Método que returna una lista enlazada con datos de los libros según su título

        Connection connection;
        LinkedList<TablaLibros> tablaLibros = new LinkedList<>();

        try {

            connection = dataBaseConnection.connectionStablish();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT* FROM Libros where titulo like '" + titulo + "%'");

            while (resultSet.next()) {

                TablaLibros tl = new TablaLibros();

                tl.setIdLibro(resultSet.getInt("idLibro"));
                tl.setTitulo(resultSet.getString("titulo"));
                tl.setAutor(resultSet.getString("autor"));
                tl.setGenero(resultSet.getString("genero"));
                tl.setISBN(resultSet.getString("ISBN"));
                tl.setFechaPublicacion(resultSet.getString("fechaPublicacion"));
                tl.setEditorial(resultSet.getString("editorial"));
                tablaLibros.add(tl);

            }

            connection.close();
            return tablaLibros;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("\nError....Ocurrio un problema al momento de realizar la consulta en la tabla Libros." +
                    " Consulta : [" + e.getCause() + "]");
        }
    }


    public LinkedList<TablaLibros> consultaLibroGenero(DataBaseConnection dataBaseConnection, String genero) throws Exception { //Método que returna una lista enlazada con datos de los libros según su Género

        Connection connection;
        LinkedList<TablaLibros> tablaLibros = new LinkedList<>();

        try {

            connection = dataBaseConnection.connectionStablish();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT* FROM Libros where genero like '" + genero + "%'");

            while (resultSet.next()) {

                TablaLibros tl = new TablaLibros();

                tl.setIdLibro(resultSet.getInt("idLibro"));
                tl.setTitulo(resultSet.getString("titulo"));
                tl.setAutor(resultSet.getString("autor"));
                tl.setGenero(resultSet.getString("genero"));
                tl.setISBN(resultSet.getString("ISBN"));
                tl.setFechaPublicacion(resultSet.getString("fechaPublicacion"));
                tl.setEditorial(resultSet.getString("editorial"));
                tablaLibros.add(tl);

            }

            connection.close();
            return tablaLibros;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("\nError....Ocurrio un problema al momento de realizar la consulta en la tabla Libros." +
                    " Consulta : [" + e.getCause() + "]");
        }

    }

    public LinkedList<TablaUsuarios> consultaDatos(DataBaseConnection dataBaseConnection, int id) throws Exception { //Método que returna los datos de los usuarios que tienen prestamos a devolver para su notificación

        Connection connection;
        LinkedList<TablaUsuarios> tablaUsuarios = new LinkedList<>();

        try {

            connection = dataBaseConnection.connectionStablish();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT nombre,telefono,correoElectronico FROM Usuario where idUsuario ="+id);

            while (resultSet.next()) {

                TablaUsuarios tu = new TablaUsuarios();

                tu.setNombre(resultSet.getString("nombre"));
                tu.setTelefono(resultSet.getString("telefono"));
                tu.setCorreoElectronico(resultSet.getString("correoElectronico"));
                tablaUsuarios.add(tu);
            }

            connection.close();
            return tablaUsuarios;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("\nError....Ocurrio un problema al momento de realizar la consulta en la tabla Usuario." +
                    " Consulta : [" + e.getCause() + "]");
        }

    }
}

