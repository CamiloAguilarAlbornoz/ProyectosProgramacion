package co.edu.uptc.prgr3.scatteredMatrices;

/**
 * Esta clase contiene el elemento
 * que va a ser guardado dentro de la matriz dispersa
 * 
 * @author Camilo Andres Aguilar Albornoz
 * @param <E> tipo de objeto del elemento que va a guardar en la matriz
 */
class MyCell<E> {
    
    /**
     * Elemento que se va a guardar en la celda
     */
    protected E element;
    
    /**
     * Apuntador a la celda de la derecha
     */
    protected MyCell<E> right;
    
    /**
     * Apuntador a la celda de abajo
     */
    protected MyCell<E> down;

    /**
     * Permite crear una celda con 
     * el nuevo elemento
     * @param element elemento que va a guardar en la celda
     */
    public MyCell(E element) {
        this.element = element;
    }
}
