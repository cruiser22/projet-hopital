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

	public String getAdresse() {
		return adresse;
	}

	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public int getAge() {
		return age;
	}

	public String getTelephone() {
		return telephone;
	}

	public String toString() {
		String res = "";
		res += "Patient [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age;
		if (telephone != null || telephone == "")
			res += ", telephone=" + telephone;
		if (adresse != null || adresse == "")
			res += ", adresse=" + adresse;
		res += "]";
		return res;
	}

}
