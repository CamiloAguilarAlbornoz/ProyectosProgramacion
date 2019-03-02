package co.edu.uptc.prgr3.recursiveDataStructure;

import java.util.Comparator;

import co.edu.uptc.prgr3.linearDataStructure.MyDoblyLinkedList;

/**
 * Esta interface proporciona
 * todos los metodos que seran de importancia
 * para la creacion de un arbol
 * @author Camilo Andres Aguilar Albornoz
 *
 * @param <E> tipo de objeto del arbol
 */
public abstract class MyTree<E> {
	
	/**
	 * Criterio de ordenamiento para agregar al arbol
	 */
	protected Comparator<E> comparator;
	
	/**
	 * Se encarga de comprobar
	 * si el arbol es vacio
	 * @return si la raiz devuelve NULL entonces retorna true
	 */
	public abstract boolean isEmpty();
	
	/**
	 * Permite agregar un
	 * nuevo elemento al arbol
	 * @param e nuevo elemento del arbol
	 */
	public abstract void put(E e);
	
	/**
	 * Permite buscar y retornar
	 * un elemento del arbol
	 * @param e elemento que desea obtener
	 * @return si encontro el elemento lo retornara, si no lo encontro retornara NULL
	 */
	public abstract E get(E e);
	
	/**
	 * Permite buscar y eliminar
	 * un elemento del arbol
	 * @param e elemento que desea eliminar
	 * @return true si encontro el elemento a eliminar
	 */
	public abstract boolean remove(E e);
	
	/**
	 * Permite recorrer el arbol in order
	 * @return lista con todos los elementos del arbol guardados in Order
	 */
	public abstract MyDoblyLinkedList<E> inOrder();
	
	/**
	 * Permite recorrer el arbol pre order
	 * @return lista con todos los elementos del arbol guardados pre Order
	 */
	public abstract MyDoblyLinkedList<E> preOrder();
	
	/**
	 * Permite recorrer el arbol post order
	 * @return lista con todos los elementos del arbol guardados post Order
	 */
	public abstract MyDoblyLinkedList<E> postOrder();
}
