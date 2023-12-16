package it.betacom.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.betacom.Dao.EditoriDao;
import it.betacom.db.DBHandler;
import it.betacom.model.Editori;
import it.betacom.model.Genere;

public class EditoriDaoImpl implements EditoriDao{

	@Override
	public List<Editori> getAllEditori() {
		List<Editori> editori = new ArrayList<>();
        Connection con = null;
        try {
            con = DBHandler.getInstance().connectDB();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM editori");
            
            while (rs.next()) {
                Editori editore = new Editori(rs.getInt("editore_id"), rs.getString("nome"));
                editori.add(editore);
                System.out.println("editore_id: " + editore.getId() + "   Nome: " + editore.getNome());
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBHandler.getInstance().disconnectDB(con);
        }
        return editori;
	}

	@Override
	public void createEditore(Editori editori) {
		Connection con = DBHandler.getInstance().connectDB();
		String query = "INSERT INTO editori (nome) VALUES (?)";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stm.setString(1, editori.getNome());
			stm.executeUpdate();
	        rs = stm.getGeneratedKeys();
	        if (rs.next()) { 
	            editori.setId(rs.getInt(1));
	        }
			 rs.close();
	         stm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBHandler.getInstance().disconnectDB(con);
		}
	}

	@Override
	public Editori readEditore(int id) {
		Connection con = DBHandler.getInstance().connectDB();
		String query = "SELECT * FROM editori WHERE editore_id = ?";
		PreparedStatement stm = null;
		ResultSet rs = null;
		String nome = null;
		int id_editore = 0;
		Editori editore = null;
		
		try {
			stm = con.prepareStatement(query);
			stm.setInt(1, id);
			rs = stm.executeQuery();
			if(rs.next()) {
				nome = rs.getString("nome");
				id_editore = rs.getInt("editore_id");
				
				editore = new Editori(id_editore, nome);
//				System.out.println("ID: " + id_editore + "   nome editore: "+nome);
				rs.close();
				stm.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBHandler.getInstance().disconnectDB(con);
		}
		return editore;
		
	}

	@Override
	public void updateEditore(Editori editori) {
		Connection con = DBHandler.getInstance().connectDB();
		PreparedStatement stm = null;
		String updatequery = "UPDATE editori SET nome = ? WHERE editore_id = ?";
		try {
			stm = con.prepareStatement(updatequery);
			stm.setString(1, editori.getNome());
			stm.setInt(2, editori.getId());
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

	@Override
	public void deleteEditori(Editori editori) {
		Connection con = DBHandler.getInstance().connectDB();
		PreparedStatement stm = null;
		String query = "DELETE FROM editori WHERE editore_id = ?";
		try {
			stm = con.prepareStatement(query);
			stm.setInt(1, editori.getId());
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

}
