# **Agenda de Gerenciamento de Contatos**

## **Descrição do Projeto**

Este projeto foi desenvolvido como parte da conclusão do módulo Lógica de Programação I do curso Santander Coders
2024.1, sob a supervisão do instrutor Igor Mascarenhas. O objetivo principal é criar uma aplicação simples de
gerenciamento de contatos em Java, sem o uso de orientação a objetos (OO). A aplicação permite que o usuário adicione,
remova, edite, detalhe e liste contatos, utilizando uma matriz de arrays primitivos de Strings para armazenar os dados.

## **Funcionalidades**

- **Adicionar Contato:** Insere um novo contato com ID, nome, telefone e email, garantindo que não haja duplicidade de
  telefone ou e-mail.
- **Remover Contato:** Remove um contato existente com base no ID.
- **Editar Contato:** Permite a edição das informações de um contato existente.
- **Detalhar Contato:** Exibe todos os detalhes de um contato específico.
- **Listar Contatos:** Lista todos os contatos armazenados, mostrando apenas o ID e o nome.
- **Página Sobre:** Descreve o projeto e seus colaboradores.

## **Requisitos Funcionais**

- **Verificação de Duplicidade:** Não é permitido armazenar contatos com telefones e/ou e-mails já cadastrados.

## **Como Executar o Projeto**

Certifique-se de que você tem o **JDK** instalado em sua máquina.

1. **Clone o Repositório:**
   ```bash
   git clone https://github.com/dluks82/coders24_lp_projeto_final.git
   ```

2. **Compile o Código-fonte:**
    - Navegue até o diretório onde os arquivos do projeto estão localizados.
    - Utilize o `javac` para compilar o código:
      ```bash
      javac AgendaApp.java
      ```

3. **Execute o Programa:**
    - Após a compilação, execute o programa utilizando o `java`:
      ```bash
      java AgendaApp
      ```

4. **Interaja com o Menu de Operações:**
    - Após a execução, um menu intuitivo será exibido para o usuário selecionar as operações desejadas.
    - Siga as instruções no terminal para gerenciar seus contatos.

### Análise do Projeto

Nesta seção, os integrantes da equipe compartilham suas experiências, desafios e sugestões de melhoria:

#### Desafios do Projeto

- **Colaboração no GitHub**: A colaboração no GitHub, especialmente devido à falta de experiência
  com a plataforma. A necessidade de coordenar as contribuições e resolver conflitos de código exigiu um aprendizado
  contínuo.
- **Sintaxe do Java**: A falta de domínio da sintaxe do Java trouxe dificuldades, principalmente para quem tinha menos
  familiaridade com a linguagem, o que tornou o processo de desenvolvimento mais desafiador.
- **Manipulação de Arrays/Matrizes**: Utilizar exclusivamente arrays/matrizes para armazenar e manipular os dados dos
  contatos, sem recorrer à orientação a objetos, foi um desafio significativo. A complexidade aumentou ao gerenciar a
  estrutura e os valores diretamente pela aplicação.
- **Verificação de Duplicidade**: Implementar verificações para garantir que não houvesse duplicidade de telefone e
  e-mail nos contatos foi uma tarefa complexa que exigiu cuidado especial na implementação.

#### Aspectos Interessantes

- **Trabalho em Grupo**: A colaboração entre os membros foi uma experiência enriquecedora, proporcionando troca de
  experiências e desenvolvimento de novas habilidades. Trabalhar juntos ajudou a fixar os conceitos e a melhorar o
  entendimento do projeto.
- **Aprendizado com Restrições**: O projeto permitiu aplicar conceitos fundamentais de programação, como controle de
  fluxo e manipulação de dados, de forma prática e eficaz, mesmo com as limitações de utilizar somente o que foi visto e
  com o desafio no uso de arrays/matrizes.
- **Ambiente Real de Colaboração**: A experiência de trabalhar em um ambiente colaborativo real, desde o nivelamento dos
  colaboradores nas bases do Git, até a satisfação de ver a integração das contribuições na branch principal, foi
  altamente gratificante.
- **Diversidade de Ideias**: Ver diferentes abordagens para o mesmo problema permitiu a expansão do conhecimento e o
  aprimoramento das soluções, enriquecendo a experiência de codificação.

#### Oportunidades de Melhoria

- **Utilização de Pacotes**: Organizar melhor o projeto utilizando pacotes facilitaria a manutenção e a compreensão do
  código.
- **Orientação a Objetos**: Refatorar o código para usar orientação a objetos tornaria o sistema mais robusto e fácil de
  manter e estender.
- **Persistência de Dados**: Implementar persistência de dados em arquivos ou banco de dados garantiria que as
  informações dos contatos fossem mantidas entre as execuções, aumentando a utilidade prática do sistema.
- **Interface Gráfica**: Adicionar uma interface gráfica tornaria o programa mais acessível e fácil de usar para os
  usuários finais.
- **Aprimoramento da Sintaxe**: Continuar estudando e praticando Java é essencial para melhorar a fluidez no
  desenvolvimento e a qualidade do código.
- **Testes Automatizados**: Implementar testes automatizados garantiria a integridade das funcionalidades e facilitaria
  futuras manutenções e expansões do sistema.

## **Contribuidores**

- **Diogo Oliveira**
- **Eloise Helena**
- **Irving Lui**
- **Isaque Menezes**

## **Licença**

Este projeto é licenciado sob a [MIT License](LICENSE).
