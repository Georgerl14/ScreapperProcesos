package com.razvan.ProbandoJaxb;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;

public class Ejemplo1_JAXB {

	private static final String MIARCHIVO_XML = "./libreria.xml";

	public static void main(String[] args) throws JAXBException, IOException {

		// Se crea la lista de libros
		ArrayList<Libreria> librerialista = new ArrayList<Libreria>();

		ArrayList<Libro> libroLista = new ArrayList<Libro>();
		ArrayList<Libro> libroLista2 = new ArrayList<Libro>();

		// Creamos dos libros y los añadimos
		Libro libro1 = new Libro("Entornos de Desarrollo", "Alicia Ramos", "Garceta", "978-84-1545-297-3");
		libroLista.add(libro1);
		libroLista2.add(libro1);

		Libro libro2 = new Libro("Acceso a Datos", "Maria Jesús Ramos", "Garceta", "978-84-1545-228-7");
		libroLista.add(libro2);
		libroLista2.add(libro2);

		Libro libro3 = new Libro("Entollo", "Alimos", "Garta", "987-24-9845-457-3");
		libroLista2.add(libro3);

		// Se crea La libreria y se le asigna la lista de libros
		Libreria milibreria = new Libreria();
		milibreria.setNombre("Prueba de libreria JAXB");
		milibreria.setLugar("Talavera, como no");
		milibreria.setListaLibro(libroLista);

		librerialista.add(milibreria);

		Libreria milibreria2 = new Libreria();
		milibreria2.setNombre("Prueba JAXB");
		milibreria2.setLugar("Algun sitio, como no");
		milibreria2.setListaLibro(libroLista2);

		librerialista.add(milibreria2);

		WLibreria MISLIBRERIAS = new WLibreria();
		MISLIBRERIAS.setListaLibreria(librerialista);

		// Creamos el contexto indicando la clase raíz
		JAXBContext context = JAXBContext.newInstance(WLibreria.class);

		// Creamos el Marshaller, convierte el java bean en una cadena XML
		Marshaller m = context.createMarshaller();

		// Formateamos el xml para que quede bien
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		// Lo visualizamos con system out
		m.marshal(MISLIBRERIAS, System.out);

		// Escribimos en el archivo
		m.marshal(MISLIBRERIAS, new File(MIARCHIVO_XML));

		// Visualizamos ahora los datos del documento XML creado
		System.out.println("------------ Leo el XML ---------");

		// Se crea Unmarshaller en el cotexto de la clase Libreria
		Unmarshaller unmars = context.createUnmarshaller();

		// Utilizamos el m�todo unmarshal, para obtener datos de un Reader
		WLibreria libreria3 = (WLibreria) unmars.unmarshal(new FileReader(MIARCHIVO_XML));
		Libreria libreria2 = (Libreria) unmars.unmarshal(new FileReader(MIARCHIVO_XML));

		// Recuperamos el array list y visualizamos
		System.out.println("Nombre de librería: " + libreria2.getNombre());
		System.out.println("Lugar de la librería: " + libreria2.getLugar());
		System.out.println("Libros de la librería: ");

		ArrayList<Libro> lista = libreria2.getListaLibro();
		for (Libro libro : lista) {
			System.out.println("\tTítulo del libro: " + libro.getNombre() + " , autora: " + libro.getAutor());

		}

	}
}