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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Controller.Controller;
import Model.Carta;
import Model.ManoHero;
import Model.ParCartas;
import Observer.Observer;

public class Grafic extends JFrame implements Observer, ActionListener, KeyListener, ChangeListener{

	private static final long serialVersionUID = 1L;

	private Controller con;
	private Container panelPrincipal;
	private JPanel panelBotonera;
	private JPanel panelInf;
	private JPanel panelIzq;
	private JPanel botonesInf;
	private JPanel zonaSlider;
	private JTextField porcentaje;
	private JTextField porcentPol;
	private JButton[][] botonera;
	private JButton aceptar;
	private JButton cancelar;
	private JButton all;
	private JButton suited;
	private JButton off;
	private JButton pair;
	private JButton clear;
	private JButton random;
	private JPanel panelB;
	private JPanel panelDer;
	private View v;
	private int nRank;
	private JLabel texto;
	private double seleccionados;

	private JSlider slider;

	private double valor;

	private JPanel zonaPol;
	static final int FPS_MIN = 0;
	static final int FPS_MAX = 1000;
	static final int FPS_INIT = 0;    //initial frames per second

	public Grafic(Controller con, View view){
		super("Grafic Rank");
		this.con = con;
		this.v = view;
		seleccionados = 0.0;
		/* Creacion de la ventana y tamano de la misma */
		panelPrincipal = this.getContentPane();
		panelPrincipal.setLayout(new BorderLayout());
		((JComponent) panelPrincipal).setBorder(new EmptyBorder(5, 5, 5, 5));
		Dimension dim = new Dimension();
		dim = super.getToolkit().getScreenSize();
		int altura = dim.height;
		int ancho = dim.width;

		this.setMinimumSize(new Dimension(850, 420));
		this.setResizable(false);
		this.setLocation((ancho / 10), (altura/10));
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
					n.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{

							if(n.getBackground() != Color.yellow){
								n.setBackground(Color.yellow);
								double selec = 6/1326.0;
								selec = Math.round(selec * 1000);
								selec = selec/1000;
								selec = selec*100;
								seleccionados += selec;
								double x = Double.parseDouble(porcentaje.getText());
								x+=selec;
								porcentaje.setText(x+"");
								valor = x;
							}
							else{
								n.setBackground(Color.green);
								double selec = 6/1326.0;
								selec = Math.round(selec * 1000);
								selec = selec/1000;
								selec = selec*100;
								seleccionados -= selec;
								double x = Double.parseDouble(porcentaje.getText());
								x-=selec;
								porcentaje.setText(x+"");
								valor = x;
							}
						}
					});
				}
				else if (i < j){
					botonera[i][j] = new JButton(cartas[i] + "" + cartas[j] + "s");
					botonera[i][j].setBackground(Color.red);
					JButton n = botonera[i][j];
					n.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							if(n.getBackground() != Color.red){
								n.setBackground(Color.red);
								double selec = 4/1326.0;
								selec = Math.round(selec * 1000);
								selec = selec/1000;
								selec = selec*100;
								seleccionados -= selec;
								double x = Double.parseDouble(porcentaje.getText());
								x-=selec;
								porcentaje.setText(x+"");
								valor = x;
							}
							else{
								n.setBackground(Color.yellow);
								double selec = 4/1326.0;
								selec = Math.round(selec * 1000);
								selec = selec/1000;
								selec = selec*100;
								seleccionados += selec;
								double x = Double.parseDouble(porcentaje.getText());
								x+=selec;
								porcentaje.setText(x+"");
								valor = x;
							}
						}
					});
				}
				else {
					botonera[i][j] = new JButton(cartas[j] + "" + cartas[i] + "o"); 
					botonera[i][j].setBackground(Color.cyan);
					JButton n = botonera[i][j];
					n.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							if(n.getBackground() != Color.cyan){
								n.setBackground(Color.cyan);
								double selec = 12/1326.0;
								selec = Math.round(selec * 1000);
								selec = selec/1000;
								selec = selec*100;
								seleccionados -= selec;
								double x = Double.parseDouble(porcentaje.getText());
								x-=selec;
								porcentaje.setText(x+"");
								valor = x;
							}
							else{
								n.setBackground(Color.yellow);
								double selec = 12/1326.0;
								selec = Math.round(selec * 1000);
								selec = selec/1000;
								selec = selec*100;
								seleccionados += selec;
								double x = Double.parseDouble(porcentaje.getText());
								x+=selec;
								porcentaje.setText(x+"");
								valor = x;
							}
						}
					});
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
		slider = new JSlider(JSlider.HORIZONTAL,
				FPS_MIN, FPS_MAX, FPS_INIT);

		slider.setPaintTicks(false);
		slider.setPaintLabels(false);
		slider.setPreferredSize(new Dimension(675, 50));
		slider.addChangeListener(this);
		valor = 0.0;
		porcentaje = new JTextField(3);
		porcentaje.setText("0.0");

		zonaSlider = new JPanel();
		zonaSlider.setLayout(new FlowLayout());
		zonaSlider.add(slider);
		zonaSlider.add(porcentaje);
		panelIzq.add(zonaSlider, BorderLayout.SOUTH);

		texto = new JLabel("Rango polarizado: ");
		porcentPol = new JTextField(15);
		porcentPol.addKeyListener(this);
		aceptar = new JButton("Aceptar");
		cancelar = new JButton("Cancelar");
		aceptar.addActionListener(this);
		cancelar.addActionListener(this);
		panelInf = new JPanel();
		botonesInf = new JPanel();
		zonaPol = new JPanel();
		botonesInf.setLayout(new FlowLayout());
		zonaPol.setLayout(new FlowLayout());
		panelInf.setLayout(new BorderLayout());
		zonaPol.add(texto);
		zonaPol.add(porcentPol);
		botonesInf.add(cancelar);
		botonesInf.add(aceptar);
		panelInf.add(zonaPol, BorderLayout.WEST);
		panelInf.add(botonesInf, BorderLayout.EAST);

		panelDer = new JPanel();
		panelDer.setLayout(new BoxLayout(panelDer,BoxLayout.Y_AXIS));
		panelDer.setBorder(new EmptyBorder(5, 5, 5, 10));
		all = new JButton("All");
		suited = new JButton("Suited");
		off = new JButton("Off Suited");
		pair = new JButton("All pair");
		clear = new JButton("Clear");
		random = new JButton("Random");
		all.setMaximumSize(new Dimension(100, 30));
		suited.setMaximumSize(new Dimension(100, 30));
		off.setMaximumSize(new Dimension(100, 30));
		pair.setMaximumSize(new Dimension(100, 30));
		clear.setMaximumSize(new Dimension(100, 30));
		random.setMaximumSize(new Dimension(100, 30));
		panelDer.add(Box.createRigidArea(new Dimension(0, 40)));
		panelDer.add(all);
		panelDer.add(Box.createRigidArea(new Dimension(0, 10)));
		panelDer.add(suited);
		panelDer.add(Box.createRigidArea(new Dimension(0, 10)));
		panelDer.add(off);
		panelDer.add(Box.createRigidArea(new Dimension(0, 10)));
		panelDer.add(pair);
		panelDer.add(Box.createRigidArea(new Dimension(0, 10)));
		panelDer.add(clear);
		panelDer.add(Box.createRigidArea(new Dimension(0, 10)));
		panelDer.add(random);
		all.addActionListener(this);
		suited.addActionListener(this);
		off.addActionListener(this);
		pair.addActionListener(this);
		clear.addActionListener(this);
		random.addActionListener(this);
		porcentaje.addKeyListener(this);
		this.add(panelDer, BorderLayout.EAST);
		this.add(panelIzq, BorderLayout.WEST);
		this.add(panelInf, BorderLayout.SOUTH);
		this.setVisible(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(pair == e.getSource()){
			for(int i = 0; i < 13; i++){
				if(this.botonera[i][i].getBackground() != Color.yellow){
					botonera[i][i].setBackground(Color.yellow);
					double selec = 6/1326.0;
					selec = Math.round(selec * 1000);
					selec = selec/1000;
					selec = selec*100;
					seleccionados += selec;
					double x = Double.parseDouble(porcentaje.getText());
					x+=selec;
					porcentaje.setText(x+"");
					valor = x;
				}
			}
		}
		if(suited == e.getSource()){
			for(int i = 0; i < 13; i++){
				for(int j = i+1; j < 13; j++){
					if(this.botonera[i][j].getBackground() != Color.yellow){
						botonera[i][j].setBackground(Color.yellow);
						double selec = 4/1326.0;
						selec = Math.round(selec * 1000);
						selec = selec/1000;
						selec = selec*100;
						seleccionados += selec;
						double x = Double.parseDouble(porcentaje.getText());
						x+=selec;
						double roundOff = Math.round(x * 100.0) / 100.0;
						porcentaje.setText(roundOff+"");
						valor = x;
					}
				}
			}
		}
		if(off == e.getSource()){
			for(int i = 0; i < 13; i++){
				for(int j = i+1; j < 13; j++){
					if(this.botonera[j][i].getBackground() != Color.yellow){
						botonera[j][i].setBackground(Color.yellow);
						double selec = 12/1326.0;
						selec = Math.round(selec * 1000);
						selec = selec/1000;
						selec = selec*100;
						seleccionados += selec;
						double x = Double.parseDouble(porcentaje.getText());
						x+=selec;
						double roundOff = Math.round(x * 100.0) / 100.0;
						porcentaje.setText(roundOff+"");
						valor = x;
					}
				}
			}
		}
		if(all == e.getSource()){
			for(int i = 0; i < 13; i++){
				for(int j = 0; j < 13; j++){
					if(this.botonera[j][i].getBackground() != Color.yellow){
						botonera[j][i].setBackground(Color.yellow);
					}
				}
			}
			valor = 100.0;
			porcentaje.setText(100.0 +"");
		}
		if(random == e.getSource()){
			for(int i = 0; i < 13; i++){
				for(int j = 0; j < 13; j++){
					if (i == j) {
						if(botonera[i][j].getBackground() == Color.yellow)
							botonera[i][j].setBackground(Color.green);
					}
					else if(i < j){
						if(botonera[i][j].getBackground() == Color.yellow)
							botonera[i][j].setBackground(Color.red);
					}
					else if (i > j){
						if(botonera[i][j].getBackground() == Color.yellow)
							botonera[i][j].setBackground(Color.cyan);
					}
				}
			}
			int numR = (int) (Math.random()*169+1);
			while(numR < 5){
				 numR = (int) (Math.random()*169+1);
			}
			int[][] selec = new int[13][13];
			for(int i = 0; i < numR; i++){
				int f = (int) (Math.random()*13);
				int c = (int) (Math.random()*13);
				selec[f][c] = 1;
			}
			for(int i = 0; i < 13; i++){
				for(int j = 0; j < 13; j++){
					if(this.botonera[j][i].getBackground() != Color.yellow){
						if(selec[i][j] == 1){
							botonera[j][i].setBackground(Color.yellow);
						}
					}
				}
			}
		}
		if(clear == e.getSource()){
			for(int i = 0; i < 13; i++){
				for(int j = 0; j < 13; j++){
					if (i == j) {
						if(botonera[i][j].getBackground() == Color.yellow)
							botonera[i][j].setBackground(Color.green);
					}
					else if(i < j){
						if(botonera[i][j].getBackground() == Color.yellow)
							botonera[i][j].setBackground(Color.red);
					}
					else if (i > j){
						if(botonera[i][j].getBackground() == Color.yellow)
							botonera[i][j].setBackground(Color.cyan);
					}
				}
			}
			porcentaje.setText(0.0 +"");
		}
		if(cancelar == e.getSource()){
			for(int i = 0; i < 13; i++){
				for(int j = 0; j < 13; j++){
					if(botonera[i][j].getBackground() == Color.yellow){
						if(i == j) botonera[i][j].setBackground(Color.green);
						else if(i < j) botonera[i][j].setBackground(Color.red);
						else if(i > j) botonera[i][j].setBackground(Color.cyan);
					}
				}
			}
			seleccionados = 0.0;
			porcentPol.setText("");
			porcentPol.setBackground(Color.WHITE);
			this.slider.setValue(0);
			this.porcentaje.setText("0.0");
			this.setVisible(false);
		}
		if(aceptar == e.getSource()){

			for(int i = 0; i < 13; i++){
				for(int j = 0; j < 13; j++){
					if(botonera[i][j].getBackground() == Color.yellow){
						con.marcar(i, j, nRank);
						if(i == j) botonera[i][j].setBackground(Color.green);
						else if(i < j) botonera[i][j].setBackground(Color.red);
						else if(i > j) botonera[i][j].setBackground(Color.cyan);
					}
				}
			}
			seleccionados = 0.0;
			this.slider.setValue((int) (valor*10));
			//this.porcentaje.setText("0.0");
			porcentPol.setText("");
			porcentPol.setBackground(Color.WHITE);
			con.selecATexto(nRank);
			this.porcentaje.setText("0.0");
			this.slider.setValue(0);
			this.setVisible(false);
		}
	}
	public void setNRank(int nRank){
		this.nRank=nRank;
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizarGrafica(int n, String text) {
		// TODO Auto-generated method stub
		this.nRank = n;
		for(int i = 0; i < 13; i++){
			for(int j = 0; j < 13; j++){
				if(botonera[i][j].getText().equals(text)){
					botonera[i][j].setBackground(Color.yellow);
				}
			}
		}
	}

	@Override
	public void actualizarTexto(int nRank,String selec) {
		// TODO Auto-generated method stub
		v.actualizarTexto(this.nRank, selec);
	}

	@Override
	public void actualizarBoton(int i, int j) {
		// TODO Auto-generated method stub
		this.botonera[i][j].setBackground(Color.yellow);	
	}

	@Override
	public void actualizarPorcentaje(double porcentajeRango) {
		// TODO Auto-generated method stub
		this.porcentaje.setText(""+ porcentajeRango);
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
	public void actualizarMano(String string) {
		// TODO Auto-generated method stub
		for(int i = 0; i < 13; i++){
			for(int j = 0; j < 13; j++){
				if(botonera[i][j].getText().equals(string)){
					botonera[i][j].setBackground(Color.yellow);
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		String text = "";

		if (e.getSource() == porcentaje) {
			text = porcentaje.getText();
			if(con.porcentajeCorrecto(text)){
				for(int i = 0; i < 13; i++){
					for(int j = 0; j < 13; j++){
						if(botonera[i][j].getBackground() == Color.yellow){
							if(i == j) botonera[i][j].setBackground(Color.green);
							else if(i < j) botonera[i][j].setBackground(Color.red);
							else if(i > j) botonera[i][j].setBackground(Color.cyan);
						}
					}
				}
				con.darPorcentaje(Double.parseDouble(text));
			}
		}
		if (e.getSource() == porcentPol) {
			boolean ok = false;
			text = porcentPol.getText();
			if(!porcentPol.getText().isEmpty()){
				text = porcentPol.getText();
				if(text.length() == 11){
					ok = con.entradaPorcentaje(text);
					if(ok){
						porcentPol.setBackground(Color.green);
					}
					else{
						porcentPol.setBackground(Color.red);
					}
				}
				else porcentPol.setBackground(Color.red);
			}
			else{
				porcentPol.setBackground(Color.white);
			}
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		if(slider == e.getSource()){
			for(int i = 0; i < 13; i++){
				for(int j = 0; j < 13; j++){
					if(botonera[i][j].getBackground() == Color.yellow){
						if(i == j) botonera[i][j].setBackground(Color.green);
						else if(i < j) botonera[i][j].setBackground(Color.red);
						else if(i > j) botonera[i][j].setBackground(Color.cyan);
					}
				}
			}
			int fps = slider.getValue();
			valor = fps/10.0;
			this.porcentaje.setText(""+valor);;
			con.darPorcentaje(valor);
		}
	}

	@Override
	public void actualizarPorcentaje() {
		this.porcentaje.setText(valor+"");
		this.slider.setValue((int) (valor*10));
	}

	public void setValor() {
		// TODO Auto-generated method stub
	}

	@Override
	public void actualizarDatosDeManos(ArrayList<ManoHero> manos) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizarBotonDeBoard(int i, int j, boolean b) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizarBotonDeDead(int i, int j, boolean b) {
		// TODO Auto-generated method stub

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
