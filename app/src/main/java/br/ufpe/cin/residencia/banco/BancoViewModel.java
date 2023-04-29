package br.ufpe.cin.residencia.banco;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import br.ufpe.cin.residencia.banco.conta.Conta;
import br.ufpe.cin.residencia.banco.conta.ContaRepository;

//Ver anotações TODO no código
public class BancoViewModel extends AndroidViewModel {
    private ContaRepository repository;
    public LiveData<List<Conta>> contas;
    private MutableLiveData<Conta> _contaAtual = new MutableLiveData<>();
    public LiveData<Conta> contaAtual = _contaAtual;
    private MutableLiveData<List<Conta>> _listContaAtual = new MutableLiveData<>();
    public LiveData<List<Conta>> listaContaAtual = _listContaAtual;

    public BancoViewModel(@NonNull Application application) {
        super(application);
        this.repository = new ContaRepository(BancoDB.getDB(application).contaDAO());
        this.contas = repository.getContas();
    }

    void transferir(String numeroContaOrigem, String numeroContaDestino, double valor) {
        new Thread(() ->{
            Conta conta = repository.buscarPeloNumero(numeroContaOrigem);
            conta.debitar(valor);
            repository.atualizar(conta);
            Conta contaCredita = repository.buscarPeloNumero(numeroContaDestino);
            Log.i("TAG", "agora " + conta.nomeCliente);
            contaCredita.creditar(valor);
            repository.atualizar(contaCredita);
        }).start();
        //TODO implementar transferência entre contas (lembrar de salvar no BD os objetos Conta modificados)
    }

    void creditar(String numeroConta, double valor) {
       new Thread(() ->{
           Conta conta = repository.buscarPeloNumero(numeroConta);
           conta.creditar(valor);
           repository.atualizar(conta);
       }).start();
        //TODO implementar creditar em conta (lembrar de salvar no BD o objeto Conta modificado)
    }

    void debitar(String numeroConta, double valor) {
        new Thread(() ->{
            Conta conta = repository.buscarPeloNumero(numeroConta);
            conta.debitar(valor);
            repository.atualizar(conta);
        }).start();
        //TODO implementar debitar em conta (lembrar de salvar no BD o objeto Conta modificado)
    }

    void buscarPeloNome(String nomeCliente) {
        new Thread(new Runnable() {
            @Override
            public void run() {
        List<Conta> listaContas  = repository.buscarPeloNome(nomeCliente);
        _listContaAtual.postValue(listaContas);
                          }
        }).start();
        //TODO implementar busca pelo nome do Cliente
    }

    void buscarPeloCPF(String cpfCliente) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Conta> listaContas  = repository.buscarPeloCPF(cpfCliente);
                _listContaAtual.postValue(listaContas);
            }
        }).start();
        //TODO implementar busca pelo CPF do Cliente
    }

    List<Conta> buscarPeloNumero(String numeroConta) {
        Log.i("TAG", "buscarPeloNumero " + numeroConta);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("TAG", "Entrou!! " + numeroConta);
                Conta conta = repository.buscarPeloNumero(numeroConta);
               List<Conta> listConta = new ArrayList<>();
                listConta.add(conta);
               _listContaAtual.postValue(listConta);
            }
        }).start();
        //TODO implementar busca pelo número da Conta
        return null;
    }
    public double saldoTotalBanco() {
        double saldoTotal = 0;
        for (Conta conta : this.contas.getValue()) {
            saldoTotal += conta.saldo;
        }
        return saldoTotal;
    }

    public ContaRepository getRepository() {
        return repository;
    }

    public void setRepository(ContaRepository repository) {
        this.repository = repository;
    }


}
