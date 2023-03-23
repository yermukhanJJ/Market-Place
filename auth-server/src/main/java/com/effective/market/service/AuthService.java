package com.effective.market.service;

import com.effective.market.dao.ProfileRepository;
import com.effective.market.dao.RoleRepository;
import com.effective.market.dto.JwtResponseDto;
import com.effective.market.dto.UserCreateDto;
import com.effective.market.dto.UserLoginDto;
import com.effective.market.entity.Profile;
import com.effective.market.entity.Role;
import com.effective.market.exception.BadRequestException;
import com.effective.market.security.UserDetailsImpl;
import com.effective.market.security.jwt.jwtUtils;
import com.effective.market.util.UserCreateValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Сервис для оутентификаций и авторизаций
 * @author YermukhanJJ
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final ProfileRepository profileRepository;
    private final UserCreateValidator userCreateValidator;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final jwtUtils jwtUtil;

    /**
     * Регистрация пользователья
     * @param userCreateDto
     * @param result
     */
    public void registerUser(UserCreateDto userCreateDto, BindingResult result) {
        userCreateValidator.validate(userCreateDto, result);

        if (result.hasErrors()) {
            log.info(result.toString());
        }

        Profile user = new Profile();
        user.setUsername(userCreateDto.getUsername());
        user.setEmail(userCreateDto.getEmail());
        user.setPassword(encoder.encode(userCreateDto.getPassword()));
        user.setBalance(0.0D);
        Set<String> strRoles = userCreateDto.getRole();
        Set<Role> roles = new HashSet<>();

        if (!roleRepository.existsByTitle("ROLE_USER")) {
            Role role = new Role();
            role.setTitle("ROLE_USER");
            roleRepository.save(role);
            log.info("Save new role: " + role);
        }

        if (strRoles == null) {
            Role userRole = roleRepository.findByTitle("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("ROLE_USER is not found"));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByTitle("ROLE_ADMIN")
                                .orElseThrow(() -> new RuntimeException("ROLE_ADMIN is not found"));
                        roles.add(adminRole);

                        break;
                    default:
                        Role uRole = roleRepository.findByTitle("ROLE_USER")
                                .orElseThrow(() -> new RuntimeException("ROLE_USER is not found"));
                        roles.add(uRole);
                }
            });
        }

        user.setRoles(roles);
        profileRepository.save(user);
        log.info("Created new profile: " + user);

    }

    /**
     * Раздача токена
     * @param userLoginDto
     * @return
     */
    public JwtResponseDto getToken(UserLoginDto userLoginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(), userLoginDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new JwtResponseDto(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getBalance(),
                roles
        );
    }
}
