package kt.SchemaBasedMultiTenancy.tenant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tenant {

    private Long id;
    private String name;

    public Tenant(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
