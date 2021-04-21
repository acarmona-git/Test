package test;
import java.io.*;
import java.util.ArrayList;

public class archivos {
	
	
	public ArrayList<String> leerTxt(String rutaDelArchivo) {
		
		ArrayList<String> lineas = new ArrayList<String>();
		
		try {
			BufferedReader bf = new BufferedReader(new FileReader(rutaDelArchivo));
			String bfRead;
			while((bfRead = bf.readLine()) != null) { 
				
				if(bfRead.charAt(0) == 'D') {
					lineas.add(bfRead);
				}
			}
		}catch(Exception e) {
			System.err.println("No se encontro el archivo");
		}
		return lineas;
	}
}
