package dom;

import java.io.File;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class LecturaEmpleadoXml {
	public static void main(String[] args) {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File("Alumno.xml"));
			document.getDocumentElement().normalize();

			System.out.printf("Elemento raiz: %s %n", document.getDocumentElement().getNodeName());

			// crea una lista con todos los nodos empleado
			NodeList alumnos = document.getElementsByTagName("persona");
			System.out.printf("Nodos alumnos a recorrer: %d %n", alumnos.getLength());

			// recorrer la lista
			for (int i = 0; i < alumnos.getLength(); i++) {
				Node alumn = alumnos.item(i); // obtener un nodo empleado
				if (alumn.getNodeType() == Node.ELEMENT_NODE) {// tipo de nodo
					// obtener los elementos del nodo
					Element elemento = (Element) alumn;
					System.out.printf("\n * ID = %s %n", 
							elemento.getElementsByTagName("id").item(0).getTextContent());
					
					System.out.printf(" * Nombre = %s %n",
							elemento.getElementsByTagName("nombre").item(0).getTextContent());
					
					System.out.printf(" * Apellido = %s %n",
							elemento.getElementsByTagName("apellido").item(0).getTextContent());
					
					System.out.printf(" * Apellido 2 = %s %n",
							elemento.getElementsByTagName("apellido2").item(0).getTextContent());
					
					System.out.printf(" * Codigo Postal = %s %n",
							elemento.getElementsByTagName("codigoPostal").item(0).getTextContent());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// fin de main
}// fin de la clase
