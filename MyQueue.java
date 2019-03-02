package co.edu.uptc.prgr3.linearDataStructure;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Esta clase se encarga de la creacion de elementos Queue, en las cuales cada
 * elemento se agrega en la ultima posicion de la lista
 *
 * @author Camilo Andres Aguilar Albornoz
 * @param <E> tipo de objeto con el que creara la Cola
 */
public class MyQueue<E> extends DataStructure<E> {

	// -------------------------- Atributos ----------------------------
	/**
	 * Tiene el ultimo elemento de la cola
	 */
	private Node<E> queue;

	/**
	 * Este contructor permite crear una cola con todos los objetos de una
	 * coleccion
	 *
	 * @param c coleccion que va a transferir a la cola
	 */
	public MyQueue(Collection<? extends E> c) {
		this.limit = Integer.MAX_VALUE;
		for (Iterator<? extends E> it = c.iterator(); it.hasNext();) {
			offerInQueue(it.next());
		}
	}

	/**
	 * Este contructor permite crear una cola de prioridad con todos los objetos de una
	 * coleccion
	 *
	 * @param c coleccion que va a transferir a la cola
	 * @param comparator prioridad con la que agregara los elementos en la cola
	 */
	public MyQueue(Comparator<E> comparator, Collection<? extends E> c) {
		this.limit = Integer.MAX_VALUE;
		for (Iterator<? extends E> it = c.iterator(); it.hasNext();) {
			offerInPrioirityQueue(it.next());
		}
	}

	/**
	 * Este constructor permite crear una cola de prioridad
	 *
	 * @param comparator prioridad con la que agregara los elementos en la cola
	 */
	public MyQueue(Comparator<E> comparator) {
		this.limit = Integer.MAX_VALUE;
		this.comparator = comparator;
	}

	/**
	 * Este constructor permite crear una cola de prioridad
	 */
	public MyQueue() {
		this.limit = Integer.MAX_VALUE;
	}
	
	/**
	 * Este contructor permite crear una cola con todos los objetos de una
	 * coleccion
	 *
	 * @param c coleccion que va a transferir a la cola
	 * @param limit limite de datos que puede agregar
	 */
	public MyQueue(Collection<? extends E> c, int limit) {
		this.limit = limit;
		for (Iterator<? extends E> it = c.iterator(); it.hasNext();) {
			offerInQueue(it.next());
		}
	}

	/**
	 * Este contructor permite crear una cola de prioridad con todos los objetos de una
	 * coleccion
	 *
	 * @param c coleccion que va a transferir a la cola
	 * @param comparator prioridad con la que agregara los elementos en la cola
	 * @param limit limite de datos que puede agregar
	 */
	public MyQueue(Comparator<E> comparator, Collection<? extends E> c, int limit) {
		this.limit = limit;
		for (Iterator<? extends E> it = c.iterator(); it.hasNext();) {
			offerInPrioirityQueue(it.next());
		}
	}

	/**
	 * Este constructor permite crear una cola de prioridad
	 *
	 * @param comparator prioridad con la que agregara los elementos en la cola
	 * @param limit limite de datos que puede agregar
	 */
	public MyQueue(Comparator<E> comparator, int limit) {
		this.limit = limit;
		this.comparator = comparator;
	}

	/**
	 * Este constructor permite crear una cola de prioridad
	 * 
	 * @param limit limite de datos que puede agregar
	 */
	public MyQueue(int limit) {
		this.limit = limit;
	}

	// --------------------------------- Atributos ----------------------------------------------
	/**
	 * Permite agregar un nuevo elemento en la cola de datos
	 *
	 * @param e elemento que va a agregar a la cola
	 */
	public void offer(E e) {
		if (comparator == null)
			offerInQueue(e);
		else 
			offerInPrioirityQueue(e);
	}

	/**
	 * Permite obtener el primer
	 * elemento de la cola sin eliminarlo
	 * @return si la cola no es nula retorna el primer elemento, de lo contrario retorna nulo
	 */
	public E get() {
		if(head != null)
			return head.element;
		return null;
	}

	/**
	 * Permite obtener el ultimo
	 * elemento de la cola sin eliminarlo
	 * @return si la cola no es nula retorna el ultimo elemento, de lo contrario retorna nulo
	 */
	public E getLast() {
		if(head != null)
			return queue.element;
		return null;
	}

	/**
	 * Permite obtener el primer
	 * elemento de la cola eliminandolo
	 * @return si la cola no es nula retorna el ultimo elemento, de lo contrario retorna nulo
	 */
	public E remove() {
		if(head != null) {
			E item = head.element;
			head = head.next;
			size--;
			return item;
		}
		return null;
	}

	@Override
	public Iterator<E> iterator() {
		SimplyLinkedList<E> linkedList = new SimplyLinkedList<>();
		return new Iterator<E>() {

			private int actual;

			@Override
			public boolean hasNext() {
				if (actual < size) {
					return true;
				}
				size = 0;
				queue = null;
				for (E e : linkedList) {
					offer(e);
				}
				return false;
			}

			@Override
			public E next() {
				E item = head.element;
				head = head.next;
				linkedList.addLast(item);
				actual++;
				return item;
			}
		};
	}

	private void offerInQueue(E e) {
		if(size < limit) {
			if (head != null) {
				queue.next = new Node<>(e);
				queue = queue.next;
			} else {
				head = queue = new Node<>(e);
			}
			size++;
		}
	}

	private void offerInPrioirityQueue(E e) {
		if(size < limit) {
			if (head != null) {
				if(comparator.compare(e, head.element) >= 0) {
					Node<E> actual = head;
					while(actual.next != null && comparator.compare(e, actual.next.element) >= 0) {
						actual = actual.next;
					}
					actual.next = new Node<>(e, actual.next);
					if(actual.next.next == null)
						queue = actual.next;
				} else
					head = new Node<>(e, head);
			} else {
				head = queue = new Node<>(e);
			}
			size++;
		}
	}

	@Override
	public void clear() {
		head = null;
		size = 0;
	}
}