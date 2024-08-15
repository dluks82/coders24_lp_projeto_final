import java.util.Scanner;

public class AgendaApp {
    static int proximoId = 1;

    static int capacidadeInicial = 1;
    static int tamanhoAtual = 0;

    static int totalAtributos = 4;
    static String[][] data = new String[capacidadeInicial][totalAtributos];

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("Tamanho atual: " + tamanhoAtual);

        // Adicionar primeiro contato
        String[] novoContato1 = new String[4];
        novoContato1[0] = Integer.toString(proximoId); // Definindo o ID do contato
        System.out.print("informe o nome: ");
        novoContato1[1] = input.nextLine();
        System.out.print("informe o telefone: ");
        novoContato1[2] = input.nextLine();
        System.out.print("informe o email: ");
        novoContato1[3] = input.nextLine();

        String[] adicionado;
        adicionado = adicionar(novoContato1);
        System.out.printf("O contato: %s com id %s foi adicionado!%n",
                adicionado[1], adicionado[0]);

        // Adicionando mais 2 contatos para teste
        String[] outroContato = {null, "Diana", "42 77", "diana"};
        adicionado = adicionar(outroContato);
        System.out.printf("O contato: %s com id %s foi adicionado!%n",
                adicionado[1], adicionado[0]);

        outroContato = new String[]{null, "Eli", "41 66", "eli"};
        adicionado = adicionar(outroContato);
        System.out.printf("O contato: %s com id %s foi adicionado!%n",
                adicionado[1], adicionado[0]);

        // Removendo...
        String[] removido;
        removido = remover("3");
        if (removido != null) {
            System.out.printf("O contato: %s com id %s foi removido!%n",
                    removido[1], removido[0]);
        }

        // Verificando os contatos no array, somente teste
        listar();
        removido = remover("1");
        if (removido != null) {
            System.out.printf("O contato: %s com id %s foi removido!%n",
                    removido[1], removido[0]);
        }
        // teste
        listar();

        // A medida que os métodos e recursos sejam adicionados ao
        // programa, podemos remover os dados de teste

        input.close();
    }

    // Para teste
    static void imprimirContatos() {
        StringBuilder output = new StringBuilder("[\n");
        for (int i = 0; i < tamanhoAtual; i++) {

            String[] contato = data[i];
            output.append(String.format("{Id: %s, Nome: %s, Telefone: %s, Email: %s},%n",
                    contato[0],
                    contato[1],
                    contato[2],
                    contato[3]));
        }
        output.append("]");

        System.out.println(output);
        System.out.flush();
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
}
