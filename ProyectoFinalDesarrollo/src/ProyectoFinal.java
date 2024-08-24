/*  INTEGRANTES
 *
 *   Michael Gay 8-996-1689, John Grant 8-983-1525, Abdiel Mendoza 3-749-1302,
 *   Minerva Garcia 8-1006-89, Neil Marin 8-1002-320, Oscar Lorenzo 8-1006-610.
 *
 *
 * */


import BaseDatosConexion.DataBaseConnection;
import TablasBaseDatos.TablaLibros;
import TablasBaseDatos.TablaUsuarios;
import TablasBaseDatos.TablaPrestamos;
import BaseDatosOperaciones.OperacionesSQL;
import java.io.*;
import java.util.LinkedList;

/*
 *
 *       ProyectoFinal : Class
 *
 *       Esta es la clase que maneja al metodo main.
 *
 * */

public class ProyectoFinal {

    public static void main(String[] args) {

        //Declaración de objetos
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        TablaLibros tl = new TablaLibros();
        TablaUsuarios tu = new TablaUsuarios();
        TablaPrestamos tp = new TablaPrestamos();
        OperacionesSQL ops = new OperacionesSQL();

        //Declaración de Listas Enlazadas
        LinkedList<TablaLibros> listalibros = new LinkedList<>();
        LinkedList<TablaUsuarios> listausuario = new LinkedList<>();
        LinkedList<TablaPrestamos> listaprestamo = new LinkedList<>();
        LinkedList<Integer> compararID = new LinkedList<>();

        //Declaración de variables;
        String masterUser = "";
        String masterPassword = "";
        int op = 0;
        boolean leido = true;

        //INICIO DEL MENÚ

        do {

            //Menu principal
            System.out.println("\nBienvenido a Biblioteca Sol");
            System.out.println("\nElija una opción:");
            System.out.println("1.Administrador.");
            System.out.println("2.Usuario.");
            System.out.println("3.Salir.");

            do {

                try {

                    leido = true;
                    System.out.println("\nIngrese la opcion que desea utilizar:");
                    op = Integer.parseInt(br.readLine());
                    if (op <= 0) {
                        System.out.println("\nError.....La opcion no puede ser negativa.");
                        leido = false;
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    leido = false;
                }

            } while (!leido);


            switch (op) {

                case 1:

                    //Modo administrador
                    System.out.println("\nBienvenido al Modo Administrador:");

                    do {

                        try {

                            System.out.println("\nIngrese el usuario:");
                            masterUser = br.readLine();
                            if (masterUser.equals("")) {
                                System.out.println("\nError....Su dato no puede estar vacio.");
                                leido = false;
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            leido = false;
                        }

                        try {

                            leido = true;
                            System.out.println("\nIngrese la constraseña:");
                            masterPassword = br.readLine();
                            if (masterUser.equals("")) {
                                System.out.println("\nError....Su dato no puede estar vacio.");
                                leido = false;
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            leido = false;
                        }

                        if (!dataBaseConnection.Administrar(masterUser, masterPassword)) {
                            System.out.println("\nError...Usuario o Constraseña incorrecta.");
                        }

                    } while (!dataBaseConnection.Administrar(masterUser, masterPassword));


                    do {
                        // SUBMENÚ DEL ADMINISTRADOR

                        System.out.println("\nOpciones del Administrador:");
                        System.out.println("\n1. Ingresar Libros Nuevos.");
                        System.out.println("2. Registrar Prestamos.");
                        System.out.println("3. Registrar Devoluciones.");
                        System.out.println("4. Informes.");
                        System.out.println("5. Notificar Devolución.");
                        System.out.println("6. Salir del Modo Administrador.");
                        do {

                            try {

                                leido = true;
                                System.out.println("\nEscoja la opcion que desea utilizar:");
                                op = Integer.parseInt(br.readLine());
                                if (op <= 0) {
                                    System.out.println("\nError....Opcion Incorrecta.");
                                    leido = true;
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                                leido = true;
                            }

                        } while (!leido);


                        switch (op) {

                            case 1: //Ingresar Libros a la base de datos
                                do {
                                    try {
                                        leido = true;
                                        System.out.println("\nIngrese el ID del libro (digite '0' para salir)");
                                        tl.setIdLibro(Integer.parseInt(br.readLine()));
                                        if(tl.getIdLibro() == 0){
                                            leido = true;
                                            break;
                                        }
                                        if (!ops.verificarLibro(dataBaseConnection, tl.getIdLibro())) {
                                            System.out.println("\nError....Ya está registrado este ID de libro" +
                                                    ". Por favor ingrese otro dato.");
                                            leido = false;
                                        }
                                    } catch (Exception e) {
                                        System.out.println("\nError...Digite correctamente el ID, segun las indicaciones");
                                        leido = false;
                                    }
                                } while (!leido);
                                do {
                                    try {
                                        leido = true;
                                        System.out.println("\nIngrese el título");
                                        tl.setTitulo(br.readLine());
                                        if(tl.getTitulo() == "0"){
                                            leido = true;
                                            break;
                                        }
                                    } catch (Exception e) {
                                        System.out.println("\nError...Digite correctamente el Titulo del libro. No puede contener simbolos númericos");
                                        leido = false;
                                    }
                                } while (!leido);

                                do {
                                    try {
                                        leido = true;
                                        System.out.println("\nIngrese el autor");
                                        tl.setAutor(br.readLine());
                                        if(tl.getAutor() == "0"){
                                            leido = true;
                                            break;
                                        }
                                    } catch (Exception e) {
                                        System.out.println("\nError...Digite correctamente el nombre del autor. No puede contener simbolos númericos");
                                        leido = false;
                                    }
                                } while (!leido);

                                do {
                                    try {
                                        leido = true;
                                        System.out.println("\nIngrese el género");
                                        tl.setGenero(br.readLine());
                                        if(tl.getGenero() == "0"){
                                            leido = true;
                                            break;
                                        }
                                    } catch (Exception e) {
                                        System.out.println("\nError...Digite correctamente el Genero. No puede contener simbolos númericos");
                                        leido = false;
                                    }
                                } while (!leido);

                                do {
                                    try {
                                        leido = true;
                                        System.out.println("\nIngrese el código ISBN");
                                        tl.setISBN(br.readLine());
                                        if(tl.getISBN() == "0"){
                                            leido = true;
                                            break;
                                        }
                                    } catch (Exception e) {
                                        System.out.println("\nError...Digite correctamente el ISBN.");
                                        leido = false;
                                    }
                                } while (!leido);

                                do {
                                    try {
                                        leido = true;
                                        System.out.println("\nIngrese la fecha de publicación en formato dd/mm/aaaa (digite los '/')");
                                        tl.setFechaPublicacion(br.readLine());
                                        if(tl.getFechaPublicacion() == "0"){
                                            leido = true;
                                            break;
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Error: " + e);
                                        leido = false;
                                    }
                                } while (!leido);
                                do {
                                    try {
                                        leido = true;
                                        System.out.println("\nIngrese la editorial");
                                        tl.setEditorial(br.readLine());
                                        if(tl.getEditorial() == "0"){
                                            leido = true;
                                            break;
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Error: " + e);
                                        leido = false;
                                    }
                                } while (!leido);

                                try {

                                    ops.insertarTablaLibros(dataBaseConnection,tl.getIdLibro(),tl.getTitulo()
                                            ,tl.getAutor(),tl.getGenero(),tl.getISBN(),tl.getFechaPublicacion(),tl.getEditorial());
                                    System.out.println("\nSe ingresaron sus datos correctamente en la base de datos.");

                                }

                                catch (Exception e) {
                                    System.out.println(e);
                                }
                                leido = false;
                                break;

                            case 2: //Ingresar préstamos a la Base de datos
                                do {
                                    try {
                                        leido = true;
                                        System.out.println("\nIngrese el ID del préstamo (digite '0' para regresar)");
                                        tp.setIdPrestamo(Integer.parseInt(br.readLine()));
                                        if(tp.getIdPrestamo() == 0){
                                            leido = true;
                                            break;
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Error: " + e);
                                        leido = false;
                                    }
                                } while (!leido);
                                do {
                                    try {
                                        leido = true;
                                        System.out.println("\nIngrese la fecha del préstamo en formato dd/mm/aaaa (digite los '/')");
                                        tp.setFechaPrestamo(br.readLine());
                                        if(tp.getFechaPrestamo() == "0"){
                                            leido = true;
                                            break;
                                        }
                                    } catch (Exception e) {
                                        System.out.println("\nError...Digite correctamente la fecha, Segun el formato dado");
                                        leido = false;
                                    }
                                } while (!leido);

                                    do {
                                        try {
                                            leido = true;
                                            System.out.println("\nIngrese el ID del libro");
                                            tp.setIdLibro(Integer.parseInt(br.readLine()));
                                            if(tp.getIdLibro() == 0){
                                                leido = true;
                                                break;
                                            }
                                            if (ops.verificarLibro(dataBaseConnection, tp.getIdLibro())) {
                                                System.out.println("\nEste libro no existe" +
                                                        ". Por favor verifique si está digitando correctamente los datos.");
                                                leido = false;
                                            }

                                            else if (!ops.verificarDisponibilidad(dataBaseConnection, tp.getIdLibro())) {
                                                System.out.println("\nEste libro no está disponible");
                                                leido = false;
                                            }

                                        } catch (Exception e) {
                                            System.out.println("\nError...Digite correctamente el ID. segun las indicaciones");
                                            leido = false;
                                        }

                                    } while (!leido);

                                do {
                                    try {
                                        leido = true;
                                        System.out.println("\nIngrese el ID del Usuario");
                                        tp.setIdUsuario(Integer.parseInt(br.readLine()));
                                        if(tp.getIdUsuario() == 0){
                                            leido = true;
                                            break;
                                        }

                                        if (ops.verificarUsuario(dataBaseConnection, tp.getIdUsuario())) {
                                            System.out.println("\nError....Este Usuario no está registrado" +
                                                    ". Por favor verifique si está ingresando correctamente los datos o regístrelo.");
                                            leido = false; }

                                    } catch (Exception e) {
                                        System.out.println("\nError...Digite correctamente el usuario. segun las indicaciones");
                                        leido = false;
                                    }
                                } while (!leido);

                                do {
                                    try {
                                        leido = true;
                                        System.out.println("\nIngrese la fecha de devolución en formato dd/mm/aaaa (digite los '/')");
                                        tp.setFechaDevolucion(br.readLine());
                                        if(tp.getFechaDevolucion() == "0"){
                                            leido = true;
                                            break;
                                        }
                                    } catch (Exception e) {
                                        System.out.println("\nError...Digite correctamente la fecha.segun las indicaciones");
                                        leido = false;
                                    }
                                } while (!leido);


                                try {

                                    ops.insertarTablaPrestamos(dataBaseConnection,tp.getIdPrestamo(),tp.getFechaPrestamo()
                                            ,tp.getIdLibro(),tp.getIdUsuario(),tp.getFechaDevolucion());
                                    System.out.println("\nSe ingresaron sus datos correctamente en la base de datos.");

                                }

                                catch (Exception e) {
                                    System.out.println(e);
                                }
                                leido = false;
                                break;

                            case 3: //Actualizar la devoluciones
                                System.out.println("\nDEVOLUCIONES");

                                do {
                                    try {
                                        leido = true;
                                        System.out.println("\nDigite el ID del Usuario (digite '0' para regresar)");
                                        tp.setIdUsuario(Integer.parseInt(br.readLine()));

                                        if(tp.getIdUsuario() == 0){
                                            leido = true;
                                            break;
                                        }

                                        if (ops.verificarUsuario(dataBaseConnection, tp.getIdUsuario())) {
                                            System.out.println("\nEste usuario no existe, verifique si está ingresando los datos correctamente");
                                            leido = false; }

                                        else if(ops.verificarPrestamo(dataBaseConnection, tp.getIdUsuario())){
                                            System.out.println("\nEste Usuario no tiene préstamos activos");
                                            leido = false;
                                            break;
                                        }

                                    }catch (Exception e){
                                        System.out.println(e.getMessage());
                                        leido = false;
                                    }
                                }while(!leido);

                                do {
                                    try {
                                        leido = true;
                                        System.out.println("\nIngrese el ID del libro");
                                        tp.setIdLibro(Integer.parseInt(br.readLine()));

                                        if(tp.getIdLibro() == 0){
                                            leido = true;
                                            break;
                                        }

                                        if (ops.verificarLibro(dataBaseConnection, tp.getIdLibro())) {
                                            System.out.println("\nEste libro no existe" +
                                                    ". Por favor verifique si está digitando correctamente los datos.");
                                            leido = false;
                                        }

                                        else if (ops.verificarPrestamo(dataBaseConnection,tp.getIdUsuario(), tp.getIdLibro())) {
                                            System.out.println("\nEste libro no está en posesión del Usuario ingresado.");
                                            leido = false;
                                            break;
                                        }
                                        else{
                                            try {

                                                ops.ActualizarDevolucion(dataBaseConnection,tp.getIdUsuario(),tp.getIdLibro());
                                                System.out.println("\nSe ha actualizado correctamente la devolución");

                                            }catch(Exception e){
                                                System.out.println(e.getMessage());
                                                leido = false;
                                            }

                                        }

                                    } catch (Exception e) {
                                        System.out.println("Error: " + e);
                                        leido = false;
                                    }

                                } while (!leido);

                                leido = false;
                                break;

                            case 4: //SUBMENÚ donde se muetran los informes

                                do {

                                    System.out.println("\nINFORMES:");
                                    System.out.println("\n1. Préstamos Totales.");
                                    System.out.println("2. Libros más populares.");
                                    System.out.println("3. Usuarios con más préstamos.");
                                    System.out.println("4. Regresar al menú anterior.");

                                do {

                                    try {

                                        leido = true;
                                        System.out.println("\nEscoja la opcion que desea utilizar:");
                                        op = Integer.parseInt(br.readLine());
                                        if (op <= 0 || op > 4) {
                                            System.out.println("\nError....Opcion Incorrecta.");
                                            leido = false;
                                        }
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                        leido = false;
                                    }

                                } while (!leido);


                                switch (op) {

                                    case 1: //opción que muestra los préstamos totales
                                        try {
                                            leido = true;
                                            System.out.println("\nPréstamos Totales: "+ops.ContarPrestamos(dataBaseConnection));
                                        } catch(Exception e){
                                            System.out.println(e.getMessage());
                                            leido = false;
                                        }
                                    leido = false;
                                    break;

                                    case 2: //opción que muestra los libros más populares
                                        do {
                                            try {
                                                leido = true;
                                                System.out.println("\nLibros más populares:");
                                                listalibros = ops.AbstraerPopulares(dataBaseConnection);
                                                System.out.println("\nID\t\t\t\t"+"Título\t\t\t\t\t\t\t\t\t\t\t\t"+"Autor\t\t\t\t\t\t\t\t\t\t\t"+"Género\t\t\t\t\t"+"ISBN\t\t\t"+"Fecha de Publicación\t\t\t");
                                                for (int i = 0; i < listalibros.size(); i++) {

                                                    System.out.print("\n" + listalibros.get(i).getIdLibro() + "\t\t\t" + listalibros.get(i).getTitulo() +
                                                            listalibros.get(i).getAutor() + " " + listalibros.get(i).getGenero() +
                                                            listalibros.get(i).getISBN() + "\t\t\t"+ listalibros.get(i).getFechaPublicacion()
                                                            + listalibros.get(i).getEditorial() + "\n");
                                                }

                                            } catch (Exception e) {
                                                System.out.println(e.getMessage());
                                                leido = false;
                                            }
                                        }while(!leido);
                                    leido = false;
                                    break;

                                    case 3: //opción que muestra los Usuarios con más préstamos
                                        do {
                                            try {
                                                leido = true;
                                                System.out.println("\nUsuarios con más préstamos:");
                                                listausuario = ops.contarUsuarios(dataBaseConnection);
                                                System.out.println("\nIdentificación\t\t"+"Nombre\t\t\t"+"Apellido");
                                                for (int i = 0; i < listausuario.size(); i++)
                                                    System.out.print("\n" + listausuario.get(i).getIdUsuario() + "\t\t\t" + listausuario.get(i).getNombre() +
                                                            listausuario.get(i).getApellido() + "\n");
                                            } catch (Exception e) {
                                                System.out.println(e.getMessage());
                                                leido = false;
                                            }
                                        }while(!leido);
                                        leido = false;
                                        break;

                                    case 4:
                                        leido = true;
                                        break;
                                }

                                }while(!leido);

                                leido = false;
                                break;

                            case 5: //Opción que busca la información para notificar a los usuarios sus devoluciones
                                do {
                                    try {
                                        leido = true;
                                        System.out.println("\nDigite el ID del Usuario a notificar (digite '0' para salir)");
                                        tp.setIdUsuario(Integer.parseInt(br.readLine()));

                                        if(tp.getIdUsuario() == 0){
                                            leido = true;
                                            break;
                                        }

                                        if (ops.verificarUsuario(dataBaseConnection, tp.getIdUsuario())) {
                                            System.out.println("\nEste usuario no existe, verifique si está ingresando los datos correctamente");
                                            leido = false; }

                                        else if(ops.verificarPrestamo(dataBaseConnection, tp.getIdUsuario())){
                                            System.out.println("\nEste Usuario no tiene préstamos activos");
                                            leido = false;
                                            break;
                                        }

                                    }catch (Exception e){
                                        System.out.println(e.getMessage());
                                        leido = false;
                                    }

                                    try {
                                        listausuario = ops.consultaDatos(dataBaseConnection,tp.getIdUsuario());
                                        System.out.println("\nNombre\t\t\t\t"+"Teléfono\t\t\t"+"Correo Electrónico");
                                        for (int i=0; i<listausuario.size();i++)
                                            System.out.print ("\n"+listausuario.get(i).getNombre()+listausuario.get(i).getTelefono()+
                                                    listausuario.get(i).getCorreoElectronico()+"\n");

                                    }catch (Exception e){
                                        System.out.println(e.getMessage());
                                        leido = false;
                                    }

                                }while(!leido);
                            leido = false;
                            break;

                            case 6: //Método que regresa al menú anterior
                            leido = true;
                            break;
                        }


                    } while (!leido);

                    leido = false;
                    break;

                case 2: //SUBMENÚ DE USUARIO

                    do {

                        System.out.println("Escoja una opción:");
                        System.out.println("\n1.Iniciar Sesion");
                        System.out.println("2.Registrarse");
                        System.out.println("3.Regresar al menú principal.");


                        do {


                            try {

                                leido = true;
                                System.out.println("\nIngrese la opcion a escoger:");
                                op = Integer.parseInt(br.readLine());
                                if (op <= 0) {
                                    System.out.println("\nError....Opcion Incorrecta.");
                                    leido = false;
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                                leido = false;
                            }

                        } while (!leido);

                        switch (op) {

                            case 1: //Inicio de Sesión



                                System.out.println("\nInicie sesion");

                                do {

                                    try {

                                        leido = true;
                                        System.out.println("\nIngrese su correo (digite '0' para regresar)");
                                        tu.setCorreoElectronico(br.readLine());

                                        if(tu.getCorreoElectronico() == "0"){
                                            leido = true;
                                            break;
                                        }

                                        if (tu.getCorreoElectronico().equals("")) {
                                            System.out.println("\nError....Su dato no puede estar vacio.");
                                            leido = false;
                                        }
                                    }

                                    catch (Exception e) {
                                        System.out.println(e.getMessage());
                                        leido = false;
                                    }

                                    try {


                                        leido = true;
                                        System.out.println("\nIngrese su Identificacion");
                                        tu.setIdUsuario(Integer.parseInt(br.readLine()));

                                        if(tu.getIdUsuario() == 0){
                                            leido = true;
                                            break;
                                        }

                                        if (tu.getIdUsuario() < 0) {
                                            System.out.println("\nError....No se pudo leer la identificacion correctamente.");
                                            leido = false;
                                        }
                                        if (ops.verificarUsuario(dataBaseConnection,tu.getIdUsuario())) {
                                            System.out.println("\nError....Usuario incorrecto. Ingrese nuevamente");
                                            leido = false;
                                        }
                                    }

                                    catch (Exception e) {
                                        System.out.println(e.getMessage());
                                        leido = false;
                                    }

                                } while (!leido);

                                System.out.println("\nSESIÓN INICIADA");

                                do{

                                    //SUBMENÚ OPCIONES PARA EL USUARIO

                                    System.out.println("\nElija una de las opciones:");
                                    System.out.println("\n1. Consultar Libros");
                                    System.out.println("2. Verificar Disponibilidad");
                                    System.out.println("3. Regresar al menú de usuario");

                                    do {

                                        try {

                                            leido = true;
                                            System.out.println("\nIngrese la opcion a escoger:");
                                            op = Integer.parseInt(br.readLine());
                                            if (op <= 0 || op > 3) {
                                                System.out.println("\nError....Opcion Incorrecta.");
                                                leido = false;
                                            }
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                            leido = false;
                                        }

                                    } while (!leido);

                                    switch (op){

                                        case 1: //SUBMENÚ PARA QUE EL USUARIO PUEDA ESCOGER COMO BUSCAR LOS LIBROS

                                            System.out.println("\nCómo desea Buscar su libro:");
                                            System.out.println("\n1. Nombre del autor");
                                            System.out.println("2. Titulo del libro");
                                            System.out.println("3. Genero del libro");
                                            System.out.println("4. Salir");
                                            do {

                                                try {

                                                    leido = true;
                                                    System.out.println("\nIngrese la opcion a escoger:");
                                                    op = Integer.parseInt(br.readLine());
                                                    if (op <= 0 || op > 4) {
                                                        System.out.println("\nError....Opcion Incorrecta.");
                                                        leido = false;
                                                    }
                                                } catch (Exception e) {
                                                    System.out.println(e.getMessage());

                                                     leido = false;
                                                }

                                            } while (!leido);

                                            switch (op){
                                                case 1: // OPCIÓN PARA BUSCAR POR EL NOMBRE DEL AUTOR
                                                    System.out.println("\nIngrese el nombre del autor: ");
                                                    try {
                                                        tl.setAutor(br.readLine());

                                                        try{

                                                            listalibros = ops.consultaLibroAutor(dataBaseConnection, tl.getAutor());
                                                            System.out.print ("\nID"+"\t\t\t\t\t"+"Libro"+"\t\t\t\t\t\t\t\t\t\t"+"Autor"+"\t\t\t\t\t\t\t\t\t\t\t   "+"Género"+"\t\t\t\t\t  "+"ISBN"+"\t\t\t"+"Fecha de Publicación"+"\t\t\t\t\t\t\t\t\t"+"Editorial");
                                                            for (int i=0; i<listalibros.size();i++)
                                                                System.out.print ("\n"+listalibros.get(i).getIdLibro()+"\t\t\t"+listalibros.get(i).getTitulo()+
                                                                        " "+listalibros.get(i).getAutor()+" "+listalibros.get(i).getGenero()+
                                                                        " "+listalibros.get(i).getISBN()+"\t\t\t"+listalibros.get(i).getFechaPublicacion()+
                                                                        " "+listalibros.get(i).getEditorial()+"\n");
                                                        }
                                                        catch(Exception e){
                                                            System.out.print (e);  }
                                                        leido = false;

                                                                                                            } catch (Exception e) {
                                                        System.out.println(e.getMessage());
                                                        leido = false;
                                                    }

                                                    break;

                                                case 2: // OPCIÓN PARA BUSCAR POR EL TÍTULO DEL LIBRO

                                                    System.out.println("\nIngrese el Titulo del libro: ");
                                                    try {
                                                        tl.setTitulo(br.readLine());

                                                        try{

                                                            listalibros = ops.consultaLibroTitulo(dataBaseConnection, tl.getTitulo());
                                                            System.out.print ("\nID"+"\t\t\t\t\t"+"Libro"+"\t\t\t\t\t\t\t\t\t\t"+"Autor"+"\t\t\t\t\t\t\t\t\t\t\t   "+"Género"+"\t\t\t\t\t  "+"ISBN"+"\t\t\t"+"Fecha de Publicación"+"\t\t\t\t\t\t\t\t\t"+"Editorial");
                                                            for (int i=0; i<listalibros.size();i++)
                                                                System.out.print ("\n"+listalibros.get(i).getIdLibro()+"\t\t\t"+listalibros.get(i).getTitulo()+
                                                                        " "+listalibros.get(i).getAutor()+" "+listalibros.get(i).getGenero()+
                                                                        " "+listalibros.get(i).getISBN()+"\t\t\t"+listalibros.get(i).getFechaPublicacion()+
                                                                        " "+listalibros.get(i).getEditorial()+"\n");
                                                        }
                                                        catch(Exception e){

                                             System.out.print (e);  }
                                                        leido = false;

                                                    } catch (Exception e) {
                                                        System.out.println(e.getMessage());
                                                        leido = false;
                                                    }
                                                    break;

                                                case 3:// OPCIÓN PARA BUSCAR POR EL GÉNERO DEL LIBRO

                                                    System.out.println("\nIngrese el Genero del libro: ");
                                                    try {
                                                        tl.setGenero(br.readLine());

                                                        try{

                                                            listalibros = ops.consultaLibroGenero(dataBaseConnection, tl.getGenero());
                                                            System.out.print ("\nID"+"\t\t\t\t\t"+"Libro"+"\t\t\t\t\t\t\t\t\t\t"+"Autor"+"\t\t\t\t\t\t\t\t\t\t\t   "+"Género"+"\t\t\t\t\t  "+"ISBN"+"\t\t\t"+"Fecha de Publicación"+"\t\t\t\t\t\t\t\t\t"+"Editorial");
                                                            for (int i=0; i<listalibros.size();i++)
                                                                System.out.print ("\n"+listalibros.get(i).getIdLibro()+"\t\t\t"+listalibros.get(i).getTitulo()+
                                                                        " "+listalibros.get(i).getAutor()+" "+listalibros.get(i).getGenero()+
                                                                        " "+listalibros.get(i).getISBN()+"\t\t\t"+listalibros.get(i).getFechaPublicacion()+
                                                                        " "+listalibros.get(i).getEditorial()+"\n");

                                                    }
                                                        catch(Exception e){
                                                            System.out.print (e);  }
                                                        leido = false;

                                                    } catch (Exception e) {
                                                        System.out.println(e.getMessage());
                                                        leido = false;
                                                    }
                                                    leido=false;
                                                    break;

                                                case 4: //OPCION PARA REGRESAR AL MENÚ ANTERIOR
                                                leido=true;
                                                break;
                                            }

                                            break;

                                        case 2: //OPCION PARA VERIFICAR LA DISPONIBILIDAD DE LOS LIBROS

                                            do {
                                                leido=true;
                                                System.out.println("\nVERIFICACIÓN");
                                                System.out.println("\nDigite el ID del libro (digite '0' para regresar)");
                                                try {
                                                    tp.setIdLibro(Integer.parseInt(br.readLine()));

                                                    if(tp.getIdLibro() == 0){
                                                        leido = true;
                                                        break;
                                                    }

                                                    if (ops.verificarLibro(dataBaseConnection, tp.getIdLibro())) {
                                                        System.out.println("\nEste libro no existe" +
                                                                ". Por favor verifique si está digitando correctamente los datos.");
                                                        leido = false;
                                                    }

                                                    else if (!ops.verificarDisponibilidad(dataBaseConnection, tp.getIdLibro())) {
                                                        System.out.println("\nEstado: No disponible");
                                                        leido = false;
                                                    }
                                                    else System.out.println("\nEstado: Disponible");

                                                } catch (Exception e) {
                                                    System.out.println(e.getMessage());
                                                }
                                            }while(!leido);

                                            leido = false;
                                            break;


                                        case 3: //OPCIÓN PAR REGRESAR AL MENÚ ANTERIOR
                                            leido = true;
                                            break;
                                    }
                                }while(!leido);

                                leido = false;
                                break;

                            case 2: //REGISTRO DE USUARIOS A LA BASE DE DATOS

                                do {


                                        try {
                                            leido = true;
                                            System.out.println("\nIngrese su cédula sin guiones (digite '0' para regresar)");
                                            tu.setIdUsuario(Integer.parseInt(br.readLine()));

                                            if (tu.getIdUsuario() < 0) {
                                                System.out.println("\nError....Se encontro caracteres invalidos.Ingrese nuevamente.");
                                                leido = false;
                                            }

                                            if(tu.getIdUsuario() == 0){
                                                leido = true;
                                                break;
                                            }

                                            if (!ops.verificarUsuario(dataBaseConnection, tu.getIdUsuario())) {
                                                System.out.println("\nError....Ya esta registrada la identificacion" +
                                                        ". Por favor ingrese otros datos.");
                                                leido = false;
                                            }

                                        } catch (Exception e) {
                                            System.out.println("\nError....Ingrese correctamente su usuario.");
                                            leido = false;
                                        }


                                        try {
                                            leido = true;
                                            System.out.println("\nIngrese su Nombre");
                                            tu.setNombre(br.readLine());

                                            if(tu.getNombre().equals("0")){
                                                leido = true;
                                                break;
                                            }

                                        } catch (Exception e) {
                                            System.out.println("\nError...Digite correctamente el nombre. No puede contener simbolos númericos");
                                            leido = false;
                                        }


                                        try {
                                            leido = true;
                                            System.out.println("\nIngrese su Apellido");
                                            tu.setApellido(br.readLine());

                                            if(tu.getApellido().equals("0")){
                                                leido = true;
                                                break;
                                            }

                                        } catch (Exception e) {
                                            System.out.println("\nError...Digite correctamente el apellido. No puede contener simbolos númericos");
                                            leido = false;
                                        }

                                        try {
                                            leido = true;
                                            System.out.println("\nIngrese su Dirección");
                                            tu.setDireccion(br.readLine());

                                            if(tu.getDireccion().equals("0")){
                                                leido = true;
                                                break;
                                            }

                                        } catch (Exception e) {
                                            System.out.println("\nError...Digite correctamente la direccion. No puede contener simbolos númericos");
                                            leido = false;
                                        }



                                        try {
                                            leido = true;
                                            System.out.println("\nIngrese su número telefónico");
                                            tu.setTelefono(br.readLine());

                                            if(tu.getTelefono().equals("0")){
                                                leido = true;
                                                break;
                                            }

                                        } catch (Exception e) {
                                            System.out.println("\nError...Digite correctamente su telefono.Siguiendo las indicaciones.");
                                            leido = false;
                                        }


                                        try {

                                            leido = true;
                                            System.out.println("\nIngrese su correo electrónico");
                                            tu.setCorreoElectronico(br.readLine());

                                            if(tu.getCorreoElectronico().equals("0")){
                                                leido = true;
                                                break;
                                            }

                                        } catch (Exception e) {
                                            System.out.println("\nError...Digite correctamente el correo.");
                                            leido = false;
                                        }

                                    try {

                                        ops.insertarTablaUsuarios(dataBaseConnection,tu.getIdUsuario(),tu.getNombre()
                                        ,tu.getApellido(),tu.getDireccion(),tu.getTelefono(),tu.getCorreoElectronico());
                                        System.out.println("\nSe ingresaron sus datos correctamente en la base de datos.");

                                    }

                                    catch (Exception e) {
                                        System.out.println(e);
                                        leido = false;
                                    }

                                } while (!leido);

                                leido = false;
                                break;

                            case 3: //OPCIÓN PARA REGRESAR AL MENÚ ANTERIOR;

                                leido = true;
                                break;
                            default:
                                System.out.println("\nDebe elegir una de las opciones del menú");
                                leido = false;
                                break;
                        }

                    } while (!leido);

                    leido = false;

                    break;

                case 3: //OPCIÓN PARA SALIR DEL MENÚ
                    System.out.println("\nGracias por utilizar nuestros servicios.Hasta luego!");
                    leido = true;
                    break;

                default:
                    System.out.println("\nError....Solo puede escoger las opciones que se desplegaron.");
                    leido = false;
            }

        } while (!leido) ;
    }
}