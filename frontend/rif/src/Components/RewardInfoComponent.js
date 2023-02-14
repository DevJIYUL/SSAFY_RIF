import {
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  Grid,
} from "@mui/material";
import BtnComponent from "../UI/BtnComponent";
import setUserRefRewardAPI from "../API/setUserRefRewardAPI";
import getUserRefBadgeAPI from "../API/getUserRefBadgeAPI";
import getUserRefAchievementAPI from "../API/getUserRefAchievementAPI";

import { useSelector } from "react-redux";
import { useState, useEffect } from "react";
// import { userInfoActions } from "../store/getUserInfo";

// show the specific information about the badge
// props : onClose, open, reward
const RewardInfoComponent = (props) => {
  const [refSettable, setRefSettable] = useState(true);
  const token = useSelector((state) => state.auth.authentication.token);
  const id = useSelector((state) => state.auth.authentication.id);
  const { rewardInfo, achievedAt, hasReward } = props.reward;
  const { title, description, imgPath } = rewardInfo;
  const imgPathColored = imgPath.slice(0, -4) + "_colored.png";

  async function ToggleHandler(e) {
    if (props.onDisplay) {
      props.toggler(false);
    } else {
      props.toggler(true);
    }
    setUserRefRewardAPI(props.type, token, id, rewardInfo.id);
    props.onClose();
  }

  useEffect(() => {
    async function getRepresentativeLength(id) {
      let curRefLength;
      if (props.type === "badge") {
        if (id) {
          const userBadgeResponse = await getUserRefBadgeAPI(id);
          curRefLength = userBadgeResponse.data.onDisplayBadge.length;
        }
      } else {
        // props.type === "achievement"
        if (id) {
          const userAcievementResponse = await getUserRefAchievementAPI(id);
          curRefLength =
            userAcievementResponse.data.onDisplayAchievement.length;
        }
      }

      if (curRefLength < 3) {
        setRefSettable(true);
      } else {
        setRefSettable(false);
      }
    }
    getRepresentativeLength(id);
  });

  return (
    <Dialog
      onClose={props.onClose}
      open={props.open}
      PaperProps={{
        style: { borderRadius: 30 },
      }}
    >
      <DialogContent sx={{ bgcolor: "#A6BB8D" }}>
        <Grid className="grid-profile-name" container>
          <Grid
            item
            className="grid-profile"
            display="flex"
            alignItems="center"
          >
            {props.type === "badge" ? (
              <img
                src={imgPathColored}
                alt={title}
                height="75px"
                style={{ margin: "1rem" }}
              />
            ) : (
              <img src={imgPath} alt={title} height="75px" />
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
        {!refSettable && !props.onDisplay && (
          <DialogContentText sx={{ mt: "2rem", color: "red", fontSize: 12 }}>
            대표 뱃지(업적)은 3 개까지 설정 가능합니다.
          </DialogContentText>
        )}
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
            disabled={!hasReward || !refSettable ? true : false}
          >
            대표 설정
          </BtnComponent>
        )}
      </DialogActions>
    </Dialog>
  );
};

export default RewardInfoComponent;
