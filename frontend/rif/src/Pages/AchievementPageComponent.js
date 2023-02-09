import PageChangerComponent from "../UI/PageChangerComponent";
import SectionTitleComponent from "../UI/SectionTitleComponent";
import RewardComponent from "../Components/RewardComponent";
import { Grid } from "@mui/material";

const AchievementPageComponent = () => {
  return (
    <div>
      <PageChangerComponent to="/main"> 마이 페이지 </PageChangerComponent>

      <Grid
        container
        direction="column"
        justifyContent="center"
        alignItems="center"
      >
        <SectionTitleComponent
          sectionTitle="업적"
          sectionDetail="정렬"
        ></SectionTitleComponent>

        <RewardComponent type="achievement" isRef={false}></RewardComponent>
      </Grid>
    </div>
  );
};

export default AchievementPageComponent;
