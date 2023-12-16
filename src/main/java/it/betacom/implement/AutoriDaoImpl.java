package it.betacom.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.betacom.Dao.AutoriDao;
import it.betacom.db.DBHandler;
import it.betacom.model.Autore;

public class AutoriDaoImpl implements AutoriDao {

	@Override
	public List<Autore> getAllAutori() {
		List<Autore> autori = new ArrayList<>();
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		String getAllQuery = "SELECT * FROM autori";
		try {
			con = DBHandler.getInstance().connectDB();
			stm = con.createStatement();
			rs = stm.executeQuery(getAllQuery);
			
			while(rs.next()) {
				Autore autore = new Autore(rs.getInt("autori_id"), rs.getString("nome"), rs.getString("cognome"),
						rs.getInt("anno_nascita"), rs.getInt("anno_nascita"), rs.getString("sesso"), rs.getString("nazione"));
				autori.add(autore);
//				System.out.println("id: " + rs.getInt("autori_id") + " nome: " +  rs.getString("nome")+ " cognome: " + rs.getString("cognome") + " nato nel: " +
//						rs.getInt("anno_nascita")+ " morto: " + rs.getInt("anno_nascita")+ " sesso: " +rs.getString("sesso")+ " nazione: " + rs.getString("nazione"));
			}
			rs.close();
			stm.close();
		} catch (SQLException e) {
			System.out.println("impossibile accedere a tutti gli autori");
		}finally {
			DBHandler.getInstance().disconnectDB(con);
		}
		return autori;
	}

	@Override
	public void createAutore(Autore autore) {
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		int id = 0;
		String insertQuery = "INSERT INTO autori (nome, cognome, anno_nascita, anno_morte, sesso, nazione) VALUES"
				+ "(?,?,?,?,?,?)";
		try {
			con = DBHandler.getInstance().connectDB();
			stm = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
			stm.setString(1, autore.getNome());
			stm.setString(2, autore.getCognome());
			stm.setInt(3, autore.getAnno_nascita());
			stm.setInt(4, autore.getAnno_morte());
			stm.setString(5, autore.getSesso());
			stm.setString(6, autore.getNazione());
			stm.executeUpdate();
			rs = stm.getGeneratedKeys();
			if(rs.next()) {
				id = rs.getInt(1);
				autore.setId(id);
			}
			rs.close();
	         
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			DBHandler.getInstance().disconnectDB(con);
		}
	}

	@Override
	public Autore readAutore(int id) {
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		String query = "SELECT * FROM autori WHERE autori_id = ?";
		Autore autore = null;
		try {
			con = DBHandler.getInstance().connectDB();
			stm = con.prepareStatement(query);
			stm.setInt(1, id);
			rs = stm.executeQuery();
			if (rs.next()){
				autore = new Autore(rs.getInt("autori_id"), rs.getString("nome"), rs.getString("cognome"), rs.getInt("anno_nascita"), rs.getInt("anno_morte"), rs.getString("sesso"), rs.getString("nazione"));
			}
			rs.close();
			stm.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			DBHandler.getInstance().disconnectDB(con);
		}
		
		return autore;
	}

	@Override
	public void UpdateAutore(Autore autore) {
		Connection con = null;
		PreparedStatement stm = null;
		String query = "UPDATE autori SET nome = ?, cognome = ?, anno_nascita = ?, anno_morte = ?, sesso = ?, nazione = ? WHERE autori_id = ?";
		 try {
		        con = DBHandler.getInstance().connectDB();
		        stm = con.prepareStatement(query);
		        
		        stm.setString(1, autore.getNome());
		        stm.setString(2, autore.getCognome());
		        stm.setInt(3, autore.getAnno_nascita());
		        stm.setInt(4, autore.getAnno_morte());
		        stm.setString(5, autore.getSesso());
		        stm.setString(6, autore.getNazione());
		        stm.setInt(7, autore.getId());
		        	
		        int rowsUpdated = stm.executeUpdate();
		        
		        if (rowsUpdated > 0) {
		            System.out.println("L'autore è stato aggiornato con successo.");
		        } else {
		            System.out.println("Nessuna riga è stata aggiornata.");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace(); 
		    }finally {
		    	DBHandler.getInstance().disconnectDB(con);
		    }
	}

	@Override
	public void DeleteAutore(Autore autore) {
		Connection con = null;
		PreparedStatement stm = null;
		String query = "DELETE FROM autori WHERE autori_id = ?";
		try {
			con = DBHandler.getInstance().connectDB();
			stm = con.prepareStatement(query);
			stm.setInt(1, autore.getId());
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBHandler.getInstance().disconnectDB(con);
		}
		
	}

}
