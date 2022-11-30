package logica;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import dominio.Dominio;

public class Scrappear extends Thread {
	private Dominio dominio;
	// private List <String> listaTerminos;

	public Scrappear(Dominio dominio/* , List<String> listaTerminos */) {
		super();
		this.dominio = dominio;
		// this.listaTerminos = listaTerminos;
	}

	public Dominio getDominio() {
		return dominio;
	}

	public void setDominio(Dominio dominio) {
		this.dominio = dominio;
	}

//	public List<String> getListaTerminos() {
//		return listaTerminos;
//	}
//	public void setListaTerminos(List<String> listaTerminos) {
//		this.listaTerminos = listaTerminos;
//	}

	public void leerURLs(Dominio domino/* , List<String> listaTerminos */) {

		for (String rutaUrl : dominio.getListaUrls()) {
			URL url;
			try {
				url = new URL(rutaUrl);

				Dominio dominioActual = new Dominio(url.getHost(), url);

				String rutaHTML;

				rutaHTML = Buscador.generarHtmlDominios(dominio, dominioActual);

				Buscador.generarURLPaginas(rutaHTML, dominio, dominioActual);
			} catch (IOException e) {
				
			}
		}

	}

	public void run() {
		try {
			dominio.setEstado(1);
			escribirEncabezadoResumen();
			String rutaHTML = Buscador.generarHtmlDominios(dominio);

			Buscador.generarURLPaginas(rutaHTML, dominio);

			leerURLs(dominio);
			dominio.setEstado(2);

		} catch (Exception e) {
			dominio.setEstado(3);
		}

	}

	private void escribirEncabezadoResumen() {
		Path path = Paths.get("..\\ScreapperProcesos\\data\\" + dominio.getNombre());
		try {
			Files.createDirectories(path);

			String ruta = "..\\ScreapperProcesos\\data\\" + dominio.getNombre() + "\\" + dominio.getNombre()
					+ "_resumen.log";
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, false));) {
				Date fechaActual = new Date();
				bw.write(fechaActual + "\n");
				bw.write("Nombre dominio: " + dominio.getNombre() + "\n");
				bw.write("URL: " + dominio.getUrl().toString() + "\n");

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
