package tr.com.nihatalim.yt.presenter.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class SerializerService {
    private final ObjectMapper objectMapper;

    public SerializerService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    String serialize(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            return "[SerializerService] Serialization Failed!";
        }
    }
}
