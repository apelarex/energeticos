package crud_Energeticos;

import java.util.ArrayList;
import java.util.Iterator; // Importa a classe Iterator
import java.util.List;
import java.util.Scanner;

public class DeleteProduto {

    private static List<Energetico> listaDeEnergeticos = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

       
        
        
        System.out.println("--- Lista de Energéticos Atuais ---");
        listarProdutos();
        System.out.println("\n--- Remover um Produto (Energético) ---\n");
        
        System.out.print("Digite o ID do energético que você deseja remover: ");
        int idParaRemover = scanner.nextInt();
        
        removerProduto(idParaRemover);

        System.out.println("\n--- Lista de Energéticos Após a Remoção ---");
        listarProdutos();

        scanner.close();
    }

  
    	
		

	private static void removerProduto(int id) {
        // Usa um Iterator para remover o item de forma segura
        Iterator<Energetico> iterator = listaDeEnergeticos.iterator();
        boolean removido = false;
        
        while (iterator.hasNext()) {
            Energetico energetico = iterator.next();
            if (energetico.getId() == id) {
                iterator.remove(); // Usa o método remove() do Iterator
                removido = true;
                System.out.println("Produto com ID " + id + " removido com sucesso!");
                break; // Sai do loop após a remoção
            }
        }
        
        if (!removido) {
            System.out.println("Erro: Produto com ID " + id + " não encontrado.");
        }
    }

    private static void listarProdutos() {
        if (listaDeEnergeticos.isEmpty()) {
            System.out.println("Nenhum energético cadastrado.");
        } else {
            for (Energetico energetico : listaDeEnergeticos) {
                System.out.println(energetico);
            }
        }
    }
    
    
}