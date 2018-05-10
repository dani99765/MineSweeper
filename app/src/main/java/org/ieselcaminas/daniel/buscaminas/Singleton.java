package org.ieselcaminas.daniel.buscaminas;

/**
 * Created by alu20927800p on 18/10/17.
 */

public class Singleton {
    private static Singleton shared;

    private int numRows, numCols;
    private int numBombs;

    private Singleton() {
        numRows=30;
        numCols=30;
        numBombs=50;
    }

    public static Singleton sharedInstance() {
        if(shared==null)
            shared = new Singleton();
            return shared;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public void setNumCols(int numCols) {
        this.numCols = numCols;
    }

    public void setNumBombs(int numBombs) {
        this.numBombs = numBombs;
    }

    public int getNumRows() {

        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public int getNumBombs() {
        return numBombs;
    }
}
