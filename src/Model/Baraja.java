package Model;

import java.awt.Color;

import javax.swing.JButton;

public class Baraja {

	private boolean[][] baraja;
	private Carta[][] cartas;
	
	public Baraja(){
		cartas = new Carta[13][4];
		char[] valores = {
				'A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2'
		};
		char[] palos = {
				's', 'h', 'd', 'c'
		};
		for(int i = 0; i < 13; i++){
			for(int j = 0; j < 4; j++){
				cartas[i][j] = new Carta(valores[i],palos[j]);
			}
		}
		baraja = new boolean[13][4];
		for (int i =0;i<13;i++){
			for (int j=0;j<4;j++){
				this.baraja[i][j]=false;
			}
		}
	}

	public boolean[][] getBaraja() {
		return baraja;
	}

	public void setBaraja(int i, int j, boolean ok) {
		this.baraja[i][j] = ok;
	}

	public Carta[][] getCartas() {
		return cartas;
	}

	public void setCartas(Carta[][] cartas) {
		this.cartas = cartas;
	}
}
