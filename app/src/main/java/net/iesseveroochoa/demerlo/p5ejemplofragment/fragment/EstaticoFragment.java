package net.iesseveroochoa.demerlo.p5ejemplofragment.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;


import net.iesseveroochoa.demerlo.p5ejemplofragment.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;

/**
 * Para reutilizar los componentes de la Interfaz de Usuario del Fragment, deberías crear cada uno
 * como
 * componentes modulares que definen su propio layout y comportamiento
 */

public class EstaticoFragment extends Fragment {

    //Utilizamos la libreria Butterknife par obtener la referencia del spiner
    //los datos los cargamos directamente de un array en string.xml
    @BindView(R.id.sp_color)
    Spinner spColor;
    @BindView(R.id.tv_envioDatos)
    TextView tvEnvioDatos;

    //Esta variable privada, apuntara a la Activity contenedora que implementara la interface
    private OnMiSeleccionColorListener mListener;

    public EstaticoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   this.setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_estatico, container, false);
        //Para usar la libreria Butterknife es necesario  realizar esta llamada. Fijaros que al
        //ser un fragment, es necesario passr el view recien creado
        ButterKnife.bind(this, view);
        //indicamos que retenga los valores ante reconstrucciones
       // this.setRetainInstance(true);
        return view;
    }


    /**
     * En este evento podemos obligar a la actividad que contenga este fragment a que implemente el
     * listener. Si no fuera necesario, podemos quitar esta parte. Podriamos crear un metodo en el fragment
     * al estilo setOnMiSeleccionColorListener que acepte como parametro el listener como aparece comentado
     * mas abajo
     *
     * @param context:es la actividad contenedora
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //si la actividad contenedora implementa el listener.

        if (context instanceof OnMiSeleccionColorListener) {
            mListener = (OnMiSeleccionColorListener) context;
        } else {//Al hacer así la signación nos aseguramos su implementación
            throw new RuntimeException(context.toString()
                    + getString(R.string.error_fragmentestatico));
        }
    }

    /*/**
     * Si no queremos obligar a la actividad receptora a implementar el listener podemos dejar la opcion de
     *  asignar el listener  mediante este metodo
     *
     * @param listener:
     *//*
    public void setOnMiSeleccionColorListener(OnMiSeleccionColorListener listener){
        this.mListener=listener;
    }*/
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Es el evento del spinner de seleccion de elemento.
     * Observar que realizamos la llamada del onColorSeleccionado del listener que en nuestro
     * caso estara implementado en la Actividad
     * Podemos crear tambien, mediante la libreria Butterknife los metodos a los eventos.
     * En este caso para el spinner
     *
     * @param sp
     * @param posicion
     */
    @OnItemSelected(R.id.sp_color)
    public void spinnerItemSelected(Spinner sp, int posicion) {
        String color;
        color = (String) spColor.getItemAtPosition(posicion);
        //mListener tendra una referencia a la Actividad, que a su vez tendra un metodo onColorSeleccionado
        mListener.onColorSeleccionado(color);
    }

    /**
     * Este metodo publico nos permitira enviar informacion al fragment. En nuestro caso
     * de ejemplo realizamos una asignacion a TextView
     * @param valor
     */
    public void setValor(String valor) {
        tvEnvioDatos.setText(valor);
    }

    /**
     * Esta sera la interface que nos permitira transmitir el evento de seleccion del spinner
     * a la actividad receptora. La actividad tendra que implementar esta interface para reaccionar ante
     * el evento del spinner
     */
    public interface OnMiSeleccionColorListener {
        void onColorSeleccionado(String color);
    }
}
