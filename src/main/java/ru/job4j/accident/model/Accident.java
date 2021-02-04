package ru.job4j.accident.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

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
public class Accident {
    private int id;
    private String name;
    private String text;
    private String address;

}
