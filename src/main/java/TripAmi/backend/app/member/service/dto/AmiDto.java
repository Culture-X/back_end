package TripAmi.backend.app.member.service.dto;

import TripAmi.backend.app.product.domain.Product;
import TripAmi.backend.app.product.domain.Program;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class AmiDto{
    private String email;
    private String nickname;
    private String imgUrl;
    private BigDecimal rating;
    private List<Long> programIdList;

    @Builder
    public AmiDto(String email, String nickname, String imgUrl, BigDecimal rating, List<Program> programs) {
        this.email = email;
        this.nickname = nickname;
        this.imgUrl = imgUrl;
        this.rating = rating;
        this.programIdList = programs.stream().map(Product::getId).collect(Collectors.toList());
    }
}
