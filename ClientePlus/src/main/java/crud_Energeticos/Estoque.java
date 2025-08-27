package crud_Energeticos;

import java.util.HashMap;
import java.util.Map;

public class Estoque {
    private Map<String, Energetico> listaDeEnergeticos;

    public Estoque() {
        this.listaDeEnergeticos = new HashMap<>();
    }

    // Adiciona um novo tipo de energético ao estoque
    public void adicionarNovoEnergetico(Energetico energetico) {
        if (!listaDeEnergeticos.containsKey(energetico.getNome().toLowerCase())) {
            listaDeEnergeticos.put(energetico.getNome().toLowerCase(), energetico);
            System.out.println("Energetico " + energetico.getNome() + " adicionado ao sistema.");
        } else {
            System.out.println("Energetico " + energetico.getNome() + " já existe no sistema.");
        }
    }

    // Realiza a entrada de caixas no estoque
    public void entradaDeCaixas(String nomeEnergetico, int caixas, int unidadesPorCaixa) {
        Energetico energetico = listaDeEnergeticos.get(nomeEnergetico.toLowerCase());
        if (energetico != null) {
            int totalUnidades = caixas * unidadesPorCaixa;
            energetico.adicionarEstoque(totalUnidades);
        } else {
            System.out.println("Erro: Energetico " + nomeEnergetico + " não encontrado no sistema.");
        }
    }

    // Realiza a saída (venda) de unidades
    public void saidaDeUnidades(String nomeEnergetico, int unidades) {
        Energetico energetico = listaDeEnergeticos.get(nomeEnergetico.toLowerCase());
        if (energetico != null) {
            energetico.removerEstoque(unidades);
        } else {
            System.out.println("Erro: Energetico " + nomeEnergetico + " não encontrado no sistema.");
        }
    }

    // Exibe o estoque completo
    public void exibirEstoque() {
        if (listaDeEnergeticos.isEmpty()) {
            System.out.println("O estoque está vazio.");
        } else {
            System.out.println("\n--- Estoque de Energéticos ---");
            for (Energetico energetico : listaDeEnergeticos.values()) {
                System.out.println(energetico);
            }
            System.out.println("------------------------------\n");
        }
    }
}