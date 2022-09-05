package kt.SchemaBasedMultiTenancy.member;

import kt.SchemaBasedMultiTenancy.tenant.Tenant;
import kt.SchemaBasedMultiTenancy.tenant.TenantMapper;
import kt.SchemaBasedMultiTenancy.tenant.TenantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Commit;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
        // 테넌트의 멤버 테이블 생성
        Tenant tenant1 = new Tenant(1001L, "SMP");
        Tenant tenant2 = new Tenant(1002L, "SOE");
        tenantService.createNewTenant(tenant1.getId(), tenant1.getName());
        tenantService.createNewTenant(tenant2.getId(), tenant2.getName());

        // 멤버 생성
        memberService.createMember(tenant1.getId(), tenant1.getName(), 1L, "SeJin");
        memberService.createMember(tenant1.getId(), tenant1.getName(), 2L, "MinWoo");

        memberService.createMember(tenant2.getId(), tenant2.getName(), 1L, "search1");

        // 멤버 리스트
        System.out.println(tenant1.getName());
        List<Member> SMPMember = memberService.findAllMember(tenant1.getId(), tenant1.getName());
        SMPMember.stream().forEach(curMember -> {
            System.out.println(curMember.getId() + " " + curMember.getName());
        });

        System.out.println(tenant2.getName());
        List<Member> SOEMember = memberService.findAllMember(tenant2.getId(), tenant2.getName());
        SOEMember.stream().forEach(curMember -> {
            System.out.println(curMember.getId() + " " + curMember.getName());
        });

        // 테넌트 멤버 테이블 제거
        tenantService.deleteTenant(tenant1.getId(), tenant1.getName());
        tenantService.deleteTenant(tenant2.getId(), tenant2.getName());
    }
}
