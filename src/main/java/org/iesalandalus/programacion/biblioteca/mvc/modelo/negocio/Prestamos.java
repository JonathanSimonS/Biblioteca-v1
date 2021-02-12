package org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio;

import java.time.LocalDate;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.*;

/**
 * @author: Jonathan Simón Sánchez
 **/
public class Prestamos {

	// Atributos y constantes
	private int capacidad;
	private int tamano;
	private Prestamo coleccionPrestamos[];

	// M. Constructores
	public Prestamos(int capacidad) {
		if (capacidad <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		this.capacidad = capacidad;
		coleccionPrestamos = new Prestamo[capacidad];
		tamano = 0;
	}

	// Métodos
	public Prestamo[] get() {
		return copiaProfundaPrestamos();
	}

	private Prestamo[] copiaProfundaPrestamos() {
		Prestamo[] copiaPrestamos = new Prestamo[capacidad];

		for (int i = 0; !tamanoSuperado(i); i++) {
			copiaPrestamos[i] = new Prestamo(coleccionPrestamos[i]);
		}
		return copiaPrestamos;
	}

	/**
	 * Crea un array de tipo Prestamo de alumnos y devuelve una copia
	 * 
	 * @param: alumno
	 * @return: array de alumnos
	 **/
	public Prestamo[] get(Alumno alumno) {
		if (alumno == null) {
			throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
		}

		Prestamo[] prestamosPorAlumnos = new Prestamo[capacidad]; // Array de préstamo que contendrá alumnos

		
		for (int i = 0; !tamanoSuperado(i); i++) {
			if (coleccionPrestamos[i].getAlumno().equals(alumno)) {
				prestamosPorAlumnos[i] = new Prestamo(coleccionPrestamos[i]);
			}
		}

		return prestamosPorAlumnos;
	}

	/**
	 * Crea un array de tipo Prestamo de libros y devuelve una copia
	 * 
	 * @param: libro
	 * @return: array de libros
	 **/
	public Prestamo[] get(Libro libro) {
		if (libro == null) {
			throw new NullPointerException("ERROR: El libro no puede ser nulo.");
		}
		Prestamo[] prestamoPorLibros = new Prestamo[capacidad];
		int j = 0;
		for (int i = 0; !tamanoSuperado(i); i++) {
			if (coleccionPrestamos[i].getLibro().equals(libro)) {
				prestamoPorLibros[j] = new Prestamo(coleccionPrestamos[i]);
				j++;
			}
		}
		return prestamoPorLibros;
	}

	/**
	 * Crea un array de tipo Prestamo de fechas y devuelve una copia
	 * 
	 * @param: fecha
	 * @return: array de fechas
	 **/
	public Prestamo[] get(LocalDate fechaPrestamo) {
		if (fechaPrestamo == null) {
			throw new NullPointerException("ERROR: La fecha no puede ser nula.");
		}
		Prestamo[] prestamosMes = new Prestamo[capacidad];
		int j = 0;
		for (int i = 0; !tamanoSuperado(i); i++) {
			if (mismoMes(coleccionPrestamos[i].getFechaPrestamo(), fechaPrestamo)) {
				prestamosMes[j] = new Prestamo(coleccionPrestamos[i]);
				j++;
			}
		}
		return prestamosMes;
	}

	/**
	 * Compara si dos fechas son del mismo mes
	 * 
	 * @param: dos fechas
	 * @return: true si las dos fechas son del mismo mes, false si no lo son
	 **/
	public boolean mismoMes(LocalDate fecha1, LocalDate fecha2) {
		return (fecha1.getMonth().equals(fecha2.getMonth()) && (fecha1.getYear() == fecha2.getYear()));
	} // Comtemplo la posibilidad de que el año también sea diferente

	public int getCapacidad() {
		return capacidad;
	}

	public int getTamano() {
		return tamano;
	}

	/**
	 * Guardará un prestamo efectuado
	 * 
	 * @param: préstamo
	 * @throws OperationNotSupportedException
	 **/
	public void prestar(Prestamo prestamo) throws OperationNotSupportedException {
		if (prestamo == null) {
			throw new NullPointerException("ERROR: No se puede prestar un préstamo nulo.");
		}
		int indice = buscarIndice(prestamo);

		if (capacidadSuperado(indice)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más préstamos.");
		}
		if (tamanoSuperado(indice)) {
			coleccionPrestamos[indice] = new Prestamo(prestamo);
			tamano++;
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un préstamo igual.");
		}

	}

	private int buscarIndice(Prestamo prestamo) {

		// Declaro las variables contador y centinela
		int indice = 0;
		boolean prestamoEncontrado = false;

		while (!tamanoSuperado(indice) && !prestamoEncontrado) {
			if (coleccionPrestamos[indice].equals(prestamo)) {
				prestamoEncontrado = true;
			} else {
				indice++;
			}
		}
		return indice;
	}

	private boolean tamanoSuperado(int i) {
		return tamano <= i;
	}

	private boolean capacidadSuperado(int i) {
		return capacidad <= i;
	}

	/**
	 * Devolverá libro por alumno
	 * 
	 * @param: préstamo y fecha de devolución
	 * @throws OperationNotSupportedException
	 **/
	public void devolver(Prestamo prestamo, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (prestamo == null) {
			throw new NullPointerException("ERROR: No se puede devolver un préstamo nulo.");
		}
		if (fechaDevolucion == null) {
			throw new NullPointerException("ERROR: No se puede devolver en una fecha nula.");
		}
		int indice = buscarIndice(prestamo);
		if (!tamanoSuperado(indice)) {
			coleccionPrestamos[indice].devolver(fechaDevolucion);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún préstamo igual.");
		}
	}

	public Prestamo buscar(Prestamo prestamo) {
		if (prestamo == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar un préstamo nulo.");
		}
		int indice = buscarIndice(prestamo);
		if (tamanoSuperado(indice)) {
			return null;
		} else {
			Prestamo prestamoBuscado = new Prestamo(coleccionPrestamos[indice]);
			return prestamoBuscado;
		}

	}

	public void borrar(Prestamo prestamo) throws OperationNotSupportedException {
		if (prestamo == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar un préstamo nulo.");
		}
		int indice = buscarIndice(prestamo);

		if (tamanoSuperado(indice)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún préstamo igual.");
		} else {
			desplazarUnaPosicionHaciaIzquierda(indice);
		}
	}

	/**
	 * Borrará la posición pasada por parámetro, esa posición se reemplazará por la
	 * siguiente Todo el array quedará compactado a la izquierda hasta que se supere
	 * el tamaño
	 * 
	 * @param indice de un préstamo
	 * 
	 */
	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		int i;
		for (i = indice; !tamanoSuperado(i); i++) {
			coleccionPrestamos[i] = coleccionPrestamos[i + 1];
		}
		coleccionPrestamos[i] = null;
		tamano--;
	}

}
