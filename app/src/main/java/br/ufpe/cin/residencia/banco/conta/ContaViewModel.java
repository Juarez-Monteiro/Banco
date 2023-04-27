package br.ufpe.cin.residencia.banco.conta;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.ufpe.cin.residencia.banco.BancoDB;

//Ver métodos anotados com TODO
public class ContaViewModel extends AndroidViewModel {

    private ContaRepository repository;
    public LiveData<List<Conta>> contas;
    private MutableLiveData<Conta> _contaAtual = new MutableLiveData<>();
    public LiveData<Conta> contaAtual = _contaAtual;

    public ContaViewModel(@NonNull Application application) {
        super(application);
        this.repository = new ContaRepository(BancoDB.getDB(application).contaDAO());
        this.contas = repository.getContas();
    }

    void inserir(Conta c) {
        new Thread(() -> repository.inserir(c)).start();
    }

    void atualizar(Conta c) { new Thread(() -> repository.atualizar(c)).start();}
        //TODO implementar
    void remover(Conta c) { new Thread(() -> repository.remover(c)).start();}
        //TODO implementar


    void buscarPeloNumero(String numeroConta) {
        Log.i("TAG", "buscarPeloNumero " + numeroConta);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("TAG", "Entrou!! " + numeroConta);
                Conta conta =  repository.buscarPeloNumero(numeroConta);
                _contaAtual.postValue(conta);
            }
        }).start();


        //TODO implementar
    }
}
