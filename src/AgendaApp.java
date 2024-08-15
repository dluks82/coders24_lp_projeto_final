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

            int opcao = input.nextInt();
            input.nextLine();

            if (opcao == 9) break;

            switch (opcao) {
                case 1:
                    // Adicionar

                    System.out.println("═════════════ Adicionar novo contato ═══════════════");
                    System.out.print("Nome: ");
                    String nome = input.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = input.nextLine();
                    System.out.print("Email: ");
                    String email = input.nextLine();

                    String[] novoContato = {null, nome, telefone, email};

                    String[] adicionado = adicionar(novoContato);

                    System.out.println("Contato adicionado! Enter para continuar...");
                    input.nextLine();
                    break;
                case 2:
                    // Remover
                    System.out.println("═════════════════ Remover contato ══════════════════");
                    System.out.print("Id [0 para cancelar]: ");
                    String idParaRemover = input.nextLine();

                    if (idParaRemover.equals("0")) break;

                    String[] removido = remover(idParaRemover);

                    String mensagem = removido != null ? "Contato removido!" : "Id não encontrado!";

                    System.out.printf("%s Enter para continuar...", mensagem);
                    input.nextLine();
                    break;
                case 3:
                    // Detalhar
                    System.out.println("════════════════ Detalhar contato ══════════════════");
                    System.out.print("Id [0 para cancelar]: ");
                    String idParaListar = input.nextLine();

                    if (idParaListar.equals("0")) break;

                    // TODO: implementar

                    System.out.println("Enter para continuar...");
                    input.nextLine();
                    break;
                case 4:
                    // Editar
                    System.out.println("═════════════════ Editar contato ═══════════════════");
                    System.out.print("Id [0 para cancelar]: ");
                    String idParaEditar = input.nextLine();

                    if (idParaEditar.equals("0")) break;

                    // TODO: implementar

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

    static String[] adicionar(String[] novoContato) {
        if (tamanhoAtual == data.length)
            crescerMatriz();

        novoContato[0] = Integer.toString(proximoId); // Definindo o ID do contato

        data[tamanhoAtual] = novoContato;

        tamanhoAtual++;
        proximoId++;

        return novoContato;
    }

    static String[] remover(String contactId) {
        if (Integer.parseInt(contactId) > tamanhoAtual) return null;

        for (int i = 0; i < tamanhoAtual; i++) {
            String[] contato = data[i];
            if (contato[0].equals(contactId)) {
                if (i != tamanhoAtual - 1) {
                    for (int j = i; j < tamanhoAtual; j++) {
                        data[j] = data[j + 1];
                    }
                }
                tamanhoAtual--;
                return contato;
            }
        }
        return null;
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
        System.out.print("Informe a operação desejada: ");
    }
}
