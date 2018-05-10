package org.ieselcaminas.daniel.buscaminas;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by alu20927800p on 18/10/17.
 */

public class Cell extends android.support.v7.widget.AppCompatImageView {

    public Cell(Context context) {
        super(context);
        setImageDrawable(getResources().getDrawable(R.drawable.back));
        final float scale = getContext().getResources().getDisplayMetrics().density;

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) (MIneButton.WIDTH * scale),
                                                (int) (scale*MIneButton.HEIGHT));
        setLayoutParams(layoutParams);

    }
}
