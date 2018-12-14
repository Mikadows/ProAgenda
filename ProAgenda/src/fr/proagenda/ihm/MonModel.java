package fr.proagenda.ihm;

import javax.swing.table.AbstractTableModel;

/**
 * Classe servant à gérer l'affichage Jtable
 * @author Mathieu
 *
 */
@SuppressWarnings("serial")
public class MonModel extends AbstractTableModel{ 
	Object donnees[][];
	String titres[];
	public MonModel(
		Object donnees[][], String titres[]){ 
		this.donnees = donnees; 
		this.titres = titres; 
	} 
	
	public int getColumnCount(){ 
		return titres.length; 
	}
	
	public Object getValueAt(int parm1, int parm2){ 
		return donnees[parm1][parm2]; 
	}
	
	public int getRowCount() { 
		return donnees.length; 
	}
	
	public String getColumnName(int col){ 
		return titres[col]; 
	} 
	public void setValueAt(Object v, int li, int co) {
		
	}
}