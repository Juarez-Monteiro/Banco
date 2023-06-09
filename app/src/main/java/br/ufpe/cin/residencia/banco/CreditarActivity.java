package br.ufpe.cin.residencia.banco;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.ufpe.cin.residencia.banco.conta.Conta;
import br.ufpe.cin.residencia.banco.conta.ContaDAO;
import br.ufpe.cin.residencia.banco.conta.ContaRepository;

//Ver anotações TODO no código
public class CreditarActivity extends AppCompatActivity {
    BancoViewModel viewModel;
    ContaRepository conta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operacoes);
        viewModel = new ViewModelProvider(this).get(BancoViewModel.class);

        TextView tipoOperacao = findViewById(R.id.tipoOperacao);
        EditText numeroContaOrigem = findViewById(R.id.numeroContaOrigem);
        TextView labelContaDestino = findViewById(R.id.labelContaDestino);
        EditText numeroContaDestino = findViewById(R.id.numeroContaDestino);
        EditText valorOperacao = findViewById(R.id.valor);
        Button btnOperacao = findViewById(R.id.btnOperacao);
        labelContaDestino.setVisibility(View.GONE);
        numeroContaDestino.setVisibility(View.GONE);
        valorOperacao.setHint(valorOperacao.getHint() + " creditado");
        tipoOperacao.setText("CREDITAR");
        btnOperacao.setText("Creditar");

        btnOperacao.setOnClickListener(
                v -> {
                    String numOrigem = numeroContaOrigem.getText().toString();
                    //TODO lembrar de implementar validação do número da conta e do valor da operação, antes de efetuar a operação de crédito.
                    if (numOrigem.isEmpty()){
                        numeroContaOrigem.setError("Digite o numero da conta vei!!");
                        return;
                    }
                    // O método abaixo está sendo chamado, mas precisa ser implementado na classe BancoViewModel para funcionar.
                    double valor = Double.valueOf(valorOperacao.getText().toString());
                    viewModel.creditar(numOrigem,valor);
                    finish();
                    Toast.makeText(this, "Credito no valor de R$ " +valor+" realizado com sucesso!", Toast.LENGTH_SHORT).show();
                }
        );
    }
}