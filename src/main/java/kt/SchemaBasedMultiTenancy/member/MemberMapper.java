package kt.SchemaBasedMultiTenancy.member;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    void save(MemberRepositoryDTO memberRepositoryDTO);

    void delete(MemberRepositoryDTO memberRepositoryDTO);

    Member findById(String memberDbAddress, Long id);

    List<Member> findAllMember(String memberDbAddress);
}
