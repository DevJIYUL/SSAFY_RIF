import SectionTitleComponent from "../UI/SectionTitleComponent";
import RewardComponent from "../Components/RewardComponent";
import { Grid } from "@mui/material";

const AchievementPageComponent = () => {
  return (
    <div>
      <Grid
        container
        direction="column"
        justifyContent="center"
        alignItems="center"
      >
        <SectionTitleComponent sectionTitle="업적"></SectionTitleComponent>

        <RewardComponent type="achievement" isRef={false}></RewardComponent>
      </Grid>
    </div>
  );
};

export default AchievementPageComponent;
