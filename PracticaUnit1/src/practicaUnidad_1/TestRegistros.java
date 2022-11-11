package practicaUnidad_1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class TestRegistros {

	File fichero = new File("AleatorioAlumno.dat");

	@Test
	public void testLeerEscribirRegistroIni() throws IOException {
		resetear();
		RegistrosAleatorios.escribirRegistroIni(fichero, new Alumno(1, "Paco", "ASDF", "ASDF", 12));
		RegistrosAleatorios.escribirRegistroIni(fichero, new Alumno(2, "Juan", "ASDF", "ASDF", 12));

		Assert.assertEquals(true,
				new Alumno(2, "Juan", "ASDF", "ASDF", 12).equals(RegistrosAleatorios.leerRegistroIni(fichero)));
		resetear();
	}

	@Test
	public void testLeerEscribirRegistroFin() throws IOException {
		resetear();
		RegistrosAleatorios.escribirRegistroIni(fichero, new Alumno(4, "Manolo", "ASDF", "ASDF", 12));
		RegistrosAleatorios.escribirRegistroIni(fichero, new Alumno(5, "Francisco", "ASDF", "ASDF", 12));
		RegistrosAleatorios.escribirRegistroFin(fichero, new Alumno(6, "Sanson", "ASDF", "ASDF", 12));

		Assert.assertEquals(true,
				new Alumno(6, "Sanson", "ASDF", "ASDF", 12).equals(RegistrosAleatorios.leerRegistroFin(fichero)));
		resetear();
	}

	@Test
	public void testLeerEscribirRegistroPos() throws IOException {
		resetear();
		RegistrosAleatorios.escribirRegistroIni(fichero, new Alumno(4, "Manolo", "ASDF", "ASDF", 12));
		RegistrosAleatorios.escribirRegistroFin(fichero, new Alumno(5, "Francisco", "ASDF", "ASDF", 12));
		RegistrosAleatorios.escribirRegistroPos(fichero, new Alumno(6, "Sanson", "ASDF", "ASDF", 12), 1);

		Assert.assertEquals(true,
				new Alumno(6, "Sanson", "ASDF", "ASDF", 12).equals(RegistrosAleatorios.leerRegistroPos(fichero, 1)));
		
	}

	@Test
	public void testBuscarRegistro() throws IOException {
		resetear();
		RegistrosAleatorios.escribirRegistroIni(fichero, new Alumno(4, "Manolo", "ASDF", "ASDF", 12));
		RegistrosAleatorios.escribirRegistroFin(fichero, new Alumno(5, "Francisco", "ASDF", "ASDF", 12));
		RegistrosAleatorios.escribirRegistroPos(fichero, new Alumno(6, "Sanson", "ASDF", "ASDF", 12), 1);

		Assert.assertEquals(0,
				RegistrosAleatorios.buscarRegistro(fichero, new Alumno(4, "Manolo", "ASDF", "ASDF", 12)));

		// Comprobar que pasa si no existe
		Assert.assertEquals(-1,
				RegistrosAleatorios.buscarRegistro(fichero, new Alumno(3, "Manolo", "ASDF", "ASDF", 22)));

		Assert.assertEquals(2,
				RegistrosAleatorios.buscarRegistro(fichero, new Alumno(5, "Francisco", "ASDF", "ASDF", 12)));
		resetear();
	}

	@Test
	public void testBorrarRegistro() throws IOException {
		resetear();
		RegistrosAleatorios.escribirRegistroIni(fichero, new Alumno(4, "Manolo", "ASDF", "ASDF", 12));
		RegistrosAleatorios.escribirRegistroFin(fichero, new Alumno(5, "Francisco", "ASDF", "ASDF", 12));
		RegistrosAleatorios.escribirRegistroPos(fichero, new Alumno(6, "Sanson", "ASDF", "ASDF", 12), 1);

		// Comprobar que hay tres registros
		Assert.assertEquals(3, RegistrosAleatorios.numRegistros(fichero));

		// Comprobar el primer registro es el correcto
		Assert.assertEquals(true,
				new Alumno(4, "Manolo", "ASDF", "ASDF", 12).equals(RegistrosAleatorios.leerRegistroIni(fichero)));

		// Borrar el primer fichero
		Assert.assertEquals(true, RegistrosAleatorios.borrarRegistro(fichero, 0));

		// Comprobar que ahora hay un registro menos
		Assert.assertEquals(2, RegistrosAleatorios.numRegistros(fichero));

		// Comprobar si el registro que debe estar primero esta primero
		Assert.assertEquals(true,
				new Alumno(6, "Sanson", "ASDF", "ASDF", 12).equals(RegistrosAleatorios.leerRegistroIni(fichero)));
		resetear();
	}

	private void resetear() throws IOException {
		try (FileWriter fileWriter = new FileWriter("AleatorioAlumno.dat", false)) {

		}
		;
	}
}
