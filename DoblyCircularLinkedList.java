package co.edu.uptc.prgr3.linearDataStructure;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Esta clase permite la creacion de una lista circular doblemente enlazada la
 * cual puede ser recorrida de manera ilimitada al no tener un final, de
 * principio a fin y de fina a principio
 *
 * @author Camilo Andres Aguilar Albornoz
 * @param <E> tipo de objeto del que creara la lista circular doblemente
 * enlazada
 */
public class DoblyCircularLinkedList<E> extends MyDoblyLinkedList<E> {

	// ----------------------------- Metodos Constructores --------------------------------
	/**
	 * Este constructor permite crear una lista circular doblemente enlazada de
	 * manera ordenada
	 *
	 * @param comparator criterio de ordenamiento de la lista
	 */
	public DoblyCircularLinkedList(Comparator<E> comparator) {
		this.comparator = comparator;
		this.limit = Integer.MAX_VALUE;
	}
	
	/**
	 * Este constructor permite crear una lista circular doblemente enlazada de
	 * manera ordenada
	 *
	 * @param comparator criterio de ordenamiento de la lista
	 * @param limit cantidad de datos maxima que podra agregar
	 */
	public DoblyCircularLinkedList(Comparator<E> comparator, int limit) {
		this.comparator = comparator;
		this.limit = limit;
	}

	/**
	 * Este constructor permite crear una lista circular doblemente enlazada con
	 * todos los elementos de una coleccion
	 *
	 * @param c coleccion de con la que creara la lista
	 */
	public DoblyCircularLinkedList(Collection<? extends E> c) {
		this.limit = Integer.MAX_VALUE;
		for (Iterator<? extends E> it = c.iterator(); it.hasNext();) {
			addLast(it.next());
		}
	}
	
	/**
	 * Este constructor permite crear una lista circular doblemente enlazada con
	 * todos los elementos de una coleccion con un limite
	 * de elementos a agregar
	 *
	 * @param c coleccion de con la que creara la lista
	 * @param limit cantidad de datos maxima que podra agregar
	 */
	public DoblyCircularLinkedList(Collection<? extends E> c, int limit) {
		this.limit = limit;
		for (Iterator<? extends E> it = c.iterator(); it.hasNext();) {
			addLast(it.next());
		}
	}

	/**
	 * Este constructor permite crear una lista circular doblemente enlazada
	 * ordenada con todos los elementos de una coleccion
	 *
	 * @param comparator criterio de ordenamiento de la lista
	 * @param c coleccion con la que creara la lista
	 */
	public DoblyCircularLinkedList(Comparator<E> comparator, Collection<? extends E> c) {
		this.comparator = comparator;
		this.limit = Integer.MAX_VALUE;
		for (Iterator<? extends E> it = c.iterator(); it.hasNext();) {
			addSort(this.comparator, it.next());
		}
	}
	
	/**
	 * Este constructor permite crear una lista circular doblemente enlazada
	 * ordenada con todos los elementos de una coleccion
	 *
	 * @param comparator criterio de ordenamiento de la lista
	 * @param c coleccion con la que creara la lista
	 * @param limit cantidad de datos maxima que podra agregar
	 */
	public DoblyCircularLinkedList(Comparator<E> comparator, Collection<? extends E> c, int limit) {
		this.comparator = comparator;
		this.limit = limit;
		for (Iterator<? extends E> it = c.iterator(); it.hasNext();) {
			addSort(this.comparator, it.next());
		}
	}

	/**
	 * Permite crear una lista circular doblemente enlazada
	 */
	public DoblyCircularLinkedList() {
		this.limit = Integer.MAX_VALUE;
	}
	
	/**
	 * Permite crear una lista circular doblemente enlazada
	 * con un limite
	 * 
	 * @param limit cantidad de datos maxima que podra agregar
	 */
	public DoblyCircularLinkedList(int limit) {
		this.limit = limit;
	}

	// ---------------------- Metodos de la clase -----------------------

	@Override
	public void removeFirst() {
		if(head != null) {
			if(head.next != head) {
				head = head.next;
				head.previous = last;
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
                last.next = head;
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
				last = new Node<>(e, head, last);
				last.previous.next = last;
				head.previous = last;
			} else {
				head = last = new Node<>(e);
				last.next = head;
				head.previous = last;
			}
			size++;
		}
	}

	@Override
	public void addFirst(E e) {
		if (size < limit) {
			if (head != null) {
				head = new Node<>(e, head, last);
				head.next.previous = head;
				last.next = head;
			} else {
				head = last = new Node<>(e);
				last.next = head;
				head.previous = last;
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
					actual.next = new Node<>(e, actual.next, actual);
					actual.next.next.previous = actual.next;
					if (actual.next.next == head) {
						head.previous = actual.next;
						last = actual.next;
					}
				} else {
					head = new Node<>(e, head, last);
					head.next.previous = head;
					last.next = head;
				}
			} else {
				head = last = new Node<>(e);
				last.next = head;
				head.previous = last;
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
						actual.next.next.previous = actual.next;
						size++;
					} else {
						head = new Node<>(e, head, last);
						head.next.previous = head;
						last.next = head;
						size++;
					}
				} else 
					throw new IndexOutOfBoundsException(INDEXOUTOFBOUNDSEXCEPTION);
			} else if (index == 0)
				addFirst(e);
			else if (index == (size - 1))
				addLast(e);
			else 
				throw new IndexOutOfBoundsException(INDEXOUTOFBOUNDSEXCEPTION);
		}
	}

	@Override
	public void remove(E e) {
		if(head != null) {
			if(!e.equals(head.element) && !e.equals(last.element)) {
				Node<E> actual = head;
				do {                    
					if(!e.equals(actual.next.element))
						actual = actual.next;
					else {
						actual.next = actual.next.next;
						actual.next.previous = actual;
						size--;
					}
				} while (actual != head);
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
