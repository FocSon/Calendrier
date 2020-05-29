package modeles;

import java.util.Collection;
import java.util.Iterator;

import javax.swing.table.DefaultTableModel;

/**
 * <b>Modèle de la JTable du panel PanelAffichage.<br>
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
	 * Date de la semaine construite par le modèle.
	 *
	 * @see modeles.Date
	 */
	private Date date;
	
	/**
	 * Construit un objet ModeleTable qui vas à partir d'un date déterminer la semaine à laquelle appartiens cette date et trouver les éléments de l'agenda correspondant à ces dates.
	 * 
	 * @param parDate Date qui vas permettre de déterminer la semaine à affecter au modèle.
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

				//si l'élément relevé n'appartiens pas au jour en cours
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
	 * Verifie si il est nécéssaire de recréer le modèle après un changement de date.
	 * 
	 * @param newDate Date qui, après comparaison avec la date du champ date vas permettre de dire si il faut actualiser ou non le modele.
	 * 
	 * @return booléen qui indique si il est nécéssaire de recontruire un nouveau modèle.
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
	 * Vérifie et retourne le type de donnée dans une colonne.
	 * 
	 * @param col Entier qui indique le numéro de colonne dont il vas falloir vérifier le type.
	 * 
	 * @return Retourne la classe de la colonne sélectionnée.
	 * 
	 * @author Antoine Limerutti
	 */
	public Class<Evenement> getColumnClass(int col) {
		return Evenement.class;
	}
}
