package modeles;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * <b>Abstraction d'un agenda contenant des Evenements.<br>
 * Appartient au package modeles.<br>
 * Cette classe est sérialisable.</b>
 * 
 * @author Antoine Limerutti
 * 
 * @see java.io.Serializable
 * 
 * @version 1.0
 */
public class Agenda implements Serializable{
	/**
	 * HashMap contenant en clef le numéro de semaine et en donnée un TreeSet contenant les Evenements de la semaine.
	 *
	 * @see java.util.HashMap
	 */
	private HashMap<Integer, TreeSet<Evenement>> hashMapEvent;
	
	/**
	 * Construit un objet Agenda en instanciant une HashMap.
	 *
	 * @author Antoine Limerutti
	 */
	public Agenda() {
		hashMapEvent =new HashMap<Integer, TreeSet<Evenement>>();
	}
	
	/**
	 * Déduit le numéro de semaine de la date de l'evenement, récupère le TreeSet correspondant et y ajoute l'évènement.
	 * 
	 * @param event Evenement à ajouter au TreeSet correspondant.
	 *
	 * @see modeles.Evenement
	 *
	 * @author Antoine Limerutti
	 */
	public void ajouterEvent(Evenement event) {
		//si la HashMap ne contient pas la clef on l'ajoute
		if(! hashMapEvent.containsKey(event.getDate().getNumeroSemaine())) {
			hashMapEvent.put(event.getDate().getNumeroSemaine(), new TreeSet<Evenement>());
		}

		//on récupère le TreeSet depuis la HashMap puis on lui ajoute l'évènement
		hashMapEvent.get(event.getDate().getNumeroSemaine()).add(event);
	}
	
	/**
	 * Retourne le TreeSet dont la Date fournie en paramètre est clef.
	 * 
	 * @param date Date dont on vas déduire le numéro de semaine clef du TreeSet à retourner.
	 *
	 * @return TreeSet dont le numéro de semaine de la date fournie est clef dans la HashMap
	 *
	 * @see modeles.Date
	 * @see java.util.Collection
	 *
	 * @author Antoine Limerutti
	 */
	public Collection <Evenement> getEvenementsSemaine (Date date){
		return hashMapEvent.get(date.getNumeroSemaine());
	}
	
	/**
	 * Crée une chaine de charactère précisant les évènements.
	 * 
	 * @return String correspondant aux champs de l'objet appellant.
	 * 
	 * @author Antoine Limerutti
	 */
	public String toString() {
		String agenda="";
		Evenement event=null;
		
		for(int compteur=0; compteur<52; compteur++) 
			if(hashMapEvent.containsKey(compteur)) //Si la clef est contenue dans la HashMap, on l'ajoute à la chaine agenda
				agenda+="\n\nSemaine " + compteur + " :\n" + hashMapEvent.get(compteur);
			
		return agenda;
	}
}
