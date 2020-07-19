package com.github.hellxz.oauth2.dao;

import com.github.hellxz.oauth2.domain.ClientUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientUserRepositories extends JpaRepository<ClientUser, Long> {
    ClientUser findOneByUsername(String username);
}
