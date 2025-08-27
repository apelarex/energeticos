package main;

import java.util.Scanner;
import crud.Clientes.*;
import crud_Energeticos.CrudEnergetico;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n========== SISTEMA ENERGETICOS =========");
            System.out.println("1 - Gerenciar Clientes");
            System.out.println("2 - Gerenciar Produtos Energéticos");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        CrudCliente.executar(scanner);
                       
                    case 2:
                        CrudEnergetico.executar(scanner);
                       
                    case 0:
                        System.out.println("Saindo do sistema..."); 
                     
                    default:
                        System.out.println("❌ Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Digite apenas números.");
                opcao = -1;
            }

        } while (opcao != 0);

        scanner.close();
    }
}
