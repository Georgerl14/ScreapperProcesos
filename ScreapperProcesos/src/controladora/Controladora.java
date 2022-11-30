package controladora;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dominio.Dominio;
import dominio.GestionarDominio;
import manejador.Utiles;
import termino.GestionarTermino;
import visual.Menu;

public class Controladora {
	private static GestionarDominio gestionar = new GestionarDominio();
	private static GestionarTermino gestionarTm = new GestionarTermino();
	
	public static void administrarDominios() {
		try {
			while (generarMenu());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static boolean logicaMenu() throws IOException {
		switch (Utiles.introducirOpcion("Elegir opcion: ", 0, 1, 5, gestionar.longitudLista())) {

		case 1:
			gestionar.insertarDominio();
			break;

		case 2:
			gestionar.listarDominio(true);
			break;

		case 3:
			gestionar.eliminarDominio();
			break;

		case 4:
			while (generarSegundoMenu());
			break;

		case 5:
			//analizarTodos();
			break;

		case 0:
			return cerrarPrograma();
		}
		return true;
	}
	
	private static boolean logicaSegundoMenu() throws FileNotFoundException, IOException {
		switch (Utiles.introducirOpcion("Elegir opcion: ", 0, 5)) {
		case 0:
			return false;
		case 1:
			gestionar.mostrarPaginaUrl();
			break;

		case 2:
			gestionar.verEstado();
			break;

		case 3:
			while (generarTercerMenu());
			break;

		case 4:
			gestionar.scrappear();
			break;

		case 5:
			//generarResumen();
			break;
		}
		return true;
	}

	private static boolean logicaTercerMenu() {
		switch (Utiles.introducirOpcion("Elegir opcion: ", 0, 1, 3, gestionarTm.longitudLista())) {
		case 1:
			gestionarTm.introducirTermino();
			break;

		case 2:
			gestionarTm.eliminarTermino();
			break;

		case 3:
			gestionarTm.listarTermino(true);
			break;

		case 0:
			return false;
		}
		return true;
	}
	
	private static boolean generarMenu() throws IOException {
		Menu.mostrarMenu(gestionar.longitudLista());
		return logicaMenu();
	}

	private static boolean generarSegundoMenu() throws FileNotFoundException, IOException {
		Menu.mostrarSegundoMenu();
		return logicaSegundoMenu();
	}
	
	private static boolean generarTercerMenu() {
		Menu.mostrarTercerMenu(gestionarTm.longitudLista());
		return logicaTercerMenu();
	}
	
	private static boolean cerrarPrograma() {
		int opcion = (Utiles.introducirOpcion("Desea cerrar el programa? (1) Si, (2) No : ", 1, 2));
		return (opcion == 1) ? false : true;
	}
}
