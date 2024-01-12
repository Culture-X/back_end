package TripAmi.backend.app.member.service.dto;

import TripAmi.backend.app.product.domain.Product;
import TripAmi.backend.app.product.domain.Program;
import TripAmi.backend.auth.authmember.domain.AuthMember;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class AmiDto{
    private String email;
    private String nickname;
    private String imgUrl;
    private List<Long> programIdList;

    @Builder
    public AmiDto(String email, String nickname, String imgUrl, List<Program> programs) {
        this.email = email;
        this.nickname = nickname;
        this.imgUrl = imgUrl;
        this.programIdList = programs.stream().map(Product::getId).collect(Collectors.toList());
    }
}
