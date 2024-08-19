import java.util.Scanner;

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

    // Daqui em diante ficam os métodos implementados

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

    static String[] adicionar(String[] novoContato) {
        if (contatoExiste(novoContato[INDEX_TELEFONE], "Telefone") ||
                contatoExiste(novoContato[INDEX_EMAIL], "Email")) {
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

    static String[] remover(String contactId) {
        int idExiste = verificarIdExistente(contactId);

        if (idExiste < 0) {
            return null;
        }

        String[] contatoRemovido = data[idExiste];

        for (int j = idExiste; j < tamanhoAtual - 1; j++) {
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

    static boolean contatoExiste(String valor, String tipo) {
        if (tipo.equals("Telefone") && verificarTelefoneExiste(valor) >= 0) {
            System.out.println(tipo + " já cadastrado!");
            return true;
        } else if (tipo.equals("Email") && verificarEmailExiste(valor) >= 0) {
            System.out.println(tipo + " já cadastrado!");
            return true;
        }
        return false;
    }

    static int verificarIdExistente(String contactId) {
        for (int i = 0; i < tamanhoAtual; i++) {
            if (data[i][INDEX_ID].equals(contactId)) {
                return i;
            }
        }
        return -1;
    }

    static int verificarTelefoneExiste(String telefone) {
        for (int i = 0; i < tamanhoAtual; i++) {
            String telefoneItem = data[i][INDEX_TELEFONE];
            if (telefoneItem.trim().equals(telefone.trim())) {
                return i;
            }
        }
        return -1;
    }

    static int verificarEmailExiste(String email) {
        for (int i = 0; i < tamanhoAtual; i++) {
            String emailItem = data[i][INDEX_EMAIL];
            if (emailItem.trim().equals(email.trim())) {
                return i;
            }
        }
        return -1;
    }

    static void editar(int indiceParaEditar, String[] contatoEditado) {
        int indiceTelefoneDuplicado = verificarTelefoneExiste(contatoEditado[INDEX_TELEFONE]);
        if (indiceTelefoneDuplicado >= 0 && indiceTelefoneDuplicado != indiceParaEditar) {
            System.out.println("Telefone já cadastrado!");
            return;
        }

        int indiceEmailDuplicado = verificarEmailExiste(contatoEditado[INDEX_EMAIL]);
        if (indiceEmailDuplicado >= 0 && indiceEmailDuplicado != indiceParaEditar) {
            System.out.println("Email já cadastrado!");
            return;
        }

        data[indiceParaEditar][INDEX_NOME] =
                contatoEditado[INDEX_NOME].isEmpty() ? data[indiceParaEditar][INDEX_NOME] : contatoEditado[INDEX_NOME];
        data[indiceParaEditar][INDEX_TELEFONE] =
                contatoEditado[INDEX_TELEFONE].isEmpty() ? data[indiceParaEditar][INDEX_TELEFONE] : contatoEditado[INDEX_TELEFONE];
        data[indiceParaEditar][INDEX_EMAIL] =
                contatoEditado[INDEX_EMAIL].isEmpty() ? data[indiceParaEditar][INDEX_EMAIL] : contatoEditado[INDEX_EMAIL];

        System.out.println("Contato atualizado com sucesso!");
    }

    static void crescerMatriz() {
        // matriz atual + 1
        int novaCapacidade = data.length + 1;
        // cria a nova matriz
        String[][] novaMatriz = new String[novaCapacidade][totalAtributos];
        // copia os dados da matriz atual para a nova matriz
        for (int i = 0; i < data.length; i++) {
            novaMatriz[i] = data[i];
        }
        // define a nova matriz como a atual
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
                ╚══════════════════════════════ Contatos""" + " " + contador + " ═════╝"); // 52 de largura
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
