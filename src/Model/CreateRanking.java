package Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import Swing.ChooserRang_Rank;

public class CreateRanking {
	
	
	private FileWriter archivo;
	private File carpeta_ranking;
	private BufferedWriter bw;
	private PrintWriter pw;
	
	public CreateRanking(StringBuilder ranking, String nombre) throws IOException{
		carpeta_ranking = new File("src/ranking");
		carpeta_ranking.mkdirs();
		archivo = new FileWriter("src/ranking/" + nombre + ".txt");
		bw = new BufferedWriter(archivo);
		if(ranking != null){
			 bw.write(ranking.toString());
		}
	   
		bw.close();
		archivo.close();
		//agregamos un nombre nuevo al archivo de rankings
		
		FileWriter file = new FileWriter("src/ranking/nombresRanking.txt", true);
		pw = new PrintWriter(file);
		pw.write(nombre + "\n");
		file.close();
		pw.close();
		
		ChooserRang_Rank ra = new ChooserRang_Rank();
	}

}
