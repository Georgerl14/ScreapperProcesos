package ejercicio1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	private static String ruta = "";
	private static String termino = "";
	private static String reemplazo = "";

	private static String numeroOcurrencia = "";
	private static String rutaReemplazo = "";

	public static void main(String[] args) {
		try {
			switch (args.length) {
			case 0:
				while (generarMenu());
				break;

			case 2:
				ruta = args[0];
				termino = args[1];
				mostrarNumeroTermino(true);
				break;

			case 3:
				ruta = args[0];
				termino = args[1];
				reemplazo = args[2];
				realizarReemplazar(false);
				break;

			default:
				errorParametros();
			}
			System.exit(0);
		} catch (FileNotFoundException e1) {
			System.out.println("Error: Archivo no encontrado");
			System.exit(1);
		} catch (IOException e1) {
			System.out.println("Error: El proceso no se pudo completar");
			System.exit(2);
		}
	}

	private static boolean generarMenu() throws FileNotFoundException, IOException {
		mostrarMenu();
		return logicaMenu();
	}

	private static boolean logicaMenu() throws FileNotFoundException, IOException {
		int minimo;
		if (ruta != "" && termino != "") {
			minimo = 4;
		} else {
			minimo = 2;
		}
		switch (introducirOpcion("Elegir opcion: ", 0, minimo)) {
		case 0:
			System.out.println("Programa cerrado");
			return false;

		case 1:
			introducirTermino();
			break;

		case 2:
			introducirRutaArchivo();
			break;

		case 3:
			if (ruta != "" && termino != "") {
				mostrarNumeroTermino(false);
			} else {
				System.out.println("Error: Numeros entre 0 - 2");
			}
			break;

		case 4:
			if (ruta != "" && termino != "") {
				introducirReemplazo();
				realizarReemplazar(true);
			} else {
				System.out.println("Error: Numeros entre 0 - 2");
			}
			break;
		}

		System.out.println();
		return true;
	}

	private static void mostrarMenu() {
		System.out.println("(1) Introducir termino de busqueda | " + termino);
		System.out.println("(2) Introducir ruta del archivo    | " + ruta);

		if (!ruta.equals("") && !termino.equals("")) {
			System.out.println("(3) Mostrar numero de ocurrencias  | " + numeroOcurrencia);
			System.out.println("(4) Realizar reemplazo             | " + rutaReemplazo);
		}
		System.out.println("(0) Salir");
	}

	private static void introducirRutaArchivo() {
		Scanner sc = new Scanner(System.in);
		File archivoRuta;
		boolean salir = false;
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
			}
		} while (!new File(ruta).isFile() && !salir);
	}

	private static void introducirTermino() {
		Scanner sc = new Scanner(System.in);
		do {
			System.out.print("Termino: ");
			termino = sc.nextLine();

			if (termino.length() == 0) {
				System.out.println("Error: Entrada en blanco");
			}

		} while (termino.length() == 0);
	}

	private static void realizarReemplazar(Boolean texto) throws FileNotFoundException, IOException {
		rutaReemplazo = ruta.replaceFirst(".txt", "_NUEVO.txt");
		try (BufferedReader br = new BufferedReader(new FileReader(ruta));
				BufferedWriter bw = new BufferedWriter(new FileWriter(rutaReemplazo));) {
			String linea;
			int numeroReemplazos = 0;
			while ((linea = br.readLine()) != null) {
				String[] parte = linea.split(" ");

				for (int i = 0; i < parte.length; i++) {
					if (parte[i].equals(termino)) {
						escribirReemplazo(bw, parte, i);
						numeroReemplazos++;
					} else {
						escribirLinea(bw, linea, parte, i);
					}
				}
			}

			if (texto) {
				System.out.println("Proceso realizado");
			} else {
				System.out.println("Ruta del reemplazo: " + rutaReemplazo);
				System.out.println("Numero de reemplazos: " + numeroReemplazos);
			}
		}

	}

	private static void introducirReemplazo() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Termino a reemplazar: ");
		reemplazo = sc.nextLine();
	}

	private static void escribirReemplazo(BufferedWriter bw, String[] parte, int i) throws IOException {
		if (i == parte.length - 1) {
			bw.write(reemplazo + "\n");
		} else {
			bw.write(reemplazo + " ");
		}
	}

	private static void escribirLinea(BufferedWriter bw, String linea, String[] parte, int i) throws IOException {
		if (i == parte.length - 1) {
			bw.write(parte[i] + "\n");
		} else {
			bw.write(parte[i] + " ");
		}
	}

	private static int mostrarNumeroTermino(boolean texto) throws FileNotFoundException, IOException {
		int contador = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(ruta));) {
			String linea;
			while ((linea = br.readLine()) != null) {
				for (int i = 0; i < linea.length(); i++) {
					char letraTermino = termino.charAt(0);
					char letraActual = linea.charAt(i);
					String palabra = " ";

					if (!(i + termino.length() > linea.length())) {
						if (letraTermino == letraActual) {
							palabra = linea.substring(i, i + termino.length());
							if (termino.equals(palabra)) {
								contador++;
							}
						}
					}

				}
			}
			if (texto) {
				System.out.println("Numero de ocurrencias: " + contador);
			} else {
				numeroOcurrencia = String.valueOf(contador);
			}
		}
		return contador;
	}

	private static void errorParametros() {
		System.out.println("Error: La cantidad de parametros no es adecuada");
		System.out.println("Opcion 1: java Main.java \n          Abre un menu para seleccionar opciones");
		System.out.println();
		System.out.println("Opcion 2: java Main.java \"ruta\" \"termino\" \n"
				+ "          Muestro la cantidad de ocurrencias del termino");
		System.out.println();
		System.out.println("Opcion 3: java Main.java \"ruta\" \"termino\" \"reemplazo\"  \n"
				+ "          Realiza el reemplazo de todas las ocurrencias del termino");
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

}
