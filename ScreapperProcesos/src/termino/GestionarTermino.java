package termino;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import manejador.Utiles;

public class GestionarTermino {
	private List<String> listaTerminos;
	
	public GestionarTermino() {
		listaTerminos = new ArrayList<String>();
	}
	
	public int longitudLista() {
		return listaTerminos.size();
	}
	
	public void eliminarTermino() {
		listarTermino(false);
		int numero = Utiles.introducirOpcion("Elegir opcion: ", 0, listaTerminos.size());
		if (numero != 0) {
			listaTerminos.remove(numero - 1);
		}
	}
	
	public void listarTermino(boolean salir) {
		for (int i = 0; i < listaTerminos.size(); i++) {
			System.out.println(" " + (i + 1) + ". " + listaTerminos.get(i));
		}

		System.out.println("(0) Salir");
		if (salir) {
			Utiles.introducirOpcion("\nElegir opcion: ", 0, 0);
		}
	}
	
	public void introducirTermino() {
		Scanner sc = new Scanner(System.in);
		String termino;
		boolean salir;

		do {
			salir = true;

			System.out.print("Introducir termino: ");
			termino = sc.nextLine();

			// COMPROBAR CAMPO VACIO
			if (termino.isEmpty()) {
				System.out.println("Error: Campo vacio");
				if ((salir = Utiles.reintentar())) {
					termino = null;
				}
			} else {
				// COMPROBAR TERMINOS REPETIDOS
				for (int i = 0; termino != null && i < listaTerminos.size(); i++) {
					if (listaTerminos.get(i).equals(termino)) {
						System.err.println("Error: El termino " + termino + " fue usado");
						if ((salir = Utiles.reintentar())) {
							termino = null;
						}
					}
				}
			}

		} while (!salir);
		
		listaTerminos.add(termino);	
	}
}
