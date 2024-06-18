package UMC.demo.study.domain;

import UMC.demo.study.domain.common.BaseEntity;
import UMC.demo.study.domain.mapping.MemberPrefer;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
public class FoodCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String name;

    @OneToMany(mappedBy = "foodCategory", cascade = CascadeType.ALL)
    List<MemberPrefer> memberPreferList = new ArrayList<>();
}
