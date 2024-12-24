package movie.utility;

import movie.security.payload.response.ApiResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;

public class ResponseBuilder {

    private static final Logger logger = LogManager.getLogger(ResponseBuilder.class);

    public static ResponseEntity<ApiResponse> buildResponse(int statusCode, String clientMessage, String developerMessage, Exception ex) {
        String messageId = StringUtility.generateUniqueId();
        String timestamp = DateUtility.getTimestamp();
        logExceptionMessage(ex, messageId, timestamp);
        return ResponseEntity.status(statusCode).body(
                ApiResponse.builder()
                        .statusCode(statusCode)
                        .clientMessage(clientMessage)
                        .developerMessage(developerMessage)
                        .messageId(messageId)
                        .timestamp(timestamp)
                        .build()
        );
    }

    public static ResponseEntity<Object> buildSuccessResponse(int statusCode, String clientMessage, Object body) {
        String messageId = StringUtility.generateUniqueId();
        String timestamp = DateUtility.getTimestamp();
        return ResponseEntity.status(statusCode).body(
                ApiResponse.builder()
                        .statusCode(statusCode)
                        .clientMessage(clientMessage)
                        .messageId(messageId)
                        .timestamp(timestamp)
                        .body(body)
                        .build()
        );
    }

    private static void logExceptionMessage(Exception ex, String messageId, String timestamp) {
        logger.info("############################################################");
        logger.info("{} [ID: {}] - Exception message: {}", timestamp, messageId, ex.getMessage());
        logger.info("############################################################");
        ex.printStackTrace();
    }

}