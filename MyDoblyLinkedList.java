package co.edu.uptc.prgr3.linearDataStructure;

import java.util.ListIterator;

public abstract class MyDoblyLinkedList<E> extends MySimplyLinkedList<E> {

	// ------------------------------- Atributos ----------------------------------------
	/**
	 * Ultimo elemento de la estructura de datos
	 */
	protected Node<E> last;

	// ------------------------------- Metodos de la clase ---------------------------------------
	/**
	 * Este metodo proporciona un iterador para poder recorrer la lista de
	 * principio a fin o de final a principio
	 *
	 * @return iterador para recorrer la lista
	 */
	public ListIterator<E> listIterator() {
		return new ManagerListIterator<>(head, last, size, this);
	}
	
	@Override
	public MySimplyLinkedList<E> subList(int start, int end) {
		if(start >= 0 && end >= 0 && start < size && end < size) {
			if(start <= end) {
				int accountant = 0;
				Node<E> actual = head;
				while(accountant < start) {
					actual = actual.next;
					accountant++;
				}
				MySimplyLinkedList<E> linkedList = new DoblyLinkedList<>();
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
	public void remove(int index) {
		if(index >= 0 && index < size) {
            if(index > 0 && index < size-1) {
                int accountant = 0;
                Node<E> actual = head;
                while(accountant < (index - 1)) {
                    actual = actual.next;
                    accountant++;
                }
                actual.next = actual.next.next;
                actual.next.previous = actual;
                size--;
            } else if(index == 0)
                removeFirst();
            else if(index == size - 1)
                removeLast();
        } else 
            throw new IndexOutOfBoundsException(INDEXOUTOFBOUNDSEXCEPTION);
	}
	
	@Override
	public void clear() {
		super.clear();
		last = null;
	}

	// --------------------------------- Metodos abtractos -------------------------------------------------
	
	/**
	 * Este metodo permite eliminar
	 * el primer elemento de una lista doblemente enlazada
	 */
	public abstract void removeFirst();

	/**
	 * Este metodo permite eliminar
	 * el ultimo elemento de una lista doblemente enlazada
	 */
	public abstract void removeLast();
}
