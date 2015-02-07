package org.camel.web.api.user;

import org.apache.camel.Exchange;
import org.camel.web.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User create(Exchange exchange) {
        User user = (User) exchange.getIn().getBody();
        return repository.save(user);
    }

    public User read(Exchange exchange) {
        long id = exchange.getIn().getHeader("id", Long.class);
        return repository.findOne(id);
    }

    public User update(Exchange exchange) {
        long id = exchange.getIn().getHeader("id", Long.class);
        User user = (User) exchange.getIn().getBody();

        User existing = repository.findOne(id);
        existing.setName(user.getName());

        return repository.save(existing);
    }

    public void delete(Exchange exchange) {
        long id = exchange.getIn().getHeader("id", Long.class);
        repository.delete(id);
    }

    public Iterable<User> findAll() {
        return repository.findAll();
    }
}