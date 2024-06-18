package UMC.demo.study.domain;

import UMC.demo.study.domain.mapping.Notice;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReceiveNotice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String type;

    private Boolean isReceived;

    @OneToMany(mappedBy = "receiveNotice")
    private List<Notice> noticeList = new ArrayList<>();
}
