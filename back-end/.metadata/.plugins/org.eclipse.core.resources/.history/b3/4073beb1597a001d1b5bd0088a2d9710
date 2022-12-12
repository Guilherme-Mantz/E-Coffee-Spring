package br.com.ecoffee.util.security;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class BcryptUtil {
	
    public static String getHash(String senha) {

        String salt = BCrypt.gensalt();
        String hashSenha = BCrypt.hashpw(senha, salt);

        return hashSenha;
    }
}
