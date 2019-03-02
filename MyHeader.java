package co.edu.uptc.prgr3.scatteredMatrices;

/**
 * Esta clase se encarga de 
 * crear el encabezado con las distintas
 * celdas que lo van a constituir
 * 
 * @author Camilo Andres Aguilar Albornoz
 * @param <HT> tipo de encabezado
 * @param <E> tipo de objeto del elemento que va a guardar en la celda
 */
public class MyHeader<HT, E> {

	/**
	 * Tipo de encabezado del header
	 */
	protected HT headerType;

	/**
	 * Primer celda del encabezado
	 */
	protected MyCell<E> firstCell;

	public HT getHeaderType() {
		return headerType;
	}

	/**
	 * Permite crear un encabezado
	 * con el tipo de este
	 * @param headerType tipo de encabezado
	 */
	public MyHeader(HT headerType) {
		this.headerType = headerType;
	}

	protected void putInFirstRight(MyCell<E> cell) {
		MyCell<E> auxCell = firstCell;
		firstCell = cell;
		firstCell.right = auxCell;
	}

	protected void putInFirstDown(MyCell<E> cell) {
		MyCell<E> auxCell = firstCell;
		firstCell = cell;
		firstCell.down = auxCell;
	}

	protected void putInSortRight(MyCell<E> cell, int positionToAdd) {
		positionToAdd--;
		int accountant = 0;
		MyCell<E> auxCell = firstCell;
		while(accountant < positionToAdd && auxCell.right != null) {
			auxCell = auxCell.right;
			accountant++;
		}
		cell.right = auxCell.right;
		auxCell.right = cell;
	}

	protected void putInSortDown(MyCell<E> cell, int positionToAdd) {
		positionToAdd--;
		int accountant = 0;
		MyCell<E> auxCell = firstCell;
		while(accountant < positionToAdd && auxCell.down != null) {
			auxCell = auxCell.down;
			accountant++;
		}
		cell.down = auxCell.down;
		auxCell.down = cell;
	}

	protected void removeRight(E e) {
		if (!e.equals(firstCell.element)) {
			MyCell<E> actual = firstCell;
			while (actual.right != null) {
				if (!e.equals(actual.right.element))
					actual = actual.right;
				else if (actual.right.right != null)
					actual.right = actual.right.right;
				else
					actual.right = null;
			}
		} else if (firstCell.right != null) {
			firstCell = firstCell.right;
		} else 
			firstCell = null;
	}

	protected void removeDown(E e) {
		if (!e.equals(firstCell.element)) {
			MyCell<E> actual = firstCell;
			while (actual.down != null) {
				if (!e.equals(actual.down.element))
					actual = actual.down;
				else if (actual.down.down != null)
					actual.down = actual.down.down;
				else
					actual.down = null;
			}
		} else if (firstCell.down != null) {
			firstCell = firstCell.down;
		} else 
			firstCell = null;
	}
}