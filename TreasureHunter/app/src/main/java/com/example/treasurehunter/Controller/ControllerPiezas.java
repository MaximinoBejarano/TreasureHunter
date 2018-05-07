package com.example.treasurehunter.Controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.example.treasurehunter.Model.Pieza;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class ControllerPiezas {
    protected int Columnas;
    protected int Filas;
    protected int numPiesas;
    protected ImageView imgPrincipal;
    public Context contexto;

    public ControllerPiezas(ImageView imgPrincipal, Context context, int columna, int filas) {
        Columnas = columna;
        Filas = filas;
        this.imgPrincipal = imgPrincipal;
        this.numPiesas = filas*columna;
        this.contexto=context;
    }

    public ArrayList<Pieza> DividirImagen(){

        ArrayList<Pieza> piesas = new ArrayList<>(numPiesas);

        // Se imagen a bitmap
        BitmapDrawable drawable = (BitmapDrawable) imgPrincipal.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        //Se obtiene la posición del bitmap dentro de ImageView
        int[] dimension = ObtenerPosicionImageView(imgPrincipal);
        int scaledBitmapLeft = dimension[0];
        int scaledBitmapTop = dimension[1];
        int scaledBitmapWidth = dimension[2];
        int scaledBitmapHeight = dimension[3];

        //Ancho y altura de la imagen a recortar
        //abs-> Valor absoluto
        int croppedImageWidth = scaledBitmapWidth - 2 * abs(scaledBitmapLeft);
        int croppedImageHeight = scaledBitmapHeight - 2 * abs(scaledBitmapTop);

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, scaledBitmapWidth, scaledBitmapHeight, true);
        Bitmap croppedBitmap = Bitmap.createBitmap(scaledBitmap, abs(scaledBitmapLeft), abs(scaledBitmapTop), croppedImageWidth, croppedImageHeight);

        // Calcular el ancho y alto de cada  pieza
        int piesaWidth = croppedImageWidth/Columnas;
        int piesaHeight = croppedImageHeight/Filas;

        //  Crea cada pieza del bitmap y la agréga al arreglo resultante
        int yCoord = 0;
        for (int row = 0; row < Filas; row++) {
            int xCoord = 0;
            for (int col = 0; col < Columnas; col++) {
                // calcula el desplazamiento de cada pieza
                int offsetX = 0;
                int offsetY = 0;
                if (col > 0) {
                    offsetX = piesaWidth / 3;
                }
                if (row > 0) {
                    offsetY = piesaHeight / 3;
                }

                // aplica el desplazamiento en "X" y "Y" de la imagen para recortar cada piesa
                Bitmap pieceBitmap = Bitmap.createBitmap(croppedBitmap, xCoord - offsetX, yCoord - offsetY, piesaWidth + offsetX, piesaHeight + offsetY);
                Pieza pieza = new Pieza(contexto);
                pieza.setImageBitmap(pieceBitmap);
                pieza.xCoord = xCoord - offsetX + imgPrincipal.getLeft();
                pieza.yCoord = yCoord - offsetY + imgPrincipal.getTop();
                pieza.piezaAncho = piesaWidth + offsetX;
                pieza.piezaAlto = piesaHeight + offsetY;

                //este bitmap mantendrá nuestra imagen final de la pieza del rompecabezas
                Bitmap Pieza_rompecabeza = Bitmap.createBitmap(piesaWidth + offsetX, piesaHeight + offsetY, Bitmap.Config.ARGB_8888);

                /*
                Se da la forma de rompecabezas a las piezas
                se dibuja un camino usando el código y luego se enmascarar la pieza
                 */
                int bumpSize = piesaHeight / 4;
                Canvas canvas = new Canvas(Pieza_rompecabeza);
                Path path = new Path();
                path.moveTo(offsetX, offsetY);
                if (row == 0) {
                    // pieza lateral superior
                    path.lineTo(pieceBitmap.getWidth(), offsetY);
                } else {
                    // Ensamblaje superior
                    path.lineTo(offsetX + (pieceBitmap.getWidth() - offsetX) / 3, offsetY);
                    path.cubicTo(offsetX + (pieceBitmap.getWidth() - offsetX) / 6, offsetY - bumpSize, offsetX + (pieceBitmap.getWidth() - offsetX) / 6 * 5, offsetY - bumpSize, offsetX + (pieceBitmap.getWidth() - offsetX) / 3 * 2, offsetY);
                    path.lineTo(pieceBitmap.getWidth(), offsetY);
                }

                if (col == Columnas - 1) {
                    // pieza lateral derecha
                    path.lineTo(pieceBitmap.getWidth(), pieceBitmap.getHeight());
                } else {
                    // Ensamblaje derecho
                    path.lineTo(pieceBitmap.getWidth(), offsetY + (pieceBitmap.getHeight() - offsetY) / 3);
                    path.cubicTo(pieceBitmap.getWidth() - bumpSize,offsetY + (pieceBitmap.getHeight() - offsetY) / 6, pieceBitmap.getWidth() - bumpSize, offsetY + (pieceBitmap.getHeight() - offsetY) / 6 * 5, pieceBitmap.getWidth(), offsetY + (pieceBitmap.getHeight() - offsetY) / 3 * 2);
                    path.lineTo(pieceBitmap.getWidth(), pieceBitmap.getHeight());
                }

                if (row == Filas - 1) {
                    // pieza lateral inferior
                    path.lineTo(offsetX, pieceBitmap.getHeight());
                } else {
                    //Ensamblaje inferior
                    path.lineTo(offsetX + (pieceBitmap.getWidth() - offsetX) / 3 * 2, pieceBitmap.getHeight());
                    path.cubicTo(offsetX + (pieceBitmap.getWidth() - offsetX) / 6 * 5,pieceBitmap.getHeight() - bumpSize, offsetX + (pieceBitmap.getWidth() - offsetX) / 6, pieceBitmap.getHeight() - bumpSize, offsetX + (pieceBitmap.getWidth() - offsetX) / 3, pieceBitmap.getHeight());
                    path.lineTo(offsetX, pieceBitmap.getHeight());
                }

                if (col == 0) {
                    // pieza lateral izquierda
                    path.close();
                } else {
                    // Ensamblaje Izquierdo
                    path.lineTo(offsetX, offsetY + (pieceBitmap.getHeight() - offsetY) / 3 * 2);
                    path.cubicTo(offsetX - bumpSize, offsetY + (pieceBitmap.getHeight() - offsetY) / 6 * 5, offsetX - bumpSize, offsetY + (pieceBitmap.getHeight() - offsetY) / 6, offsetX, offsetY + (pieceBitmap.getHeight() - offsetY) / 3);
                    path.close();
                }

                // mascara de la piesa
                Paint paint = new Paint();
                paint.setColor(0XFF000000);
                paint.setStyle(Paint.Style.FILL);

                canvas.drawPath(path, paint);
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                canvas.drawBitmap(pieceBitmap, 0, 0, paint);

                // Dibujar el borde blanco a la piesa
                Paint borde = new Paint();
                borde.setColor(0X80FFFFFF);
                borde.setStyle(Paint.Style.STROKE);
                borde.setStrokeWidth(8.0f);
                canvas.drawPath(path, borde);

                // Dibujar el borde negro a la piesa
                borde = new Paint();
                borde.setColor(0X80000000);
                borde.setStyle(Paint.Style.STROKE);
                borde.setStrokeWidth(3.0f);
                canvas.drawPath(path, borde);

                // establecer el bitmap resultante a la pieza
                pieza.setImageBitmap(Pieza_rompecabeza);

                piesas.add(pieza);
                xCoord += piesaWidth;
            }
            yCoord += piesaHeight;
        }

        return piesas;

    }

    private int[]  ObtenerPosicionImageView(ImageView imageView) {
        int[] ret = new int[4];

        if (imageView == null || imageView.getDrawable() == null)
            return ret;

        // Se obtiene la dimencion de la imagen
        // Se obtinen los valores de la matriz de la imagen y se colocan en la matriz
        float[] f = new float[9];
        imageView.getImageMatrix().getValues(f);

        // Se extrae los valores de las escalas, usando las cosntantes(escalaX, escalay)
        final float escaleX = f[Matrix.MSCALE_X];
        final float escaleY = f[Matrix.MSCALE_Y];

        // Se obtiene el drawable de la imagen para obtener su tamaño original
        final Drawable d = imageView.getDrawable();
        final int origW = d.getIntrinsicWidth();
        final int origH = d.getIntrinsicHeight();

        // Se calcula la dimencion actual
        final int actW = Math.round(origW * escaleX);
        final int actH = Math.round(origH * escaleY);

        ret[2] = actW;
        ret[3] = actH;

        // se obtine la dimencion atual del ImageView
        //
        int imgViewW = imageView.getWidth();
        int imgViewH = imageView.getHeight();

        int top = (int) (imgViewH - actH)/2;
        int left = (int) (imgViewW - actW)/2;

        ret[0] = left;
        ret[1] = top;

        return ret;
    }
}
