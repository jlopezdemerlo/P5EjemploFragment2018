package net.iesseveroochoa.demerlo.p5ejemplofragment.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import net.iesseveroochoa.demerlo.p5ejemplofragment.R;

/**
 * Para reutilizar los componentes de la Interfaz de Usuario del Fragment, deberías crear cada uno
 * como
 * componentes modulares que definen su propio layout y comportamiento
 */

public class EstaticoFragment extends Fragment {

    //Utilizamos la libreria Butterknife par obtener la referencia del spiner
    //los datos los cargamos directamente de un array en string.xml

    Spinner spColor;
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

        tvEnvioDatos = view.findViewById(R.id.tv_envioDatos);
        spColor = view.findViewById(R.id.sp_color);

        //indicamos que retenga los valores ante reconstrucciones
       // this.setRetainInstance(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*
         * Es el evento del spinner de seleccion de elemento.
         * Observar que realizamos la llamada del onColorSeleccionado del listener que en nuestro
         * caso estara implementado en la Actividad*/
        spColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String color;
                color = (String) spColor.getItemAtPosition(i);
                //mListener tendra una referencia a la Actividad, que a su vez tendra un metodo onColorSeleccionado
                mListener.onColorSeleccionado(color);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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
