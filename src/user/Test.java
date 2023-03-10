package user;

import java.util.List;
import java.util.Scanner;

import dao.AuthentificationDAO;
import model.Administrateur;
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
				System.out.println("Veuillez entrer des charact?res ASCII !");
			}
			p = auth.verif(login, password);
			if (p != null) {
				System.out.println("Authentification a r?ussie !");
				System.out.println("Bienvenu " + p.getNom());
				// Prochain menu
				metier = p.getMetier();
				if (metier == 0)
					menuSecretaire(p);
				else if (metier == -1)
					menuAdmin(p);
				else
					menuMedecin(p);
			} else
				System.out.println("L'authentification a ?chou?e.");
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
					+ "5) Modifier les coordonn?es d'un patient\n" + "0) Quitter le menu");
			try {
				choix = sc.nextInt();
			} catch (Exception e) {
				System.out.println("Veuillez entrer un carat?re num?rique !");
				break;
			}

			switch (choix) {
			case 0:
				System.out.println("Retour au menu pr?c?dent.");
				break;
			case 1:
				s.ajouterPatientFilleAttente(formulairePatient(false));
				break;
			case 2:
				afficherListe(s.getFileAttente());
				break;
			case 3:
				System.out.println(s.afficherProchainPatient());
				break;
			case 4:
				int idPatient = personneId("patient");
				if (s.getListeVisites(idPatient).size() != 0)
					afficherListe(s.getListeVisites(idPatient));
				else
					System.out.println("Patient sans visites ou mauvais ID !");
				break;
			case 5:
				int idPatient2 = personneId("patient");
				String tel = "";
				String adresse = "";
				System.out.println("Veuillez renseigner les coordonn?es du patient :");
				try {
					System.out.println("Telephone");
					tel = sc.nextLine();
					System.out.println("Adresse");
					adresse = sc.nextLine();
				} catch (Exception e) {
					System.out.println("Veuillez entrer un carat?re num?rique ou ASCII !");
					break;
				}
				s.modifierPatient(idPatient2, tel, adresse);
				break;
			default:
				System.out.println("Veuillez entrer un chiffre correspondant ? votre choix !");
				break;
			}
		}
	}

	private static Patient formulairePatient(boolean modif) {
		Scanner scInt = new Scanner(System.in);
		Scanner scString = new Scanner(System.in);
		Patient p = null;
		int id = 0, age;
		String nom, prenom, tel, adresse;

		do {
			System.out.println("Veuillez renseigner les informations du patient :");
			try {
				if (!modif) {
					System.out.println("Id");
					id = scInt.nextInt();
				}
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
				System.out.println("Veuillez entrer un carat?re num?rique ou ASCII !");
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
				System.out.println("Veuillez entrer un carat?re num?rique !");
			}

			switch (choix) {
			case 0:
				System.out.println("Retour au menu pr?c?dent.");
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
					System.out.println("M?decin sans visites !");
				break;
			default:
				System.out.println("Veuillez entrer un chiffre correspondant ? votre choix !");
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

	private static void menuAdmin(Personnel p) {
		Administrateur a = new Administrateur(p.getLogin(), p.getPassword(), p.getNom(), p.getMetier());
		Scanner sc = new Scanner(System.in);
		int choix = -1;

		while (choix != 0) {
			System.out.println("Veuillez choisir une action :");
			System.out.println("1) Supprimer un patient\n" + "2) Modifier un patient\n"
					+ "3) Cr?er un nouveau patient\n" + "4) Cr?er un nouveau m?decin\n" + "5) Supprimer un m?decin\n"
					+ "6) Cr?er un nouveau secr?taire\n" + "7) Supprimer un secr?taire\n" + "0) Quitter le menu");
			try {
				choix = sc.nextInt();
			} catch (Exception e) {
				System.out.println("Veuillez entrer un carat?re num?rique !");
			}

			switch (choix) {
			case 0:
				System.out.println("Retour au menu pr?c?dent.");
				break;
			case 1:
				a.supprimerPatient(personneId("patient"));
				break;
			case 2:
				a.modifierPatient(personneId("patient"), formulairePatient(true));
				break;
			case 3:
				a.creerPatient(formulairePatient(false));
				break;
			case 4:
				Personnel m = formulairePersonnel(-2);
				a.creerMedecin(new Medecin(m.getLogin(), m.getPassword(), m.getNom(), m.getMetier()));
				break;
			case 5:
				a.supprimerMedecin(personneLogin("m?decin"));
				break;
			case 6:
				Personnel s = formulairePersonnel(0);
				a.creerSecretaire(new Secretaire(s.getLogin(), s.getPassword(), s.getNom(), s.getMetier()));
				break;
			case 7:
				a.supprimerSecretaire(personneLogin("secr?taire"));
				break;
			default:
				System.out.println("Veuillez entrer un chiffre correspondant ? votre choix !");
				break;
			}
		}
	}

	private static int personneId(String cible) {
		Scanner sc = new Scanner(System.in);
		int id = -1;

		System.out.println("Veuillez entrer l'ID du " + cible + " :");
		try {
			id = sc.nextInt();
		} catch (Exception e) {
			System.out.println("Veuillez entrer un carat?re num?rique !");
		}

		return id;
	}

	private static String personneLogin(String cible) {
		Scanner sc = new Scanner(System.in);
		String login = null;

		System.out.println("Veuillez entrer le login du " + cible + " :");
		try {
			login = sc.nextLine();
		} catch (Exception e) {
			System.out.println("Veuillez entrer un carat?re ASCII !");
		}

		return login;
	}

	private static Personnel formulairePersonnel(int typePersonnel) {
		Scanner scInt = new Scanner(System.in);
		Scanner scString = new Scanner(System.in);
		Personnel p = null;
		String login, password, tel;
		int metier = typePersonnel;

		do {
			System.out.println("Veuillez renseigner les informations du personnel :");
			try {
				System.out.println("Login");
				login = scString.nextLine();
				System.out.println("Password");
				password = scString.nextLine();
				System.out.println("Nom");
				tel = scString.nextLine();
				if (metier != 0) {
					while (metier != 1 && metier != 2) {
						System.out.println("Salle : 1 ou 2");
						metier = scInt.nextInt();
					}
				}

				p = new Personnel(login, password, tel, metier);
			} catch (Exception e) {
				System.out.println("Veuillez entrer un carat?re num?rique ou ASCII !");
				break;
			}
		} while (p == null);

		return p;
	}
}
