package modeles;

import java.util.Collection;
import java.util.TreeSet;
import java.util.Iterator;

/**
 * <b>Abstraction d'un calendrier.<br>
 * Appartient au package modèles.</b>
 * 
 * @author Isabelle Robba
 * 
 * @see java.util.Collection
 * @see modeles.Date
 *
 * @version 1.0
 */
public class CalendrierDuMois {
	/**
	 * Collection que vas contenir les dates du calendrier.
	 *
	 * @see java.util.Collection
	 */
	private Collection <Date> dates;
	
	/**
	 * Construit un CalendrierDuMois correspondant au mois et à l'année précisés en paramètre.
	 * 
	 * @param mois Mois duquel on veut le calendrier.
	 * @param annee Annee de laquelle on veut le calendrier.
	 * 
	 * @author Isabelle Robba
	 */
	public CalendrierDuMois (int mois, int annee) {
		dates = new TreeSet <Date> ();
		Date date = new Date (1, mois, annee);
		
		int indiceJour = date.getJourSemaine() == 1 ? 6 : date.getJourSemaine()-2;
		
		for (int indice = indiceJour ; indice>=0 ; indice--) {
			dates.add(date);
			date = date.dateDeLaVeille();
		}
		
		date = new Date (2,mois,annee);
		
		indiceJour = indiceJour+1 % 7 ;
		
		while (date.getMois () == mois) {
			while(indiceJour<7) {
				dates.add(date);
				date = date.dateDuLendemain();
				indiceJour++ ;
			}
			indiceJour=0;
		}
	}
	
	/**
	 * Crée une chaine de charactère contenant l'intégralité du calendrier.
	 * 
	 * @return String contenant l'intégralité du calendrier.
	 * 
	 * @author Isabelle Robba
	 */
	public String toString(){
		String chaine="";
		
		Iterator<Date> iterateur = dates.iterator();
		
		while(iterateur.hasNext()){
			Object date1 = iterateur.next();
			chaine+=date1.toString() + "\n";
		}
		
		return chaine;
	}

	/**
	 * Accesseur du champ dates.
	 *
	 * @return Collection qui correspond à la valeur du champ dates.
	 *
	 * @see modeles.Date
	 *
	 * @author Isabelle Robba
	 */
	public Collection <Date> getDates() {
		return dates;
	}

}
