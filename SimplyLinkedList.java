package co.edu.uptc.prgr3.linearDataStructure;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Esta clase permite la creacion de una lista simplemente enlazada la cual
 * puede ser recorrida desde el primer elemento hasta el ultimo
 *
 * @author Camilo Andres Aguilar Albornoz
 * @param <E> tipo de objeto del que creara la lista simplemente enlazada
 */
public class SimplyLinkedList<E> extends MySimplyLinkedList<E> {

	// ----------------------------- Metodos Constructores --------------------------------
	/**
	 * Este constructor permite crear una lista simplemente enlazada de manera
	 * ordenada
	 *
	 * @param comparator criterio de ordenamiento de la lista
	 */
	public SimplyLinkedList(Comparator<E> comparator) {
		this.limit = Integer.MAX_VALUE;
		this.comparator = comparator;
	}

	/**
	 * Este constructor permite crear una lista simplemente enlazada con todos
	 * los elementos de una coleccion
	 *
	 * @param c coleccion de con la que creara la lista
	 */
	public SimplyLinkedList(Collection<? extends E> c) {
		this.limit = Integer.MAX_VALUE;
		for (Iterator<? extends E> it = c.iterator(); it.hasNext();) {
			addLast(it.next());
		}
	}

	/**
	 * Este constructor permite crear una lista simplemente enlazada ordenada
	 * con todos los elementos de una coleccion
	 *
	 * @param comparator criterio de ordenamiento de la lista
	 * @param c coleccion con la que creara la lista
	 */
	public SimplyLinkedList(Comparator<E> comparator, Collection<? extends E> c) {
		this.comparator = comparator;
		this.limit = Integer.MAX_VALUE;
		for (Iterator<? extends E> it = c.iterator(); it.hasNext();) {
			addSort(this.comparator, it.next());
		}
	}

	/**
	 * Permite crear una lista simplemente enlazada
	 */
	public SimplyLinkedList() {
		this.limit = Integer.MAX_VALUE;
	}
	
	/**
	 * Este constructor permite crear una lista simplemente enlazada de manera
	 * ordenada
	 *
	 * @param comparator criterio de ordenamiento de la lista
	 * @param limit limite de datos que puede agregar
	 */
	public SimplyLinkedList(Comparator<E> comparator, int limit) {
		this.limit = limit;
		this.comparator = comparator;
	}

	/**
	 * Este constructor permite crear una lista simplemente enlazada con todos
	 * los elementos de una coleccion
	 *
	 * @param c coleccion de con la que creara la lista
	 * @param limit limite de datos que puede agregar
	 */
	public SimplyLinkedList(Collection<? extends E> c, int limit) {
		this.limit = limit;
		for (Iterator<? extends E> it = c.iterator(); it.hasNext();) {
			addLast(it.next());
		}
	}

	/**
	 * Este constructor permite crear una lista simplemente enlazada ordenada
	 * con todos los elementos de una coleccion
	 *
	 * @param comparator criterio de ordenamiento de la lista
	 * @param c coleccion con la que creara la lista
	 * @param limit limite de datos que puede agregar
	 */
	public SimplyLinkedList(Comparator<E> comparator, Collection<? extends E> c, int limit) {
		this.comparator = comparator;
		this.limit = limit;
		for (Iterator<? extends E> it = c.iterator(); it.hasNext();) {
			addSort(this.comparator, it.next());
		}
	}

	/**
	 * Permite crear una lista simplemente enlazada
	 * 
	 * @param limit limite de datos que puede agregar
	 */
	public SimplyLinkedList(int limit) {
		this.limit = limit;
	}

	// ------------------------- Metodos de la clase -------------------------------------

	@Override
	public void addLast(E e) {
		if (size < limit) {
			if (head != null) {
				Node<E> actual = head;
				while (actual.next != null)
					actual = actual.next;
				actual.next = new Node<>(e);
			} else 
				head = new Node<>(e);
			size++;
		}
	}

	@Override
	public void addFirst(E e) {
		if(size < limit) {
			head = new Node<>(e, head);
			size++;
		}
	}

	@Override
	public void addSort(Comparator<E> comparator, E e) {
		if(size < limit) {
			if(head != null) {
				if(comparator.compare(e, head.element) >= 0) {
					Node<E> actual = head;
					while(actual.next != null && comparator.compare(e, actual.next.element) >= 0) 
						actual = actual.next;
					actual.next = new Node<>(e, actual.next);
				} else 
					head = new Node<>(e, head);
			} else
				head = new Node<>(e);
			size++;
		}
	}

	@Override
	public void add(E e, int index) {
		if (size < limit) {
			if (head != null) {
				if (index >= 0 && index < size) {
					Node<E> actual = head;
					int accountant = 0;
					while (accountant < (index - 1)) {
						actual = actual.next;
						accountant++;
					}
					actual.next = new Node<>(e, actual.next);
					size++;
				} else
					throw new IndexOutOfBoundsException(INDEXOUTOFBOUNDSEXCEPTION);
			} else if (index == 0) {
				head = new Node<>(e, head);
				size++;
			} else
				throw new IndexOutOfBoundsException(INDEXOUTOFBOUNDSEXCEPTION);
		}
	}

	@Override
	public void remove(E e) {
		if(head != null) {
			if(!e.equals(head.element)) {
				Node<E> actual = head;
				while(actual.next != null) {
					if(!e.equals(actual.next.element))
						actual = actual.next;
					else if(actual.next.next != null) {
						actual.next = actual.next.next;
						size--;
						break;
					} else {
						actual.next = null;
						size--;
						break;
					}
				}
			} else if (head.next != null) {
				head = head.next;
				size--;
			}
			else {
				head = null;
				size--;
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
				if(actual.next.next != null) {
					actual.next = actual.next.next;
					size--;
				} else {
					actual.next = null;
					size--;
				}
			} else if(head.next != null){
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
	public boolean contains(E e) {
		if (head != null) {
			Node<E> actual = head;
			while(actual != null) {
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
			while(actual != null) {
				if (!e.equals(actual.element))
					actual = actual.next;
				else
					return actual.element;
			}
			return null;
		}
		return null;
	}

}
