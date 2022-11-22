package sax;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import jaxb.Alumno;

public class PruebaSax1 {
	
	public static void main(String[] args)
			throws FileNotFoundException, IOException, SAXException, ParserConfigurationException {

		SAXParserFactory parserFactory = SAXParserFactory.newInstance();
		SAXParser parser = parserFactory.newSAXParser();
		XMLReader procesadorXML = parser.getXMLReader();

		GestionContenido gestor = new GestionContenido();
		procesadorXML.setContentHandler(gestor);
		InputSource fileXML = new InputSource("Alumno.xml");
		procesadorXML.parse(fileXML);
	}
}// fin PruebaSax1

class GestionContenido extends DefaultHandler {
	static List<Alumno> listaPersona = new ArrayList<>();
	static int i;
	static String nombre;
	public GestionContenido() {
		super();
	}

	public void startDocument() {
		System.out.println("Comienzo del Documento XML");
	}

	public void endDocument() {
		System.out.println("Final del Documento XML");
		
		for (Alumno persona : listaPersona) {
			System.out.println(persona.toString());
		}
	}

	public void startElement(String uri, String nombre, String nombreC, Attributes atts) {
		System.out.printf("\n\tIniciar Elemento: %s %n", nombreC);
	}

	public void endElement(String uri, String nombre, String nombreC) {
		System.out.printf("\n\tFin Elemento: %s %n", nombreC);
	}

	public void characters(char[] ch, int inicio, int longitud) throws SAXException {
		String car = new String(ch, inicio, longitud);
		// quitar saltos de linea
		car = car.replaceAll("[\t\n]", "");
		System.out.printf("\tCaracteres: %s %n", car);
	}
}
