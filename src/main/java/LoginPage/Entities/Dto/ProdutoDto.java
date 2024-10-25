package LoginPage.Entities.Dto;

import org.springframework.web.multipart.MultipartFile;

public class ProdutoDto {

    private String produto;
    private String categoria;

    private Integer quantidade;
    private Double PrecoUnitario;
    private Double PrecoTotal;

    private MultipartFile foto;


    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
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

    public MultipartFile getFoto() {
        return foto;
    }

    public void setFoto(MultipartFile foto) {
        this.foto = foto;
    }
}
