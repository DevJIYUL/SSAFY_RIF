import {
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  Grid,
} from "@mui/material";
import BtnComponent from "../UI/BtnComponent";
import setUserRefRewardAPI from "../API/setUserRefRewardAPI";
import { useSelector } from "react-redux";
import { useState, useEffect } from "react";
// show the specific information about the badge
// props : onClose, open, reward
const RewardInfoComponent = (props) => {
  const token = useSelector((state) => state.auth.authentication.token);
  const id = useSelector((state) => state.auth.authentication.id);
  const { rewardInfo, achievedAt, hasReward } = props.reward;
  const { title, description, imgPath } = rewardInfo;
  const imgPathColored = imgPath.slice(0, -4) + "_colored.png";

  let refReward;

  const refBadges = useSelector((state) => state.user.userRefBadges);
  const refAchievements = useSelector(
    (state) => state.user.userRefAchievements
  );

  if (props.type == "badge") {
    refReward = refBadges;
  } else {
    refReward = refAchievements;
  }

  const ToggleHandler = (e) => {
    if (props.onDisplay) {
      props.toggler(false);
    } else {
      props.toggler(true);
    }
    setUserRefRewardAPI(props.type, token, id, rewardInfo.id);
    props.onClose();
  };

  return (
    <Dialog onClose={props.onClose} open={props.open}>
      <DialogContent sx={{ bgcolor: "#A6BB8D" }}>
        <Grid className="grid-profile-name" container>
          <Grid
            item
            className="grid-profile"
            display="flex"
            alignItems="center"
          >
            {props.type === "badge" ? (
              <img src={imgPathColored} alt={title} height="75px" />
            ) : (
              <img
                src={imgPath}
                alt={title}
                height="75px"
                style={{ margin: "1rem" }}
              />
            )}
          </Grid>
          <Grid item className="grid-name" sx={{ m: "auto" }}>
            <h2 style={{ margin: "0px", textAlign: "center" }}>{title}</h2>
            <h4 style={{ margin: "0px" }}>
              {achievedAt && achievedAt.slice(0, 10)}
            </h4>
          </Grid>
        </Grid>
        <DialogContentText sx={{ mt: "2rem" }}>{description}</DialogContentText>
      </DialogContent>
      <DialogActions
        sx={{ bgcolor: "#A6BB8D", display: "flex", justifyContent: "center" }}
      >
        {props.onDisplay && !props.isRef && (
          <BtnComponent color="secondary" onClick={ToggleHandler}>
            대표 해제
          </BtnComponent>
        )}
        {!props.onDisplay && !props.isRef && (
          <BtnComponent
            color="secondary"
            onClick={ToggleHandler}
            // disabled={hasReward || isLimited ? false : true}
          >
            대표 설정
          </BtnComponent>
        )}
      </DialogActions>
    </Dialog>
  );
};

export default RewardInfoComponent;
