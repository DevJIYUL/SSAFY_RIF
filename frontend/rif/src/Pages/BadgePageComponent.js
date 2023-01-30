import PageChangerComponent from "../UI/PageChangerComponent";
import SectionTitleComponent from "../UI/SectionTitleComponent";
import { Grid } from "@mui/material";
import RewardItemComponent from "../Components/RewardItemComponent";
import RewardComponent from "../Components/RewardComponent";

const BadgePageComponent = () => {
  return (
    <div>
      <PageChangerComponent to="/home"> 마이 페이지</PageChangerComponent>

      <Grid
        container
        direction="column"
        justifyContent="center"
        alignItems="center"
      >
        <SectionTitleComponent
          sectionTitle="뱃지"
          sectionDetail="정렬"
        ></SectionTitleComponent>

        <RewardComponent></RewardComponent>
      </Grid>
    </div>
  );
};

export default BadgePageComponent;
