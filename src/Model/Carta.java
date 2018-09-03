package Model;


public class Carta implements Comparable<Carta>{
	
	char numCarta;
	char paloCarta;
	int valorNum;
	int valorPalo;
	/**
	 * @param numCarta
	 * @param paloCarta
	 */
	public Carta(char numCarta, char paloCarta) {
		super();
		this.numCarta = numCarta;
		this.paloCarta = paloCarta;
		this.valorNum = this.valor();
		this.valorPalo = this.palo();
	}
	public Carta() {
		// TODO Auto-generated constructor stub
	}
	public char getNumCarta() {
		return numCarta;
	}
	public void setNumCarta(char numCarta) {
		this.numCarta = numCarta;
	}
	public char getPaloCarta() {
		return paloCarta;
	}
	public void setPaloCarta(char paloCarta) {
		this.paloCarta = paloCarta;
	}
	
	public int getValorNum() {
		return valorNum;
	}
	
	public int getValorPalo() {
		return valorPalo;
	}

	public int valor(){
		int valor = 0;
		if(numCarta == 'A') valor = 14;
		else if(numCarta == 'K') valor = 13;
		else if(numCarta == 'Q') valor = 12;
		else if(numCarta == 'J') valor = 11;
		else if(numCarta == 'T') valor = 10;
		else if(numCarta == '9') valor = 9;
		else if(numCarta == '8') valor = 8;
		else if(numCarta == '7') valor = 7;
		else if(numCarta == '6') valor = 6;
		else if(numCarta == '5') valor = 5;
		else if(numCarta == '4') valor = 4;
		else if(numCarta == '3') valor = 3;
		else if(numCarta == '2') valor = 2;
		else if(numCarta == '1') valor = 1;
	return valor;
	}
	
	public int palo(){
		int palo = 0;
		if(paloCarta == 's') palo = 1;
		else if (paloCarta == 'h') palo = 2;
		else if(paloCarta == 'd') palo = 3;
		else if (paloCarta == 'c') palo = 4;
	return palo;
	}
	
	@Override
	public String toString() {
		return Character.toString(numCarta) + Character.toString(paloCarta) + "";
	}
	
	@Override
	public int compareTo(Carta o) {
		// TODO Auto-generated method stub
		if(this.valorNum < o.valorNum)
			return -1;
		if(this.valorNum > o.valorNum)
			return 1;
		return 0;
	}
}
