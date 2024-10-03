package com.upiita.graficasconfunciones;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GraficaFunciones grafica = new GraficaFunciones(this);
        setContentView(grafica);
    }

    private class GraficaFunciones extends View {
        public GraficaFunciones(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint pintura = new Paint();
            pintura.setColor(Color.rgb(249, 150, 176));
            canvas.drawPaint(pintura);

            int ancho = canvas.getWidth();
            int alto = canvas.getHeight();

            pintura.setColor(Color.WHITE);
            pintura.setTextSize(40);
            pintura.setStrokeWidth(5);

            for (int n = 0; n < alto; n += 50) {
                canvas.drawLine(0, n, ancho, n, pintura);
                canvas.drawText("" + n, ancho - 100, n, pintura);
            }

            // Gráfico 1
            pintura.setColor(Color.BLACK);
            pintura.setStrokeWidth(8);
            canvas.drawLine(50, 400, ancho - 50, 400, pintura); // Subido de 500 a 400
            canvas.drawLine(50, 150, 50, 650, pintura); // Subido de 250-750 a 150-650

            float t, f, x;
            int A = 200;
            f = (float) 3 / (ancho - 100);
            Path funcionx = new Path();

            t = 0;
            funcionx.moveTo(0, 0);
            for (t = 0; t <= ancho - 100; t++) {
                x = (float) (Math.sin(2 * Math.PI * f * t) * (-A * 4 / Math.PI));
                x = x + (float) (Math.sin(3 * 2 * Math.PI * f * t) * (-A * 4 / (3 * Math.PI)));
                x = x + (float) (Math.sin(5 * 2 * Math.PI * f * t) * (-A * 4 / (5 * Math.PI)));
                funcionx.lineTo(t, x);
            }
            pintura.setColor(Color.BLUE);
            pintura.setStyle(Paint.Style.STROKE);
            funcionx.offset(50, 400); // Subido de 500 a 400
            canvas.drawPath(funcionx, pintura);

            // Gráfico 2 con inclinación y traslación
            canvas.save();
            canvas.translate(0, 500); // Subido de 600 a 500
            canvas.rotate(5, 50, 400); // Ajustado de 500 a 400
            pintura.setColor(Color.BLACK);
            pintura.setStrokeWidth(8);
            canvas.drawLine(50, 400, ancho - 50, 400, pintura); // Ajustado de 500 a 400
            canvas.drawLine(50, 150, 50, 650, pintura); // Subido de 250-750 a 150-650

            float[] intervalos1 = {20, 5, 10,3};
            DashPathEffect dash = new DashPathEffect(intervalos1, 0);
            pintura.setPathEffect(dash);
            pintura.setColor(Color.BLUE);
            pintura.setStyle(Paint.Style.STROKE);
            canvas.drawPath(funcionx, pintura);
            canvas.restore();

            // Gráfico 3
            canvas.save();
            canvas.translate(0, 1000); // Subido de 1200 a 1000
            pintura.setColor(Color.BLACK);
            pintura.setStrokeWidth(8);
            canvas.drawLine(50, 400, ancho - 50, 400, pintura); // Subido de 500 a 400
            canvas.drawLine(50, 150, 50, 650, pintura); // Subido de 250-750 a 150-650

            float[] intervalos2 = {20, 30};
            DashPathEffect dash2 = new DashPathEffect(intervalos2, 0);
            pintura.setPathEffect(dash2);
            pintura.setColor(Color.BLUE);
            pintura.setStyle(Paint.Style.STROKE);
            canvas.drawPath(funcionx, pintura);
            canvas.restore();
        }
    }
}
