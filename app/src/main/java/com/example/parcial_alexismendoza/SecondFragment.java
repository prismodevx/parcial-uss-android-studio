package com.example.parcial_alexismendoza;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.parcial_alexismendoza.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private String descripcion;
    private String impacto;
    private String probabilidad;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String usuario = SecondFragmentArgs.fromBundle(getArguments()).getUsuario();
        binding.txtUsuario.setText(usuario);

        binding.btnVerMapa.setOnClickListener(v -> {
            descripcion = binding.edtDescripcion.getText().toString();

            int selectedImpactoId = binding.rdgImpacto.getCheckedRadioButtonId();
            RadioButton rbImpacto = view.findViewById(selectedImpactoId);
            if (rbImpacto != null) {
                impacto = rbImpacto.getText().toString();
            }

            int selectedProbabilidadId = binding.rdgProbabilidad.getCheckedRadioButtonId();
            RadioButton rbProbabilidad = view.findViewById(selectedProbabilidadId);
            if (rbProbabilidad != null) {
                impacto = rbProbabilidad.getText().toString();
            }

            if(!descripcion.isEmpty()  && rbProbabilidad != null && rbImpacto != null) {
//                Toast.makeText(getContext(), "envio correcto", Toast.LENGTH_SHORT).show();
                SecondFragmentDirections.ActionSecondFragmentToTercerFragment action =
                        SecondFragmentDirections.actionSecondFragmentToTercerFragment("Nombre");
                NavHostFragment.findNavController(SecondFragment.this).navigate(action);
            }
            else {
                Toast.makeText(getContext(), "Por favor, completa todos los campos solicitados", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}