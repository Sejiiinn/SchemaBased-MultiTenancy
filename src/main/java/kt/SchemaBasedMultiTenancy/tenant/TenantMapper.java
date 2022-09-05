package kt.SchemaBasedMultiTenancy.tenant;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TenantMapper {

    void save(Tenant tenant);

    void delete(Long id);

    void createMemberTable(Map createTable);

    void dropMemberTable(Map dropTable);

    Tenant findById(Long id);

    List<Tenant> findAllTenant();

}
