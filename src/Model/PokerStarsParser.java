package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Observer.Observer;

public class PokerStarsParser extends Model {
	
	public enum Accion {OR, FOLD, CHECK, CALL, BET}
	public enum Posicion {UTG, HJ, CO, BTN, SB, BB}

	private ArrayList<ManoHero> manos;
	private String pokerStarsFile;
	ArrayList<Observer> obs;
	private String nombreRango;
	
	public PokerStarsParser(String porkerStarsFile) {
		this.manos = new ArrayList<ManoHero>();
		this.pokerStarsFile = porkerStarsFile; ////////////////////////////////////////
		this.obs = super.obs;
	}
	
	public void parsear() throws IOException {
		
		FileReader fr = new FileReader(pokerStarsFile);
		BufferedReader br = new BufferedReader(fr);
		String linea;
		int i = -1;
		boolean primeraAccionOK = false;
		boolean primeraBB = false;
		ManoHero mano = null;
		
		while((linea=br.readLine()) != null) {
			
			// número de mano
			if (linea.contains("PokerStars Hand #")) {
				i++;
				mano = new ManoHero("");
				manos.add(mano);
				primeraAccionOK = false;
				primeraBB = false;
			}
			// nombre de la BB
			if (linea.contains("posts big blind")) {
				if (!primeraBB) {
					manos.get(i).setNombreBB(parsearNombreBB(linea));
					primeraBB = true;
				}
			}
			// guardando jugadores
			if (linea.contains("in chips")) {
				mano.aniadirJugador(linea.split(" ")[2]);
				manos.get(i).setJugadores(mano.getJugadores());
			}
			// cartas del hero
			if (linea.contains("Dealt to")) {
				manos.get(i).setCartasHero(parsearCartasHero(linea));
			}
			// acción del hero
			if (linea.contains(ManoHero.HERO + ": raises") || linea.contains(ManoHero.HERO + ": folds") ||
					linea.contains(ManoHero.HERO + ": check") || linea.contains(ManoHero.HERO + ": calls") ||
					linea.contains(ManoHero.HERO + ": bets")) {
				if (!primeraAccionOK) {
					manos.get(i).setAccionHero(parsearAccion(linea, i));
					primeraAccionOK = true;
				}	
			}
		}
		for (int k = 0; k < manos.size(); k++) {
			manos.get(k).setPosicionHero(calcularPosicion(manos.get(k).getNombreBB(), k));
			manos.get(k).evaluarAccion(nombreRango);
		}
		br.close();
		fr.close();
		for(Observer o : obs)
			o.actualizarDatosDeManos(manos);
	}

	private Posicion calcularPosicion(String nombreBB, int index) {
		Posicion posicion = null;
		boolean encontrado = false;
		ArrayList<String> jugadores = manos.get(index).getJugadores();
		int indexBB = 0;
		int contPos = 0;
		// buscar indice de la BB
		for (int i = 0; i < jugadores.size(); i++) {
			if (jugadores.get(i).equals(nombreBB)) {
				indexBB = i;
				break;
			}
		}
		// buscar el HERO e ir contando posiciones hasta encontrarlo
		while (!encontrado) {
			if (jugadores.get(indexBB).equals(ManoHero.HERO)) {
				encontrado = true;
			} else {
				indexBB--;
				contPos++;
			}
			if (indexBB == -1) {
				indexBB = jugadores.size()-1;
			}
		}
		// posición del HERO según lo lejos que esté de la BB
		switch (contPos) {
			case 0: posicion = Posicion.BB; break;
			case 1: posicion = Posicion.SB; break;
			case 2: posicion = Posicion.BTN; break;
			case 3: posicion = Posicion.CO; break;
			case 4: posicion = Posicion.HJ; break;
			case 5: posicion = Posicion.UTG; break;
		}
		return posicion;
	}

	private Accion parsearAccion(String linea, int i) {
		if (linea.contains("folds"))
			return Accion.FOLD;
		else if (linea.contains("raises"))
			return Accion.OR;
		else if (linea.contains("checks"))
			return Accion.CHECK;
		else if (linea.contains("calls"))
			return Accion.CALL;
		else if (linea.contains("bets"))
			return Accion.BET;
		else
			return null;
	}

	private String parsearNombreBB(String linea) {
		return linea.split(":")[0]; 
	}

	public String parsearCartasHero(String linea) {
		String mano = linea.split(ManoHero.HERO)[1];
		mano = "" + mano.charAt(2) + mano.charAt(3) + mano.charAt(5) + mano.charAt(6);
		////// convertir por ejempolo Qh4s en Q4o y devolver
		String carta1 = mano.charAt(0) + "";
		String carta2 = mano.charAt(2) + "";
		String palo1 = mano.charAt(1) + "";
		String palo2 = mano.charAt(3) + "";
		int c1 = letraANumero(carta1);
		int c2 = letraANumero(carta2);
		String cartaAux;
		
		// pone primero la carta alta y luego a baja, si no no la encuentra
		// en el ranking y falla
		if (c1 < c2) {
			cartaAux = carta1;
			carta1 = carta2;
			carta2 = cartaAux;
		}
		if (carta1.equals(carta2))
			mano = carta1 + carta2; // pareja
		else if (palo1.equals(palo2))
			mano = carta1 + carta2 + "s"; // suit
		else
			mano = carta1 + carta2 + "o"; // offsuit
	
		return mano;
	}
	
	public static String convertirMano(String mano4) {
		if (mano4.length() == 4) {
			String carta1 = mano4.charAt(0) + "";
			String carta2 = mano4.charAt(2) + "";
			String palo1 = mano4.charAt(1) + "";
			String palo2 = mano4.charAt(3) + "";
			int c1 = letraANumero(carta1);
			int c2 = letraANumero(carta2);
			String cartaAux;
			if (c1 < c2) {
				cartaAux = carta1;
				carta1 = carta2;
				carta2 = cartaAux;
			}
			if (carta1.equals(carta2))
				mano4 = carta1 + carta2; // pareja
			else if (palo1.equals(palo2))
				mano4 = carta1 + carta2 + "s"; // suit
			else
				mano4 = carta1 + carta2 + "o"; // offsuit
			return mano4;
		} else {
			String carta1 = mano4.charAt(0) + "";
			String carta2 = mano4.charAt(1) + "";
			String palo1 = mano4.charAt(2) + "";
			int c1 = letraANumero(carta1);
			int c2 = letraANumero(carta2);
			String cartaAux;
			if (c1 < c2) {
				cartaAux = carta1;
				carta1 = carta2;
				carta2 = cartaAux;
			}
			if (carta1.equals(carta2))
				mano4 = carta1 + carta2; // pareja
			else if (palo1.equals("s"))
				mano4 = carta1 + carta2 + "s"; // suit
			else
				mano4 = carta1 + carta2 + "o"; // offsuit
			return mano4;
		}
		
	}
	
	private static int letraANumero(String letra) {
		int valor;
		switch (letra) {
			case "2": valor = 2; break;
			case "3": valor = 3; break;
			case "4": valor = 4; break;
			case "5": valor = 5; break;
			case "6": valor = 6; break;
			case "7": valor = 7; break;
			case "8": valor = 8; break;
			case "9": valor = 9; break;
			case "T": valor = 10; break;
			case "J": valor = 11; break;
			case "Q": valor = 12; break;
			case "K": valor = 13; break;
			case "A": valor = 14; break;
			default : valor = 0;
		}
		return valor;
	}

	
	public ArrayList<ManoHero> getManos() {
		return manos;
	}
	
	public void setPokerStarsFile(String pokerStarsFile) {
		this.pokerStarsFile = pokerStarsFile;
	}

	public void setNombreRango(String nombreRango) {
		this.nombreRango = nombreRango;
	}

	
}
