package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.ConsumerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ConsumerServiceImpl implements ConsumerService {
    @Override
    public ConsumerDto getConsumerById(UUID consumerId) {
        return ConsumerDto.builder().id(UUID.randomUUID())
                .name("Juan Perez")
                .build();
    }
}
