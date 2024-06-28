package net.springboot.employeemanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USERS")
public class User {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Getter @Setter private long id;

	    @Column(name = "username")
	    @Getter @Setter private String username;

	    @Column(name = "password")
	    @Getter @Setter private String password;

	    @Column(name = "enabled")
	    @Getter @Setter private Boolean enabled;
}
