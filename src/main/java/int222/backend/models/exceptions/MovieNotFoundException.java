package int222.backend.models.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Movie not found by this id")
public class MovieNotFoundException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = -9123810148983264352L;
}
