package modeles;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * <b>Classe qui vas d�finir les donn�es � mettre dans la JTable de la classe PanelAffichage.<br>
 * Appartient au package modeles.</b>
 * 
 * @author Antoine Limerutti
 * 
 * @see javax.swing.table.TableCellRenderer
 * @see javax.swing.JTable
 * @see vue.PanelAffichage
 *
 * @version 1.0
 */
public class CelluleRenderer extends JLabel implements TableCellRenderer{

	/**
	 * Contruit un objet CelluleRenderer en appellant le contructeur parent et d�finit ses principales caract�ristiques.
	 * 
	 * @author Antoine Limerutti
	 */
	public CelluleRenderer() {
		super();
		setOpaque(true);
		setHorizontalAlignment(JLabel.CENTER);
		setForeground(new Color(255,255,255));
		setBackground(new Color(100,100,100));
		setPreferredSize(new Dimension(200,200));
	}
	
	/**
	 * D�finit la valeur � mettre dans la cellule einsi que certaines de ses caract�ristiques (ToolTipText).
	 * 
	 * @param table JTable � traiter.
	 * @param cellValue Valeur � mettre dans la cellule.
	 * @param isSelected Indique si la cellule est selectionn�e.
	 * @param hasFocus Indique si la cellule a le focus.
	 * @param row Indique la ligne de la cellule.
	 * @param col Indique la colone de la cellule.
	 *
	 * @see javax.swing.JTable
	 *
	 * @author Antoine Limerutti
	 */
	public Component getTableCellRendererComponent(JTable table, Object cellValue, boolean isSelected, boolean hasFocus, int row, int col) {		
		cellValue = ((Evenement) cellValue);
		
		if(cellValue!=null) {
			setText(cellValue.toString());
			
			setToolTipText("Le " + ((Evenement) cellValue).getDate().toString() + ", � " + ((Evenement) cellValue).getLieu().toString() + " vous avez pr�vu " + ((Evenement) cellValue).getTitre().toString() + " de " + ((Evenement) cellValue).getHeureDebut() + ":" + ((Evenement) cellValue).getMinutesDebut() + " � " + ((Evenement) cellValue).getHeureFin() + ":" + ((Evenement) cellValue).getMinutesFin() + ".");
		}
		
		else {
			setText(null);
			setToolTipText(null);
		}
		
		return this;
	}

}
