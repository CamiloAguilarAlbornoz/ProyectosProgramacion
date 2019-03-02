package co.edu.uptc.prgr3.linearDataStructure;

/**
 * Esta clase permite almacenar el objeto que agregue a una estructura de datos,
 * ademas de que proporciona los enlaces a los siguientes nodos
 * 
 * @author Camilo Andres Aguilar Albornoz
 */
class Node<E> {

	// ---------------------------- Atributos --------------------------------------------------
	/**
	 * Elemento que va a guardar en el nodo
	 */
	public E element;

	/**
	 * Nodo siguiente
	 */
	public Node<E> next;

	/**
	 * Nodo anterior
	 */
	public Node<E> previous;

	// ------------------------------ Metodos contructores -------------------------------------

	/**
	 * Este constructor permite crear un nodo con el elemento que va a guardar
	 * en este
	 *
	 * @param element elemento que va a guardar en el nodo
	 */
	public Node(E element) {
		this.element = element;
	}

	/**
	 * Este constructor permite crear un nodo con el emento que va a guardar en
	 * este y con el enlace al nodo siguiente
	 *
	 * @param element elemento que va a guardar en el nodo
	 * @param next enlace al nodo siguiente
	 */
	public Node(E element, Node<E> next) {
		this.element = element;
		this.next = next;
	}

	/**
	 * Este constructor permite crear un nodo con el elemento que va a guardar
	 * en este y con los enlaces al siguiente y anterior elemento
	 *
	 * @param element elemento que va a guardar en el nodo
	 * @param next enlace al nodo siguiente
	 * @param previous enlace al nodo anterior
	 */
	public Node(E element, Node<E> next, Node<E> previous) {
		this.element = element;
		this.next = next;
		this.previous = previous;
	}
}

