package com.example.conversor2.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.conversor2.R;
import com.example.conversor2.databinding.FragmentHomeBinding;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private FragmentHomeBinding binding;
    TextView tvMetro, tvYarda, tvPie, tvPulgada;
    EditText etCantidad;
    Button btnConvertir, btnReset;

    int unidad;

    private String medidas[] = {"Metro (m)", "Yarda (yd)", "Pie (ft)", "Pulgada (in)"};


    Spinner spinner;

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        tvMetro = (TextView) rootView.findViewById(R.id.tvMetro);
        tvYarda = (TextView) rootView.findViewById(R.id.tvYarda);
        tvPie = (TextView) rootView.findViewById(R.id.tvPie);
        tvPulgada = (TextView) rootView.findViewById(R.id.tvPulgada);
        etCantidad = (EditText) rootView.findViewById(R.id.etCantidad);

        btnConvertir = (Button) rootView.findViewById(R.id.btnConvertir);
        btnReset = (Button) rootView.findViewById(R.id.btnReset);


        Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, medidas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });

        btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etCantidad.getText().toString().equals("")) {

                    String scantidad = etCantidad.getText().toString();
                    Double dcantidad = Double.parseDouble(scantidad);

                    if (spinner != null) {
                        unidad = spinner.getSelectedItemPosition();
                        switch (unidad) {
                            case 0:
                                tvMetro.setText(scantidad);
                                tvYarda.setText(metroToYarda(dcantidad) );
                                tvPie.setText(metroToPie(dcantidad));
                                tvPulgada.setText(metroToPulgada(dcantidad));
                                break;
                            case 1:
                                tvYarda.setText(scantidad);
                                tvMetro.setText(yardaToMetro(dcantidad) );
                                tvPie.setText(yardaToPie(dcantidad));
                                tvPulgada.setText(yardaToPulgada(dcantidad));
                                break;
                            case 2:
                                tvPie.setText(scantidad);
                                tvYarda.setText(pieToYarda(dcantidad) );
                                tvMetro.setText(pieToMetro(dcantidad));
                                tvPulgada.setText(pieToPulgada(dcantidad));
                                break;
                            case 3:
                                tvPulgada.setText(scantidad);
                                tvYarda.setText(pulgadaToYarda(dcantidad) );
                                tvPie.setText(pulgadaToPie(dcantidad));
                                tvMetro.setText(pulgadaToMetro(dcantidad));
                                break;

                            default:
                                break;

                        }
                    }

                } else {

                    Toast.makeText(v.getContext(), "Escriba la cantidad de unidades", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvMetro.setText("---");
                tvYarda.setText("---");
                tvPie.setText("---");
                tvPulgada.setText("---");

                etCantidad.setText("");
            }
        });
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public String metroToYarda(double metro) {
        return String.format("%.2f", metro * 1.094);
    }

    public String metroToPie(double metro) {
        return String.format("%.2f", metro * 3.281);
    }

    public String metroToPulgada(double metro) {
        return String.format("%.2f", metro * 39.37);
    }

    public String yardaToMetro(double yarda) {
        return String.format("%.2f", yarda / 1.094);
    }

    public String yardaToPie(double yarda) {
        return String.format("%.2f", yarda * 3);
    }

    public String yardaToPulgada(double yarda) {
        return String.format("%.2f", yarda * 36);
    }

    public String pieToMetro(double pie) {
        return String.format("%.2f", pie / 3.281);
    }

    public String pieToYarda(double pie) {
        return String.format("%.2f", pie / 3);
    }

    public String pieToPulgada(double pie) {
        return String.format("%.2f", pie * 12);
    }

    public String pulgadaToMetro(double pulgada) {
        return String.format("%.2f", pulgada / 39.37);
    }

    public String pulgadaToYarda(double pulgada) {
        return String.format("%.2f", pulgada / 36);
    }

    public String pulgadaToPie(double pulgada) {
        return String.format("%.2f", pulgada / 12);
    }
}