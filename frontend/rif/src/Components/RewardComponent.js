import RewardItemComponent from "./RewardItemComponent";
import getBadgesAPI from "../API/getBadgesAPI";
import getAchievementsAPI from "../API/getAchievementsAPI";
import { useState, useEffect } from "react";
import { Grid } from "@mui/material";
import { useSelector, useDispatch } from "react-redux";
import { authActions } from "../store/auth";
import { useNavigate } from "react-router-dom";

// show rounded reward icons
// props : type, isRef

const RewardComponent = (props) => {
  // states
  const token = useSelector((state) => state.auth.authentication.token);
  const id = useSelector((state) => state.auth.authentication.id);
  const [rewards, setRewards] = useState([]);
  const type = props.type;

  const navigate = useNavigate();
  const dispatch = useDispatch();

  // when mounted
  useEffect(() => {
    async function getAchievements() {
      const response = await getAchievementsAPI(token, id);

      if (response.newToken) {
        dispatch(authActions.updateToken(response.newToken));
        return;
      }
      if (response.status === 307) {
        dispatch(authActions.logout());
        navigate("/login");
        return;
      }

      let totalReward = response.data.totalAchievement;

      const rewardHas = [];
      const rewardNotHas = [];

      totalReward.forEach((reward, rewardIndex) => {
        const tempReward = {
          rewardInfo: reward.achievementInfo,
          onDisplay: reward.onDisplay,
          hasReward: reward.hasAchievement,
          achievedAt: reward.achievedAt,
        };
        if (tempReward.hasReward) {
          rewardHas.push(tempReward);
        } else {
          rewardNotHas.push(tempReward);
        }
      });

      totalReward = [...rewardHas, ...rewardNotHas];
      setRewards(totalReward);
    }

    async function getBadges() {
      console.log(token);
      console.log(id, typeof id);
      const response = await getBadgesAPI(token, id);

      if (response.newToken) {
        dispatch(authActions.updateToken(response.newToken));
        return;
      }
      if (response.status === 307) {
        dispatch(authActions.logout());
        navigate("/login");
        return;
      }

      let totalReward = response.data.totalBadge;

      const rewardHas = [];
      const rewardNotHas = [];

      totalReward.forEach((reward, rewardIndex) => {
        const tempReward = {
          rewardInfo: reward.badgeInfo,
          onDisplay: reward.onDisplay,
          hasReward: reward.hasBadge,
          achievedAt: reward.achievedAt,
        };
        if (tempReward.hasReward) {
          rewardHas.push(tempReward);
        } else {
          rewardNotHas.push(tempReward);
        }
      });

      totalReward = [...rewardHas, ...rewardNotHas];
      setRewards(totalReward);
    }

    if (type === "badge" && !props.isRef) {
      getBadges();
    } else if (type === "achievement" && !props.isRef) {
      getAchievements();
    } else {
      setRewards(props.rewards);
    }
  }, [type, props.isRef, props.rewards, dispatch, navigate, token, id]);

  return (
    <Grid
      container
      columns={{ xs: 6, sm: 12 }}
      sx={{ display: "flex", margin: "auto", my: "1rem" }}
      rowSpacing={1}
      width="75%"
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
            <RewardItemComponent
              reward={value}
              type={type}
            ></RewardItemComponent>
          </Grid>
        );
      })}
    </Grid>
  );
};

export default RewardComponent;
