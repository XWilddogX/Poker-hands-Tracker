package Model;

import java.text.DecimalFormat;
import java.util.ArrayList;

import Main.Main;
import Observer.Observer;

public class CalculadorRango extends Model {
	
	private double porcentaje;
	private String manoInicial;
	private int posicionEnRanking;
	private Ranking ranking;
	//ArrayList<Observer> obs;//////////
	
	final int COMBOS_PAREJAS = 6;
	final int COMBOS_OFFSUIT = 12;
	final int COMBOS_SUIT = 4;
	final int COMBOS_TOTALES = 1326;
	
	public CalculadorRango(Ranking ranking, Double porcentaje) {
		this.ranking = ranking;
		this.porcentaje = porcentaje;
		this.obs = new ArrayList<Observer>();//super.obs; //////////
	}
	
	public CalculadorRango(Ranking ranking, String manoInicial) {
		this.ranking = ranking;
		this.manoInicial = manoInicial;
	}
	
	public CalculadorRango() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Calcula un rango de cartas dado un ranking y un porcentaje
	 * Por ejemplo:
	 * 		rankingEV.txt 10
	 * 		Entonces el 5% del rango según el rankingEV sería
	 * 		AA,KK,QQ,JJ,AKs,AQs,TT,AKo
	 */
	public void calcularRango() {
		double porcentajeActual = 0;
		double combos = 0;
		for (int i = 0; i < Main.TOTAL_MANOS_INICIALES; i++) {
			combos += contarCombos(ranking.getRankingDeManosIniciales()[i]);
			porcentajeActual = (combos / COMBOS_TOTALES) * 100;
			if (porcentajeActual <= porcentaje) {
				for(Observer o : obs)
					o.actualizarMano(ranking.getRankingDeManosIniciales()[i]);
			}
			
		}
	}
	
	public void calcularRangoPol(int p, int s, int t, int c) {
		double porcentajeActual = 0;
		double porcentajeMinimo = p/1.00;
		double combos = 0;
		porcentaje = s/1.00;
		for (int i = 0; i < Main.TOTAL_MANOS_INICIALES; i++) {
			combos += contarCombos(ranking.getRankingDeManosIniciales()[i]);
			porcentajeActual = (combos / COMBOS_TOTALES) * 100;
			if((porcentajeMinimo <= porcentajeActual) && (porcentajeActual <= porcentaje)) {
				for(Observer o : obs)
					o.actualizarMano(ranking.getRankingDeManosIniciales()[i]);
			}
			
		}
		combos = 0;
		porcentajeMinimo = t/1.00;
		porcentaje = c/1.00;
		for (int i = 0; i < Main.TOTAL_MANOS_INICIALES; i++) {
			combos += contarCombos(ranking.getRankingDeManosIniciales()[i]);
			porcentajeActual = (combos / COMBOS_TOTALES) * 100;
			if((porcentajeMinimo <= porcentajeActual) && (porcentajeActual <= porcentaje)) {
				for(Observer o : obs)
					o.actualizarMano(ranking.getRankingDeManosIniciales()[i]);
			}
			
		}
	}
	
	public void calcularPorcentaje() {
		int totalCombos = 0;
		// buscar la mano inicial en el ranking e ir contando los combos
		for (int i = 0; i < Main.TOTAL_MANOS_INICIALES - 1; i++) {
			String manoActual = ranking.getRankingDeManosIniciales()[i];
			totalCombos = totalCombos + contarCombos(manoActual);
			if (manoActual.equals(manoInicial)) {
				posicionEnRanking = i;
				break;
			}
		}
		porcentaje = ((double)totalCombos / (double)COMBOS_TOTALES) * 100;
		for(Observer o : obs)
			o.actualizarPorcentaje(porcentaje);
	}
	
	private int contarCombos(String mano) {
		int combos = 0;
		if (mano.length() == 2) {
			combos = COMBOS_PAREJAS;
		} else if (mano.charAt(2) == 's') {
			combos = COMBOS_SUIT;
		} else
			combos = COMBOS_OFFSUIT;
		
		return combos;
	}
	
	public int getPosicionEnRanking() {
		return posicionEnRanking;
	}
	
	public double getPorcentaje() {
		return porcentaje;
	}
	
	public String porcentajeToString() {
		String salida = "";
		int intervaloRango = 100;
		if (posicionEnRanking != Main.TOTAL_MANOS_INICIALES-1) {
			intervaloRango = (((int)porcentaje / 10) + 1) * 10;
		}		
		salida += "Mano inicial   : " + manoInicial + "\n";
		salida += "Pos. en ranking: " + (posicionEnRanking+1) + "\n";
		salida += "Rango          : " + intervaloRango + "%" + "\n";
		DecimalFormat df = new DecimalFormat("0.00"); 
		salida += "Exacto         : " + df.format(porcentaje) + "%" + "\n";
		return salida;
	}
	
	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}
}
