package crud_Energeticos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class CrudEnergetico {

    private static final List<Energetico> lista = new ArrayList<>();
    private static final AtomicInteger nextId = new AtomicInteger(1);

    static {
        lista.add(new Energetico(nextId.getAndIncrement(),"Monster","Original",8.50,50));
        lista.add(new Energetico(nextId.getAndIncrement(),"Red Bull","Tropical",9.00,30));
        lista.add(new Energetico(nextId.getAndIncrement(),"Rockstar","Híbrido",7.80,45));
    }

    public static void executar(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n--- Menu de Produtos (Energéticos) ---");
            System.out.println("1 - Listar todos os produtos");
            System.out.println("2 - Adicionar novo produto");
            System.out.println("3 - Remover produto");
            System.out.println("4 - Atualizar produto");
            System.out.println("0 - Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch(opcao) {
                    case 1: listar(); break;
                    case 2: adicionar(scanner); break;
                    case 3: remover(scanner); break;
                    case 4: atualizar(scanner); break;
                    case 0: System.out.println("↩ Voltando ao menu principal..."); break;
                    default: System.out.println("❌ Opção inválida.");
                }
            } catch(Exception e) { System.out.println("❌ Entrada inválida."); opcao = -1; }
        } while(opcao != 0);
    }

    private static void listar() {
        if(lista.isEmpty()) System.out.println("Nenhum produto cadastrado.");
        else lista.forEach(System.out::println);
    }

    private static void adicionar(Scanner scanner) {
        System.out.print("Nome: "); String nome = scanner.nextLine();
        System.out.print("Sabor: "); String sabor = scanner.nextLine();
        System.out.print("Preço: "); double preco = Double.parseDouble(scanner.nextLine());
        System.out.print("Quantidade: "); int qtd = Integer.parseInt(scanner.nextLine());
        lista.add(new Energetico(nextId.getAndIncrement(), nome, sabor, preco, qtd));
        System.out.println("✅ Produto adicionado com sucesso!");
    }

    private static void remover(Scanner scanner) {
        listar();
        System.out.print("ID do produto para remover: "); int id = Integer.parseInt(scanner.nextLine());
        lista.removeIf(p -> p.getId() == id);
        System.out.println("✅ Produto removido (se existia).");
    }
    
    private static void atualizar(Scanner scanner) {
        listar();
        System.out.print("ID do produto para atualizar: "); int id = Integer.parseInt(scanner.nextLine());
        for(Energetico p : lista) {
            if(p.getId()==id) {
                System.out.print("Novo Nome ("+p.getNome()+"): "); String n=scanner.nextLine();
                if(!n.isEmpty());
            }
        }    
    }
}