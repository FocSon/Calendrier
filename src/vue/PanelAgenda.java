package vue;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controleur.Controleur;
import modeles.Agenda;
import modeles.Date;
import modeles.LectureEcriture;

/**
 *Panel comportant le panelAffichage, le panelFormulaire et le panelFilsCalendrier.
 *Appartient au package Vue.
 * 
 *@author Antoine Limerutti
 * 
 *@see javax.swing.JPanel
 *@see java.awt.event.ActionListener;
 *
 *@version 1.0
 */
public class PanelAgenda extends JPanel implements ActionListener{
	/**
	 * Fenêtre qui contient le panel (fenêtre mère).
	 *
	 * @see vue.FenetreAgenda
	 */
	private FenetreAgenda fenetreMere;

	/**
	 * CardLayout qui contient le panelAffichage, le panelFormulaire et le panelFilsCalendrier.
	 *
	 * @see java.awt.CardLayout
	 */
	private CardLayout card;

	/**
	 * Construit un objet PanelAgenda en créant et lui associant également les différents panels qu'il vas contenir.
	 *
	 * @param parFenetreMere Fenêtre mère.
	 *
	 * @see vue.FenetreAgenda
	 *
	 * @author Antoine Limerutti
	 */
	public PanelAgenda(FenetreAgenda parFenetreMere) {
		fenetreMere = parFenetreMere;
		card = new CardLayout();
		setLayout(card);
			
		Agenda agenda=null;

		//Un agenda est déjà sauvegardé ?
		File fichierAgenda = new File("save" + File.separator + "agenda.ser");
		if(fichierAgenda.length()==0) {
			agenda = new Agenda();
		}
		else {
			agenda=(Agenda) LectureEcriture.lecture(fichierAgenda);
		}
				
		PanelFilsCalendrier calendrier = new PanelFilsCalendrier();
		PanelFormulaire formulaire = new PanelFormulaire(new Date());
		PanelAffichage panelAffichage = new PanelAffichage(agenda);
		
		Controleur controleur = new Controleur(agenda, formulaire, calendrier, panelAffichage);

		add(calendrier, 0);
		add(formulaire, 1);
		add(panelAffichage, 2);
	}

	/**
	 * Traite un évènement pour savoir quel panel l'utilisateur veut afficher.
	 *
	 * @param event Evenement qui vas être traité pour savoir quel panel afficher.
	 *
	 * @see java.awt.ActiveEvent
	 *
	 * @author Antoine Limerutti
	 */
	public void actionPerformed(ActionEvent event) {
		if(event.getActionCommand().compareTo("Calendrier")==0) {
			card.first(this);
		}
		
		else if(event.getActionCommand().compareTo("Evenement")==0) {
			card.first(this);
			card.next(this);
		}
		
		else if(event.getActionCommand().compareTo("Semaine")==0) {
			card.last(this);
		}
		
		else if(event.getActionCommand().compareTo("Fermer")==0) {
			int quitter = JOptionPane.showConfirmDialog(null, "Etes-vous sûre de vouloir quitter l'application ?", "test", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(quitter==JOptionPane.OK_OPTION)
				fenetreMere.dispose();
		}
	}
}
