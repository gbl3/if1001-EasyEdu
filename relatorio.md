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
  - Entrar em uma turma
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

