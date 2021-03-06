package net.iesseveroochoa.demerlo.p5ejemplofragment;

import android.content.Context;
import android.os.Bundle;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import net.iesseveroochoa.demerlo.p5ejemplofragment.fragment.DinamicoFragment;


public class TrabajarFragmentosDinamicosActivity extends AppCompatActivity {


    TextView tvSinFragment;
    EditText etParametro;
    CheckBox ckbNavegabilidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trabajar_fragmentos_dinamicos);

        etParametro = findViewById(R.id.et_Parametro);
        ckbNavegabilidad = findViewById(R.id.ckb_Navegabilidad);
        tvSinFragment = findViewById(R.id.tv_sinFragment);

    }

    /**
     * Creación de un fragmento sin el envío de parametros previo utilizando el
     * constructor directamente
     */
   // @OnClick(R.id.btn_CrearFragmentoSinP)
    public void onClickCrearFragmentoSinP(View view) {
        //ocultamos el textview
        tvSinFragment.setVisibility(View.INVISIBLE);
        //OJO, que si no estamos utilizando la importacion v4 es getFragmetManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        //Iniciamos transaccion.------>OJO con las importaciones. Utilizar V4
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //creamos una instancia del fragmento
        DinamicoFragment dinamicoFragment = new DinamicoFragment();
        //Lo añadimos en el FrameLayout de la actividad.
        transaction.replace(R.id.frmLayoutContenedor, dinamicoFragment);
       //si queremos navegabilidad hacia atras entre fragments
        if(ckbNavegabilidad.isChecked())
            transaction.addToBackStack(null);
        transaction.commit();
        ocultarTeclado();
    }

    /**
     * Creación de un fragmento mediante el patrón factory.
     * Utilizaremos el método estatico newInstance
     * para permitir enviar parametros al fragment antes del onCreate.
     *
     */
    //@OnClick(R.id.btn_CrearFragmentoConP)
    public void onClickCrearFragmentoConP(View v) {
        //ocultamos el textview
        tvSinFragment.setVisibility(View.INVISIBLE);
        //OJO, que si no estamos utilizando la importacion v4 es getFragmetManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        //Iniciamos transaccion.OJO con las importaciones. Utilizar V4
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        //creamos una instancia del fragmento enviándole parámetros
        String param = etParametro.getText().toString();
        DinamicoFragment dinamicoFragment = DinamicoFragment.newInstance(param);
        //Tenemos la posibilidad de enviar un bundle al fragment con EXTRAS
        //mediante frmDinamico.setArguments(bundle);
        //Lo añadimos en el FrameLayout de la actividad.
        transaction.replace(R.id.frmLayoutContenedor, dinamicoFragment);
        //si queremos navegabilidad hacia atras entre fragments
        if(ckbNavegabilidad.isChecked())
            transaction.addToBackStack(null);
        transaction.commit();
        ocultarTeclado();
    }
    /**
     * Nos permite saber si el usuario pulsa el botón de volver y actuar en consecuencia
     * Nosotros mostraremos el textView que mostramos si no hay fragment
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();

            //buscamos el fragment anterior.
            //Para ello, le indicamos al fragmentManager que me devuelva el fragment
            //contenido en el layout

        Fragment fragment = (Fragment) getSupportFragmentManager().findFragmentById(R.id.frmLayoutContenedor);
            //Si no hay fragment mostramos el textview
            if (fragment==null)
                tvSinFragment.setVisibility(View.VISIBLE);

    }
    /**
     * Permite ocultar el teclado
     */
    private void ocultarTeclado() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        if (imm != null) {
            imm.hideSoftInputFromWindow(etParametro.getWindowToken(), 0);
        }
    }

}
