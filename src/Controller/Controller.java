package Controller;

import java.io.IOException;
import java.util.ArrayList;

import Model.Baraja;
import Model.CalculadorRango;
import Model.Carta;
import Model.Jugador;
import Model.ManoHero;
import Model.ParseJugador;
import Model.PokerStarsParser;


public class Controller {

	private Jugador[] jug;
	private CalculadorRango calc;
	private PokerStarsParser psParser;
	private ManoHero mano;

	public Controller(Jugador[] mod, CalculadorRango calc, PokerStarsParser psParser, ManoHero mano) {
		//this.mod = new Jugador[10];
		this.jug = mod;
		this.calc = calc;
		this.psParser = psParser;
		this.mano = mano;
	}

	public boolean entradaRango(String text) {
		if((text.length() == 4) && (text.charAt(1)=='s' || text.charAt(1)=='h' || text.charAt(1)=='d' || text.charAt(1)=='c') &&
				(text.charAt(3)=='s' || text.charAt(3)=='h' || text.charAt(3)=='d' || text.charAt(3)=='c') &&
				(text.charAt(0)=='A' || text.charAt(0)=='2' || text.charAt(0)=='3' || text.charAt(0)=='4' || text.charAt(0)=='5' || text.charAt(0)=='6' || text.charAt(0)=='7' || text.charAt(0)=='8' || text.charAt(0)=='9' || text.charAt(0)=='T' || text.charAt(0)=='J' || text.charAt(0)=='Q' || text.charAt(0)=='K')
				&& (text.charAt(2)=='A' || text.charAt(2)=='2' || text.charAt(2)=='3' || text.charAt(2)=='4' || text.charAt(2)=='5' || text.charAt(2)=='6' || text.charAt(2)=='7' || text.charAt(2)=='8' || text.charAt(2)=='9' || text.charAt(2)=='T' || text.charAt(2)=='J' || text.charAt(2)=='Q' || text.charAt(2)=='K')){
			return true;
		}
		String []f=text.split(",");
		try{
			for(int j=0;j<f.length;j++){
				if(ParseJugador.ParseRango(f[j])==null)
					return false;
			}
		}catch(Exception e){
			return false;
		}
		return true;
	}



	public void darRango(int i, String text) {
		if(!text.isEmpty()){
			if((text.length() == 4) && (text.charAt(1)=='s' || text.charAt(1)=='h' || text.charAt(1)=='d' || text.charAt(1)=='c')){
				
			}
			else{
				jug[i].setMano(text.split(","));
				jug[i].leerMano();
			}
		}
	}

	public void marcar (int i,int j,int k){
		jug[k].marcarMano(i, j);
	}
	public void selecATexto(int nRank) {
		jug[nRank].leerTabla(nRank);
	}

	public void darPorcentaje(double parseDouble) {
		calc.setPorcentaje(parseDouble);
		calc.calcularRango();
	}

	public boolean porcentajeCorrecto(String text) {
		// TODO Auto-generated method stub
		if((text == null) || (text.isEmpty())) return false; 
		int i = 0;
		if(text.charAt(0) == '-'){
			if(text.length() > 1){
				i++;
			} else return false;
		}
		for(; i < text.length(); i++){
			if(!Character.isDigit(text.charAt(i))){
				if(!text.substring(i, i+1).equalsIgnoreCase("."))
					return false;
			}
		}
		return true;
	}

	public void darRango(String nombreRango, int numMano) {
		psParser.setNombreRango(nombreRango);
	}

	public void darRutaFichero(String pokerStarsFile) {
		psParser.setPokerStarsFile(pokerStarsFile);
		try {
			psParser.parsear();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void darDatosMano(String cartas, String posicion, String rango, String accion) throws IOException {
		mano = new ManoHero(cartas, ManoHero.cadenaAPosicion(posicion), ManoHero.cadenaAAccion(accion));
		mano.evaluarAccion(rango);
	}

	public boolean esCorrecto() {
		return mano.isEsCorrecto();
	}

	public boolean entraBoard(String text) {
		if((text.length() % 2) == 0){
			ArrayList<String>f = new ArrayList<String>();
			for(int i = 0; i < text.length(); i+=2){
				f.add(text.substring(i, i+2));
			}
			Carta[] car = new Carta[f.size()];
			try{
				for(int j=0;j<f.size();j++){
					car[j] = ParseJugador.ParseBoardDead(f.get(j));
				}
				for(int j=0;j<f.size()-1;j++){
					if((car[j]==null) || car[j].toString().equals(car[j+1].toString()))
						return false;
				}
				for(int i = 0; i < 10; i++)
					jug[i].actializaArrayBoard(car);
			}catch(Exception e){
				return false;
			}
		}else return false;
		return true;
	}

	public boolean entraDead(String text) {
		if((text.length() % 2) == 0){
			ArrayList<String>f = new ArrayList<String>();
			for(int i = 0; i < text.length(); i+=2){
				f.add(text.substring(i, i+2));
			}
			Carta[] car = new Carta[f.size()];
			try{
				for(int j=0;j<f.size();j++){
					car[j] = ParseJugador.ParseBoardDead(f.get(j));
				}
				for(int j=0;j<f.size()-1;j++){
					if((car[j]==null) || car[j].toString().equals(car[j+1].toString()))
						return false;
				}
				for(int i = 0; i < 10; i++)
					jug[i].actializaArrayDead(car);
			}catch(Exception e){
				return false;
			}
		}else return false;
		return true;
	}

	public void darBoard(String text, String textAux) {
		// TODO Auto-generated method stub
		ArrayList<String> antiguo = new ArrayList<String>();
		ArrayList<String>nuevo = new ArrayList<String>();
		for(int i = 0; i < textAux.length(); i+=2){
			antiguo.add(textAux.substring(i, i+2));
		}
		for(int i = 0; i < text.length(); i+=2){
			nuevo.add(text.substring(i, i+2));
		}
		Carta[] carN = new Carta[nuevo.size()];
		Carta[] carA = new Carta[antiguo.size()];
		for(int j=0;j<antiguo.size();j++){
			carA[j] = ParseJugador.ParseBoardDead(antiguo.get(j));
		}
		for(int j=0;j<nuevo.size();j++){
			carN[j] = ParseJugador.ParseBoardDead(nuevo.get(j));
		}
		for(int i=0; i < 10; i++){
			jug[i].quitaCartasB(carA);
			jug[i].ponCartasB(carN);
		}
	}

	public void darDead(String text, String textAux) {
		// TODO Auto-generated method stub
		ArrayList<String> antiguo = new ArrayList<String>();
		ArrayList<String>nuevo = new ArrayList<String>();
		for(int i = 0; i < textAux.length(); i+=2){
			antiguo.add(textAux.substring(i, i+2));
		}
		for(int i = 0; i < text.length(); i+=2){
			nuevo.add(text.substring(i, i+2));
		}
		Carta[] carN = new Carta[nuevo.size()];
		Carta[] carA = new Carta[antiguo.size()];
		for(int j=0;j<antiguo.size();j++){
			carA[j] = ParseJugador.ParseBoardDead(antiguo.get(j));
		}
		for(int j=0;j<nuevo.size();j++){
			carN[j] = ParseJugador.ParseBoardDead(nuevo.get(j));
		}
		for(int i=0; i < 10; i++){
			jug[i].quitaCartasD(carA);
			jug[i].ponCartasD(carN);
		}
	}

	public boolean repetida(String text, String text2) {
		// TODO Auto-generated method stub
		ArrayList<String> aT = new ArrayList<String>();
		ArrayList<String> aT2 = new ArrayList<String>();
		for(int i = 0; i < text2.length(); i+=2){
			aT2.add(text2.substring(i, i+2));
		}
		for(int i = 0; i < text.length(); i+=2){
			aT.add(text.substring(i, i+2));
		}
		for(int i = 0; i < aT.size(); i++){
			for(int j = 0; j < aT2.size(); j++){
				if(aT.get(i).equals(aT2.get(j))){
					return false;
				}
			}
		}
		return true;
	}

	public void botonesBoard(ArrayList<String> cartas) {
		// TODO Auto-generated method stub
		Carta[] car = new Carta[cartas.size()];
		for(int j=0;j<cartas.size();j++){
			car[j] = ParseJugador.ParseBoardDead(cartas.get(j));
		}

		for(int i=0; i < 10; i++){
			jug[i].ponCartasBB(car);
		}
	}

	public void botonesDead(ArrayList<String> cartas) {
		// TODO Auto-generated method stub
		Carta[] car = new Carta[cartas.size()];
		for(int j=0;j<cartas.size();j++){
			car[j] = ParseJugador.ParseBoardDead(cartas.get(j));
		}

		for(int i=0; i < 10; i++){
			jug[i].ponCartasBD(car);
		}
	}


	public void quitarCartas(String text) {
		// TODO Auto-generated method stub
		Carta car = new Carta(text.charAt(0), text.charAt(1));
		for(int i=0; i < 10; i++){
			jug[i].quitarCartas(car);
		}
	}

	public boolean entradaPorcentaje(String text) {
		// TODO Auto-generated method stub
		String[] cad = text.split(",");
		String[] rang1 = cad[0].split("-");
		String[] rang2 = cad[1].split("-");
		int primero = Integer.parseInt(rang1[0]);
		int segundo = Integer.parseInt(rang1[1]);
		int tercero = Integer.parseInt(rang2[0]);
		int cuarto = Integer.parseInt(rang2[1]);
		if((primero <= segundo) && (segundo <= tercero) && (tercero <= cuarto)){
			int primerRango = segundo - primero;
			int segundoRango = cuarto - tercero;
			if((primerRango + segundoRango) <= 100){
				calc.calcularRangoPol(primero, segundo, tercero, cuarto);
				return true;
			}
		}
		return false;
	}

	public void asignarCartasBaraja(int i, String text) {
		// TODO Auto-generated method stub
		jug[i].cartasJugadorParejas(i, text);
	}

	public void actualizaBoard(String cartasParaTf) {
		// TODO Auto-generated method stub
		ArrayList<String>cartas = new ArrayList<String>();
		for(int i = 0; i < cartasParaTf.length(); i+=2){
			cartas.add(cartasParaTf.substring(i, i+2));
		}
		Carta[] car = new Carta[cartas.size()];
		for(int j=0;j<cartas.size();j++){
			car[j] = ParseJugador.ParseBoardDead(cartas.get(j));
		}
		for(int i=0; i < 10; i++){
			jug[i].ponCartasBoard(car, cartasParaTf);
		}
	}

	public void actualizaDead(String cartasParaTf) {
		// TODO Auto-generated method stub
		ArrayList<String>cartas = new ArrayList<String>();
		for(int i = 0; i < cartasParaTf.length(); i+=2){
			cartas.add(cartasParaTf.substring(i, i+2));
		}
		Carta[] car = new Carta[cartas.size()];
		for(int j=0;j<cartas.size();j++){
			car[j] = ParseJugador.ParseBoardDead(cartas.get(j));
		}
		for(int i=0; i < 10; i++){
			jug[i].ponCartasDead(car, cartasParaTf);
		}
	}

	public void comprobarPonerATrue(int jug2, Carta car1, Carta car2) {
		// TODO Auto-generated method stub
		jug[jug2].comprobarPonerATrue(car1, car2);
	}

	public void comprobarLibres(int jug2, Carta car1, Carta car2) {
		// TODO Auto-generated method stub
		jug[jug2].comprobarLibres(car1, car2);
	}

	public void comprObtPaloLibre(int jug2, char numCarta) {
		// TODO Auto-generated method stub
		jug[jug2].comprObtPaloLibre(jug2, numCarta);
	}

	public void comprobarPonerAFalse(int jug2, Carta car1, Carta car2) {
		// TODO Auto-generated method stub
		jug[jug2].comprobarPonerAFalse(car1, car2);
	}

	public void comprobarPonerATrueV2(int jug2, Carta car1o, Carta car2o) {
		// TODO Auto-generated method stub
		jug[jug2].comprobarPonerATrueV2(car1o, car2o);
	}

	public Baraja getBaraja(int jug2) {
		// TODO Auto-generated method stub
		return jug[jug2].getBaraja();
	}

	public Baraja ponerBarajaAuxTrue(Baraja barajaAux, Carta car) {
		// TODO Auto-generated method stub
		for (int i =0;i<13;i++){
			for (int j=0;j<4;j++){
				if(car.toString().equals(barajaAux.getCartas()[i][j].toString())){
					barajaAux.setBaraja(i, j, true);
				}
			}
		}
		return barajaAux;
	}
	
	public Baraja obtenerCarEnTrue(Baraja barajaAux, Carta car) {
		// TODO Auto-generated method stub
		for (int i =0;i<13;i++){
			for (int j=0;j<4;j++){
				if(car.toString().equals(barajaAux.getCartas()[i][j].toString())){
					barajaAux.setBaraja(i, j, true);
				}
			}
		}
		return barajaAux;
	}

	public int LibresAux(Baraja barajaAux, Carta car) {
		// TODO Auto-generated method stub
		int l = 0;
		for (int i =0;i<13;i++){
			if(car.getNumCarta() == barajaAux.getCartas()[i][0].getNumCarta()){
				for (int j=0;j<4;j++){
					if(!barajaAux.getBaraja()[i][j]){
						l++;
					}
				}
			}
		}
		return l;
	}

	public char damePaloLibreDeAux(Baraja barajaAux, char numCarta) {
		// TODO Auto-generated method stub
		int v = jug[0].valor(numCarta);
		int paloLibre = 0;
		for (int i =0;i<13;i++){
			for (int j=0;j<4;j++){
				if(v == i){
					if(!barajaAux.getBaraja()[i][j]){
						paloLibre = j;
					}
			}
			}
		}
		return jug[0].palo(paloLibre);
	}

	public Baraja ponerBarajaAuxFalse(Baraja barajaAux, Carta car) {
		// TODO Auto-generated method stub
		for (int i =0;i<13;i++){
			for (int j=0;j<4;j++){
				if(car.toString().equals(barajaAux.getCartas()[i][j].toString())){
					barajaAux.setBaraja(i, j, false);
				}
			}
		}
		return barajaAux;
	}

	public boolean verSiCoinciden(Baraja barajaAux, Carta car1a, Carta car2a) {
		// TODO Auto-generated method stub
		ArrayList<Integer> libre1 = new ArrayList<Integer>();
		ArrayList<Integer> libre2 = new ArrayList<Integer>();
		for (int i =0;i<13;i++){
			for (int j=0;j<4;j++){
				if(!car1a.toString().equals(barajaAux.getCartas()[i][j].toString())){
					if(!barajaAux.getBaraja()[i][j]){
						libre1.add(j);
					}
				}
			}
		}
		
		for (int i =0;i<13;i++){
			for (int j=0;j<4;j++){
				if(!car2a.toString().equals(barajaAux.getCartas()[i][j].toString())){
					if(!barajaAux.getBaraja()[i][j]){
						libre2.add(j);
					}
				}
			}
		}
		boolean ok = false;
		for(int i = 0; i < libre1.size() && !ok; i++){
			for(int j = 0; j < libre2.size() && !ok; j++){
				if(libre1.get(i) == libre2.get(j)){
					ok = true;
					char p = jug[0].palo(libre1.get(i));
					jug[0].damePalo(p);
				}
			}
		}
		return ok;
	}

	public boolean verSiNoCoinciden(Baraja barajaAux, Carta car1a, Carta car2a) {
		// TODO Auto-generated method stub
		ArrayList<Integer> libre1 = new ArrayList<Integer>();
		ArrayList<Integer> libre2 = new ArrayList<Integer>();
		for (int i =0;i<13;i++){
			for (int j=0;j<4;j++){
				if(!car1a.toString().equals(barajaAux.getCartas()[i][j].toString())){
					if(!barajaAux.getBaraja()[i][j]){
						libre1.add(j);
					}
				}
			}
		}
		
		for (int i =0;i<13;i++){
			for (int j=0;j<4;j++){
					if(!barajaAux.getBaraja()[i][j]){
						libre2.add(j);
					}
			}
		}
		boolean ok = false;
		for(int i = 0; i < libre1.size() && !ok; i++){
			for(int j = 0; j < libre2.size() && !ok; j++){
				if(libre1.get(i) != libre2.get(j)){
					ok = true;
					char p = jug[0].palo(libre1.get(i));
					jug[0].damePalo(p);
				}
			}
		}
		return ok;
	}

	public char palo(int p) {
		// TODO Auto-generated method stub
		return jug[0].palo(p);
	}

	public char valor(int filas) {
		// TODO Auto-generated method stub
		return jug[0].peso(filas);
	}

}
