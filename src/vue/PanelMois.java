package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Iterator;

import javax.swing.JPanel;

import controleur.Controleur;
import modeles.BoutonDate;
import modeles.CalendrierDuMois;
import modeles.Date;

/**
 * <b>Panel qui vas représenter le calendrier du mois avec des BoutonDate.<br>
 * Appartient au package vue.</b>
 *
 * @see javax.swing.JPanel
 *
 * @author Antoine Limerutti
 *
 * @version 1.0
 */
public class PanelMois extends JPanel{
	/**
	 * Calendrier du mois qui contient les dates que l'on vas associer aux objets BoutonDate.
	 *
	 * @see modeles.CalendrierDuMois
	 */
	private CalendrierDuMois calendrier;

	/**
	 * Tableau qui vas contenir les objets BoutonDate.
	 *
	 * @see modeles.BoutonDate
	 */
	private BoutonDate[] joursDuMois;

	/**
	 * Entier qui indique le numéro du mois à traiter.
	 */
	private int numMois;

	/**
	 * Constructeur de la classe PanelMois
	 *
	 * @param parNumMois Entier qui indique le numéro du mois à traiter.
	 *
	 * @author Antoine Limerutti
	 */
	public PanelMois(int parNumMois) {
		numMois=parNumMois;
		
		Date dateCourante = new Date();

		calendrier = new CalendrierDuMois(numMois, dateCourante.getAnnee());
		
		Iterator<Date> iterateur = calendrier.getDates().iterator();	//création des boutons
		joursDuMois = new BoutonDate[42];

		int i=0;
		Date date;
		
		Color gris = new Color(191,191,191);
		Color grisClaire = new Color(217,217,217);
		Color grisFonce = new Color(153,153,153);
		
		while(iterateur.hasNext()) {
			date = iterateur.next();
			joursDuMois[i] = new BoutonDate(date);
			joursDuMois[i].setVisible(true);
			if(date.compareTo(dateCourante)==0) 
				joursDuMois[i].setBackground(gris);
			else if(date.getMois()==numMois && dateCourante.compareTo(date)<0) 
				joursDuMois[i].setBackground(grisClaire);
			else
				joursDuMois[i].setBackground(grisFonce);
			i++;
		}
		
		if(i<=35)								//on définit le nb de cases dispo
			setLayout(new GridLayout(5,7));
		else
			setLayout(new GridLayout(7,6));
		
		for(int j=0;j<i;j++)					//on ajoute les bouttons
			add(joursDuMois[j]);


	}

	/**
	 * Met le contrôleur à l'écoute des boutons du tableau de BoutonDate bouton.
	 *
	 * @param controleur Controleur à mettre à l'écoute des boutons du tableau BoutonDate.
	 *
	 * @see controleur.Controleur
	 *
	 * @author Antoine Limerutti
	 */
	public void enregistreEcoute(Controleur controleur) {
		for(BoutonDate bouton : joursDuMois) {
			if(bouton!=null && bouton.getDate().compareTo(new Date())>=0 && bouton.getDate().getMois() == numMois) {
				bouton.addActionListener(controleur);
			}
		}
	}
}
