package br.com.bluesoft.desafio.api.pedido;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidacaoProduto {

    @Value("${bluesoft.url.produtoValidation}")
    private static String URLvalidacao;

    public static String getUrlFalidacao(){
        return URLvalidacao;
    }
}
