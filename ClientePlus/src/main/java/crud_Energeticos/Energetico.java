package crud_Energeticos;

public class Energetico {
    private int id;
    private String nome;
    private String sabor;
    private double precoVenda;
    private int quantidade;

    public Energetico(int id, String nome, String sabor, double precoVenda, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.sabor = sabor;
        this.precoVenda = precoVenda;
        this.quantidade = quantidade;
    }

  

	public int getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getSabor() { return sabor; }
    public void setSabor(String sabor) { this.sabor = sabor; }
    public double getPrecoVenda() { return precoVenda; }
    public void setPrecoVenda(double precoVenda) { this.precoVenda = precoVenda; }
    public int getQuantidade() { return quantidade; }
    public void adicionarEstoque(int unidades) { this.quantidade += unidades; }
    public void removerEstoque(int unidades) { this.quantidade -= unidades; }

    @Override
    public String toString() {
        return "ID: " + id + ", Nome: " + nome + " (" + sabor + "), Preço: R$" + precoVenda + ", Estoque: " + quantidade;
    }
}
