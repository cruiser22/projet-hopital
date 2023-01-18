package user;

import model.Hopital;
import model.Medecin;
import model.Patient;
import model.Salle;
import model.Secretaire;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		
		System.out.println("Init main");

		metierMenu();
		//testMedecin();
		//testSecretaire();
		//testMedecin();
		//test package
	}

	private static void metierMenu() {
		Scanner sc = new Scanner(System.in);
		int choix = -1;
		
		// menu Secrétaire/Médecin
		while (choix != 0) {
			System.out.println("Veuillez choisir sous quel métier s'authentifier :");
			System.out.println("1) Médecin en Salle 1\n"
							 + "2) Médecin en Salle 2\n"
							 + "3) Secrétaire\n"
							 + "0) Quitter le menu");
			try {
				choix = sc.nextInt();
			} catch (Exception e) {
				System.out.println("Veuillez entrer un caratère numérique !");
				break;
			}
			
			if (choix == 0)
				System.out.println("Merci de votre visite, au revoir !");
			else if (choix == 1 || choix == 2)
				authMenu(choix);
			else
				System.out.println("Veuillez entrer un chiffre correspondant à votre choix !");
		}
			
	}
	
	// menu authentification
	private static void authMenu(int choix) {
		Scanner sc = new Scanner(System.in);
		Authentification auth  = new Authentification();
		String login;
		String password;
		int continuer = 0;
		
		try {
			System.out.println("Veuillez saisir votre nom :");
			login = sc.nextLine();
			System.out.println("Veuillez saisir votre mot de passe :");
			password = sc.nextLine();
		} catch (Exception e) {
			System.out.println("Veuillez entrer des charactères ASCII !");
		}
		if (auth.verification(choix, login, password)) {
			System.out.println("Authentification a réussie !");
			//Prochain menu
			if (choix == 3)
				menuSecretaire();
			else
				menuMedecin(choix);
		} else
			System.out.println("L'authentification a échouée.");
	}
	
	private static void menuSecretaire() {
		Secretaire s = new Secretaire();
		Scanner sc = new Scanner(System.in);
		int choix = -1;
		
		while (choix != 0) {
			System.out.println("Veuillez choisir une action :");
			System.out.println("1) Ajouter un patient en file d'attente\n"
							 + "2) Afficher la file d'attente\n"
							 + "3) Afficher le prochain patient\n"
							 + "4) Afficher les visites d'un patient\n"
							 + "0) Quitter le menu");
			try {
				choix = sc.nextInt();
			} catch (Exception e) {
				System.out.println("Veuillez entrer un caratère numérique !");
				break;
			}
			
			switch(choix){
			case 0 :
				System.out.println("Retour au menu précédent.");
				break;
			case 1 :
				s.addFileAttente(formulairePatient());
				break;
			case 2 :
				s.afficherFileAttente();
				break;
			case 3 :
				s.afficherNextPatient();
				break;
			case 4 :
				s.afficherVisitesPatient();
				break;
			default :
				System.out.println("Veuillez entrer un chiffre correspondant à votre choix !");
				break;
			}
		}
	}
	
	private static Patient formulairePatient() {
		Scanner sc = new Scanner(System.in);
		Patient p;
		int id, age;
		String nom, prenom, tel, adresse;
		
		do {
			System.out.println("Veuillez renseigner les informations du patient :");
			try {
				System.out.println("Id");
				id = sc.nextInt();
				System.out.println("Nom");
				nom = sc.nextLine();
				System.out.println("Prenom");
				prenom = sc.nextLine();
				System.out.println("Age");
				age = sc.nextInt();
				System.out.println("Telephone");
				tel = sc.nextLine();
				System.out.println("Adresse");
				adresse = sc.nextLine();
				p = new Patient(id, nom, prenom, age, tel, adresse);
			} catch (Exception e) {
				System.out.println("Veuillez entrer un caratère numérique ou ASCII !");
				break;
			}
		} while(p != null);
		
		return p;
	}
	
	private static void menuMedecin(int salle) {
		Medecin m = new Medecin();
		Scanner sc = new Scanner(System.in);
		int choix = -1;
		
		while (choix != 0) {
			System.out.println("Veuillez choisir une action :");
			System.out.println("1) Rendre la salle disponible\n"
							 + "2) Afficher la file d'attente\n"
							 + "3) Sauvegarder ses visites\n"
							 + "4) Afficher sa liste des visites\n"
							 + "0) Quitter le menu");
			try {
				choix = sc.nextInt();
			} catch (Exception e) {
				System.out.println("Veuillez entrer un caratère numérique !");
			}
			
			switch(choix){
			case 0 :
				System.out.println("Retour au menu précédent.");
				break;
			case 1 :
				m.salleDispo(salle);
				break;
			case 2 :
				m.afficherFileAttente();
				break;
			case 3 :
				m.sauvegarderVisites();
				break;
			case 4 :
				m.afficherListeVisites();
				break;
			default :
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
		System.out.println(s.afficherFilleAttente());
		s.ajouterPatientFilleAttente(p1);
		s.ajouterPatientFilleAttente(p2);
		System.out.println(s.afficherFilleAttente());
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
		// System.out.println(s.afficherFilleAttente());

		Medecin m = new Medecin("test", "test", "rayhan", 1);
		m.salleDispo();
		System.out.println(s.afficherFilleAttente());
		System.out.println(h.getSalles().get(0).getVisites());
		m.salleDispo();
		System.out.println(s.afficherFilleAttente());
		System.out.println(h.getSalles().get(0).getVisites());
		m.salleDispo();
		System.out.println(s.afficherFilleAttente());
		System.out.println(h.getSalles().get(0).getVisites());
		m.sauvegarderVisites();
		System.out.println(s.afficherFilleAttente());
		System.out.println(h.getSalles().get(0).getVisites());
	}

}
