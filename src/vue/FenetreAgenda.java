package vue;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * <b>Fenetre mère du programme.<br>
 * Appartient au package vue.</b>
 * 
 * @author Antoine Limerutti
 * 
 * @see javax.swing.JFrame
 * 
 * @version 1.0
 */
public class FenetreAgenda extends JFrame {
	
	/**
	 * Construit une fenêtre, définit ses caractéristiques, construit son menu et crée son panel fils (PanelAgenda).
	 *
	 * @see vue.PanelAgenda
	 *
	 * @author Antoine Limerutti
	 */
	public FenetreAgenda() {
		super("Agenda");
		PanelAgenda panelFils = new PanelAgenda(this);
		
		panelFils.setBackground (new Color(100,2,56));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setSize(770,700);
		setVisible(true);
		setLocation(100,100);
		setBackground (new Color(100,2,56));
		
		JMenuItem[] itemsMenu = new JMenuItem[]{new JMenuItem("Calendrier", 'C'), new JMenuItem("Evenement", 'v'), new JMenuItem("Semaine", 'S'), new JMenuItem("Fermer",'r')};
		JMenuBar menu = new JMenuBar();

		for(int i=0; i<itemsMenu.length; i++) {
			itemsMenu[i].setAccelerator(KeyStroke.getKeyStroke(itemsMenu[i].getText().charAt(0),java.awt.Event.CTRL_MASK));
			itemsMenu[i].addActionListener(panelFils);
			itemsMenu[i].setActionCommand(itemsMenu[i].getText());
			menu.add(itemsMenu[i]);
		}
		
		setJMenuBar(menu);
		setContentPane(panelFils);
	}
}