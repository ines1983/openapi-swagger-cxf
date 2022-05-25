package exception;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author Ines Heni
 *
 */
public class RecordNotFoundError {

    @Schema(example = "404")
    private String code;

    @Schema(example = "Record not found")
    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
