package project.domain;

import javax.persistence.*;

@MappedSuperclass
public abstract class TableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    public TableModel() {
    }

    public Long getId() {
        return id;
    }
}
