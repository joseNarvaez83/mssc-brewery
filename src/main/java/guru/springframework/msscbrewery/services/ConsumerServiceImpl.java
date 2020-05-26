package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.ConsumerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class ConsumerServiceImpl implements ConsumerService {
    @Override
    public ConsumerDto getConsumerById(UUID consumerId) {
        return ConsumerDto.builder().id(UUID.randomUUID())
                .name("Juan Perez")
                .build();
    }

    @Override
    public ConsumerDto saveNewConsumer(ConsumerDto consumerDto) {
        return ConsumerDto.builder()
                .id(UUID.randomUUID())
                .build();
    }

    @Override
    public void updateConsumer(UUID consumerId, ConsumerDto consumerDto) {
        //todo update real consumer in DB
    }

    @Override
    public void deleteById(UUID consumerId) {
        log.debug("Deleting a costumer...");
    }
}
