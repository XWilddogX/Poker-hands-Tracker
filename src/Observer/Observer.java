package Observer;

import java.util.ArrayList;

import Model.Carta;
import Model.ManoHero;
import Model.ParCartas;

public interface Observer {

	public void init();

	public void actualizarPorcentaje(double porcentaje);

	public void actualizarPosicionEnRanking(int posicionEnRanking);

	public void actualizarRankingStr(String[] rankingStr);

	public void actualizarRanking(String[] rankingDeManosIniciales);
	
	public void actualizarGrafica(int i, String text);

	public void actualizarTexto(int nRank, String string);

	public void actualizarBoton(int i, int j);

	public void actualizarMano(String string);

	public void actualizarPorcentaje();

	public void actualizarDatosDeManos(ArrayList<ManoHero> manos);

	public void actualizarBotonDeBoard(int i, int j, boolean b);

	public void actualizarBotonDeDead(int i, int j, boolean b);

	public void actualizarDatosJug(int jug, ArrayList<ParCartas> cartasRango);

	public void actualizaTextBoard(Carta[] car, String cartasParaTf);

	public void actualizaTextDead(Carta[] car, String cartasParaTf);

	public void actualizaArraytBoard(Carta[] car);

	public void actualizaArraytDead(Carta[] car);

	public void comprobarConocerLibres(int libresCar1, int libresCar2);

	public void cambiarPaloLibre(char palo);

	public void comprobarRepetida(boolean c1, boolean c2);

	public void damePalo(char p);

}
