package practicaUnidad_1;

import java.io.Serializable;
import java.util.Objects;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"id", "nombre", "apellido1", "apellido2" ,"codigoPostal"})

public class Alumno implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private int codigoPostal;

	public Alumno(int id, String nombre, String apellido1, String apellido2, int codigoPostal) {
		this.id = id;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.codigoPostal = codigoPostal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public int getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	@Override
	public String toString() {
		return "Alumno [id=" + id + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2
				+ ", codigoPostal=" + codigoPostal + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellido1, apellido2, codigoPostal, id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		return Objects.equals(apellido1, other.apellido1) && Objects.equals(apellido2, other.apellido2)
				&& codigoPostal == other.codigoPostal && id == other.id && Objects.equals(nombre, other.nombre);
	}
}
