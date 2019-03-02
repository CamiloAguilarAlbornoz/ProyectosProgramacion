package co.edu.uptc.prgr3.linearDataStructure;

import java.util.Collection;
import java.util.Iterator;

/**
 * Esta clase se encarga
 * de la creacion de elementos Stack,
 * en las cuales cada elemento se agrega
 * en la primer posicion de la lista empujando
 * el resto de los elementos de a la posicion siguiente
 * @author Camilo Andres Aguilar Albornoz
 * @param <E> tipo de objeto con el que creara la Pila
 */
public class MyStack<E> extends DataStructure<E> {

	//--------------------- Metodos constructores ----------------
	/**
	 * Este contructor permite crear
	 * una pila con todos los objetos de una coleccion
	 * @param c coleccion que va a transferir a la pila
	 */
	public MyStack(Collection<? extends E> c) {
		this.limit = Integer.MAX_VALUE;
		for(Iterator<? extends E> it = c.iterator(); it.hasNext();)
			put(it.next());
	}

	/**
	 * Este constructor permite
	 * crear una pila de objetos
	 */
	public MyStack() {
		this.limit = Integer.MAX_VALUE;
	}
	
	/**
	 * Este contructor permite crear
	 * una pila con todos los objetos de una coleccion
	 * @param c coleccion que va a transferir a la pila
	 */
	public MyStack(Collection<? extends E> c, int limit) {
		this.limit = limit;
		for(Iterator<? extends E> it = c.iterator(); it.hasNext();)
			put(it.next());
	}

	/**
	 * Este constructor permite
	 * crear una pila de objetos
	 * 
	 * @param limit limite de datos que puede agregar
	 */
	public MyStack(int limit) {
		this.limit = limit;
	}

	// -------------------- Metodos ---------------------
	/**
	 * Este metodo permite colocar un elemento
	 * en la pila
	 * @param e elemento que va a agregar a la pila
	 */
	public void put(E e) {
		if(size < limit) {
			head = new Node<>(e, head);
			size++;
		}
	}

	/**
	 * Este metodo permite
	 * obtener el primer elemento
	 * de la pila sin eliminarlo
	 * @return si la pila tiene al menos un objeto retornara el primer elemento de esta
	 * sino retornara null
	 */
	public E peek() {
		if(head != null)
			return head.element;
		return null;
	}

	/**
	 * Este metodo permite
	 * obtener y eliminar el primer
	 * elemento de la pila
	 * @return si la pila tiene al menos un objeto retornara el primer elemento de esta
	 * sino retornara null
	 */
	public E pop() {
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
				if(actual < size)
					return true;
				size = 0;
				for (E e : linkedList)
					put(e);
				return false;
			}

			@Override
			public E next() {
				E item = head.element;
				head = head.next;
				linkedList.addFirst(item);
				actual++;
				return item;
			}
		};
	}

	@Override
	public void clear() {
		head = null;
		size = 0;
	}
}