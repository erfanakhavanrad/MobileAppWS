package com.example.mobileappws.io.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * @author Erfan Akhavan Rad
 * @created 04/02/2023
 */
@Entity
@Table(name = "roles")
public class RoleEntity implements Serializable {

    public RoleEntity() {
    }

    public RoleEntity(String name) {
        this.name = name;
    }

    private static final long serialVersionUID = 7496438942722679831L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<UserEntity> users;

    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "roles_authorities",
            joinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authorities_id", referencedColumnName = "id")
    )
    private Collection<AuthorityEntity> authorities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Collection<UserEntity> users) {
        this.users = users;
    }

    public Collection<AuthorityEntity> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<AuthorityEntity> authorities) {
        this.authorities = authorities;
    }
}
