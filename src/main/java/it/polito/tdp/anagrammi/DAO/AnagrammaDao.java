package it.polito.tdp.anagrammi.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.anagrammi.model.Anagramma;


public class AnagrammaDao {
	
	
	public boolean isCorrect(String anagramma) {
		
		boolean isCorrect=false;
		final String sql = "SELECT * "
				+ "FROM parola p "
				+ "WHERE p.nome= ?";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, anagramma);
			ResultSet rs = st.executeQuery();
			

			while (rs.next()) {
				isCorrect=true;
			}

			conn.close();
			
			return isCorrect;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
		
}


