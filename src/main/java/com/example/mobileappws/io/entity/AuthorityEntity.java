package com.example.mobileappws.io.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * @author Erfan Akhavan Rad
 * @created 04/02/2023
 */
@Entity
@Table(name = "authorities")
public class AuthorityEntity implements Serializable {

    public AuthorityEntity() {}
    public AuthorityEntity(String name) {
        this.name = name;
    }

    private static final long serialVersionUID = 9052344132194044767L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, length = 20)
    private String name;

    @ManyToMany(mappedBy = "authorities")
    private Collection<RoleEntity> roles;

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

    public Collection<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Collection<RoleEntity> roles) {
        this.roles = roles;
    }
}
