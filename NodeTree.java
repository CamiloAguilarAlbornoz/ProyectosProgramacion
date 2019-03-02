package co.edu.uptc.prgr3.recursiveDataStructure;

import java.util.Comparator;

import co.edu.uptc.prgr3.linearDataStructure.SimplyLinkedList;

/**
 * Esta clase es la que guarda
 * el dato que posteriormente sera
 * agregado a un arbol
 * 
 * @author Camilo Andres Aguilar Albornoz
 * @param <E> tipo de objeto del dato que guardara en el nodo
 */
public class NodeTree<E> {

	/**
	 * Dato guardado en el nodo
	 */
	protected E info;

	/**
	 * Factor de balanceo del nodo
	 */
	protected int balance;

	/**
	 * Apuntador al nodo de la derecha
	 */
	protected NodeTree<E> right;

	/**
	 * Apuntador al nodo de la izquierda
	 */
	protected NodeTree<E> left;
	
	/**
	 * Lista de hijos izquierdos
	 */
	protected SimplyLinkedList<NodeTree<E>> leftList;
	
	/**
	 * Lista de hijos derechos
	 */
	protected SimplyLinkedList<NodeTree<E>> rightList;
	
	/**
	 * Criterio de ordenamiento de los hijos
	 */
	protected Comparator<NodeTree<E>> comparator;
	
	/**
	 * Cantidad de datos de la lista de nodos
	 */
	protected int order;
	
	public E getInfo() {
		return info;
	}

	/**
	 * Permite crear un nuevo
	 * nodo con la informacion que va a contener
	 * dentro de esta
	 * @param info dato que guardara en el nodo
	 */
	public NodeTree(E info) {
		this.info = info;
	}
	
	/**
	 * Crea una lista de hijos derechos
	 */
	public void createRightChild() {
		rightList = new SimplyLinkedList<>(comparator, order);
	}
	
	public void createLefhtChild() {
		leftList = new SimplyLinkedList<>(comparator, order);
	}

	public void setRightList(SimplyLinkedList<NodeTree<E>> rightList) {
		this.rightList = rightList;
	}

	public void setLeftList(SimplyLinkedList<NodeTree<E>> leftList) {
		this.leftList = leftList;
	}

	/**
	 * Permite crear un nuevo nodo
	 * con la informacion que va a almacenar
	 * en este, ademas de que tambien recibe
	 * la cantidad de elementos que sus hijos van a poder
	 * guardar
	 * @param comparator criterio de ordenamiento de los datos de los hijos
	 * @param info elemento que va a guardar en el nodo
	 * @param order cantidad de elementos que tendran los hijos
	 */
	public NodeTree(Comparator<NodeTree<E>> comparator, E info, int order) {
		this.order = order;
		this.comparator = comparator;
		leftList = new SimplyLinkedList<>(this.comparator, this.order);
		rightList = new SimplyLinkedList<>(this.comparator, this.order);
		this.info = info;
	}
}
