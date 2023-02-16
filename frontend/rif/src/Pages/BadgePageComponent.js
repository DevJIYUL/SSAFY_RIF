import SectionTitleComponent from "../UI/SectionTitleComponent";
import { Grid } from "@mui/material";
import RewardComponent from "../Components/RewardComponent";

const BadgePageComponent = () => {
  return (
    <div>
      <Grid
        container
        direction="column"
        justifyContent="center"
        alignItems="center"
      >
        <SectionTitleComponent sectionTitle="뱃지"></SectionTitleComponent>

        <RewardComponent type="badge" isRef={false}></RewardComponent>
      </Grid>
    </div>
  );
};

export default BadgePageComponent;
