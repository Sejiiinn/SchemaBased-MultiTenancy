package kt.SchemaBasedMultiTenancy.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    private Long id;
    private String name;
}
