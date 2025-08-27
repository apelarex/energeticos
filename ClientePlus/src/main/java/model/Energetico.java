package model;

import jakarta.persistence.*;

@Entity
@NamedQueries({
    @NamedQuery(name = "Energetico.buscarPorNome", query = "SELECT e FROM Energetico e WHERE LOWER(e.nome) LIKE LOWER(:nome)")
})
public class Energetico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String sabor;
    private double precoVenda;
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "cliente_id") // cria a FK no banco
    private Cliente cliente;

    public Energetico() {}

    public Energetico(String nome, double precoVenda) {
        this.nome = nome;
        this.precoVenda = precoVenda;
        this.quantidade = 0;
    }

    public Energetico(Long id, String nome, String sabor, double precoVenda, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.sabor = sabor;
        this.precoVenda = precoVenda;
        this.quantidade = quantidade;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSabor() { return sabor; }
    public void setSabor(String sabor) { this.sabor = sabor; }

    public double getPrecoVenda() { return precoVenda; }
    public void setPrecoVenda(double precoVenda) { this.precoVenda = precoVenda; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public void adicionarEstoque(int unidades) { this.quantidade += unidades; }
    public void removerEstoque(int unidades) { this.quantidade -= unidades; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    @Override
    public String toString() {
        return "Energetico [id=" + id + 
               ", nome=" + nome + 
               ", sabor=" + sabor + 
               ", precoVenda=" + precoVenda + 
               ", quantidade=" + quantidade + "]";
    }
}
