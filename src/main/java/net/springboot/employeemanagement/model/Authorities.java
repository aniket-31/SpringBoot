package net.springboot.employeemanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "AUTHORITIES")
public class Authorities {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private long id;

    @Column(name = "username")
    @Getter @Setter private String username;

    @Column(name = "authority")
    @Getter @Setter private String authority;

}
