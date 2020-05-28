package client;

import modeles.ExceptionDate;
import vue.FenetreAgenda;

/**
 * Classe qui vas lancer le programme.
 * Appartient au package client.
 *
 * @author Antoine Limerutti
 *
 * @version 1.0
 */
public class Client {
	/**
	 * Main du programme.
	 *
	 * @param args arguments transmits au programme.
	 *
	 * @throws ExceptionDate Erreur sur une date.
	 *
	 * @see ExceptionDate
	 *
	 * @author Antoine Limerutti
	 */
	public static void main(String[]args) throws ExceptionDate {
		FenetreAgenda fenetre = new FenetreAgenda();
	}
}
