package ejercicio2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static List<File> listaArchivos = new ArrayList<File>();

	public static void main(String[] args) throws IOException, InterruptedException {
		while (generarMenu())
			;
	}

	private static void addArchivo() {
		Scanner sc = new Scanner(System.in);
		File archivoRuta;
		boolean salir = false;
		String ruta;
		do {
			System.out.print("Ruta del archivo: ");
			ruta = sc.nextLine();
			archivoRuta = new File(ruta);

			if (archivoRuta.isDirectory())
				System.err.println("Error: Solo se introdujo el directorio");

			if (!archivoRuta.exists())
				System.err.println("Error: No existe el archivo");

			if (!new File(ruta).isFile()) {
				if (introducirOpcion("Volver a intentar? (1)Si (2)No: ", 1, 2) == 2) {
					salir = true;
				}
			} else {
				listaArchivos.add(archivoRuta);
			}

		} while (!new File(ruta).isFile() && !salir);

	}

	private static void removeArchivo() {
		listarArchivos();
		int numero = introducirOpcion("(0) Salir\nCualquiere eliminar: ", 0, listaArchivos.size());
		if (numero != 0) {
			listaArchivos.remove(numero - 1);
		}
	}

	private static void reemplazarCadena() throws IOException {
		Scanner sc = new Scanner(System.in);
		String termino = introducirTermino();
		System.out.print("Reemplazo: ");
		String reemplazo = sc.nextLine();
		
		ProcessBuilder pb = new ProcessBuilder();
		pb.directory(new File("..\\PSPR_UD1_Practica2\\src\\ejercicio1"));

		for (int i = 0; i < listaArchivos.size(); i++) {
			pb.command(Arrays.asList("java", "Main.java", 
					listaArchivos.get(i).getAbsolutePath(), termino, reemplazo));
			Process salida = pb.start();

			try (BufferedReader br = new BufferedReader(new InputStreamReader(salida.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new FileWriter("..\\PSPR_UD1_Practica2\\src\\ejercicio2\\resumen.log",false));
			){
			System.out.println(listaArchivos.get(i).getName());
			String linea;
			
			while((linea = br.readLine()) != null) {
				System.out.println(linea);
				bw.write(linea+"\n");
			}
			System.out.println();
			bw.write("\n");
		} catch(IOException e) {
			
		}
		}
	}

	private static void numeroTermino() throws IOException, InterruptedException {
		String termino = introducirTermino();

		System.out.println();
		System.out.println("Lista de archivos y ocurrencias: ");
		ProcessBuilder pb = new ProcessBuilder();
		pb.directory(new File("..\\PSPR_UD1_Practica2\\src\\ejercicio1"));

		for (int i = 0; i < listaArchivos.size(); i++) {
			pb.command(Arrays.asList("java", "Main.java", listaArchivos.get(i).getAbsolutePath(), termino));
			Process salida = pb.start();

			BufferedReader br = new BufferedReader(new InputStreamReader(salida.getInputStream()));

			System.out.println(listaArchivos.get(i).getName());
			System.out.println(br.readLine());
			System.out.println();
		}
	}

	private static boolean listarArchivos() {
		System.out.println();
		if (listaArchivos.size() != 0) {
			System.out.println("Archivos: ");
			for (int i = 0; i < listaArchivos.size(); i++) {
				System.out.println("(" + (i + 1) + ") " + listaArchivos.get(i).getName());
			}
			return true;
		} else {
			System.out.println("No se encontro ningun archivo para listar");
			return false;
		}

	}

	private static void visualizarArchivo() throws IOException {
		if (introducirOpcion("Desea visualizar algun archivo (1)Si (2)No: ", 1, 2) == 1) {

			int numero = introducirOpcion("Numero de archivo: ", 1, listaArchivos.size());

			ProcessBuilder pb = new ProcessBuilder();
			pb.command(Arrays.asList("notepad.exe", listaArchivos.get(numero - 1).getAbsolutePath()));
			Process salida = pb.start();
		}

	}

	private static boolean generarMenu() throws IOException, InterruptedException {
		mostrarMenu();
		return logicaMenu();
	}

	private static void mostrarMenu() {
		System.out.println("(1) Meter archivo");
		if (listaArchivos.size() != 0) {
			System.out.println("(2) Eliminar archivo");
			System.out.println("(3) Listar archivos a explorar");
			System.out.println("(4) Buscar numero de aparariones de un termino");
			System.out.println("(5) Reemplazar cadena");
		}
		System.out.println("(0) Cerrar");
	}

	private static boolean logicaMenu() throws IOException, InterruptedException {

		int maximo;
		if (listaArchivos.size() != 0) {
			maximo = 5;
		} else {
			maximo = 1;
		}
		switch (introducirOpcion("Elegir opcion: ", 0, maximo)) {
		case 0:
			System.out.println("Programa cerrado");
			return false;

		case 1:
			addArchivo();
			break;

		case 2:
			if (listaArchivos.size() != 0) {
				removeArchivo();
			} else {
				System.out.println("Error: Numeros entre 0 - 1");
			}
			break;

		case 3:
			if (listaArchivos.size() != 0) {
				if (listarArchivos()) {
					visualizarArchivo();
				}
			} else {
				System.out.println("Error: Numeros entre 0 - 1");
			}
			break;

		case 4:
			if (listaArchivos.size() != 0) {
				numeroTermino();
			} else {
				System.out.println("Error: Numeros entre 0 - 1");
			}
			break;

		case 5:
			if (listaArchivos.size() != 0) {
				reemplazarCadena();
			} else {
				System.out.println("Error: Numeros entre 0 - 1");
			}
			break;
		}

		System.out.println();
		return true;
	}

	private static int introducirOpcion(String texto, int minimo, int maximo) {
		Scanner sc = new Scanner(System.in);
		int numero = -1;
		do {
			System.out.print(texto);
			try {
				numero = Integer.parseInt(sc.nextLine());
				if (numero < minimo || numero > maximo) {
					System.out.println("Error: Numeros entre " + minimo + " - " + maximo);
					numero = -1;
				}
			} catch (NumberFormatException e) {
				System.out.println("Error: Solo se permiten numero enteros");
			}
		} while (numero == -1);
		return numero;
	}

	private static String introducirTermino() {
		Scanner sc = new Scanner(System.in);
		String termino;
		do {
			System.out.print("Termino: ");
			termino = sc.nextLine();

			if (termino.length() == 0) {
				System.out.println("Error: Entrada en blanco");
			}

		} while (termino.length() == 0);
		return termino;
	}

}
