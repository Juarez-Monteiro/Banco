package br.ufpe.cin.residencia.banco;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.ufpe.cin.residencia.banco.conta.Conta;

//Ver anotações TODO no código
public class DebitarActivity extends AppCompatActivity {
    BancoViewModel viewModel;
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

        valorOperacao.setHint(valorOperacao.getHint() + " debitado");
        tipoOperacao.setText("DEBITAR");
        btnOperacao.setText("Debitar");

        btnOperacao.setOnClickListener(
                v -> {
                    String numOrigem = numeroContaOrigem.getText().toString();
                    //TODO lembrar de implementar validação do número da conta e do valor da operação, antes de efetuar a operação de débito.
                    if (numOrigem.isEmpty()){
                        numeroContaOrigem.setError("Digite o número da conta vei!!");
                        return;
                    }
                    // O método abaixo está sendo chamado, mas precisa ser implementado na classe BancoViewModel para funcionar.
                    double valor = Double.valueOf(valorOperacao.getText().toString());
                    viewModel.debitar(numOrigem, valor);
                    finish();
                    Toast.makeText(this, "Debito no valor de R$ " +valor+" realizado com sucesso!", Toast.LENGTH_SHORT).show();
                }
        );
    }
}