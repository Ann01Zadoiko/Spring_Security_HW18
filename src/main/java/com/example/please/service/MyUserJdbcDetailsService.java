package com.example.please.service;


import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class MyUserJdbcDetailsService implements UserDetailsService{
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetails> userData = jdbcTemplate.query(
                "SELECT username, password, role, enabled FROM users WHERE username = :username",
                Map.of("username", username),
                new UserDataRowMapper()
        );

        return userData.isEmpty() ? null : userData.get(0);
    }

    private static class UserDataRowMapper implements RowMapper<UserDetails> {
        @Override
        public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
            String role = rs.getString("role");
            String username = rs.getString("username");
            String password = rs.getString("password");
            boolean enabled = rs.getBoolean("enabled");

            return new UserDetails() {
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    return Collections.singleton(() -> "ROLE_" + role);
                }

                public PasswordEncoder passwordEncoder(){
                    return new BCryptPasswordEncoder();
                }

                @Override
                public String getPassword() {
                    return passwordEncoder().encode(password);
                }

                @Override
                public String getUsername() {
                    return username;
                }

                @Override
                public boolean isAccountNonExpired() {
                    return true;
                }

                @Override
                public boolean isAccountNonLocked() {
                    return true;
                }

                @Override
                public boolean isCredentialsNonExpired() {
                    return true;
                }

                @Override
                public boolean isEnabled() {
                    return enabled;
                }
            };
        }
    }
}
