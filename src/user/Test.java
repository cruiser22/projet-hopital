package user;

import model.Hopital;
import model.Medecin;
import model.Patient;
import model.Salle;
import model.Secretaire;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// testSecretaire();
		testMedecin();
		// test package
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
		System.out.println("-------------------------");
		System.out.println(s.afficherListesVisite(1));
		System.out.println(s.afficherListesVisite(2));
		System.out.println(s.afficherListesVisite(3));
		// System.out.println(s.afficherFilleAttente());
		System.out.println("-------------------------");
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
		System.out.println(m.afficherListesVisite());

	}

}
