package Main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Controller.Controller;
import Model.CalculadorRango;
import Model.Jugador;
import Model.LecturaRanking;
import Model.ManoHero;
import Model.Ranking;
import Swing.View;

public class Principal {

	//private JTabbedPane panel;

	private Controller con;
	private String rang;
	private String ranking;
	
	public Principal(String archivoRango, String archivoRanking){
		
		
		rang = "src/rangos/" + archivoRango;
		ranking = "src/ranking/" + archivoRanking;

		try{
			String[] rankingStr = new String[Main.TOTAL_MANOS_INICIALES];	
			LecturaRanking lecturaRanking = new LecturaRanking(ranking);
			lecturaRanking.cargarRanking();
			
			rankingStr = lecturaRanking.getRankingStr();
			Ranking ranking = new Ranking(rankingStr);
			
			CalculadorRango rango = new CalculadorRango(ranking, 0.0);
			rango.calcularRango();
			
			Jugador[] jug = new Jugador[10];
			for (int i=0;i<10;i++)
				jug[i]=new Jugador();
			
		
			con = new Controller(jug, rango, null, new ManoHero());
			View v = new View(con);	
			
			for (int i=0;i<10;i++)
				jug[i].add(v);
			rango.addObs(v);
					
			} 
		
		catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
