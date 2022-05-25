package exception;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author Ines Heni
 *
 */

public class Ok {

    @Schema(example = "200")
    private String code;

    @Schema(example = "Deleted")
    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
