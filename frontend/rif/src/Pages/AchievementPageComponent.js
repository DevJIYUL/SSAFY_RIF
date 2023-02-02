import PageChangerComponent from "../UI/PageChangerComponent"
import SectionTitleComponent from "../UI/SectionTitleComponent"
import RewardComponent from "../Components/RewardComponent"
import { Grid } from "@mui/material"

const AchievementPageComponent = () => {
  return (
    <div>
      <h1> Achievement Page Component </h1>
      <PageChangerComponent to="/home"> 마이 페이지 </PageChangerComponent>

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

        <RewardComponent></RewardComponent>
      </Grid>
    </div>
  )
}

export default AchievementPageComponent
