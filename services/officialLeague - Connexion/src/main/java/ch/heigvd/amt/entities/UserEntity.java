package ch.heigvd.amt.entities;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

import javax.persistence.*;

// TODO
@Getter
@Entity
@Table(name = "User")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    private string firstName;

    @Setter
    private string lastName;

    @Setter
    private string email;

    @Setter
    private string password;
}
