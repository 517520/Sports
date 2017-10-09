package com.example.user.sports.main.model;

import java.io.IOException;

/**
 * Author : user
 * Date : 17-9-4
 * Description :
 */
public interface User {
    void regist(String phone, String password,UserSignUpListenner listenner) throws IOException;
    void login(String phone, String password,UserSignUpListenner listenner) throws  IOException;

    interface UserSignUpListenner {
        void complete(String result);
    }
}
