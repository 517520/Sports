package com.example.user.sports.main.presenter;

import com.example.user.sports.main.activity.SignUpView;
import com.example.user.sports.main.model.User;
import com.example.user.sports.main.model.UserModel;

import java.io.IOException;

/**
 * Author : yufeng.cao
 * Date : 17-9-4
 * Description : a detailed class implements SignUpPresenter interface.
 */

public class SignUpPresenterCompl implements SignUpPresenter {

    private SignUpView signUpView;
    private User user;

    public SignUpPresenterCompl(SignUpView signUpView) {
        this.signUpView = signUpView;
        user = new UserModel();
    }


    @Override
    public void regist(String phone, String password) throws IOException {
        signUpView.showDialog();
        if (user != null) {
            user.regist(phone, password, new User.UserSignUpListenner() {
                @Override
                public void complete(String result) {
                    signUpView.mResult(result);
                }
            });
        }
    }

    @Override
    public void login(String phone, String password) throws IOException {
        signUpView.showDialog();
        if (user != null) {
            user.login(phone, password, new User.UserSignUpListenner() {
                @Override
                public void complete(String result) {
                    signUpView.mResult(result);
                }
            });
        }
    }
}
