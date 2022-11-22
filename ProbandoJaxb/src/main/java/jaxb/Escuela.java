package jaxb;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Escuela")
@XmlType(propOrder = {"listaAlumno"})

public class Escuela {
	private ArrayList<Alumno> listaAlumnos;

    public Escuela(ArrayList<Alumno> listaAlumnos) {
		super();
		this.listaAlumnos = listaAlumnos;
	}
    public Escuela(){}

    @XmlElementWrapper(name = "Clase")
    @XmlElement(name = "Alumno")
    
    public ArrayList<Alumno> getListaAlumno() {
        return listaAlumnos;    
    }
 
    public void setListaAlumno(ArrayList<Alumno> listaAlumnos) {
		this.listaAlumnos = listaAlumnos;
	}
}
