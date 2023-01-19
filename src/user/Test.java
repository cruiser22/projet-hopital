package user;

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
		// testMedecin();
		// testSecretaire();
		// testMedecin();
		// test package
	}

	/*
	 * private static void metierMenu() { Scanner sc = new Scanner(System.in);
	 * int choix = -1;
	 * 
	 * // Récupérer n°métier du personnel while (choix != 0) {
	 * System.out.println("Veuillez choisir sous quel métier s'authentifier :");
	 * System.out.println("1) Médecin en Salle 1\n" + "2) Médecin en Salle 2\n"
	 * + "3) Secrétaire\n" + "0) Quitter le menu"); try { choix = sc.nextInt();
	 * } catch (Exception e) { System.out.println(
	 * "Veuillez entrer un caratère numérique !"); break; }
	 * 
	 * if (choix == 0) System.out.println("Merci de votre visite, au revoir !");
	 * else if (1 <= choix && choix <= 3) authMenu(choix); else
	 * System.out.println(
	 * "Veuillez entrer un chiffre correspondant à votre choix !"); }
	 * 
	 * }
	 */

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
				System.out.println(s.afficherFilleAttente());
				break;
			case 3:
				System.out.println(s.afficherProchainPatient());
				break;
			case 4:
				System.out.println("Veuillez entrer l'ID du patient :");
				int idPatient = sc.nextInt();
				if (s.afficherListesVisite(idPatient).size() != 0)
					System.out.println(s.afficherListesVisite(idPatient));
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
				System.out.println(m.afficherFilleAttente());
				break;
			case 3:
				m.sauvegarderVisites();
				break;
			case 4:
				if (m.afficherListesVisite().size() != 0)
					System.out.println(m.afficherListesVisite());
				else
					System.out.println("Médecin sans visites !");
				break;
			default:
				System.out.println("Veuillez entrer un chiffre correspondant à votre choix !");
				break;
			}
		}
	}

	static void testSecretaire() {
		Hopital h = Hopital.getInstance();
		h.ajouterSalle(new Salle(1));
		h.ajouterSalle(new Salle(2));
		Secretaire s = new Secretaire("test", "test", "sylvie", 0);
		Patient p1 = new Patient(1, "toto", "titi", 15);
		Patient p2 = new Patient(2, "dupond", "jean", 16);
		Patient p3 = new Patient(3, "dupont", "jacques", 17);
		Patient p4 = new Patient(4, "doe", "john", 18);
		System.out.println(s.getFileAttente());
		s.ajouterPatientFilleAttente(p1);
		s.ajouterPatientFilleAttente(p2);
		System.out.println(s.getFileAttente());
		System.out.println(s.afficherProchainPatient());

	}

	static void testMedecin() {
		Hopital h = Hopital.getInstance();
		h.ajouterSalle(new Salle(1));
		h.ajouterSalle(new Salle(2));

		Secretaire s = new Secretaire("test", "test", "sylvie", 0);

		Patient p1 = new Patient(1, "toto", "titi", 15);
		Patient p2 = new Patient(2, "dupond", "jean", 16);
		Patient p3 = new Patient(3, "dupont", "jacques", 17);
		Patient p4 = new Patient(4, "doe", "john", 18);

		s.ajouterPatientFilleAttente(p1);
		s.ajouterPatientFilleAttente(p2);
		s.ajouterPatientFilleAttente(p3);
		s.ajouterPatientFilleAttente(p4);
		System.out.println("-------------------------");
		System.out.println(s.getListeVisites(1));
		System.out.println(s.getListeVisites(2));
		System.out.println(s.getListeVisites(3));
		// System.out.println(s.afficherFilleAttente());
		System.out.println("-------------------------");
		Medecin m = new Medecin("test", "test", "rayhan", 1);
		m.salleDispo();
		System.out.println(s.getFileAttente());
		System.out.println(h.getSalles().get(0).getVisites());
		m.salleDispo();
		System.out.println(s.getFileAttente());
		System.out.println(h.getSalles().get(0).getVisites());
		m.salleDispo();
		System.out.println(s.getFileAttente());
		System.out.println(h.getSalles().get(0).getVisites());
		m.sauvegarderVisites();
		System.out.println(s.getFileAttente());
		System.out.println(h.getSalles().get(0).getVisites());

	}

}
