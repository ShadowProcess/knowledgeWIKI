package com.github.hellxz.oauth2.config;

import com.github.hellxz.oauth2.dao.ClientUserRepositories;
import com.github.hellxz.oauth2.domain.ClientUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ClientUserDetailsService implements UserDetailsService {

    @Autowired
    private ClientUserRepositories clientUserRepositories;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 数据库查用户对象实现，这里不做权限控制
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ClientUser clientUser = clientUserRepositories.findOneByUsername(username);
        if(Objects.isNull(clientUser)){
            throw new UsernameNotFoundException("用户不存在");
        }
        return clientUser;
    }
}
