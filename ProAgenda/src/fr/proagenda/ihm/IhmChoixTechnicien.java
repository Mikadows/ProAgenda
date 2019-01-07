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
	 * @param fils : JPannel de la fenetre précédente 
	 * @param id_account : Int ID du technicien a voir les rendez vous 
	 * TODO Fonction de découpage de la date et l'heure 
	 */
	@SuppressWarnings("serial")
	public IhmChoixTechnicien(int id_account){
		this.setBackground(Color.LIGHT_GRAY);
		this.setBounds(350, 100, 500, 200);
			      
	    table_1 = new JTable();
	    table_1.setCellSelectionEnabled(false);
	    table_1.setColumnSelectionAllowed(false);
	    table_1.setRowSelectionAllowed(true);
	    table_1.setBackground(Color.GRAY);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Date", "Heure", "Adresse"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table_1.getColumnModel().getColumn(0).setPreferredWidth(10);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(10);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(120);
		
		
		Application app = new Application();
		ArrayList<String> test = app.ihmToDAOGetRdv(id_account);
		
		DefaultTableModel dtm = (DefaultTableModel) table_1.getModel();
		
		if(!test.isEmpty()) {
			for(int i = 0; i < test.size(); i+=4 ) {
				
				dtm.addRow(new Object[] {test.get(i).substring(0,10),test.get(i+1).substring(10,16),test.get(i+2)});
			} 
		}else {}
		this.setViewportView(table_1);
	}

}
