package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.Ville;

public class DaoVille extends Dao {
	
	private final String SQL_CREATE_VILLE = "INSERT INTO ville_france (Code_commune_INSEE,Nom_commune,Code_postal,Libelle_acheminement,Ligne_5,Latitude,Longitude) VALUES (?,?,?,?,?,?,?);";
	private final String SQL_DELETE_VILLE = "DELETE FROM ville_france WHERE Code_commune_INSEE=? AND Nom_commune=? AND Code_postal=? AND Libelle_acheminement=? AND Ligne_5=? AND Latitude=? AND Longitude=?;";
	private final String SQL_SELECT_VILLE = "SELECT * FROM ville_france WHERE Code_commune_INSEE LIKE(?) OR Nom_commune LIKE(?) OR Code_postal LIKE(?) OR Libelle_acheminement LIKE(?) OR Ligne_5 LIKE(?) OR Latitude LIKE(?) OR Longitude LIKE(?);";
	
	public DaoVille() {
		super();
	}

	public boolean create(Object obj) {
		try {
			Ville ville = (Ville) obj;
			PreparedStatement preparedStm = this.connect.prepareStatement(SQL_CREATE_VILLE);
			setPreparedStatement(ville, preparedStm);
			int insert = preparedStm.executeUpdate();
			if(insert == 1) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean delete(Object obj) {
		try {
			Ville ville = (Ville) obj;
			PreparedStatement preparedStm = this.connect.prepareStatement(SQL_DELETE_VILLE);
			setPreparedStatement(ville, preparedStm);
			int insert = preparedStm.executeUpdate();
			if(insert == 1) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	public Object find(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Ville> find(String str) {
		str = "%"+str+"%";
		List<Ville> villes = new ArrayList<Ville>();
		PreparedStatement preparedStm = null;
		ResultSet rSet = null;
		try {
			preparedStm = this.connect.prepareStatement(SQL_SELECT_VILLE);
			preparedStm.setString(1, str);
			preparedStm.setString(2, str);
			preparedStm.setString(3, str);
			preparedStm.setString(4, str);
			preparedStm.setString(5, str);
			preparedStm.setString(6, str);
			preparedStm.setString(7, str);
			rSet = preparedStm.executeQuery();
			while(rSet.next()) {
				Ville ville = new Ville();
				ville.setCode_commune_INSEE(rSet.getString("code_commune_INSEE"));
				ville.setNom_commune(rSet.getString("Nom_commune"));
				ville.setCode_postal(rSet.getString("Code_postal"));
				ville.setLibelle_acheminement(rSet.getString("Libelle_acheminement"));
				ville.setLigne_5(rSet.getString("Ligne_5"));
				ville.setLatitude(rSet.getString("Latitude"));
				ville.setLongitude(rSet.getString("Longitude"));
				villes.add(ville);
			}
			rSet.close();
			preparedStm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return villes;
	}

	private PreparedStatement setPreparedStatement(Ville ville, PreparedStatement preparedStm) throws SQLException {
		preparedStm.setString(1, ville.getCode_commune_INSEE());
		preparedStm.setString(2, ville.getNom_commune());
		preparedStm.setString(3, ville.getCode_postal());
		preparedStm.setString(4, ville.getLibelle_acheminement());
		preparedStm.setString(5, ville.getLigne_5());
		preparedStm.setString(6, ville.getLatitude());
		preparedStm.setString(7, ville.getLongitude());
		return preparedStm;
	}
}
