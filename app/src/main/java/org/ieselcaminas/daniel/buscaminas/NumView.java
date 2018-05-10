package org.ieselcaminas.daniel.buscaminas;

import android.content.Context;
import android.widget.LinearLayout;

import static java.security.AccessController.getContext;

/**
 * Created by alu20927800p on 31/10/17.
 */

public class NumView extends android.support.v7.widget.AppCompatImageView {

    public NumView(Context context, int number) {
        super(context);
        setImageDrawable(getResources().getDrawable(R.drawable.back));
        final float scale = getContext().getResources().getDisplayMetrics().density;

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) (MIneButton.WIDTH * scale),
                (int) (scale*MIneButton.HEIGHT));
        setLayoutParams(layoutParams);

        switch (number) {
            case 0: setImageDrawable(getResources().getDrawable(R.drawable.back));
                break;

            case 1:  setImageDrawable(getResources().getDrawable(R.drawable.number1));
                break;

            case 2:  setImageDrawable(getResources().getDrawable(R.drawable.number2));
                break;

            case 3:  setImageDrawable(getResources().getDrawable(R.drawable.number3));
                break;

            case 4:  setImageDrawable(getResources().getDrawable(R.drawable.number4));
                break;

            case 5:  setImageDrawable(getResources().getDrawable(R.drawable.number5));
                break;

            case 6:  setImageDrawable(getResources().getDrawable(R.drawable.number6));
                break;

            case 7:  setImageDrawable(getResources().getDrawable(R.drawable.number7));
                break;

            case 8:  setImageDrawable(getResources().getDrawable(R.drawable.number8));
                break;


        }



    }
}
