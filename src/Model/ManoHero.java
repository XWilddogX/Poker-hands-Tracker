package Model;

import java.io.IOException;
import java.util.ArrayList;

import Model.PokerStarsParser.Accion;
import Model.PokerStarsParser.Posicion;

public class ManoHero {
	
	final static String HERO = "MN-UCM";
	
	private Posicion posicionHero;
	private String cartasHero;
	private Accion accionHero;
	private Ranking ranking;
	private String nombreHero;
	private ArrayList<String> jugadores;
	private String nombreBB;
	private boolean esCorrecto;
	
	public ManoHero() {	}
	
	public ManoHero(String nombreRango) {
		this.nombreHero = HERO;	
		jugadores = new ArrayList<String>();
		esCorrecto = false;
	}
	
	public ManoHero(String cartas, Posicion posicion, Accion accion) {
		this.cartasHero = cartas;
		this.posicionHero = posicion;
		this.accionHero = accion;
	}
	
	/**
	 * 
	 * @param rango
	 * 		por ejemplo rango = Janda o rango = Grupo1
	 * @throws IOException 
	 */
	public void evaluarAccion(String nombreRango) throws IOException {
		// obtener el porcentaje para la mano dada
		double porcentajeMano;
		String[] rankingStr = new String[169];
		LecturaRanking lecturaRanking = new LecturaRanking("ranking.txt");
		try {
			lecturaRanking.cargarRanking();
		} catch (IOException e) {
			e.printStackTrace();
		}
		rankingStr = lecturaRanking.getRankingStr(); ////
		
		Ranking ranking = new Ranking(rankingStr);
		CalculadorRango calc = new CalculadorRango(ranking, cartasHero);
		calc.calcularPorcentaje();
		porcentajeMano = calc.getPorcentaje();
		
		// ver si el porcentaje de la mano es menor o igual al porcentaje del rango
		// de apertura para la posicion dada
		RangosOR rangos = new RangosOR();
		rangos.setNombreRangosOR(nombreRango); 
		rangos.asignarRangosOR();
		switch (posicionHero) {
			case UTG: {
				if (accionHero == Accion.OR)
					esCorrecto = (porcentajeMano <= rangos.getOrUTG()); 
				else if (accionHero == Accion.FOLD)
					esCorrecto = (porcentajeMano > rangos.getOrUTG());
				break;
			}
			case HJ: {
				if (accionHero == Accion.OR)
					esCorrecto = (porcentajeMano <= rangos.getOrHJ()); 
				else if (accionHero == Accion.FOLD)
					esCorrecto = (porcentajeMano > rangos.getOrHJ());
				break;
			}
			case CO: {
				if (accionHero == Accion.OR)
					esCorrecto = (porcentajeMano <= rangos.getOrCO()); 
				else if (accionHero == Accion.FOLD)
					esCorrecto = (porcentajeMano > rangos.getOrCO());
				break;
			}
			case BTN: {
				if (accionHero == Accion.OR)
					esCorrecto = (porcentajeMano <= rangos.getOrBTN()); 
				else if (accionHero == Accion.FOLD)
					esCorrecto = (porcentajeMano > rangos.getOrBTN());
				break;
			}
			case SB: {
				if (accionHero == Accion.OR)
					esCorrecto = (porcentajeMano <= rangos.getOrSB()); 
				else if (accionHero == Accion.FOLD)
					esCorrecto = (porcentajeMano > rangos.getOrSB());
				break;
			
			}
			default : esCorrecto = true;
		}
	}
	
	public static Posicion cadenaAPosicion(String cadena) {
		Posicion pos = null;
		switch (cadena) {
			case "BTN": pos = Posicion.BTN; break;
			case "SB": pos = Posicion.SB; break;
			case "BB": pos = Posicion.BB; break;
			case "UTG": pos = Posicion.UTG; break;
			case "HJ": pos = Posicion.HJ; break;
			case "CO": pos = Posicion.CO; break;
		}
		return pos;
	}
	
	public static Accion cadenaAAccion(String cadena) {
		Accion accion = null;
		switch (cadena) {
			case "Fold": accion = Accion.FOLD; break;
			case "Open Raise": accion = Accion.OR; break;
		}
		return accion;
	}
	
	public Posicion getPosicionHero() {
		return posicionHero;
	}

	public void setPosicionHero(Posicion posicionHero) {
		this.posicionHero = posicionHero;
	}

	public String getCartasHero() {
		return cartasHero;
	}

	public void setCartasHero(String cartasHero) {
		this.cartasHero = cartasHero;
	}

	public Accion getAccionHero() {
		return accionHero;
	}

	public void setAccionHero(Accion accionHero) {
		this.accionHero = accionHero;
	}

	public Ranking getRanking() {
		return ranking;
	}

	public void setRanking(Ranking ranking) {
		this.ranking = ranking;
	}

	public String getNombreHero() {
		return nombreHero;
	}

	public void setNombreHero(String nombreHero) {
		this.nombreHero = nombreHero;
	}

	public ArrayList<String> getJugadores() {
		return jugadores;
	}
	
	public void setJugadores(ArrayList<String> jugadores) {
		this.jugadores = jugadores;
	}

	public void aniadirJugador(String jugador) {
		jugadores.add(jugador);
	}
	
	public void setNombreBB(String nombreBB) {
		this.nombreBB = nombreBB;
	}
	
	public String getNombreBB() {
		return nombreBB;
	}

	public boolean isEsCorrecto() {
		return esCorrecto;
	}

}
