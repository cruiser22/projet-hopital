package model;

public class Patient {
	private int id;
	private String nom, prenom;
	private int age;
	private String telephone, adresse;

	public Patient(int id, String nom, String prenom, int age, String telephone, String adresse) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.telephone = telephone;
		this.adresse = adresse;
	}

	public Patient(int id, String nom, String prenom, int age) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
	}

	public String toString() {
		return "Patient [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", telephone=" + telephone
				+ ", adresse=" + adresse + "]";
	}

}
