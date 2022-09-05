package kt.SchemaBasedMultiTenancy.tenant;

import org.junit.jupiter.api.*;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TenantCRUDTest {

    @Autowired
    private TenantMapper tenantMapper;
    private TenantService tenantService;

    @BeforeEach
    void init() {
        tenantService = new TenantService(tenantMapper);
    }


    @Test // 테넌트 생성
    public void joinTenantTest() {
        Tenant tenant = new Tenant(1001L, "SMP");
        tenantMapper.save(tenant);

        assertThat(tenantMapper.findById(1001L).getName()).isEqualTo("SMP");
    }

    @Test // 테넌트 제거
    void deleteTenantTest() {
        Tenant tenant = new Tenant(1001L, "SMP");
        tenantMapper.save(tenant);
        System.out.println("tenantMapper = " + tenantMapper.findById(tenant.getId()));
        tenantMapper.delete(tenant.getId());
        System.out.println("tenantMapper = " + tenantMapper.findById(tenant.getId()));

        org.junit.jupiter.api.Assertions.assertNull(tenantMapper.findById(tenant.getId()));
    }

    @Test // 테넌트 및 테이블 생성
    @Order(1)
    void joinAndCreateTableTest() {
        assertThat(tenantService.createNewTenant(1001L, "SMP")).isEqualTo(0);
    }

    @Test // 테넌트 및 테이블 제거
    @Order(2)
    void deleteAndDropTableTest() {
        assertThat(tenantService.deleteTenant(1001L, "SMP")).isEqualTo(0);
    }

    @Test
    void findAllTenantTest() {
        Tenant tenant1 = new Tenant(1001L, "SMP");
        Tenant tenant2 = new Tenant(1002L, "SOE");
        tenantMapper.save(tenant1);
        tenantMapper.save(tenant2);

        List<Tenant> allTenant = tenantMapper.findAllTenant();
        System.out.println("allTenant = " + allTenant);
        allTenant.stream().forEach(curTenant -> {
            System.out.println(curTenant.getId() + " " + curTenant.getName());
            assertThat(curTenant.getName()).isEqualTo(tenantMapper.findById(curTenant.getId()).getName());
        });
    }
}
