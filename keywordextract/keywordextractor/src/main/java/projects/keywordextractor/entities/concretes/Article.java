package projects.keywordextractor.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import projects.keywordextractor.core.entities.UserEntity;

import java.util.List;

@Data ////Lombok
@Entity
@Table(name="Article")
@AllArgsConstructor
@NoArgsConstructor

public class Article {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )

    @Column(name="id")
    private int id;

    @Column(name="user_id")
    private int userId;

    @Column(name="title")
    private String title;

    @Column(name="content",length = 4000)
    private String content;

    @Column(name="category")
    private String category;

    @Column(name="get_abstract",length = 1000)
    private String get_abstract;

    @Column(name="ispublic")
    private boolean isPublic;

    /*@ElementCollection
    @Column(name = "keywords")
    private List<String> keywords;*/

    @ElementCollection
    @CollectionTable(name = "article_keywords", joinColumns = @JoinColumn(name = "article_id"))
    @Column(name = "keyword")
    private List<String> keywords;










}
