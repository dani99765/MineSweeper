package org.ieselcaminas.daniel.buscaminas;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import static android.R.attr.version;

/**
 * Created by alu20927800p on 18/10/17.
 */

public class MIneButton extends android.support.v7.widget.AppCompatImageButton {
    public static final int WIDTH=30;
    public static final int HEIGHT=30;
    private int col;
    private int row;
    private ButtonState state;

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public MIneButton(Context c, int row, int col) {
        super(c);
        this.row=row;
        this.col=col;
        state=ButtonState.CLOSED;

        final float scale=getContext().getResources().getDisplayMetrics().density;
        int width=(int) (WIDTH*scale);
        int heigth=(int) (HEIGHT*scale);

        android.view.ViewGroup.LayoutParams params = new FrameLayout.LayoutParams(width,heigth);
        setLayoutParams(params);

        setBackgroundDrawable(getResources().getDrawable(R.drawable.boton));

    }

    public void setState(ButtonState state) {
        this.state=state;
    }

    public ButtonState getState() {
        return state;
    }






}
