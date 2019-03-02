package co.edu.uptc.prgr3.scatteredMatrices;

import co.edu.uptc.prgr3.linearDataStructure.SimplyLinkedList;

import java.util.Comparator;

/**
 * Esta clase permite crear
 * matrices dispersas, de acuerdo al tipo
 * de fila y columna que esta tendra, y tambien
 * al tipo de objeto de los elementos que va a guardar
 * 
 * @author Camilo Andres Aguilar Albornoz
 * @param <RT> tipo de fila de la matriz
 * @param <CT> tipo de columna de la matriz
 * @param <E> tipo de objeto del elemento que guardara en la matriz
 */
public class MyMatrix<RT, CT, E> {
    
    /**
     * Lista de filas de la matriz
     */
    private SimplyLinkedList<MyHeader<RT, E>> rowsList;
    
    /**
     * Lista de columnas de la matriz
     */
    private SimplyLinkedList<MyHeader<CT, E>> columnsList;
    
    /**
     * Advertencia de posicion ocupada
     */
    private static final String ILLEGAL_ARGUMENT_EXCEPTION = "Ha ingresado una posicion ocupada";
    
    /**
     * Criterio de ordenamiento de la lista de filas

    /**
     * Permite crear una matriz dispersa recibiendo
     * el criterio de ordenamiento de sus filas y columnas
     * 
     * @param comparatorRow criterio de ordenamiento de las filas
     * @param comparatorColumn criterio de ordenamiento de las columnas
     */
    public MyMatrix(Comparator<MyHeader<RT, E>> comparatorRow, Comparator<MyHeader<CT, E>> comparatorColumn) {
        rowsList = new SimplyLinkedList<>(comparatorRow);
        columnsList = new SimplyLinkedList<>(comparatorColumn);
    }
    
    /**
     * Este metodo permite colocar
     * un nuevo elemento dentro de la matriz
     * @param rT fila en la que agregara el objeto
     * @param cT columna en la que agregara el objeto
     * @param e nuevo elemento que va a agregar
     */
    public void put(RT rT, CT cT, E e) {
        MyHeader<RT, E> row = existOrCreateRow(rT);
        MyHeader<CT, E> column = existOrCreateColumn(cT);
        MyCell<E> cell = new MyCell<>(e);
        putInRow(row, column, cell);
        putInColumn(row, column, cell);
    }
    
    /**
     * Este metodo retorna en una lista
     * enlazada todos los elementos de la matriz
     * recorriendola tomando primero todos los elementos
     * de cada fila
     * @return lista enlazada con todos los elementos de la matriz
     */
    public SimplyLinkedList<E> getAllElementsForRows() {
        SimplyLinkedList<E> linkedList = new SimplyLinkedList<>();
        for (MyHeader<RT, E> row : rowsList) {
            MyCell<E> cell = row.firstCell;
            while(cell != null) {
                linkedList.add(cell.element);
                cell = cell.right;
            }
        }
        return linkedList;
    }
    
    /**
     * Este metodo retorna en una lista
     * enlazada todos los elementos de la matriz
     * recorriendola tomando primero todos los elementos
     * de cada columna
     * @return lista enlazada con todos los elementos de la matriz
     */
    public SimplyLinkedList<E> getAllElementsForColumns() {
        SimplyLinkedList<E> linkedList = new SimplyLinkedList<>();
        for (MyHeader<CT, E> column : columnsList) {
            MyCell<E> cell = column.firstCell;
            while(cell != null) {
                linkedList.add(cell.element);
                cell = cell.down;
            }
        }
        return linkedList;
    }
    
    /**
     * Permite reemplazad un elemento
     * de la matriz
     * @param rT fila donde esta el elemento que va a reemplazar
     * @param cT columna donde esta el elemento que va a reemplazar
     * @param e elemento con el que va a reemplazar
     * @return si la fila y columna ingresada existen, realiza el reemplazo de datos retornando el elemento original, de lo contrario retorna null
     */
    public E replace(RT rT, CT cT, E e) {
        if(existRow(rT) && existColumn(cT)) {
            MyHeader<RT, E> row = existOrCreateRow(rT);
            MyHeader<CT, E> column = existOrCreateColumn(cT);
            return replace(row, column, e);
        }
        return null;
    }
    
    /**
     * Permite obtener el elemento que esta
     * en la fila y columna indicada
     * @param rT fila donde esta el objeto
     * @param cT columna donde esta el objeto
     * @return si la fila y columna ingresada existen, retorna el elemento en estas, de lo contrario retorna null
     */
    public E get(RT rT, CT cT) {
        if(existRow(rT) && existColumn(cT)) {
            MyHeader<RT, E> row = existOrCreateRow(rT);
            MyHeader<CT, E> column = existOrCreateColumn(cT);
            return get(row, column);
        }
        return null;
    }
    
    /**
     * Este metodo se encarga de
     * eliminar el elemento que este en las posiciones
     * indicadas
     * @param rT fila donde esta el elemento
     * @param cT columna donde esta el elemento
     */
    public void remove(RT rT, CT cT) {
        if(existRow(rT) && existColumn(cT)) {
            MyHeader<RT, E> row = existOrCreateRow(rT);
            MyHeader<CT, E> column = existOrCreateColumn(cT);
            remove(row, column);
        }
    }

    // --------------------------- Metodos encapsulados -------------------------
    private MyHeader<RT, E> existOrCreateRow(RT rT) {
        for (MyHeader<RT, E> row : rowsList) {
            if (rT.equals(row.headerType))
                return row;
        }
        MyHeader<RT, E> row = new MyHeader<>(rT);
        rowsList.add(row);
        return row;
    }

    private MyHeader<CT, E> existOrCreateColumn(CT cT) {
        for (MyHeader<CT, E> column : columnsList) {
            if (cT.equals(column.headerType))
                return column;
        }
        MyHeader<CT, E> column = new MyHeader<>(cT);
        columnsList.add(column);
        return column;
    }

    private void putInRow(MyHeader<RT, E> row, MyHeader<CT, E> column, MyCell<E> cell) {
        if (row.firstCell != null) {
            MyHeader<CT, E> columUbic = findPoisitionInColumn(row.firstCell);
            int positionToAdd = columnsList.comparation(column, columUbic, columnsList.getOrderingCriterion());
            if (positionToAdd < 0) 
                row.putInFirstRight(cell);
             else if (positionToAdd > 0) 
                row.putInSortRight(cell, positionToAdd);
             else 
                throw new IllegalArgumentException(ILLEGAL_ARGUMENT_EXCEPTION);
        } else 
            row.firstCell = cell;
    }
    
    private void putInColumn(MyHeader<RT, E> row, MyHeader<CT, E> column, MyCell<E> cell) {
        if (column.firstCell != null) {
            MyHeader<RT, E> rowUbic = findPositionInRow(column.firstCell);
            int positionToAdd = rowsList.comparation(row, rowUbic, rowsList.getOrderingCriterion());
            if (positionToAdd < 0)
                column.putInFirstDown(cell);
            else if (positionToAdd > 0)
                column.putInSortDown(cell, positionToAdd);
            else
                throw new IllegalArgumentException(ILLEGAL_ARGUMENT_EXCEPTION);
        } else {
            column.firstCell = cell;
        }
    }

    private MyHeader<CT, E> findPoisitionInColumn(MyCell<E> firstCell) {
        for (MyHeader<CT, E> column : columnsList) {
            MyCell<E> cell = column.firstCell;
            while(cell != null) {
                if(firstCell.equals(cell))
                    return column;
                cell = cell.down;
            }
        }
        return null;
    }
    
    private MyHeader<RT, E> findPositionInRow(MyCell<E> firstCell) {
        for (MyHeader<RT, E> row : rowsList) {
            MyCell<E> cell = row.firstCell;
            while(cell != null) {
                if(firstCell.equals(cell))
                    return row;
                cell = cell.right;
            }
        }
        return null;
    }

    private boolean existRow(RT rT) {
        for (MyHeader<RT, E> row : rowsList) {
            if (rT.equals(row.headerType))
                return true;
        }
        return false;
    }

    private boolean existColumn(CT cT) {
        for (MyHeader<CT, E> column : columnsList) {
            if (cT.equals(column.headerType))
                return true;
        }
        return false;
    }

    private E replace(MyHeader<RT, E> row, MyHeader<CT, E> column, E e) {
        MyCell<E> auxRow = row.firstCell;
        MyCell<E> auxColumn = column.firstCell;
        while(auxColumn != null) {
            while(auxRow != null) {
                if (!auxColumn.equals(auxRow))
                    auxRow = auxRow.right;
                else {
                    E item = auxRow.element;
                    auxRow.element = e;
                    return item;
                }
            }
            auxColumn = auxColumn.down;
            auxRow = row.firstCell;
        }
        return null;
    }

    private E get(MyHeader<RT, E> row, MyHeader<CT, E> column) {
        MyCell<E> auxRow = row.firstCell;
        MyCell<E> auxColumn = column.firstCell;
        while(auxColumn != null) {
            while(auxRow != null) {
                if (!auxColumn.equals(auxRow))
                    auxRow = auxRow.right;
                else {
                    return auxRow.element;
                }
            }
            auxColumn = auxColumn.down;
            auxRow = row.firstCell;
        }
        return null;
    }

    private void remove(MyHeader<RT, E> row, MyHeader<CT, E> column) {
        E dataFind = get(row, column);
        row.removeRight(dataFind);
        column.removeDown(dataFind);
        if (row.firstCell == null) {
            rowsList.remove(row);
        }
        if(column.firstCell == null) {
            columnsList.remove(column);
        }
    }
}