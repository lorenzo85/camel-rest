package org.camel.web.api.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = AUTO)
    protected long id;

    @Column(name="name")
    protected String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
