package LoginPage.Entities;


import jakarta.persistence.*;

@Entity
@Table(name="tab_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "order_id")
    private Integer id ;



    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String Produto;
    private Integer Quantidade;
    private Double PrecoUnitario;
    private Double PrecoTotal;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProduto() {
        return Produto;
    }

    public void setProduto(String produto) {
        Produto = produto;
    }

    public Integer getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        Quantidade = quantidade;
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
}
