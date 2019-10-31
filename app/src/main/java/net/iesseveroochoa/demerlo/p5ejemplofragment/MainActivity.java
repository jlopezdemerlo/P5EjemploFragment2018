package net.iesseveroochoa.demerlo.p5ejemplofragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_FEstatico)
    Button btnFEstatico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //PARA UTILIZAR EL PLUGING android-butterknife-zelezny pulsar con el boton drcho sobre la referencia al layout de la clase
        //R de abajo y seleccionar "Generate"
        setContentView(R.layout.activity_main);
        //necesario para utilizar butterknife
        ButterKnife.bind(this);
    }

    /**
     * Mediante butterknife podemos referenciar al evento del boton
     */
    @OnClick(R.id.btn_FEstatico)
    public void onClickEstatico() {

        Intent i;
        i = new Intent(MainActivity.this, TrabajarFragmentosEstaticosActivity.class);
        startActivity(i);

    }

    @OnClick(R.id.btn_FDinamico)
    public void onClickDinamico() {
        Intent i;
        i = new Intent(MainActivity.this, TrabajarFragmentosDinamicosActivity.class);
        startActivity(i);
    }
}
