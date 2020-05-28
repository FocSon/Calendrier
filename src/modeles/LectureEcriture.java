package modeles;

import java.io.FileOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Class qui vas permettre de sauvegarder et de charger des fichiers.
 * Appartient au package Modeles
 *
 * @author Isabelle Robba
 *
 * @version 1.0
 */
public class LectureEcriture {

	/**
	 * Méthode qui lit un fichier et retourne l'objet qu'il contient.
	 *
	 * @param parFichier File chemin vers le fichier lu.
	 *
	 * @return Ebject correspondant à l'objet lu.
	 *
	 * @see java.io.File
	 *
	 * @author Isabelle Robba
	 */
	public static Object lecture (File parFichier) {
		ObjectInputStream flux ;	// Ouverture du fichier en mode lecture
		Object objetLu = null;

		try {
			flux = new ObjectInputStream(new FileInputStream (parFichier));
			objetLu = (Object)flux.readObject ();
			flux.close ();
		}
		
		catch (ClassNotFoundException parException) {
			System.err.println (parException.toString ());
			System.exit (1);
		}
		
		catch (IOException parException) {
			System.err.println ("Erreur lecture du fichier " + parException.toString ());
			System.exit (1);
		}
		return objetLu ;
	}

	/** Méthode qui écrit un objet dans un fichier.
	 *
	 * @param parFichier File, le chemin vers le fichier dans lequel on écrit.
	 * @param parObjet Object étant l’objet à écrire dans le fichier.
	 *
	 * @see java.io.File
	 *
	 * @author Isabelle Robba
	 */
	public static void ecriture (File parFichier, Object parObjet) {
		ObjectOutputStream flux = null;
		// Ouverture du fichier en mode écriture
		try {
			flux = new ObjectOutputStream (new FileOutputStream (parFichier));
			flux.writeObject (parObjet);
			flux.flush ();
			flux.close ();
		}
		
		catch (IOException parException) {
			System.err.println ("Probleme a l’ecriture\n" + parException.toString ());
			System.exit (1);
		}
	}
}
