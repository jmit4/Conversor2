package com.example.conversor2.ui.gallery;
import java.math.BigDecimal;
import java.math.RoundingMode;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.conversor2.R;
import com.example.conversor2.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private FragmentGalleryBinding binding;

    TextView tvKilo, tvLibra, tvOnza;
    EditText etCantidad;
    Button btnConvertir, btnReset;

    int unidad;

    private String medidas[] = {"Kilogramo (kg)", "Libra (lb)", "Onza (oz)"};

    Spinner spinner;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);
        tvKilo = (TextView) rootView.findViewById(R.id.tvKilo);
        tvLibra = (TextView) rootView.findViewById(R.id.tvLibra);
        tvOnza = (TextView) rootView.findViewById(R.id.tvOnza);

        etCantidad = (EditText) rootView.findViewById(R.id.etCantidad2);

        btnConvertir = (Button) rootView.findViewById(R.id.btnConvertir2);
        btnReset = (Button) rootView.findViewById(R.id.btnReset2);


        Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner2);

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
                                tvKilo.setText(scantidad);
                                tvLibra.setText(kilogramoALibra(dcantidad));
                                tvOnza.setText(kilogramoAOnza(dcantidad));
                                break;
                            case 1:
                                tvKilo.setText(libraAKilogramo(dcantidad));
                                tvLibra.setText(scantidad);
                                tvOnza.setText(libraAOnza(dcantidad));
                                break;
                            case 2:
                                tvKilo.setText(onzaAKilogramo(dcantidad));
                                tvLibra.setText(onzaALibra(dcantidad));
                                tvOnza.setText(scantidad);
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
                tvKilo.setText("---");
                tvLibra.setText("---");
                tvOnza.setText("---");

                etCantidad.setText("");
            }
        });

    return rootView;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public static String kilogramoALibra(double kilogramo) {
        double libra = kilogramo * 2.20462;
        return formatDecimal(libra);
    }
    public static String kilogramoAOnza(double kilogramo) {
        double onza = kilogramo * 35.274;
        return formatDecimal(onza);
    }

    public static String libraAKilogramo(double libra) {
        double kilogramo = libra / 2.20462;
        return formatDecimal(kilogramo);
    }
    public static String libraAOnza(double libra) {
        double onza = libra * 16;
        return formatDecimal(onza);
    }

    public static String onzaAKilogramo(double onza) {
        double kilogramo = onza / 35.274;
        return formatDecimal(kilogramo);
    }

    public static String onzaALibra(double onza) {
        double libra = onza / 16;
        return formatDecimal(libra);
    }
    private static String formatDecimal(double value) {
        BigDecimal decimal = new BigDecimal(value);
        decimal = decimal.setScale(2, RoundingMode.HALF_UP);
        return decimal.toString();
    }

}