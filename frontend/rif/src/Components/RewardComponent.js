import RewardItemComponent from "./RewardItemComponent"
import getBadgesAPI from "../API/getBadgesAPI"
import getAchievementsAPI from "../API/getAchievementsAPI"
import { useState, useEffect } from "react"
import { Grid } from "@mui/material"

const RewardComponent = (props) => {
  // states
  const [rewards, setRewards] = useState([])
  const type = props.type

  // when mounted
  useEffect(() => {
    async function getAchievements() {
      const response = await getAchievementsAPI("accessToken")
      let totalReward = response.data.totalAchievement

      const rewardHas = []
      const rewardNotHas = []

      totalReward.forEach((reward, rewardIndex) => {
        if (reward.hasReward) {
          rewardHas.push(reward)
        } else {
          rewardNotHas.push(reward)
        }
      })

      totalReward = [...rewardHas, ...rewardNotHas]
      setRewards(totalReward)
    }

    async function getBadges() {
      const response = await getBadgesAPI("accessToken")
      let totalReward = response.data.totalBadge

      const rewardHas = []
      const rewardNotHas = []

      totalReward.forEach((reward, rewardIndex) => {
        if (reward.hasReward) {
          rewardHas.push(reward)
        } else {
          rewardNotHas.push(reward)
        }
      })

      totalReward = [...rewardHas, ...rewardNotHas]
      setRewards(totalReward)
    }

    if (type === "badge") {
      getBadges()
    } else {
      getAchievements()
    }
  }, [type])

  return (
    <div>
      <Grid
        container
        columns={{ xs: 6, sm: 12 }}
        sx={{ display: "flex", margin: "auto" }}
        spacing={1}
      >
        {rewards.map((value, index) => {
          return (
            <Grid
              item
              xs={2}
              sm={4}
              key={index}
              display="flex"
              justifyContent="center"
              alignContent="center"
            >
              <RewardItemComponent reward={value}></RewardItemComponent>
            </Grid>
          )
        })}
      </Grid>
    </div>
  )
}

export default RewardComponent
