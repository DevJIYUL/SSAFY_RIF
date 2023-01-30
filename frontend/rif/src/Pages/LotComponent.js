import PageChangerComponent from "../UI/PageChangerComponent";
import BtnComponent from "../UI/BtnComponent";
import {
  Grid,
  Dialog,
  DialogTitle,
  DialogContentText,
  DialogContent,
} from "@mui/material";
import React from "react";

const LotDialog = (props) => {
  return (
    <Dialog onClose={props.onClose} open={props.open} maxWidth={"sm"}>
      <DialogTitle sx={{ textAlign: "center" }}> 뽑기 결과 </DialogTitle>
      <DialogContent>
        <DialogContentText>
          개 쓰레기가 나왔습니다!! 크크루삥뽕
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
  const handleOpen = () => {
    setOpen(true);
    console.log("modal opened");
  };
  const handleClose = () => {
    setOpen(false);
    console.log("modal closed");
  };

  return (
    <div>
      <PageChangerComponent to="/"> 뱃지 </PageChangerComponent>
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
            <h2 style={{ color: "#5D5E58" }}>잔여 포인트 : 1024 pt</h2>
          </div>
        </Grid>
        <Grid item className="grid-body"></Grid>
        <Grid item className="grid-buttons">
          <BtnComponent color="secondary" onClick={handleOpen}>
            뽑기
          </BtnComponent>
        </Grid>
      </Grid>
      <LotDialog open={open} onClose={handleClose}></LotDialog>
    </div>
  );
};

export default LotComponent;
