package com.razvan.ProbandoJaxb;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class WLibreria {
	private ArrayList<Libreria> listaLibreria;
   
    public WLibreria(ArrayList<Libro> listaLibro) {
		super();
	}
    public WLibreria(){}

    //Wrapper, envoltura alrededor la representaci�n XML
    @XmlElement(name = "Libreria")
    
    public ArrayList<Libreria> getListaLibreria() {
        return listaLibreria;    }
 
    public void setListaLibreria(ArrayList<Libreria> milibreria) {
		this.listaLibreria = milibreria;
	}
}
