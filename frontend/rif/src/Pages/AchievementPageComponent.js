import PageChangerComponent from "../UI/PageChangerComponent"
import SectionTitleComponent from "../UI/SectionTitleComponent"

const AchievementPageComponent = () => {
  return (
    <div>
      <h1> Achievement Page Component </h1>
      <PageChangerComponent to="/home"> 마이 페이지 </PageChangerComponent>
    </div>
  )
}

export default AchievementPageComponent
