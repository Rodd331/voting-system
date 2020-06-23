package com.voting.system.src.stubs;

import com.voting.system.src.impl.model.UserModel;

public class UserModelStub {

    public static UserModel generationUserModel() {
        return UserModel.builder()
                .idUser("someid")
                .cpf("01905793073")
                .password("teste@live.com")
                .build();
    }
}