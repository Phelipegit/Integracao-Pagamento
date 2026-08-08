package PhelipeProject.Integracao_Pagamento.service;

import PhelipeProject.Integracao_Pagamento.dto.ApiResponse.*;
import PhelipeProject.Integracao_Pagamento.service.JWT.JwtService;
import PhelipeProject.Integracao_Pagamento.service.JWT.UserDetailsImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {


    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserLoginService(AuthenticationManager authenticationManager,JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public ResponseEntity<ApiResponse<String>> userLogin(UserLoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getData(),
                    request.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            String token = jwtService.generateToken(userDetails.getUsername(),userDetails.getAuthorities().stream().map(e -> e.getAuthority()).findFirst().get());

            ResponseCookie cookie = ResponseCookie
                            .from("authToken",token)
                            .httpOnly(true)
                            .secure(true)
                            .sameSite("Lax")
                            .domain(".phelipedev.com.br")
                            .path("/")
                            .build();

            System.out.println(userDetails.getAuthorities().stream().map(e -> e.getAuthority()).findFirst().get());
            return ResponseEntity.status(201).header(HttpHeaders.SET_COOKIE,cookie.toString()).body(new ApiResponse<>(true, TypesSucess.LOGIN_OK.name(), null));
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(401).body(new ApiResponse<>(false,null,new ErrorCode(HttpStatus.UNAUTHORIZED,TypesErrors.INVALIDS_CREDENTIALS.name())));
        }
    }
}
