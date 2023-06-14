package Model;

import Model.AuthResult;

public interface AuthInterface {
    AuthResult execute(String name, String pw);
}

