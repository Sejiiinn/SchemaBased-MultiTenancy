package kt.SchemaBasedMultiTenancy.tenant;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TenantService {

    private final TenantMapper tenantMapper;

    @Transactional
    public int createTenant(Long id, String name) {
        Tenant tenant = new Tenant(id, name);
        try {
            // 테넌트 정보 저장
            tenantMapper.save(tenant);

            // 테넌트의 멤버 테이블 생성
            String createTable = "CREATE TABLE member_" + id + "_" + name + " (ID             BIGINT                                   NOT NULL COMMENT 'ID',\n" +
                                                                            "    NAME           VARCHAR(30) COLLATE utf8mb4_unicode_ci   NULL COMMENT '멤버명',\n" +
                                                                            "    PRIMARY KEY (ID))";
            Map<String, String> map = new HashMap<String, String>();
            map.put("createTable", createTable);

            tenantMapper.createMemberTable(map);
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    @Transactional
    public int deleteTenant(Long id, String name) {
        try {
            // 테넌트 정보 삭제
            tenantMapper.delete(id);

            // 테넌트의 멤버 테이블 삭제
            String dropTable = "DROP TABLE member_" + id + "_" + name;
            Map<String, String> map = new HashMap<String, String>();
            map.put("dropTable", dropTable);

            tenantMapper.dropMemberTable(map);
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

}
