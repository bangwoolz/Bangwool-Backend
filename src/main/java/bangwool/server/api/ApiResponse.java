package bangwool.server.api;

import lombok.Data;

@Data
public class ApiResponse {
    private int status;
    private String message;
    private String description;
    
    public static ApiResponse success(String description) {
        ApiResponse response = new ApiResponse();
        response.setStatus(ApiStatus.SUCCESS.getCode());
        response.setMessage(ApiStatus.SUCCESS.getMessage());
        response.setDescription(description);
        return response;
    }
    
    public static ApiResponse error(ApiStatus status, String message) {
        ApiResponse response = new ApiResponse();
        response.setStatus(status.getCode());
        response.setMessage(status.getMessage());
        response.setDescription(message);
        return response;
    }
}
