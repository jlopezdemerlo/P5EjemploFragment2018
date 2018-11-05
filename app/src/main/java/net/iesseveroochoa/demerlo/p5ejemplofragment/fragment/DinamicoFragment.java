package net.iesseveroochoa.demerlo.p5ejemplofragment.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import net.iesseveroochoa.demerlo.p5ejemplofragment.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DinamicoFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    @BindView(R.id.tv_nombreFragment)
    TextView tvNombreFragment;

    // TODO: Rename and change types of parameters
    private String mParam1;



    public DinamicoFragment() {
        // Required empty public constructor
    }

    /**
     * Nos permite crear un fragment enviando parametros.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment DinamicoFragment.
     */

    public static DinamicoFragment newInstance(String param1) {
        DinamicoFragment fragment = new DinamicoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //si se ha creado el fregment mediante newInstance recibiremos parametros
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);

        } else {
            mParam1 = "Fragment creado sin Parametros";
        }

    }
/*
La modificación de los views no puede ser en el onCreate, nos daría error
 */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dinamico, container, false);
        ButterKnife.bind(this, view);
        //mostramos el valor del parámetro en textview
        String stFragParametro=getResources().getString(R.string.tvNombreFragmenet);
        tvNombreFragment.setText(String.format(stFragParametro,mParam1));
        //indicamos que retenga los valores ante reconstrucciones
        this.setRetainInstance(true);
        return view;

    }

}
