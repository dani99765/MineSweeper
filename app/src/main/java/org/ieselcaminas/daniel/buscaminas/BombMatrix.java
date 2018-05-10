package org.ieselcaminas.daniel.buscaminas;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

/**
 * Created by alu20927800p on 24/10/17.
 */

public class BombMatrix {
    private int[][] bombMatrix;

    public BombMatrix() {
        bombMatrix = new int[Singleton.sharedInstance().getNumRows()] [Singleton.sharedInstance().getNumCols()];

        resetMatrix();
    }

    private boolean isBombNext(int row, int col) {
        if(row>=0 && row<Singleton.sharedInstance().getNumRows() && col>=0 && col<Singleton.sharedInstance().getNumCols()) {
            if(bombMatrix[row][col] == -1) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void resetMatrix() {
        for(int i=0; i<Singleton.sharedInstance().getNumRows(); i++) {
            for(int j=0; j<Singleton.sharedInstance().getNumCols(); j++) {
                bombMatrix[i][j]=0;
            }
        }

        int numBombs=0;
        int row, col;
        Random random = new Random();

        while(numBombs<Singleton.sharedInstance().getNumBombs()) {
            row=random.nextInt(Singleton.sharedInstance().getNumRows());
            col=random.nextInt(Singleton.sharedInstance().getNumCols());

            if(bombMatrix[row][col] != -1) {
                bombMatrix[row][col]=-1;
                numBombs++;
            }
        }

        for(int i=0; i<Singleton.sharedInstance().getNumRows(); i++) {
            for(int j=0; j<Singleton.sharedInstance().getNumCols(); j++) {
                numBombs=0;

                if(bombMatrix[i][j]!=-1) {
                    if(isBombNext(i-1, j-1)) numBombs++;
                    if(isBombNext(i-1, j)) numBombs++;
                    if(isBombNext(i-1, j+1)) numBombs++;
                    if(isBombNext(i, j-1)) numBombs++;
                    if(isBombNext(i, j+1)) numBombs++;
                    if(isBombNext(i+1, j-1)) numBombs++;
                    if(isBombNext(i+1, j)) numBombs++;
                    if(isBombNext(i+1, j+1)) numBombs++;
                    bombMatrix[i][j]=numBombs;

                }
            }
        }


    }

    public View getTextView(Context context, int row, int col) {
        View textView = new TextView(context);
        return textView;

    }

    public int getValue(int row, int col) {

        return bombMatrix[row][col];
    }

}
