package com.example.bloconotas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavAction;
import androidx.navigation.NavDestination;
import androidx.navigation.NavDirections;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.bloconotas.databinding.FragmentPrincipalBinding;

import java.util.ArrayList;
import java.util.List;

public class PrincipalFragment extends Fragment {

    FragmentPrincipalBinding principalBinding;
    NotaDao notaDao;
    ArrayList<Nota> notas;
    ArrayList<String> notasString;
    ArrayAdapter<String> adapter;

    public PrincipalFragment() {
        super(R.layout.fragment_principal);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        principalBinding = FragmentPrincipalBinding.inflate(inflater, container, false);

        notaDao = DatabaseSingleton.getInstance(getContext()).appDatabase.notaDao();
        notas = (ArrayList<Nota>) notaDao.listarTodos();
        notasString = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1, notasString);

        return principalBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        for (Nota nota : notas) {
            notasString.add(nota.toString());
        }
        principalBinding.listaNotas.setAdapter(adapter);

        principalBinding.listaNotas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Bundle parametros = new Bundle();
                parametros.putString("titulo", notas.get(i).titulo);
                parametros.putString("descricao", notas.get(i).descricao);
                parametros.putInt("id", notas.get(i).id);

                Navigation.findNavController(view).navigate(R.id.cadastrarFragment, parametros);
            }
        });

        principalBinding.cadastrar.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.cadastrarFragment));
        principalBinding.excluirTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder caixaDialogo = new AlertDialog.Builder(getContext());
                caixaDialogo.setMessage("Deseja deletar todas as notas?");
                caixaDialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        notaDao.deletarTodos();

                        adapter.clear();
                        adapter.addAll(notasString);
                        adapter.notifyDataSetChanged();

                        principalBinding.listaNotas.setAdapter(adapter);
                    }
                });

                caixaDialogo.setNegativeButton("Não", null);
                caixaDialogo.show();
            }
        });

    }
}