import java.util.Scanner;

/**
 * Esta classe representa uma aplicação de agenda para gerenciamento de contatos.
 * Permite adicionar, remover, editar, detalhar e listar contatos.
 * <p>
 * A agenda armazena os contatos em uma matriz bidimensional onde cada linha representa um contato,
 * e cada coluna representa um atributo do contato (ID, nome, telefone e e-mail).
 * <p>
 * A aplicação é executada a partir do método {@link #main(String[])} e oferece um menu de opções
 * para o usuário interagir com a agenda.
 *
 * @since 1.0
 */
public class AgendaApp {
    static int proximoId = 1;

    static int capacidadeInicial = 1;
    static int tamanhoAtual = 0;

    static int totalAtributos = 4;

    static final int COLUNA_ID_WIDTH = 2;
    static final int COLUNA_NOME_WIDTH = 30;
    static final int COLUNA_TELEFONE_WIDTH = 20;
    static final int COLUNA_EMAIL_WIDTH = 30;

    static final int INDEX_ID = 0;
    static final int INDEX_NOME = 1;
    static final int INDEX_TELEFONE = 2;
    static final int INDEX_EMAIL = 3;

    static String[][] data = new String[capacidadeInicial][totalAtributos];

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        while (true) {

            exibirMenu();

            int opcao = solicitarOpcaoUsuario(input, "Informe a operação desejada: ");

            if (opcao == 9) break;

            switch (opcao) {
                case 1:
                    // Adicionar

                    System.out.println("═════════════ Adicionar novo contato ═══════════════");
                    String nome = solicitarEntradaTexto(input, "Nome: ", false);
                    String telefone = solicitarEntradaTexto(input, "Telefone: ", false);
                    String email = solicitarEntradaTexto(input, "Email: ", false);

                    String[] novoContato = {null, nome, telefone, email};

                    String[] adicionado = adicionar(novoContato);

                    String AdicionarMensagem = adicionado != null ? "Contato adicionado!" : "Falha ao adicionar contato!";

                    System.out.printf("%s Enter para continuar...%n", AdicionarMensagem);
                    input.nextLine();
                    break;
                case 2:
                    // Remover
                    System.out.println("═════════════════ Remover contato ══════════════════");
                    String idParaRemover =
                            solicitarEntradaTexto(input, "Id [0 para cancelar]: ", false);

                    if (idParaRemover.equals("0")) break;

                    String[] removido = remover(idParaRemover);

                    String mensagem = removido != null ? "Contato removido!" : "Id não encontrado!";

                    System.out.printf("%s Enter para continuar...", mensagem);
                    input.nextLine();
                    break;
                case 3:
                    // Detalhar
                    System.out.println("════════════════ Detalhar contato ══════════════════");
                    String idParaListar =
                            solicitarEntradaTexto(input, "Id [0 para cancelar]: ", false);

                    if (idParaListar.equals("0")) break;

                    detalhar(idParaListar);


                    System.out.println("Enter para continuar...");
                    input.nextLine();
                    break;
                case 4:
                    // Editar
                    System.out.println("═════════════════ Editar contato ═══════════════════");
                    String idParaEditar =
                            solicitarEntradaTexto(input, "Id [0 para cancelar]: ", false);

                    if (idParaEditar.equals("0")) break;

                    int indiceParaEditar = verificarIdExistente(idParaEditar);

                    if (indiceParaEditar < 0) {
                        System.out.println("Id não encontrado! Tente novamente ou digite '0' para cancelar. ");
                    } else {
                        System.out.println("CASO ALGUM CAMPO FICAR SEM PREENCHER OS DADOS ANTERIORES SERÃO MANTIDOS");
                        String novoNome = solicitarEntradaTexto(input, "Novo Nome: ", true);
                        String novoTelefone = solicitarEntradaTexto(input, "Novo Telefone: ", true);
                        String novoEmail = solicitarEntradaTexto(input, "Novo Email: ", true);

                        String[] editado = {idParaEditar, novoNome, novoTelefone, novoEmail};

                        editar(indiceParaEditar, editado);
                    }
                    System.out.println("Enter para continuar...");
                    input.nextLine();
                    break;
                case 5:
                    // Listar
                    System.out.println("═════════════════ Listar contatos ══════════════════");
                    listar();

                    System.out.println("Enter para continuar...");
                    input.nextLine();
                    break;
                case 6:
                    // Sobre

                    exibirSobre();

                    System.out.println("Enter para continuar...");
                    input.nextLine();
                    break;
                default:
                    System.out.println("Opção inválida! Enter para continuar...");
                    input.nextLine();
            }
        }

        input.close();
    }

    /**
     * Solicita ao usuário que insira uma opção numérica, exibindo uma mensagem.
     * O método continuará solicitando até que uma entrada válida seja fornecida.
     *
     * @param input    Scanner para capturar a entrada do usuário.
     * @param mensagem Mensagem a ser exibida solicitando a entrada.
     * @return A opção numérica inserida pelo usuário.
     */
    static int solicitarOpcaoUsuario(Scanner input, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = input.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Por favor, insira um número. Pressione Enter para continuar...");
                input.nextLine();
            }
        }
    }

    /**
     * Solicita ao usuário que insira um texto, exibindo uma mensagem.
     * Permite que a entrada seja vazia se o parâmetro {@code podeSerVazio} for verdadeiro.
     *
     * @param input        Scanner para capturar a entrada do usuário.
     * @param mensagem     Mensagem a ser exibida solicitando a entrada.
     * @param podeSerVazio Se {@code true}, permite que a entrada seja vazia.
     * @return O texto inserido pelo usuário.
     */
    static String solicitarEntradaTexto(
            Scanner input, String mensagem, boolean podeSerVazio) {
        while (true) {
            System.out.print(mensagem);
            String entrada = input.nextLine().trim();

            if (!entrada.isEmpty() || podeSerVazio) {
                return entrada;
            }

            System.out.println("Não pode ser vazio! Enter para continuar...");
            input.nextLine();
        }
    }

    /**
     * Adiciona um novo contato à agenda.
     *
     * @param novoContato Um array contendo os dados do novo contato (ID, nome, telefone, e-mail).
     * @return O contato adicionado ou {@code null} se o telefone ou e-mail já estiverem cadastrados.
     */
    static String[] adicionar(String[] novoContato) {
        if (telefoneOuEmailDuplicado(-1, novoContato)) {
            return null;
        }

        if (tamanhoAtual == data.length)
            crescerMatriz();

        novoContato[INDEX_ID] = Integer.toString(proximoId); // Definindo o ID do contato

        data[tamanhoAtual] = novoContato;

        tamanhoAtual++;
        proximoId++;

        return novoContato;
    }

    /**
     * Remove um contato da agenda com base no ID fornecido.
     *
     * @param contactId O ID do contato a ser removido.
     * @return O contato removido ou {@code null} se o ID não for encontrado.
     */
    static String[] remover(String contactId) {
        int indiceParaRemover = verificarIdExistente(contactId);

        if (indiceParaRemover < 0) {
            return null;
        }

        String[] contatoRemovido = data[indiceParaRemover];

        for (int j = indiceParaRemover; j < tamanhoAtual - 1; j++) {
            data[j] = data[j + 1];
        }

        data[tamanhoAtual - 1] = null;

        tamanhoAtual--;
        return contatoRemovido;
    }

    static void listar() {
        String format = "| %-" + COLUNA_ID_WIDTH + "s | %-" + COLUNA_NOME_WIDTH + "s |%n";

        System.out.printf(format, "ID", "Nome");
        System.out.printf("+-%s-+-%s-+%n",
                "-".repeat(COLUNA_ID_WIDTH),
                "-".repeat(COLUNA_NOME_WIDTH));

        for (int i = 0; i < tamanhoAtual; i++) {
            String[] contato = data[i];
            System.out.printf(format, contato[INDEX_ID], contato[INDEX_NOME]);
        }
    }

    /**
     * Exibe os detalhes de um contato com base no ID fornecido.
     *
     * @param idParaListar O ID do contato a ser detalhado.
     */
    static void detalhar(String idParaListar) {
        int indiceParalistar = verificarIdExistente(idParaListar);

        if (indiceParalistar < 0) {
            System.out.println("Id não encontrado! Tente novamente ou digite '0' para cancelar.");
            return;
        }
        String format = "| %-" + COLUNA_ID_WIDTH + "s | %-" + COLUNA_NOME_WIDTH + "s | %-" + COLUNA_TELEFONE_WIDTH + "s | %-" + COLUNA_EMAIL_WIDTH + "s %n";

        System.out.printf(format, "ID", "Nome", "Telefone", "Email");
        System.out.printf("+-%s-+-%s-+-%s-+-%s-+%n",
                "-".repeat(COLUNA_ID_WIDTH),
                "-".repeat(COLUNA_NOME_WIDTH),
                "-".repeat(COLUNA_TELEFONE_WIDTH),
                "-".repeat(COLUNA_EMAIL_WIDTH));

        String[] contato = data[indiceParalistar];
        System.out.printf(format,
                contato[INDEX_ID],
                contato[INDEX_NOME],
                contato[INDEX_TELEFONE],
                contato[INDEX_EMAIL]);
    }

    /**
     * Verifica se um contato com o ID fornecido existe na agenda.
     *
     * @param contactId O ID do contato a ser verificado.
     * @return O índice do contato na matriz se existir, ou {@code -1} se não existir.
     */
    static int verificarIdExistente(String contactId) {
        for (int i = 0; i < tamanhoAtual; i++) {
            if (data[i][INDEX_ID].equals(contactId)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Verifica se um telefone já está cadastrado na agenda.
     *
     * @param telefone O telefone a ser verificado.
     * @return O índice do contato na matriz se o telefone já existir, ou {@code -1} se não existir.
     */
    static int verificarTelefoneExiste(String telefone) {
        for (int i = 0; i < tamanhoAtual; i++) {
            String telefoneItem = data[i][INDEX_TELEFONE];
            if (telefoneItem.trim()
                    .replace(" ", "")
                    .equals(telefone.trim()
                            .replace(" ", ""))) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Verifica se um e-mail já está cadastrado na agenda.
     *
     * @param email O e-mail a ser verificado.
     * @return O índice do contato na matriz se o e-mail já existir, ou {@code -1} se não existir.
     */
    static int verificarEmailExiste(String email) {
        for (int i = 0; i < tamanhoAtual; i++) {
            String emailItem = data[i][INDEX_EMAIL];
            if (emailItem.trim().equals(email.trim())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Verifica se o telefone ou e-mail de um contato editado já estão cadastrados.
     *
     * @param indiceContato O índice do contato que está sendo editado.
     * @param contatoEditado Um array contendo os dados do contato editado (ID, nome, telefone, e-mail).
     * @return {@code true} se o telefone ou e-mail já estiverem cadastrados, {@code false} caso contrário.
     */
    private static boolean telefoneOuEmailDuplicado(int indiceContato, String[] contatoEditado) {
        int indiceTelefoneDuplicado = verificarTelefoneExiste(contatoEditado[INDEX_TELEFONE]);
        if (indiceTelefoneDuplicado >= 0 && indiceTelefoneDuplicado != indiceContato) {
            System.out.println("Telefone já cadastrado!");
            return true;
        }

        int indiceEmailDuplicado = verificarEmailExiste(contatoEditado[INDEX_EMAIL]);
        if (indiceEmailDuplicado >= 0 && indiceEmailDuplicado != indiceContato) {
            System.out.println("Email já cadastrado!");
            return true;
        }

        return false;
    }

    /**
     * Edita um contato na agenda com base no índice fornecido.
     *
     * @param indiceContato O índice do contato a ser editado.
     * @param contatoEditado Um array contendo os dados atualizados do contato (ID, nome, telefone, e-mail).
     */
    static void editar(int indiceContato, String[] contatoEditado) {
        if (telefoneOuEmailDuplicado(indiceContato, contatoEditado)) {
            return;
        }

        data[indiceContato][INDEX_NOME] =
                contatoEditado[INDEX_NOME].isEmpty() ? data[indiceContato][INDEX_NOME] : contatoEditado[INDEX_NOME];
        data[indiceContato][INDEX_TELEFONE] =
                contatoEditado[INDEX_TELEFONE].isEmpty() ? data[indiceContato][INDEX_TELEFONE] : contatoEditado[INDEX_TELEFONE];
        data[indiceContato][INDEX_EMAIL] =
                contatoEditado[INDEX_EMAIL].isEmpty() ? data[indiceContato][INDEX_EMAIL] : contatoEditado[INDEX_EMAIL];

        System.out.println("Contato atualizado com sucesso!");
    }

    static void crescerMatriz() {
        int novaCapacidade = data.length + 1;
        String[][] novaMatriz = new String[novaCapacidade][totalAtributos];

        // copia os dados da matriz atual para a nova matriz
        for (int i = 0; i < data.length; i++) {
            novaMatriz[i] = data[i];
        }
        data = novaMatriz;
    }

    static void exibirMenu() {
        String contador = String.format("%04d", tamanhoAtual);

        System.out.println("""
                ╔══════════════════════════════════════════════════╗
                ║             _                    _               ║
                ║            /_\\  __ _ ___ _ _  __| |__ _          ║
                ║           / _ \\/ _` / -_) ' \\/ _` / _` |         ║
                ║          /_/ \\_\\__, \\___|_||_\\__,_\\__,_|         ║
                ║                |___/                             ║
                ║          Coders 2024       versão: 1.0.0         ║
                ╠══════════════════════════════════════════════════╣
                ║                  >>>> MENU <<<<                  ║
                ║                                                  ║
                ║        [1] - Adicionar Contato                   ║
                ║        [2] - Remover Contato                     ║
                ║        [3] - Detalhar Contato                    ║
                ║        [4] - Editar Contato                      ║
                ║        [5] - Listar Contatos                     ║
                ║        [6] - Sobre                               ║
                ║                                                  ║
                ║        [9] - Sair                                ║
                ╚══════════════════════════════ Contatos""" + " " + contador + " ═════╝");
    }

    static void exibirSobre() {
        System.out.println("""
                ╔══════════════════════════════════════════════════╗
                ║             _                    _               ║
                ║            /_\\  __ _ ___ _ _  __| |__ _          ║
                ║           / _ \\/ _` / -_) ' \\/ _` / _` |         ║
                ║          /_/ \\_\\__, \\___|_||_\\__,_\\__,_|         ║
                ║                |___/                             ║
                ║          Coders 2024       versão: 1.0.0         ║
                ╠══════════════════════════════════════════════════╣
                ║                  >>>> SOBRE <<<<                 ║
                ║                                                  ║
                ║    A aplicação Agenda foi desenvolvida como      ║
                ║  projeto de conclusão do módulo 'Lógica de       ║
                ║  Programação I', no curso 'Santander Coders      ║
                ║  2024.1 - Back-End'.                             ║
                ║                                                  ║
                ║                 COLABORADORES                    ║
                ║      -  Diogo Lucas de Oliveira                  ║
                ║      -  Eloise Helena Moraes                     ║
                ║      -  Irving Lui                               ║
                ║      -  Isaque Menezes                           ║
                ║                                                  ║
                ╚══════════════════════════════════════════════════╝""");
    }
}
