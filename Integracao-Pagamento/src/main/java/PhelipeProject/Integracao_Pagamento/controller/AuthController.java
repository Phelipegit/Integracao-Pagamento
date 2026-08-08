package PhelipeProject.Integracao_Pagamento.controller;

import PhelipeProject.Integracao_Pagamento.dto.ApiResponse.ApiResponse;
import PhelipeProject.Integracao_Pagamento.dto.ApiResponse.UserLoginRequest;
import PhelipeProject.Integracao_Pagamento.dto.UserRegisterRequest;
import PhelipeProject.Integracao_Pagamento.service.ActiveAccountService;
import PhelipeProject.Integracao_Pagamento.service.UserLoginService;
import PhelipeProject.Integracao_Pagamento.service.UserRegisterService;
import com.resend.core.exception.ResendException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/auth/")
public class AuthController {

    private final UserLoginService userLoginService;
    private final UserRegisterService userRegisterService;
    private final ActiveAccountService activeAccountService;

    public AuthController(UserLoginService userLoginService,UserRegisterService userRegisterService,ActiveAccountService activeAccountService) {
        this.userLoginService = userLoginService;
        this.userRegisterService = userRegisterService;
        this.activeAccountService = activeAccountService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> userLogin(@Valid @RequestBody UserLoginRequest request) {
        return userLoginService.userLogin(request);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> userRegister(@Valid @RequestBody UserRegisterRequest request) throws ResendException {
        return userRegisterService.userRegister(request);
    }

    @GetMapping("/verify-account/{uuid}")
    public ResponseEntity<ApiResponse<String>> activeAccount(   @PathVariable @NotBlank String uuid, @RequestParam @NotBlank String email) {
        return activeAccountService.activeAccount(email,uuid);
    }
}
