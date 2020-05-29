package controleur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Timer;

import modeles.Agenda;
import modeles.BoutonDate;
import modeles.Date;
import modeles.Evenement;
import modeles.LectureEcriture;

import vue.PanelAffichage;
import vue.PanelFilsCalendrier;
import vue.PanelFormulaire;
import vue.PanelMois;

/**
 * <b>Contôleur qui vas traiter les actions qui impliquent les différents packages.<br>
 * Elle appartient au package controleur.</b>
 * 
 * @author Antoine Limerutti
 * 
 * @see java.awt.event.ActionListener
 *
 * @version 1.0
 */
public class Controleur implements ActionListener{
	/**
	 * Agenda qui vas être utilisé dans le traitement des actions.
	 *
	 * @see modeles.Agenda
	 */
	private Agenda agenda;
	
	/**
	 * PanelFormulaire duquel on vas extraire les données des évènements à ajouter dans l'agenda.
	 *
	 * @see vue.PanelFormulaire
	 */
	private PanelFormulaire formulaire;
	
	/**
	 * Calendrier qui peut provoquer un changement de date du formulaire ainsi qu'une actualisation du panelAffichage.
	 *
	 * @see PanelFilsCalendrier
	 */
	private PanelFilsCalendrier calendrier;
	
	/**
	 * Dernier BoutonDate cliqué. Variable utilisée pour redonner une couleur normale au bouton.
	 *
	 * @see modeles.BoutonDate
	 */
	private BoutonDate boutonSelect;
	
	/**
	 * PanelAffichage qui vas s'actualiser lors d'un changement de semaine.
	 *
	 * @see PanelAffichage
	 */
	private PanelAffichage panelAffichage;
	
	/**
	 * Entier utilisé pour retenir le nombre de cllignottement en cas d'erreur de saisie du formulaire.
	 */
	private int compteur=0;
	
	/**
	 * Timer qui vas gérer la durée des clignotements des éléments du formulaire en cas de mauvaise saisie.
	 *
	 * @see javax.swing.Timer
	 */
	private Timer[] clignotement;

	 /**
	 * Crée un objet Controleur avec les arguments fournis, s'enregistre à lécoute du formulaire, du calendrier et  instancie le timer.
	 *
	 * @param parAgenda Agenda dans lequel il vas falloir ajouter les nouveaux évènements.
	 * @param parFormulaire PanelFormulaire depuis lequel on vas récupérer les données à donner aux nouveaux évènements.
	 * @param parCalendrier PanelFilsCalendrier dans lequel on vas changer le mois courant.
	 * @param parPanelAffichage PanelAffichage dans lequel on vas actualiser le modèle de la table après ajout d'un évènement.
	 *
	 * @see modeles.Agenda
	 * @see vue.PanelFormulaire
	 * @see vue.PanelFilsCalendrier
	 * @see vue.PanelAffichage
	 *
	 * @author Antoine Limerutti
	 */
	public Controleur(Agenda parAgenda, PanelFormulaire parFormulaire, PanelFilsCalendrier parCalendrier, PanelAffichage parPanelAffichage) {
		agenda=parAgenda;
		formulaire=parFormulaire;
		calendrier=parCalendrier;
		panelAffichage = parPanelAffichage;
		
		formulaire.enregistreEcoute(this);
		calendrier.enregistreEcoute(this);
		
		clignotement = new Timer[7];
		for(int i=0; i<clignotement.length; i++) {
			clignotement[i]=new Timer(1,this);
			clignotement[i].setInitialDelay(250);
			clignotement[i].setRepeats(false);
		}
		/* Correspondance des cases du tableau (éléments à faire clignotter):
		 * 0:combo
		 * 1:Titre
		 * 2:Lieu
		 * 3:combo, Titre
		 * 4:combo, Lieu
		 * 5:Titre, Lieu
		 * 6:tout
		 */
	}

	/**
	 * Action performed qui vas être invoqué lors d'un action sur le calendrier ou sur le formulaire.
	 *
	 * @param event ActionEvent évènement qui a eu lieu et qu'il vas falloit traiter
	 *
	 * @see java.awt.ActiveEvent
	 *
	 * @author Antoine Limerutti
	 */
	public void actionPerformed(ActionEvent event) { //Les trois if ont pour but de faire clignotter les éléments
		if(event.getSource()==clignotement[0] || event.getSource()==clignotement[1] || event.getSource()==clignotement[2] || event.getSource()==clignotement[3] || event.getSource()==clignotement[4] || event.getSource()==clignotement[5] || event.getSource()==clignotement[6]) {
			if(event.getSource()==clignotement[6] || event.getSource()==clignotement[0] || event.getSource()==clignotement[3] || event.getSource()==clignotement[4]) {
				formulaire.getHeureDebut().setBackground(compteur%2==0 ? new Color(205,0,0) : new Color(255,255,255));
				formulaire.getHeureFin().setBackground(compteur%2==0 ? new Color(205,0,0) : new Color(255,255,255));
				formulaire.getMinutesDebut().setBackground(compteur%2==0 ? new Color(205,0,0) : new Color(255,255,255));
				formulaire.getMinutesFin().setBackground(compteur%2==0 ? new Color(205,0,0) : new Color(255,255,255));
			}
			if (event.getSource()==clignotement[6] || event.getSource()==clignotement[1] || event.getSource()==clignotement[3] || event.getSource()==clignotement[5]) {
				formulaire.getTitreTxt().setBackground(compteur%2==0 ? new Color(205,0,0) : new Color(255,255,255));
			}
			if (event.getSource()==clignotement[6] || event.getSource()==clignotement[2] || event.getSource()==clignotement[4] || event.getSource()==clignotement[5]) {
				formulaire.getLieuTxt().setBackground(compteur%2==0 ? new Color(205,0,0) : new Color(255,255,255));
			}

			compteur++;					//compteur qui vas permettre d'arrêter le clignottement
			if(compteur<4) {			//tant que le compteur est inférieur à 4, on relance le clignottement
				Timer relaunch = (Timer) event.getSource();
				relaunch.restart();
			}
		}
		
		else if(event.getSource() == formulaire.getAjouter()) {	//si clic sur le bouton ajouter dans le formulaire
			String titre = formulaire.getTitreTxt().getText();	//on récupère les champs pour créer un nouvel évènement
			String lieu = formulaire.getLieuTxt().getText();
			
			int heureDebut = formulaire.getHeureDebut().getSelectedIndex();
			int heureFin = formulaire.getHeureFin().getSelectedIndex();
			int minutesDebut = formulaire.getMinutesDebut().getSelectedIndex();
			int minutesFin = formulaire.getMinutesFin().getSelectedIndex();
			
			Date date = formulaire.getDate();

			//on vérifie que les données entrées sont cohérentes (si oui, on ajoute l'évenement, on reset le formulaire et on sauvegarde)
			if((heureDebut<heureFin || (heureDebut==heureFin && minutesDebut<=minutesFin)) && !titre.equals("") && !lieu.equals("")) {
				agenda.ajouterEvent(new Evenement(date,titre, lieu, heureDebut, heureFin, minutesDebut, minutesFin));
				formulaire.reset();
				panelAffichage.reinitModele(date);
				File fichierAgenda = new File("save" + File.separator + "agenda.ser");
				LectureEcriture.ecriture(fichierAgenda, agenda);
				System.out.println(agenda.toString());

			}
			//sinon, on regarde quel clignotement il faut invoquer.
			else {
				compteur=0;
				if((heureDebut>heureFin || (heureDebut==heureFin && minutesDebut>minutesFin)) && titre.equals("") && lieu.equals(""))
					clignotement[6].start();
				else if((heureDebut>heureFin || (heureDebut==heureFin && minutesDebut>minutesFin)) && titre.equals(""))
					clignotement[3].start();
				else if((heureDebut>heureFin || (heureDebut==heureFin && minutesDebut>minutesFin)) && lieu.equals(""))
					clignotement[4].start();
				else if(titre.equals("") && lieu.equals(""))
					clignotement[5].start();
				else if(heureDebut>heureFin || (heureDebut==heureFin && minutesDebut>minutesFin))
					clignotement[0].start();
				else if(titre.equals(""))
					clignotement[1].start();
				else if(lieu.equals(""))
					clignotement[2].start();
			}
		}
		//si clic pour faire défiler le calendrier
		else if(event.getSource()==calendrier.getBoutonsTab(0) || event.getSource()==calendrier.getBoutonsTab(1) || event.getSource()==calendrier.getBoutonsTab(2) || event.getSource()==calendrier.getBoutonsTab(3)) {
			
			if(event.getSource()==calendrier.getBoutonsTab(0)) {
				calendrier.getCardLayout().first(calendrier.getPanelNord());
				calendrier.setMoisAffiche((new Date()).getMois());
			}
			
			else if(event.getSource()==calendrier.getBoutonsTab(1)) {
				calendrier.getCardLayout().previous(calendrier.getPanelNord());
				calendrier.setMoisAffiche(calendrier.getMoisAffiche()-1);
				if(calendrier.getMoisAffiche()<1)
					calendrier.setMoisAffiche(12);
			}			
			
			else if (event.getSource()==calendrier.getBoutonsTab(2)) {
				calendrier.getCardLayout().next(calendrier.getPanelNord());
				calendrier.setMoisAffiche((calendrier.getMoisAffiche()+1)%13);
				if(calendrier.getMoisAffiche()==0)
					calendrier.setMoisAffiche(calendrier.getMoisAffiche()+1);
			}
			
			else if (event.getSource()==calendrier.getBoutonsTab(3)) {
				calendrier.getCardLayout().last(calendrier.getPanelNord());
				calendrier.setMoisAffiche((new Date()).getMois()-1);
				if(calendrier.getMoisAffiche()==0)
					calendrier.setMoisAffiche(calendrier.getMoisAffiche()+1);
			}
			calendrier.remove(calendrier.getPanelCentre());
			calendrier.setPanelCentre(new PanelMois(calendrier.getMoisAffiche()));
			calendrier.add(calendrier.getPanelCentre(), BorderLayout.CENTER);
			calendrier.getPanelCentre().enregistreEcoute(this);
		}

		//si aucune des actions ci-dessus, alors clic sur un boutonDate de la classe panelMois
		else {

			//si bouton cliqué précédement, on lui remet une couleur normale
			if(boutonSelect != null && boutonSelect.getDate().compareTo(new Date())!=0) 
				boutonSelect.setBackground(new Color(217,217,217));

			//on modifie la couleur du boutonDate sélectionné
			boutonSelect = (BoutonDate) event.getSource();	
			boutonSelect.setBackground(new Color(191,191,191));
			
			formulaire.setDate(boutonSelect.getDate().getJour(), boutonSelect.getDate().getMois(), boutonSelect.getDate().getAnnee());
			formulaire.reset();

			//besoin de refaire le modèle de la table dans le panel panelAffichage ?
			if(panelAffichage.getModele().refaireTab(boutonSelect.getDate()))
				panelAffichage.reinitModele(boutonSelect.getDate());
			
			calendrier.revalidate();
			calendrier.repaint();
		}
		
	}
}
