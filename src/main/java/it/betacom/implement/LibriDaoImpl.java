package it.betacom.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.betacom.Dao.LibriDao;
import it.betacom.db.DBHandler;
import it.betacom.model.Libro;

public class LibriDaoImpl implements LibriDao {

	@Override
	public List<Libro> getAllLibri() {
		List<Libro> libri = new ArrayList<>();
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		String query = "SELECT * FROM libri";
		
		try {
			con = DBHandler.getInstance().connectDB();
			stm = con.createStatement();
			rs = stm.executeQuery(query);
			while(rs.next()) {
				Libro libro = new Libro(rs.getInt("id_libri"),rs.getString("nome"),rs.getInt("numero_pagine"), rs.getInt("anno"));
				libro.setAutori_id(rs.getInt("autori_id"));
				libro.setGenere_id(rs.getInt("genere_id"));
				libro.setEditore_id(rs.getInt("editore_id"));
				libri.add(libro);
//				System.out.println(libro.toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBHandler.getInstance().disconnectDB(con);
		}
		
		return libri;
	}

	@Override
	public void createLibro(Libro libro) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "INSERT INTO LIBRI (nome, numero_pagine, anno, autori_id, genere_id, editore_id) VALUES (?, ?, ?, ?, ?, ?)";
		
        try {
        	con = DBHandler.getInstance().connectDB();
			pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, libro.getNome());
	        pstmt.setInt(2, libro.getNumero_pagine());
	        pstmt.setInt(3, libro.getAnno());
	        pstmt.setInt(4, libro.getAutori_id());
	        pstmt.setInt(5, libro.getGenere_id());
	        pstmt.setInt(6, libro.getEditore_id());
	        pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				libro.setId(rs.getInt(1));
			}
			rs.close();
	        pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBHandler.getInstance().disconnectDB(con);
		}
	}

	@Override
	public Libro readLibro(int id) {
	    Connection con = null;
	    PreparedStatement stm = null;
	    ResultSet rs = null;
	    String query = "SELECT * FROM libri WHERE id_libri = ? ";
	    Libro libro = null;
	    try {
	        con = DBHandler.getInstance().connectDB();
	        stm = con.prepareStatement(query);
	        stm.setInt(1, id);
	        rs = stm.executeQuery();
	        if (rs.next()) {
	            libro = new Libro(id, rs.getString("nome"), rs.getInt("numero_pagine"), rs.getInt("anno"));
	            libro.setAutori_id(rs.getInt("autori_id"));
	            libro.setGenere_id(rs.getInt("genere_id"));
	            libro.setEditore_id(rs.getInt("editore_id"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (stm != null) stm.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    if (libro != null) {
	        System.out.println(libro.toString());
	    } else {
	        System.out.println("Nessun libro trovato con ID: " + id);
	    }
	    return libro;
	}


	@Override
	public void UpdateLibro(Libro libro) {
		String query = "UPDATE libri SET nome = ?, numero_pagine = ?,  anno = ?, autori_id = ?, genere_id = ?, editore_id = ? WHERE id_libri = ?";
		Connection con = null;
		PreparedStatement pstmt = null;		
        try {
        	con = DBHandler.getInstance().connectDB();
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, libro.getNome());
	        pstmt.setInt(2, libro.getNumero_pagine());
	        pstmt.setInt(3, libro.getAnno());
	        pstmt.setInt(4, libro.getAutori_id());
	        pstmt.setInt(5, libro.getGenere_id());
	        pstmt.setInt(6, libro.getEditore_id());
	        pstmt.setInt(7, libro.getId());
	        pstmt.executeLargeUpdate();

	        pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBHandler.getInstance().disconnectDB(con);
		}
	}

	@Override
	public void deleteLibro(Libro libro) {
		// TODO Auto-generated method stub

	}

}
