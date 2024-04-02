package kg.kamalov_front.handler;

import feign.FeignException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FeignException.BadRequest.class)
    public String hand(FeignException.BadRequest ex){
        return ex.contentUTF8();
    }

    @ExceptionHandler(FeignException.FeignClientException.class)
    public String hand(FeignException.FeignClientException e){
        return e.contentUTF8();
    }
}
