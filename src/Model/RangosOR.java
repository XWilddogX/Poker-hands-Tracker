package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RangosOR {
	
	/*
	// Rangos de OR de Janda
	final double OR_JANDA_UTG = 13.9;
	final double OR_JANDA_HJ  = 17.9;
	final double OR_JANDA_CO  = 23.7;
	final double OR_JANDA_BTN = 47.5;
	final double OR_JANDA_SB  = 36.3;
	
	// Nuestros rangos de OR
	final double OR_GRUPO1_UTG = 15;
	final double OR_GRUPO1_HJ  = 20;
	final double OR_GRUPO1_CO  = 30;
	final double OR_GRUPO1_BTN = 50;
	final double OR_GRUPO1_SB  = 50;
	
	// Rangos de OR super tight
	final double OR_TIGHT_UTG  = 5;
	final double OR_TIGHT_HJ   = 7;
	final double OR_TIGHT_CO   = 12;
	final double OR_TIGHT_BTN  = 17;
	final double OR_TIGHT_SB   = 17;
	
	// Rangos de OR super loose
	final double OR_LOOSE_UTG  = 30;
	final double OR_LOOSE_HJ   = 40;
	final double OR_LOOSE_CO   = 60;
	final double OR_LOOSE_BTN  = 80;
	final double OR_LOOSE_SB   = 80;
	*/
	
	private String nombreRangosOR;
	
	private double orUTG;
	private double orHJ;
	private double orCO;
	private double orBTN;
	private double orSB;
	
	private double[] rangos; //orUTG orHJ orCO orBTN orSB
	private FileReader fr;
	private BufferedReader br;
	
		
	public void asignarRangosOR() throws IOException {
		
		// buscar en los archivos el nombreRangosOR
		
		rangos = new double[5];
		fr = new FileReader("src/rangos/" + nombreRangosOR + ".txt");
		br = new BufferedReader(fr);
		
		String linea = "";
		double x;
		int i = 0;
		while((linea = br.readLine()) != null){
			x = Double.parseDouble(linea);
			rangos[i] = x;
			i++;
		}
		
		br.close();
		fr.close();

	}

	public double getOrUTG() {
		return rangos[0];
	}

	public double getOrHJ() {
		return rangos[1];
	}

	public double getOrCO() {
		return rangos[2];
	}

	public double getOrBTN() {
		return rangos[3];
	}

	public double getOrSB() {
		return rangos[4];
	}
	
	public void setNombreRangosOR(String nombreRangosOR) {
		this.nombreRangosOR = nombreRangosOR;
	}
}
