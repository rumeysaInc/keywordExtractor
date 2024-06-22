package projects.keywordextractor.core.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import projects.keywordextractor.entities.concretes.Article;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)

    @Column(name="id")
    private int id;

    @Column(name="username")
    private String username;

    @Column(name="surname")
    private String surname;

    @Column(name="email")
    @Email
    @NotBlank //" " geçme
    @NotNull //boş geçme
    private String email;

    @Column(name="password")
    @NotBlank //" " geçme
    @NotNull //boş geçme
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();


}
