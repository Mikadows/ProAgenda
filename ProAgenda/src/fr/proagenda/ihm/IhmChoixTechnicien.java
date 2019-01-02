package fr.proagenda.ihm;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import fr.proagenda.application.Application;

public class IhmChoixTechnicien extends JScrollPane{
	private JTable table_1;

	/**
	 * affiche un JTable avec les date des RDV 
	 * @param fils : JPannel de la fenetre pr�c�dente 
	 * @param id_account : Int ID du technicien a voir les rendez vous 
	 * TODO Fonction de d�coupage de la date et l'heure 
	 */
	@SuppressWarnings("serial")
	public IhmChoixTechnicien(int id_account){
 //TOCHECK 
		this.setBounds(250, 70, 400, 200);
			       
	    table_1 = new JTable();
	    table_1.setBackground(Color.GRAY);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Date", "Heure", "Adresse", "Autre"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		Application app = new Application();
		ArrayList<String> test = app.ihmToDAOGetRdv(id_account);
		
		DefaultTableModel dtm = (DefaultTableModel) table_1.getModel();
		
		if(!test.isEmpty()) {
			for(int i = 0; i < test.size(); i+=4 ) {
					dtm.addRow(new Object[] {test.get(i),test.get(i+1),test.get(i+2),test.get(i+3)});
			} 
		}else {}
		this.setViewportView(table_1);
	}

}
