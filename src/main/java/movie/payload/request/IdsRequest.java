package movie.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class IdsRequest {

    private List<String> ids;

    @Override
    public String toString() {
        return "IdsRequest{" +
                "ids=" + ids +
                '}';
    }
}
