package LoginPage.Entities;


import jakarta.persistence.*;


@Entity
@Table(name="tab_produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer Id;
    private String produto;
    private Integer quantidade;
    private Double PrecoUnitario;
    private Double PrecoTotal;

    private String categoria;



    @Lob
    private byte[] Foto;

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPrecoUnitario() {
        return PrecoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        PrecoUnitario = precoUnitario;
    }

    public Double getPrecoTotal() {
        return PrecoTotal;
    }

    public void setPrecoTotal(Double precoTotal) {
        PrecoTotal = precoTotal;
    }

    public byte[] getFoto() {
        return Foto;
    }

    public void setFoto(byte[] foto) {
        this.Foto = foto;
    }
}
