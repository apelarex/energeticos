package crud.Clientes;

import model.Cliente;
import model.Energetico;
import dao.DAO;

import java.util.Scanner;

public class CreateCliente {

    private final Scanner scanner;

    public CreateCliente(Scanner scanner) {
        this.scanner = scanner;
    }

    public void executar() {
        System.out.println("\n📝 Cadastro de Novo Cliente");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("RG: ");
        String rg = scanner.nextLine();

        System.out.print("Data de Nascimento (dd/mm/aaaa): ");
        String dataNascimento = scanner.nextLine();

        System.out.print("Gênero (Masculino/Feminino/Outro): ");
        String genero = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();

        System.out.print("Estado: ");
        String estado = scanner.nextLine();

        System.out.print("CEP: ");
        String cep = scanner.nextLine();

        System.out.print("Observações: ");
        String observacoes = scanner.nextLine();

        Cliente cliente = new Cliente(
            nome, cpf, rg, dataNascimento, genero, email,
            telefone, endereco, cidade, estado, cep, observacoes
        );

        String resposta;
        do {
            System.out.print("Deseja cadastrar um produto para esse cliente? (s/n): ");
            resposta = scanner.nextLine().trim().toLowerCase();

            if (resposta.equals("s")) {
                System.out.print("Nome do produto: ");
                String nomeEnergetico  = scanner.nextLine();

                System.out.print("Preço do produto: ");
                double preco = Double.parseDouble(scanner.nextLine());

                // cria o energético vinculado ao cliente
                Energetico produto = new Energetico(nomeEnergetico, preco);
                cliente.adicionarProduto(produto); // usa o método correto
            }
        } while (resposta.equals("s"));

        new DAO<>(Cliente.class).incluirTransacional(cliente);
        System.out.println("✅ Cliente e produtos cadastrados com sucesso!");
    }
}
