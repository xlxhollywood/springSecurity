    package org.example.project0316.domain;

    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.Setter;


    @Entity
    @Setter
    @Getter
    public class Member {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @Column(unique = true)
        private String username;

        private String password;

        private String role; // admin or user
    }
