package br.com.bluesoft.desafio.utils;

public class BusinessException extends RuntimeException {

    BusinessException(String mensagem)
    {
        super(mensagem);
    }

    public static void Validar(Boolean validacao, String mensagemDeErro){
        if(validacao)
            throw new BusinessException(mensagemDeErro);
    }
}
