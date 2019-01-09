package fr.proagenda.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import fr.proagenda.application.Application;
import fr.proagenda.ihm.*;

public class IhmNewCalendar extends JFrame{
	private Cal panelMainFenetre;
	protected int posX;
	protected int posY;
	
	private int jour ;
	private int mois;
	private int annee;
	private String date;

	public IhmNewCalendar() {
		
		this.setUndecorated(true);
		
		try { 
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		    e.printStackTrace();
		}
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 310, 276);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		// TOP WINDOW 
		JPanel panelCloseWindow = new JPanel();
		panelCloseWindow.setBounds(287, 0, 22, 23);
		getContentPane().add(panelCloseWindow);
		panelCloseWindow.setLayout(null);
		
		JButton btnX = new JButton(new ImageIcon(Ihm.class.getResource("/fr/proagenda/img/close-window-32.png")));
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		
		btnX.setVisible(true);
		panelCloseWindow.add(btnX);
		btnX.setBounds(0, 0, 23, 23);
		btnX.setBackground(Color.DARK_GRAY);
		panelCloseWindow.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnX}));
		
		panelMainFenetre = new Cal();
		panelMainFenetre.setBounds(0, 23, 310, 220);
		panelMainFenetre.setVisible(true);
		getContentPane().add(panelMainFenetre);
		
		JPanel panelTopWindow = new JPanel();
		panelTopWindow.addMouseMotionListener(new MouseMotionAdapter() {
			   // A chaque deplacement on recalcul le positionnement de la fenetre
			   @SuppressWarnings("static-access")
			@Override
			   public void mouseDragged(MouseEvent e) {
						   int depX = e.getX() - posX;
						   int depY = e.getY() - posY;
						   JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(panelMainFenetre);
		        	   topFrame.getWindows()[topFrame.getWindows().length-1].setLocation(getX()+depX, getY()+depY);
			   }
		});

		panelTopWindow.setLayout(null);
		panelTopWindow.setBackground(Color.DARK_GRAY);
		panelTopWindow.setBounds(0, 0, 288, 23);
		getContentPane().add(panelTopWindow);
		
		JPanel panelBotWindow = new JPanel();
		panelBotWindow.setBackground(Color.DARK_GRAY);
		panelBotWindow.setBounds(0, 242, 310, 34);
		getContentPane().add(panelBotWindow);
		panelBotWindow.setLayout(null);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jour = panelMainFenetre.getActiveDay();
				mois = panelMainFenetre.GetActiveMonth();
				annee = panelMainFenetre.getYearChoice();
				date = Integer.toString(annee)+"-"+Integer.toString(mois).format("%02d", mois)+"-"+Integer.toString(jour).format("%02d", jour);
				System.out.println(date);
								
			}
		});
		btnValider.setIcon(new ImageIcon(IhmNewCalendar.class.getResource("/fr/proagenda/img/check-mark-12-16.png")));
		btnValider.setBounds(106, 2, 89, 23);
		panelBotWindow.add(btnValider);
	}
	
	public String getDate() {
		return date;
	}
}
