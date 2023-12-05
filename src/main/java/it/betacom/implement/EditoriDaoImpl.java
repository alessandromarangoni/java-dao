package it.betacom.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import it.betacom.Dao.EditoriDao;
import it.betacom.db.DBHandler;
import it.betacom.model.Editori;

public class EditoriDaoImpl implements EditoriDao{

	@Override
	public List<Editori> getAllEditori() {
		// TODO Auto-generated method stub
		return null;
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
		}
	}

	@Override
	public Editori readEditore(int id) {
		Connection con = DBHandler.getInstance().connectDB();
		String query = "INSERT INTO editori (nome) VALUES (?)";
		PreparedStatement stm = null;
		ResultSet rs = null;
		return null;
	}

	@Override
	public void updateEditore(Editori editori) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEditori(Editori editori) {
		// TODO Auto-generated method stub
		
	}

}
