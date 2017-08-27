package com.hexor.reandroid.security;

import com.hexor.reandroid.persistence.entity.CustomerEntity;
import com.hexor.reandroid.persistence.service.CustomerManager;
import com.hexor.reandroid.persistence.service.exception.ServiceException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service("userDetailsService")
public class CustomerDetailsServiceImpl implements UserDetailsService {

    private static Log LOG = LogFactory.getLog(CustomerDetailsServiceImpl.class);

    @Autowired
    private CustomerManager customerManager;

    @Transactional
    public UserDetails loadUserByUsername(String customer)
            throws UsernameNotFoundException, DataAccessException {
        UserDetails userDetails = null;
        try {
            CustomerEntity entity = customerManager.findCustomerById(customer);
            if (entity != null) {
                userDetails = buildUserFromCustomerEntity(entity);
            } else {
                throw new UsernameNotFoundException(customer);
            }
        } catch (ServiceException se) {
            throw new UsernameNotFoundException(se.getMessage());
        }

        return userDetails;
    }

    private User buildUserFromCustomerEntity(CustomerEntity entity) {
        String username = entity.getId();
        String password = entity.getPassword();
        boolean enabled = entity.isEnabled();
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        User user = new User(username, password, enabled,
                true, true, true, authorities);
        return user;
    }
}
