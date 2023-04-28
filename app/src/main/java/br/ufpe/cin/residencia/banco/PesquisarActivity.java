package br.ufpe.cin.residencia.banco;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import br.ufpe.cin.residencia.banco.conta.ContaAdapter;

//Ver anotações TODO no código
public class PesquisarActivity extends AppCompatActivity {
    BancoViewModel viewModel;
    ContaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);
        viewModel = new ViewModelProvider(this).get(BancoViewModel.class);
        EditText aPesquisar = findViewById(R.id.pesquisa);
        Button btnPesquisar = findViewById(R.id.btn_Pesquisar);
        RadioGroup tipoPesquisa = findViewById(R.id.tipoPesquisa);
        RecyclerView rvResultado = findViewById(R.id.rvResultado);
        adapter = new ContaAdapter(getLayoutInflater());
        rvResultado.setLayoutManager(new LinearLayoutManager(this));
        rvResultado.setAdapter(adapter);

        btnPesquisar.setOnClickListener(
                v -> {
                    String oQueFoiDigitado = aPesquisar.getText().toString();
                        int rdEscolhido = tipoPesquisa.getCheckedRadioButtonId();
                    RadioButton radioButton = findViewById(rdEscolhido);
                    String buscarPor = radioButton.getText().toString();

                    //TODO implementar a busca de acordo com o tipo de busca escolhido pelo usuário

                    if (buscarPor.equals("Nome")) {
                        if (!oQueFoiDigitado.isEmpty()) {
                            Log.i("TAG", "buscarPor " + oQueFoiDigitado);
                            viewModel.buscarPeloNome(oQueFoiDigitado);
                        }

                    } else if (buscarPor.equals("CPF")) {

                        if (!oQueFoiDigitado.isEmpty()) {
                            viewModel.buscarPeloCPF(oQueFoiDigitado);
                        }

                    } else {
                         if (!oQueFoiDigitado.isEmpty()) {
                            viewModel.buscarPeloNumero(oQueFoiDigitado);
                        }
                    }
                }
        );

        //TODO atualizar o RecyclerView com resultados da busca na medida que encontrar
        viewModel.listaContaAtual.observe(this, contas-> {
            Log.i("TAG", "onCreate: " + contas);
            adapter.submitList(contas);
        });

        }

}