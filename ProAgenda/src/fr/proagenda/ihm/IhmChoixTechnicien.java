package fr.proagenda.ihm;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import fr.proagenda.application.Application;

public class IhmChoixTechnicien {
	private JTable table_1;

	/**
	 * affiche un JTable avec les date des RDV 
	 * @param fils : JPannel de la fenetre précédente 
	 * @param id_account : Int ID du technicien a voir les rendez vous 
	 * TODO Fonction de découpage de la date et l'heure 
	 */
	@SuppressWarnings("serial")
	public IhmChoixTechnicien(JPanel fils,int id_account) {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(250, 70, 400, 200);
		fils.add(scrollPane);
	 
			      
	    table_1 = new JTable();
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
		for(int i = 0; i < test.size(); i+=4 ) {
				dtm.addRow(new Object[] {test.get(i),test.get(i+1),test.get(i+2),test.get(i+3)});
		} 		 
		scrollPane.setViewportView(table_1);
	}

}
