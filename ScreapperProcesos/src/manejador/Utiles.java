package manejador;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Utiles {

	public static boolean doesURLExist(URL url) throws IOException {
		// We want to check the current URL
		HttpURLConnection.setFollowRedirects(false);

		HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

		// We don't need to get data
		httpURLConnection.setRequestMethod("HEAD");

		// Some websites don't like programmatic access so pretend to be a browser
		httpURLConnection.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
		int responseCode = httpURLConnection.getResponseCode();

		// We only accept response code 200
		return responseCode == HttpURLConnection.HTTP_OK;
	}

	public static int introducirOpcion(String texto, int minimo, int maximo) {
		Scanner sc = new Scanner(System.in);
		int numero = -1;
		do {
			System.out.print("[n] " + texto);
			try {
				numero = Integer.parseInt(sc.nextLine());
				if (numero < minimo || numero > maximo) {
					mostrarError("Error: Numeros entre " + minimo + " - " + maximo);
					numero = -1;
				}
			} catch (NumberFormatException e) {
				mostrarError("Error: Solo se permiten numero enteros");
			}
			
		} while (numero == -1);
		return numero;
	}

	public static int introducirOpcion(String texto, int minimo, int medio, int maximo, int limite) {
		Scanner sc = new Scanner(System.in);
		int numero = -1;
		do {
			System.out.print("[n] " + texto);
			try {
				numero = Integer.parseInt(sc.nextLine());
				if (limite == 0) {
					if (numero < minimo || numero > medio) {
						mostrarError("Error: Solo se permiten numeros entre " + minimo + " - " + medio);
						numero = -1;
					}
				} else if (numero < minimo || numero > maximo) {
					mostrarError("Error: Numeros entre " + minimo + " - " + maximo);
					numero = -1;
				}
			} catch (NumberFormatException e) {
				mostrarError("Error: Solo se permiten numero enteros");
			}
			System.out.println();
		} while (numero == -1);
		return numero;
	}

	private static void mostrarError(String error) {
		for (int i = 0; i < error.length(); i++) {
			System.out.print("-");
		}
		System.out.println("\n" + error);
		for (int i = 0; i < error.length(); i++) {
			System.out.print("-");
		}
	}
	
	public static boolean reintentar() {
		int opcion = Utiles.introducirOpcion("Quiere introducir otro dato? (1) Si (2) No : ", 1, 2);
		return (opcion == 1) ? false : true;
	}
}