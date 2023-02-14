package team.a501.rif.domain.riflog;

import lombok.Builder;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import team.a501.rif.domain.BaseEntity;
import team.a501.rif.domain.member.Member;

import javax.persistence.*;

@NoArgsConstructor
@Entity
public class RifLog extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    private Integer plasticTotal;

    private Integer plasticOk;

    private Integer recycleTotal;

    private Integer recycleOk;

    private Integer exp;

    private Integer point;

    @Builder
    public RifLog(Integer plasticTotal, Integer plasticOk, Integer recycleTotal, Integer recycleOk, Integer exp, Integer point) {
        this.plasticTotal = plasticTotal;
        this.plasticOk = plasticOk;
        this.recycleTotal = recycleTotal;
        this.recycleOk = recycleOk;
        this.exp = exp;
        this.point = point;
    }

    public Long getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Integer getPlasticTotal() {
        return plasticTotal;
    }

    public Integer getPlasticOk() {
        return plasticOk;
    }

    public Integer getRecycleTotal() {
        return recycleTotal;
    }

    public Integer getRecycleOk() {
        return recycleOk;
    }

    public Integer getExp() {
        return exp;
    }

    public Integer getPoint() {
        return point;
    }

    @Override
    public String toString() {
        return "RifLog{" +
                "\n id=" + id +
                ",\n member=" + member.getId() +
                ",\n plasticTotal=" + plasticTotal +
                ",\n plasticOk=" + plasticOk +
                ",\n recycleTotal=" + recycleTotal +
                ",\n recycleOk=" + recycleOk +
                ",\n createdAt= " + this.getCreated().toString() +
                "\n}";
    }
}
