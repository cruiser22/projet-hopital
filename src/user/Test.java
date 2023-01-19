package user;

import java.util.List;
import java.util.Scanner;

import dao.AuthentificationDAO;
import model.Hopital;
import model.Medecin;
import model.Patient;
import model.Personnel;
import model.Salle;
import model.Secretaire;

public class Test {

	public static void main(String[] args) {

		Hopital h = Hopital.getInstance();
		h.ajouterSalle(new Salle(1));
		h.ajouterSalle(new Salle(2));

		// TESTS FILE D'ATTENTE
		Secretaire s = new Secretaire("test", "test", "sylvie", 0);

		Patient p1 = new Patient(1, "toto", "titi", 15);
		Patient p2 = new Patient(2, "dupond", "jean", 16);
		Patient p3 = new Patient(3, "dupont", "jacques", 17);
		Patient p4 = new Patient(4, "doe", "john", 18);

		s.ajouterPatientFilleAttente(p1);
		s.ajouterPatientFilleAttente(p2);
		s.ajouterPatientFilleAttente(p3);
		s.ajouterPatientFilleAttente(p4);

		authMenu();
	}

	// menu authentification
	private static void authMenu() {
		AuthentificationDAO auth = new AuthentificationDAO();
		Scanner sc = new Scanner(System.in);
		String login = "";
		String password = "";
		Personnel p;
		int metier;

		do {
			try {
				System.out.println("Veuillez saisir votre login :");
				login = sc.nextLine();
				System.out.println("Veuillez saisir votre mot de passe :");
				password = sc.nextLine();
			} catch (Exception e) {
				System.out.println("Veuillez entrer des charactères ASCII !");
			}
			p = auth.verif(login, password);
			if (p != null) {
				System.out.println("Authentification a réussie !");
				System.out.println("Bienvenu " + p.getNom());
				// Prochain menu
				metier = p.getMetier();
				if (metier == 0)
					menuSecretaire(p);
				else
					menuMedecin(p);
			} else
				System.out.println("L'authentification a échouée.");
		} while (true);
	}

	private static void menuSecretaire(Personnel p) {
		Secretaire s = new Secretaire(p.getLogin(), p.getPassword(), p.getNom(), p.getMetier());
		Scanner sc = new Scanner(System.in);
		int choix = -1;

		while (choix != 0) {
			System.out.println("Veuillez choisir une action :");
			System.out.println("1) Ajouter un patient en file d'attente\n" + "2) Afficher la file d'attente\n"
					+ "3) Afficher le prochain patient\n" + "4) Afficher les visites d'un patient\n"
					+ "0) Quitter le menu");
			try {
				choix = sc.nextInt();
			} catch (Exception e) {
				System.out.println("Veuillez entrer un caratère numérique !");
				break;
			}

			switch (choix) {
			case 0:
				System.out.println("Retour au menu précédent.");
				break;
			case 1:
				s.ajouterPatientFilleAttente(formulairePatient());
				break;
			case 2:
				afficherListe(s.getFileAttente());
				break;
			case 3:
				System.out.println(s.afficherProchainPatient());
				break;
			case 4:
				System.out.println("Veuillez entrer l'ID du patient :");
				int idPatient = sc.nextInt();
				if (s.getListeVisites(idPatient).size() != 0)
					afficherListe(s.getListeVisites(idPatient));
				else
					System.out.println("Patient sans visites ou mauvais ID !");
				break;
			default:
				System.out.println("Veuillez entrer un chiffre correspondant à votre choix !");
				break;
			}
		}
	}

	private static Patient formulairePatient() {
		Scanner scInt = new Scanner(System.in);
		Scanner scString = new Scanner(System.in);
		Patient p = null;
		int id, age;
		String nom, prenom, tel, adresse;

		do {
			System.out.println("Veuillez renseigner les informations du patient :");
			try {
				System.out.println("Id");
				id = scInt.nextInt();
				System.out.println("Nom");
				nom = scString.nextLine();
				System.out.println("Prenom");
				prenom = scString.nextLine();
				System.out.println("Age");
				age = scInt.nextInt();
				System.out.println("Telephone");
				tel = scString.nextLine();
				System.out.println("Adresse");
				adresse = scString.nextLine();
				p = new Patient(id, nom, prenom, age, tel, adresse);
			} catch (Exception e) {
				System.out.println("Veuillez entrer un caratère numérique ou ASCII !");
				break;
			}
		} while (p == null);

		return p;
	}

	private static void menuMedecin(Personnel p) {
		Medecin m = new Medecin(p.getLogin(), p.getPassword(), p.getNom(), p.getMetier());
		Scanner sc = new Scanner(System.in);
		int choix = -1;

		while (choix != 0) {
			System.out.println("Veuillez choisir une action :");
			System.out.println("1) Rendre la salle disponible\n" + "2) Afficher la file d'attente\n"
					+ "3) Sauvegarder ses visites\n" + "4) Afficher sa liste des visites\n" + "0) Quitter le menu");
			try {
				choix = sc.nextInt();
			} catch (Exception e) {
				System.out.println("Veuillez entrer un caratère numérique !");
			}

			switch (choix) {
			case 0:
				System.out.println("Retour au menu précédent.");
				break;
			case 1:
				m.salleDispo();
				break;
			case 2:
				afficherListe(m.getFileAttente());
				break;
			case 3:
				m.sauvegarderVisites();
				break;
			case 4:
				if (m.getListeVisites().size() != 0)
					afficherListe(m.getListeVisites());
				else
					System.out.println("Médecin sans visites !");
				break;
			default:
				System.out.println("Veuillez entrer un chiffre correspondant à votre choix !");
				break;
			}
		}
	}

	static void afficherListe(List liste) {
		for (Object o : liste) {
			System.out.println(o);
		}
		System.out.println();

	}

}
