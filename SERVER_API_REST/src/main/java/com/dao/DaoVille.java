package com.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.dto.Ville;

public class DaoVille {
	
	protected Connection connect = null;
	private final String SQL_CREATE_VILLE = "INSERT INTO ville_france (Code_commune_INSEE,Nom_commune,Code_postal,Libelle_acheminement,Ligne_5,Latitude,Longitude) VALUES (?,?,?,?,?,?,?);";
	private final String SQL_UPDATE_VILLE = "UPDATE ville_france SET Code_commune_INSEE=?,Nom_commune=?,Code_postal=?,Libelle_acheminement=?,Ligne_5=?,Latitude=?,Longitude=? WHERE id=?;";
	private final String SQL_DELETE_VILLE = "DELETE FROM ville_france WHERE id=?;";
	private final String SQL_SELECT_VILLE_CP = "SELECT * FROM ville_france WHERE Code_postal=?;";
	private final String SQL_SELECT_VILLE_ID = "SELECT * FROM ville_france WHERE id=?;";
	private final String SQL_SELECT_VILLE = "SELECT * FROM ville_france;";
	private static final Logger LOGGER = Logger.getLogger(DaoVille.class);
	
	public DaoVille() {
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";
 
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			String password = prop.getProperty("password");
			
			
			DriverManager.deregisterDriver(new com.mysql.jdbc.Driver());
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3308/maven_tp1?user=admin&password="+password+"");
		} catch (SQLException e) {
			LOGGER.error("Error", e);
		} catch (IOException e) {
			LOGGER.error("Error", e);
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
			return responseReturn(insert);
		} catch (SQLException e) {
			LOGGER.error("Error", e);
			return false;
		}
	}

	public boolean delete(String id) {
		try {
			PreparedStatement preparedStm = this.connect.prepareStatement(SQL_DELETE_VILLE);
			preparedStm.setInt(1, Integer.parseInt(id));
			int delete = preparedStm.executeUpdate();
			return responseReturn(delete);
		} catch (SQLException e) {
			LOGGER.error("Error", e);
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
			return responseReturn(preparedStm.executeUpdate());
		} catch (SQLException e) {
			LOGGER.error("Error", e);
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
			LOGGER.error("Error", e);
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
			LOGGER.error("Error", e);
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
			LOGGER.error("Error", e);
		}
		return ville;
	}
	
	private boolean responseReturn(int response) {
		if(response == 1) {
			return true;
		}else {
			return false;
		}
	}
}
