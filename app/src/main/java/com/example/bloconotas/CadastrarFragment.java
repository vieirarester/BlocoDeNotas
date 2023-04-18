package com.example.bloconotas;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.bloconotas.databinding.FragmentCadastrarBinding;


public class CadastrarFragment extends Fragment {

    FragmentCadastrarBinding cadastrarBinding;
    String tituloText;
    String descricaoText;

    public CadastrarFragment() {
        super(R.layout.fragment_cadastrar);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        cadastrarBinding = FragmentCadastrarBinding.inflate(inflater, container, false);
        return cadastrarBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cadastrarBinding.salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tituloText = cadastrarBinding.campoTitulo.getText().toString();
                descricaoText = cadastrarBinding.campoDescricao.getText().toString();

                Nota nota = new Nota(tituloText, descricaoText);

                NotaDao notaDao = DatabaseSingleton.getInstance(getContext()).appDatabase.notaDao();

                notaDao.inserir(nota);

                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.principalFragment);

            }
        });
    }
}