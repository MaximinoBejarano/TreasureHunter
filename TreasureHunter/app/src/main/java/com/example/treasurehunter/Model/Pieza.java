package com.example.treasurehunter.Model;

import android.content.Context;

public class Pieza extends android.support.v7.widget.AppCompatImageView {
    public int xCoord;
    public int yCoord;
    public int piezaAncho;
    public int piezaAlto;
    public boolean puedeMover = true;

    public Pieza(Context context) {
        super(context);
    }
}
