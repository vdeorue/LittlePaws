package ufro.dci.litlepaws.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ufro.dci.litlepaws.model.Usuario;
import ufro.dci.litlepaws.model.data.UsuarioDao;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Usuario us = usuarioDao.findByRut(s);
        List<GrantedAuthority> autoridad = new ArrayList<GrantedAuthority>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(us.getAuthority());
        autoridad.add(grantedAuthority);

        UserDetails userDetails = new User(us.getRut(),us.getContraseña(),autoridad);
        return userDetails;
    }
}