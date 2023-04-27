package br.ufpe.cin.residencia.banco.conta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.ufpe.cin.residencia.banco.R;

//Ver anotações TODO no código
public class EditarContaActivity extends AppCompatActivity {

    public static final String KEY_NUMERO_CONTA = "numeroDaConta";
    ContaViewModel viewModel;
    Conta contaEscolhida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_conta);
        viewModel = new ViewModelProvider(this).get(ContaViewModel.class);

        Button btnAtualizar = findViewById(R.id.btnAtualizar);
        Button btnRemover = findViewById(R.id.btnRemover);
        EditText campoNome = findViewById(R.id.nome);
        EditText campoNumero = findViewById(R.id.numero);
        EditText campoCPF = findViewById(R.id.cpf);
        EditText campoSaldo = findViewById(R.id.saldo);
        campoNumero.setEnabled(false);

        Intent i = getIntent();
        String numeroConta = i.getStringExtra(KEY_NUMERO_CONTA);
        Log.i("TAG", "onCreate: " + numeroConta);
        viewModel.buscarPeloNumero(numeroConta);
        //TODO usar o número da conta passado via Intent para recuperar informações da conta
        viewModel.contaAtual.observe(this, conta -> {
                   if(conta != null) {
                       Log.i("TAG", "eita ");
                       campoCPF.setText(conta.cpfCliente);
                       Log.i("TAG", "eita " + campoCPF);
                       campoNome.setText(conta.nomeCliente);
                       String saldo = String.valueOf(conta.saldo);
                       campoSaldo.setText(saldo);
                       campoNumero.setText(conta.numero);
                       this.contaEscolhida = conta;
                   }else{
                       Log.i("TAG", "deu ruim ");
                   }
            });
        btnAtualizar.setText("Editar");
        btnAtualizar.setOnClickListener(
                v -> {

                    String nomeCliente = campoNome.getText().toString();
                    String cpfCliente = campoCPF.getText().toString();
                    String saldoConta = campoSaldo.getText().toString();
                    //TODO: Incluir validações aqui, antes de criar um objeto Conta. Se todas as validações passarem, aí sim monta um objeto Conta.
                    //TODO: chamar o método que vai atualizar a conta no Banco de Dados
                    if(cpfCliente.isEmpty() || cpfCliente.length() < 11 || !checkNumeric(cpfCliente)){
                        Toast.makeText(this, "Infome Cpf valido", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    try {
                        Double.parseDouble(saldoConta);
                    } catch (NumberFormatException e) {
                        Toast.makeText(this, "Saldo deve ser um número válido", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (contaEscolhida != null) {
                        Conta c = new Conta(numeroConta, Double.valueOf(saldoConta), nomeCliente,cpfCliente);
                        viewModel.atualizar(c);
                        finish();
                        Toast.makeText(this, "Conta atualizada com sucessso!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this, "Não existe um Cliente com esse CPF", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        btnRemover.setOnClickListener(v -> {
            //TODO implementar remoção da conta
            viewModel.remover(contaEscolhida);
            finish();
            Toast.makeText(this, "Conta removida com sucesso!", Toast.LENGTH_SHORT).show();

        });
    }
    private boolean checkNumeric(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        for (int i = 0; i < value.length(); i++) {
            if (!Character.isDigit(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}