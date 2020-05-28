package vue;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import modeles.Agenda;
import modeles.CelluleRenderer;
import modeles.Date;
import modeles.Evenement;
import modeles.ModeleTable;

/**
 * Panel contenant un scroll pane contenant lui m�me une table qui contient tout les �v�nements de l'agenda.
 * Appartient au package Vue.
 * 
 * @author Antoine Limerutti
 *
 * @see javax.swing.JPanel
 *
 * @version 1.0
 */
public class PanelAffichage extends JPanel{
	/**
	 * Mod�le de la table des �v�nements.
	 *
	 * @see modeles.ModeleTable
	 */
	private ModeleTable modele;
	
	/**
	 * Table des �v�nements de l'agenda.
	 *
	 * @see javax.swing.JTable
	 */
	private JTable tableSemaine;
	
	/**
	 * ScrollPane contenant la table.
	 *
	 * @see javax.swing.JScrollPane
	 */
	private JScrollPane scrollPane;
	
	/**
	 * Agenda dont les �v�nements vont �tre affich�s dans la table
	 *
	 * @see modeles.Agenda
	 */
	private Agenda agenda;
	
	/**
	 * Construit un mod�le, un table, un scrollpane et d�finit leurs caract�ristiques.
	 *
	 * @param parAgenda Agenda � utiliser dans la construction du mod�le.
	 *
	 * @see modeles.Agenda
	 *
	 * @author Antoine Limerutti
	 */
	public PanelAffichage(Agenda parAgenda){
		agenda=parAgenda;
		
		modele = new ModeleTable(new Date(), agenda);
		
		tableSemaine = new JTable(modele);
		tableSemaine.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		tableSemaine.setRowHeight(40);
		tableSemaine.setDefaultRenderer(Evenement.class, new CelluleRenderer());
		tableSemaine.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				JTable table = (JTable) event.getSource();
				ModeleTable model =(ModeleTable) table.getModel();
				Point point = event.getPoint();
				int rowIndex = table.rowAtPoint (point);
				int colIndex = table.columnAtPoint (point);
				JOptionPane.showMessageDialog ( table,model.getValueAt (rowIndex,colIndex) );
			}
		});
		
		scrollPane = new JScrollPane(tableSemaine,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension (750,700));
		add(scrollPane);
	}
	
	/**
	 * Retourne le mod�le de la table.
	 * 
	 * @return ModeleTable de la table
	 *
	 * @see modeles.ModeleTable
	 *
	 * @author Antoine Limerutti
	 */
	public ModeleTable getModele() {
		return modele;
	}
	
	/**
	 * Actualise la mod�le de la table et son contenu.
	 * 
	 * @param date Date qui pr�cise � quelle semaine doit correspondre le mod�le
	 *
	 * @see modeles.Date
	 *
	 * @author Antoine Limerutti
	 */
	public void reinitModele(Date date) {
		modele = new ModeleTable(date, agenda);
		tableSemaine.setModel(modele);

		tableSemaine.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		
		remove(scrollPane);
		scrollPane = new JScrollPane(tableSemaine,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension (750,700));
		add(scrollPane);
	}
	

}
