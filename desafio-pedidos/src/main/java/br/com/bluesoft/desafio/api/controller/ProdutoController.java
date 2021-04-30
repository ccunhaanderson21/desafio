package br.com.bluesoft.desafio.api.controller;
 
import java.util.List;
import java.util.Optional;

import br.com.bluesoft.desafio.api.pedido.Fornecedor;
import br.com.bluesoft.desafio.api.pedido.ValidacaoProduto;
import com.google.gson.Gson;
import com.sun.org.apache.bcel.internal.generic.ARETURN;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.bluesoft.desafio.dominio.produto.Produto;
import br.com.bluesoft.desafio.repository.ProdutoRepository;
import br.com.bluesoft.desafio.view.ErroView;
import org.springframework.web.client.RestTemplate;

import javax.xml.crypto.Data;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private static final Logger log = LoggerFactory.getLogger(ProdutoController.class);
    private ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<Produto> findAll() {
        return produtoRepository.findAll();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public  ResponseEntity<Object> GetProduto(@PathVariable(value = "id") String gtin)
    {
        try {


        Produto produto = produtoRepository.findOne(gtin);
         return ResponseEntity.ok(produto);
            // 
            //  if(produto != null){
                 
            //     return ResponseEntity.ok(produto);
            //  }else{   
            //     return ResponseEntity.notFound();
            //  } 
            
        } catch (Exception e) {
            
            log.info("Exception "+ e.getMessage());
 
             return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("Produto não localizado");
      }
    }

    @RequestMapping(value = "/rest/{id}", method = RequestMethod.GET)
    public  ResponseEntity<Object> GetProdutorest(@PathVariable(value = "id") String gtin)
    {
        return  ResponseEntity.ok(getProductRestrinct(gtin));
    }


    private static String getProductRestrinct(String gtin)
    {
        final String uri = ValidacaoProduto.getUrlFalidacao() +gtin;

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

log.info( "URL "+result);
       // List<Fornecedor> data = new Gson().fromJson(result, Fornecedor.class);

         return result;

    }

    @PostMapping
    public ResponseEntity<Object>  postProduto(@RequestBody Produto produto){
     
             
            log.info("Produto: "+produto.toString());
             produtoRepository.save(produto);

            return ResponseEntity.ok("Produto incluido com sucesso");
           
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object>   PutProduct(@PathVariable(value = "id") String id, @RequestBody Produto newProduct)
     {
            Produto oldProduct = produtoRepository.findOne(id);
            if(oldProduct != null){ 
                 
                produtoRepository.save(newProduct);
 
                return ResponseEntity.ok(newProduct);
           
            }
            else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Produto não localizado"); 
          //return new ResponseEntity<>(ReturnErrorMessage(HttpResponseConstants.MESSAGE_ERRO,HttpResponseConstants.CODE_ERRO_DADOS_N_LOCALIZADO));
        }
    

}
