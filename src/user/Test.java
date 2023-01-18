package user;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		
		System.out.println("Init main");

		modeMenu();
	}

	private static void modeMenu() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int choix = -1;
		
		// menu Secr�taire/M�decin
		while (choix != 0) {
			System.out.println("Veuillez choisir sous quel m�tier s'authentifier :");
			System.out.println("1) M�decin en Salle 1\n"
							 + "2) M�decin en Salle 2\n"
							 + "3) Secr�taire\n"
							 + "0) Quitter le menu");
			try {
				choix = sc.nextInt();
			} catch (Exception e) {
				System.out.println("Veuillez entrer un carat�re num�rique !");
			}
			
			if (choix == 0)
				System.out.println("Merci de votre visite, au revoir !");
			else if (choix == 1 || choix == 2)
				authMenu(choix);
			else
				System.out.println("Veuillez entrer un chiffre correspondant � votre choix !");
		}
			
	}
	
	// menu authentification
	private static void authMenu(int choix) {
		Scanner sc = new Scanner(System.in);
		//Authentification auth  = new Authentification();
		String nom;
		String motdepasse;
		int continuer = 0;
		
		try {
			System.out.println("Veuillez saisir votre nom :");
			nom = sc.nextLine();
			System.out.println("Veuillez saisir votre mot de passe :");
			motdepasse = sc.nextLine();
		} catch (Exception e) {
			System.out.println("Veuillez entrer des charact�res ASCII !");
		}
		if (/*auth.verification(choix, nom, motdepasse)*/ true) {
			System.out.println("Authentification a r�ussie !");
			//Prochain menu
		} else {
			System.out.println("L'authentification a �chou�e.");
		}
	}

}
