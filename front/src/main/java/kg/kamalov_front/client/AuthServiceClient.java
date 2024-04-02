package kg.kamalov_front.client;

import kg.kamalov_front.entity.SignInRequest;
import kg.kamalov_front.entity.SignUpRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "authClient", url = "http://localhost:8081")
public interface AuthServiceClient{

    @GetMapping("/hello")
    String test();

    @PostMapping("/auth/sign-up")
    ResponseEntity<String> register(SignUpRequest request);

    @PostMapping("/auth/sign-in")
    ResponseEntity<String> login(SignInRequest request);
}