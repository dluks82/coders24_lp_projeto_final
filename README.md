# **Agenda de Gerenciamento de Contatos**

## **Descrição do Projeto**

Este projeto foi desenvolvido como parte da conclusão do módulo Lógica de Programação I do curso Santander Coders 2024.1, sob a supervisão do instrutor Igor Mascarenhas. O objetivo principal é criar uma aplicação simples de gerenciamento de contatos em Java, sem o uso de orientação a objetos (OO). A aplicação permite que o usuário adicione, remova, edite, detalhe e liste contatos, utilizando uma matriz de arrays primitivos de Strings para armazenar os dados.

## **Funcionalidades**

- **Adicionar Contato:** Insere um novo contato com ID, nome, telefone e email, garantindo que não haja duplicidade de telefone ou e-mail.
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

## **Análise do Projeto**

Nesta seção, os integrantes da equipe compartilham suas experiências, desafios e sugestões de melhoria:

### **Desafios do Projeto**

- **Colaboração no GitHub:** Um dos maiores desafios foi a colaboração de código no GitHub, especialmente devido à falta de experiência com a plataforma.
- **Sintaxe do Java:** A falta de domínio da sintaxe do Java apresentou dificuldades, principalmente para quem tinha menos familiaridade com a linguagem.
- **Manipulação de Arrays:** Trabalhar com arrays primitivos em vez de utilizar orientação a objetos exigiu cuidado extra na implementação.
- **Verificação de Duplicidade:** Implementar verificações para garantir que não houvesse duplicidade de telefone e e-mail nos contatos foi desafiador.

### **Aspectos Interessantes**

- **Trabalho em Grupo:** A colaboração entre os membros foi enriquecedora, proporcionando troca de experiências e desenvolvimento de novas habilidades.
- **Aprendizado com Restrições:** O projeto permitiu aplicar conceitos fundamentais de programação, como controle de fluxo e manipulação de dados, de maneira prática e eficaz.
- **Experiência de Codificação:** Mesmo com as limitações impostas pelo uso de arrays primitivos, a experiência de codificação foi interessante e desafiadora.

### **Oportunidades de Melhoria**

- **Orientação a Objetos:** Refatorar o código para usar orientação a objetos facilitaria a manutenção e extensão do sistema.
- **Persistência de Dados:** Implementar persistência de dados em arquivos ou banco de dados garantiria que as informações dos contatos fossem mantidas entre as execuções.
- **Interface Gráfica:** Adicionar uma interface gráfica tornaria o programa mais acessível e fácil de usar.
- **Aprimoramento da Sintaxe:** Continuar estudando e praticando Java para melhorar a fluidez no desenvolvimento.

## **Contribuidores**

- **Diogo Oliveira**
- **Eloise Helena**
- **Irving Lui**
- **Isaque Menezes**

## **Licença**

Este projeto é licenciado sob a [MIT License](LICENSE).
