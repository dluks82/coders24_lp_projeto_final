import java.util.Scanner;

public class AgendaApp {
    static int proximoId = 1;

    static int capacidadeInicial = 1;
    static int tamanhoAtual = 0;

    static int totalAtributos = 4;
    static String[][] data = new String[capacidadeInicial][totalAtributos];

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        while (true) {

            exibirMenu();

            int opcao = receberOpcao(input, "Informe a operação desejada: ");

            if (opcao == 9) break;

            switch (opcao) {
                case 1:
                    // Adicionar

                    System.out.println("═════════════ Adicionar novo contato ═══════════════");
                    String nome = receberStringInput(input, "Nome: ", false);
                    String telefone = receberStringInput(input, "Telefone: ", false);
                    String email = receberStringInput(input, "Email: ", false);

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
                            receberStringInput(input, "Id [0 para cancelar]: ", false);

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
                            receberStringInput(input, "Id [0 para cancelar]: ", false);

                    if (idParaListar.equals("0")) break;

                    // TODO: implementar

                    System.out.println("Enter para continuar...");
                    input.nextLine();
                    break;
                case 4:
                    // Editar
                    System.out.println("═════════════════ Editar contato ═══════════════════");
                    String idParaEditar =
                            receberStringInput(input, "Id [0 para cancelar]: ", false);

                    if (idParaEditar.equals("0")) break;

                    int indiceParaEditar = verificarIdExistente(idParaEditar);

                    if (indiceParaEditar < 0) {
                        System.out.println("Id não encontrado! Tente novamente ou digite '0' para cancelar. ");
                    } else {
                        System.out.println("CASO ALGUM CAMPO FICAR SEM PREENCHER OS DADOS ANTERIORES SERÃO MANTIDOS");
                        String novoNome = receberStringInput(input, "Novo Nome: ", true);
                        String novoTelefone = receberStringInput(input, "Novo Telefone: ", true);
                        String novoEmail = receberStringInput(input, "Novo Email: ", true);

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

                    // TODO: implementar

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

    static int receberOpcao(Scanner input, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido! Enter para continuar...");
                input.nextLine();
            }
        }
    }

    static String receberStringInput(
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
        if (verificarTelefoneExiste(novoContato[2]) >= 0) {
            System.out.println("Telefone já cadastrado!");
            return null;
        }

        if (verificarEmailExiste(novoContato[3]) >= 0) {
            System.out.println("Email já cadastrado!");
            return null;
        }

        if (tamanhoAtual == data.length)
            crescerMatriz();

        novoContato[0] = Integer.toString(proximoId); // Definindo o ID do contato

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
        if (idExiste != tamanhoAtual - 1) {
            for (int j = idExiste; j < tamanhoAtual - 1; j++) {
                data[j] = data[j + 1];
            }
        }
        tamanhoAtual--;
        return data[idExiste];
    }

    static void listar() {

        int charactersColunaId = 2;
        int charactersColunaNome = 30;

        String format = "| %-" + charactersColunaId + "s | %-" + charactersColunaNome + "s |%n";

        System.out.printf(format, "ID", "Nome");
        System.out.printf("+-%s-+-%s-+%n",
                "-".repeat(charactersColunaId),
                "-".repeat(charactersColunaNome));

        for (int i = 0; i < tamanhoAtual; i++) {
            String[] contato = data[i];
            System.out.printf(format, contato[0], contato[1]);
        }

    }

    static int verificarIdExistente(String contactId) {
        for (int i = 0; i < tamanhoAtual; i++) {
            if (data[i][0].equals(contactId)) {
                return i;
            }
        }
        return -1;
    }

    static int verificarTelefoneExiste(String telefone) {
        for (int i = 0; i < tamanhoAtual; i++) {
            String telefoneItem = data[i][2];
            if (telefoneItem.trim().equals(telefone.trim())) {
                return i;
            }
        }
        return -1;
    }

    static int verificarEmailExiste(String email) {
        for (int i = 0; i < tamanhoAtual; i++) {
            String emailItem = data[i][3];
            if (emailItem.trim().equals(email.trim())) {
                return i;
            }
        }
        return -1;
    }

    static void editar(int indiceParaEditar, String[] contatoEditado) {
        int indiceTelefoneDuplicado = verificarTelefoneExiste(contatoEditado[2]);
        if (indiceTelefoneDuplicado >= 0 && indiceTelefoneDuplicado != indiceParaEditar) {
            System.out.println("Telefone já cadastrado!");
            return;
        }

        int indiceEmailDuplicado = verificarEmailExiste(contatoEditado[3]);
        if (indiceEmailDuplicado >= 0 && indiceEmailDuplicado != indiceParaEditar) {
            System.out.println("Email já cadastrado!");
            return;
        }

        // atualiza ou mantém nome
        data[indiceParaEditar][1] =
                contatoEditado[1].isEmpty() ? data[indiceParaEditar][1] : contatoEditado[1];
        // atualiza ou mantém telefone
        data[indiceParaEditar][2] =
                contatoEditado[2].isEmpty() ? data[indiceParaEditar][2] : contatoEditado[2];
        // atualiza ou mantém e-mail
        data[indiceParaEditar][3] =
                contatoEditado[3].isEmpty() ? data[indiceParaEditar][3] : contatoEditado[3];

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
}
