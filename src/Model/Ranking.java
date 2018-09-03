package Model;

import java.util.ArrayList;
import Observer.Observer;

public class Ranking extends Model {

	private String[] rankingDeManosIniciales;
	private ArrayList<Observer> obs;

	
	public Ranking(String[] rankingDeManosIniciales) {
		this.rankingDeManosIniciales = rankingDeManosIniciales;
		this.obs = super.obs;
	}

	public String[] getRankingDeManosIniciales() {
		for(Observer o : obs)
			o.actualizarRanking(rankingDeManosIniciales);
		return rankingDeManosIniciales;
	}
	
}
