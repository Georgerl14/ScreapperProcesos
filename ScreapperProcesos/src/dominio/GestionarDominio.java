package dominio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import logica.Scrappear;
import manejador.Utiles;

public class GestionarDominio {
	private List<Dominio> listaDominios;
	
	public GestionarDominio() {
		listaDominios = new ArrayList<>();
	}
	
	public GestionarDominio(List<Dominio> listaDominios) {
		this.listaDominios = listaDominios;
	}
	
	public List<Dominio> getListaDominios() {
		return listaDominios;
	}

	public void setListaDominios(List<Dominio> listaDominios) {
		this.listaDominios = listaDominios;
	}
	
	public int longitudLista() {
		return listaDominios.size();
	}
	
	public void insertarDominio() {
		Scanner sc = new Scanner(System.in);
		String nombre;
		URL url;

		System.out.println(" -  Insertar Dominio: ");
		if ((nombre = introducirNombre()) != null && (url = introducirUrl()) != null) {
			listaDominios.add(new Dominio(nombre, url));
			System.out.println("Dominio Introducido\n");
		}
	}

	public void eliminarDominio() {
		listarDominio(false);
		int numero = Utiles.introducirOpcion("Elegir opcion: ", 0, listaDominios.size());
		if (numero != 0) {
			listaDominios.remove(numero - 1);
			System.out.println("Dominio Eliminado\n");
		}
	}
	
	public void listarDominio(boolean salir) {
		for (int i = 0; i < listaDominios.size(); i++) {
			System.out.println(
					" " + (i + 1) + ". " + listaDominios.get(i).getNombre() + " || " + listaDominios.get(i).getUrl());
		}

		System.out.println("(0) Salir");
		if (salir) {
			Utiles.introducirOpcion("Elegir opcion: ", 0, 0);
		}
	}
	
	public void scrappear() throws FileNotFoundException, IOException {
		listarDominio(false);
		int numero = Utiles.introducirOpcion("Elegir opcion: ", 0, listaDominios.size());
		if (numero != 0) {
			Dominio dominio = listaDominios.get(numero - 1);
			Scrappear scrappear = new Scrappear(dominio/*, listaTerminos*/);
			scrappear.start();
		}
	}
	
	public String introducirNombre() {
		Scanner sc = new Scanner(System.in);
		String nombre;
		boolean salir;

		do {
			salir = true;

			System.out.print("Introducir nombre: ");
			nombre = sc.nextLine();

			// COMPROBAR CAMPO VACIO
			if ((nombre.trim()).isEmpty()) {
				System.out.println("Error: Campo vacio");
				if ((salir = Utiles.reintentar())) {
					nombre = null;
				}
			} else {
				// COMPROBAR NOMBRES REPETIDOS
				for (int i = 0; nombre != null && i < listaDominios.size(); i++) {
					if (listaDominios.get(i).getNombre().equals(nombre)) {
						System.err.println("Error: El nombre " + nombre + " fue usado");
						if ((salir = Utiles.reintentar())) {
							nombre = null;
						}
					}
				}
			}

		} while (!salir);

		return nombre;
	}

	public URL introducirUrl() {
		Scanner sc = new Scanner(System.in);
		URL url = null;
		boolean salir;
		do {
			salir = true;
			System.out.print("Introducir URL: ");
			try {
				url = new URL(sc.nextLine());

				if (!Utiles.doesURLExist(url)) {
					System.out.println("Error: URL no encontrada");
					if ((salir = Utiles.reintentar())) {
						url = null;
					}
				}

				// COMPROBAR URLs REPETIDAS
				else {
					for (int i = 0; url != null && i < listaDominios.size(); i++) {
						if (listaDominios.get(i).getUrl().equals(url)) {
							System.err.println("Error: La Url " + url + " ya esta insertada");
							if ((salir = Utiles.reintentar())) {
								url = null;
							}
						}
					}
				}

			} catch (MalformedURLException e) {
				System.out.println("Error: URL no encontrada");
				if ((salir = Utiles.reintentar())) {
					url = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		} while (!salir);

		return url;
	}

	public void mostrarPaginaUrl() {
		listarDominio(false);
		int opcion = Utiles.introducirOpcion("Pagina a mostrar: ", 0, listaDominios.size()+1);
		listaDominios.get(opcion-1).verUrl();
	}
	
	public void verEstado() {
		listarDominio(false);
		int opcion = Utiles.introducirOpcion("Estado a mostrar: ", 0, listaDominios.size()+1);
		
		switch(listaDominios.get(opcion-1).getEstado()) {
		case 0:	System.out.println(listaDominios.get(opcion-1).getNombre() + ": No analizado");
			break;
			
		case 1: System.out.println("Analizando...");
			break;
			
		case 2: System.out.println("Analizado");
			break;
			
		case 3: System.out.println(" [X] - No se pudo analizar correctamente");
			break;
		}
		
		Utiles.introducirOpcion("Para volver introduzca 0: ", 0, 0);
	}
}
