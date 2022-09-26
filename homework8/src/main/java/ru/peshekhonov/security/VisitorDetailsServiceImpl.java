package ru.peshekhonov.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.peshekhonov.service.VisitorService;

@Service
@RequiredArgsConstructor
public class VisitorDetailsServiceImpl implements UserDetailsService {

    private final VisitorService visitorService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return visitorService.findUserByUsername(username);
    }
}
