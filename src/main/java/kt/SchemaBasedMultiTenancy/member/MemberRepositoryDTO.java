package kt.SchemaBasedMultiTenancy.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRepositoryDTO {

    private String memberDbAddress;

    private Long id;
    private String name;
}
