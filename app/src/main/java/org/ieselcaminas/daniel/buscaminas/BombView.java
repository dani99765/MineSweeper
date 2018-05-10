package org.ieselcaminas.daniel.buscaminas;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import static java.security.AccessController.getContext;

/**
 * Created by alu20927800p on 20/10/17.
 */

public class BombView extends android.support.v7.widget.AppCompatImageView {


    public BombView(Context context) {
        super(context);

        setImageDrawable(getResources().getDrawable(R.drawable.bomb));
        final float scale = getContext().getResources().getDisplayMetrics().density;

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) (MIneButton.WIDTH * scale),
                (int) (scale*MIneButton.HEIGHT));
        setLayoutParams(layoutParams);
    }
}
