package dominio;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Objects;

public class Dominio {
	private String nombre;
	private URL url;
	private int estado;
	private HashSet<String> listaUrls;
	
	public Dominio(String nombre, URL url) {
		this.nombre = nombre;
		this.url = url;
		this.estado = 0;
		listaUrls = new HashSet<String>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	public HashSet<String> getListaUrls() {
		return listaUrls;
	}

	public void setListaUrls(HashSet<String> listaUrls) {
		this.listaUrls = listaUrls;
	}

	public void verUrl() {
		try {
			new ProcessBuilder("cmd.exe", "/c", "start", getUrl().toString()).start();
		} catch (IOException e) {
			System.out.println("Error: No se pudo abrir la pagina web");
			e.printStackTrace();
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dominio other = (Dominio) obj;
		return Objects.equals(nombre, other.nombre) || Objects.equals(url, other.url);
	}

}
