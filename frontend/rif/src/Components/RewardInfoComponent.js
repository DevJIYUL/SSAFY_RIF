import {
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  Grid,
} from "@mui/material";
import BtnComponent from "../UI/BtnComponent";

// show the specific information about the badge
// props : onClose, open, reward
const RewardInfoComponent = (props) => {
  const { rewardInfo, onDisplay, achievedAt } = props.reward;
  const { title, description, imgPath } = rewardInfo;
  const imgPathColored = imgPath.slice(0, -4) + "_colored.png";

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
              <img src={imgPath} alt={title} height="75px" />
            )}
          </Grid>
          <Grid item className="grid-name" sx={{ ml: "2rem" }}>
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
        {onDisplay ? (
          <BtnComponent color="secondary"> 대표 해제 </BtnComponent>
        ) : (
          <BtnComponent color="secondary"> 대표 설정 </BtnComponent>
        )}
      </DialogActions>
    </Dialog>
  );
};

export default RewardInfoComponent;
