package org.example.validate;

import org.example.model.TokenAndPortModel;
import org.example.util.ButtonUtils;

import java.util.Objects;

public class Validate {

    public static boolean validateTokenAndPort(TokenAndPortModel auth, boolean isNew){

        if (Objects.nonNull(auth)) {
            String token = auth.getToken();
            String port = auth.getPort();
            if(Objects.isNull(token)){
                ButtonUtils.error("token cannot be empty!");
                return false;
            }
            if(port.isEmpty()){
                ButtonUtils.error("port cannot be empty!");
                return false;
            }
        }
        return true;
    }

    public static TokenAndPortModel validateUpdateTokenAndPort(TokenAndPortModel authFile,TokenAndPortModel newAuth){

        if (Objects.nonNull(newAuth)) {
            String token = newAuth.getToken();
            String port = newAuth.getPort();
            if(!token.isEmpty()){
                authFile.setToken(token);
            }
            if(!port.isEmpty()){
                authFile.setPort(port);
            }
        }
        return authFile;
    }
}
