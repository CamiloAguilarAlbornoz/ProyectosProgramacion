package co.edu.uptc.prgr3.linearDataStructure;

import java.util.Comparator;

/**
 * Esta clase contiene
 * todos los metodos que cualquier estructura de datos
 * considerar escencial para el su buen funcionamiento
 * 
 * @author Camilo Andres Aguilar Albornoz
 * @param <E> tipo de objeto del que creara una estructura de datos
 */
public abstract class DataStructure<E> implements Iterable<E> {

	//--------------------- Atributos -----------------------  
	/**
	 * Criterio de ordenamiento de la estructura de datos
	 */
	protected Comparator<E> comparator;  

	/**
	 * Tamanio actual de la estructura de datos
	 */
	protected int size;
	
	/**
	 * Limite de datos que va a agregar
	 */
	protected int limit;

	/**
	 * Cabeza de la estructura de datos
	 */
	protected Node<E> head; 

	/**
	 * Permite obtener el criterio de ordenamiento
	 * @return criterio de ordenamiento
	 */
	public Comparator<E> getOrderingCriterion() {
		return comparator;
	}
	
	// --------------------------- Metodos de la clase --------------------------

	/**
	 * Se encarga de obtener el mayor entre
	 * 2 objetos
	 * @param e1 objeto que desea comparar
	 * @param e2 objeto que desea comparar
	 * @param comparator criterio de comparacion de los objetos
	 * @return resultado para hacer la valoracion
	 */
	public int comparation(E e1, E e2, Comparator<E> comparator) {
		return comparator.compare(e1, e2);
	}
	
	/**
	 * Retorna la 
	 * posicion de un elemento
	 * dentro de la lista si este existe
	 * @return posicion de la lista, -1 si la lista esta vacia
	 */
	public int getIndex(E e) {
		if (head != null) {
			Node<E> actual = head;
			int position = 0;
			while(actual != null) {
				if (!e.equals(actual.element)) {
					actual = actual.next;
					position++;
				} else
					return position;
			}
			return -1;
		}
		return -1;
	}

	/**
	 * Este metodo permite eliminar
	 * todos los elementos de la lista
	 */
	public abstract void clear();

	/**
	 * Este metodo permite comprobar si la lista enlazada se encuentra vacia
	 *
	 * @return true si no hay elementos en la lista
	 */
	public boolean isEmpty() {
		return head == null;
	}
}
