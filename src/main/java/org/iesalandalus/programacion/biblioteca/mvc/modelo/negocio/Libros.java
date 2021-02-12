package org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Libro;

/**
 * @author: Jonathan Simón Sánchez
 * 
 **/
public class Libros {

	// Atributos
	private int capacidad;
	private int tamano;

	private Libro coleccionLibros[]; // Inicializado array de tipo Libro

	// M.Constructor
	public Libros(int capacidad) {
		if (capacidad <= 0) 
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		
		this.capacidad = capacidad;
		coleccionLibros = new Libro[capacidad]; // Inicializo el array a la capacidad pasada por parámetro
		tamano = 0;
	}

	// Métodos
	public Libro[] get() {
		return copiaProfundaLibros();
	}

	private Libro[] copiaProfundaLibros() {
		Libro[] copiaLibros = new Libro[capacidad]; // Hago una copia del array con la misma capacidad
		for (int i = 0; !tamanoSuperado(i); i++) {
			copiaLibros[i] = new Libro(coleccionLibros[i]);
		}
		return copiaLibros;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public int getTamano() {
		return tamano;
	}

	/**
	 * Devolverá la posición de un libro en el array
	 * 
	 * @param libro
	 * @return indice
	 * @throws OperationNotSupportedException
	 * 
	 */
	private int buscarIndice(Libro libro)   {

		// Declaro el 'contador' y el 'centinela'
		int indice = 0;
		boolean libroEncontrado = false;
		while (!tamanoSuperado(indice) && !libroEncontrado) {
			if (coleccionLibros[indice].equals(libro)) { // Recorreremos el array en busca de un libro igual
				libroEncontrado = true; // Si no hay, el índice se irá sumando y llegará hasta que el tamaño sea
										// superado
			} else {
				indice++;
			}
		}
		return indice;
	}

	public void insertar(Libro libro) throws OperationNotSupportedException {

		if (libro == null) {
			throw new NullPointerException("ERROR: No se puede insertar un libro nulo.");
		}

		int indice = buscarIndice(libro); // Asignamos a índice el valor del alumno pasado por parámetro

		if (capacidadSuperada(indice)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más libros.");
		}

		if (tamanoSuperado(indice)) { // Si el tamaño se supera, se crea un nuevo alumno en ese índice
			coleccionLibros[indice] = new Libro(libro);
			
			tamano++;
		} else { // Si el tamaño no es superado, es que ya hay un alumno igual
			throw new OperationNotSupportedException("ERROR: Ya existe un libro con ese título y autor.");
		}

	}

	private boolean capacidadSuperada(int i) {
		return capacidad <= i;
	} 

	private boolean tamanoSuperado(int i) {
		return tamano <= i;
	}

	public Libro buscar(Libro libro)   {

		if (libro == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar un libro nulo.");
		}

		int indice = buscarIndice(libro);

		if (tamanoSuperado(indice)) {
			return null;
		} else {
			coleccionLibros[indice] = new Libro(libro);
		}
		return coleccionLibros[indice];
	}

	public void borrar(Libro libro) throws OperationNotSupportedException {
		if (libro == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar un libro nulo.");
		}

		int indice = buscarIndice(libro);

		if (tamanoSuperado(indice)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún libro con ese título y autor.");
		} else {
			desplazarUnaPosicionHaciaIzquierda(indice);
		}
	}

	/**
	 * Borrará la posición pasada por parámetro, esa posición se reemplazará por la siguiente
	 * Todo el array quedará compactado a la izquierda hasta que se supere el tamaño
	 * 
	 * @param indice de un libro
	 * 
	 */
	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		int i;
		for (i = indice; !tamanoSuperado(i); i++) {
			coleccionLibros[i] = coleccionLibros[i + 1];
		}
		coleccionLibros[i] = null;
		tamano--;
	}

}
