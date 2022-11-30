package visual;

public class Menu {
	
	public static void mostrarMenu(int longitudLista) {
		System.out.println(" -  Manejar dominios");
		System.out.println("(1) Insertar dominio");
		if (longitudLista != 0) {
			System.out.println("(2) Listar dominio");
			System.out.println("(3) Eliminar dominio");
			System.out.println("(4) Analizar dominio");
			System.out.println("(5)          todos los dominios");
		}
		System.out.println("(0) Cerrar");
	}
	
	public static void mostrarSegundoMenu() {
		System.out.println("Manejar analisis de dominio\n");
		System.out.println("(1) Ver url navegador");
		System.out.println("(2) Ver estado actual");
		System.out.println("(3) Configurar palabras a buscar");
		System.out.println("(4) Scrappear");
		System.out.println("(5) Ver archivo resumen");
		System.out.println("(0) Salir");
	}
	
	public static void mostrarTercerMenu(int longitudLista) {
		System.out.println("Manejar terminos \n");
		System.out.println("(1) Introducir termino");
		if (longitudLista != 0) {
			System.out.println("(2) Eliminar termino");
			System.out.println("(3) Listar termino");
		}
		System.out.println("(0) Salir");
	}
	
}
