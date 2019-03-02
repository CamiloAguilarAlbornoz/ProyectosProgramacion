package co.edu.uptc.prgr3.linearDataStructure;

import java.util.ListIterator;

/**
/**
 * Esta clase se encarga de manejer el iterador para poder recorrer la lista de
 * principio a fin y de fin a principio
 *
 * @author Camilo Andres Aguilar Albornoz
 */
class ManagerListIterator<E> implements ListIterator<E> {

	/**
     * Actual elemento cuando la lista se esta recorriendo de principio a fin
     */
    protected Node<E> actualFirst;

    /**
     * Actual elemento cuando la lista se esta recorriendo de fin a principio
     */
    protected Node<E> actualLast;

    /**
     * Actual primera posicion cuando la lista se esta recorriendo de principio
     * a fin
     */
    protected int actualFirstPosition;

    /**
     * Actual ultima posicion cuando la lista se esta recorriendo de fin a
     * principio
     */
    protected int actualLastPosition;

    /**
     * Tamanio de la lista
     */
    private final int size;
    
    /**
     * Ultimo elemento llamado por el metodo next() o previous()
     */
    private E lastElementCall;
    
    /**
     * Lista doblemente enlazada que esta recorriendo
     */
    private MyDoblyLinkedList<E> doblyLinkedList;

    /**
     * Este constructor permite crear un iterador con los elementos a partir de
     * los cuales recorrera la lista
     *
     * @param actualFirst actual primer elemento
     * @param actualLast actual final elemento
     */
    public ManagerListIterator(Node<E> actualFirst, Node<E> actualLast, int actualLastPosition, MyDoblyLinkedList<E> doblyLinkedList) {
        this.actualFirst = actualFirst;
        this.actualLast = actualLast;
        this.actualLastPosition = actualLastPosition;
        this.size = actualLastPosition;
        this.doblyLinkedList = doblyLinkedList;
    }

    @Override
    public boolean hasNext() {
        return actualFirstPosition < size;
    }

    @Override
    public E next() {
        E item = lastElementCall = actualFirst.element;
        actualFirst = actualFirst.next;
        actualFirstPosition++;
        return item;
    }

    @Override
    public boolean hasPrevious() {
        return actualLastPosition > 0;
    }

    @Override
    public E previous() {
        E item = lastElementCall = actualLast.element;
        actualLast = actualLast.previous;
        actualLastPosition--;
        return item;
    }

    @Override
    public int nextIndex() {
        return actualFirstPosition;
    }

    @Override
    public int previousIndex() {
        return actualLastPosition;
    }

    @Override
    public void remove() {
        doblyLinkedList.remove(lastElementCall);
    }

    @Override
    public void set(E e) {
        doblyLinkedList.set(e, lastElementCall);
    }

    @Override
    public void add(E e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
