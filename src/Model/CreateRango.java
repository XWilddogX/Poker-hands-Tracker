package Model;

import java.io.*;

import Swing.ChooserRang_Rank;

public class CreateRango {
	
	private File carpeta_rangos;
	private FileWriter fw;
	private BufferedWriter bw;
	private PrintWriter pw;
	
	
	public CreateRango(String nombre, StringBuilder rango) throws IOException{
		
		carpeta_rangos = new File("src/rangos");
		carpeta_rangos.mkdirs();
		
		fw = new FileWriter("src/rangos/" + nombre + ".txt");
		bw = new BufferedWriter(fw);
		
		
		if(rango != null){
			bw.write(rango.toString());
		}
		
		bw.close();
		fw.close();
	
		//ahora vamos a añadir el nuevo nombre a la lista de rangos
		
		FileWriter file = new FileWriter("src/rangos/nombresRangos.txt", true);
		pw = new PrintWriter(file);
		pw.write(nombre + "\n");
		file.close();
		pw.close();
		
		ChooserRang_Rank ra = new ChooserRang_Rank();

	}

}
