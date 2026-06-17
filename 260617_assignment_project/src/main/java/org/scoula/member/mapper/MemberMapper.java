package org.scoula.member.mapper;

import org.scoula.member.domain.MemberVO;

import java.util.List;

public interface MemberMapper {
    List<MemberVO> getList();

    MemberVO get(Long no);

    int insert(MemberVO member);

    int update(MemberVO member);

    int delete(Long no);
}
