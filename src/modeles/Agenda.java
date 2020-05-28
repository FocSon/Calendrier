package modeles;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * Abstraction d'un agenda contenant des Evenements.
 * Appartient au package Modeles.
 * Cette classe est s�rialisable.
 * 
 * @author Antoine Limerutti
 * 
 * @see java.io.Serializable
 * 
 * @version 1.0
 */
public class Agenda implements Serializable{
	/**
	 * HashMap contenant en clef le num�ro de semaine et en donn�e un TreeSet contenant les Evenements de la semaine.
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
	 * D�duit le num�ro de semaine de la date de l'evenement, r�cup�re le TreeSet correspondant et y ajoute l'�v�nement.
	 * 
	 * @param event �venement � ajouter au TreeSet correspondant.
	 *
	 * @see modeles.Evenement
	 *
	 * @author Antoine Limerutti
	 */
	public void ajouterEvent(Evenement event) {
		//si la HashMap ne contient pas la clef on l'ajoute
		if(! hashMapEvent.containsKey(event.getDate().getSemaine())) {
			hashMapEvent.put(event.getDate().getSemaine(), new TreeSet<Evenement>());
		}

		//on r�cup�re le TreeSet depuis la HashMap puis on lui ajoute l'�v�nement
		hashMapEvent.get(event.getDate().getSemaine()).add(event);
	}
	
	/**
	 * Retourne le TreeSet dont la Date fournie en param�tre est clef.
	 * 
	 * @param date Date dont on vas d�duire le num�ro de semaine clef du TreeSet � retourner.
	 *
	 * @return TreeSet dont le num�ro de semaine de la date fournie est clef dans la HashMap
	 *
	 * @see modeles.Date
	 * @see java.util.Collection
	 *
	 * @author Antoine Limerutti
	 */
	public Collection <Evenement> getEvenementsSemaine (Date date){
		return hashMapEvent.get(date.getSemaine());
	}
	
	/**
	 * Cr�e une chaine de charact�re pr�cisant les �v�nements.
	 * 
	 * @return String correspondant aux champs de l'objet appellant.
	 * 
	 * @author Antoine Limerutti
	 */
	public String toString() {
		String agenda="";
		Evenement event=null;
		
		for(int compteur=0; compteur<52; compteur++) 
			if(hashMapEvent.containsKey(compteur)) //Si la clef est contenue dans la HashMap, on l'ajoute � la chaine agenda
				agenda+="\n\nSemaine " + compteur + " :\n" + hashMapEvent.get(compteur);
			
		return agenda;
	}
}
