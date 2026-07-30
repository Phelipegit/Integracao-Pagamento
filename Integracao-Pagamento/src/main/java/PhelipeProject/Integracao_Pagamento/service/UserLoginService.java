package PhelipeProject.Integracao_Pagamento.service;

import PhelipeProject.Integracao_Pagamento.dto.ApiResponse.ApiResponse;
import PhelipeProject.Integracao_Pagamento.dto.ApiResponse.ErrorCode;
import PhelipeProject.Integracao_Pagamento.dto.ApiResponse.TypesErrors;
import PhelipeProject.Integracao_Pagamento.dto.ApiResponse.UserLoginRequest;
import PhelipeProject.Integracao_Pagamento.service.JWT.JwtService;
import PhelipeProject.Integracao_Pagamento.service.JWT.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {

    private AuthenticationManager authenticationManager;
    private JwtService jwtService;

    public UserLoginService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public ResponseEntity<ApiResponse<String>> userLogin(UserLoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getData(),
                    request.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            String token = jwtService.generateToken(userDetails.getUsername(),null);

            return ResponseEntity.status(201).body(new ApiResponse<>(true, token,null));
        }catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(new ApiResponse<>(false,null,new ErrorCode(HttpStatus.UNAUTHORIZED,TypesErrors.INVALIDS_CREDENTIALS.name())));
        }
    }
}
