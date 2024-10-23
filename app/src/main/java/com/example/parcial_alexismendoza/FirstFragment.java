package com.example.parcial_alexismendoza;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.parcial_alexismendoza.databinding.FragmentFirstBinding;

import java.util.HashMap;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private String usuario;
    private String password;

    private static final HashMap<String, String> userMap = new HashMap<>();
    static {
        userMap.put("alex", "1234");
        userMap.put("jer", "5678");
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnLogin.setOnClickListener(v -> {
            usuario = binding.edtUsuario.getText().toString();
            password = binding.edtPassword.getText().toString();

            if(usuario.length() == 0 || password.length() == 0) {
                Toast.makeText(getContext(), "Por favor, completa todos los campos solicitados", Toast.LENGTH_SHORT).show();
                return;
            }

            if (userMap.containsKey(usuario) && userMap.get(usuario).equals(password)) {
                FirstFragmentDirections.ActionFirstFragmentToSecondFragment action =
                        FirstFragmentDirections.actionFirstFragmentToSecondFragment(usuario);
                NavHostFragment.findNavController(FirstFragment.this).navigate(action);
            } else {
                Toast.makeText(getContext(), "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
            }
        });
//        binding.buttonFirst.setOnClickListener(v ->
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment)
//        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}