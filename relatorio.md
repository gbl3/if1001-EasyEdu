# Projeto: EasyEdu

## Equipe:
- Eric Araújo: Desenvolvedor
- Gustavo Lopes: Desenvolvedor
- Pedro Vinícius: Desenvolvedor

## Objetivo
Uma aplicação voltada para educação com o objetivo de facilitar algumas atividades do cotidiano dos professores e alunos universitários. Buscando otimizar os processos envolvidos na rotina destes indivíduos, como por exemplo: realização de chamada, criação de posts/anotações, realização de atividades/provas remotamente, entre outros.

## Páginas Criadas (Activitys)

### Geral
- Menu inicial
- Tela de login
- Tela de cadastro
- Tela de postagens/anotações
### Para o Professor
- Tela de listagem de alunos que responderam a chamada
- Tela de cadastro de turmas
- Tela de criação de provas
- Tela de criação de questões
- Telas para geração de QRCode e validação de geolocalização
- 
### Para o Aluno
- Tela de responder chamada com scanner QRCode
- Tela de validação de geolocalização
- Tela de realização das questões de prova
- Tela para fazer parte de uma turma

## Banco de Dados e Sistema de Autenticação Utilizados
- Fizemos toda a implementação via local, a ideia era fazer uma versão funcional que desse para mostrar as funcionalidades, mas numa versão local.
- Room Database (Toda a autenticação e todas as activitys que precisavam que os dados fossem salvos foi usados do Room que foi ensinado em aula).
- *OBS:* Tentamos utilizar do Firebase para toda a parte de armazenamento de dados, mas acabamos não conseguindo e decidimos fazer todo o projeto de forma local. Nunca haviamos trabalho com android nem com kotlin, então foi uma decisão que acabamos tomando.

 ## Conteúdos das aulas utilizados
 - Adapters
 - Recycler View
 - Activities
 - Threads
 - Permissions
 - Room
 
## Layouts Utilizados
- RelativeLayout
- LinearLayout
- ConstraintLayout
- ScrollView
 
 
# Features Implementadas
- Sistema de Login e Cadastro com diferença de perfis (Professor e Aluno)
- Postagem de anotações e comentários
- Gerar QRCode para ser lido
- Leitor de QRCode para ler o código gerado e responder a chamada
- Responder a chamada com cálculo de distância entre coordenadas
- Criar Turmas
- Criação de provas/atividades
- Criação de questões para as alocar nas provas, e separadas por turmas
- Tela para checaguem de quais alunos responderam a chamada
- Separação das telas da aplicação por perfis 

## Estrutura da aplicação 

- Tela inicial
- Cadastro
- Login

*Para Alunos:*

- Ver turmas
  - Ver provas da turma
    - Ver questões da prova
  - Ver posts da turma
    - Fazer um novo post
      - Ver um post
  - Responder chamada
- Fazer logout

*Para Professores:*

- Criar turmas
  - Adicionar turma
    - Ver Prova
      - Criar Prova
        - Inserir Questão 
  - Ver posts da turma
    - Fazer um novo Post
      - Ver um post
  - Fazer chamada
    - Gerar QrCode
- Fazer Logout

# Descrição da Implementação

## Principais Features:

Gerar/Ler QR Code: Utilizando a biblioteca Zxing foi possível criar a implementação do leitor e do gerador de QR codes utilizados na marcação de presença dos alunos. 
A classe QRCodeGenerator recebe uma string de entrada e encoda um bitmap de saída com o QR Code gerado. Dentro da classe existe a função getPosition salva as coordenadas do professor na tabela "professor" para que mais tarde seja comparada com as coordenadas do aluno que escaneou o código QR, tudo isso utilizando o Haversine algorithm para o cálculo da distância.
A classe QRCodeScan abre um scanner de qrcodes na tela da aplicação e captura o código qr gerado previamente juntamente com as coordenadas do aluno, a tolerância de distância entre um aluno e um professor foi setada para 30m, acima disso, sua presença não é salva.

PerfilActivity é a classe que mostra a homescreen após o login, nesta estão implementados os botões com seus respectivos listeners que levam as partes principais da aplicação.

A ProvasActivity renderiza as provas existentes, onde temos a tela de criação de provas, só são exibidas nessa activity as provas referentes à turmas que o aluno ou professor participam, através do idTurma é possível saber a qual turma aquela prova pertence e quais pessoas fazem parte daquela turma.
ExibeProvasActivity implementa uma recyclerview, renderizando um item genérico que segue o conceito do recycler view que também conta com um Adapter, o AdapterProvas, que prepara um dado para ser inserido na view e exibindo-o após ser definido na tela, a AdicionarProvasActivity é um crud que adiciona uma nova prova à uma turma.
Uma prova conta com questões, que também conta com um adapter, o adapter questões, que segue a mesma linha do provasAdapter, prepara o dado pra ser inserido na view. O intuito das questões é que exista um banco de questões na aplicação para que o professor possa utilizar posteriormente em atividades em sala ou coisas do tipo.

A TurmasActivity é a tela principal da aplicação, nela é possível ter acesso a provas, posts, o leitor e o scanner de QRCode. A turmas Activity exibe provas e posts diferentes para turmas diferentes, bem como cria qrcodes diferentes para as mesmas. Como um professor é possível criar uma nova turma, e quem cuida disso é o AdicionarTurmaActivity. Que segue o mesmo conceito do AdicionarProvasActivity também sendo um crud, o ExibeTurmaActivity conta com o mesmo ponto de vista do ExibeProvasActivity, implementando um recyclerview e contando com um Adapter, o AdapterTurmas.

PostsActivity também segue o mesmo princípio dos seus predecessores, cria um novo post no AdicionarPostActivity e exibe os posts já existentes no ExibePostsActivity, separando-os de acordo com a turma da qual se faz parte através do idTurma.

Existe a tabela de usuário que guarda dados de usuários registrados, na LoginActivity são feitas comparações com os dados inseridos na tabela de usuário, caso existam, o login é realizado, caso contrário é exibida uma mensagem de erro informando que o usuário não está cadastrado.
Na CadastroActivity são feitas algumas validações, por exemplo, não é possível se cadastrar sem escolher um dos perfis existentes (Aluno ou Professor), Existe também a tabela chamada UsuárioAtual, ao realizar o login o usuário é automaticamente adicionado à essa tabela, as primeiras telas checam se o usuário está logado para realizar as validações necessárias (como por exemplo quais tela deve ver).
