package co.edu.uptc.prgr3.linearDataStructure;

import java.util.Iterator;

/**
 * Esta clase permite crear iteradores necesarios para el recorrido de la lista
 * enlazada
 * 
 * @author Camilo Andres Aguilar Albornoz
 */
class ManagerIterator<E> implements Iterator<E> {

	/**
	 * Nodo actual que esta recorriendo
	 */
	private Node<E> actual;

	/**
	 * Permite contar el elemento en el que se encuentra actualmente
	 */
	private int accountant;

	/**
	 * Tamanio de la lista
	 */
	private int size;
	
	/**
	 * Lista enlazada con la que realizara las operaciones
	 */
	private MySimplyLinkedList<E> mySimplyLinkedList;
	
	/**
	 * Elemento actual devuelto por {@link element()}
	 */
	private E actualElement;

	/**
	 * Permite crear un iterador con el nodo a partir del cual va a recorrer la
	 * lista
	 *
	 * @param actual nodo a partir de la cual va a recorrer la lista
	 * @param size tamanio de la lista
	 */
	public ManagerIterator(Node<E> actual, int size, MySimplyLinkedList<E> mySimplyLinkedList) {
		this.actual = actual;
		this.size = size;
		this.mySimplyLinkedList = mySimplyLinkedList;
	}

	@Override
	public boolean hasNext() {
		return accountant < size;
	}

	@Override
	public E next() {
		E item = actualElement = actual.element;
		actual = actual.next;
		accountant++;
		return item;
	}
	
	@Override
	public void remove() {
		mySimplyLinkedList.remove(actualElement);
	}
}
