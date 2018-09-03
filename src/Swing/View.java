package Swing;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.rtf.RTFEditorKit;

import Controller.Controller;
import Model.Baraja;
import Model.BarajaJugador;
import Model.Carta;
import Model.ManoHero;
import Model.ParCartas;
import Model.Resolucion;
import Observer.Observer;


public class View implements Observer, ActionListener, KeyListener{

	private Controller con;
	private JButton ayuda;
	private JButton nuevo;
	private JButton evaluar;
	private JButton p1;
	private JButton p2;
	private JButton p3;
	private JButton p4;
	private JButton p5;
	private JButton p6;
	private JButton p7;
	private JButton p8;
	private JButton p9;
	private JButton p10;
	private JTextField r1Text;
	private JTextField r2Text;
	private JTextField r3Text;
	private JTextField r4Text;
	private JTextField r5Text;
	private JTextField r6Text;
	private JTextField r7Text;
	private JTextField r8Text;
	private JTextField r9Text;
	private JTextField r10Text;
	private JTextField e1;
	private JTextField e2;
	private JTextField e3;
	private JTextField e4;
	private JTextField e5;
	private JTextField e6;
	private JTextField e7;
	private JTextField e8;
	private JTextField e9;
	private JTextField e10;
	private JTextArea resultado;
	private JPanel panelIzq;
	@SuppressWarnings("unused")
	private String dec;
	private JPanel panelCen;
	private JPanel panelDer;
	private static JTextField board;
	protected static JButton cartasBoard;
	protected static JButton cartasMuertas;
	private static JTextField dead;
	private JPanel panel;
	private JPanel panel2;
	private Grafic g;
	private boolean ok;
	PanelSeleccionMano panelCartas;
	public String textAux;
	public String textAux2;
	private BarajaJugador[] cartasJugadores;
	private Carta[] arrayCartasBoard;
	private Carta[] arrayCartasBoardConRandom;
	private Carta[] arrayCartasDead;
	public String textAux3;
	private boolean gr1;
	private boolean gr10;
	private boolean gr9;
	private boolean gr8;
	private boolean gr7;
	private boolean gr6;
	private boolean gr5;
	private boolean gr4;
	private boolean gr3;
	private boolean gr2;
	private boolean c1;
	private boolean c2;
	private int alMenosDos;
	private int sum1;
	private int sum2;
	private int sum3;
	private int sum4;
	private int sum5;
	private int sum6;
	private int sum7;
	private int sum8;
	private int sum9;
	private int sum10;
	private char peso;
	private int libresCar1;
	private int libresCar2;
	private char paloLibre;
	private Carta carta1a;
	private Carta carta2a;
	private JFrame prin;

	public View(Controller con2){
		
		prin = new JFrame("Practica 3, The New PokerStove");
		this.con = con2;
		g = new Grafic(con, this);
		carta1a = null;
		carta2a = null;
		gr1 = false;
		gr2 = false;
		gr3 = false;
		gr4 = false;
		gr5 = false;
		gr6 = false;
		gr7 = false;
		gr8 = false;
		gr9 = false;
		gr10 = false;
		sum1 = 0;
		sum2 = 0;
		sum3 = 0;
		sum4 = 0;
		sum5 = 0;
		sum6 = 0;
		sum7 = 0;
		sum8 = 0;
		sum9 = 0;
		sum10 = 0;
		arrayCartasBoard = new Carta[5];
		panelCartas = new PanelSeleccionMano(con);
		panelCartas.setVisible(false);
		cartasJugadores = new BarajaJugador[10];
		
		
		
		for(int i = 0; i < 10; i++) cartasJugadores[i] = new BarajaJugador();
		prin.setLayout(new BorderLayout());
		prin.add(new panelNorte(con), BorderLayout.NORTH);
		prin.add(new panelEste(con), BorderLayout.EAST);
		prin.add(new panelOeste(con), BorderLayout.WEST);
		prin.add(new panelSur(con), BorderLayout.SOUTH);
		prin.setVisible(true);
	/*	Dimension dim = new Dimension();
		dim = super.getToolkit().getScreenSize();
		int altura = dim.height;
		int ancho = dim.width;*/
		prin.setMinimumSize(new Dimension(700, 650));
		//prin.setLocation((ancho / 7), (altura / 15));
		prin.setResizable(false);
		//prin.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

	class panelNorte extends JPanel implements ActionListener {

		private static final long serialVersionUID = 1L;
		private Controller con;
		public panelNorte(Controller con){
			this.con = con;
			setLayout(new FlowLayout());
			ayuda = new JButton("Ayuda");
		
			nuevo = new JButton("Nuevo Ranking y/o Rango");
			ayuda.setLayout((new BorderLayout()));
			nuevo.setLayout((new BorderLayout()));
		
			Dimension tamCarg = new Dimension(100, 25);
			ayuda.setPreferredSize(tamCarg);
			
			nuevo.setPreferredSize(new Dimension(125, 25));
			ayuda.addActionListener(this);
			
			nuevo.addActionListener(this);
			add(ayuda);
			
			add(nuevo);
			setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == nuevo){
				try {
					ChooserRang_Rank c = new ChooserRang_Rank();
					prin.setVisible(false);
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

		}
	}

	class panelEste extends JPanel implements ActionListener, KeyListener {

		private static final long serialVersionUID = 1L;
		private Controller con;

		public panelEste(Controller con){
			this.con = con;
			this.setLayout(new BorderLayout());
			panel = new JPanel();
			panel2 = new JPanel();
			panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
			panel2.setLayout(new BoxLayout(panel2,BoxLayout.Y_AXIS));
			cartasBoard = new JButton();
			cartasBoard.setIcon(new ImageIcon("cartas.png"));
			cartasBoard.addActionListener(this);
			panel2.add(Box.createRigidArea(new Dimension(0, 10)));
			cartasMuertas = new JButton();
			cartasMuertas.setIcon(new ImageIcon("cartas.png"));
			cartasMuertas.addActionListener(this);
			Dimension b = new Dimension(30, 30);
			cartasBoard.setSize(b);
			cartasMuertas.setSize(b);
			dead = new JTextField(15);
			board = new JTextField(15);
			Dimension t = new Dimension(100, 20);
			board.setMaximumSize(t);
			dead.setMaximumSize(t);
			board.addKeyListener(this);
			dead.addKeyListener(this);
			panel2.add(board);
			panel2.add(Box.createRigidArea(new Dimension(0, 20)));
			panel.add(cartasBoard);
			panel.add(Box.createRigidArea(new Dimension(0, 20)));
			panel2.add(dead);
			panel.add(cartasMuertas);
			textAux = "";
			textAux2 = "";
			textAux3 = "";
			alMenosDos = 0;

			evaluar = new JButton("Evaluar");
			evaluar.addActionListener(this);
			add(panel, BorderLayout.EAST);
			add(panel2, BorderLayout.WEST);
			add(evaluar, BorderLayout.SOUTH);
			setBorder(new TitledBorder("Mesa"));
			setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			String text = "";
			if(e.getSource() == cartasBoard){
				text = board.getText();
				ok = con.entraBoard(text);
				if(ok){
					panelCartas.setVisible(true);
					panelCartas.quienSoy(0);
					con.darBoard(text, textAux);
					textAux = text;
					cartasMuertas.setEnabled(false);
				}
			}
			if(e.getSource() == cartasMuertas){
				text = dead.getText();
				ok = con.entraDead(text);
				if(ok){
					panelCartas.setVisible(true);
					panelCartas.quienSoy(1);
					con.darDead(text, textAux2);
					textAux2 = text;
					cartasBoard.setEnabled(false);
				}
			}
			if(e.getSource() == evaluar){
				int s = 0;
				for(int i = 0; i < cartasJugadores.length; i++){
					if(cartasJugadores[i].getCartas().size() != 0){
						s++;
					}
				}
				if(s >= 2){
					Resolucion r = new Resolucion(con, cartasJugadores, arrayCartasBoard, arrayCartasDead);
					switch(Resolucion.equityTotal.length) {
					case 1:
						e1.setText(Resolucion.equityTotal[0] + "%"); break;
					case 2: 
						e1.setText(Resolucion.equityTotal[0] + "%"); 
						e2.setText(Resolucion.equityTotal[1] + "%");break;
					case 3:
						e1.setText(Resolucion.equityTotal[0] + "%"); 
						e2.setText(Resolucion.equityTotal[1] + "%");
						e3.setText(Resolucion.equityTotal[2] + "%");break;
					case 4:
						e1.setText(Resolucion.equityTotal[0] + "%"); 
						e2.setText(Resolucion.equityTotal[1] + "%");
						e3.setText(Resolucion.equityTotal[2] + "%");
						e4.setText(Resolucion.equityTotal[3] + "%");break;
					case 5:
						e1.setText(Resolucion.equityTotal[0] + "%"); 
						e2.setText(Resolucion.equityTotal[1] + "%");
						e3.setText(Resolucion.equityTotal[2] + "%");
						e4.setText(Resolucion.equityTotal[3] + "%");
						e5.setText(Resolucion.equityTotal[4] + "%"); break;
					case 6:
						e1.setText(Resolucion.equityTotal[0] + "%"); 
						e2.setText(Resolucion.equityTotal[1] + "%");
						e3.setText(Resolucion.equityTotal[2] + "%");
						e4.setText(Resolucion.equityTotal[3] + "%");
						e5.setText(Resolucion.equityTotal[4] + "%");
						e6.setText(Resolucion.equityTotal[5] + "%"); break;
					case 7:
						e1.setText(Resolucion.equityTotal[0] + "%"); 
						e2.setText(Resolucion.equityTotal[1] + "%");
						e3.setText(Resolucion.equityTotal[2] + "%");
						e4.setText(Resolucion.equityTotal[3] + "%");
						e5.setText(Resolucion.equityTotal[4] + "%");
						e6.setText(Resolucion.equityTotal[5] + "%");
						e7.setText(Resolucion.equityTotal[6] + "%"); break;
					case 8:
						e1.setText(Resolucion.equityTotal[0] + "%"); 
						e2.setText(Resolucion.equityTotal[1] + "%");
						e3.setText(Resolucion.equityTotal[2] + "%");
						e4.setText(Resolucion.equityTotal[3] + "%");
						e5.setText(Resolucion.equityTotal[4] + "%");
						e6.setText(Resolucion.equityTotal[5] + "%");
						e7.setText(Resolucion.equityTotal[6] + "%");
						e8.setText(Resolucion.equityTotal[7] + "%");break;
					case 9:
						e1.setText(Resolucion.equityTotal[0] + "%"); 
						e2.setText(Resolucion.equityTotal[1] + "%");
						e3.setText(Resolucion.equityTotal[2] + "%");
						e4.setText(Resolucion.equityTotal[3] + "%");
						e5.setText(Resolucion.equityTotal[4] + "%");
						e6.setText(Resolucion.equityTotal[5] + "%");
						e7.setText(Resolucion.equityTotal[6] + "%");
						e8.setText(Resolucion.equityTotal[7] + "%");
						e9.setText(Resolucion.equityTotal[8] + "%"); break;
					case 10:
						e1.setText(Resolucion.equityTotal[0] + "%"); 
						e2.setText(Resolucion.equityTotal[1] + "%");
						e3.setText(Resolucion.equityTotal[2] + "%");
						e4.setText(Resolucion.equityTotal[3] + "%");
						e5.setText(Resolucion.equityTotal[4] + "%");
						e6.setText(Resolucion.equityTotal[5] + "%");
						e7.setText(Resolucion.equityTotal[6] + "%");
						e8.setText(Resolucion.equityTotal[7] + "%");
						e9.setText(Resolucion.equityTotal[8] + "%");
						e10.setText(Resolucion.equityTotal[9] + "%"); break;
					}
					resultado.setText("");
					resultado.setText(Resolucion.estoEsParaLaVista);
				}
				else{
					JOptionPane.showMessageDialog(null, "Hay pocos rangos, son necesarios 2");
				}
				s=0;
			}
		}

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			String text = "";

			if (e.getSource() == board) {
				if(!board.getText().isEmpty()){
					if(board.getText().length() <= 10){
						text = board.getText();
						ok = con.entraBoard(text);
						if(ok){
							if(con.repetida(board.getText(), dead.getText())){
								board.setBackground(Color.green);
								con.darBoard(text, textAux);
								textAux = text;
								String mom = r1Text.getText();
								r1Text.setText("");
								r1Text.setText(mom);
								con.asignarCartasBaraja(0, mom);
								mom = r2Text.getText();
								r2Text.setText("");
								r2Text.setText(mom);
								con.asignarCartasBaraja(1, mom);
								mom = r3Text.getText();
								r3Text.setText("");
								r3Text.setText(mom);
								con.asignarCartasBaraja(2, mom);
								mom = r4Text.getText();
								r4Text.setText("");
								r4Text.setText(mom);
								con.asignarCartasBaraja(3, mom);
								mom = r5Text.getText();
								r5Text.setText("");
								r5Text.setText(mom);
								con.asignarCartasBaraja(4, mom);
								mom = r6Text.getText();
								r6Text.setText("");
								r6Text.setText(mom);
								con.asignarCartasBaraja(5, mom);
								mom = r7Text.getText();
								r7Text.setText("");
								r7Text.setText(mom);
								con.asignarCartasBaraja(6, mom);
								mom = r8Text.getText();
								r8Text.setText("");
								r8Text.setText(mom);
								con.asignarCartasBaraja(7, mom);
								mom = r9Text.getText();
								r9Text.setText("");
								r9Text.setText(mom);
								con.asignarCartasBaraja(8, mom);
								mom = r10Text.getText();
								r10Text.setText("");
								r10Text.setText(mom);
								con.asignarCartasBaraja(9, mom);
								cartasBoard.setEnabled(true);
							}
							else{
								board.setBackground(Color.red);
								cartasBoard.setEnabled(false);
							}
						}
						else{
							board.setBackground(Color.red);
							cartasBoard.setEnabled(false);
						}
					}
					else {
						board.setBackground(Color.red);
						cartasBoard.setEnabled(false);
						ok= false;
					}
				}
				else{
					board.setBackground(Color.white);
					con.darBoard("", textAux);
					textAux = "";
					cartasBoard.setEnabled(true);
				}
			}
			if (e.getSource() == dead) {
				if(!dead.getText().isEmpty()){
					text = dead.getText();
					ok = con.entraDead(text);
					if(ok){
						if(con.repetida(dead.getText(), board.getText())){
							dead.setBackground(Color.green);
							con.darDead(text, textAux2);
							textAux2 = text;
							String mom = r1Text.getText();
							r1Text.setText("");
							r1Text.setText(mom);
							con.asignarCartasBaraja(0, mom);
							mom = r2Text.getText();
							r2Text.setText("");
							r2Text.setText(mom);
							con.asignarCartasBaraja(1, mom);
							mom = r3Text.getText();
							r3Text.setText("");
							r3Text.setText(mom);
							con.asignarCartasBaraja(2, mom);
							mom = r4Text.getText();
							r4Text.setText("");
							r4Text.setText(mom);
							con.asignarCartasBaraja(3, mom);
							mom = r5Text.getText();
							r5Text.setText("");
							r5Text.setText(mom);
							con.asignarCartasBaraja(4, mom);
							mom = r6Text.getText();
							r6Text.setText("");
							r6Text.setText(mom);
							con.asignarCartasBaraja(5, mom);
							mom = r7Text.getText();
							r7Text.setText("");
							r7Text.setText(mom);
							con.asignarCartasBaraja(6, mom);
							mom = r8Text.getText();
							r8Text.setText("");
							r8Text.setText(mom);
							con.asignarCartasBaraja(7, mom);
							mom = r9Text.getText();
							r9Text.setText("");
							r9Text.setText(mom);
							con.asignarCartasBaraja(8, mom);
							mom = r10Text.getText();
							r10Text.setText("");
							r10Text.setText(mom);
							con.asignarCartasBaraja(9, mom);
							cartasMuertas.setEnabled(true);
						}
						else{
							dead.setBackground(Color.red);
							cartasMuertas.setEnabled(false);
						}
					}
					else{
						dead.setBackground(Color.red);
						cartasMuertas.setEnabled(false);
					}
				}
				else{
					dead.setBackground(Color.white);
					con.darDead(text, textAux2);
					textAux2 = "";
					cartasMuertas.setEnabled(true);
				}
			}
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}
	}

	class panelOeste extends JPanel implements ActionListener, KeyListener {

		private static final long serialVersionUID = 1L;
		private Controller con;

		public panelOeste(Controller con){
			this.con = con;
			this.setLayout(new BorderLayout());
			panelIzq = new JPanel();
			panelIzq.setBorder(new EmptyBorder(15, 5, 5, 5));
			panelIzq.setLayout(new BoxLayout(panelIzq,BoxLayout.Y_AXIS));
			p1 = new JButton("Grafic Rank 1");
			p2 = new JButton("Grafic Rank 2");
			p3 = new JButton("Grafic Rank 3");
			p4 = new JButton("Grafic Rank 4");
			p5 = new JButton("Grafic Rank 5");
			p6 = new JButton("Grafic Rank 6");
			p7 = new JButton("Grafic Rank 7");
			p8 = new JButton("Grafic Rank 8");
			p9 = new JButton("Grafic Rank 9");
			p10 = new JButton("Grafic Rank 10");
			Dimension tamB = new Dimension(140, 25);
			p1.setMaximumSize(tamB);
			p2.setMaximumSize(tamB);
			p3.setMaximumSize(tamB);
			p4.setMaximumSize(tamB);
			p5.setMaximumSize(tamB);
			p6.setMaximumSize(tamB);
			p7.setMaximumSize(tamB);
			p8.setMaximumSize(tamB);
			p9.setMaximumSize(tamB);
			p10.setMaximumSize(tamB);
			panelIzq.add(p1);
			panelIzq.add(p2);
			panelIzq.add(p3);
			panelIzq.add(p4);
			panelIzq.add(p5);
			panelIzq.add(p6);
			panelIzq.add(p7);
			panelIzq.add(p8);
			panelIzq.add(p9);
			panelIzq.add(p10);
			p1.setEnabled(true);
			p2.setEnabled(true);
			p3.setEnabled(true);
			p4.setEnabled(true);
			p5.setEnabled(true);
			p6.setEnabled(true);
			p7.setEnabled(true);
			p8.setEnabled(true);
			p9.setEnabled(true);
			p10.setEnabled(true);
			p1.addActionListener(this);
			p2.addActionListener(this);
			p3.addActionListener(this);
			p4.addActionListener(this);
			p5.addActionListener(this);
			p6.addActionListener(this);
			p7.addActionListener(this);
			p8.addActionListener(this);
			p9.addActionListener(this);
			p10.addActionListener(this);
			panelCen = new JPanel();
			panelCen.setBorder(new EmptyBorder(15, 5, 0, 0));
			panelCen.setLayout(new BoxLayout(panelCen,BoxLayout.Y_AXIS));
			Dimension tamT = new Dimension(200, 25);
			r10Text = new JTextField(15);
			r9Text = new JTextField(15);
			r8Text = new JTextField(15);
			r7Text = new JTextField(15);
			r6Text = new JTextField(15);
			r5Text = new JTextField(15);
			r4Text = new JTextField(15);
			r3Text = new JTextField(15);
			r2Text = new JTextField(15);
			r1Text = new JTextField(15);
			r1Text.setMaximumSize(tamT);
			r2Text.setMaximumSize(tamT);
			r3Text.setMaximumSize(tamT);
			r4Text.setMaximumSize(tamT);
			r5Text.setMaximumSize(tamT);
			r6Text.setMaximumSize(tamT);
			r7Text.setMaximumSize(tamT);
			r8Text.setMaximumSize(tamT);
			r9Text.setMaximumSize(tamT);
			r10Text.setMaximumSize(tamT);
			r1Text.addKeyListener(this);
			r2Text.addKeyListener(this);
			r3Text.addKeyListener(this);
			r4Text.addKeyListener(this);
			r5Text.addKeyListener(this);
			r6Text.addKeyListener(this);
			r7Text.addKeyListener(this);
			r8Text.addKeyListener(this);
			r9Text.addKeyListener(this);
			r10Text.addKeyListener(this);
			panelCen.add(r1Text);
			panelCen.add(r2Text);
			panelCen.add(r3Text);
			panelCen.add(r4Text);
			panelCen.add(r5Text);
			panelCen.add(r6Text);
			panelCen.add(r7Text);
			panelCen.add(r8Text);
			panelCen.add(r9Text);
			panelCen.add(r10Text);

			panelDer = new JPanel();
			panelDer.setBorder(new EmptyBorder(15, 5, 0, 0));
			panelDer.setLayout(new BoxLayout(panelDer,BoxLayout.Y_AXIS));
			Dimension tamE = new Dimension(80, 25);
			e10 = new JTextField(15);
			e9 = new JTextField(15);
			e8 = new JTextField(15);
			e7 = new JTextField(15);
			e6 = new JTextField(15);
			e5 = new JTextField(15);
			e4 = new JTextField(15);
			e3 = new JTextField(15);
			e2 = new JTextField(15);
			e1 = new JTextField(15);
			e1.setMaximumSize(tamE);
			e2.setMaximumSize(tamE);
			e3.setMaximumSize(tamE);
			e4.setMaximumSize(tamE);
			e5.setMaximumSize(tamE);
			e6.setMaximumSize(tamE);
			e7.setMaximumSize(tamE);
			e8.setMaximumSize(tamE);
			e9.setMaximumSize(tamE);
			e10.setMaximumSize(tamE);
			e1.setEditable(false);
			e2.setBackground(Color.WHITE);
			e2.setEditable(false);
			e3.setBackground(Color.WHITE);
			e3.setEditable(false);
			e4.setBackground(Color.WHITE);
			e4.setEditable(false);
			e5.setBackground(Color.WHITE);
			e5.setEditable(false);
			e6.setBackground(Color.WHITE);
			e6.setEditable(false);
			e7.setBackground(Color.WHITE);
			e7.setEditable(false);
			e8.setBackground(Color.WHITE);
			e8.setEditable(false);
			e9.setBackground(Color.WHITE);
			e9.setEditable(false);
			e10.setBackground(Color.WHITE);
			e10.setEditable(false);
			e1.setBackground(Color.WHITE);
			panelDer.add(e1);
			panelDer.add(e2);
			panelDer.add(e3);
			panelDer.add(e4);
			panelDer.add(e5);
			panelDer.add(e6);
			panelDer.add(e7);
			panelDer.add(e8);
			panelDer.add(e9);
			panelDer.add(e10);

			add(panelIzq, BorderLayout.WEST);
			add(panelCen, BorderLayout.CENTER);
			add(panelDer, BorderLayout.EAST);
			setBorder(new TitledBorder("Jugadores"));
			setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == p1) {
				con.darRango(0, r1Text.getText());
				g.setNRank(0);
				g.setValor();
				g.setVisible(true);
			}
			if (e.getSource() == p2) {
				con.darRango(1, r2Text.getText());
				g.setNRank(1);
				g.setVisible(true);
			}
			if (e.getSource() == p3) {
				con.darRango(2, r3Text.getText());
				g.setNRank(2);
				g.setVisible(true);
			}
			if (e.getSource() == p4) {
				con.darRango(3, r4Text.getText());
				g.setNRank(3);
				g.setVisible(true);
			}
			if (e.getSource() == p5) {
				con.darRango(4, r5Text.getText());
				g.setNRank(4);
				g.setVisible(true);
			}
			if (e.getSource() == p6) {
				con.darRango(5, r6Text.getText());
				g.setNRank(5);
				g.setVisible(true);
			}
			if (e.getSource() == p7) {
				con.darRango(6, r7Text.getText());
				g.setNRank(6);
				g.setVisible(true);
			}
			if (e.getSource() == p8) {
				con.darRango(7, r8Text.getText());
				g.setNRank(7);
				g.setVisible(true);
			}
			if (e.getSource() == p9) {
				con.darRango(8, r9Text.getText());
				g.setNRank(8);
				g.setVisible(true);
			}
			if (e.getSource() == p10) {
				con.darRango(9, r10Text.getText());
				g.setNRank(9);
				g.setVisible(true);
			}
			//			if (e.getSource() == evaluar) {
			//				g.setVisible(true);
			//			}
		}

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			//alMenosDos = sum1 + sum2 + sum3 + sum4 + sum5 + sum6 + sum7 + sum8 + sum9 + sum10; 
			ok = true;
			String text = "";
			if (e.getSource() == r1Text) {

				if(!r1Text.getText().isEmpty()){
					text = r1Text.getText();
					ok = con.entradaRango(text);
					if(ok){
						r1Text.setBackground(Color.green);
						con.asignarCartasBaraja(0, text);
						p1.setEnabled(true);
					}
					else{
						r1Text.setBackground(Color.red);
						p1.setEnabled(false);
					}
				}
				else{
					r1Text.setBackground(Color.white);
					con.asignarCartasBaraja(0, text);
					p1.setEnabled(true);
				}
			}
			if (e.getSource() == r2Text) {
				if(!r2Text.getText().isEmpty()){
					text = r2Text.getText();
					ok = con.entradaRango(text);
					if(ok){
						r2Text.setBackground(Color.green);
						con.asignarCartasBaraja(1, text);
						p2.setEnabled(true);
					}
					else{
						r2Text.setBackground(Color.red);
						p2.setEnabled(false);
					}
				}
				else{
					r2Text.setBackground(Color.white);
					con.asignarCartasBaraja(1, text);
					p2.setEnabled(true);
				}
			}
			if (e.getSource() == r3Text) {
				if(!r3Text.getText().isEmpty()){
					text = r3Text.getText();
					ok = con.entradaRango(text);
					if(ok){
						r3Text.setBackground(Color.green);
						con.asignarCartasBaraja(2, text);
						p3.setEnabled(true);
					}
					else{
						r3Text.setBackground(Color.red);
						p3.setEnabled(false);
					}
				}
				else{
					r3Text.setBackground(Color.white);
					con.asignarCartasBaraja(2, text);
					p3.setEnabled(true);
				}
			}
			if (e.getSource() == r4Text) {
				if(!r4Text.getText().isEmpty()){
					text = r4Text.getText();
					ok = con.entradaRango(text);
					if(ok){
						r4Text.setBackground(Color.green);
						con.asignarCartasBaraja(3, text);
						p4.setEnabled(true);
					}
					else{
						r4Text.setBackground(Color.red);
						p4.setEnabled(false);
					}

				}
				else{
					r4Text.setBackground(Color.white);
					con.asignarCartasBaraja(3, text);
					p4.setEnabled(true);
				}
			}
			if (e.getSource() == r5Text) {
				if(!r5Text.getText().isEmpty()){
					text = r5Text.getText();
					ok = con.entradaRango(text);
					if(ok){
						r5Text.setBackground(Color.green);
						con.asignarCartasBaraja(4, text);
						p5.setEnabled(true);
					}
					else{
						r5Text.setBackground(Color.red);
						p5.setEnabled(false);
					}
				}
				else{
					r5Text.setBackground(Color.white);
					con.asignarCartasBaraja(4, text);
					p5.setEnabled(true);
				}
			}
			if (e.getSource() == r6Text) {
				if(!r6Text.getText().isEmpty()){
					text = r6Text.getText();
					ok = con.entradaRango(text);
					if(ok){
						r6Text.setBackground(Color.green);
						con.asignarCartasBaraja(5, text);
						p6.setEnabled(true);
					}
					else{
						r6Text.setBackground(Color.red);
						p6.setEnabled(false);
					}
				}
				else{
					r6Text.setBackground(Color.white);
					con.asignarCartasBaraja(6, text);
					p6.setEnabled(true);
				}
			}
			if (e.getSource() == r7Text) {
				if(!r7Text.getText().isEmpty()){
					text = r7Text.getText();
					ok = con.entradaRango(text);
					if(ok){
						r7Text.setBackground(Color.green);
						con.asignarCartasBaraja(6, text);
						p7.setEnabled(true);
					}
					else{
						r7Text.setBackground(Color.red);
						p7.setEnabled(false);
					}
				}
				else{
					r7Text.setBackground(Color.white);
					con.asignarCartasBaraja(6, text);
					p7.setEnabled(true);
				}
			}
			if (e.getSource() == r8Text) {
				if(!r8Text.getText().isEmpty()){
					text = r8Text.getText();
					ok = con.entradaRango(text);
					if(ok){
						r8Text.setBackground(Color.green);
						con.asignarCartasBaraja(7, text);
						p8.setEnabled(true);
					}
					else{
						r8Text.setBackground(Color.red);
						p8.setEnabled(false);
					}
				}
				else{
					r8Text.setBackground(Color.white);
					con.asignarCartasBaraja(7, text);
					p8.setEnabled(true);
				}
			}
			if (e.getSource() == r9Text) {
				if(!r9Text.getText().isEmpty()){
					text = r9Text.getText();
					ok = con.entradaRango(text);
					if(ok){
						r9Text.setBackground(Color.green);
						con.asignarCartasBaraja(8, text);
						p9.setEnabled(true);
					}
					else{
						r9Text.setBackground(Color.red);
						p9.setEnabled(false);
					}
				}
				else{
					r9Text.setBackground(Color.white);
					con.asignarCartasBaraja(8, text);
					p9.setEnabled(true);
				}
			}
			if (e.getSource() == r10Text) {
				if(!r10Text.getText().isEmpty()){
					text = r10Text.getText();
					ok = con.entradaRango(text);
					if(ok){
						r10Text.setBackground(Color.green);
						con.asignarCartasBaraja(9, text);
						p10.setEnabled(true);
					}
					else{
						r10Text.setBackground(Color.red);
						p10.setEnabled(false);
					}
				}
				else{
					r10Text.setBackground(Color.white);
					con.asignarCartasBaraja(9, text);
					p10.setEnabled(true);
				}
			}
			//	alMenosDos = sum1 + sum2 + sum3 + sum4 + sum5 + sum6 + sum7 + sum8 + sum9 + sum10;
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			//alMenosDos = sum1 + sum2 + sum3 + sum4 + sum5 + sum6 + sum7 + sum8 + sum9 + sum10; 
			ok = true;
			String text = "";
			if (e.getSource() == r1Text) {

				if(!r1Text.getText().isEmpty()){
					text = r1Text.getText();
					ok = con.entradaRango(text);
					if(ok){
						r1Text.setBackground(Color.green);
						con.asignarCartasBaraja(0, text);
						p1.setEnabled(true);
					}
					else{
						r1Text.setBackground(Color.red);
						p1.setEnabled(false);
					}
				}
				else{
					r1Text.setBackground(Color.white);
					con.asignarCartasBaraja(0, text);
					p1.setEnabled(true);
				}
			}
			if (e.getSource() == r2Text) {
				if(!r2Text.getText().isEmpty()){
					text = r2Text.getText();
					ok = con.entradaRango(text);
					if(ok){
						r2Text.setBackground(Color.green);
						con.asignarCartasBaraja(1, text);
						p2.setEnabled(true);
					}
					else{
						r2Text.setBackground(Color.red);
						p2.setEnabled(false);
					}
				}
				else{
					r2Text.setBackground(Color.white);
					con.asignarCartasBaraja(1, text);
					p2.setEnabled(true);
				}
			}
			if (e.getSource() == r3Text) {
				if(!r3Text.getText().isEmpty()){
					text = r3Text.getText();
					ok = con.entradaRango(text);
					if(ok){
						r3Text.setBackground(Color.green);
						con.asignarCartasBaraja(2, text);
						p3.setEnabled(true);
					}
					else{
						r3Text.setBackground(Color.red);
						p3.setEnabled(false);
					}
				}
				else{
					r3Text.setBackground(Color.white);
					con.asignarCartasBaraja(2, text);
					p3.setEnabled(true);
				}
			}
			if (e.getSource() == r4Text) {
				if(!r4Text.getText().isEmpty()){
					text = r4Text.getText();
					ok = con.entradaRango(text);
					if(ok){
						r4Text.setBackground(Color.green);
						con.asignarCartasBaraja(3, text);
						p4.setEnabled(true);
					}
					else{
						r4Text.setBackground(Color.red);
						p4.setEnabled(false);
					}

				}
				else{
					r4Text.setBackground(Color.white);
					con.asignarCartasBaraja(3, text);
					p4.setEnabled(true);
				}
			}
			if (e.getSource() == r5Text) {
				if(!r5Text.getText().isEmpty()){
					text = r5Text.getText();
					ok = con.entradaRango(text);
					if(ok){
						r5Text.setBackground(Color.green);
						con.asignarCartasBaraja(4, text);
						p5.setEnabled(true);
					}
					else{
						r5Text.setBackground(Color.red);
						p5.setEnabled(false);
					}
				}
				else{
					r5Text.setBackground(Color.white);
					con.asignarCartasBaraja(4, text);
					p5.setEnabled(true);
				}
			}
			if (e.getSource() == r6Text) {
				if(!r6Text.getText().isEmpty()){
					text = r6Text.getText();
					ok = con.entradaRango(text);
					if(ok){
						r6Text.setBackground(Color.green);
						con.asignarCartasBaraja(5, text);
						p6.setEnabled(true);
					}
					else{
						r6Text.setBackground(Color.red);
						p6.setEnabled(false);
					}
				}
				else{
					r6Text.setBackground(Color.white);
					con.asignarCartasBaraja(6, text);
					p6.setEnabled(true);
				}
			}
			if (e.getSource() == r7Text) {
				if(!r7Text.getText().isEmpty()){
					text = r7Text.getText();
					ok = con.entradaRango(text);
					if(ok){
						r7Text.setBackground(Color.green);
						con.asignarCartasBaraja(6, text);
						p7.setEnabled(true);
					}
					else{
						r7Text.setBackground(Color.red);
						p7.setEnabled(false);
					}
				}
				else{
					r7Text.setBackground(Color.white);
					con.asignarCartasBaraja(6, text);
					p7.setEnabled(true);
				}
			}
			if (e.getSource() == r8Text) {
				if(!r8Text.getText().isEmpty()){
					text = r8Text.getText();
					ok = con.entradaRango(text);
					if(ok){
						r8Text.setBackground(Color.green);
						con.asignarCartasBaraja(7, text);
						p8.setEnabled(true);
					}
					else{
						r8Text.setBackground(Color.red);
						p8.setEnabled(false);
					}
				}
				else{
					r8Text.setBackground(Color.white);
					con.asignarCartasBaraja(7, text);
					p8.setEnabled(true);
				}
			}
			if (e.getSource() == r9Text) {
				if(!r9Text.getText().isEmpty()){
					text = r9Text.getText();
					ok = con.entradaRango(text);
					if(ok){
						r9Text.setBackground(Color.green);
						con.asignarCartasBaraja(8, text);
						p9.setEnabled(true);
					}
					else{
						r9Text.setBackground(Color.red);
						p9.setEnabled(false);
					}
				}
				else{
					r9Text.setBackground(Color.white);
					con.asignarCartasBaraja(8, text);
					p9.setEnabled(true);
				}
			}
			if (e.getSource() == r10Text) {
				if(!r10Text.getText().isEmpty()){
					text = r10Text.getText();
					ok = con.entradaRango(text);
					if(ok){
						r10Text.setBackground(Color.green);
						con.asignarCartasBaraja(9, text);
						p10.setEnabled(true);
					}
					else{
						r10Text.setBackground(Color.red);
						p10.setEnabled(false);
					}
				}
				else{
					r10Text.setBackground(Color.white);
					con.asignarCartasBaraja(9, text);
					p10.setEnabled(true);
				}
			}
			//	alMenosDos = sum1 + sum2 + sum3 + sum4 + sum5 + sum6 + sum7 + sum8 + sum9 + sum10;
		}
	}

	class panelSur extends JPanel implements ActionListener {

		private static final long serialVersionUID = 1L;
		private Controller con;

		public panelSur(Controller con){
			this.con = con;
			this.setLayout(new GridLayout());
			this.setSize(700, 500);
			resultado = new JTextArea("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			resultado.setEditable(false);
			resultado.setFocusable(false);
			add(resultado);
			setBorder(new TitledBorder("Resultado"));
			setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizarGrafica(int i, String text) {
		// TODO Auto-generated method stub
		g.actualizarGrafica(i, text);
	}

	@Override
	public void actualizarTexto(int nRank, String selec) {
		// TODO Auto-generated method stub
		switch(nRank){
		case 0: r1Text.setText(selec); break;
		case 1: r2Text.setText(selec); break;
		case 2: r3Text.setText(selec); break;
		case 3: r4Text.setText(selec); break;
		case 4: r5Text.setText(selec); break;
		case 5: r6Text.setText(selec); break;
		case 6: r7Text.setText(selec); break;
		case 7: r8Text.setText(selec); break;
		case 8: r9Text.setText(selec); break;
		case 9: r10Text.setText(selec); break;
		}
		switch(nRank){
		case 0: con.asignarCartasBaraja(0, selec); break;
		case 1: con.asignarCartasBaraja(1, selec); break;
		case 2: con.asignarCartasBaraja(2, selec); break;
		case 3: con.asignarCartasBaraja(3, selec); break;
		case 4: con.asignarCartasBaraja(4, selec); break;
		case 5: con.asignarCartasBaraja(5, selec); break;
		case 6: con.asignarCartasBaraja(6, selec); break;
		case 7: con.asignarCartasBaraja(7, selec); break;
		case 8: con.asignarCartasBaraja(8, selec); break;
		case 9: con.asignarCartasBaraja(9, selec); break;
		}
		ok = con.entradaRango(selec);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizarBoton(int i, int j) {
		// TODO Auto-generated method stub
		g.actualizarBoton(i, j);
	}

	@Override
	public void actualizarPorcentaje(double porcentaje) {
		// TODO Auto-generated method stub
		g.actualizarPorcentaje(porcentaje);
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
		g.actualizarMano(string);
	}

	@Override
	public void actualizarPorcentaje() {
		// TODO Auto-generated method stub
		g.actualizarPorcentaje();
	}

	@Override
	public void actualizarDatosDeManos(ArrayList<ManoHero> manos) {
		// TODO Auto-generated method stub

	}


	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizarBotonDeBoard(int i, int j, boolean b) {
		// TODO Auto-generated method stub
		panelCartas.actualizarBotonDeBoard(i, j, b);
	}


	@Override
	public void actualizarBotonDeDead(int i, int j, boolean b) {
		// TODO Auto-generated method stub
		panelCartas.actualizarBotonDeDead(i, j, b);
	}


	@Override
	public void actualizarDatosJug(int jug, ArrayList<ParCartas> cartasRango) {
		// TODO Auto-generated method stub
		switch(jug){
		case 0:{
			if(cartasRango.size() == 0){
				r1Text.setBackground(Color.red);
				cartasJugadores[jug].setCartas(cartasRango);
				gr1 = false;
				sum1 = 0;
			}
			else{
				r1Text.setBackground(Color.green);
				gr1 = true;
				sum1 = 1;
				cartasJugadores[jug].setCartas(cartasRango);
				
				
				
			}
			break;
		}
		case 1:{
			if(cartasRango.size() == 0){
				r2Text.setBackground(Color.red);
				cartasJugadores[jug].setCartas(cartasRango);
				gr2 = false;
				sum2 = 0;
			}
			else{
				r2Text.setBackground(Color.green);
				gr2 = true;
				sum2 = 1;
				cartasJugadores[jug].setCartas(cartasRango);


				
			}
			break;
		}
		case 2:{
			if(cartasRango.size() == 0){
				r3Text.setBackground(Color.red);
				cartasJugadores[jug].setCartas(cartasRango);
				gr3 = false;
				sum1 = 0;
			}
			else{
				r3Text.setBackground(Color.green);
				gr3 = true;
				sum3 = 1;
				cartasJugadores[jug].setCartas(cartasRango);
				
				
				
			}
			break;
		}
		case 3:{
			if(cartasRango.size() == 0){
				r4Text.setBackground(Color.red);
				cartasJugadores[jug].setCartas(cartasRango);
				gr4 = false;
				sum4 = 0;
			}
			else{
				r4Text.setBackground(Color.green);
				gr4 = true;
				sum4 = 1;
				cartasJugadores[jug].setCartas(cartasRango);
				
				
				}
			break;
		}
		case 4:{
			if(cartasRango.size() == 0){
				r5Text.setBackground(Color.red);
				cartasJugadores[jug].setCartas(cartasRango);
				gr5 = false;
				sum5 = 0;
			}
			else{
				r5Text.setBackground(Color.green);
				gr5 = true;
				sum5 = 1;
				cartasJugadores[jug].setCartas(cartasRango);
				
			}
			break;
		}
		case 5:{
			if(cartasRango.size() == 0){
				r6Text.setBackground(Color.red);
				cartasJugadores[jug].setCartas(cartasRango);
				gr6 = false;
				sum6 = 0;
			}
			else{
				r6Text.setBackground(Color.green);
				gr6 = true;
				sum6 = 1;
				cartasJugadores[jug].setCartas(cartasRango);
				
				
			}
			break;
		}
		case 6:{
			if(cartasRango.size() == 0){
				r7Text.setBackground(Color.red);
				cartasJugadores[jug].setCartas(cartasRango);
				gr7 = false;
				sum7 = 0;
			}
			else{
				r7Text.setBackground(Color.green);
				gr7 = true;
				sum7 = 1;
				cartasJugadores[jug].setCartas(cartasRango);
				
				
				
			}
			break;
		}
		case 7:{
			if(cartasRango.size() == 0){
				r8Text.setBackground(Color.red);
				cartasJugadores[jug].setCartas(cartasRango);
				gr8 = false;
				sum8 = 0;
			}
			else{
				r8Text.setBackground(Color.green);
				gr8 = true;
				sum8 = 1;
				cartasJugadores[jug].setCartas(cartasRango);
				
				
				
			}
			break;
		}
		case 8:{
			if(cartasRango.size() == 0){
				r9Text.setBackground(Color.red);
				cartasJugadores[jug].setCartas(cartasRango);
				gr9 = false;
				sum9 = 0;
			}
			else{
				r9Text.setBackground(Color.green);
				gr9 = true;
				sum9 = 1;
				cartasJugadores[jug].setCartas(cartasRango);
				
				
			}
			break;
		}
		case 9:{
			if(cartasRango.size() == 0){
				r10Text.setBackground(Color.red);
				cartasJugadores[jug].setCartas(cartasRango);
				gr10 = false;
				sum10 = 0;
			}
			else{
				r10Text.setBackground(Color.green);
				gr10 = true;
				sum10 = 1;
				cartasJugadores[jug].setCartas(cartasRango);
				
				
				
			}
			break;
		}
		}
	}


	@Override
	public void actualizaTextBoard(Carta[] car, String cartasParaTf) {
		// TODO Auto-generated method stub
		board.setText(cartasParaTf);
		con.darBoard(cartasParaTf, textAux);
		textAux = cartasParaTf;
		String mom = r1Text.getText();
		r1Text.setText("");
		r1Text.setText(mom);
		con.asignarCartasBaraja(0, mom);
		mom = r2Text.getText();
		r2Text.setText("");
		r2Text.setText(mom);
		con.asignarCartasBaraja(1, mom);
		mom = r3Text.getText();
		r3Text.setText("");
		r3Text.setText(mom);
		con.asignarCartasBaraja(2, mom);
		mom = r4Text.getText();
		r4Text.setText("");
		r4Text.setText(mom);
		con.asignarCartasBaraja(3, mom);
		mom = r5Text.getText();
		r5Text.setText("");
		r5Text.setText(mom);
		con.asignarCartasBaraja(4, mom);
		mom = r6Text.getText();
		r6Text.setText("");
		r6Text.setText(mom);
		con.asignarCartasBaraja(5, mom);
		mom = r7Text.getText();
		r7Text.setText("");
		r7Text.setText(mom);
		con.asignarCartasBaraja(6, mom);
		mom = r8Text.getText();
		r8Text.setText("");
		r8Text.setText(mom);
		con.asignarCartasBaraja(7, mom);
		mom = r9Text.getText();
		r9Text.setText("");
		r9Text.setText(mom);
		con.asignarCartasBaraja(8, mom);
		mom = r10Text.getText();
		r10Text.setText("");
		r10Text.setText(mom);
		con.asignarCartasBaraja(9, mom);
		arrayCartasBoard = car;

	}


	@Override
	public void actualizaTextDead(Carta[] car, String cartasParaTf) {
		// TODO Auto-generated method stub
		dead.setText(cartasParaTf);
		con.darBoard(cartasParaTf, textAux2);
		textAux2 = cartasParaTf;
		String mom = r1Text.getText();
		r1Text.setText("");
		r1Text.setText(mom);
		con.asignarCartasBaraja(0, mom);
		mom = r2Text.getText();
		r2Text.setText("");
		r2Text.setText(mom);
		con.asignarCartasBaraja(1, mom);
		mom = r3Text.getText();
		r3Text.setText("");
		r3Text.setText(mom);
		con.asignarCartasBaraja(2, mom);
		mom = r4Text.getText();
		r4Text.setText("");
		r4Text.setText(mom);
		con.asignarCartasBaraja(3, mom);
		mom = r5Text.getText();
		r5Text.setText("");
		r5Text.setText(mom);
		con.asignarCartasBaraja(4, mom);
		mom = r6Text.getText();
		r6Text.setText("");
		r6Text.setText(mom);
		con.asignarCartasBaraja(5, mom);
		mom = r7Text.getText();
		r7Text.setText("");
		r7Text.setText(mom);
		con.asignarCartasBaraja(6, mom);
		mom = r8Text.getText();
		r8Text.setText("");
		r8Text.setText(mom);
		con.asignarCartasBaraja(7, mom);
		mom = r9Text.getText();
		r9Text.setText("");
		r9Text.setText(mom);
		con.asignarCartasBaraja(8, mom);
		mom = r10Text.getText();
		r10Text.setText("");
		r10Text.setText(mom);
		con.asignarCartasBaraja(9, mom);
		arrayCartasDead = car;
	}


	@Override
	public void actualizaArraytBoard(Carta[] car) {
		// TODO Auto-generated method stub
		arrayCartasBoard = car;
	}


	@Override
	public void actualizaArraytDead(Carta[] car) {
		// TODO Auto-generated method stub
		arrayCartasDead = car;
	}


	@Override
	public void comprobarConocerLibres(int libresCar1, int libresCar2) {
		// TODO Auto-generated method stub
		this.libresCar1 = libresCar1;
		this.libresCar2 = libresCar2;
	}


	@Override
	public void cambiarPaloLibre(char palo) {
		// TODO Auto-generated method stub
		paloLibre = palo;
	}


	@Override
	public void comprobarRepetida(boolean c1, boolean c2) {
		// TODO Auto-generated method stub
		this.c1 = c1;
		this.c2 = c2;
	}


	@Override
	public void damePalo(char p) {
		// TODO Auto-generated method stub
		this.peso = p;
	}
}
