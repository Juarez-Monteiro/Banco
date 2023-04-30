Sistema desenvolvido com fins acadêmicos para disciplina da android com JAVA.

Funcionalidades do app BANCO

1-   Na class ContasActivity foi utilizado o método observe para que toda vez que 
     tiver alteração no banco em relação às contas faz com quer o adapter atualize a 
     lista que será apresentada com todas as contas.

2-   Na classe ContaViewHolder no método bindTo onde é condicionado a aparição de 
     uma imagem vinculada ao Saldo da conta.

3-4- Na classe AdicionarContaActivity e utilizado o método onCreate para captura
     todas as informações digitadas nos campos específicos realizando validações
     no momento do click, nessas avaliações foi utilizado o método setErro onde 
     apresenta uma exclamação vermelha e um campo de texto ao clkicar na 
     exclamação trazendo a mensagem na qual o erro se refere, estando tudo ok 
     é criado um novo objeto do tipo conta contendo todos os dados validos e 
     em seguida é chamado o método insert da ContaViewModel.

5-   Na classe ContaDAO foi criado os métodos atualizar e remover e os métodos 
     de busca pelo CPF, Nome e Número da conta.

6-   Na classe ContaRepository foi criado os metodos atualizar e remor contas, 
     esses métodos utilizam os métodos criados na classe ContaRepository.

7-8- Na classe EditarContaActivity foi criado funcionalidade para recuperar os 
     dados das contas e as informações digitadas, implementado funcionalidade 
     que utiliza a ContaViewModel atualizar ou remover do banco de dados.

9-10-Na classe EditarContaActivity foi incluido funcionabilidade para validar 
     os campos e não haver campos errados na hora de salvar no banco, criado
     também funcionabilidade para a remover objeto do banco atraves da 
     ContaViewModel.

11-  Na classe BancoViewModel foi criado métodos para realizar as operações de 
     transferir creditar e debitar e os métodos de buscar  por nome do cliente 
     e pelo cpf.

12-  Nas classes DebitarActivity, CreditarActivity, e TransferirActivity foi 
     implementados funcionalidades para validações e chamar os métodos na classe 
     BancoViewModel

13-14-Na classe PesquisarActivity foi implementado funcionalidades para chamar 
     os métodos de busca a partir da opção escolhida pelo usuário, utilizando 
     também o método de RecyclerView para que seja possível renderizar na mesma
     tela utilizando do adapter e do método observer para atualizar sempre que 
     houver uma nova pesquisa.

15-  Na classe MainActivity utilizando do método observer para sempre atualizar 
     o saldo total do Banco sempre que houver modificação.
     
     
     Link para vídeo vídeo https://youtu.be/rFfsx_oqQ1Y
