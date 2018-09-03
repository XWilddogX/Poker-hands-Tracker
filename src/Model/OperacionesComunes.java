package Model;
import java.util.ArrayList;
import java.util.Arrays;


public class OperacionesComunes {

	public static Mano victoriosa(Mano[] manos) {
		// TODO Auto-generated method stub
		Mano mano = null;
		Arrays.sort(manos);
		mano = manos[0];
		return mano;
	}

	private static int factorial(int n) {
		int fact = 1;
		for (int i = 1; i <= n; i++) {
			fact *= i;
		}
		return fact;
	}

	public static Mano[] combinaciones(ArrayList<Carta> cartas) {
		// TODO Auto-generated method stub
		int numCartas = cartas.size(), combinacion = 0;; 
		Carta[] mano = new Carta[5];
		int res = factorial(numCartas) / (factorial(5) * factorial(numCartas - 5));
		Mano[] manos = new Mano[res];
		int v[] = new int[numCartas];

		for(int i = 0; i < 5; i++){ 
			v[i]=i; 
			mano[i] = cartas.get(i);
		}
		manos[combinacion] = new Mano(mano);
		combinacion++;
		while(combinacion < res){
			int i = 4;
			mano = new Carta[5];
			while (v[i] == numCartas - 5 + i) {
				i--;
			}
			v[i] = v[i] + 1;
			for (int j = i + 1; j < 5; j++) {
				v[j] = v[i] + j - i;
			}
			for(int s = 0; s < 5; s++)
				mano[s] = cartas.get(v[s]);
			manos[combinacion] = new Mano(mano);
			combinacion++;
		}

		return manos;
	}
}
