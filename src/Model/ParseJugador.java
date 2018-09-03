package Model;

public class ParseJugador {
public static Rango ParseRango(String k){
	int i=0;
	Rango r=new Rango();
	int valor[]=new int[4];
	valor[0]=ParseJugador.traducirCarta(k.charAt(0));
	valor[1]=ParseJugador.traducirCarta(k.charAt(1));
	if (valor[0]==valor[1] && valor[0]!=-1)i=-1;
	if(k.contains("-")){
	valor[2]=ParseJugador.traducirCarta(k.charAt(4+i));
	valor[3]=ParseJugador.traducirCarta(k.charAt(5+i));
	}
	if(valor[0]==-1 || valor[1]==-1 || valor[3]==-1 || valor[2]==-1)
		return null;
	r.setValor(valor);
	if(valor[0]== valor[1] && k.length()<6){
		r.setPareja(true);
		if(k.length()>2){
		if((k.charAt(2)=='+'&&k.length()==3)){
			r.setTipo(k.charAt(2));
		}
		else if (k.charAt(2)=='-'&&valor[2]==valor[3]  && valor[0]<valor[2]){
				r.setTipo(k.charAt(2));
			}
		else return null;
	}
	}
	else if(valor[0]<valor[1] &&k.length()<8){
		if(k.charAt(2)=='s'){
			r.setSuited(true);
		}
		else if(k.charAt(2)=='o'){
			r.setOffsuited(true);
		}
		else return null;
		if(k.length()>3){
		if((k.charAt(3)=='+'&& k.length()==4)){
			r.setTipo(k.charAt(3));
		}
		else if (k.charAt(3)=='-'){
			if(valor[0]==valor[2] && valor[1]<valor[3]&&k.charAt(2)==k.charAt(6)){
				r.setTipo(k.charAt(3));
			}
			else return null;
		}
		}
	}
	else return null;
	return r;
}

public static Carta ParseBoardDead(String k){
	Carta car = new Carta(k.charAt(0), k.charAt(1));
	if((car.getValorNum() == 0) || (car.getValorPalo() == 0))
		car = null;
	return car;
}

private static int traducirCarta(char valor){
	if(valor=='A')
		return 0;
	else if (valor=='K')
		return 1;
	else if (valor=='Q')
		return 2;
	else if (valor=='J')
		return 3;
	else if (valor=='T')
		return 4;
	else if (valor=='9')
		return 5;
	else if (valor=='8')
		return 6;
	else if (valor=='7')
		return 7;
	else if (valor=='6')
		return 8;
	else if (valor=='5')
		return 9;
	else if (valor=='4')
		return 10;
	else if (valor=='3')
		return 11;
	else if (valor=='2')
		return 12;
	else return -1;
}
}
