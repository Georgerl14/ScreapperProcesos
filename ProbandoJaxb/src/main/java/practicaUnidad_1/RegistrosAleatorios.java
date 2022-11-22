package practicaUnidad_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import jaxb.Alumno;

public class RegistrosAleatorios {
	private static int espacio = 92;

	public static int numRegistros(File fichero) {
		try {
			int cantidad = (int) fichero.length();
			return cantidad / espacio;
		} catch (Exception e) {
			return -1;
		}
	}

	public static Alumno leerRegistroIni(File fichero) {
		try (RandomAccessFile file = new RandomAccessFile(fichero, "r");) {
			int posicion = 0;
			return leerFichero(file, posicion);
		} catch (Exception e) {
			return null;
		}
	}

	public static Alumno leerRegistroFin(File fichero) {
		try (RandomAccessFile file = new RandomAccessFile(fichero, "r");) {
			int posicion = (int) (fichero.length() - espacio);
			return leerFichero(file, posicion);
		} catch (Exception e) {
			return null;
		}
	}

	public static Alumno leerRegistroPos(File fichero, int posicion) throws FileNotFoundException, IOException {
		try (RandomAccessFile file = new RandomAccessFile(fichero, "r");) {
			if (posicion * espacio <= file.length()) {
				return leerFichero(file, posicion * espacio);
			} else {
				System.err.println("Error: Posicion no posible");
				return null;
			}
		}
	}

	public static boolean escribirRegistroIni(File fichero, Alumno alumno) throws IOException {
		RandomAccessFile file = new RandomAccessFile(fichero, "rw");
		desplazarDatos(fichero, file, 0);
		file.seek(0);
		escribirFichero(alumno, file);
		return true;
	}

	public static boolean escribirRegistroPos(File fichero, Alumno alumno, int posicion)
			throws FileNotFoundException, IOException {
		try (RandomAccessFile file = new RandomAccessFile(fichero, "rw");) {
			if (posicion * espacio <= file.length()) {
				desplazarDatos(fichero, file, posicion * espacio);
				file.seek(posicion * espacio);
				escribirFichero(alumno, file);
				return true;
			} else {
				System.err.println("Error: Posicion no posible");
				System.err.println("Posicion: " + posicion);
				System.err.println(alumno.toString());
				return false;
			}
		}
	}

	public static boolean escribirRegistroFin(File fichero, Alumno alumno) throws IOException {
		try (RandomAccessFile file = new RandomAccessFile(fichero, "rw");) {
			file.seek(file.length());
			escribirFichero(alumno, file);
			return true;
		} catch (FileNotFoundException e) {
			return false;
		}
	}

	public static boolean borrarRegistro(File fichero, int posicion) throws IOException {
		try (RandomAccessFile file = new RandomAccessFile(fichero, "rw");) {
			if (posicion * espacio <= file.length()) {
				desplazarDatosInvert(fichero, file, posicion * espacio);
				file.setLength(file.length() - espacio);
				return true;
			} else {
				System.err.println("Error: Posicion no posible");
				return false;
			}
		}
	}

	public static int buscarRegistro(File fichero, Alumno alumno) throws FileNotFoundException, IOException {
		try (RandomAccessFile file = new RandomAccessFile(fichero, "r");) {
			int posicion = 0;
			do {
				if (alumno.equals(leerFichero(file, posicion))) {
					return posicion / espacio;
				}
				posicion += espacio;
			} while (file.getFilePointer() != file.length());
		}
		return -1;
	}

	public static void desplazarDatos(File fichero, RandomAccessFile file, int maximo) throws IOException {
		for (int i = (int) file.length(); i != maximo; i -= espacio) {
			Alumno alumnoAnterior = leerFichero(file, (i - espacio)); // Cogemos al alumno posterior
			file.seek(i); // Vamos a la posicion anterior
			escribirFichero(alumnoAnterior, file); // Escribimos en esa posicion
		}
	}

	public static void desplazarDatosInvert(File fichero, RandomAccessFile file, int posicion) throws IOException {
		for (int i = posicion; i < file.length() - espacio; i += espacio) {
			Alumno alumnoAnterior = leerFichero(file, (i + espacio));
			file.seek(i);
			escribirFichero(alumnoAnterior, file);
		}
	}

	public static void escribirFichero(Alumno alumno, RandomAccessFile file) throws IOException {
		StringBuffer buffer = null;

		file.writeInt(alumno.getId());

		buffer = new StringBuffer(alumno.getNombre());
		buffer.setLength(12);
		file.writeChars(buffer.toString());

		buffer = new StringBuffer(alumno.getApellido1());
		buffer.setLength(14);
		file.writeChars(buffer.toString());

		buffer = new StringBuffer(alumno.getApellido2());
		buffer.setLength(16);
		file.writeChars(buffer.toString());

		file.writeInt(alumno.getCodigoPostal());
	}

	public static Alumno leerFichero(RandomAccessFile file, int posicion) throws IOException {
		char nombre[] = new char[12], apellido[] = new char[14], apellido2[] = new char[16];

		file.seek(posicion);
		int id = file.readInt();

		for (int i = 0; i < nombre.length; i++) {
			char aux = file.readChar();
			nombre[i] = aux;
		}

		for (int i = 0; i < apellido.length; i++) {
			char aux = file.readChar();
			apellido[i] = aux;
		}

		for (int i = 0; i < apellido2.length; i++) {
			char aux = file.readChar();
			apellido2[i] = aux;
		}

		int cp = file.readInt();

		return new Alumno(id, new String(nombre).trim(), new String(apellido).trim(), new String(apellido2).trim(), cp);
	}
}
