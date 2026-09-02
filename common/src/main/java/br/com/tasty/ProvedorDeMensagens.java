package br.com.tasty;

import org.springframework.context.support.ResourceBundleMessageSource;

public class ProvedorDeMensagens {

    private static final ProvedorDeMensagens INSTANCE = new ProvedorDeMensagens();

    private final ResourceBundleMessageSource messageSource;

    private ProvedorDeMensagens() {

        messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages_pt_br");
        messageSource.setDefaultEncoding("UTF-8");
    }

    public static ProvedorDeMensagens getInstance() {
        return INSTANCE;
    }

    public String getMensagem(String chave) {
        return messageSource.getMessage(chave, null, chave, null);
    }

}