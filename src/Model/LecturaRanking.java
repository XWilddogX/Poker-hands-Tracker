package Model;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Observer.Observer;
import Main.Main;

public class LecturaRanking extends Model {
	
	String rankingFile;
	String[] rankingStr;
	private ArrayList<Observer> obs;
	
	public LecturaRanking(String rankingFile) {
		this.rankingFile = rankingFile;
		this.rankingStr = new String[Main.TOTAL_MANOS_INICIALES];
		this.obs = super.obs;
	}
	
	public void cargarRanking() throws IOException {
		FileReader fr = new FileReader(rankingFile);
		BufferedReader br = new BufferedReader(fr);
		String linea;
		int i = 0;
		while((linea=br.readLine()) != null) {
			rankingStr[i] = linea;
			i++;
		}
		br.close();
		fr.close();
	}
	
	public String[] getRankingStr() {
		for(Observer o : obs)
			o.actualizarRankingStr(rankingStr);
		return rankingStr;
	}
	

}
