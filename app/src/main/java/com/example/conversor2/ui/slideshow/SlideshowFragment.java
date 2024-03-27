package com.example.conversor2.ui.slideshow;
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
import com.example.conversor2.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;

    TextView tvMs, tvKmh, tvMph, tvNudo, tvIps;
    EditText etCantidad;
    Button btnConvertir, btnReset;

    int unidad;

    private String medidas[] = {"Metro/seg", "Kilometro/hr", "Milla/hr","Nudo","Pulgada/seg"};

    Spinner spinner;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_slideshow, container, false);
        tvMs = (TextView) rootView.findViewById(R.id.tvMs);
        tvKmh = (TextView) rootView.findViewById(R.id.tvKmh);
        tvMph = (TextView) rootView.findViewById(R.id.tvMph);
        tvNudo = (TextView) rootView.findViewById(R.id.tvKn);
        tvIps = (TextView) rootView.findViewById(R.id.tvIps);

        etCantidad = (EditText) rootView.findViewById(R.id.etCantidad3);

        btnConvertir = (Button) rootView.findViewById(R.id.btnConvertir3);
        btnReset = (Button) rootView.findViewById(R.id.btnReset3);


        Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner3);

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
                                tvMs.setText(scantidad);
                                tvKmh.setText(metroSegundoAKilometroHora(dcantidad));
                                tvMph.setText(metroSegundoAMillaHora(dcantidad));
                                tvNudo.setText(metroSegundoANudo(dcantidad));
                                tvIps.setText(metroSegundoAPulgadaSegundo(dcantidad));
                                break;
                            case 1:
                                tvMs.setText(kilometroHoraAMetroSegundo(dcantidad));
                                tvKmh.setText(scantidad);
                                tvMph.setText(kilometroHoraAMillaHora(dcantidad));
                                tvNudo.setText(kilometroHoraANudo(dcantidad));
                                tvIps.setText(kilometroHoraAPulgadaSegundo(dcantidad));
                                break;
                            case 2:
                                tvMs.setText(millaHoraAMetroSegundo(dcantidad));
                                tvKmh.setText(millaHoraAKilometroHora(dcantidad));
                                tvMph.setText(scantidad);
                                tvNudo.setText(millaHoraANudo(dcantidad));
                                tvIps.setText(millaHoraAPulgadaSegundo(dcantidad));
                                break;
                            case 3:
                                tvMs.setText(nudoAMetroSegundo(dcantidad));
                                tvKmh.setText(nudoAKilometroHora(dcantidad));
                                tvMph.setText(nudoAMillaHora(dcantidad));
                                tvNudo.setText(scantidad);
                                tvIps.setText(nudoAPulgadaSegundo(dcantidad));
                                break;
                            case 4:
                                tvMs.setText(pulgadaSegundoAMetroSegundo(dcantidad));
                                tvKmh.setText(pulgadaSegundoAKilometroHora(dcantidad));
                                tvMph.setText(pulgadaSegundoAMillaHora(dcantidad));
                                tvNudo.setText(pulgadaSegundoANudo(dcantidad));
                                tvIps.setText(scantidad);
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
                tvMs.setText("---");
                tvKmh.setText("---");
                tvMph.setText("---");
                tvNudo.setText("---");
                tvIps.setText("---");

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
    public static String metroSegundoAKilometroHora(double metroSegundo) {
        double kilometroHora = metroSegundo * 3.6;
        return formatDecimal(kilometroHora);
    }

    public static String metroSegundoAMillaHora(double metroSegundo) {
        double millaHora = metroSegundo * 2.23694;
        return formatDecimal(millaHora);
    }

    public static String metroSegundoANudo(double metroSegundo) {
        double nudo = metroSegundo * 1.94384;
        return formatDecimal(nudo);
    }

    public static String metroSegundoAPulgadaSegundo(double metroSegundo) {
        double pulgadaSegundo = metroSegundo * 39.3701;
        return formatDecimal(pulgadaSegundo);
    }

    public static String kilometroHoraAMetroSegundo(double kilometroHora) {
        double metroSegundo = kilometroHora / 3.6;
        return formatDecimal(metroSegundo);
    }

    public static String kilometroHoraAMillaHora(double kilometroHora) {
        double millaHora = kilometroHora / 1.60934;
        return formatDecimal(millaHora);
    }

    public static String kilometroHoraANudo(double kilometroHora) {
        double nudo = kilometroHora / 1.852;
        return formatDecimal(nudo);
    }

    public static String kilometroHoraAPulgadaSegundo(double kilometroHora) {
        double pulgadaSegundo = kilometroHora * 39.3701 / 3600;
        return formatDecimal(pulgadaSegundo);
    }

    public static String millaHoraAMetroSegundo(double millaHora) {
        double metroSegundo = millaHora / 2.23694;
        return formatDecimal(metroSegundo);
    }

    public static String millaHoraAKilometroHora(double millaHora) {
        double kilometroHora = millaHora * 1.60934;
        return formatDecimal(kilometroHora);
    }

    public static String millaHoraANudo(double millaHora) {
        double nudo = millaHora / 1.15078;
        return formatDecimal(nudo);
    }

    public static String millaHoraAPulgadaSegundo(double millaHora) {
        double pulgadaSegundo = millaHora * 17.6;
        return formatDecimal(pulgadaSegundo);
    }

    public static String nudoAMetroSegundo(double nudo) {
        double metroSegundo = nudo / 1.94384;
        return formatDecimal(metroSegundo);
    }

    public static String nudoAKilometroHora(double nudo) {
        double kilometroHora = nudo * 1.852;
        return formatDecimal(kilometroHora);
    }

    public static String nudoAMillaHora(double nudo) {
        double millaHora = nudo * 1.15078;
        return formatDecimal(millaHora);
    }

    public static String nudoAPulgadaSegundo(double nudo) {
        double pulgadaSegundo = nudo * 51.444;
        return formatDecimal(pulgadaSegundo);
    }

    public static String pulgadaSegundoAMetroSegundo(double pulgadaSegundo) {
        double metroSegundo = pulgadaSegundo / 39.3701;
        return formatDecimal(metroSegundo);
    }

    public static String pulgadaSegundoAKilometroHora(double pulgadaSegundo) {
        double kilometroHora = pulgadaSegundo * 3600 / 39.3701;
        return formatDecimal(kilometroHora);
    }

    public static String pulgadaSegundoAMillaHora(double pulgadaSegundo) {
        double millaHora = pulgadaSegundo / 17.6;
        return formatDecimal(millaHora);
    }

    public static String pulgadaSegundoANudo(double pulgadaSegundo) {
        double nudo = pulgadaSegundo / 51.444;
        return formatDecimal(nudo);
    }

    private static String formatDecimal(double value) {
        BigDecimal decimal = new BigDecimal(value);
        decimal = decimal.setScale(2, RoundingMode.HALF_UP);
        return decimal.toString();
    }
}