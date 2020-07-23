package br.com.startideia.vuttr.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicatedResourceException extends RuntimeException {

    public DuplicatedResourceException(String exception) {
        super(exception);
    }

}
