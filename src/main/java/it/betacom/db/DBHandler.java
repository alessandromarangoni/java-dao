package it.betacom.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler {
    private String connection;
    private String user;
    private String password;
    private String nomeDB;
    private static DBHandler instance;

    private DBHandler() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("attenzione non connesso il driver");
        }
        this.nomeDB = "libri";
        this.connection = "jdbc:mysql://localhost:3306/" + nomeDB;
        this.user = "root";
        this.password = "root";
    }

    public static DBHandler getInstance() {
        if (instance == null) {
            instance = new DBHandler();
        }
        return instance;
    }

    public Connection connectDB() {
        try {
        	Connection con = DriverManager.getConnection(connection, user, password); 
            return con;
        } catch (SQLException e) {
            System.out.println("non connesso");
            e.printStackTrace();
            return null;
        }
    }
    
    public void disconnectDB(Connection con) {
        if (con != null) {
            try {
                con.close();
                con = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
	public String getConnection() {
		return connection;
	}

	public void setConnection(String connection) {
		this.connection = connection;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNomeDB() {
		return nomeDB;
	}

	public void setNomeDB(String nomeDB) {
		this.nomeDB = nomeDB;
	}
	
}
