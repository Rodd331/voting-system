package com.voting.system.src.impl.facade;

import com.voting.system.src.impl.entity.UserEntity;
import com.voting.system.src.impl.mapper.UserImplMapper;
import com.voting.system.src.impl.model.UserModel;
import com.voting.system.src.impl.model.VoteModel;
import com.voting.system.src.impl.service.UserService;
import com.voting.system.src.impl.service.ValidsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.voting.system.src.impl.mapper.UserImplMapper.mapUserEntityToUserModel;
import static com.voting.system.src.impl.mapper.UserImplMapper.mapUserModelToUserEntity;

@Component
@AllArgsConstructor
public class UserImplFacade {

    private UserService userService;
    private ValidsService validsService;

    public UserModel createUser(UserModel user) {
        return mapUserEntityToUserModel(userService.createUser(mapUserModelToUserEntity(user)));
    }

    public void deleteUser(String cpf) {
        userService.deleteUser(cpf);
    }

    public List<UserModel> allUsers() {
        userService.validationEmptyList();
        List<UserEntity> usersList = userService.listAllUsers();
        return usersList.stream().map(UserImplMapper::mapUserEntityToUserModel).collect(Collectors.toList());
    }

    public UserModel findByCPF(String cpf) {
        return mapUserEntityToUserModel(userService.findByCPF(cpf));
    }

    public void voteUser(VoteModel vote) {
        validsService.validatorPassword(vote);
        validsService.validatorIdSchedule(vote);
        validsService.validatorTimeSchedule(vote);
        validsService.validatorCPF(vote.getCpf());
        validsService.insertVote(vote);


    }
}
