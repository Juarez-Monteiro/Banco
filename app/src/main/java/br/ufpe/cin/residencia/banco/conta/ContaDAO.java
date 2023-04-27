package br.ufpe.cin.residencia.banco.conta;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//Ver anotações TODO no código
@Dao
public interface ContaDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void adicionar(Conta c);
    //TODO incluir métodos para atualizar conta e remover conta

    @Update
    void atualizarConta(Conta c);

    @Delete
    void deletarConta(Conta c);

    @Query("SELECT * FROM contas ORDER BY numero ASC")
    LiveData<List<Conta>> contas();

    //TODO incluir métodos para buscar pelo (1) número da conta, (2) pelo nome e (3) pelo CPF do Cliente
    @Query("SELECT * FROM contas WHERE numero = :numeroConta LIMIT 1")
    Conta buscarPeloNumero(String numeroConta);

    @Query("SELECT * FROM contas WHERE nomeCliente LIKE :titularConta")
    List<Conta> buscarPeloNome(String titularConta);

    @Query("SELECT * FROM contas WHERE cpfCliente LIKE :cpfTitularConta")
    List<Conta> buscarPeloCPF(String cpfTitularConta);



}
