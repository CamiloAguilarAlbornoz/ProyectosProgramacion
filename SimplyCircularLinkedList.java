package co.edu.uptc.prgr3.linearDataStructure;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Esta clase permite la creacion de una lista circular simplemente enlazada la
 * cual puede ser recorrida de manera ilimitada al no tener un final, sin
 * embargo solo puede ser recorrida desde atras hacia adelante
 *
 * @author Camilo Andres Aguilar Albornoz
 * @param <E> tipo de objeto del que creara la lista circular simplemente
 * enlazada
 */
public class SimplyCircularLinkedList<E> extends MySimplyLinkedList<E> {

	// ----------------------------- Metodos Constructores --------------------------------
	/**
	 * Este constructor permite crear una lista circular simplemente enlazada de
	 * manera ordenada
	 *
	 * @param comparator criterio de ordenamiento de la lista
	 */
	public SimplyCircularLinkedList(Comparator<E> comparator) {
		this.limit = Integer.MAX_VALUE;
		this.comparator = comparator;
	}

	/**
	 * Este constructor permite crear una lista circular simplemente enlazada
	 * con todos los elementos de una coleccion
	 *
	 * @param c coleccion de con la que creara la lista
	 */
	public SimplyCircularLinkedList(Collection<? extends E> c) {
		this.limit = Integer.MAX_VALUE;
		for (Iterator<? extends E> it = c.iterator(); it.hasNext();)
			addLast(it.next());
	}

	/**
	 * Este constructor permite crear una lista circular simplemente enlazada
	 * ordenada con todos los elementos de una coleccion
	 *
	 * @param comparator criterio de ordenamiento de la lista
	 * @param c coleccion con la que creara la lista
	 */
	public SimplyCircularLinkedList(Comparator<E> comparator, Collection<? extends E> c) {
		this.limit = Integer.MAX_VALUE;
		this.comparator = comparator;
		for (Iterator<? extends E> it = c.iterator(); it.hasNext();)
			addSort(this.comparator, it.next());
	}

	/**
	 * Permite crear una lista circular simplemente enlazada
	 */
	public SimplyCircularLinkedList() {
		this.limit = Integer.MAX_VALUE;
	}
	
	/**
	 * Este constructor permite crear una lista circular simplemente enlazada de
	 * manera ordenada
	 *
	 * @param comparator criterio de ordenamiento de la lista
	 * @param limit limite de datos que puede agregar
	 */
	public SimplyCircularLinkedList(Comparator<E> comparator, int limit) {
		this.limit = limit;
		this.comparator = comparator;
	}

	/**
	 * Este constructor permite crear una lista circular simplemente enlazada
	 * con todos los elementos de una coleccion
	 *
	 * @param limit limite de datos que puede agregar
	 */
	public SimplyCircularLinkedList(Collection<? extends E> c, int limit) {
		this.limit = limit;
		for (Iterator<? extends E> it = c.iterator(); it.hasNext();)
			addLast(it.next());
	}

	/**
	 * Este constructor permite crear una lista circular simplemente enlazada
	 * ordenada con todos los elementos de una coleccion
	 *
	 * @param comparator criterio de ordenamiento de la lista
	 * @param limit limite de datos que puede agregar
	 */
	public SimplyCircularLinkedList(Comparator<E> comparator, Collection<? extends E> c, int limit) {
		this.limit = limit;
		this.comparator = comparator;
		for (Iterator<? extends E> it = c.iterator(); it.hasNext();)
			addSort(this.comparator, it.next());
	}

	/**
	 * Permite crear una lista circular simplemente enlazada
	 * 
	 * @param limit limite de datos que puede agregar
	 */
	public SimplyCircularLinkedList(int limit) {
		this.limit = limit;
	}

	// ------------------------- Metodos de la clase -------------------------------------
	@Override
	public void addLast(E e) {
		if (size < limit) {
			if (head != null) {
				Node<E> actual = head;
				while (!actual.next.equals(head))
					actual = actual.next;
				actual.next = new Node<>(e, head);
			} else {
				head = new Node<>(e);
				head.next = head;
			}
			size++;
		}
	}

	@Override
	public void addFirst(E e) {
		if (size < limit) {
			if (head != null)
				addInFirstPosition(e);
			else {
				head = new Node<>(e);
				head.next = head;
			}
			size++;
		}
	}

	@Override
	public void addSort(Comparator<E> comparator, E e) {
		if (size < limit) {
			if (head != null) {
				if (comparator.compare(e, head.element) >= 0) {
					Node<E> actual = head;
					while (actual.next != head && comparator.compare(e, actual.next.element) >= 0)
						actual = actual.next;
					actual.next = new Node<>(e, actual.next);
				} else
					addInFirstPosition(e);
			} else {
				head = new Node<>(e);
				head.next = head;
			}
			size++;
		}
	}

	@Override
	public void add(E e, int index) {
		if (size < limit) {
			if (head != null) {
				if (index >= 0 && index < size) {
					if (index > 0) {
						Node<E> actual = head;
						int accountant = 0;
						while (accountant < (index - 1)) {
							actual = actual.next;
							accountant++;
						}
						actual.next = new Node<>(e, actual.next);
						size++;
					} else
						addInFirstPosition(e);
					size++;
				} else
					throw new IndexOutOfBoundsException(INDEXOUTOFBOUNDSEXCEPTION);
			} else if (index == 0)
				addFirst(e);
			else
				throw new IndexOutOfBoundsException(INDEXOUTOFBOUNDSEXCEPTION);
		}
	}

	@Override
	public void remove(E e) {
		if(head != null) {
			if(!e.equals(head.element)) {
				Node<E> actual = head;
				do {                    
					if(!e.equals(actual.next.element))
						actual = actual.next;
					else {
						actual.next = actual.next.next;
						size--;
						break;
					}
				} while (actual != head);
			} else if(head.next != head) {
				head = head.next;
				size--;
			} else {
				head = null;
				size = 0;
			}
		}
	}

	@Override
	public void remove(int index) {
		if(index >= 0 && index < size) {
			if(index > 0) {
				int accountant = 0;
				Node<E> actual = head;
				while (accountant < index) {
					accountant++;
					actual = actual.next;
				}
				actual.next = actual.next.next;
				size--;
			} else if(head.next != head){
				head = head.next;
				size--;
			} else {
				head = null;
				size = 0;
			}
		} else 
			throw new IndexOutOfBoundsException(INDEXOUTOFBOUNDSEXCEPTION);
	}

	@Override
	public void clear() {
		head = null;
		size = 0;
	}

	// -------------------------------------- Metodos encapsulados ----------------------------------
	private void addInFirstPosition(E e) {
		head = new Node<>(e, head);
		Node<E> actual = head.next;
		while (actual.next != head.next) {
			actual = actual.next;
		}
		actual.next = head;
	}

	@Override
	public boolean contains(E e) {
		if (head != null) {
			Node<E> actual = head;
			do {
				if (!e.equals(actual.element))
					actual = actual.next;
				else
					return true;
			} while (actual != head);
		}
		return false;
	}

	@Override
	public void set(E newElement, E actualElement) {
		if (head != null) {
			Node<E> actual = head;
			do {
				if (!actualElement.equals(actual.element))
					actual = actual.next;
				else
					actual.element = newElement;
			} while (actual != head);
		}
	}

	@Override
	public E get(E e) {
		if (head != null) {
			Node<E> actual = head;
			do {
				if (!e.equals(actual.element))
					actual = actual.next;
				else
					return actual.element;
			} while (actual != head);
			return null;
		}
		return null;
	}
}