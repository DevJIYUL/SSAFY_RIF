import BtnComponent from "../UI/BtnComponent";
import {
  Grid,
  Dialog,
  DialogTitle,
  DialogContentText,
  DialogContent,
} from "@mui/material";
import React, { useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { lotteryCloseHandler, lotteryOpenHandler } from "../store/lottoSlice";
import Firework from "../Components/Firework";
import Zoom from "@mui/material/Zoom";
import { mainPageRequestHandler } from "../store/getUserInfo";
import { useNavigate } from "react-router-dom";

const LotDialog = (props) => {
  const colors = ["#4C70B0", "#A48D25", "#434343", "#865A42"];
  const badgeTitle = useSelector((state) => state.lotto.badgeTitle);
  const badgeDesc = useSelector((state) => state.lotto.badgeDesc);
  const badgeTier = useSelector((state) => state.lotto.badgeTier);
  const badgeImgPath = useSelector((state) => state.lotto.badgeImgPath);
  const showResult = useSelector((state) => state.lotto.showResult);

  return (
    <Dialog onClose={props.onClose} open={props.open} maxWidth={"sm"}>
      {showResult ? (
        <DialogTitle sx={{ textAlign: "center" }}> 뽑기 결과 </DialogTitle>
      ) : (
        <DialogTitle sx={{ textAlign: "center" }}> 로 딩 중 </DialogTitle>
      )}
      <DialogContent>
        <Zoom in={showResult}>
          <h1
            style={{ textAlign: "center", color: `${colors[badgeTier - 1]}` }}
            id="badgeTitle"
          >
            {badgeTitle}
          </h1>
        </Zoom>
        <Zoom in={showResult}>
          <div
            style={{
              display: "flex",
              justifyContent: "center",
              margin: "1rem",
            }}
          >
            {badgeImgPath && (
              <img
                src={badgeImgPath}
                alt={badgeTier}
                width={"150"}
                height={"150"}
              ></img>
            )}
          </div>
        </Zoom>
        <DialogContentText>
          <Zoom in={showResult}>
            <span style={{ textAlign: "center" }}>{badgeDesc}</span>
          </Zoom>
        </DialogContentText>
      </DialogContent>

      <BtnComponent onClick={props.onClose} color="secondary">
        확인
      </BtnComponent>
    </Dialog>
  );
};

const LotComponent = () => {
  // state to control modal dialog
  const [open, setOpen] = React.useState(false);
  const token = useSelector((state) => state.auth.authentication.token);
  const id = useSelector((state) => state.auth.authentication.id);

  const dispatch = useDispatch();
  const navigate = useNavigate();

  let userPoint = useSelector((state) => state.user.userInfo.point);

  const modelOpenHandler = () => {
    setOpen(true);
    dispatch(lotteryOpenHandler(token, id));
  };

  const modelCloseHandler = () => {
    setOpen(false);
    dispatch(lotteryCloseHandler());
  };

  useEffect(() => {
    if (!token || !id) {
      navigate("/login");
      return;
    } else {
      dispatch(mainPageRequestHandler(id, token));
    }
  }, [navigate, dispatch, token, id]);

  return (
    <div>
      <Grid
        container
        className="grid-container"
        direction="column"
        alignItems="center"
        justifyContent="center"
        sx={{ my: "2rem" }}
      >
        <Grid item className="grid-header">
          <div className="rif-stands-for">
            <h1 style={{ color: "#5C855D", textAlign: "center" }}>뽑기</h1>
            <h2 style={{ color: "#5D5E58" }}>잔여 포인트 : {userPoint} pt</h2>
          </div>
        </Grid>
        <Grid item className="grid-body">
          <img
            src="/badge/question_box.png"
            alt="question box"
            style={{
              width: "100px",
              height: "100px",
              border: "5px solid white",
              overflow: "hidden",
              borderRadius: "50%",
              objectFit: "cover",
              objectPosition: "0 0",
              boxShadow: "0px 5px 15px 0px rgba(0, 0, 0, 0.6)",
            }}
          ></img>
        </Grid>
        <Grid item className="grid-buttons">
          <BtnComponent
            color="secondary"
            onClick={modelOpenHandler}
            disabled={userPoint < 100 || !userPoint ? true : false}
          >
            뽑기
          </BtnComponent>
        </Grid>
      </Grid>
      <LotDialog open={open} onClose={modelCloseHandler}></LotDialog>
      <Firework closer={modelCloseHandler} />
    </div>
  );
};

export default LotComponent;
