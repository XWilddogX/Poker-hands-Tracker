package Swing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import Model.CreateRango;




public class NewRang implements ActionListener {

	private String nombre;
	
	private JFrame principal;
	private JPanel panelNorte;
	private JPanel panelSur;
	
	private JTextField textUTG;
	private JTextField textHJ;
	private JTextField textCO;
	private JTextField textBTN;
	private JTextField textSB;
	
	private JLabel UTG;
	private JLabel HJ;
	private JLabel CO;
	private JLabel BTN;
	private JLabel SB;
	
	private JButton aceptar;
	private JButton cancelar;
	
	private StringBuilder rango;
	private String ln;
	
	
	public NewRang(){
		nombre = JOptionPane.showInputDialog("Introduzca el nombre del archivo: ");
		rango = new StringBuilder();
		ln = System.getProperty("line.separator");
		principal = new JFrame();
		principal.setLayout(new BorderLayout());
		principal.setVisible(true);
		panelNorte();
		panelSur();
		principal.add(panelNorte, BorderLayout.CENTER);
		principal.add(panelSur, BorderLayout.SOUTH);
		principal.pack();
		
	}
	
	private void panelNorte(){
		panelNorte = new JPanel();
		panelNorte.setLayout(new GridBagLayout());
		panelNorte.setVisible(true);
		
		
		UTG = new JLabel("UTG");
		HJ = new JLabel("HJ");
		CO = new JLabel("CO");
		BTN = new JLabel("BTN");
		SB = new JLabel("SB");
		
		textUTG = new JTextField();
		textHJ = new JTextField();
		textCO = new JTextField();
		textBTN = new JTextField();
		textSB = new JTextField();
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,5,5,5);
		
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		panelNorte.add(UTG, c);
		c.gridx = 1;
		c.gridy = 0;
		panelNorte.add(textUTG, c);
		
		c.gridx = 0;
		c.gridy = 1;
		panelNorte.add(HJ, c);
		c.gridx = 1;
		c.gridy = 1;
		panelNorte.add(textHJ, c);
		
		c.gridx = 0;
		c.gridy = 2;
		panelNorte.add(CO, c);
		c.gridx = 1;
		c.gridy = 2;
		panelNorte.add(textCO, c);
		
		c.gridx = 0;
		c.gridy = 3;
		panelNorte.add(BTN, c);
		c.gridx = 1;
		c.gridy = 3;
		panelNorte.add(textBTN, c);
		
		c.gridx = 0;
		c.gridy = 4;
		panelNorte.add(SB, c);
		c.gridx = 1;
		c.gridy = 4;
		panelNorte.add(textSB, c);
		
	}
	
	private void panelSur(){
		panelSur = new JPanel();
		panelSur.setLayout(new FlowLayout());
		panelSur.setVisible(true);
		
		aceptar = new JButton("Aceptar");
		cancelar = new JButton("Cancelar");
		
		aceptar.addActionListener(this);
		cancelar.addActionListener(this);
		
		panelSur.add(aceptar);
		panelSur.add(cancelar);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cancelar){
			principal.setVisible(false);
		}
		else if(e.getSource() == aceptar){
			rango.append(textUTG.getText());
			rango.append(ln);
			rango.append(textHJ.getText());
			rango.append(ln);
			rango.append(textCO.getText());
			rango.append(ln);
			rango.append(textBTN.getText());
			rango.append(ln);
			rango.append(textSB.getText());
			rango.append(ln);
			
			try {
				CreateRango r = new CreateRango(nombre, rango);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			principal.setVisible(false);
			
		}
		
	}

}
