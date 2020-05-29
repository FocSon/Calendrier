package modeles;

import java.util.Collection;
import java.util.Iterator;

import javax.swing.table.DefaultTableModel;

/**
 * <b>Mod�le de la JTable du panel PanelAffichage.<br>
 * Appartient au package modeles.</b>
 * 
 * @see javax.swing.table.DefaultTableModel
 * 
 * @author Antoine Limerutti
 *
 * @version 1.0
 */
public class ModeleTable extends DefaultTableModel{
	/**
	 * Date de la semaine construite par le mod�le.
	 *
	 * @see modeles.Date
	 */
	private Date date;
	
	/**
	 * Construit un objet ModeleTable qui vas � partir d'un date d�terminer la semaine � laquelle appartiens cette date et trouver les �l�ments de l'agenda correspondant � ces dates.
	 * 
	 * @param parDate Date qui vas permettre de d�terminer la semaine � affecter au mod�le.
	 * @param agenda Agenda dans lequel on vas chercher les Evenements qui correspondent aux dates de la semaine.
	 *
	 * @see modeles.Date
	 * @see modeles.Agenda
	 * 
	 * @author Antoine Limerutti
	 */
	public ModeleTable(Date parDate, Agenda agenda) {
		setColumnCount(7);
		setRowCount(15);
		
		//agenda.triSelection();
		
		String [] identifieur = new String[7];
		
		date = parDate.datePremierJourSemaine();

		for(int i=0; i<7; i++) {
			identifieur[i]=date.toString();
			date=date.dateDuLendemain();
		}
		
		date = parDate.datePremierJourSemaine();
		
		Collection<Evenement> eventSemaine = agenda.getEvenementsSemaine(date);

		//si il y a un TreeSet pour la semaine choisie
		if(eventSemaine!=null) {
			Iterator<Evenement> iterateur = eventSemaine.iterator();
			
			Evenement temp;
			int ligne=0, colonne=0;
			
			while(iterateur.hasNext()) {
				temp = iterateur.next();

				//si l'�l�ment relev� n'appartiens pas au jour en cours
				if(temp.getDate().compareTo(date)!=0) {
					while(date.compareTo(temp.getDate())!=0) {	//tant que le jour que l'on traite n'est pas le bon
						colonne++;
						date=date.dateDuLendemain();
					}
					ligne=0;
				}
				setValueAt(temp,ligne,colonne);
				ligne++;
			}
		}
		date = parDate.datePremierJourSemaine();
		setColumnIdentifiers(identifieur);
	}
	
	/**
	 * Verifie si il est n�c�ssaire de recr�er le mod�le apr�s un changement de date.
	 * 
	 * @param newDate Date qui, apr�s comparaison avec la date du champ date vas permettre de dire si il faut actualiser ou non le modele.
	 * 
	 * @return bool�en qui indique si il est n�c�ssaire de recontruire un nouveau mod�le.
	 *
	 * @see modeles.Date
	 * 
	 * @author Antoine Limerutti
	 */
	public boolean refaireTab(Date newDate) {
		if(newDate.datePremierJourSemaine().compareTo(date)==0)
			return false;
		return true;
	}
	
	/**
	 * V�rifie et retourne le type de donn�e dans une colonne.
	 * 
	 * @param col Entier qui indique le num�ro de colonne dont il vas falloir v�rifier le type.
	 * 
	 * @return Retourne la classe de la colonne s�lectionn�e.
	 * 
	 * @author Antoine Limerutti
	 */
	public Class<Evenement> getColumnClass(int col) {
		return Evenement.class;
	}
}
