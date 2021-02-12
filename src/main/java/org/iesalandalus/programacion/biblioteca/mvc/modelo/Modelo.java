package org.iesalandalus.programacion.biblioteca.mvc.modelo;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.*;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio.*;

/**
 * En esta clase, cada método hará una llamada al método homólogo del objeto
 * adecuado.
 * 
 * @author: Jonathan Simón Sánchez
 * 
 **/
public class Modelo {

	// Atributos y constantes
	private static final int CAPACIDAD = 43;
	private Alumnos alumnos;
	private Libros libros;
	private Prestamos prestamos;

	// M.Constructor
	public Modelo() {
		alumnos = new Alumnos(CAPACIDAD);
		libros = new Libros(CAPACIDAD);
		prestamos = new Prestamos(CAPACIDAD);
	}

	// Métodos
	public void insertar(Alumno alumno) throws OperationNotSupportedException {
		alumnos.insertar(alumno);
	}

	public void insertar(Libro libro) throws OperationNotSupportedException {
		libros.insertar(libro);
	}

	public void prestar(Prestamo prestamo) throws OperationNotSupportedException {
		if (prestamo == null) {
			throw new NullPointerException("ERROR: No se puede prestar un préstamo nulo.");
		}
		if (alumnos.buscar(prestamo.getAlumno()) == null) {
			throw new OperationNotSupportedException("ERROR: No existe el alumno del préstamo.");
		} 
		if (libros.buscar(prestamo.getLibro()) == null) {
			throw new OperationNotSupportedException("ERROR: No existe el libro del préstamo.");
		} else {
		prestamos.prestar(prestamo);
		}
	}

	public void devolver(Prestamo prestamo, LocalDate fecha) throws OperationNotSupportedException {

		if(prestamos.buscar(prestamo)==null) {
			throw new OperationNotSupportedException("ERROR: No se puede devolver un préstamo no prestado.");
		}
		
		prestamos.devolver(new Prestamo(prestamo), fecha);
	}

	public Alumno buscar(Alumno alumno) {
		return alumnos.buscar(alumno);
	}

	public Libro buscar(Libro libro) {
		return libros.buscar(libro);

	}

	public Prestamo buscar(Prestamo prestamo) {
		return prestamos.buscar(prestamo);
	}

	public void borrar(Alumno alumno) throws OperationNotSupportedException {
		alumnos.borrar(alumno);
	}

	public void borrar(Libro libro) throws OperationNotSupportedException {
		libros.borrar(libro);
	}

	public void borrar(Prestamo prestamo) throws OperationNotSupportedException {
		prestamos.borrar(prestamo);
	}

	public Alumno[] getAlumnos() {
		return alumnos.get();
	}

	public Libro[] getLibros() {
		return libros.get();
	}

	public Prestamo[] getPrestamos() {
		return prestamos.get();
	}

	public Prestamo[] getPrestamos(Alumno alumno) {
		return prestamos.get(alumno);
	}

	public Prestamo[] getPrestamos(Libro libro) {
		return prestamos.get(libro);
	}

	public Prestamo[] getPrestamos(LocalDate fecha) {
		return prestamos.get(fecha);
	}

}
