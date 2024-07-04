package com.cepeda.trs.model.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author CyborgK27
 */
public class DbConnection {
    private Connection cx = null;
    
    public Connection connect() {
        try{
            Class.forName("org.sqlite.JDBC");
            cx = DriverManager.getConnection("jdbc:sqlite:trs.db");
        }catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return cx;
    }
    
    public void disconnect() {
        try {
            if(cx != null && !cx.isClosed()){
                cx.close();
                System.out.println("Disconnected Successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
