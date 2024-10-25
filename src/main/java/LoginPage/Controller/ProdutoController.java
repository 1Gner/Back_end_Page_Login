package LoginPage.Controller;

import LoginPage.Entities.Dto.ProdutoDto;
import LoginPage.Entities.Produto;
import LoginPage.Service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RestController
@RequestMapping(value ="/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveProduto(@ModelAttribute ProdutoDto produtoDto) throws IOException {

        try {
            Produto produto = new Produto();
            produto.setProduto(produtoDto.getProduto());
            produto.setQuantidade(produtoDto.getQuantidade());
            produto.setPrecoTotal(produtoDto.getPrecoTotal());
            produto.setPrecoUnitario(produtoDto.getPrecoUnitario());
            produto.setFoto(produtoDto.getFoto().getBytes());
            produto.setCategoria(produtoDto.getCategoria());
            produto = service.salvarProduto(produto);

            return ResponseEntity.ok().body(produto);
        }catch(IOException e){
            return ResponseEntity.status(500).body("Error");
        }
    }


    @PostMapping(value = "/find")
    public ResponseEntity<Produto> procuraProduto(@RequestBody String produto){
         Produto pro = service.findProduto(produto);
        return ResponseEntity.ok().body(pro);
    }

    @GetMapping(value = "/findall")
    public ResponseEntity<List<Produto>> TodosProdutos(){
        List<Produto> pro = service.findAll();
        return ResponseEntity.ok().body(pro);
    }

}
