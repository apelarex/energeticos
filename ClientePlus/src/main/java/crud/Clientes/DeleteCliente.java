package crud.Clientes;

import dao.DAO;
import model.Cliente;

import java.util.Scanner;

public class DeleteCliente {

    public static void executar(Scanner scanner) {
        DAO<Cliente> dao = new DAO<>(Cliente.class);

        System.out.print("Digite o ID do cliente que deseja excluir (ou 0 para cancelar): ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            if (id == 0) {
                System.out.println("↩ Exclusão cancelada.");
                return;
            }
            Cliente cliente = dao.obterPorID(id);
            if (cliente == null) {
                System.out.println("❌ Cliente com ID " + id + " não encontrado.");
            } else {
                dao.removerPorIdTransacional(id);
                System.out.println("✅ Cliente excluído com sucesso!");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ ID inválido.");
        }

        dao.fecharEm();
    }
}
