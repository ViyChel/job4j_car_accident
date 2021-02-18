package ru.job4j.accident.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Class Accident.
 *
 * @author Vitaly Yagufarov (for.viy@gmail.com)
 * @version 1.0
 * @since 04.02.2021
 */
@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "accident")
public class Accident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "text")
    private String text;
    @Column(name = "address")
    private String address;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private AccidentType type;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "accident_rules",
            joinColumns = {@JoinColumn(name = "accident_id", nullable = false, updatable = false,
                    referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "rule_id", nullable = false, updatable = false,
                    referencedColumnName = "id")})
    private Set<Rule> rules = new LinkedHashSet<>();

    public Accident(String name, String text, String address, AccidentType type, Set<Rule> rules) {
        this.name = name;
        this.text = text;
        this.address = address;
        this.type = type;
        this.rules = rules;
    }
}
