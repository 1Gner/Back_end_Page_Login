package LoginPage.Service;

import LoginPage.Entities.Produto;
import LoginPage.Entities.User;
import LoginPage.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;

    public List<Produto> findAll() {
        return repository.findAll();
    }

    public Produto salvarProduto(Produto produto) {
        return repository.save(produto);
    }

    public Produto findProduto(String nome){
        List<Produto> produtos = repository.findAll();
        if(!produtos.isEmpty()){
           Optional<Produto> prod =  produtos.stream().filter(p -> p.getProduto().equals(nome)).findFirst();
           return  prod.orElse(null);
        }

        return null;
    }

}
