package Model;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import Observer.Observer;


public class Jugador {
	private String[] mano;
	private boolean [][] tabla;
	private Baraja baraja;
	private String[] board;
	private String[] dead;
	private ArrayList<Observer> obs;
	public Jugador(){
		this.mano=null;
		this.tabla=new boolean[13][13];
		this.restartTable();
		this.baraja = new Baraja();
		obs = new ArrayList<Observer>();
	}
	public void restartTable(){
		for (int i =0;i<13;i++){
			for (int j=0;j<13;j++){
				this.tabla[i][j]=false;
			}
		}
	}

	public void marcarMano(int i,int j){
		this.tabla[i][j]=true;
	}

	public void setMano(String []k ){
		this.mano=k;
	}

	public void setBoard(String []k ){
		this.board=k;
	}

	public void setDead(String []k ){
		this.dead=k;
	}
	//lee de tablero a texto
	public void leerTabla(int i){
		StringBuilder k=new StringBuilder();
		k.append(leerPareja());
		k.append(leerSuited());
		k.append(leerOffSuited());
		this.restartTable();
		this.mano=k.toString().split(",");
		for(Observer o : obs)
			o.actualizarTexto(i, k.toString());
	}


	private String leerSuited(){
		StringBuilder build = new StringBuilder();	
		int ini=0;
		for (int i=11;i>=0;i--){
			for(int j=12;j>i;j--){
				if(this.tabla[i][j]==true && ini==0){
					ini=j;
				}
				else if(this.tabla[i][j]==true && this.tabla[i][j-1]==false && j-1!=i && ini!=0){
					build.append(this.traducirValor(i)+this.traducirValor(j)+"s"+"-"+this.traducirValor(i)+this.traducirValor(ini)+"s,");
					ini=0;
				}
				else if(this.tabla[i][j]==false && ini!=0){
					build.append(this.traducirValor(i)+this.traducirValor(ini)+"s,");
					ini=0;
				}
			}
			if(this.tabla[i][i+1]==true){
				if(ini!=0 && ini-1!=i){
					build.append(this.traducirValor(i)+this.traducirValor(ini)+"s"+'+'+",");
					ini=0;
				}
				else{
					build.append(this.traducirValor(i)+this.traducirValor(i+1)+"s,");
					ini=0;
				}
			}
			else if(ini!=0){
				build.append(this.traducirValor(ini)+this.traducirValor(ini+1)+"s,");
				ini=0;
			}
		}
		return build.toString();	
	}
	private String leerOffSuited(){

		StringBuilder build = new StringBuilder();	
		int ini=0;
		for (int i=11;i>=0;i--){
			for(int j=12;j>i;j--){
				if(this.tabla[j][i]==true && ini==0){
					ini=j;
				}
				else if(this.tabla[j][i]==true && this.tabla[j-1][i]==false && j-1!=i && ini!=0){
					build.append(this.traducirValor(i)+this.traducirValor(j)+"o"+"-"+this.traducirValor(i)+this.traducirValor(ini)+"o,");
					ini=0;
				}
				else if(this.tabla[j][i]==false && ini!=0){
					build.append(this.traducirValor(i)+this.traducirValor(ini)+"o,");
					ini=0;
				}
			}
			if(this.tabla[i+1][i]==true){
				if(ini!=0 && ini-1!=i){
					build.append(this.traducirValor(i)+this.traducirValor(ini)+"o"+'+'+",");
					ini=0;
				}
				else{
					build.append(this.traducirValor(i)+this.traducirValor(i+1)+"o,");
					ini=0;
				}
			}
			else if(ini!=0){
				build.append(this.traducirValor(ini)+this.traducirValor(ini+1)+"o,");
				ini=0;
			}
		}
		return build.toString();	
	}
	private String leerPareja(){
		StringBuilder build = new StringBuilder();
		int ini=0;
		for (int i=12;i>0;i--){
			if(this.tabla[i][i]==true && ini==0){
				ini=i;
			}
			else if(this.tabla[i][i]==true && this.tabla[i-1][i-1]==false&& ini!=0){
				build.append(this.traducirValor(i)+this.traducirValor(i)+"-"+this.traducirValor(ini)+this.traducirValor(ini)+",");
				ini=0;
			}
			else if(this.tabla[i][i]==false && ini!=0){
				build.append(this.traducirValor(ini)+this.traducirValor(ini)+",");
				ini=0;
			}

		}
		if(this.tabla[0][0]==true){
			if(ini!=0){
				build.append(this.traducirValor(ini)+this.traducirValor(ini)+'+'+",");
			}
			else build.append(this.traducirValor(0)+this.traducirValor(0)+",");
		}
		else if(ini!=0){
			build.append(this.traducirValor(ini)+this.traducirValor(ini)+",");
		}
		return build.toString();

	}
	private String traducirValor(int valor){
		if(valor==0)
			return "A";
		else if (valor==1)
			return "K";
		else if (valor==2)
			return "Q";
		else if (valor==3)
			return "J";
		else if (valor==4)
			return "T";
		else if (valor==5)
			return "9";
		else if (valor==6)
			return "8";
		else if (valor==7)
			return "7";
		else if (valor==8)
			return "6";
		else if (valor==9)
			return "5";
		else if (valor==10)
			return "4";
		else if (valor==11)
			return "3";
		else if (valor==12)
			return "2";
		else return "x";
	}
	//texto a tablero
	public void leerMano(){
		boolean correct=false;
		Rango r=null;
		try{
			for (int i=0;i<mano.length;i++){
				r=ParseJugador.ParseRango(mano[i]);
				if (r==null)
					throw new Exception();
				if(r.isPareja()){
					if(r.getTipo()=='+'){
						for(int j=r.getValorAt(0);j>=0;j--){
							this.tabla[j][j]=true;
							for(Observer o : obs)
								o.actualizarBoton(j, j);
						}
					}
					else if(r.getTipo()=='-'){
						for(int j=r.getValorAt(2);j>=r.getValorAt(0);j--){	
							this.tabla[j][j]=true;
							for(Observer o : obs)
								o.actualizarBoton(j, j);
						}
					}
					else {this.tabla[r.getValorAt(0)][r.getValorAt(1)]=true;
					for(Observer o : obs)
						o.actualizarBoton(r.getValorAt(0), r.getValorAt(1));
					}
				}
				else if(r.isOffsuited()){
					if(r.getTipo()=='+'){
						for(int j=r.getValorAt(1);j>r.getValorAt(0);j--){
							this.tabla[j][r.getValorAt(0)]=true;
							for(Observer o : obs)
								o.actualizarBoton(j, r.getValorAt(0));
						}
					}
					else if(r.getTipo()=='-'){
						for(int j=r.getValorAt(3);j>=r.getValorAt(1);j--){
							this.tabla[j][r.getValorAt(0)]=true;
							for(Observer o : obs)
								o.actualizarBoton(j, r.getValorAt(0));
						}
					}
					else {
						this.tabla[r.getValorAt(1)][r.getValorAt(0)]=true;
						for(Observer o : obs)
							o.actualizarBoton(r.getValorAt(1), r.getValorAt(0));
					}
				}
				else if(r.isSuited()){
					if(r.getTipo()=='+'){
						for(int j=r.getValorAt(1);j>r.getValorAt(0);j--){
							this.tabla[r.getValorAt(0)][j]=true;
							for(Observer o : obs)
								o.actualizarBoton(r.getValorAt(0), j);
						}
					}
					else if(r.getTipo()=='-'){
						for(int j=r.getValorAt(3);j>=r.getValorAt(1);j--)	{
							this.tabla[r.getValorAt(0)][j]=true;
							for(Observer o : obs)
								o.actualizarBoton(r.getValorAt(0), j);
						}
					}
					else{
						this.tabla[r.getValorAt(0)][r.getValorAt(1)]=true;
						for(Observer o : obs)
							o.actualizarBoton(r.getValorAt(0), r.getValorAt(1));
					}
				}
			}
			for(Observer o : obs)
				o.actualizarPorcentaje();
			correct=true;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Datos incorrectos");
		}
		this.restartTable();
	}

	public void add(Observer view) {
		// TODO Auto-generated method stub
		obs.add(view);
	}
	public void quitaCartasB(Carta[] carA) {
		// TODO Auto-generated method stub
		for(int s = 0; s < carA.length; s++){
			for (int i =0;i<13;i++){
				for (int j=0;j<4;j++){
					if(carA[s].toString().equals(baraja.getCartas()[i][j].toString()) && (baraja.getBaraja()[i][j])){
						baraja.setBaraja(i, j, false);
						for(Observer o : obs)
							o.actualizarBotonDeBoard(i, j, false);
					}
				}
			}
		}
	}

	public void ponCartasB(Carta[] carN) {
		// TODO Auto-generated method stub
		for(int s = 0; s < carN.length; s++){
			for (int i =0;i<13;i++){
				for (int j=0;j<4;j++){
					if(carN[s].toString().equals(baraja.getCartas()[i][j].toString()) && (!baraja.getBaraja()[i][j])){
						baraja.setBaraja(i, j, true);
						for(Observer o : obs)
							o.actualizarBotonDeBoard(i, j, true);
					}
				}
			}
		}
	}

	public void quitaCartasD(Carta[] carA) {
		// TODO Auto-generated method stub
		for(int s = 0; s < carA.length; s++){
			for (int i =0;i<13;i++){
				for (int j=0;j<4;j++){
					if(carA[s].toString().equals(baraja.getCartas()[i][j].toString()) && (baraja.getBaraja()[i][j])){
						baraja.setBaraja(i, j, false);
						for(Observer o : obs)
							o.actualizarBotonDeDead(i, j, false);
					}
				}
			}
		}
	}

	public void ponCartasD(Carta[] carN) {
		// TODO Auto-generated method stub
		for(int s = 0; s < carN.length; s++){
			for (int i =0;i<13;i++){
				for (int j=0;j<4;j++){
					if(carN[s].toString().equals(baraja.getCartas()[i][j].toString()) && (!baraja.getBaraja()[i][j])){
						baraja.setBaraja(i, j, true);
						for(Observer o : obs)
							o.actualizarBotonDeDead(i, j, true);
					}
				}
			}
		}
	}
	public void ponCartasBB(Carta[] car) {
		// TODO Auto-generated method stub
		for(int s = 0; s < car.length; s++){
			for (int i =0;i<13;i++){
				for (int j=0;j<4;j++){
					if(car[s].toString().equals(baraja.getCartas()[i][j].toString())){
						baraja.setBaraja(i, j, true);
					}
				}
			}
		}
	}

	public void ponCartasBD(Carta[] car) {
		// TODO Auto-generated method stub
		for(int s = 0; s < car.length; s++){
			for (int i =0;i<13;i++){
				for (int j=0;j<4;j++){
					if(car[s].toString().equals(baraja.getCartas()[i][j].toString())){
						baraja.setBaraja(i, j, true);
					}
				}
			}
		}
	}

	public void quitarCartas(Carta car) {
		// TODO Auto-generated method stub
		for (int i =0;i<13;i++){
			for (int j=0;j<4;j++){
				if(car.toString().equals(baraja.getCartas()[i][j].toString())){
					baraja.setBaraja(i, j, false);
				}
			}
		}
	}
	public void cartasJugadorParejas(int jug, String text) {
		// TODO Auto-generated method stub
		ArrayList<ParCartas> cartasRango = new ArrayList<ParCartas>();
		if(!text.equals("") && text != null){
			if((text.charAt(1)=='s' || text.charAt(1)=='h' || text.charAt(1)=='d' || text.charAt(1)=='c') &&
					(text.charAt(3)=='s' || text.charAt(3)=='h' || text.charAt(3)=='d' || text.charAt(3)=='c')){
				if(text.length() == 4){
					int v1 = valor(text.charAt(0));
					int v2 = valor(text.charAt(2));
					int p1 = pesoPalo(text.charAt(1));
					int p2 = pesoPalo(text.charAt(3));
					if((baraja.getBaraja()[v1][p1]) || (baraja.getBaraja()[v2][p2])){
						for(Observer o : obs)
							o.actualizarDatosJug(jug, cartasRango);
					}
					else{
						Carta[] cartas = new Carta[2];
						Carta car1 = new Carta(text.charAt(0), text.charAt(1));
						Carta car2 = new Carta(text.charAt(2), text.charAt(3));
						cartas[0] = car1;
						cartas[1] = car2;
						if((v1 == v2) && (p1 == p2)){
							for(Observer o : obs)
								o.actualizarDatosJug(jug, cartasRango);
						}
						else if((v1 == v2) && (p1 != p2)){
							ParCartas c = new ParCartas(cartas[0], cartas[1], 6);
							cartasRango.add(c);
							for(Observer o : obs)
								o.actualizarDatosJug(jug, cartasRango);
						}
						else if((v1 != v2) && (p1 != p2)){
							ParCartas c = new ParCartas(cartas[0], cartas[1], 12);
							cartasRango.add(c);
							for(Observer o : obs)
								o.actualizarDatosJug(jug, cartasRango);
						}
						else if((v1 != v2) && (p1 == p2)){
							ParCartas c = new ParCartas(cartas[0], cartas[1], 4);
							cartasRango.add(c);
							for(Observer o : obs)
								o.actualizarDatosJug(jug, cartasRango);
						}
					}
				}
				else{
					for(Observer o : obs)
						o.actualizarDatosJug(jug, cartasRango);
				}
			}
			else{
				//			boolean repetido = false;
				//			if(text.equals("random")){
				//				int rango = 0;
				//				int ocupadas = 0;
				//				for(int i = 0; i < 13; i++){
				//					for(int j = 0; j < 4; j++){
				//						if(baraja.getBaraja()[i][j])
				//							ocupadas++;
				//					}
				//				}
				//				rango = 52 - ocupadas;
				//				int numCartas = (int) (Math.random()*rango+1);
				//				while(numCartas < 2) numCartas = (int) (Math.random()*rango+1);
				//				if((numCartas % 2) != 0) numCartas-=1;
				//				Carta[] cartas = new Carta[numCartas];
				//				for(int i = 0; i < numCartas; i++){
				//					int palo = (int) (Math.random()*3);
				//					int peso = (int) (Math.random()*12);
				//					while(baraja.getBaraja()[peso][palo]){
				//						palo = (int) (Math.random()*3);
				//						peso = (int) (Math.random()*12);
				//					}
				//					char p = palo(palo);
				//					char n = peso(peso);
				//					Carta car = new Carta(n, p);
				//					for(int j = 0; j < i; j++){
				//						if(cartas[j].toString().equals(car.toString())){
				//							palo = (int) (Math.random()*3);
				//							peso = (int) (Math.random()*12);
				//							p = palo(palo);
				//							n = peso(peso);
				//							car = new Carta(n, p);
				//							j=-1;
				//						}
				//					}
				//					cartas[i] = car;
				//				}
				//				for(int i = 0; i < numCartas; i+=2){
				//					Carta[] c = new Carta[2];
				//					c[0] = new Carta(cartas[i].numCarta, cartas[i].paloCarta);
				//					c[1] = new Carta(cartas[i+1].numCarta, cartas[i+1].paloCarta);
				//					cartasRango.add(c);
				//				}
				//				for(Observer o : obs)
				//					o.actualizarDatosJug(jug, cartasRango);
				//			}
				//			else{
				String[] sePorComas = text.split(","); 
				boolean repetido = false;
				for(int i = 0; i < sePorComas.length && !repetido; i++){
					int primero = valor(sePorComas[i].charAt(0));
					int segundo = valor(sePorComas[i].charAt(1));
					if(primero == segundo){
						if(sePorComas[i].length() == 3){
							if(sePorComas[i].charAt(2)=='+'){
								int valorI = valor(sePorComas[i].charAt(0));
								for(int j=valorI; j>=0 && !repetido;j--){	
									Carta[] carta = new Carta[2];
									int s = 0;
									ArrayList<Integer> libres = new ArrayList<Integer>();
									for(int i1 = 0; i1 < 4; i1++){
										if(!baraja.getBaraja()[j][i1]){
											libres.add(i1);
											s++;
										}
									}
									if(s >= 2){
										int x = (int) (Math.random()*libres.size()+0);
										char p = palo(libres.get(x));
										char n = peso(j);
										carta[0] = new Carta(n, p);
										int x2 = -1;
										while((x2 == -1)){
											x2 = (int) (Math.random()*libres.size()+0);
											if(x == x2){
												x2 = -1;
											}
										}
										n = peso(j);
										p = palo(libres.get(x2));
										carta[1] = new Carta(n, p);
										ParCartas c = new ParCartas(carta[0], carta[1], 6);
										cartasRango.add(c);
									}
									else{
										cartasRango.clear();
										repetido = true;
									}
								}
							}
						}
						else if(sePorComas[i].length() > 3){
							if(text.contains("-")){
								int valorI = valor(sePorComas[i].charAt(0));
								int valorF = valor(sePorComas[i].charAt(3));
								for(int j=valorI; j<=valorF && !repetido;j++){	
									Carta[] carta = new Carta[2];
									int s = 0;
									ArrayList<Integer> libres = new ArrayList<Integer>();
									for(int i1 = 0; i1 < 4; i1++){
										if(!baraja.getBaraja()[j][i1]){
											libres.add(i1);
											s++;
										}
									}
									if(s >= 2){
										int x = (int) (Math.random()*libres.size()+0);
										char p = palo(libres.get(x));
										char n = peso(j);
										carta[0] = new Carta(n, p);
										int x2 = -1;
										while((x2 == -1)){
											x2 = (int) (Math.random()*libres.size()+0);
											if(x == x2){
												x2 = -1;
											}
										}
										n = peso(j);
										p = palo(libres.get(x2));
										carta[1] = new Carta(n, p);
										ParCartas c = new ParCartas(carta[0], carta[1], 6);
										cartasRango.add(c);
									}
									else{
										cartasRango.clear();
										repetido = true;
									}
								}
							}
						}
						else if(sePorComas[i].length() == 2){
							Carta[] carta = new Carta[2];
							int valor = valor(sePorComas[i].charAt(0));
							int s = 0;
							ArrayList<Integer> libres = new ArrayList<Integer>();
							for(int i1 = 0; i1 < 4; i1++){
								if(!baraja.getBaraja()[valor][i1]){
									libres.add(i1);
									s++;
								}
							}
							if(s >= 2){
								int x = (int) (Math.random()*libres.size()+0);
								char p = palo(libres.get(x));
								carta[0] = new Carta(sePorComas[i].charAt(0), p);
								int x2 = -1;
								while((x2 == -1)){
									x2 = (int) (Math.random()*libres.size()+0);
									if(x == x2){
										x2 = -1;
									}
								}
								p = palo(libres.get(x2));
								carta[1] = new Carta(sePorComas[i].charAt(0), p);
								ParCartas c = new ParCartas(carta[0], carta[1], 6);
								cartasRango.add(c);
							}
							else{
								cartasRango.clear();
								repetido = true;
							}

						}
					}
					else if(primero < segundo){
						if(sePorComas[i].charAt(2)=='o'){
							if(sePorComas[i].length() == 3){
								Carta[] carta = new Carta[2];
								int valor1 = valor(sePorComas[i].charAt(0));
								int valor2 = valor(sePorComas[i].charAt(1));
								int s = 0, s2 = 0;
								ArrayList<Integer> libres = new ArrayList<Integer>();
								ArrayList<Integer> libres2 = new ArrayList<Integer>();
								for(int i1 = 0; i1 < 4; i1++){
									if(!baraja.getBaraja()[valor1][i1]){
										libres.add(i1);
										s++;
									}
								}
								for(int i1 = 0; i1 < 4; i1++){
									if(!baraja.getBaraja()[valor2][i1]){
										libres2.add(i1);
										s2++;
									}
								}
								if((s == 0) || (s2 == 0)){
									repetido = true;
									cartasRango.clear();
								}
								else if((s == 1) && (s2 == 1)){
									if(libres.get(0) == libres2.get(0)){
										repetido = true;
										cartasRango.clear();
									}
									else{
										char p1 = palo(libres.get(0));
										char p2 = palo(libres2.get(0));
										carta[0] = new Carta(sePorComas[i].charAt(0), p1);
										carta[1] = new Carta(sePorComas[i].charAt(1), p2);
										ParCartas c = new ParCartas(carta[0], carta[1], 12);
										cartasRango.add(c);
									}
								}
								else if((s == 1) && (s2 > 1)){
									int x = (int) (Math.random()*libres.size()+0);
									int x2 = (int) (Math.random()*libres2.size()+0);
									while(x == x2){
										x2 = (int) (Math.random()*libres2.size()+0);
									}
									char p1 = palo(libres.get(x));
									char p2 = palo(libres2.get(x2));
									carta[0] = new Carta(sePorComas[i].charAt(0), p1);
									carta[1] = new Carta(sePorComas[i].charAt(1), p2);
									ParCartas c = new ParCartas(carta[0], carta[1], 12);
									cartasRango.add(c);
								}
								else if((s > 1) && (s2 == 1)){
									int x = (int) (Math.random()*libres.size()+0);
									int x2 = (int) (Math.random()*libres2.size()+0);
									while(x == x2){
										x = (int) (Math.random()*libres.size()+0);
									}
									char p1 = palo(libres.get(x));
									char p2 = palo(libres2.get(x2));
									carta[0] = new Carta(sePorComas[i].charAt(0), p1);
									carta[1] = new Carta(sePorComas[i].charAt(1), p2);
									ParCartas c = new ParCartas(carta[0], carta[1], 12);
									cartasRango.add(c);
								}
								else if((s >= 2) && (s2 >= 2)){
									int x = (int) (Math.random()*libres.size()+0);
									int x2 = (int) (Math.random()*libres2.size()+0);
									while(x == x2){
										x = (int) (Math.random()*libres.size()+0);
										x2 = (int) (Math.random()*libres2.size()+0);
									}
									char p1 = palo(libres.get(x));
									char p2 = palo(libres2.get(x2));
									carta[0] = new Carta(sePorComas[i].charAt(0), p1);
									carta[1] = new Carta(sePorComas[i].charAt(1), p2);
									ParCartas c = new ParCartas(carta[0], carta[1], 12);
									cartasRango.add(c);
								}
							}
							else if(sePorComas[i].charAt(3)=='+'){
								if(sePorComas[i].length() == 4){
									int valor1 = valor(sePorComas[i].charAt(0));
									int valor2 = valor(sePorComas[i].charAt(1));
									if(valor1 < valor2){
										for(int j=valor1+1; j<=valor2 && !repetido;j++){
											Carta[] carta = new Carta[2];
											int s = 0, s2 = 0;
											ArrayList<Integer> libres = new ArrayList<Integer>();
											ArrayList<Integer> libres2 = new ArrayList<Integer>();
											for(int i1 = 0; i1 < 4; i1++){
												if(!baraja.getBaraja()[j][i1]){
													libres.add(i1);
													s++;
												}
											}
											for(int i1 = 0; i1 < 4; i1++){
												if(!baraja.getBaraja()[j][i1]){
													libres2.add(i1);
													s2++;
												}
											}
											if((s == 0) || (s2 == 0)){
												repetido = true;
												cartasRango.clear();
											}
											else if((s == 1) && (s2 == 1)){
												if(libres.get(0) == libres2.get(0)){
													repetido = true;
													cartasRango.clear();
												}
												else{
													char p1 = palo(libres.get(0));
													char p2 = palo(libres2.get(0));
													char n1 = peso(valor1);
													char n2 = peso(j);
													carta[0] = new Carta(n1, p1);
													carta[1] = new Carta(n2, p2);
													ParCartas c = new ParCartas(carta[0], carta[1], 12);
													cartasRango.add(c);
												}
											}
											else if((s == 1) && (s2 > 1)){
												int x = (int) (Math.random()*libres.size()+0);
												int x2 = (int) (Math.random()*libres2.size()+0);
												while(x == x2){
													x2 = (int) (Math.random()*libres2.size()+0);
												}
												char p1 = palo(libres.get(x));
												char p2 = palo(libres2.get(x2));
												char n1 = peso(valor1);
												char n2 = peso(j);
												carta[0] = new Carta(n1, p1);
												carta[1] = new Carta(n2, p2);
												ParCartas c = new ParCartas(carta[0], carta[1], 12);
												cartasRango.add(c);
											}
											else if((s > 1) && (s2 == 1)){
												int x = (int) (Math.random()*libres.size()+0);
												int x2 = (int) (Math.random()*libres2.size()+0);
												while(x == x2){
													x = (int) (Math.random()*libres.size()+0);
												}
												char p1 = palo(libres.get(x));
												char p2 = palo(libres2.get(x2));
												char n1 = peso(valor1);
												char n2 = peso(j);
												carta[0] = new Carta(n1, p1);
												carta[1] = new Carta(n2, p2);
												ParCartas c = new ParCartas(carta[0], carta[1], 12);
												cartasRango.add(c);
											}
											else if((s >= 2) && (s2 >= 2)){
												int x = (int) (Math.random()*libres.size()+0);
												int x2 = (int) (Math.random()*libres2.size()+0);
												while(x == x2){
													x = (int) (Math.random()*libres.size()+0);
													x2 = (int) (Math.random()*libres2.size()+0);
												}
												char p1 = palo(libres.get(x));
												char p2 = palo(libres2.get(x2));
												char n1 = peso(valor1);
												char n2 = peso(j);
												carta[0] = new Carta(n1, p1);
												carta[1] = new Carta(n2, p2);
												ParCartas c = new ParCartas(carta[0], carta[1], 12);
												cartasRango.add(c);
											}
										}

									}
								}
							}
							else if(sePorComas[i].contains("-")){
								if(sePorComas[i].length() == 7){
									if((sePorComas[i].charAt(2) == 'o') && (sePorComas[i].charAt(6) == 'o')){
										int valor1 = valor(sePorComas[i].charAt(0));
										int valor2 = valor(sePorComas[i].charAt(1));
										int valor3 = valor(sePorComas[i].charAt(4));
										int valor4 = valor(sePorComas[i].charAt(5));
										if((valor1 < valor2) && (valor3 < valor4)){
											if(valor1 == valor3){
												for(int j=valor2; j<=valor4 && !repetido;j++){
													Carta[] carta = new Carta[2];
													int s = 0, s2 = 0;
													ArrayList<Integer> libres = new ArrayList<Integer>();
													ArrayList<Integer> libres2 = new ArrayList<Integer>();
													for(int i1 = 0; i1 < 4; i1++){
														if(!baraja.getBaraja()[valor1][i1]){
															libres.add(i1);
															s++;
														}
													}
													for(int i1 = 0; i1 < 4; i1++){
														if(!baraja.getBaraja()[valor2][i1]){
															libres2.add(i1);
															s2++;
														}
													}
													if((s == 0) || (s2 == 0)){
														repetido = true;
														cartasRango.clear();
													}
													else if((s == 1) && (s2 == 1)){
														if(libres.get(0) == libres2.get(0)){
															repetido = true;
															cartasRango.clear();
														}
														else{
															char p1 = palo(libres.get(0));
															char p2 = palo(libres2.get(0));
															char n1 = peso(valor1);
															char n2 = peso(j); 
															carta[0] = new Carta(n1, p1);
															carta[1] = new Carta(n2, p2);
															ParCartas c = new ParCartas(carta[0], carta[1], 12);
															cartasRango.add(c);
														}
													}
													else if((s == 1) && (s2 > 1)){
														int x = (int) (Math.random()*libres.size()+0);
														int x2 = (int) (Math.random()*libres2.size()+0);
														while(x == x2){
															x2 = (int) (Math.random()*libres2.size()+0);
														}
														char p1 = palo(libres.get(x));
														char p2 = palo(libres2.get(x2));
														char n1 = peso(valor1);
														char n2 = peso(j); 
														carta[0] = new Carta(n1, p1);
														carta[1] = new Carta(n2, p2);
														ParCartas c = new ParCartas(carta[0], carta[1], 12);
														cartasRango.add(c);
													}
													else if((s > 1) && (s2 == 1)){
														int x = (int) (Math.random()*libres.size()+0);
														int x2 = (int) (Math.random()*libres2.size()+0);
														while(x == x2){
															x = (int) (Math.random()*libres.size()+0);
														}
														char p1 = palo(libres.get(x));
														char p2 = palo(libres2.get(x2));
														char n1 = peso(valor1);
														char n2 = peso(j); 
														carta[0] = new Carta(n1, p1);
														carta[1] = new Carta(n2, p2);
														ParCartas c = new ParCartas(carta[0], carta[1], 12);
														cartasRango.add(c);
													}
													else if((s >= 2) && (s2 >= 2)){
														int x = (int) (Math.random()*libres.size()+0);
														int x2 = (int) (Math.random()*libres2.size()+0);
														while(x == x2){
															x = (int) (Math.random()*libres.size()+0);
															x2 = (int) (Math.random()*libres2.size()+0);
														}
														char p1 = palo(libres.get(x));
														char p2 = palo(libres2.get(x2));
														char n1 = peso(valor1);
														char n2 = peso(j); 
														carta[0] = new Carta(n1, p1);
														carta[1] = new Carta(n2, p2);
														ParCartas c = new ParCartas(carta[0], carta[1], 12);
														cartasRango.add(c);
													}
												}
											}
										}
									}
								}
							}
						}
						else if(sePorComas[i].charAt(2)=='s'){
							if(sePorComas[i].length() == 3){
								Carta[] carta = new Carta[2];
								int valor1 = valor(sePorComas[i].charAt(0));
								int valor2 = valor(sePorComas[i].charAt(1));
								int s = 0, s2 = 0;
								ArrayList<Integer> libres = new ArrayList<Integer>();
								ArrayList<Integer> libres2 = new ArrayList<Integer>();
								for(int i1 = 0; i1 < 4; i1++){
									if(!baraja.getBaraja()[valor1][i1]){
										libres.add(i1);
										s++;
									}
								}
								for(int i1 = 0; i1 < 4; i1++){
									if(!baraja.getBaraja()[valor2][i1]){
										libres2.add(i1);
										s2++;
									}
								}
								if((s > 0) && (s2 > 0)){
									int k;
									int i1 = 0;
									boolean ok = false;
									for(k = 0; k < libres2.size() && !ok; k++){
										for(i1 = 0; i1 < libres.size() && !ok; i1++){
											if(libres.get(i1) != libres2.get(k)){

											}
											else{
												ok = true;
											}
										}
									}
									k--;
									i1--;
									if(ok){
										char p1 = palo(libres.get(i1));
										char p2 = palo(libres2.get(k));
										carta[0] = new Carta(sePorComas[i].charAt(0), p1);
										carta[1] = new Carta(sePorComas[i].charAt(1), p2);
										ParCartas c = new ParCartas(carta[0], carta[1], 4);
										cartasRango.add(c);
									}
									else{
										repetido = true;
										cartasRango.clear();
									}
								}
								else{
									repetido = true;
									cartasRango.clear();
								}
							}
							else if(sePorComas[i].charAt(3)=='+'){
								if(sePorComas[i].length() == 4){
									int valor1 = valor(sePorComas[i].charAt(0));
									int valor2 = valor(sePorComas[i].charAt(1));
									if(valor1 < valor2){
										for(int j=valor1+1; j<=valor2 && !repetido;j++){
											Carta[] carta = new Carta[2];
											int s = 0, s2 = 0;
											ArrayList<Integer> libres = new ArrayList<Integer>();
											ArrayList<Integer> libres2 = new ArrayList<Integer>();
											for(int i1 = 0; i1 < 4; i1++){
												if(!baraja.getBaraja()[j][i1]){
													libres.add(i1);
													s++;
												}
											}
											for(int i1 = 0; i1 < 4; i1++){
												if(!baraja.getBaraja()[j][i1]){
													libres2.add(i1);
													s2++;
												}
											}
											if((s > 0) && (s2 > 0)){
												int k;
												int i1 = 0;
												boolean ok = false;
												for(k = 0; k < libres2.size() && !ok; k++){
													for(i1 = 0; i1 < libres.size() && !ok; i1++){
														if(libres.get(i1) != libres2.get(k)){

														}
														else{
															ok = true;
														}
													}
												}
												k--;
												i1--;
												if(ok){
													char p1 = palo(libres.get(i1));
													char p2 = palo(libres2.get(k));
													char n1 = peso(valor1);
													char n2 = peso(j);
													carta[0] = new Carta(n1, p1);
													carta[1] = new Carta(n2, p2);
													ParCartas c = new ParCartas(carta[0], carta[1], 4);
													cartasRango.add(c);
												}
												else{
													repetido = true;
													cartasRango.clear();
												}
											}
											else{
												repetido = true;
												cartasRango.clear();
											}
										}

									}
								}
							}
							else if(sePorComas[i].contains("-")){
								if(sePorComas[i].length() == 7){
									if((sePorComas[i].charAt(2) == 's') && (sePorComas[i].charAt(6) == 's')){
										int valor1 = valor(sePorComas[i].charAt(0));
										int valor2 = valor(sePorComas[i].charAt(1));
										int valor3 = valor(sePorComas[i].charAt(4));
										int valor4 = valor(sePorComas[i].charAt(5));
										if((valor1 < valor2) && (valor3 < valor4)){
											if(valor1 == valor3){
												for(int j=valor2; j<=valor4 && !repetido;j++){
													Carta[] carta = new Carta[2];
													int s = 0, s2 = 0;
													ArrayList<Integer> libres = new ArrayList<Integer>();
													ArrayList<Integer> libres2 = new ArrayList<Integer>();
													for(int i1 = 0; i1 < 4; i1++){
														if(!baraja.getBaraja()[valor1][i1]){
															libres.add(i1);
															s++;
														}
													}
													for(int i1 = 0; i1 < 4; i1++){
														if(!baraja.getBaraja()[valor2][i1]){
															libres2.add(i1);
															s2++;
														}
													}
													if((s > 0) && (s2 > 0)){
														int k;
														int i1 = 0;
														boolean ok = false;
														for(k = 0; k < libres2.size() && !ok; k++){
															for(i1 = 0; i1 < libres.size() && !ok; i1++){
																if(libres.get(i1) != libres2.get(k)){

																}
																else{
																	ok = true;
																}
															}
														}
														k--;
														i1--;
														if(ok){
															char p1 = palo(libres.get(i1));
															char p2 = palo(libres2.get(k));
															char n1 = peso(valor1);
															char n2 = peso(j);
															carta[0] = new Carta(n1, p1);
															carta[1] = new Carta(n2, p2);
															ParCartas c = new ParCartas(carta[0], carta[1], 4);
															cartasRango.add(c);
														}
														else{
															repetido = true;
															cartasRango.clear();
														}
													}
													else{
														repetido = true;
														cartasRango.clear();
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			for(Observer o : obs)
				o.actualizarDatosJug(jug, cartasRango);
		}
		//}
		else{
			for(Observer o : obs)
				o.actualizarDatosJug(jug, cartasRango);
		}
	}

	public char peso(int numCarta){
		char valor = 0;
		if(numCarta == 0) valor = 'A';
		else if(numCarta == 1) valor = 'K';
		else if(numCarta == 2) valor = 'Q';
		else if(numCarta == 3) valor = 'J';
		else if(numCarta == 4) valor = 'T';
		else if(numCarta == 5) valor = '9';
		else if(numCarta == 6) valor = '8';
		else if(numCarta == 7) valor = '7';
		else if(numCarta == 8) valor = '6';
		else if(numCarta == 9) valor = '5';
		else if(numCarta == 10) valor = '4';
		else if(numCarta == 11) valor = '3';
		else if(numCarta == 12) valor = '2';
		else if(numCarta == 13) valor = '1';
		return valor;
	}

	public int valor(char numCarta){
		int valor = 0;
		if(numCarta == 'A') valor = 0;
		else if(numCarta == 'K') valor = 1;
		else if(numCarta == 'Q') valor = 2;
		else if(numCarta == 'J') valor = 3;
		else if(numCarta == 'T') valor = 4;
		else if(numCarta == '9') valor = 5;
		else if(numCarta == '8') valor = 6;
		else if(numCarta == '7') valor = 7;
		else if(numCarta == '6') valor = 8;
		else if(numCarta == '5') valor = 9;
		else if(numCarta == '4') valor = 10;
		else if(numCarta == '3') valor = 11;
		else if(numCarta == '2') valor = 12;
		else if(numCarta == '1') valor = 13;
		return valor;
	}

	public char palo(int palo){
		char valor = 0;
		if(palo == 0) valor = 's';
		else if(palo == 1) valor = 'h';
		else if(palo == 2) valor = 'd';
		else if(palo == 3) valor = 'c';
		return valor;
	}
	
	public int pesoPalo(char palo){
		int valor = 0;
		if(palo == 's') valor = 0;
		else if(palo == 'h') valor = 1;
		else if(palo == 'd') valor = 2;
		else if(palo == 'c') valor = 3;
		return valor;
	}

	public void ponCartasBoard(Carta[] car, String cartasParaTf) {
		// TODO Auto-generated method stub
		for(int s = 0; s < car.length; s++){
			for (int i =0;i<13;i++){
				for (int j=0;j<4;j++){
					if(car[s].toString().equals(baraja.getCartas()[i][j].toString())){
						baraja.setBaraja(i, j, true);
					}
				}
			}
		}
		for(Observer o : obs)
			o.actualizaTextBoard(car, cartasParaTf);
	}

	public void ponCartasDead(Carta[] car, String cartasParaTf) {
		// TODO Auto-generated method stub
		for(int s = 0; s < car.length; s++){
			for (int i =0;i<13;i++){
				for (int j=0;j<4;j++){
					if(car[s].toString().equals(baraja.getCartas()[i][j].toString())){
						baraja.setBaraja(i, j, true);
					}
				}
			}
		}
		for(Observer o : obs)
			o.actualizaTextDead(car, cartasParaTf);
	}
	public void actializaArrayDead(Carta[] car) {
		// TODO Auto-generated method stub
		for(Observer o : obs)
			o.actualizaArraytDead(car);
	}

	public void actializaArrayBoard(Carta[] car) {
		// TODO Auto-generated method stub
		for(Observer o : obs)
			o.actualizaArraytBoard(car);
	}
	public void comprobarPonerATrue(Carta car1, Carta car2) {
		// TODO Auto-generated method stub
			for (int i =0;i<13;i++){
				for (int j=0;j<4;j++){
					if(car1.toString().equals(baraja.getCartas()[i][j].toString())){
						baraja.setBaraja(i, j, true);
					}
				}
			}
			for (int i =0;i<13;i++){
				for (int j=0;j<4;j++){
					if(car2.toString().equals(baraja.getCartas()[i][j].toString())){
						baraja.setBaraja(i, j, true);
					}
				}
			}
	}
	public void comprobarLibres(Carta car1, Carta car2) {
		// TODO Auto-generated method stub
		int libresCar1 = -1;
		int libresCar2 = -1;
		for (int i =0;i<13;i++){
			if(car1.numCarta == baraja.getCartas()[i][0].numCarta){
				for (int j=0;j<4;j++){
					if(!baraja.getBaraja()[i][j]){
						libresCar1++;
					}
				}
			}
		}
		for (int i =0;i<13;i++){
			if(car2.numCarta == baraja.getCartas()[i][0].numCarta){
				for (int j=0;j<4;j++){
					if(!baraja.getBaraja()[i][j]){
						libresCar2++;
					}
				}
			}
		}
		for(Observer o : obs)
			o.comprobarConocerLibres(libresCar1, libresCar2);
	}
	
	public void comprObtPaloLibre(int jug2, char numCarta) {
		// TODO Auto-generated method stub
		int v = valor(numCarta);
		int paloLibre = 0;
		for (int i =0;i<13;i++){
			for (int j=0;j<4;j++){
				if(v == i){
					if(!baraja.getBaraja()[i][j]){
						paloLibre = j;
					}
				}
			}
		}
		char palo = palo(paloLibre);
		for(Observer o : obs)
			o.cambiarPaloLibre(palo);
	}
	public void comprobarPonerAFalse(Carta car1, Carta car2) {
		// TODO Auto-generated method stub
		for (int i =0;i<13;i++){
			for (int j=0;j<4;j++){
				if(car1.toString().equals(baraja.getCartas()[i][j].toString())){
					baraja.setBaraja(i, j, false);
				}
			}
		}
		for (int i =0;i<13;i++){
			for (int j=0;j<4;j++){
				if(car2.toString().equals(baraja.getCartas()[i][j].toString())){
					baraja.setBaraja(i, j, false);
				}
			}
		}
	}
	public void comprobarPonerATrueV2(Carta car1o, Carta car2o) {
		// TODO Auto-generated method stub
		boolean c1 = false;
		boolean c2 = false;
		for (int i =0;i<13 && !c1;i++){
			for (int j=0;j<4 && !c1;j++){
				if(car1o.toString().equals(baraja.getCartas()[i][j].toString()) && (!c1)){
					if(!baraja.getBaraja()[i][j]){
						baraja.setBaraja(i, j, true);
					}
					else{
						c1 = true;
					}
				}
			}
		}
		for (int i =0;i<13 && !c2;i++){
			for (int j=0;j<4 && !c2;j++){
				if(car2o.toString().equals(baraja.getCartas()[i][j].toString()) && (!c2)){
					if(!baraja.getBaraja()[i][j]){
						baraja.setBaraja(i, j, true);
					}
					else{
						c2 = true;
					}
				}
			}
		}
		for(Observer o : obs)
			o.comprobarRepetida(c1, c2);
	}
	public Baraja getBaraja() {
		// TODO Auto-generated method stub
		return this.baraja;
	}
	public void damePalo(char p) {
		// TODO Auto-generated method stub
		for(Observer o : obs)
			o.damePalo(p);
	}
}
