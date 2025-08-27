package crud.Clientes;

import dao.DAO;
import model.Cliente;

import java.util.List;
import java.util.Scanner;

public class ReadCliente {

    public static void executar(Scanner scanner) {
        int opcao;

        do {
            System.out.println("\n========== CONSULTA DE CLIENTES ==========");
            System.out.println("1 - Buscar cliente por nome");
            System.out.println("2 - Buscar cliente por CPF");
            System.out.println("3 - Listar todos os clientes (com paginação)");
            System.out.println("0 - Voltar ao menu de clientes");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1 : buscarPorNome(scanner);
                    case 2 : buscarPorCPF(scanner);
                    case 3 : listarTodosComPaginacao(scanner);
                    case 0 : System.out.println("↩ Retornando ao menu anterior...");
                    default : System.out.println("❌ Opção inválida. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Digite apenas números.");
                opcao = -1;
            }

        } while (opcao != 0);
    }

    public static void buscarPorNome(Scanner scanner) {
        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.nextLine();

        DAO<Cliente> dao = new DAO<>(Cliente.class);
        List<Cliente> clientes = dao.consultar(
            "SELECT c FROM Cliente c WHERE LOWER(c.nome) LIKE LOWER(?1) ORDER BY c.nome",
            "%" + nome + "%");

        if (clientes.isEmpty()) {
            System.out.println("❌ Nenhum cliente encontrado com esse nome.");
        } else {
            System.out.println("\n📋 Clientes encontrados:");
            clientes.forEach(System.out::println);
        }
        dao.fecharEm();
    }

    public static void buscarPorCPF(Scanner scanner) {
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();

        DAO<Cliente> dao = new DAO<>(Cliente.class);
        Cliente cliente = dao.buscarUm("Cliente.buscarPorCpf", "cpf", cpf);

        if (cliente == null) {
            System.out.println("❌ Nenhum cliente encontrado com esse CPF.");
        } else {
            System.out.println("\n📋 Cliente encontrado:");
            System.out.println(cliente);
        }
        dao.fecharEm();
    }

    public static void listarTodosComPaginacao(Scanner scanner) {
        System.out.print("Quantos clientes deseja ver por página (ex: 10, 20, 50)? ");

        int tamanhoPagina = 0;
        while (tamanhoPagina <= 0) {
            try {
                tamanhoPagina = Integer.parseInt(scanner.nextLine());
                if (tamanhoPagina <= 0) {
                    System.out.print("⚠ Valor deve ser maior que zero. Digite novamente: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("❌ Número inválido. Digite novamente: ");
            }
        }

        int pagina = 0;
        int opcaoPag;

        DAO<Cliente> dao = new DAO<>(Cliente.class);

        do {
            List<Cliente> clientes = dao.obterTodos(tamanhoPagina, pagina * tamanhoPagina);

            System.out.println("\n                                                              📄 Página " + (pagina + 1));
            System.out.println("                          ----------------------------------------------------------------------------------------------------");

            if (clientes.isEmpty()) {
                System.out.println("Nenhum cliente encontrado nesta página.");
            } else {
                System.out.printf("%-8s %-35s %-17s %-33s %-17s %-39s%n", "ID", "Nome", "CPF", "Email", "Telefone", "Endereço");
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------");

                for (Cliente c : clientes) {
                    System.out.printf("%-8d %-35s %-17s %-33s %-17s %-39s%n",
                            c.getId(), c.getNome(), c.getCpf(), c.getEmail(), c.getTelefone(), c.getEndereco());
                }
            }

            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("1 - Próxima página");
            System.out.println("2 - Página anterior");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            try {
                opcaoPag = Integer.parseInt(scanner.nextLine());

                switch (opcaoPag) {
                    case 1 : pagina++;
                    case 2 : {
                        if (pagina > 0) pagina--;
                        else System.out.println("⚠ Já está na primeira página.");
                    }
                    case 0 : System.out.println("↩ Saindo da listagem...");
                    default : System.out.println("❌ Opção inválida!");
                }

            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida.");
                opcaoPag = -1;
            }

        } while (opcaoPag != 0);

        dao.fecharEm();
    }
}
