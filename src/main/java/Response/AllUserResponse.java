package Response;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class AllUserResponse {
    private String first_name;
    private String last_name;
    private String career;
    private String phone;


   /* private List abc;

    @JsonAnySetter
    public void setAbc(Map<String, String> ab) {
        abc.add(ab);
    }*/
}
