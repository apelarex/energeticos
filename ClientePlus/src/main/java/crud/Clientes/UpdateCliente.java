package crud.Clientes;

import dao.DAO;
import model.Cliente;

import java.util.Scanner;

public class UpdateCliente {

    public static void executar(Scanner scanner) {
        int opcao;

        do {
            System.out.println("\n========== MENU DE ATUALIZAÇÃO DE CLIENTES ==========");
            System.out.println("1 - Buscar cliente por nome");
            System.out.println("2 - Buscar cliente por CPF");
            System.out.println("3 - Listar todos os clientes");
            System.out.println("4 - Atualizar cliente por ID");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1 : ReadCliente.buscarPorNome(scanner);
                    case 2 : ReadCliente.buscarPorCPF(scanner);
                    case 3 : ReadCliente.listarTodosComPaginacao(scanner);
                    case 4 : atualizarCliente(scanner);
                    case 0 : System.out.println("↩ Retornando ao menu anterior...");
                    default: System.out.println("❌ Opção inválida. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Digite apenas números.");
                opcao = -1;
            }

        } while (opcao != 0);
    }

    private static void atualizarCliente(Scanner scanner) {
        DAO<Cliente> dao = new DAO<>(Cliente.class);
        Cliente cliente = null;

        while (cliente == null) {
            System.out.print("Digite o ID do cliente que deseja atualizar (ou 0 para voltar): ");
            try {
                Long id = Long.parseLong(scanner.nextLine());
                if (id == 0) {
                    System.out.println("↩ Voltando ao menu anterior...");
                    return;
                }
                cliente = dao.obterPorID(id);
                if (cliente == null) {
                    System.out.println("❌ Cliente com ID " + id + " não encontrado. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ ID inválido. Digite um número válido.");
            }
        }

        System.out.println("\nCliente encontrado:");
        System.out.println(cliente);

        System.out.println("\nQuais campos deseja atualizar?");
        System.out.println("Digite os números separados por vírgula, ou '0' para atualizar todos:");
        System.out.println("1 - Nome");
        System.out.println("2 - CPF");
        System.out.println("3 - Telefone");
        System.out.println("4 - Endereço");
        System.out.print("Opções: ");
        String opcoes = scanner.nextLine();

        boolean atualizarTodos = opcoes.trim().equals("0");

        if (atualizarTodos || opcoes.contains("1")) {
            System.out.print("Novo nome: ");
            cliente.setNome(scanner.nextLine());
        }

        if (atualizarTodos || opcoes.contains("2")) {
            System.out.print("Novo CPF: ");
            cliente.setCpf(scanner.nextLine());
        }

        if (atualizarTodos || opcoes.contains("3")) {
            System.out.print("Novo telefone: ");
            cliente.setTelefone(scanner.nextLine());
        }

        if (atualizarTodos || opcoes.contains("4")) {
            System.out.print("Novo endereço: ");
            cliente.setEndereco(scanner.nextLine());
        }

        dao.atualizarTransacional(cliente);
        System.out.println("\n✅ Cliente atualizado com sucesso!");
        System.out.println("Novo estado do cliente:");
        System.out.println(cliente);

        dao.fecharEm();
    }
}
