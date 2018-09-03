package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import Controller.Controller;

public class Resolucion {

	private final int MILLONES = 2000;
	private Controller con;
	private BarajaJugador[] cartasJugadores;
	private ArrayList<Carta> board;
	private Carta[] dead;
	private int numTotal;

	private ArrayList<ParCartas> parCartasJugadores;
	private Mano[] mejoresManosDeCadaJug;
	public static double[] equityTotal;
	public static String estoEsParaLaVista;
	int NUM_JUG;
	private ArrayList<Carta> boardConRandom;
	private int tamanoBoardIni;
	long tEjec;

	public Resolucion(Controller con, BarajaJugador[] cartasJugadores,
			Carta[] arrayCartasBoard, Carta[] arrayCartasDead) {
		parCartasJugadores = new ArrayList<ParCartas>();
		int n = 0;
		for (int s = 0; s < cartasJugadores.length; s++) {
			if(cartasJugadores[s].getCartas().size() > 0){
				n++;
			}
		}
		NUM_JUG = n;
		mejoresManosDeCadaJug = new Mano[NUM_JUG];
		equityTotal = new double[NUM_JUG];
		// TODO Auto-generated constructor stub
		this.cartasJugadores = cartasJugadores;
		this.con = con;
		board = new ArrayList<Carta>();
		if(arrayCartasBoard[0] != null){
		for(int i = 0; i < arrayCartasBoard.length; i++) board.add(arrayCartasBoard[i]);
		}
		if(arrayCartasDead != null){
			dead = new Carta[arrayCartasDead.length];
			for(int i = 0; i < arrayCartasDead.length; i++) dead[i] = arrayCartasDead[i];
		}
		else
			dead = new Carta[0];

		boardConRandom = new ArrayList<Carta>();
		tamanoBoardIni = board.size();
		numTotal = 0;
		estoEsParaLaVista = "";
		long tIni = 0, tFin = 0;
		tEjec = 0;
		tIni = System.currentTimeMillis();
		this.obtenerEquity();
		tFin = System.currentTimeMillis();
		tEjec = tFin - tIni;
		
		estoEsParaLaVista += MILLONES + " juegos en " + tEjec/1000f + "segundos - " + MILLONES/(tEjec/1000f) + " manos/segundo\n" +
		"Board: ";
		for (int i = 0; i < board.size(); i++) {
			estoEsParaLaVista += board.get(i).toString();
		}
		estoEsParaLaVista += "\nDead: ";
		for (int i = 0; i < dead.length; i++) {
			estoEsParaLaVista += dead[i].toString();
		}
		estoEsParaLaVista += "\n\n	botes ganados		equity\n";
		for (int i = 0; i < equityTotal.length; i++) {
		estoEsParaLaVista += "Jugador "+ (i+1) + ":	" + equityTotal[i] + "  		  ";
			equityTotal[i] = (double)equityTotal[i]/(double)MILLONES * 100.0;
			estoEsParaLaVista += equityTotal[i] + "%\n";
		}
					
		int tam = equityTotal.length;
		switch(tam){
		case 2: estoEsParaLaVista += "\n\n\n\n\n\n\n\n\n"; break;
		case 3: estoEsParaLaVista += "\n\n\n\n\n\n\n\n"; break;
		case 4: estoEsParaLaVista += "\n\n\n\n\n\n\n"; break;
		case 5: estoEsParaLaVista += "\n\n\n\n\n\n"; break;
		case 6: estoEsParaLaVista += "\n\n\n\n\n"; break;
		case 7: estoEsParaLaVista += "\n\n\n\n"; break;
		case 8: estoEsParaLaVista += "\n\n\n"; break;
		case 9: estoEsParaLaVista += "\n\n"; break;
		case 10: estoEsParaLaVista += "\n"; break;
		}
		
		
	}

	private void calcular() {
		
		// coger un par de cartas aleatorios de todos los jugadores
		for(int i = 0; i < NUM_JUG; i++){
			Random aleatorio = new Random();
			int valor = aleatorio.nextInt(cartasJugadores[i].getCartas().size());
			parCartasJugadores.add(cartasJugadores[i].getCartas().get(valor));
		}

		// generar board aleatorio
		this.cartasBoardRandom(0);

		// obtener la mejor mano de cada jugador
		Carta[][] matriz = new Carta[NUM_JUG][7];
		for (int j = 0; j < NUM_JUG; j++) {
			for (int k = 0; k < 5; k++) {
				matriz[j][k] = boardConRandom.get(k);
			}
			matriz[j][5] = parCartasJugadores.get(j).getCar1();
			matriz[j][6] = parCartasJugadores.get(j).getCar2();
		}

		for (int j = 0; j < NUM_JUG; j++) {
			ArrayList<Carta> mano = new ArrayList<Carta>();
			for (int k = 0; k < 7; k++) {
				mano.add(matriz[j][k]);
			}
			Mano[] manos = OperacionesComunes.combinaciones(mano);
			for (int k = 0; k < manos.length; k++) {
				manos[k].compruebaJugada();
			}
			Mano manoVencedora = OperacionesComunes.victoriosa(manos);
			mejoresManosDeCadaJug[j] = manoVencedora;
			mejoresManosDeCadaJug[j].setJugador(""+j);
		}

		// ordenar las mejores manos por fuerza
		Arrays.sort(mejoresManosDeCadaJug);
	}

	private void cartasBoardRandom(int i) {
		//int cartasRandom = 5 - board.size();
		Baraja aux = con.getBaraja(i);
		
		boardConRandom.clear();
		boardConRandom = new ArrayList<Carta>();

		int aRellenar = 5 - tamanoBoardIni;
		for(int s = 0; s < aRellenar; s++){
			int filas = (int) (Math.random()*13);
			int columnas = (int) (Math.random()*4);
			while(aux.getBaraja()[filas][columnas]){
				filas = (int) (Math.random()*13);
				columnas = (int) (Math.random()*4);
			}
			char valor = con.valor(filas);
			char palo = con.palo(columnas);
			Carta c = new Carta(valor, palo);
			boardConRandom.add(c);
		}
		for (int j = 0; j < tamanoBoardIni; j++) {
			boardConRandom.add(board.get(j));
		}
	}

	private void obtenerEquity() {

		for (int i = 0; i < MILLONES; i++) {

			calcular();
			
			if (mejoresManosDeCadaJug[0].getJugador().equals("0"))
				equityTotal[0]++;
			else if (mejoresManosDeCadaJug[0].getJugador().equals("1"))
				equityTotal[1]++;
			else if (mejoresManosDeCadaJug[0].getJugador().equals("2"))
				equityTotal[2]++;
			else if (mejoresManosDeCadaJug[0].getJugador().equals("3"))
				equityTotal[3]++;
			else if (mejoresManosDeCadaJug[0].getJugador().equals("4"))
				equityTotal[4]++;
			else if (mejoresManosDeCadaJug[0].getJugador().equals("5"))
				equityTotal[5]++;
			else if (mejoresManosDeCadaJug[0].getJugador().equals("6"))
				equityTotal[6]++;
			else if (mejoresManosDeCadaJug[0].getJugador().equals("7"))
				equityTotal[7]++;
			else if (mejoresManosDeCadaJug[0].getJugador().equals("8"))
				equityTotal[8]++;
			else if (mejoresManosDeCadaJug[0].getJugador().equals("9"))
				equityTotal[9]++;			
		}
	}



}
