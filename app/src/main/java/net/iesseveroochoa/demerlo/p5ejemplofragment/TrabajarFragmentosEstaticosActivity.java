package net.iesseveroochoa.demerlo.p5ejemplofragment;

import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.iesseveroochoa.demerlo.p5ejemplofragment.fragment.EstaticoFragment;


/**
 * Esta Actividad mostrara mediante la inclusion de un fragment estatico como tenemos que comunicarnos con
 * el fragment.
 * Para la comunicacion de eventos del Fragment a la Actividad, tenemos que implementar el interface definido en el fragment
 * Para la comunicacion de valores de la Actividad al fragment, implementar metodos publicos en el fragment que permita
 * dicha comunicacion
 */
public class TrabajarFragmentosEstaticosActivity extends AppCompatActivity implements EstaticoFragment.OnMiSeleccionColorListener {

    //Creamos las referencias a los views mediante la libreria butterknife

    TextView tvSeleccionColor;
    EditText etDatos;
    //    @BindView(R.id.fragment)
//    EstaticoFragment fragment;
    EstaticoFragment fragment;
    Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //PARA UTILIZAR EL PLUGING android-butterknife-zelezny pulsar con el boton drcho sobre la referencia al layout de la clase
        //R de abajo y seleccionar "Generate"
        setContentView(R.layout.activity_trabajar_fragmentos_estaticos);

        //buscamos la referencia del fragmento por el id asignado en el layout
        fragment = (EstaticoFragment) getSupportFragmentManager().findFragmentById(R.id.frm_FramentEstatico);
      //  btnEnviar.requestFocus();

        tvSeleccionColor = findViewById(R.id.tv_seleccionColor);
        btnEnviar = findViewById(R.id.btn_Enviar);
        etDatos = findViewById(R.id.et_datos);
        btnEnviar.setOnClickListener(v->onClick());

    }

    @Override
    protected void onResume() {
        super.onResume();
        //Esto lo hacemos solamente para ver el funcionamiento del TextInputLayout asignando el foco al botón
        btnEnviar.setFocusable(true);
        btnEnviar.setFocusableInTouchMode(true);
        btnEnviar.requestFocus();
    }

    /**
     * Al implementar OnMiSeleccionColorListener, nos obliga a sobrecargar
     *
     * @param color
     */
    @Override
    public void onColorSeleccionado(String color) {
        tvSeleccionColor.setText(color);
    }

    /**
     * Nos permite probar como enviar datos al fragment.
     * Nota: Aunque podriamos modificar directamente los valores de fragment, es preferible que se comporte
     * como una caja cerrada que expone su uso a las actividades mediante metodos publicos necesarios
     */

    public void onClick() {
        fragment.setValor(etDatos.getText().toString());
        ocultarTeclado();
    }

    /**
     * Permite ocultar el teclado
     */
    private void ocultarTeclado() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        // mgr.showSoftInput(etDatos, InputMethodManager.HIDE_NOT_ALWAYS);
        if (imm != null) {
            imm.hideSoftInputFromWindow(etDatos.getWindowToken(), 0);
        }
    }
}
