package XML;

import org.w3c.dom.*;

import jaxb.Alumno;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class CrearXML {
	public static void main(String args[]) throws IOException {
		File fichero = new File("ObjetoAlumno.dat");

		Alumno alumno;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		ObjectInputStream dataIS = new ObjectInputStream(new FileInputStream(fichero));

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			Document document = implementation.createDocument(null, "Alumnos", null);
			document.setXmlVersion("1.0");

			for (;;) {
				try {
					alumno = (Alumno) dataIS.readObject();

					Element raiz = document.createElement("persona");
					document.getDocumentElement().appendChild(raiz);

					// id
					CrearElemento("id", Integer.toString(alumno.getId()), raiz, document);
					// Nombre
					CrearElemento("nombre", alumno.getNombre(), raiz, document);
					// apellido
					CrearElemento("apellido", alumno.getApellido1(), raiz, document);
					// apellido2
					CrearElemento("apellido2", alumno.getApellido2(), raiz, document);
					// codigo postal
					CrearElemento("codigoPostal", Integer.toString(alumno.getCodigoPostal()), raiz, document);
				} catch (Exception eo) {
					break;
				}
			}

			Source source = new DOMSource(document);
			Result result = new StreamResult(new java.io.File("Alumno.xml"));
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, result);

			dataIS.close();
		} catch (Exception e) {
			System.out.println("Error");
		}

	}// fin de main

	// Inserci�n de los datos del empleado
	static void CrearElemento(String datoPersona, String valor, Element raiz, Document document) {
		Element elem = document.createElement(datoPersona);
		Text text = document.createTextNode(valor); // damos valor
		raiz.appendChild(elem); // pegamos el elemento hijo a la raiz
		elem.appendChild(text); // pegamos el valor
	}
}// fin de la clase