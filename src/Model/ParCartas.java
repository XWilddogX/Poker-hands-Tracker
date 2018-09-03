package Model;

public class ParCartas {

	private Carta car1;
	private Carta car2;
	private int combos;
	
	public ParCartas(Carta c1, Carta c2, int c){
		car1 = c1;
		car2 = c2;
		combos = c;
	}

	@Override
	public String toString() {
		return car1.toString() + car2.toString() +"";
	}

	public Carta getCar1() {
		return car1;
	}

	public void setCar1(Carta car1) {
		this.car1 = car1;
	}

	public Carta getCar2() {
		return car2;
	}

	public void setCar2(Carta car2) {
		this.car2 = car2;
	}

	public int getCombos() {
		return combos;
	}

	public void setCombos(int combos) {
		this.combos = combos;
	}
}
