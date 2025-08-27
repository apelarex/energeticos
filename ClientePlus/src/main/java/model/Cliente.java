package model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity 
@NamedQueries({
    @NamedQuery(name = "Cliente.buscarPorCpf", query = "SELECT c FROM Cliente c WHERE c.cpf = :cpf")
})
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private String rg;
    private String dataNascimento;
    private String genero;

    @Column(name = "t_email", nullable = false, length = 512)
    private String email;

    private String telefone;
    private String endereco;
    private String cidade;
    private String estado;
    private String cep;
    private String observacoes;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Energetico> produtos = new ArrayList<>();

    public Cliente() {}

    public Cliente(String nome, String cpf, String rg, String dataNascimento, String genero,
                   String email, String telefone, String endereco, String cidade,
                   String estado, String cep, String observacoes) {
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.observacoes = observacoes;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getRg() { return rg; }
    public void setRg(String rg) { this.rg = rg; }

    public String getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    public List<Energetico> getProdutos() { return produtos; }
    public void setProdutos(List<Energetico> produtos) { this.produtos = produtos; }

    // Métodos para manter a associação bidirecional
    public void adicionarProduto(Energetico produto) {
        produto.setCliente(this); // corrige o cliente no produto
        this.produtos.add(produto);
    }

    public void removerProduto(Energetico produto) {
        produto.setCliente(null); // remove vínculo
        this.produtos.remove(produto);
    }

    @Override
    public String toString() {
        return "Cliente [id=" + id +
                ", nome=" + nome +
                ", cpf=" + cpf +
                ", rg=" + rg +
                ", nascimento=" + dataNascimento +
                ", genero=" + genero +
                ", email=" + email +
                ", telefone=" + telefone +
                ", endereco=" + endereco +
                ", cidade=" + cidade +
                ", estado=" + estado +
                ", cep=" + cep +
                ", observacoes=" + observacoes +
                ", produtos=" + produtos.size() + "]";
    }
}
