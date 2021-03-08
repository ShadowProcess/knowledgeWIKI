package com.github.hellxz.oauth2.service;

import com.github.hellxz.oauth2.dao.ClientUserRepositories;
import com.github.hellxz.oauth2.domain.ClientUser;
import com.github.hellxz.oauth2.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientUserService {

    @Autowired
    private ClientUserRepositories repositories;

    public void updateClientUser(ClientUser clientUser){
        Optional<ClientUser> userById = repositories.findById(clientUser.getId());
        if(userById.isPresent()){
            repositories.save(clientUser);
        }
    }
}
