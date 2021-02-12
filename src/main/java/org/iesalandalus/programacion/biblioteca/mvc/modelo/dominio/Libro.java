package org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio;

/**
 * @author: Jonathan Simón Sánchez
 * 
 **/
public class Libro {

	// Atributos y constantes
	private static final int PAGINAS_PARA_RECOMPENSA = 25;
	private static final float PUNTOS_PREMIO = 0.5f;

	private String titulo;
	private String autor;
	private int numPaginas;

	// Constructores

	public Libro(String titulo, String autor, int numPaginas) {
		setTitulo(titulo);
		setAutor(autor);
		setNumPaginas(numPaginas);
	}

	public Libro(Libro e) {
		if (e == null) {
			throw new NullPointerException("ERROR: No es posible copiar un libro nulo.");
		}
		setTitulo(e.getTitulo());
		setAutor(e.getAutor());
		setNumPaginas(e.getNumPaginas());
	}

	// Métodos

	/**
	 * Va a devolver un libro correcto y se usará para búsquedas y borrados
	 * 
	 * @param: título y autor return: un libro ficticio correcto de la clase Libro
	 **/
	public static Libro getLibroFicticio(String titulo, String autor) {
		if (autor == null) {
			throw new NullPointerException("ERROR: El autor no puede ser nulo.");
		}
		if (autor.trim().equals("")) {
			throw new IllegalArgumentException("ERROR: El autor no puede estar vacío.");
		}
		if (titulo == null) {
			throw new NullPointerException("ERROR: El título no puede ser nulo.");
		}
		if (titulo.trim().equals("")) {
			throw new IllegalArgumentException("ERROR: El título no puede estar vacío.");
		}
		Libro libroFicticio = new Libro("Cien años de soledad", "Gabriel García Márquez", 275);
		return libroFicticio;
	}

	public String getTitulo() {
		return titulo;
	}

	private void setTitulo(String titulo) {
		if (titulo == null) {
			throw new NullPointerException("ERROR: El título no puede ser nulo.");
		}
		if (titulo.trim().equals("")) {
			throw new IllegalArgumentException("ERROR: El título no puede estar vacío.");
		}
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	private void setAutor(String autor) {
		if (autor == null) {
			throw new NullPointerException("ERROR: El autor no puede ser nulo.");
		}
		if (autor.trim().equals("")) {
			throw new IllegalArgumentException("ERROR: El autor no puede estar vacío.");
		}
		this.autor = autor;
	}

	public int getNumPaginas() {
		return numPaginas;
	}

	private void setNumPaginas(int numPaginas) {
		if (numPaginas <= 0) {
			throw new IllegalArgumentException("ERROR: El número de páginas debe ser mayor que cero.");
		}
		this.numPaginas = numPaginas;
	}

	/**
	 * Va a devolver el número de puntos que se obtienen de un libro
	 * 
	 * Obtendremos 0,5 puntos por cada 25 páginas del libro: de 0-24 obtenemos 0,5
	 * puntos, de 25-49 obtenemos 1 puntos, de 50-74 obtenemos 1,5 puntos, etc.
	 * 
	 * @return: puntos
	 **/
	public float getPuntos() {
		float puntos = (float) (Math.ceil(numPaginas /  PAGINAS_PARA_RECOMPENSA+1) * PUNTOS_PREMIO );
		return puntos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autor == null) ? 0 : autor.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		if (!(obj instanceof Libro)) {
			return false;
		}
		Libro other = (Libro) obj;
		if (autor == null) {
			if (other.autor != null)
				return false;
		} else if (!autor.equals(other.autor))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("título=%s, autor=%s, número de páginas=%s", titulo, autor, numPaginas);
	}

}
