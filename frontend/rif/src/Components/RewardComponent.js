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

  const userRefBadges = useSelector((state) => state.user.userRefBadges);
  const userRefAchievements = useSelector(
    (state) => state.user.userRefAchievements
  );

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
      } else if (response.status === 307) {
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
    } else if (type === "badge" && props.isRef) {
      setRewards(userRefBadges);
    } else if (type === "achievement" && props.isRef) {
      setRewards(userRefAchievements);
    }
  }, [
    type,
    props.isRef,
    dispatch,
    navigate,
    token,
    id,
    userRefAchievements,
    userRefBadges,
  ]);

  console.log(props, rewards, "rewardCom");

  return (
    <Grid
      container
      columns={{ xs: 6, sm: 12 }}
      sx={{ display: "flex", margin: "auto", my: "1rem" }}
      rowSpacing={1}
      width="75%"
    >
      {rewards.length ? (
        rewards.map((value, index) => {
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
                isRef={props.isRef}
              ></RewardItemComponent>
            </Grid>
          );
        })
      ) : (
        <Grid
          item
          display="flex"
          justifyContent="space-around"
          alignContent="center"
          sx={{ width: "100%", overflow: "auto" }}
        >
          {[1, 2, 3].map((imgPath, index) => {
            return (
              <img
                src={`/${props.type}/${imgPath}.png`}
                alt={`/${props.type}/${imgPath}.png`}
                style={{ opacity: 0.3, filter: "grayscale(100)" }}
                height="75px"
              />
            );
          })}
          <h4 style={{ position: "absolute" }}>
            아직 설정된{" "}
            <span style={{ color: "#4E9E00", fontWeight: "700" }}>
              대표 {props.type === "badge" ? "뱃지가 " : "업적이 "}
            </span>
            없습니다.
          </h4>
        </Grid>
      )}
    </Grid>
  );
};

export default RewardComponent;
