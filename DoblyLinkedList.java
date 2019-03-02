package co.edu.uptc.prgr3.linearDataStructure;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Esta clase permite la creacion de una lista circular enlazada la cual puede
 * ser recorrida de principio a fin y de fina a principio
 *
 * @author Camilo Andres Aguilar Albornoz
 * @param <E> tipo de objeto del que creara la lista circular enlazada
 */
public class DoblyLinkedList<E> extends MyDoblyLinkedList<E> {

	// ----------------------------- Metodos Constructores --------------------------------
	/**
	 * Este constructor permite crear una lista doblemente enlazada de
	 * manera ordenada
	 *
	 * @param comparator criterio de ordenamiento de la lista
	 */
	public DoblyLinkedList(Comparator<E> comparator) {
		this.comparator = comparator;
		this.limit = Integer.MAX_VALUE;
	}

	/**
	 * Este constructor permite crear una lista doblemente enlazada con
	 * todos los elementos de una coleccion
	 *
	 * @param c coleccion de con la que creara la lista
	 */
	public DoblyLinkedList(Collection<? extends E> c) {
		this.limit = Integer.MAX_VALUE;
		for (Iterator<? extends E> it = c.iterator(); it.hasNext();) {
			addLast(it.next());
		}
	}

	/**
	 * Este constructor permite crear una lista doblemente enlazada
	 * ordenada con todos los elementos de una coleccion
	 *
	 * @param comparator criterio de ordenamiento de la lista
	 * @param c coleccion con la que creara la lista
	 */
	public DoblyLinkedList(Comparator<E> comparator, Collection<? extends E> c) {
		this.comparator = comparator;
		this.limit = Integer.MAX_VALUE;
		for (Iterator<? extends E> it = c.iterator(); it.hasNext();) {
			addSort(this.comparator, it.next());
		}
	}

	/**
	 * Permite crear una lista doblemente enlazada
	 */
	public DoblyLinkedList() {
		this.limit = Integer.MAX_VALUE;
	}
	
	/**
	 * Este constructor permite crear una lista doblemente enlazada de
	 * manera ordenada
	 *
	 * @param comparator criterio de ordenamiento de la lista
	 * @param limit limite de datos que puede agregar
	 */
	public DoblyLinkedList(Comparator<E> comparator, int limit) {
		this.comparator = comparator;
		this.limit = limit;
	}

	/**
	 * Este constructor permite crear una lista doblemente enlazada con
	 * todos los elementos de una coleccion
	 *
	 * @param c coleccion de con la que creara la lista
	 * @param limit limite de datos que puede agregar
	 */
	public DoblyLinkedList(Collection<? extends E> c, int limit) {
		this.limit = limit;
		for (Iterator<? extends E> it = c.iterator(); it.hasNext();) {
			addLast(it.next());
		}
	}

	/**
	 * Este constructor permite crear una lista doblemente enlazada
	 * ordenada con todos los elementos de una coleccion
	 *
	 * @param comparator criterio de ordenamiento de la lista
	 * @param c coleccion con la que creara la lista
	 * @param limit limite de datos que puede agregar
	 */
	public DoblyLinkedList(Comparator<E> comparator, Collection<? extends E> c, int limit) {
		this.comparator = comparator;
		this.limit = limit;
		for (Iterator<? extends E> it = c.iterator(); it.hasNext();) {
			addSort(this.comparator, it.next());
		}
	}

	/**
	 * Permite crear una lista doblemente enlazada
	 * @param limit limite de datos que puede agregar
	 */
	public DoblyLinkedList(int limit) {
		this.limit = limit;
	}

	// ------------------------- Metodos de la clase -------------------------------------
	@Override
	public void removeFirst() {
		if(head != null) {
			if(head.next != null) {
				head = head.next;
				head.previous = null;
				size--;
			} else {
				head = last = null;
				size = 0;
			}
		}
	}

	@Override
	public void removeLast() {
		if(head != null) {
			if(last.previous != last) {
				last = last.previous;
				last.next = null;
				size--;
			} else {
				head = last = null;
				size = 0;
			}
		}
	}

	@Override
	public void addLast(E e) {
		if (size < limit) {
			if (head != null) {
				last = new Node<>(e, null, last);
				last.previous.next = last;
			} else {
				head = last = new Node<>(e);
			}
			size++;
		}
	}

	@Override
	public void addFirst(E e) {
		if (size < limit) {
			if (head != null) {
				head = new Node<>(e, head, null);
				head.next.previous = head;
			} else {
				head = last = new Node<>(e);
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
					while (actual.next != null && comparator.compare(e, actual.next.element) >= 0)
						actual = actual.next;
					actual.next = new Node<>(e, actual.next);
					if(actual.next.next != null)
						actual.next.next.previous = actual.next;
					else
						last = actual.next;
				} else {
					head = new Node<>(e, head, last);
					head.next.previous = head;
				}
			} else {
				head = last = new Node<>(e);
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
							accountant++;
							actual = actual.next;
						}
						actual.next = new Node<>(e, actual.next, actual);
						if(actual.next.next != null)
							actual.next.next.previous = actual.next;
						else
							last = actual.next;
						size++;
					} else {
						head = new Node<>(e, head, null);
						head.next.previous = head;
						size++;
					}
				} else 
					throw new IndexOutOfBoundsException(INDEXOUTOFBOUNDSEXCEPTION);
			} else if (index == 0) {
				addFirst(e);
			} else if (index == (size - 1)) {
				addLast(e);
			} else {
				throw new IndexOutOfBoundsException(INDEXOUTOFBOUNDSEXCEPTION);
			}
		}
	}

	@Override
	public void remove(E e) {
		if(head != null) {
			if(!e.equals(head.element) && !e.equals(last.element)) {
				Node<E> actual = head;
				while(actual.next != null) {
					if(!e.equals(actual.next.element))
						actual = actual.next;
					else {
						remove(actual);
						break;
					}
				}
			} else if(e.equals(last.element))
				removeLast();
			else if(e.equals(head.element))
				removeFirst();
		}
	}

	@Override
	public boolean contains(E e) {
		if (head != null) {
			Node<E> actual = head;
			while (actual != null) {
				if (!e.equals(actual.element))
					actual = actual.next;
				else
					return true;
			}
			return false;
		}
		return false;
	}

	@Override
	public void set(E newElement, E actualElement) {
		if (head != null) {
			Node<E> actual = head;
			while (actual != null) {
				if (!actualElement.equals(actual.element))
					actual = actual.next;
				else
					actual.element = newElement;
			}
		}
	}

	@Override
	public E get(E e) {
		if (head != null) {
			Node<E> actual = head;
			while (actual != null) {
				if (!e.equals(actual.element))
					actual = actual.next;
				else
					return actual.element;
			}
			return null;
		}
		return null;
	}


	// ------------------------------ Metodos encapsulados --------------------
	private void remove(Node<E> actual) {
		if(actual.next.next != null) {
			actual.next = actual.next.next;
			if(actual.next.next == null)
				last = actual.next;
		} else {
			actual.next = null;
			last = actual;
		}
		size--;
	}
}
