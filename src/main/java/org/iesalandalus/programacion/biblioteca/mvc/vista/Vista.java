package org.iesalandalus.programacion.biblioteca.mvc.vista;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.controlador.Controlador;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.*;

/**
 * @author: Jonathan Simón Sánchez
 * 
 **/
public class Vista {

	// Atributos
	private Controlador controlador;

	// M.Constructor
	public Vista() {
		Opcion.setVista(this);
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void comenzar() {
		int opcion;
		do {
			Consola.mostrarMenu();
			opcion = Consola.elegirOpcion();
			Opcion opcionElegida = Opcion.getOpcionSegunOrdinal(opcion);
			opcionElegida.ejecutar();
		} while (opcion != Opcion.SALIR.ordinal());
	}

	public void terminar() {
		controlador.terminar();
	}

	// Métodos Alumno
	public void insertarAlumno() {
		try {
			controlador.insertar(Consola.leerAlumno());
			System.out.println("Alumno insertado.");
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarAlumno() {
		Alumno alumno;
		try {
			alumno = controlador.buscar(Consola.leerAlumnoFicticio());
			String mensaje = (alumno != null) ? alumno.toString() : "No existe ese alumno.";
			System.out.println(mensaje);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarAlumno() {
		try {
			controlador.borrar(Consola.leerAlumnoFicticio());
			System.out.println("Alumno borrado.");
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarAlumnos() {
		Alumno[] alumnos = controlador.getAlumnos();
		if (alumnos[0] != null) {
			for (Alumno alumno : alumnos) {
				if (alumno != null) 
					System.out.println(alumno);
			}
		} else {
			System.out.println("No hay alumnos para mostrar.");
		}
	}

	// Métodos Libro
	public void insertarLibro() {
		try {
			controlador.insertar(Consola.leerLibro());
			System.out.println("Libro insertado.");
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarLibro() {
		Libro libro;
		try {
			libro = controlador.buscar(Consola.leerLibroFicticio());
			String mensaje = (libro != null) ? libro.toString() : "No existe ese libro.";
			System.out.println(mensaje);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarLibro() {
		try {
			controlador.borrar(Consola.leerLibroFicticio());
			System.out.println("Libro borrado.");
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarLibros() {
		Libro[] libros = controlador.getLibros();
		if (libros[0] != null) {
			for (Libro libro : libros) {
				if (libro != null) 
					System.out.println(libro);
			}
		} else {
			System.out.println("No hay libros para mostrar.");
		}
	}

	public void prestarLibro() {
		try {
			controlador.prestar(Consola.leerPrestamo());
			System.out.println("Libro Prestado.");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	public void devolverLibro() {
		try {
			controlador.devolver(Consola.leerPrestamo(), LocalDate.now() );
			System.out.println("Libro devuelto.");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	// Métodos Prestamo

	public void buscarPrestamo() {
		Prestamo prestamo;
		try {
			prestamo = controlador.buscar(Consola.leerPrestamoFicticio());
			String mensaje = (prestamo != null) ? prestamo.toString() : "No existe ese prestamo.";
			System.out.println(mensaje);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarPrestamo() {
		try {
			controlador.borrar(Consola.leerPrestamoFicticio());
			System.out.println("Préstamo borrado.");
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarPrestamos() {
		Prestamo[] prestamos = controlador.getPrestamos();
		if (prestamos[0] != null) {
			for (Prestamo prestamo : prestamos) {
				if (prestamo != null) 
					System.out.println(prestamo);
			}
		} else {
			System.out.println("No hay prestamos para mostrar.");
		}
	}

	public void listarPrestamosAlumno() {
		Prestamo[] prestamos = controlador.getPrestamos(Consola.leerAlumnoFicticio());
		if (prestamos[0] != null) {
			for (Prestamo prestamo : prestamos) {
				if (prestamo != null) 
					System.out.println(prestamo);
			}
		} else {
			System.out.println("No hay prestamos para mostrar.");
		}
	}

	public void listarPrestamosLibro() {
		Prestamo[] prestamos = controlador.getPrestamos(Consola.leerLibroFicticio());
		if (prestamos[0] != null) {
			for (Prestamo prestamo : prestamos) {
				if (prestamo != null) 
					System.out.println(prestamo);
			}
		} else {
			System.out.println("No hay prestamos para mostrar.");
		}
	}

	public void listarPrestamosFecha() {
		Prestamo[] prestamos = controlador.getPrestamos(Consola.leerPrestamo().getFechaPrestamo());
		if (prestamos[0] != null) {
			for (Prestamo prestamo : prestamos) {
				if (prestamo != null) 
					System.out.println(prestamo);
			}
		} else {
			System.out.println("No hay prestamos para mostrar.");
		}
	}

}
