package jaxb;

import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Ejemplo1_JAXB {

	private static final String MIARCHIVO_XML = "Escuela.xml";

	public static void main(String[] args) throws JAXBException, IOException {
		// Se crea la lista de alumnos
		ArrayList<Alumno> alumnoLista = new ArrayList<Alumno>();

		// Creamos dos libros y los añadimos
		Alumno alumno1 = new Alumno(1, "A", "B", "C", 3);
		Alumno alumno2 = new Alumno(2, "A", "B", "C", 4);
		alumnoLista.add(alumno1);
		alumnoLista.add(alumno2);

		// Se crea La escuela y se le asigna la lista de alumnos
		Escuela escuela = new Escuela();
		escuela.setListaAlumno(alumnoLista);

		// Creamos el contexto indicando la clase raíz
		JAXBContext context = JAXBContext.newInstance(Escuela.class);

		// Creamos el Marshaller, convierte el java bean en una cadena XML
		Marshaller m = context.createMarshaller();

		// Formateamos el xml para que quede bien
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		// Lo visualizamos con system out
		m.marshal(escuela, System.out);

		// Escribimos en el archivo
		m.marshal(escuela, new File(MIARCHIVO_XML));

		// Visualizamos ahora los datos del documento XML creado
		System.out.println("------------ Leo el XML ------------");

		// Se crea Unmarshaller en el cotexto de la clase Libreria
		Unmarshaller unmars = context.createUnmarshaller();

		// Utilizamos el m�todo unmarshal, para obtener datos de un Reader
		Escuela escuela2 = (Escuela) unmars.unmarshal(new FileReader(MIARCHIVO_XML));
		
		// Recuperamos el array list y visualizamos
		System.out.println("Alumnos de la escuela: ");

		ArrayList<Alumno> lista = escuela2.getListaAlumno();
		for (Alumno alumnos : lista) {
			System.out.println("\tId del alumno: " + alumnos.getId() + 
					"\nnombre: " + alumnos.getNombre() +
					"\napellido: "+ alumnos.getApellido1() +
					"\napellido2: " + alumnos.getApellido2() +
					"\ncodigo postal: "+alumnos.getCodigoPostal());
		}
	}
}