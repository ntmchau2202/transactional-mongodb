package src.repository_one;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "tbl_entity_one")
public class EntityOne {
    @Id
    private String id;
    private String value1;
    private Integer value2;
}
