package Model;
import java.util.Arrays;

public class Mano implements Comparable<Mano>{

	private Carta[] mano;
	private Carta[] manoAux;
	private boolean drawFlush;
	private boolean gutshot;
	private boolean open_ended;
	private int fuerzaMano;
	private Jugada mejorMano;
	private String lineaAux;
	private String jugador;
	private String cadMano;
	private boolean primera;
	/**
	 * 
	 */
	public Mano(Carta[] cartas) {
		// TODO Auto-generated constructor stub
		mano = cartas;
		primera = false;
		drawFlush = false;
		gutshot = false;
		open_ended = false;
		fuerzaMano = 0;
		lineaAux = "";
		mejorMano = Jugada.CARTA_ALTA;
		jugador = "";
		Arrays.sort(mano);
		manoAux = mano;
	}

	public void compruebaJugada() {
		// TODO Auto-generated method stub
		if(!primera)
			cadMano = setCadMano();
		this.esFlush();
		this.esStraight();
		if(this.fuerzaMano == 0)
			this.resto();
	}

	//color
	private void esFlush() {
		// TODO Auto-generated method stub
		int h = 0, c = 0, s = 0, d = 0;
		for(int i = 0; i < 5; i++){
			if(mano[i].getValorPalo() == 1){
				h++;
			}
			if(mano[i].getValorPalo() == 2){
				c++;
			}
			if(mano[i].getValorPalo() == 3){
				s++;
			}
			if(mano[i].getValorPalo() == 4){
				d++;
			}
		}
		if((h == 4) || (c == 4) || (s == 4) || (d == 4)) drawFlush = true;
		else if((h == 5) || (c == 5) || (s == 5) || (d == 5)){
			mejorMano = Jugada.COLOR;
			this.setFuerzaMano();
			this.cartas(mano[0]);
			this.cartas(mano[1]);
			this.cartas(mano[2]);
			this.cartas(mano[3]);
			this.cartas(mano[4]);
		}
	}

	//escalera
	private void esStraight() {
		// TODO Auto-generated method stub
		int saltos = 0; 
		boolean color = false, escalera = false;
		if(mejorMano.equals(Jugada.COLOR)) color = true;
		if((mano[0].getValorNum() == 2) && (mano[1].getValorNum() == 3) && (mano[2].getValorNum() == 4)&& (mano[3].getValorNum() == 5) && (mano[4].getValorNum() == 14)){
			escalera = true;
			if(color) lineaAux = "";
			this.cartas(mano[4]);
			this.cartas(mano[0]);
			this.cartas(mano[1]);
			this.cartas(mano[2]);
			this.cartas(mano[3]);
		}
		else{
			int j = 0;
			int k = 0;
			for (int i = 0; i < mano.length - 1; i++) {
				if (mano[i].getValorNum() == mano[i+1].getValorNum() - 1) {
					j++;
					k++;
				}
				else if (mano[i].getValorNum() == mano[i+1].getValorNum() - 2) {
					k++;
				}
			}
			if (j == 4) {
				escalera = true;
				if(color) lineaAux = "";
				this.cartas(mano[4]);
				this.cartas(mano[3]);
				this.cartas(mano[2]);
				this.cartas(mano[1]);
				this.cartas(mano[0]);
			} else if (j == 3) {
				open_ended = true;
			} else if (k == 3) {
				gutshot = true;
			} else if (k == 4) {
				open_ended = true;
			}
		}
		if(escalera){
			if(color) mejorMano = Jugada.ESCALERA_COLOR;
			else mejorMano = Jugada.ESCALERA;
			this.setFuerzaMano();
		}
		else{
			if(!open_ended){
				if(saltos == 2) gutshot = true;
				else if (saltos == 1) open_ended = true;
			}
		}
	}

	private void resto(){
		int igual1 = 1, igual2 = 1, diferentes = 0;
		for(int i = mano.length-1; i > 0; i--){
			if(diferentes <= 3){
				if(mano[i].getValorNum() != mano[i-1].getValorNum()){
					diferentes++;
					if((diferentes == 1) && (i < 1)){
						if(mano[i+1].getValorNum() == mano[i].getValorNum()){
							this.cartas(mano[i]);
						}
					}
				}
				else if((diferentes > 0) && (igual1 > 1)){
					if(mano[i+1].getValorNum() == mano[i-1].getValorNum()){
						igual1++;
						this.cartas(mano[i]);
						if(i >= 3) 	this.cartas(mano[i-1]);
					}
					else if(mano[i].getValorNum() == mano[i-1].getValorNum()){
						igual2++;
						this.cartas(mano[i]);
						if(i <= 3) 	this.cartas(mano[i-1]);
					}
				}
				else{
					if(mano[i].getValorNum() == mano[i-1].getValorNum()){
						igual1++;
						this.cartas(mano[i]);
						if(i > 3) this.cartas(mano[i-1]);
					}
				}
			}
		}
		if(igual1 == 4) mejorMano = Jugada.POKER;
		else if(((igual1 == 3) && (igual2 == 2) || (igual1 == 2) && (igual2 == 3))) mejorMano = Jugada.FULL;
		else if((igual1 == 3) && (igual2 < 2)) mejorMano = Jugada.TRIO;
		else if((igual1 == 2) && (igual2 == 2)) mejorMano = Jugada.DOBLE_PAREJA;
		else if((igual1 == 2) && (igual2 < 2)) mejorMano = Jugada.PAREJA;
		else{ 
			mejorMano = Jugada.CARTA_ALTA;
			this.cartas(mano[0]);
		}
		if(this.fuerzaMano == 0)
			this.setFuerzaMano();
	}

	private void cartas(Carta car){
		lineaAux += car.toString();
	}

	public boolean isDrawFlush() {
		return drawFlush;
	}

	public boolean isGutshot() {
		return gutshot;
	}

	public boolean isOpen_ended() {
		return open_ended;
	}

	private void setFuerzaMano(){
		switch (mejorMano.toString()){
		case "CARTA_ALTA": fuerzaMano = 1; break;
		case "PAREJA": fuerzaMano = 2; break;
		case "DOBLE_PAREJA": fuerzaMano = 3; break;
		case "TRIO": fuerzaMano = 4; break;
		case "ESCALERA": fuerzaMano = 5; break;
		case "COLOR": fuerzaMano = 6; break;
		case "FULL": fuerzaMano = 7; break;
		case "POKER": fuerzaMano = 8; break;
		case "ESCALERA_COLOR": fuerzaMano = 9; break;
		}	
	}

	private String setCadMano() {
		// TODO Auto-generated method stub
		String cad = "";
		for(int i = mano.length-1; i >= 0; i--)
			cad += mano[i].toString();
		return cad;
	}

	public String getCadMano(){
		return this.cadMano;
	}

	public int getMejorMano(){
		int mejorMano = 0;
		switch(lineaAux.charAt(0)){
		case 'A': mejorMano = 14; break;
		case 'K': mejorMano = 13; break;
		case 'Q': mejorMano = 12; break;
		case 'J': mejorMano = 11; break;
		case 'T': mejorMano = 10; break;
		case '9': mejorMano= 9; break;
		case '8': mejorMano = 8; break;
		case '7': mejorMano = 7; break;
		case '6': mejorMano = 6; break;
		case '5': mejorMano = 5; break;
		case '4': mejorMano = 4; break;
		case '3': mejorMano = 3; break;
		case '2': mejorMano = 2; break;
		}
		return mejorMano;
	}
	

	public int getFuerzaMano(){
		return fuerzaMano;
	}

	public String getStringJugada() {
		return lineaAux;
	}

	public String getJugador() {
		return jugador;
	}

	public void setJugador(String jugador) {
		this.jugador = jugador;
	}

	@Override
	public int compareTo(Mano mano) {
		// TODO Auto-generated method stub
		if(this.fuerzaMano > mano.fuerzaMano)
			return -1;
		if(this.fuerzaMano < mano.fuerzaMano)
			return 1;
		else{
			for(int i = manoAux.length-1; i >= 0; i--){
				if(this.manoAux[i].valor() > mano.manoAux[i].valor())
					return -1;
				if(this.manoAux[i].valor() < mano.manoAux[i].valor())
					return 1;
			}
		}
		return 0;
	}

	public void esLaPrimera(boolean b) {
		// TODO Auto-generated method stub
		primera = b;
	}


}
