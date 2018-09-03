package Swing;

import java.awt.BorderLayout;


import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Main.Main;
import Model.CreateRanking;

public class NewRanking{
	private StringBuilder ranking;
	private String ln;
	
	private JButton aceptar;
	private JButton cancelar;
	private JButton clear;
	private JButton deshacer;
	
	private JPanel rank;
	private JPanel panelBotonera;
	private JPanel panelInf;
	private JPanel panelIzq;
	private JPanel botonesInf;


	private JButton[][] botonera;

	private JPanel panelB;
	private JPanel panelDer;

	private int a;
	private int b;
	private String nombre;
	private int contador;
	
	JFrame principal;
	
	public NewRanking(){
		contador = 0;
		nombre = JOptionPane.showInputDialog("Introduzca el nombre del archivo: ");
		ln = System.getProperty("line.separator");
		principal = new JFrame();
		principal.setVisible(true);
		principal.setLayout(new FlowLayout());
		principal.add(new panelRanking());
		principal.add(rank);
		principal.setResizable(false);
		principal.pack();
	
		
	}
	
	class panelRanking extends JPanel implements ActionListener{
	

		public panelRanking(){
			
			ranking = new StringBuilder();
			rank = new JPanel();
			rank.setLayout(new BorderLayout());
			rank.setBorder(new EmptyBorder(5, 5, 5, 5));
			Dimension dim = new Dimension();
			dim = super.getToolkit().getScreenSize();
			int altura = dim.height;
			int ancho = dim.width;
			
			setMinimumSize(new Dimension(850, 420));
			
			setLocation((ancho / 10), (altura/10));
			panelBotonera = new JPanel();
			panelBotonera.setLayout(new GridLayout(13,13));
			botonera = new JButton[13][13];
			Font f = new Font("arial",Font.BOLD,9); 
			String[] cartas = {
					"A", "K", "Q", "J", "T", "9", "8", "7", "6", "5", "4", "3", "2"
			};
			int i = 0;
			for(i = 0; i < 13; i++){
				for(int j = 0; j < 13; j++){
					if (i == j) {
						botonera[i][j] = new JButton(cartas[i] + "" + cartas[j]); 
						botonera[i][j].setBackground(Color.green);
						JButton n = botonera[i][j];
						n.addActionListener(this);
						
					}
					else if (i < j){
						botonera[i][j] = new JButton(cartas[i] + "" + cartas[j] + "s");
						botonera[i][j].setBackground(Color.red);
						JButton n = botonera[i][j];
						n.addActionListener(this);
						
					}
					else {
						botonera[i][j] = new JButton(cartas[j] + "" + cartas[i] + "o"); 
						botonera[i][j].setBackground(Color.cyan);
						JButton n = botonera[i][j];
						n.addActionListener(this);
						
					}
					botonera[i][j].setFont(f);
					botonera[i][j].setFocusPainted(false);
					botonera[i][j].setPreferredSize(new Dimension(55, 20));
					panelBotonera.add(botonera[i][j]);
				}
			}

			panelIzq = new JPanel();
			panelIzq.setLayout(new BorderLayout());
			panelB = new JPanel();
			panelB.setLayout(new FlowLayout());
			panelB.add(panelBotonera);
			panelIzq.add(panelB, BorderLayout.NORTH);
			
			
			cancelar = new JButton("Cancelar");
			aceptar = new JButton("Aceptar");
		
			panelInf = new JPanel();
			botonesInf = new JPanel();
			botonesInf.setLayout(new FlowLayout());
			panelInf.setLayout(new BorderLayout());
			botonesInf.add(cancelar);
			botonesInf.add(aceptar);
			
			aceptar.addActionListener(this);
			cancelar.addActionListener(this);

			panelInf.add(botonesInf, BorderLayout.EAST);

			panelDer = new JPanel();
			panelDer.setLayout(new BoxLayout(panelDer,BoxLayout.Y_AXIS));
			panelDer.setBorder(new EmptyBorder(5, 5, 5, 10));
			
			clear = new JButton("Clear");

			deshacer = new JButton("Deshacer");
			clear.setMaximumSize(new Dimension(100, 30));
			deshacer.setMaximumSize(new Dimension(100, 30));
			deshacer.setEnabled(false);
			clear.addActionListener(this);
			deshacer.addActionListener(this);
			cancelar.addActionListener(this);
			
			panelDer.add(Box.createRigidArea(new Dimension(0, 10)));
			panelDer.add(clear);
			panelDer.add(deshacer);
		
			rank.add(panelDer, BorderLayout.EAST);
			rank.add(panelIzq, BorderLayout.WEST);
			rank.add(panelInf, BorderLayout.SOUTH);
			rank.setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			

			for(int i = 0; i < 13; i++){
				for(int j = 0; j < 13; j++){
					if(e.getSource() == botonera[i][j]){
											
						ranking.append(botonera[i][j].getText());
						ranking.append(ln);
						contador++;
						botonera[i][j].setEnabled(false);
						botonera[i][j].setBackground(Color.YELLOW);
						deshacer.setEnabled(true);
						a = i;
						b = j;
					}
				}
			}
			
			if(e.getSource() == clear){
				
				for(int i = 0; i < 13; i++){
					for(int j = 0; j < 13; j++){
						if (i == j) {
							botonera[i][j].setEnabled(true);
							ranking.setLength(0);
							botonera[i][j].setBackground(Color.green);
						}
						else if (i < j){
							botonera[i][j].setEnabled(true);
							ranking.setLength(0);
							botonera[i][j].setBackground(Color.red);
						}
						else {
							botonera[i][j].setEnabled(true);
							ranking.setLength(0);
							botonera[i][j].setBackground(Color.cyan);
						}
						
					}
				}
				contador = 0;
				System.out.println(ranking);
			}
			
			else if(e.getSource() == deshacer){
				
				botonera[a][b].setEnabled(true);
				int t = ranking.indexOf(botonera[a][b].getText());
				ranking.delete(t, ranking.length());
				contador--;
				if (a == b) {
					botonera[a][b].setBackground(Color.green);
				}
				else if (a < b){
					botonera[a][b].setBackground(Color.red);
				}
				else {
					botonera[a][b].setBackground(Color.cyan);
				}
				
				
				deshacer.setEnabled(false);
			}
			else if(e.getSource() == aceptar){
				
				if(contador == Main.TOTAL_MANOS_INICIALES){
					try {
						CreateRanking c = new CreateRanking(ranking, nombre);
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
					principal.setVisible(false);
				}
				else{
					JOptionPane.showMessageDialog( null, "Todavía faltan cartas por marcar" );
				}
			}
			else if(e.getSource() == cancelar){
				principal.setVisible(false);
			}
		}			
	}
}
