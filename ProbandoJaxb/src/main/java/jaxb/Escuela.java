package jaxb;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import practicaUnidad_1.Alumno;

@XmlRootElement
@XmlType(propOrder = {"listaAlumno"})
public class Escuela {
	private ArrayList<Alumno> listaAlumnos;

    public Escuela(ArrayList<Alumno> listaAlumnos) {
		super();
		this.listaAlumnos = listaAlumnos;
	}
    public Escuela(){}

    //Wrapper, envoltura alrededor la representaci�n XML
    @XmlElementWrapper(name = "MiListaAlumnos")
    @XmlElement(name = "alumno")
    
    public ArrayList<Alumno> getListaAlumno() {
        return listaAlumnos;    }
 
    public void setListaAlumno(ArrayList<Alumno> listaAlumnos) {
		this.listaAlumnos = listaAlumnos;
	}
}
