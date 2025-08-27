package crud.Clientes;

import java.util.Scanner;

public class CrudCliente {

    public static void executar(Scanner scanner) {
        int opcao;
 
        do {
            System.out.println("\n========== MENU DE CLIENTES ==========");
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Consultar cliente");
            System.out.println("3 - Atualizar cliente");
            System.out.println("4 - Excluir cliente");
            System.out.println("0 - Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1 : new CreateCliente(scanner).executar();
                    case 2 : ReadCliente.executar(scanner);
                    case 3 : UpdateCliente.executar(scanner);
                    case 4 : DeleteCliente.executar(scanner);
                    case 0 : System.out.println("↩ Retornando ao menu principal...");
                    default : System.out.println("❌ Opção inválida. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Digite apenas números.");
                opcao = -1;
            }

        } while (opcao != 0);
    }
}
