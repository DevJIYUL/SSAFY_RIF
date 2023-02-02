import RewardItemComponent from "./RewardItemComponent"
import getBadgesAPI from "../API/getBadgesAPI"
import getAchievementsAPI from "../API/getAchievementsAPI"
import { useState, useEffect } from "react"
import { Grid } from "@mui/material"

async function APIHandler(type) {
  let totalRewards
  let hasReward

  if (type === "badge") {
    const apiResponse = getBadgesAPI("testaccestoken")
    apiResponse.then((res) => {
      totalRewards = res.data.totalBadge
    })
    hasReward = "hasBadge"
  } else if (type === "achievement") {
    const apiResponse = getAchievementsAPI("testaccestoken")
    apiResponse.then((res) => {
      totalRewards = res.data.totalachievement
    })
    hasReward = "hasAchievement"
  }

  return { totalRewards, hasReward }
}

const RewardComponent = (props) => {
  // states
  const [rewards, setRewards] = useState([])
  const type = props.type

  // when mounted
  useEffect(async () => {
    const { totalRewards, hasReward } = await APIHandler(type)
    console.log("total reward", totalRewards)

    const tempRewardsHas = []
    const tempRewardsNotHas = []

    for (const rewardInfo of totalRewards) {
      if (rewardInfo[hasReward]) {
        tempRewardsHas.push(rewardInfo)
      } else {
        tempRewardsNotHas.push(rewardInfo)
      }
    }

    setRewards(tempRewardsHas.concat(tempRewardsNotHas))
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
              <RewardItemComponent badge={value}></RewardItemComponent>
            </Grid>
          )
        })}
      </Grid>
    </div>
  )
}

export default RewardComponent
