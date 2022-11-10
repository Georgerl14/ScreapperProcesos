package XML;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import practicaUnidad_1.Alumno;

public class EscribirFichObject {
	public static void main(String[] args) throws IOException {

		Alumno alumno;// defino variable persona

		File fichero = new File("FichAlumnos.dat");// declara el fichero
		FileOutputStream fileout = new FileOutputStream(fichero, false); // crea el flujo de salida
		// conecta el flujo de bytes al flujo de datos
		ObjectOutputStream dataOS = new ObjectOutputStream(fileout);

		int id[] = { 2, 4, 1, 5, 6, 7, 8 };
		String nombre[] = { "Paco", "Mano", "Serg", "Ionel", "Ale", "Pedro", "Pablo" };
		String apellido[] = { "Man", "Gil", "Lopez", "Ramos", "Sevi", "Casi", "Rey" };
		String apellido2[] = { "Montes", "Lopez", "Campo", "Villa", "Rojas", "Campillo", "Mier" };
		int cp[] = { 39543, 43456, 23456, 11223, 30789, 30432, 20564 };

		System.out.println("GRABO LOS DATOS DE ALUMNO.");
		for (int i = 0; i < nombre.length; i++) { // recorro los arrays
			alumno = new Alumno(id[i], nombre[i], apellido[i], apellido2[i], cp[i]); // creo la persona
			dataOS.writeObject(alumno); // escribo la persona en el fichero
			System.out.println("GRABO LOS DATOS DE AlUMNO.");
		}
		dataOS.close(); // cerrar stream de salida
	}
}
