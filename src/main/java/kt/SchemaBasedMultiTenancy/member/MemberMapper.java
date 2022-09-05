package kt.SchemaBasedMultiTenancy.member;

import kt.SchemaBasedMultiTenancy.tenant.Tenant;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    void save(MemberRepositoryDTO memberRepositoryDTO);

    void delete(MemberRepositoryDTO memberRepositoryDTO);

    Member findById(Long id);

    List<Member> findAllMember();
}