package com.dto;

public class Ville {

	private String Code_commune_INSEE;
	private String Nom_commune;
	private String Code_postal;
	private String Libelle_acheminement;
	private String Ligne_5;
	private String Latitude;
	private String Longitude;
	
	public String getCode_commune_INSEE() {
		return Code_commune_INSEE;
	}
	public void setCode_commune_INSEE(String code_commune_INSEE) {
		Code_commune_INSEE = code_commune_INSEE;
	}
	public String getNom_commune() {
		return Nom_commune;
	}
	public void setNom_commune(String nom_commune) {
		Nom_commune = nom_commune;
	}
	public String getCode_postal() {
		return Code_postal;
	}
	public void setCode_postal(String code_postal) {
		Code_postal = code_postal;
	}
	public String getLibelle_acheminement() {
		return Libelle_acheminement;
	}
	public void setLibelle_acheminement(String libelle_acheminement) {
		Libelle_acheminement = libelle_acheminement;
	}
	public String getLigne_5() {
		return Ligne_5;
	}
	public void setLigne_5(String ligne_5) {
		Ligne_5 = ligne_5;
	}
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	public String toString() {
		return "Ville : Code_commune_INSEE="+Code_commune_INSEE+" Nom_commune="+Nom_commune;
	}
}
