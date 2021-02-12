package org.iesalandalus.programacion.biblioteca.mvc.controlador;

import java.time.LocalDate;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.Modelo;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.*;
import org.iesalandalus.programacion.biblioteca.mvc.vista.Vista;

/**
 * @author: Jonathan Simón Sánchez
 * 
 **/

public class Controlador {

	// Atributos
	private Modelo modelo;
	private Vista vista;

	// M.Constructor
	public Controlador(Modelo modelo, Vista vista) {
		if (modelo == null) {
			throw new IllegalArgumentException("Ha habido un error, el modelo no puede ser nulo.");
		}
		if (vista == null) {
			throw new IllegalArgumentException("Ha habido un error, la vista no puede ser nula.");
		}
		this.modelo = new Modelo();
		this.vista = new Vista();
		this.vista.setControlador(this);
	}

	// Métodos
	public void comenzar() {
		vista.comenzar();
	}

	public void terminar() {
		System.out.println("Muchas gracias por su tiempo.");
	}

	public void insertar(Alumno alumno) throws OperationNotSupportedException {
		modelo.insertar(alumno);
	}

	public void insertar(Libro libro) throws OperationNotSupportedException {
		modelo.insertar(libro);
	}

	public void prestar(Prestamo prestamo) throws OperationNotSupportedException {
		modelo.prestar(prestamo);
	}

	public void devolver(Prestamo prestamo, LocalDate fecha) throws OperationNotSupportedException {
		modelo.devolver(prestamo, fecha);
	}

	public Alumno buscar(Alumno alumno) {
		return modelo.buscar(alumno);
	}

	public Libro buscar(Libro libro) {
		return modelo.buscar(libro);
	}

	public Prestamo buscar(Prestamo prestamo) {
		return modelo.buscar(prestamo);
	}

	public void borrar(Alumno alumno) throws OperationNotSupportedException {
		modelo.borrar(alumno);
	}

	public void borrar(Libro libro) throws OperationNotSupportedException {
		modelo.borrar(libro);
	}

	public void borrar(Prestamo prestamo) throws OperationNotSupportedException {
		modelo.borrar(prestamo);
	}

	public Alumno[] getAlumnos() {
		return modelo.getAlumnos();
	}

	public Libro[] getLibros() {
		return modelo.getLibros();
	}

	public Prestamo[] getPrestamos() {
		return modelo.getPrestamos();
	}

	public Prestamo[] getPrestamos(Alumno alumno) {
		return modelo.getPrestamos(alumno);
	}

	public Prestamo[] getPrestamos(Libro libro) {
		return modelo.getPrestamos(libro);
	}

	public Prestamo[] getPrestamos(LocalDate fecha) {
		return modelo.getPrestamos(fecha);
	}
}
