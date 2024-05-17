package src.repository_two;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "tbl_entity_two")
public class EntityTwo {
    @Id
    private String id;
    private String value1;
    private String value2;
    private String value3;
}
