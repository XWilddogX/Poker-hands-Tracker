package Model;

import java.util.ArrayList;

public class BarajaJugador {

	private ArrayList<ParCartas> cartas;
	private int victorias;
	private int empates;
	
	public BarajaJugador(){
		cartas = new ArrayList<ParCartas>();
		victorias = 0;
		empates = 0;
	}

	public int getVictorias() {
		return victorias;
	}

	public void setVictorias() {
		this.victorias++;
	}

	public int getEmpates() {
		return empates;
	}

	public void setEmpates() {
		this.empates++;
	}

	public ArrayList<ParCartas> getCartas() {
		return cartas;
	}

	public void setCartas(ArrayList<ParCartas> cartas) {
		this.cartas = cartas;
	}
}
