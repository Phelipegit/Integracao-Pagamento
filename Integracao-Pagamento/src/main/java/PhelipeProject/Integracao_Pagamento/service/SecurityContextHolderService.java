package PhelipeProject.Integracao_Pagamento.service;

import PhelipeProject.Integracao_Pagamento.entity.UserEntity;
import PhelipeProject.Integracao_Pagamento.repository.UserRepository;
import PhelipeProject.Integracao_Pagamento.service.JWT.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityContextHolderService {

    private final UserRepository userRepository;

    public SecurityContextHolderService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserEntity> getUser() {
        Authentication securityContextHolder = SecurityContextHolder.getContext().getAuthentication();

        if(securityContextHolder == null  || !securityContextHolder.isAuthenticated() ) {
            return Optional.empty();
        }

        UserDetailsImpl userDetails = (UserDetailsImpl) securityContextHolder.getPrincipal();

        if(userDetails == null) {
            return Optional.empty();
        }

        return userRepository.findByEmail(userDetails.getUsername());
    }
}
