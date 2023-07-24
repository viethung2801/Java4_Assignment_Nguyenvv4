package viethung.utilities;


import viethung.models.Producer;
import viethung.repositories.ProducerRepositoryImpl;
import viethung.repositories.impl.ProducerRepository;

public class ProducerUtil {
    public static String validateInsert(Producer producer) {
        ProducerRepository producerRepo = new ProducerRepositoryImpl();

        if (producer.getCode().trim().equals("") || producer.getCode() == null) {
            return "Fail! Code is empty";
        }
        if (producer.getCode().length() > 20) {
            return "Fail! Code more than 20 characters";
        }
        if (producer.getName().trim().equals("") || producer.getName() == null) {
            return "Fail! Name is empty";
        }
        if (producer.getName().length() > 30) {
            return "Fail! Name more than 30 characters";
        }
        if (producerRepo.getByCode(producer.getCode()) != null) {
            return "Fail! Code is exist";
        }
        return null;
    }

    public static String validateUpdate(Producer producer) {
        if (producer.getCode().trim().equals("") || producer.getCode() == null) {
            return "Fail! Code is empty";
        }
        if (producer.getCode().length() > 20) {
            return "Fail! Code more than 20 characters";
        }
        if (producer.getCode().trim().equals("") || producer.getCode() == null) {
            return "Fail! Name is empty";
        }
        if (producer.getName().length() > 30) {
            return "Fail! Name more than 30 characters";
        }
        return null;
    }
}
