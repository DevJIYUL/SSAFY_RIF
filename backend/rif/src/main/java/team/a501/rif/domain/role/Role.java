package team.a501.rif.domain.role;

import lombok.*;
import team.a501.rif.domain.member.Member;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Role {
    @Id
    @GeneratedValue
    private Long id;

    private String type;
    @ManyToOne
    private Member member;
}
