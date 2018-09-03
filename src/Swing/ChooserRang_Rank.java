package Swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Main.Principal;

public class ChooserRang_Rank implements ActionListener{
	
	private JComboBox<String> rangos;
	private JComboBox<String> rankings;
	
	private JLabel nombreRang;
	private JLabel nombreRank;
	
	private JButton nuevoRango;
	private JButton nuevoRanking;
	private JButton aceptar;
	
	private JPanel panelNorte;
	private JPanel panelSur;
	private JFrame prin;
	private String archivoRanking;
	private String archivoRango;
	
	public ChooserRang_Rank() throws IOException{
		prin = new JFrame();
		prin.setVisible(true);
		prin.setLayout(new BorderLayout());
		archivoRango = "";
		archivoRanking = "";
		panelNorte();
		panelSur();
		prin.add(panelNorte, BorderLayout.CENTER);
		prin.add(panelSur, BorderLayout.SOUTH);
		prin.setVisible(true);
		prin.pack();
	}
	
	private void panelNorte() throws IOException{
		panelNorte = new JPanel();
		panelNorte.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 5, 5, 5);
		
		nombreRang = new JLabel("Rango ");
		nombreRank = new JLabel("Ranking ");
		
		rangos = new JComboBox<String>();
		rankings = new JComboBox<String>();
		
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		panelNorte.add(nombreRang, c);
		c.gridx = 1;
		c.gridy = 0;
		panelNorte.add(rangos, c);
		
		c.gridx = 0;
		c.gridy = 1;
		panelNorte.add(nombreRank, c);
		c.gridx = 1;
		c.gridy = 1;
		panelNorte.add(rankings, c);
		
		//añado los rankings al comboBox
		FileReader file = new FileReader("src/ranking/nombresRanking.txt");
		BufferedReader br = new BufferedReader(file);
		String linea = "";
		while((linea=br.readLine()) != null){
			rankings.addItem(linea);
		}
		br.close();
		file.close();
		
		//añado los rangos al comboBox
		FileReader f = new FileReader("src/rangos/nombresRangos.txt");
		BufferedReader b = new BufferedReader(f);
		String line = "";
		while((line = b.readLine()) != null){
			rangos.addItem(line);
		}
		b.close();
		f.close();
		
		
		panelNorte.setVisible(true);
				
	}
	private void panelSur(){
		
		panelSur = new JPanel();
		panelSur.setLayout(new FlowLayout());
		
		aceptar = new JButton("Aceptar");
		nuevoRango = new JButton("Nuevo Rango");
		nuevoRanking = new JButton("Nuevo Ranking");
		aceptar.addActionListener(this);
		nuevoRango.addActionListener(this);
		nuevoRanking.addActionListener(this);
		
		
		panelSur.add(nuevoRango);
		panelSur.add(nuevoRanking);
		panelSur.add(aceptar);
		panelSur.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == nuevoRango){
			NewRang rang = new NewRang();
			prin.setVisible(false);
		}
		if(e.getSource() == nuevoRanking){
			NewRanking rank = new NewRanking();
			prin.setVisible(false);
		}
		if(e.getSource() == rankings){
			archivoRanking = rankings.getItemAt(rankings.getSelectedIndex()) + ".txt";
		}
		if(e.getSource() == rangos){
			archivoRango = rangos.getItemAt(rangos.getSelectedIndex()) + ".txt";
		}
		if(e.getSource() == aceptar){
			archivoRanking = rankings.getItemAt(rankings.getSelectedIndex()) + ".txt";
			archivoRango = rangos.getItemAt(rangos.getSelectedIndex()) + ".txt";
			prin.setVisible(false);
			Principal principal = new Principal(archivoRango, archivoRanking);
		}
	}
	
	

}
