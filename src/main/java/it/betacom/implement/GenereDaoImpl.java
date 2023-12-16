package it.betacom.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.betacom.Dao.GenereDao;
import it.betacom.db.DBHandler;
import it.betacom.model.Genere;

public class GenereDaoImpl implements GenereDao {

	 public List<Genere> getAllGenere() {
	        List<Genere> generi = new ArrayList<>();
	        Connection con = null;
	        try {
	            con = DBHandler.getInstance().connectDB();
	            Statement stmt = con.createStatement();
	            ResultSet rs = stmt.executeQuery("SELECT * FROM genere");
	            
	            while (rs.next()) {
	                Genere genere = new Genere(rs.getInt("genere_id"), rs.getString("descrizione"));
	                generi.add(genere);
	                System.out.println("genere_id: " + genere.getId() + "   Descrizione: " + genere.getDescrizione());
	            }
	            rs.close();
	            stmt.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            DBHandler.getInstance().disconnectDB(con);
	        }
	        return generi;
	    }


	public void createGenere(Genere genere) {
		Connection con = DBHandler.getInstance().connectDB();
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			String sqlInsert = "INSERT INTO genere (descrizione) VALUES (?)";
	        stmt = con.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
	        stmt.setString(1, genere.getDescrizione());
	        stmt.executeUpdate();
	        rs = stmt.getGeneratedKeys();
	        if (rs.next()) {
	            genere.setId(rs.getInt(1));
	        }
			 rs.close();
	         stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBHandler.getInstance().disconnectDB(con);
		}
	}

	public Genere readGenereById(int idGenere) {
		String descrizione = null;
		Genere genere = null;
		Connection con = DBHandler.getInstance().connectDB();
		try {
			String sqlSelect = "SELECT descrizione FROM genere WHERE genere_id = ?";
			PreparedStatement stmt = con.prepareStatement(sqlSelect);
			stmt.setInt(1, idGenere);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				descrizione = rs.getString("descrizione");
//				System.out.println(descrizione);
			}
			genere = new Genere(idGenere, descrizione);
			rs.close();
            stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBHandler.getInstance().disconnectDB(con);
		}
		return genere;
		
	}

	public void updateGenere(Genere genere) {
		Connection con = DBHandler.getInstance().connectDB();
		PreparedStatement stm = null;
		String updatequery = "UPDATE genere SET descrizione = ? WHERE genere_id = ?";
		try {
			stm = con.prepareStatement(updatequery);
			stm.setString(1, genere.getDescrizione());
			stm.setInt(2, genere.getId());
			int affectedRows = stm.executeUpdate();
			if(affectedRows == 0 ) {
				System.out.println("nessun record aggiornato");
			}
            stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBHandler.getInstance().disconnectDB(con);
		}
		
	}

	public void deleteGenere(Genere genere) {
		Connection con = DBHandler.getInstance().connectDB();
		PreparedStatement stm = null;
		String delete = "DELETE FROM genere WHERE genere_id = ?";
		try {
			stm = con.prepareStatement(delete);
			stm.setInt(1, genere.getId());
			int affectedRows = stm.executeUpdate();
			if(affectedRows == 0 ) {
				System.out.println("nessuna riga eleiminata");
			}
            stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBHandler.getInstance().disconnectDB(con);
		}
	}

}
