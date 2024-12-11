package sc.pkgProject_Lenjuage_BD;

import ConectionDB.ConectionDB;
import Interfaz.Menu;
import popups.Popups;

/**
 *
 * @author ROURY
 */
public class Project_Lenjuage_BD {

    public static String EstadoDB = "";

    public static void main(String[] args) {
        Popups popups = new Popups();
        Popups.bienvenido();

       String conecting = "Realizando conexión con la base de datos";
        popups.espera(conecting);

        ConectionDB conectarOracle = new ConectionDB();
        conectarOracle.getConnection();
        popups.ConnectingDB(EstadoDB);

        Menu inicio = new Menu();
        inicio.setVisible(true);
    }
}
