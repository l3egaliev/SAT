package kg.kamalov_back.controller;

import jakarta.validation.Valid;
import kg.kamalov_back.dto.JwtAuthResponse;
import kg.kamalov_back.dto.SignInRequest;
import kg.kamalov_back.dto.SignUpRequest;
import kg.kamalov_back.model.User;
import kg.kamalov_back.security.AuthenticationService;
import kg.kamalov_back.utils.EmailValidator;
import kg.kamalov_back.utils.ErrorSender;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authService;
    private final EmailValidator emailValidator;

    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUp(@RequestBody @Valid SignUpRequest request,
                                 BindingResult bd){
        emailValidator.validate(new User(request.getEmail()), bd);
        if (bd.hasErrors()){
            List<String> errors = ErrorSender.returnErrorsToClient(bd);
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok(authService.signUp(request));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Object> signIn(@RequestBody SignInRequest request){
//        if (bd.hasErrors()){
//            List<String> errors = ErrorSender.returnErrorsToClient(bd);
//            return ResponseEntity.badRequest().body(errors);
//        }
        JwtAuthResponse response = authService.signIn(request);
        if (response.getToken().isEmpty()){
            return ResponseEntity.badRequest().body("Неправильный email или пароль");
        }
        return ResponseEntity.ok(authService.signIn(request));
    }
}
