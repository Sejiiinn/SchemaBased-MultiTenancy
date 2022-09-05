package kt.SchemaBasedMultiTenancy.member;

import kt.SchemaBasedMultiTenancy.tenant.Tenant;
import kt.SchemaBasedMultiTenancy.tenant.TenantMapper;
import kt.SchemaBasedMultiTenancy.tenant.TenantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MemberCRUDTest {

    @Autowired
    private MemberMapper memberMapper;
    private MemberService memberService;
    @Autowired
    private TenantMapper tenantMapper;
    private TenantService tenantService;

    @BeforeEach
    void init() {
        memberService = new MemberService(memberMapper);
        tenantService = new TenantService(tenantMapper);
    }

    @Test
    void joinMemberTest() {
        Tenant tenant1 = new Tenant(1001L, "Sejin");
        tenantService.createNewTenant(tenant1.getId(), tenant1.getName());

        MemberRepositoryDTO memberRepositoryDTO = new MemberRepositoryDTO();
        memberRepositoryDTO.setTenantId(tenant1.getId());

        memberMapper.save(memberRepositoryDTO);
    }
}
