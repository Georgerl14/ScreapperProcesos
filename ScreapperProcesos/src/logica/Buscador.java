package logica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import dominio.Dominio;

public class Buscador {
	public static String generarHtmlDominios(Dominio dominio) throws IOException {

		String ruta = "..\\ScreapperProcesos\\data\\" + dominio.getNombre() + "\\" + dominio.getNombre()
				+ "_codigofuente.log";

		try (BufferedReader br = new BufferedReader(new InputStreamReader((dominio.getUrl()).openStream()));
				BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, false));) {

			String linea;
			while ((linea = br.readLine()) != null) {
				bw.write(linea + "\n");
			}
			
		}
		return ruta;
	}

	public static String generarHtmlDominios(Dominio dominio, Dominio dominioActual) throws IOException {
		
		String ruta = "..\\ScreapperProcesos\\data\\" + dominio.getNombre() + "\\" + dominioActual.getNombre()
				+ "_codigofuente.log";

		try (BufferedReader br = new BufferedReader(new InputStreamReader((dominioActual.getUrl()).openStream()));
				BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, false));) {

			String linea;
			while ((linea = br.readLine()) != null) {
				bw.write(linea + "\n");
			}
			
		}
		return ruta;
	}
	
	public static String generarURLPaginas(String rutaHTML, Dominio dominio) throws FileNotFoundException, IOException {
		// BUSCA LAS URLs DENTRO DEL HTML
		HashSet<String> listaURL = buscarURL(rutaHTML);

		dominio.setListaUrls(listaURL);

		// PASA LOS DATOS ENCONTRADOS A UN FICHERO
		return escribirURL(listaURL, dominio);
	}

	public static String generarURLPaginas(String rutaHTML, Dominio dominio, Dominio dominioActual) throws FileNotFoundException, IOException {
		// BUSCA LAS URLs DENTRO DEL HTML
		HashSet<String> listaURL = buscarURL(rutaHTML);

		dominio.setListaUrls(listaURL);

		// PASA LOS DATOS ENCONTRADOS A UN FICHERO
		return escribirURL(listaURL, dominio, dominioActual);
	}
	
	private static String escribirURL(HashSet<String> listaURL, Dominio dominio) throws IOException {
		String ruta = "..\\ScreapperProcesos\\data\\" + dominio.getNombre() + "\\" + dominio.getNombre()
				+ "_resumen.log";

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, true));) {
			bw.write("\nURls encontradas en " + dominio.getUrl().toString() + " : \n");

			for (String urls : listaURL) {
				bw.write(urls + "\n");
			}
		}
		return ruta;
	}

	private static String escribirURL(HashSet<String> listaURL, Dominio dominio, Dominio dominioActual) throws IOException {
		String ruta = "..\\ScreapperProcesos\\data\\" + dominio.getNombre() + "\\" + dominio.getNombre()
				+ "_resumen.log";

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, true));) {
			bw.write("\nURls encontradas en " + dominio.getUrl().toString() + " : \n");

			for (String urls : listaURL) {
				bw.write(urls + "\n");
			}
		}
		return ruta;
	}
	
	private static HashSet<String> buscarURL(String rutaHTML) throws FileNotFoundException, IOException {

		HashSet<String> listaURL = new HashSet<>();

		String inicioBusqueda = "href=\"h";
		int longitudPrimeraParte = inicioBusqueda.length();
		char finalBusqueda = '"';

		try (BufferedReader br = new BufferedReader(new FileReader(rutaHTML));) {

			String linea;
			while ((linea = br.readLine()) != null) {
				int contador = 0;
				for (int i = 0; i < linea.length(); i += 1) {
					contador = 0;
					try {
						String etiqueta = linea.substring(i, i + longitudPrimeraParte);

						if (inicioBusqueda.equals(etiqueta)) {
							int j = i + longitudPrimeraParte;
							char letraActual;
							do {
								letraActual = linea.charAt(j);
								contador++;
								j++;
							} while (letraActual != finalBusqueda);

							int indexInicial = i + longitudPrimeraParte - 1;
							int indexFinal = indexInicial + contador;
							i = indexInicial + indexFinal;

							String rutaURL = linea.substring(indexInicial, indexFinal);
							listaURL.add(rutaURL);
						}
					} catch (Exception e) {
					}
				}
			}
		}

		return listaURL;
	}

//	public static int buscarTermino(String rutaHTML, List<String> listaTermino, Dominio dominio)
//			throws FileNotFoundException, IOException {
//		int contador = 0;
//		try (BufferedReader br = new BufferedReader(new FileReader(rutaHTML)); // ENTRAMOS EN EL HTML DE UNA PAGINA
//				BufferedWriter bw = new BufferedWriter(
//						new FileWriter("./src/terminos/" + dominio.getUrl().getHost() + "_Terminos.log"))) {
//			String linea;
//			for (int j = 0; j < listaTermino.size(); j++) {
//				while ((linea = br.readLine()) != null) {
//					contador = 0;
//					for (int i = 0; i < linea.length(); i++) {
//						char letraTermino = listaTermino.get(j).charAt(0);
//						char letraActual = linea.charAt(i);
//						String palabra = " ";
//
//						if (!(i + listaTermino.get(j).length() > linea.length())) {
//							if (letraTermino == letraActual) {
//								palabra = linea.substring(i, i + listaTermino.get(j).length());
//								if (listaTermino.get(j).equals(palabra)) {
//									contador++;
//								}
//							}
//						}
//					}
//					bw.write(listaTermino.get(j) +": "+contador+"\n");
//				}
//			}
//		}
//		return contador;
//	}
}
