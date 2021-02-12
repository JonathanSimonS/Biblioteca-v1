package org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Alumno;

/**
 * @author: Jonathan Simón Sánchez
 * 
 **/
public class Alumnos {

	// Atributos
	private int capacidad; //
	private int tamano;

	private Alumno coleccionAlumnos[];

	// M.Constructor
	public Alumnos(int capacidad) {

		if (capacidad <= 0)
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");

		this.capacidad = capacidad;
		coleccionAlumnos = new Alumno[capacidad]; // Alumnos totales que se registrarán
		tamano = 0; // El tamaño sería de 0, pues no hay ningún alumno creado
	}

	// Métodos
	/**
	 * @return: copia profunda de coleccionAlumnos
	 **/
	public Alumno[] get() {
		return copiaProfundaAlumnos();
	}

	private Alumno[] copiaProfundaAlumnos() {
		Alumno[] copiaAlumnos = new Alumno[capacidad]; // Creo una copia profunda de Alumno
		for (int i = 0; !tamanoSuperado(i); i++) {
			copiaAlumnos[i] = new Alumno(coleccionAlumnos[i]); // Voy copiando y almacenando los alumnos
		}
		return copiaAlumnos;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public int getTamano() {
		return tamano;
	}

	/**
	 * Buscará en el array coleccionAlumnos un alumno que coincida con la posición
	 * del índice, si no lo encuentra, el indice devuelto superará el tamaño del
	 * array en 1
	 * 
	 * @param alumno
	 * @return indice
	 */
	private int buscarIndice(Alumno alumno) {
		int indice = 0;
		boolean alumnoEncontrado = false;
		while (!tamanoSuperado(indice) && !alumnoEncontrado) {
			if (coleccionAlumnos[indice].equals(alumno)) { // Recorreremos el array en busca de un alumno igual
				alumnoEncontrado = true; // Si no hay, el índice se irá sumando y llegará hasta que el tamaño sea
											// superado
			} else {
				indice++;
			}
		}
		return indice;
	}

	public void insertar(Alumno alumno) throws OperationNotSupportedException {
		if (alumno == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alumno nulo.");
		}

		int indice = buscarIndice(alumno); // Asignamos a índice el valor del alumno pasado por parámetro

		if (capacidadSuperada(indice)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más alumnos.");
		}
		if (tamanoSuperado(indice)) { // Si el tamaño se supera, se crea un nuevo alumno en ese índice
			coleccionAlumnos[indice] = new Alumno(alumno);
			
			tamano++;
		} else { // Si el tamaño no es superado, es que ya hay un alumno igual
			throw new OperationNotSupportedException("ERROR: Ya existe un alumno con ese correo.");
		}
	}

	/**
	 * @return true, si la capacidad ha sido superada, false si no
	 */
	private boolean capacidadSuperada(int i) {
		return capacidad <= i;
	}

	/**
	 * @return true, si el tamaño ha sido superado, false si no
	 */
	private boolean tamanoSuperado(int i) {
		return tamano <= i;
	}

	public Alumno buscar(Alumno alumno) {
		if (alumno == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar un alumno nulo.");
		}

		int indice = buscarIndice(alumno); // Buscamos al alumno por su índice

		if (tamanoSuperado(indice)) { // Si el tamaño se supera, es porque no se ha encontrado ningún alumno igual
			return null;
		} else {
			coleccionAlumnos[indice] = new Alumno(alumno);
		}

		return coleccionAlumnos[indice];

	}

	public void borrar(Alumno alumno) throws OperationNotSupportedException {
		if (alumno == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar un alumno nulo.");
		}

		int indice = buscarIndice(alumno);

		if (tamanoSuperado(indice)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alumno con ese correo.");
		} else {
			desplazarUnaPosicionHaciaIzquierda(indice);
		}
	}
	
	/**
	 * Borrará la posición pasada por parámetro, esa posición se reemplazará por la siguiente
	 * Todo el array quedará compactado a la izquierda hasta que se supere el tamaño
	 * 
	 * @param indice de un préstamo
	 * 
	 */
	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		
		int i;
		for (i = indice; !tamanoSuperado(i); i++) {
			coleccionAlumnos[i] = coleccionAlumnos[i + 1];
		}
		coleccionAlumnos[i] = null;
		tamano--;
		
	}
}
