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

import java.util.ArrayList;


public class CadastrarFragment extends Fragment {

    FragmentCadastrarBinding cadastrarBinding;
    NotaDao notaDao;
    String tituloText;
    String descricaoText;
    ArrayList<Nota> notas;

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

        if (getArguments() != null) {
            String titulo = getArguments().getString("titulo");
            String descricao = getArguments().getString("descricao");
            int id = getArguments().getInt("id");

            // valores para preencher os campos do formulário
            cadastrarBinding.campoTitulo.setText(titulo);
            cadastrarBinding.campoDescricao.setText(descricao);
        }

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
                notaDao = DatabaseSingleton.getInstance(getContext()).appDatabase.notaDao();

                if (getArguments() != null && getArguments().containsKey("id")) {
                    int id = getArguments().getInt("id");

                    for (Nota nota : notaDao.listarTodos()) {
                        if (nota.id == id) {
                            nota.titulo = tituloText;
                            nota.descricao = descricaoText;

                            notaDao.atualizar(nota);
                            break;
                        }
                    }
                } else {
                    Nota nota = new Nota(tituloText, descricaoText);
                    notaDao.inserir(nota);
                }
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.principalFragment);

            }
        });
    }
}