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

    @Builder
    public RifLog(Integer plasticTotal, Integer plasticOk, Integer recycleTotal, Integer recycleOk) {
        this.plasticTotal = plasticTotal;
        this.plasticOk = plasticOk;
        this.recycleTotal = recycleTotal;
        this.recycleOk = recycleOk;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setPlasticTotal(Integer plasticTotal) {
        this.plasticTotal = plasticTotal;
    }

    public Integer getPlasticOk() {
        return plasticOk;
    }

    public void setPlasticOk(Integer plasticOk) {
        this.plasticOk = plasticOk;
    }

    public Integer getRecycleTotal() {
        return recycleTotal;
    }

    public void setRecycleTotal(Integer recycleTotal) {
        this.recycleTotal = recycleTotal;
    }

    public Integer getRecycleOk() {
        return recycleOk;
    }

    public void setRecycleOk(Integer recycleOk) {
        this.recycleOk = recycleOk;
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
