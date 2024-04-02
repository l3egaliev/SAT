package kg.kamalov_front.service;

import kg.kamalov_front.entity.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final RestTemplate restTemplate;
    private String URL = "http://localhost:8080";


    public String test(){
        return "";
    }

    public void registration(SignUpRequest request, BindingResult br){
        HttpEntity<SignUpRequest> entity = new HttpEntity<>(request);
        ResponseEntity<String> response = restTemplate.postForEntity(URL + "/auth/sign-up",
                entity, String.class);
        if (response.getStatusCode().is5xxServerError()
        || response.getStatusCode().is4xxClientError() || response.getStatusCode().isError()){
            br.rejectValue("", "", Objects.requireNonNull(response.getBody()));
        }
    }
}