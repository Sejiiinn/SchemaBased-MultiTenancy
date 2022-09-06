package kt.SchemaBasedMultiTenancy.member;

import kt.SchemaBasedMultiTenancy.tenant.Tenant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;

    @Transactional
    public int createMember(Long tenantId, String tenantName, Long id, String name) {
        MemberRepositoryDTO memberRepositoryDTO = new MemberRepositoryDTO();

        String memberDbAddress = makeMemberDbAddress(tenantId, tenantName);
        memberRepositoryDTO.setMemberDbAddress(memberDbAddress);
        memberRepositoryDTO.setId(id);
        memberRepositoryDTO.setName(name);

        try {
            // 멤버 정보 저장
            memberMapper.save(memberRepositoryDTO);

            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    @Transactional
    public int deleteMember(Long tenantId, String tenantName, Long id) {
        MemberRepositoryDTO memberRepositoryDTO = new MemberRepositoryDTO();

        memberRepositoryDTO.setMemberDbAddress(makeMemberDbAddress(tenantId, tenantName));
        memberRepositoryDTO.setId(id);

        try {
            // 테넌트 정보 삭제
            memberMapper.delete(memberRepositoryDTO);

            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public List<Member> findAllMember(Long tenantId, String tenantName) {
        return memberMapper.findAllMember(makeMemberDbAddress(tenantId, tenantName));
    }

    public Member findById(Long tenantId, String tenantName, Long id) {
        String memberDbAddress = makeMemberDbAddress(tenantId, tenantName);
        return memberMapper.findById(memberDbAddress, id);
    }

    public String makeMemberDbAddress(Long tenantId, String tenantName) {
        return "member_" + tenantId + "_" + tenantName;
    }
}
