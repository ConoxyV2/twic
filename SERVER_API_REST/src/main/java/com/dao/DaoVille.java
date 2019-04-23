package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.Ville;

public class DaoVille {
	
	protected Connection connect = null;
	private final String SQL_CREATE_VILLE = "INSERT INTO ville_france (Code_commune_INSEE,Nom_commune,Code_postal,Libelle_acheminement,Ligne_5,Latitude,Longitude) VALUES (?,?,?,?,?,?,?);";
	private final String SQL_UPDATE_VILLE = "UPDATE ville_france SET Code_commune_INSEE=?,Nom_commune=?,Code_postal=?,Libelle_acheminement=?,Ligne_5=?,Latitude=?,Longitude=? WHERE id=?;";
	private final String SQL_DELETE_VILLE = "DELETE FROM ville_france WHERE id=?;";
	private final String SQL_SELECT_VILLE_CP = "SELECT * FROM ville_france WHERE Code_postal=?;";
	private final String SQL_SELECT_VILLE_ID = "SELECT * FROM ville_france WHERE id=?;";
	private final String SQL_SELECT_VILLE = "SELECT * FROM ville_france;";
	
	public DaoVille() {
		try {
			DriverManager.deregisterDriver(new com.mysql.jdbc.Driver());
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3308/maven_tp1?user=admin&password=admin");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean create(Object obj) {
		try {
			Ville ville = (Ville) obj;
			PreparedStatement preparedStm = this.connect.prepareStatement(SQL_CREATE_VILLE);
			preparedStm.setString(1, ville.getCode_commune_INSEE());
			preparedStm.setString(2, ville.getNom_commune());
			preparedStm.setString(3, ville.getCode_postal());
			preparedStm.setString(4, ville.getLibelle_acheminement());
			preparedStm.setString(5, ville.getLigne_5());
			preparedStm.setString(6, ville.getLatitude());
			preparedStm.setString(7, ville.getLongitude());
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

	public boolean delete(String id) {
		try {
			PreparedStatement preparedStm = this.connect.prepareStatement(SQL_DELETE_VILLE);
			preparedStm.setInt(1, Integer.parseInt(id));
			int delete = preparedStm.executeUpdate();
			if(delete == 1) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(Ville ville) {
		try {
			PreparedStatement preparedStm = this.connect.prepareStatement(SQL_UPDATE_VILLE);
			preparedStm.setString(1, ville.getCode_commune_INSEE());
			preparedStm.setString(2, ville.getNom_commune());
			preparedStm.setString(3, ville.getCode_postal());
			preparedStm.setString(4, ville.getLibelle_acheminement());
			preparedStm.setString(5, ville.getLigne_5());
			preparedStm.setString(6, ville.getLatitude());
			preparedStm.setString(7, ville.getLongitude());
			preparedStm.setInt(8, ville.getId());
			if(preparedStm.executeUpdate()==1) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Ville> find() {
		List<Ville> villes = new ArrayList<Ville>();
		PreparedStatement preparedStm = null;
		ResultSet rSet = null;
		try {
			preparedStm = this.connect.prepareStatement(SQL_SELECT_VILLE);
			rSet = preparedStm.executeQuery();
			while(rSet.next()) {
				Ville ville = new Ville();
				ville.setId(rSet.getInt("id"));
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
	
	public List<Ville> find(String cp) {
		List<Ville> villes = new ArrayList<Ville>();
		PreparedStatement preparedStm = null;
		ResultSet rSet = null;
		try {
			preparedStm = this.connect.prepareStatement(SQL_SELECT_VILLE_CP);
			preparedStm.setString(1, cp);
			rSet = preparedStm.executeQuery();
			while(rSet.next()) {
				Ville ville = new Ville();
				ville.setId(rSet.getInt("id"));
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
	
	public Ville findId(String id) {
		PreparedStatement preparedStm = null;
		ResultSet rSet = null;
		Ville ville = new Ville();
		try {
			preparedStm = this.connect.prepareStatement(SQL_SELECT_VILLE_ID);
			preparedStm.setString(1, id);
			rSet = preparedStm.executeQuery();
			while(rSet.next()) {
				
				ville.setId(rSet.getInt("id"));
				ville.setCode_commune_INSEE(rSet.getString("code_commune_INSEE"));
				ville.setNom_commune(rSet.getString("Nom_commune"));
				ville.setCode_postal(rSet.getString("Code_postal"));
				ville.setLibelle_acheminement(rSet.getString("Libelle_acheminement"));
				ville.setLigne_5(rSet.getString("Ligne_5"));
				ville.setLatitude(rSet.getString("Latitude"));
				ville.setLongitude(rSet.getString("Longitude"));
			}
			rSet.close();
			preparedStm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ville;
	}
}
