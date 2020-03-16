package com.konxsys.goodmapdemo;

public class Location {
	private String nom;
	private String ville;
	private String delegation;
	private String adresse;
	private int totalLitReanimationAvecMachine;
	private int totalLitReanimationSansMachine;
	private int totalLitSimple;

	private int totalLitReanimationAvecMachineDispo;
	private int totalLitReanimationSansMachineDispo;
	private int totalLitSimpleDispo;


	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getDelegation() {
		return delegation;
	}

	public void setDelegation(String delegation) {
		this.delegation = delegation;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public int getTotalLitReanimationAvecMachine() {
		return totalLitReanimationAvecMachine;
	}

	public void setTotalLitReanimationAvecMachine(int totalLitReanimationAvecMachine) {
		this.totalLitReanimationAvecMachine = totalLitReanimationAvecMachine;
	}

	public int getTotalLitReanimationSansMachine() {
		return totalLitReanimationSansMachine;
	}

	public void setTotalLitReanimationSansMachine(int totalLitReanimationSansMachine) {
		this.totalLitReanimationSansMachine = totalLitReanimationSansMachine;
	}

	public int getTotalLitSimple() {
		return totalLitSimple;
	}

	public void setTotalLitSimple(int totalLitSimple) {
		this.totalLitSimple = totalLitSimple;
	}

	public int getTotalLitReanimationAvecMachineDispo() {
		return totalLitReanimationAvecMachineDispo;
	}

	public void setTotalLitReanimationAvecMachineDispo(int totalLitReanimationAvecMachineDispo) {
		this.totalLitReanimationAvecMachineDispo = totalLitReanimationAvecMachineDispo;
	}

	public int getTotalLitReanimationSansMachineDispo() {
		return totalLitReanimationSansMachineDispo;
	}

	public void setTotalLitReanimationSansMachineDispo(int totalLitReanimationSansMachineDispo) {
		this.totalLitReanimationSansMachineDispo = totalLitReanimationSansMachineDispo;
	}

	public int getTotalLitSimpleDispo() {
		return totalLitSimpleDispo;
	}

	public void setTotalLitSimpleDispo(int totalLitSimpleDispo) {
		this.totalLitSimpleDispo = totalLitSimpleDispo;
	}

	public int getTotalLit() {
		return totalLitReanimationAvecMachine + totalLitReanimationSansMachine + totalLitSimple;
	}

	public int getTotalLitDispo() {
		return totalLitReanimationAvecMachineDispo + totalLitReanimationSansMachineDispo + totalLitSimpleDispo;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	private double latitude;
	private double longitude;
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getNom());
		sb.append("Nombre de lits disponibles: "+ getTotalLitDispo());
		sb.append("<br>");
		sb.append("Nombre de lits réanimation avec machine disponibles: "+ getTotalLitReanimationAvecMachineDispo());
		sb.append("<br>");
		sb.append("Nombre de lits réanimation sans machine disponibles: "+ getTotalLitReanimationSansMachineDispo());
		sb.append("<br>");
		sb.append("Nombre de lits simples disponibles: "+ getTotalLitSimpleDispo());
		return sb.toString();
	}

}
