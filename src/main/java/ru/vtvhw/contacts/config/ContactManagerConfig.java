package ru.vtvhw.contacts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.vtvhw.contacts.dao.ContactDao;
import ru.vtvhw.contacts.dao.InMemoryContactDao;

@Configuration
public class ContactManagerConfig {
    @Bean
    public ContactDao contactDao() {
        return new InMemoryContactDao();
    }
}
