package com.example.user.sports.main.presenter;

import java.io.IOException;

/**
 * Author : user
 * Date : 17-9-4
 * Description :
 */
public interface SignUpPresenter {

    void regist(String phone, String password) throws IOException;
    void login(String phone, String password) throws IOException;
}
