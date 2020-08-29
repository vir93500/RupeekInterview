package Response;

import lombok.Data;

import java.util.List;

@Data
public class UserListResponse {
    private List<AllUserResponse> allUserResponseList;
}
