package vue;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controleur.Controleur;
import modeles.Date;

/**
 * panel qui vas afficher un formulaire à remplire pour ajouter les données dans l'agenda.
 * Appartient au package Vue.
 *
 * @see javax.swing.JPanel
 *
 * @see java.awt.event.ActionListener
 *
 * @author Antoine Limerutti
 *
 * @version 1.0
 */
public class PanelFormulaire extends JPanel implements ActionListener {
	/**
	 * Champ de saisie qui vas acceuillir les données sur le titre de l'évènement à ajouter dans l'agenda.
	 *
	 * @see javax.swing.JTextField
	 */
	private final JTextField titretxt;

	/**
	 * Champ de saisie qui vas acceuillir les données sur le lieu de l'évènement à ajouter dans l'agenda.
	 *
	 * @see javax.swing.JTextField
	 */
	private final JTextField lieutxt;

	/**
	 * ComboBox qui vas acceuillir les données sur l'heure de début de l'évènement à ajouter dans l'agenda.
	 *
	 * @see javax.swing.JComboBox
	 */
	private final JComboBox<String> heureDebut;

	/**
	 * ComboBox qui vas acceuillir les données sur l'heure de fin de l'évènement à ajouter dans l'agenda.
	 *
	 * @see javax.swing.JComboBox
	 */
	private final JComboBox<String> heureFin;


	/**
	 * ComboBox qui vas acceuillir les données sur les minutes de début de l'évènement à ajouter dans l'agenda.
	 *
	 * @see javax.swing.JComboBox
	 */
	private final JComboBox<String> minutesDebut;

	/**
	 * ComboBox qui vas acceuillir les données sur les minutes de fin de l'évènement à ajouter dans l'agenda.
	 *
	 * @see javax.swing.JComboBox
	 */
	private final JComboBox<String> minutesFin;

	/**
	 * champs de saisie qui vas acceuillir la description de l'évènement à ajouter dans l'agenda.
	 *
	 * @see javax.swing.JTextArea
	 */
	private final JTextArea descriptiontxt;

	/**
	 * JButton qui vas provoquer l'ajout des données à l'agenda.
	 *
	 * @see javax.swing.JButton
	 */
	private final JButton ajouter;

	/**
	 * JLabel qui vas préciser le jour auquel l'évènement vas être ajouté.
	 *
	 * @see javax.swing.JLabel
	 */
	private JLabel labelTitre;

	/**
	 * Date auquel l'évènement vas être ajouté
	 *
	 * @see modeles.Date
	 */
	private Date date;

	/**
	 * Constructeur de la classe PanelFormulaire qui vas instancier et définir les contraintes de tout les éléments du formulaire.
	 *
	 * @param parDate
	 *
	 * @see modeles.Date
	 *
	 * @author Antoine Limerutti
	 */
	public PanelFormulaire(Date parDate) {
		GridBagLayout grid = new GridBagLayout();
		setLayout(grid);
				
		date = parDate;
		
		JLabel titre = new JLabel("Titre");
		titretxt = new JTextField();
		titre.setDisplayedMnemonic('T');
		titre.setLabelFor(titretxt);
		titretxt.setFocusable(true);
		titretxt.requestFocus();
		
		JLabel lieu = new JLabel("Lieu");
		lieutxt = new JTextField();
		lieu.setDisplayedMnemonic('L');
		lieu.setLabelFor(lieutxt);
		lieutxt.setFocusable(true);
		
		String[] intituleMinutes = new String[60];
		String[] intituleHeures = new String[24];
		String chaine;
		for(int i=0; i<60; i++) {
			if(i>9)
				chaine=String.valueOf(i);
			else
				chaine="0"+String.valueOf(i);
			intituleMinutes[i]=chaine;
			if(i<24)
				intituleHeures[i]=chaine;
		}
		heureDebut = new JComboBox<String>(intituleHeures);
		heureFin = new JComboBox<String>(intituleHeures);
		minutesDebut = new JComboBox<String>(intituleMinutes);
		minutesFin = new JComboBox<String>(intituleMinutes);
		
		Color blanc = new Color(255,255,255);
		heureDebut.setBackground(blanc);
		heureFin.setBackground(blanc);
		minutesDebut.setBackground(blanc);
		minutesFin.setBackground(blanc);
		
		JLabel debut = new JLabel("Début");
		debut.setDisplayedMnemonic('D');
		debut.setLabelFor(heureDebut);
		
		JLabel fin = new JLabel("Fin");
		fin.setDisplayedMnemonic('F');
		fin.setLabelFor(heureFin);
		
		JLabel labelCombo1 = new JLabel(":");
		JLabel labelCombo2 = new JLabel(":");
		
		JLabel description = new JLabel("Description");
		descriptiontxt = new JTextArea(7,10);
		description.setDisplayedMnemonic('e');
		description.setLabelFor(descriptiontxt);
		descriptiontxt.setFocusable(true);
		descriptiontxt.setLineWrap(true);

		labelTitre = new JLabel(date.toString());
		
		ajouter = new JButton("+");
		ajouter.setFocusable(false);
		ajouter.setMnemonic('A');
		
		GridBagConstraints contrainte = new GridBagConstraints();
				
		setLabelTitre(0,0,contrainte);
		add(labelTitre, contrainte);
		
		setBouton(4,0,contrainte);
		add(ajouter, contrainte);
		
		setLabel(0,1,contrainte);
		add(titre, contrainte);
		
		setText(1,1,contrainte);
		add(titretxt, contrainte);
		
		setLabel(0,2,contrainte);
		add(lieu, contrainte);

		setText(1,2,contrainte);
		add(lieutxt, contrainte);
		
		setLabel(0,3,contrainte);
		add(debut, contrainte);
		
		setCombo(1,3, contrainte);
		add(heureDebut, contrainte);
		
		setLabel(2,3, contrainte);
		add(labelCombo1, contrainte);
		
		setCombo(4,3,contrainte);
		add(minutesDebut, contrainte);
		
		setLabel(0,4,contrainte);
		add(fin, contrainte);
		
		setCombo(1,4,contrainte);
		add(heureFin,contrainte);
		
		setLabel(2,4, contrainte);
		add(labelCombo2, contrainte);
		
		setCombo(4,4,contrainte);
		add(minutesFin, contrainte);
		
		setLabel(0,5,contrainte);
		add(description, contrainte);
		
		setText(1,5,contrainte);
		add(descriptiontxt, contrainte);
	}

	/**
	 * Définit les contraintes pour les objets de type JLabel (sauf labelTitre).
	 *
	 * @param x Entier qui indique la position en abscisse de l'élément à ajouter.
	 * @param y Entier qui indique la position en ordonnée de l'élément à ajouter.
	 * @param contrainte GridBagConstraint dont on se sert pour définir les contraintes de l'élément à ajouter.
	 *
	 * @see javax.swing.JLabel
	 * @see java.awt.GridBagConstraints
	 *
	 * @author Antoine Limerutti
	 */
	private void setLabel(int x, int y, GridBagConstraints contrainte) {
		contrainte.anchor=GridBagConstraints.FIRST_LINE_START;
		
		contrainte.insets=new Insets(10,10,10,10);
		
		contrainte.gridx=x;
		contrainte.gridy=y;
		
		contrainte.gridheight=1;
		contrainte.gridwidth=1;
		
		contrainte.fill=GridBagConstraints.NONE;		
	}

	/**
	 * Définit les contraintes pour le champ labelTitre.
	 *
	 * @param x Entier qui indique la position en abscisse de l'élément à ajouter.
	 * @param y Entier qui indique la position en ordonnée de l'élément à ajouter.
	 * @param contrainte GridBagConstraint dont on se sert pour définir les contraintes de l'élément à ajouter.
	 *
	 * @see javax.swing.JLabel
	 * @see java.awt.GridBagConstraints
	 *
	 * @author Antoine Limerutti
	 */
	private void setLabelTitre(int x, int y, GridBagConstraints contrainte) {
		contrainte.anchor=GridBagConstraints.CENTER;

		contrainte.insets=new Insets(10,10,10,10);
		
		contrainte.gridx=x;
		contrainte.gridy=y;
		
		contrainte.gridheight=1;
		contrainte.gridwidth=3;
		
		contrainte.fill=GridBagConstraints.NONE;		
	}

	/**
	 * Définit les contraintes pour les objets de type JButton.
	 *
	 * @param x Entier qui indique la position en abscisse de l'élément à ajouter.
	 * @param y Entier qui indique la position en ordonnée de l'élément à ajouter.
	 * @param contrainte GridBagConstraint dont on se sert pour définir les contraintes de l'élément à ajouter.
	 *
	 * @see javax.swing.JButton
	 * @see java.awt.GridBagConstraints
	 *
	 * @author Antoine Limerutti
	 */
	private void setBouton(int x, int y, GridBagConstraints contrainte) {
		contrainte.anchor=GridBagConstraints.FIRST_LINE_END;
		contrainte.insets=new Insets(10,150,10,10);
		
		contrainte.gridx=x;
		contrainte.gridy=y;
				
		contrainte.gridheight=1;
		contrainte.gridwidth=1;
		contrainte.fill=GridBagConstraints.BOTH;
	}

	/**
	 * Définit les contraintes pour les objets de type JTextFiels et JTextArea.
	 *
	 * @param x Entier qui indique la position en abscisse de l'élément à ajouter.
	 * @param y Entier qui indique la position en ordonnée de l'élément à ajouter.
	 * @param contrainte GridBagConstraint dont on se sert pour définir les contraintes de l'élément à ajouter.
	 *
	 * @see javax.swing.JTextArea
	 * @see javax.swing.JTextField
	 * @see java.awt.GridBagConstraints
	 *
	 * @author Antoine Limerutti
	 */
	private void setText(int x, int y, GridBagConstraints contrainte) {
		contrainte.anchor=GridBagConstraints.CENTER;
		contrainte.insets=new Insets(10,10,10,50);
		
		contrainte.gridx=x;
		contrainte.gridy=y;
				
		contrainte.gridheight=1;
		contrainte.gridwidth=4;
		contrainte.fill=GridBagConstraints.BOTH;
	}

	/**
	 * Définit les contraintes pour les objets de type JComboBox.
	 *
	 * @param x Entier qui indique la position en abscisse de l'élément à ajouter.
	 * @param y Entier qui indique la position en ordonnée de l'élément à ajouter.
	 * @param contrainte GridBagConstraint dont on se sert pour définir les contraintes de l'élément à ajouter.
	 *
	 * @see javax.swing.JComboBox
	 * @see java.awt.GridBagConstraints
	 *
	 * @author Antoine Limerutti
	 */
	private void setCombo(int x, int y, GridBagConstraints contrainte) {
		contrainte.anchor=GridBagConstraints.CENTER;
		contrainte.insets=new Insets(10,10,10,50);
		
		contrainte.gridx=x;
		contrainte.gridy=y;
				
		contrainte.gridheight=1;
		contrainte.gridwidth=1;
		contrainte.fill=GridBagConstraints.BOTH;
	}

	/**
	 * Modifieur du champ date.
	 *
	 * @param jour Entier qui vas être utilisé pour définir le jour de la nouvelle date à créer.
	 * @param mois Entier qui vas être utilisé pour définir le mois de la nouvelle date à créer.
	 * @param annee Entier qui vas être utilisé pour définir l'année de la nouvelle date à créer.
	 *
	 * @author Antoine Limerutti
	 */
	public void setDate(int jour, int mois, int annee) {
		date=new Date(jour,mois,annee);
	}

	/**
	 * Met le contrôleur à l'écoute du bouton ajouter.
	 *
	 * @param controleur contrôleur à mettre à l'écoute du bouton ajouter.
	 *
	 * @see controleur.Controleur
	 *
	 * @author Antoine Limerutti
	 */
	public void enregistreEcoute(Controleur controleur) {
		ajouter.addActionListener(controleur);
	}

	/**
	 * Accesseur du champ ajouter.
	 *
	 * @return JButton contenu dans le champ ajouter.
	 *
	 * @see javax.swing.JButton
	 *
	 * @author Antoine Limerutti
	 */
	public JButton getAjouter() {
		return ajouter;
	}

	/**
	 * Réinitialise le formulaire en vidant les champs, actualisant le label labelTitre et en remettant les comboBox à 0.
	 *
	 * @author Antoine Limerutti
	 */
	public void reset() {
		titretxt.setText("");
		lieutxt.setText("");
		descriptiontxt.setText("");
			
		titretxt.requestFocus();
		couleurCombo(new Color(255,255,255));				
		remove(labelTitre);
		
		labelTitre.setText(date.toString());
		labelTitre.setForeground(new Color(0,0,0));
		
		GridBagConstraints contrainte = new GridBagConstraints();
		
		setLabelTitre(0,0,contrainte);
		add(labelTitre, contrainte);

		revalidate();
		repaint();
	}

	/**
	 * Accesseur du champ date.
	 *
	 * @return Date contenue dans le champ date.
	 *
	 * @see modeles.Date
	 *
	 * @author Antoine Limerutti
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Accesseur du champ titretxt.
	 *
	 * @return JTextFiels contenu dans le champ titretxt.
	 *
	 * @see javax.swing.JTextField
	 *
	 * @author Antoine Limerutti
	 */
	public JTextField getTitreTxt() {
		return titretxt;
	}

	/**
	 * Accesseur du champ lieutxt.
	 *
	 * @return JTextFiels contenu dans le champ lieutxt.
	 *
	 * @see javax.swing.JTextField
	 *
	 * @author Antoine Limerutti
	 */
	public JTextField getLieuTxt() {
		return lieutxt;
	}

	/**
	 * Accesseur du champ heureDebut.
	 *
	 * @return JComboBox contenue dans le champ heureDebut.
	 *
	 * @see javax.swing.JComboBox
	 *
	 * @author Antoine Limerutti
	 */
	public JComboBox<String> getHeureDebut() {
		return heureDebut;
	}

	/**
	 * Accesseur du champ heureFin.
	 *
	 * @return JComboBox contenue dans le champ heureFin.
	 *
	 * @see javax.swing.JComboBox
	 *
	 * @author Antoine Limerutti
	 */
	public JComboBox<String> getHeureFin() {
		return heureFin;
	}

	/**
	 * Accesseur du champ minutesDebut.
	 *
	 * @return JComboBox contenue dans le champ minutesDebut.
	 *
	 * @see javax.swing.JComboBox
	 *
	 * @author Antoine Limerutti
	 */
	public JComboBox<String> getMinutesDebut() {
		return minutesDebut;
	}

	/**
	 * Accesseur du champ minutesFin.
	 *
	 * @return JComboBox contenue dans le champ minutesFin.
	 *
	 * @see javax.swing.JComboBox
	 *
	 * @author Antoine Limerutti
	 */
	public JComboBox<String> getMinutesFin() {
		return minutesFin;
	}

	/**
	 * Définit la couleur de toute les JComboBox du formulaire
	 *
	 * @param couleur Color couleur à donner aux JComcoBox
	 *
	 * @see java.awt.Color
	 *
	 * @author Antoine Limerutti
	 */
	public void couleurCombo(Color couleur) {
		heureDebut.setBackground(couleur);
		heureFin.setBackground(couleur);
		minutesDebut.setBackground(couleur);
		minutesFin.setBackground(couleur);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
	}
}
