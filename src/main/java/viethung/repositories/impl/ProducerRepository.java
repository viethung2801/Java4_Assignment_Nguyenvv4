package viethung.repositories.impl;

import viethung.models.Producer;

import java.util.List;

public interface ProducerRepository {
    Producer insert(Producer producer);
    Producer update(Producer producer, String producerId);
    Producer delete(String producerId);
    List<Producer> getAll();
    Producer getById(String producerId);
    Producer getByCode(String producerCode);
}
