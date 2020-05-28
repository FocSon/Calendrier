package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.Controleur;
import modeles.Date;

/**
 * Panel qui affiche le panelMois.
 * Appartient au package Vue.
 *
 * @author Antoine Limerutti
 *
 * @see javax.swing.JPanel
 *
 * @see java.awt.event.ActionListener
 *
 * @version 1.0
 */
public class PanelFilsCalendrier extends JPanel implements ActionListener {
	/**
	 * Panel qui vas contenir et afficher le nom du mois
	 *
	 * @see javax.swing.JPanel
	 */
	private JPanel panelNord;

	/**
	 * Panel qui vas contenir le panelMois
	 *
	 * @see vue.PanelMois
	 */
	private PanelMois panelCentre;

	/**
	 * Panel contenant les boutons qui vont permettre de changer de mois
	 *
	 * @see javax.swing.JPanel
	 */
	private JPanel panelSud;

	/**
	 * CardLayout qui vas contenir tout les noms des mois
	 *
	 * @see java.awt.CardLayout
	 */
	private CardLayout cardLabelMois;

	/**
	 * Boutons qui permettent de changer le mois courant
	 *
	 * @see javax.swing.JButton
	 */
	private JButton [] boutons;

	/**
	 * Entier qui indique le mois courant
	 */
	private int moisAffiche;

	/**
	 * Constructeur qui vas construire et s'ajouter les différents panels qui le composent.
	 *
	 * @author Antoine Limerutti
	 */
	public PanelFilsCalendrier() {
		setLayout(new BorderLayout());

		panelSud = new JPanel();
		panelSud.setLayout(new GridLayout(1,4,4,0));
				
		String [] intituleBoutons = {"<<", "<", ">", ">>"};
		boutons = new JButton [intituleBoutons.length];
		
		for(int i=0; i<boutons.length; i++) {
			boutons[i] = new JButton(intituleBoutons[i]);
			boutons[i].setVisible(true);
			panelSud.add(boutons[i]);
		}
		this.add(panelSud, BorderLayout.SOUTH);
		
		
		panelNord = new JPanel();
		cardLabelMois = new CardLayout();
		panelNord.setLayout(cardLabelMois);
		
		JLabel [] labelsMois = new JLabel [12];
		String mois[]= {"Jan.", "Fev.", "Mars", "Avr.", "Mai", "Juin", "Juil.", "Août", "Sept.", "Oct.", "Nov.", "Dec."};

		//paramétrer les labels des mois et les ajouter au panelNord.
		for (int i=0; i<labelsMois.length; i++) {
			labelsMois[i] = new JLabel (mois[(i+(new Date()).getMois()-1)%12]);
			labelsMois[i].setHorizontalAlignment(JLabel.CENTER);
			labelsMois[i].setVerticalAlignment(JLabel.TOP);
			labelsMois[i].setVisible(true);
			panelNord.add(labelsMois[i],i);
			}
		
		add(panelNord,BorderLayout.NORTH);

		moisAffiche=(new Date()).getMois();
		panelCentre = new PanelMois(moisAffiche);
		add(panelCentre, BorderLayout.CENTER);
	}

	/**
	 * Met le controleur à l'écoute des boutons qui permettent de changer le mois courant.
	 *
	 * @param controleur Controleur à mettre à l'écoute.
	 *
	 * @see controleur.Controleur
	 *
	 * @author Antoine Limerutti
	 */
	public void enregistreEcoute(Controleur controleur) {
		panelCentre.enregistreEcoute(controleur);
		
		for(int i=0; i<boutons.length; i++)
			boutons[i].addActionListener(controleur);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
	}

	/**
	 * Retourne le bouton situé à l'indice fournis en paramètre.
	 *
	 * @param i Entier qui indique la place dans le tableau du bouton que l'on veut obtenir.
	 *
	 * @return JButton correspondant au JButton à l'indice i dans le tableau boutons.
	 *
	 * @see javax.swing.JButton
	 *
	 * @author Antoine Limerutti
	 */
	public JButton getBoutonsTab(int i) {
		return boutons[i];
	}

	/**
	 * Accesseur du CardLayout de panelNord.
	 *
	 * @return CardLayout du panelNord (contenant les labels de tout les mois).
	 *
	 * @see java.awt.CardLayout
	 *
	 * @author Antoine Limerutti
	 */
	public CardLayout getCardLayout() {
		return cardLabelMois;
	}

	/**
	 * Modifieur du champ moisAffiche.
	 *
	 * @param parMoisAffiche Entier à mettre dans le champ moisAffiche.
	 *
	 * @author Antoine Limerutti
	 */
	public void setMoisAffiche(int parMoisAffiche) {
		moisAffiche=parMoisAffiche;
	}

	/**
	 * Accesseur du champ moisAffiche
	 *
	 * @return Entier correspondant à la valeur du champ moisAffiche
	 *
	 * @author Antoine Limerutti
	 */
	public int getMoisAffiche() {
		return moisAffiche;
	}

	/**
	 * Accesseur du champ panelCentre.
	 *
	 * @return panelMois.
	 *
	 * @see vue.PanelMois
	 *
	 * @author Antoine Limerutti
	 */
	public PanelMois getPanelCentre() {
		return panelCentre;
	}

	/**
	 * Modifieur du champ panelCentre.
	 *
	 * @param panelMois PanelMois qui vas remplacer la valeur du champ panelCentre.
	 *
	 * @see vue.PanelMois
	 *
	 * @author Antoine Limerutti
	 */
	public void setPanelCentre(PanelMois panelMois) {
		panelCentre=panelMois;
	}

	/**
	 * Accesseur du champ PanelNord.
	 *
	 * @return JPanel Panel contenu dans le champ panelNord.
	 *
	 * @see javax.swing.JPanel
	 *
	 * @author Antoine Limerutti
	 */
	public JPanel getPanelNord() {
		return panelNord;
	}
}
