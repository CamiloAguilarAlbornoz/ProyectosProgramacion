package co.edu.uptc.prgr3.linearDataStructure;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Esta clase proporciona los metodos necesarios para la creacion de listas
 * enlazadas simples o dobles, ademas de que posee metodos propios que seran de
 * utilidad para cualquier estructura de datos
 *
 * @author Camilo Andres Aguilar Albornoz
 * @param <E> tipo de objeto del que creara la estructura de datos
 */
public abstract class MySimplyLinkedList<E> extends DataStructure<E> {

	protected static final String INDEXOUTOFBOUNDSEXCEPTION = "A ingresado una posicion invalida";
	protected static final String NULLPOINTEREXCEPTION = "Ha intentado trabajar con una lista vacia";
	protected static final String ILLEGAL_ARGUMENT_EXCEPTION = "Los datos ingresados son erroneos";

	// ------------------------- Metodos propios de clase -------------------------------
	/**
	 * Este metodo permite agregar un nuevo objeto a la lista, primero se
	 * encarga de verificar si la lista es ordenada, en caso de serlo debera
	 * agregar el objeto invocando el metodo addSort(E e, Comparator
	 * comparator), pero si la lista esta creada sin un criterio de ordenamiento
	 * entonces agregara el elemento el final de la lista
	 *
	 * @param e elemento que va a agregar a la lista
	 */
	public void add(E e) {
		if (comparator == null) 
			addLast(e);
		else 
			addSort(comparator, e);
	}

	/**
	 * Este metodo permite agregar cada metodo de una coleccion a la lista
	 * enlazada tomando cada elemento de esta e invocando el metodo add(E e),
	 * donde realizara las respectivas operaciones
	 *
	 * @param c coleccion con la que va a trabajar
	 */
	public void addAll(Collection<? extends E> c) {
		for (Iterator<? extends E> it = c.iterator(); it.hasNext();)
			add(it.next());
	}
	
	/**
	 * Este metodo permite obtener un elemento de la lista que este en la
	 * posicion ingresada por parametro
	 *
	 * @param index indice ingresado
	 * @return elemento de la lista despues de encontrarlo
	 * @throws IndexOutOfBoundsException en caso de que ingrese un indice menor
	 * que 0 o mayor que el indice del ultimo nodo
	 * @throws NullPointerException en caso de que ingrese una posicion mayor
	 * que 0 y la lista este vacia
	 */
	public E get(int index) {
		if (index >= 0 && index < size) {
			if (index >= 0 && head != null) {
				Node<E> actual = head;
				int accountant = 0;
				while (accountant != index) {
					actual = actual.next;
					accountant++;
				}
				return actual.element;
			}
			throw new NullPointerException(NULLPOINTEREXCEPTION);
		}
		throw new IndexOutOfBoundsException(INDEXOUTOFBOUNDSEXCEPTION);
	}

	/**
	 * Este metodo permite crear otra lista enlazada en la cual guardar todos
	 * los elementos que se encuentren a partir de la primer posicion ingresada
	 * hasta la ultima
	 *
	 * @param start posicion a partir de la cual agregara objetos
	 * @param end posicion hasta la que agregara objetos
	 * @return lista enlzada con los elementos agregados a esta
	 * @throws IndexOutOfBoundsException en caso de que ingrese un indice menor
	 * que 0 o mayor que el indice del ultimo nodo
	 * @throws NullPointerException en caso de que ingrese una posicion mayor
	 * que 0 y la lista este vacia
	 * @throws IllegalArgumentException si el primer parametro es mayor que el ultimo
	 */
	public MySimplyLinkedList<E> subList(int start, int end) {
		if(start >= 0 && end >= 0 && start < size && end < size) {
			if(start <= end) {
				int accountant = 0;
				Node<E> actual = head;
				while(accountant < start) {
					actual = actual.next;
					accountant++;
				}
				MySimplyLinkedList<E> linkedList = new SimplyLinkedList<>();
				while(accountant <= end) {
					linkedList.add(actual.element);
					actual = actual.next;
					accountant++;
				}
				return linkedList;
			} else 
				throw new IllegalArgumentException(ILLEGAL_ARGUMENT_EXCEPTION);
		} else
			throw new IndexOutOfBoundsException(INDEXOUTOFBOUNDSEXCEPTION);
	}
	
	@Override
	public Iterator<E> iterator() {
		return new ManagerIterator<>(head, size, this);
	}
	
	/**
	 * Este metodo permite crear un iterador para recorrer una lista enlazada a
	 * partir del indice ingresado
	 *
	 * @param index indice a partir del cual recorrera la lista
	 * @return iterador con el cual recorrera la lista
	 * @throws IndexOutOfBoundsException en caso de que ingrese un indice menor
	 * que 0 o mayor que el indice del ultimo nodo
	 * @throws NullPointerException en caso de que ingrese una posicion mayor
	 * que 0 y la lista este vacia
	 */
	public Iterator<E> iterator(int index) {
		if (index >= 0 && index < size) {
			if (index >= 0 && head != null) {
				Node<E> actual = head;
				int accountant = 0;
				while (accountant != index) {
					actual = actual.next;
					accountant++;
				}
				return new ManagerIterator<>(actual, size, this);
			}
			throw new NullPointerException(NULLPOINTEREXCEPTION);
		}
		throw new IndexOutOfBoundsException(INDEXOUTOFBOUNDSEXCEPTION);
	}
	
	/**
	 * Este metodo permite buscar y eliminar de la lista enlazada cada elemento
	 * que este en la coleccion y tambien en la lista enlazada
	 *
	 * @param c coleccion que desea comparar con la lista enlazada para realizar
	 * la operacion
	 */
	public void removeAll(Collection<? extends E> c) {
		for (Iterator<? extends E> it = c.iterator(); it.hasNext();)
			remove(it.next());
	}

	/**
	 * Este metodo permite agregar todos los elementos de una coleccion a partir
	 * de el indice ingresado
	 *
	 * @param c coleccion que va a agregar
	 * @param index indice a partir del cual agregara un elemento en la lista
	 * @throws IndexOutOfBoundsException en caso de que ingrese un indice menor
	 * que 0 o mayor que el indice del ultimo nodo
	 */
	public void addAll(Collection<? extends E> c, int index) {
		for(Iterator<? extends E> it = c.iterator(); it.hasNext();) {
			add(it.next(), index);
			index++;
		}
	}
	
	/**
	 * Este metodo permite eliminar de la lista cada elemento que se encuentre
	 * entre las posiciones indicadas por parametro
	 *
	 * @param start posicion a partir de la cual agregara objetos
	 * @param end posicion hasta la que agregara objetos
	 * @throws IndexOutOfBoundsException en caso de que ingrese un indice menor
	 * que 0 o mayor que el indice del ultimo nodo
	 * @throws NullPointerException en caso de que ingrese una posicion mayor
	 * que 0 y la lista este vacia
	 */
	public void removeAll(int start, int end) {
		MySimplyLinkedList<E> linkedList = subList(start, end);
		for (E e : linkedList)
			remove(e);
	}

	/**
	 * Este metodo permite buscar y retener en la lista enlazada cada elemento
	 * que este en la coleccion y tambien en la lista enlazada
	 *
	 * @param c coleccion que desea comparar con la lista enlazada para realizar
	 * la operacion
	 */
	public void retainAll(Collection<? extends E> c) {
		for (E e : this) {
			if (!c.contains(e))
				remove(e);
		}
	}

	/**
	 * Este metodo retorna el tamanio actual de la lista
	 *
	 * @return tamanio actual de la lista
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Este metodo permite corroborar que todos los elementos de la coleccion
	 * ingresada se encuentran dentro de la lista enlazada
	 *
	 * @param c coleccion que desea corroborar si se encuentra en la lista
	 * @return true si todos los elementos se encuentran en la lista, false si
	 * al menos uno llega a faltar
	 */
	public boolean containsAll(Collection<? extends E> c) {
		for (Iterator<? extends E> it = c.iterator(); it.hasNext();) {
			if (!contains(it.next()))
				return false;
		}
		return true;
	}

	/**
	 * Este metodo permite agregar
	 * a un vector de objetos
	 * todos los elementos de una lista
	 * enlazada
	 * 
	 * @return un vector con todos los elementos de la lista enlazada
	 * @throws NullPointerException si la lista no tiene elementos
	 */
	public Object[] toArray() {
		if(head != null) {
			Object[] objects = new Object[size];
			Node<E> actual = head;
			for (int i = 0; i < size; i++) {
				objects[i] = actual.element;
				actual = actual.next;
			}
			return objects;
		}
		throw new NullPointerException(NULLPOINTEREXCEPTION);
	}

	public <T> E[] toArray(E[] a) {
		return null;
	}
	
	@Override
	public void clear() {
		head = null;
		size = 0;
	}

	// ---------------------------------------------- Metodos abstractos ----------------------------------------------
	/**
	 * Este metodo permite agregar un elemento al final de la lista
	 *
	 * @param e elemento que va a agregar a la lista
	 */
	public abstract void addLast(E e);

	/**
	 * Este metodo permite agregar un elemento al principio de la lista
	 *
	 * @param e elemento que va a agregar a la lista
	 */
	public abstract void addFirst(E e);

	/**
	 * Este metodo permite agregar un elemento a la lista de manera ordenada
	 *
	 * @param comparator criterio de ordenamiento con el que agrega a la lista
	 * @param e elemento que agrega en la lista
	 */
	public abstract void addSort(Comparator<E> comparator, E e);

	/**
	 * Este metodo permite agregar un elemento en la lista en la posicion
	 * ingresada por parametro
	 *
	 * @param e elemento que va a agregar en la lista
	 * @param index indice en que que va a agregar un objeto
	 * @throws IndexOutOfBoundsException en caso de que ingrese un indice menor
	 * que 0 o mayor que el indice del ultimo nodo
	 */
	public abstract void add(E e, int index);

	/**
	 * Este metodo permite remover un elemento de la lista
	 *
	 * @param e elemento que buscara y eliminara de la lista
	 */
	public abstract void remove(E e);

	/**
	 * Este metodo permite buscar y eliminar el elemento que este en la posicion
	 * ingresada
	 *
	 * @param index posicion ingresada
	 * @throws IndexOutOfBoundsException en caso de que ingrese un indice menor
	 * que 0 o mayor que el indice del ultimo nodo
	 * @throws NullPointerException en caso de que ingrese una posicion mayor
	 * que 0 y la lista este vacia
	 */
	public abstract void remove(int index);

	/**
	 * Este metodo permite verificar si el objeto ingresado por parametro se
	 * encuentra dentro de la lista enlazada
	 *
	 * @param e elemento que desea corroborar que esta en la lista
	 * @return true si el objeto se encuentra en la lista
	 */
	public abstract boolean contains(E e);
	
	/**
	 * Reemplaza un elemento de la lista
	 * con otro nuevo
	 * 
	 * @param newElement nuevo elemento que colocara en la lista
	 * @param actualElement elemento que desea reemplazar
	 */
	public abstract void set(E newElement, E actualElement);
	
	/**
	 * Este metodo permite obtener un objeto de la estructura de datos, si este existe
	 *
	 * @param e elemento que desea de la lista que desea obtener
	 * @return si el elemento se encuentra dentro de la lista lo obtendra, si no
	 * entonces retornara null
	 */    
	public abstract E get(E e);
}
