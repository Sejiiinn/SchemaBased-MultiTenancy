package kt.SchemaBasedMultiTenancy.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRepositoryDTO {

    private Long tenantId;
    private String tenantName;

    private Long id;
    private String name;
}
