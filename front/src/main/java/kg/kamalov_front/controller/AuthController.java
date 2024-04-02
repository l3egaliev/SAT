package kg.kamalov_front.controller;

import feign.FeignException;
import jakarta.validation.Valid;
import kg.kamalov_front.client.AuthServiceClient;
import kg.kamalov_front.entity.SignInRequest;
import kg.kamalov_front.entity.SignUpRequest;
import kg.kamalov_front.handler.GlobalExceptionHandler;
import kg.kamalov_front.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthServiceClient authServiceClient;

    @GetMapping
    public String login(@ModelAttribute("login_user") SignInRequest request){
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("login_user") @Valid SignInRequest request,
                        BindingResult br){
        try {
            ResponseEntity<String> response = authServiceClient.login(request);
            String message = response.getBody();
            log.info("Message {}", message);
            if (br.hasErrors())
                return "auth/login";
        } catch (FeignException.FeignClientException ex) {
            String errorMessage = ex.contentUTF8();
            log.info("Message {}", errorMessage);
            br.rejectValue("", null, errorMessage);
            return "auth/login";
        }
        return "public/index";
    }

    @GetMapping("/registration")
    public String register(@ModelAttribute("register_user") SignUpRequest request){
        return "auth/registration";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("register_user") @Valid SignUpRequest request,
                           BindingResult bd){
        try {
            ResponseEntity<String> response = authServiceClient.register(request);
            String message = response.getBody();
            log.info("Message {}", message);
            if (bd.hasErrors())
                return "auth/registration";
        } catch (FeignException.BadRequest ex) {
            String errorMessage = ex.contentUTF8();
            log.info("Message {}", errorMessage);
            bd.rejectValue("email", null, errorMessage); // Добавляем сообщение об ошибке к полю "email"
            return "auth/registration";
        }
        return "redirect:/auth/login";
    }
}
