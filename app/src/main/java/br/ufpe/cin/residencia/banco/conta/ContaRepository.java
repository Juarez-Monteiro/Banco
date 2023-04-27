package br.ufpe.cin.residencia.banco.conta;

import android.util.Log;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;

import java.util.List;

//Ver anotações TODO no código
public class ContaRepository {
    private ContaDAO dao;
    private LiveData<List<Conta>> contas;

    public ContaRepository(ContaDAO dao) {
        this.dao = dao;
        this.contas = dao.contas();
    }

    public LiveData<List<Conta>> getContas() {
        return contas;
    }

    @WorkerThread
    public void inserir(Conta c) {
        dao.adicionar(c);
    }

    @WorkerThread
    public void atualizar(Conta c) { dao.atualizarConta(c);
        //TODO implementar atualizar
    }

    @WorkerThread
    public void remover(Conta c) { dao.deletarConta(c);
        //TODO implementar remover
    }

    @WorkerThread
    public  List<Conta> buscarPeloNome(String nomeCliente) {
       return dao.buscarPeloNome(nomeCliente);
        //TODO implementar busca

    }

    @WorkerThread
    public List<Conta> buscarPeloCPF(String cpfCliente) {
        dao.buscarPeloCPF(cpfCliente);
        //TODO implementar busca
        return null;
    }

    @WorkerThread
    public Conta buscarPeloNumero(String numeroConta) {
        Conta conta = dao.buscarPeloNumero(numeroConta);
        //TODO implementar busca
        return conta;
    }
}
