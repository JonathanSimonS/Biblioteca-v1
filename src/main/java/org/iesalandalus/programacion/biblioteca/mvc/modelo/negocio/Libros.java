package org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Libro;

/**
 * @author: Jonathan Simón Sánchez
 * 
 **/
public class Libros {

	// Atributos

	private ArrayList<Libro> coleccionLibros;

	// M.Constructor
	public Libros() {
		coleccionLibros = new ArrayList<Libro>();
	}

	// Métodos
	public List<Libro> get() {
		List<Libro> librosOrdenados = copiaProfundaLibros();
		librosOrdenados.sort(Comparator.comparing(Libro::getTitulo).thenComparing(Libro::getAutor));
		return librosOrdenados;
	}

	private List<Libro> copiaProfundaLibros() {
		ArrayList<Libro> copiaLibros = new ArrayList<>();
		for (int i = 0; i < getTamano(); i++) {
			Libro libro = coleccionLibros.get(i); // Con el método .get guardo al objeto en esa posicion
			copiaLibros.add(new Libro(libro)); // Voy copiando y almacenando los alumnos }
		}
		return copiaLibros;
	}

	public int getTamano() {
		return coleccionLibros.size();
	}

	public void insertar(Libro libro) throws OperationNotSupportedException {
		if (libro == null) {
			throw new NullPointerException("ERROR: No se puede insertar un libro nulo.");
		}
		if (coleccionLibros.contains(libro)) {
			throw new OperationNotSupportedException("ERROR: Ya existe un libro con ese título y autor.");
		} else {
			coleccionLibros.add(new Libro(libro));
		}
	}

	public Libro buscar(Libro libro) {
		if (libro == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar un libro nulo.");
		}
		if (!coleccionLibros.contains(libro)) {
			return null;
		} else {
			int indice = coleccionLibros.indexOf(libro);
			return new Libro(coleccionLibros.get(indice));
		}
	}

	public void borrar(Libro libro) throws OperationNotSupportedException {
		if (libro == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar un libro nulo.");
		}
		if (coleccionLibros.contains(libro)) {
			coleccionLibros.remove(libro);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún libro con ese título y autor.");
		}
	}

}
