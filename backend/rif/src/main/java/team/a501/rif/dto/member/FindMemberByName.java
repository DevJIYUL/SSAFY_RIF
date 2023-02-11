package team.a501.rif.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindMemberByName {
    private String id;
    private String name;
    private Integer exp;
    private String imgPath;
}
