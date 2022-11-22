package XML;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.xml.parsers.DocumentBuilderFactory;

import jaxb.Alumno;
import practicaUnidad_1.RegistrosAleatorios;

public class EscribirFichObject {
	public static void main(String[] args) throws IOException  {

		Alumno alumno;// defino variable alumno
		
		File fichero = new File("ObjetoAlumno.dat");// declara el fichero
		
		FileOutputStream fileout = new FileOutputStream(fichero, false); // crea el flujo de salida
		// conecta el flujo de bytes al flujo de datos
		ObjectOutputStream dataOS = new ObjectOutputStream(fileout);
		
		File ficheroApoyo = new File("AleatorioAlumno.dat");
		System.out.println("GRABO LOS DATOS DE AlUMNO.");
		for (int i = 0; i < RegistrosAleatorios.numRegistros(ficheroApoyo); i++) { // recorro los arrays
			alumno = RegistrosAleatorios.leerRegistroPos(ficheroApoyo, i); // creo la persona
			System.out.println(alumno.toString());
			dataOS.writeObject(alumno); // escribo la persona en el fichero
			System.out.println("GRABO LOS DATOS DE AlUMNO.");
		}
		dataOS.close(); // cerrar stream de salida
	}
}
