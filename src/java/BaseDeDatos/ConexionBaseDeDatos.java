/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Portatil
 */
public class ConexionBaseDeDatos {

    static DataSource datasource;

    public ConexionBaseDeDatos() {
        try {
            InitialContext initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup("jdbc/trivialJDBC");
        } catch (NamingException ex) {
            Logger.getLogger(ConexionBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Connection getConexion(){
        Connection c = null;
        try {
            c = datasource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

}
