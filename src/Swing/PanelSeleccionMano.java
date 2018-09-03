package Swing;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import Controller.Controller;
import Model.CalculadorRango;
import Model.Carta;
import Model.ManoHero;
import Model.ParCartas;
import Model.PokerStarsParser;
import Observer.Observer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelSeleccionMano extends JFrame implements Observer{
	
	private Controller con;
	private JButton aceptar;
	private JButton cancelar;
	private JButton[][] botonera;
	private ArrayList<String> cartas;
	private ArrayList<String> board;
	private ArrayList<String> dead;
	private String cartasParaTf;
	private int boardDead;
	private int contador;
	private int max;
	

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelSeleccionMano(Controller con) {
		this.con = con;
		contador = 0;
		max = 0;
		cartasParaTf = "";
		cartas = new ArrayList<String>();
		board = new ArrayList<String>();
		dead = new ArrayList<String>();
		this.setLayout(new BorderLayout());
		this.add(new PanelSeleccion(con), BorderLayout.NORTH);
		this.add(new PanelOpciones(this, con), BorderLayout.SOUTH);
		
		this.setMinimumSize(new Dimension(250, 500));
		Dimension dim = new Dimension();
		dim = super.getToolkit().getScreenSize();
		int altura = dim.height;
		int ancho = dim.width;
		//this.setMinimumSize(new Dimension(anchoPantalla, altoPantalla));
		this.setLocation((ancho / 2)+220, (altura / 15));
		this.setVisible(false);
		this.setResizable(false);
		
		
	}
	
	class PanelSeleccion extends JPanel implements ActionListener{
		
		Controller con;
		public PanelSeleccion(Controller con){	
			this.con = con;
			setLayout(new GridLayout(13, 4, 5, 5));
			
			String[] cartas = {
					"A", "K", "Q", "J", "T", "9", "8", "7", "6", "5", "4", "3", "2"
			};
			String[] palos = {
					"s", "h", "d", "c"
			};
			botonera = new JButton[13][4];
			for(int i = 0; i < 13; i++){
				for(int j = 0; j < 4; j++){
					botonera[i][j] = new JButton(cartas[i]+palos[j]);
					botonera[i][j].setFocusPainted(false);
					JButton n = botonera[i][j];
				
					if(j == 0)
						n.setForeground(Color.ORANGE);
					else if(j == 1)
						n.setForeground(Color.RED);
					else if(j == 2)
						n.setForeground(Color.BLUE);
					else if(j == 3)
						n.setForeground(Color.green);
					
					n.addActionListener(this);
					
					add(botonera[i][j]); 
				}
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i = 0; i < 13; i++){
				for(int j = 0; j < 4; j++){
					if(e.getSource() == botonera[i][j]){
						if(boardDead == 0){
							if(botonera[i][j].getBackground() != Color.YELLOW){
								if(contador < max){
									botonera[i][j].setBackground(Color.YELLOW);
									cartas.add(botonera[i][j].getText());
									board.add(botonera[i][j].getText());
									contador++;
									if(contador == max){
										botonera[i][j].setEnabled(true);
									}
								}
							}
							else if(botonera[i][j].getBackground() == Color.YELLOW){
								botonera[i][j].setBackground(null);
								for(int w = 0; w < cartas.size(); w++){
									if(cartas.get(w).equals(botonera[i][j].getText())){
										cartas.remove(w);
									}
								}
								for(int w = 0; w < board.size(); w++){
									if(board.get(w).equals(botonera[i][j].getText())){
										board.remove(w);
									}
								}
								contador--;
							}
							else{
								botonera[i][j].setBackground(null);
							}
						}
						else if(boardDead == 1){
							if(botonera[i][j].getBackground() != Color.BLACK){
								botonera[i][j].setBackground(Color.BLACK);
								cartas.add(botonera[i][j].getText());
								dead.add(botonera[i][j].getText());
							}
							else if(botonera[i][j].getBackground() == Color.BLACK){
								botonera[i][j].setBackground(null);
								for(int w = 0; w < cartas.size(); w++){
									if(cartas.get(w).equals(botonera[i][j].getText())){
										cartas.remove(w);
									}
								}
								for(int w = 0; w < dead.size(); w++){
									if(dead.get(w).equals(botonera[i][j].getText())){
										dead.remove(w);
									}
								}
							}
							else{
								botonera[i][j].setBackground(null);
							}
						}
					}
				}
			}
		}			
	}
	
	class PanelOpciones extends JPanel implements ActionListener{
		JFrame prin;
		Controller con;
		public PanelOpciones(JFrame f, Controller con){
			this.con = con;
			prin = f;
			setLayout(new FlowLayout());
			aceptar = new JButton("aceptar");
			aceptar.addActionListener(this);
			add(aceptar);
			
			cancelar = new JButton("cancelar");
			cancelar.addActionListener(this);
			add(cancelar);
			
			setVisible(true);
			
		}
		
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == aceptar){
				if(boardDead == 0){
					con.botonesBoard(cartas);
					for(int i = 0; i < 13; i++){
						for(int j = 0; j < 4; j++){
							if(botonera[i][j].getBackground() == Color.YELLOW){
									cartasParaTf += botonera[i][j].getText();
						}
					}
					}
					con.actualizaBoard(cartasParaTf);
				}
				if(boardDead == 1){
					con.botonesDead(cartas);
					for(int i = 0; i < 13; i++){
						for(int j = 0; j < 4; j++){
							if(botonera[i][j].getBackground() == Color.BLACK){
									cartasParaTf += botonera[i][j].getText();
						}
					}
					}
					con.actualizaDead(cartasParaTf);
				}
				for(int i = 0; i < 13; i++){
					for(int j = 0; j < 4; j++){
						for(int w = 0; w < contador; w++){
							if(cartas.get(w).equals(botonera[i][j].getText())){
								cartas.remove(w);
							}
							contador--;
						}
						
					}
				}
				for(int i = 0; i < 13; i++){
					for(int j = 0; j < 4; j++){
						for(int w = 0; w < contador; w++){
							if(board.get(w).equals(botonera[i][j].getText())){
								board.remove(w);
							}
							contador--;
						}
						
					}
				}
				for(int i = 0; i < 13; i++){
					for(int j = 0; j < 4; j++){
						for(int w = 0; w < contador; w++){
							if(dead.get(w).equals(botonera[i][j].getText())){
								dead.remove(w);
							}
							contador--;
						}
						
					}
				}
				prin.setVisible(false);
				View.cartasMuertas.setEnabled(true);
				View.cartasBoard.setEnabled(true);
				cartasParaTf = "";
			}
			else if(e.getSource() == cancelar){
				for(int i = 0; i < 13; i++){
					for(int j = 0; j < 4; j++){
							if((cartas.size() > 0) && (cartas.get(0).equals(botonera[i][j].getText()))){
								con.quitarCartas(botonera[i][j].getText());
								cartas.remove(0);
							}
							if((board.size() > 0) && (board.get(0).equals(botonera[i][j].getText()))){
								con.quitarCartas(botonera[i][j].getText());
								board.remove(0);
							}
							if((dead.size() > 0) && (dead.get(0).equals(botonera[i][j].getText()))){
								con.quitarCartas(botonera[i][j].getText());
								dead.remove(0);
							}
						contador = 0;
						botonera[i][j].setBackground(null);
					}
				}
				prin.setVisible(false);
				View.cartasMuertas.setEnabled(true);
				View.cartasBoard.setEnabled(true);
				cartasParaTf = "";
			}
			
		}
	}

	public void quienSoy(int s) {
		// TODO Auto-generated method stub
		boardDead = s;
		if(boardDead == 0){
			for(int i = 0; i < 13; i++){
				for(int j = 0; j < 4; j++){
					if(botonera[i][j].getBackground() == Color.BLACK){
						botonera[i][j].setEnabled(false);
					}
					else if(botonera[i][j].getBackground() == Color.YELLOW){
						botonera[i][j].setEnabled(true);
						cartas.add(botonera[i][j].getText());
						contador++;
					}
				}
			}
			max = 5;
		}
		if(boardDead == 1){
			for(int i = 0; i < 13; i++){
				for(int j = 0; j < 4; j++){
					if(botonera[i][j].getBackground() == Color.BLACK){
						botonera[i][j].setEnabled(true);
						cartas.add(botonera[i][j].getText());
					}
					else if(botonera[i][j].getBackground() == Color.YELLOW){
						botonera[i][j].setEnabled(false);
					}
				}
			}
			max = 52;
		}
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarPorcentaje(double porcentaje) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarPosicionEnRanking(int posicionEnRanking) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarRankingStr(String[] rankingStr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarRanking(String[] rankingDeManosIniciales) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarGrafica(int i, String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarTexto(int nRank, String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarBoton(int i, int j) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarMano(String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarPorcentaje() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarDatosDeManos(ArrayList<ManoHero> manos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarBotonDeBoard(int i, int j, boolean b) {
		// TODO Auto-generated method stub
		if(b)
			botonera[i][j].setBackground(Color.YELLOW);
		else{
			botonera[i][j].setBackground(null);
			botonera[i][j].setEnabled(true);
		}
	}

	@Override
	public void actualizarBotonDeDead(int i, int j, boolean b) {
		// TODO Auto-generated method stub
		if(b)
			botonera[i][j].setBackground(Color.BLACK);
		else{
			botonera[i][j].setBackground(null);
			botonera[i][j].setEnabled(true);
		}
	}

	@Override
	public void actualizarDatosJug(int jug, ArrayList<ParCartas> cartasRango) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizaTextBoard(Carta[] car, String cartasParaTf) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizaTextDead(Carta[] car, String cartasParaTf) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizaArraytBoard(Carta[] car) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizaArraytDead(Carta[] car) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void comprobarConocerLibres(int libresCar1, int libresCar2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cambiarPaloLibre(char palo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void comprobarRepetida(boolean c1, boolean c2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void damePalo(char p) {
		// TODO Auto-generated method stub
		
	}
	
}
