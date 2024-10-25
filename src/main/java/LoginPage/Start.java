package LoginPage;

import LoginPage.Entities.Produto;
import LoginPage.Repository.ProdutoRepository;
import LoginPage.Service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Start implements CommandLineRunner {


    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private ProdutoService service;

    @Override
    public void run(String... args) throws Exception {

       /* Produto produto = new Produto();
        produto.setProduto("Waffle with Berrie");
        produto.setCategoria("Waffle");
        produto.setFoto();
        produto.setPrecoUnitario(6.50);
        produto.setPrecoTotal(null);
        produto.setQuantidade(null);

        service.salvarProduto(produto);
*/

    }
}
