package co.edu.uptc.prgr3.recursiveDataStructure;

import co.edu.uptc.prgr3.linearDataStructure.DoblyLinkedList;
import co.edu.uptc.prgr3.linearDataStructure.MyDoblyLinkedList;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Esta clase permite la creacion de arboles binarios de busqueda
 *
 * @author Camilo Andres Aguilar Albornoz
 * @param <E> tipo de objeto de los datos que se van a agregar al arbol
 */
public class AvlTree<E> extends MyTree<E> {

	protected static final String WARNING_NOT_ELEMENTS = "No hay elementos en el arbol";

	/**
	 * Raiz del arbol
	 */
	protected NodeTree<E> root;

	/**
	 * Permite crear un arbol binario de busqueda con el criterio de
	 * ordenamiento
	 *
	 * @param comparator criterio de ordenamiento del arbol
	 */
	public AvlTree(Comparator<E> comparator) {
		this.comparator = comparator;
		this.root = null;
	}

	@Override
	public boolean isEmpty() {
		return this.root == null;
	}

	@Override
	public void put(E e) {
		if (this.root != null) {
			this.root = put(e, this.root);
		} else {
			this.root = new NodeTree<E>(e);
		}
	}

	@Override
	public E get(E e) {
		if (this.root != null) {
			NodeTree<E> actual = this.root;
			while (actual != null) {
				if (!e.equals(actual.info)) {
					if (comparator.compare(e, actual.info) >= 0) {
						actual = actual.right;
					} else {
						actual = actual.left;
					}
				} else {
					return actual.info;
				}
			}
			return null;
		} else {
			throw new NoSuchElementException(WARNING_NOT_ELEMENTS);
		}
	}

	@Override
	public boolean remove(E e) {
		if (this.root != null) {
			if (!e.equals(this.root.info)) {
				boolean result = remove(e, this.root);
				balanceRoot();
				return result;
			} else {
				removeRoot();
				return true;
			}
		} else {
			return false;
		}
	}

	@Override
	public MyDoblyLinkedList<E> inOrder() {
		MyDoblyLinkedList<E> dobleList = new DoblyLinkedList<>();
		inOrder(this.root, dobleList);
		return dobleList;
	}

	@Override
	public MyDoblyLinkedList<E> preOrder() {
		MyDoblyLinkedList<E> dobleList = new DoblyLinkedList<>();
		preOrder(this.root, dobleList);
		return dobleList;
	}

	@Override
	public MyDoblyLinkedList<E> postOrder() {
		MyDoblyLinkedList<E> dobleList = new DoblyLinkedList<>();
		postOrder(this.root, dobleList);
		return dobleList;
	}

	/**
	 * Permite agregar un nuevo dato a la rama actual
	 *
	 * @param e nuevo dato
	 * @param actual rama actual
	 * @return rama modificada
	 */
	private NodeTree<E> put(E e, NodeTree<E> actual) {
		if (comparator.compare(e, actual.info) >= 0) {
			if (actual.right != null) {
				actual.right = put(e, actual.right);
				actual = balanceRight(actual);
				updateBalance(actual);
				return actual;
			} else {
				actual.right = new NodeTree<E>(e);
				updateBalance(actual);
				return actual;
			}
		} else {
			if (actual.left != null) {
				actual.left = put(e, actual.left);
				actual = balanceLeft(actual);
				updateBalance(actual);
				return actual;
			} else {
				actual.left = new NodeTree<E>(e);
				updateBalance(actual);
				return actual;
			}
		}
	}

	/**
	 * Permite actualizar el factor de balanceo de la actual rama
	 *
	 * @param actual rama a la que actualizara el balanceo
	 */
	private void updateBalance(NodeTree<E> actual) {
		if (actual.right != null) {
			if (actual.left != null) {
				actual.balance = Math.max(actual.right.balance, actual.left.balance) + 1;
			} else {
				actual.balance = actual.right.balance + 1;
			}
		} else if (actual.left != null) {
			actual.balance = actual.left.balance + 1;
		} else {
			actual.balance--;
		}
	}

	/**
	 * Revisa la rama actual para ver si debe ser balanceada a la derecha y de
	 * ser necesario realizada el balanceo
	 *
	 * @param actual rama que desea modificar
	 * @return rama modificada
	 */
	private NodeTree<E> balanceRight(NodeTree<E> actual) {
		if ((getBalance(actual.right) - getBalance(actual.left)) == 2) {
			if (actual.right.left != null) {
				return doubleRotationToTheRigh(actual);
			} else if (actual.right.right != null) {
				return simpleRotationToTheLeft(actual);
			} else if (actual.left.left != null) {
				return simpleRotationToTheRight(actual);
			}
		}
		return actual;
	}

	/**
	 * Revisa la rama actual para ver si debe ser balanceada a la izquierda y de
	 * ser necesario realizada el balanceo
	 *
	 * @param actual rama que desea modificar
	 * @return rama modificada
	 */
	private NodeTree<E> balanceLeft(NodeTree<E> actual) {
		if ((getBalance(actual.left) - getBalance(actual.right)) == 2) {
			if (actual.left.left != null) {
				return simpleRotationToTheRight(actual);
			} else if (actual.left.right != null) {
				return doubleRotationToTheLeft(actual);
			} else if (actual.right.right != null) {
				return simpleRotationToTheLeft(actual);
			}
		}
		return actual;
	}

	/**
	 * Permite obtener el balanceo del nodo actual
	 *
	 * @param actual nodo del cual se quiere obtener el balanceo
	 * @return si el nodo no es nulo retornara el balanceo de este, si es nulo
	 * retornara -1
	 */
	private int getBalance(NodeTree<E> actual) {
		if (actual != null) {
			return actual.balance;
		} else {
			return -1;
		}
	}

	/**
	 * Permite dar una doble rotacion de la rama actual hacia la derecha
	 *
	 * @param actual rama a la que dara una doble rotacion
	 * @return rama modificada
	 */
	private NodeTree<E> doubleRotationToTheRigh(NodeTree<E> actual) {
		actual.right = simpleRotationToTheRight(actual.right);
		return simpleRotationToTheLeft(actual);
	}

	/**
	 * Permite dar una simple rotacion de la rama actual hacia la derecha
	 *
	 * @param actual rama a la que dara una simple rotacion
	 * @return rama modificada
	 */
	private NodeTree<E> simpleRotationToTheRight(NodeTree<E> actual) {
		NodeTree<E> aux = actual.left;
		actual.left = aux.right;
		aux.right = actual;
		aux.right.balance = Math.max(getBalance(aux.right.left), getBalance(aux.right.right)) + 1;
		aux.balance = Math.max(getBalance(aux.right), getBalance(aux.left)) + 1;
		return aux;
	}

	/**
	 * Permite dar una simple rotacion de la rama actual hacia la izquierda
	 *
	 * @param actual rama a la que dara una simple rotacion
	 * @return rama modificada
	 */
	private NodeTree<E> simpleRotationToTheLeft(NodeTree<E> actual) {
		NodeTree<E> aux = actual.right;
		actual.right = aux.left;
		aux.left = actual;
		aux.left.balance = Math.max(getBalance(aux.left.left), getBalance(aux.left.right)) + 1;
		aux.balance = Math.max(getBalance(aux.right), getBalance(aux.left)) + 1;
		return aux;
	}

	/**
	 * Permite dar una doble rotacion de la rama actual hacia la izquierda
	 *
	 * @param actual rama a la que dara una doble rotacion
	 * @return rama modificada
	 */
	private NodeTree<E> doubleRotationToTheLeft(NodeTree<E> actual) {
		actual.left = simpleRotationToTheLeft(actual.left);
		return simpleRotationToTheRight(actual);
	}

	/**
	 * Permite guardar en una lista un elemento del arbol in order
	 *
	 * @param actual rama actual
	 * @param dobleList lista con los elementos guardados
	 * @return lista completada
	 */
	private void inOrder(NodeTree<E> actual, MyDoblyLinkedList<E> dobleList) {
		if (actual != null) {
			inOrder(actual.left, dobleList);
			dobleList.add(actual.info);
			inOrder(actual.right, dobleList);
		}
	}

	/**
	 * Permite guardar en una lista un elemento del arbol pre order
	 *
	 * @param actual rama actual
	 * @param dobleList lista con los elementos guardados
	 * @return lista completada
	 */
	private void preOrder(NodeTree<E> actual, MyDoblyLinkedList<E> dobleList) {
		if (actual != null) {
			dobleList.add(actual.info);
			preOrder(actual.left, dobleList);
			preOrder(actual.right, dobleList);
		}
	}

	/**
	 * Permite guardar en una lista un elemento del arbol post order
	 *
	 * @param actual rama actual
	 * @param dobleList lista con los elementos guardados
	 * @return lista completada
	 */
	private void postOrder(NodeTree<E> actual, MyDoblyLinkedList<E> dobleList) {
		if (actual != null) {
			postOrder(actual.left, dobleList);
			postOrder(actual.right, dobleList);
			dobleList.add(actual.info);
		}
	}

	/**
	 * Permite buscar y eliminar un elemento en la rama u hoja actual
	 *
	 * @param e elemento que desea eliminar
	 * @param actual rama de la que eliminara un elemento
	 * @return true si encontro el elemento
	 */
	private boolean remove(E e, NodeTree<E> actual) {
		if (comparator.compare(e, actual.info) >= 0) {
			if (actual.right != null) {
				if (!e.equals(actual.right.info)) {
					boolean result = remove(e, actual.right);
					actual.right = balanceRight(actual.right);
					updateBalance(actual);
					return result;
				} else {
					removeRight(actual);
					return true;
				}
			}
			return false;
		} else {
			if (actual.left != null) {
				if (!e.equals(actual.left.info)) {
					boolean result = remove(e, actual.left);
					actual.left = balanceLeft(actual.left);
					updateBalance(actual);
					return result;
				} else {
					removeLeft(actual);
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * Elimina la raiz del arbol
	 */
	private void removeRoot() {
		if (this.root.right != null) {
			if (this.root.left != null) {
				this.root.info = getMinorElement(this.root);
				updateBalance(this.root);
			} else {
				this.root = this.root.right;
			}
		} else if (this.root.left != null) {
			this.root = this.root.left;
		} else {
			this.root = null;
		}
	}

	/**
	 * Elimina el elemento que esta a la derecha de la rama actual
	 *
	 * @param actual rama actual
	 */
	private void removeRight(NodeTree<E> actual) {
		if (actual.right.right != null) {
			if (actual.right.left != null) {
				actual.right.info = getMinorElement(actual.right);
				updateBalance(actual);
			} else {
				actual.right = actual.right.right;
				updateBalance(actual);
			}
		} else if (actual.right.left != null) {
			actual.right = actual.right.left;
			updateBalance(actual);
		} else {
			actual.right = null;
			updateBalance(actual);
		}
	}

	/**
	 * Elimina el elemento que esta a la izquierda de la rama actual
	 *
	 * @param actual rama actual
	 */
	private void removeLeft(NodeTree<E> actual) {
		if (actual.left.left != null) {
			if (actual.left.right != null) {
				actual.left.info = getMinorElement(actual.left);
				updateBalance(actual);
			} else {
				actual.left = actual.left.left;
				updateBalance(actual);
			}
		} else if (actual.left.right != null) {
			actual.left = actual.left.right;
			updateBalance(actual);
		} else {
			actual.left = null;
			updateBalance(actual);
		}
	}

	/**
	 * Se encarga de obtener el elemento menor de la rama actual
	 *
	 * @param actual rama actual
	 * @return elemento de la rama menor
	 */
	private E getMinorElement(NodeTree<E> actual) {
		if (actual.right.left != null) {
			NodeTree<E> actualNode = actual.right;
			while (actualNode.left.left != null) {
				actualNode = actualNode.left;
			}
			E item = actualNode.left.info;
			removeLeft(actualNode);
			return item;
		} else {
			E item = actual.right.info;
			removeRight(actual);
			return item;
		}
	}

	/**
	 * Este metodo permite balancear la raiz del arbol
	 */
	private void balanceRoot() {
		if ((getBalance(this.root.right) - getBalance(this.root.left)) == 2) {
			if (this.root.right.left != null) {
				this.root = doubleRotationToTheRigh(this.root);
			} else if (this.root.right.right != null) {
				this.root = simpleRotationToTheLeft(this.root);
			} else if (this.root.left.left != null) {
				this.root = simpleRotationToTheRight(this.root);
			}
		} else if ((getBalance(this.root.left) - getBalance(this.root.right)) == 2) {
			if (this.root.left.left != null) {
				this.root = simpleRotationToTheRight(this.root);
			} else if (this.root.left.right != null) {
				this.root = doubleRotationToTheLeft(this.root);
			} else if (this.root.right.right != null) {
				this.root = simpleRotationToTheLeft(this.root);
			}
		}
	}
}