package test;

import java.util.ArrayList;
import java.util.Scanner; 


public class main {

	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
				
		String simbolo = "$";
		int acumPesos = 0, acumDolares = 0;
		int contEfectivo = 0, contDebito = 0, contCredito = 0;
		int acumEfectivo = 0, acumDebito = 0, acumCredito = 0;
		
		int cont=0;
		archivos a = new archivos();
		
		System.out.println("Ingrese la ruta donde se encuentra el archivo a parsear");
		String ruta = input.nextLine();
		
		ArrayList<String> lineas = a.leerTxt(ruta);
		
		
		System.out.println("------------------------------------------------------------------");
		System.out.println("Transac " + "  Monto    " + "  identif   " + " Fecha  " + " Medio");
		System.out.println("------------------------------------------------------------------");
		
		for(int i=0;i<lineas.size();i++) {
			cont++;
			String lineaActual = lineas.get(i);
			
			String transaccion = lineaActual.substring(40,47);
			System.out.print(transaccion + " ");
			
			String importe = lineaActual.substring(77,87);
			
			if(lineaActual.charAt(110) == '0') {
				simbolo = "$";
				acumPesos = acumPesos + Integer.parseInt(importe);
			}else if(lineaActual.charAt(110) == '1') {
				simbolo = "U$D";
				acumDolares = acumDolares + Integer.parseInt(importe);
			}
			
			System.out.print(simbolo + importe + " ");
			
			String identificador = lineaActual.substring(58,68); //Es hasta 77, pero no se tienen en cuenta los espacios vacios
			System.out.print(identificador + " ");
			
			
			String fechaDePago = lineaActual.substring(224,230);  
			System.out.print(fechaDePago + " ");
	
			String formaDePago = lineaActual.substring(247,249);
			
			if(Integer.parseInt(formaDePago) == 00) {
				formaDePago = "Efectivo";
				acumEfectivo = acumEfectivo + Integer.parseInt(importe);
				contEfectivo++;
			}else if(Integer.parseInt(formaDePago) == 90) {
				formaDePago = "Debito";
				acumDebito = acumDebito + Integer.parseInt(importe);
				contDebito++;
			}else {
				formaDePago = "Credito";
				acumCredito = acumCredito + Integer.parseInt(importe);
				contCredito++;
			}
			
			System.out.println(formaDePago);
			
		}
		
		System.out.println("------------------------------------------------------------------");
		System.out.println("La cantidad de registros cobrados fue de: " + cont + " registros" );

		System.out.println("El monto total de la cobranza en $ fue de: $" + acumPesos);
		System.out.println("El monto total de la cobranza en U$D fue de: U$D " + acumDolares);
		
		
		System.out.println("---- No se tuvo en cuenta la posibilidad de haber pagos en USD ----");
		if(contEfectivo>0) {
			System.out.println("El valor promedio en Efectivo en $ fue de: "+ acumEfectivo/contEfectivo);
		}
		
		if(contDebito>0) {
			System.out.println("El valor promedio con Debito en $ fue de $: "+ acumDebito/contDebito);
		}
		if(contCredito>0) {
			System.out.println("El valor promedio con Credito en $ fue de$: "+ acumCredito/contCredito);
		}
		System.out.println("------------------------------------------------------------------");
	}

}
