import PageChangerComponent from "../UI/PageChangerComponent"
import BtnComponent from "../UI/BtnComponent"
import {
  Grid,
  Dialog,
  DialogTitle,
  DialogContentText,
  DialogContent,
} from "@mui/material"
import React from "react"
import { useSelector, useDispatch } from "react-redux"
import { lotteryCloseHandler, lotteryOpenHandler } from "../store/lottoSlice"

const LotDialog = (props) => {
  const badgeTitle = useSelector((state) => state.lotto.badgeTitle)
  const badgeDesc = useSelector((state) => state.lotto.badgeDesc)

  return (
    <Dialog onClose={props.onClose} open={props.open} maxWidth={"sm"}>
      <DialogTitle sx={{ textAlign: "center" }}> 뽑기 결과 </DialogTitle>
      <DialogContent>
        <h1 style={{ textAlign: "center" }}> {badgeTitle} </h1>
        <DialogContentText>
          <span style={{ textAlign: "center" }}> {badgeDesc} </span>
        </DialogContentText>
      </DialogContent>
      <BtnComponent onClick={props.onClose} color="secondary">
        확인
      </BtnComponent>
    </Dialog>
  )
}

const LotComponent = () => {
  // state to control modal dialog
  const [open, setOpen] = React.useState(false)
  const dispatch = useDispatch()

  let userPoint = useSelector((state) => state.user.userInfo.point)

  const modelOpenHandler = () => {
    setOpen(true)
    console.log("modal opened")
    dispatch(lotteryOpenHandler())
  }

  const modelCloseHandler = () => {
    setOpen(false)
    console.log("modal closed")
    dispatch(lotteryCloseHandler())
  }

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
            <h2 style={{ color: "#5D5E58" }}>잔여 포인트 : {userPoint} pt</h2>
          </div>
        </Grid>
        <Grid item className="grid-body"></Grid>
        <Grid item className="grid-buttons">
          <BtnComponent
            color="secondary"
            onClick={modelOpenHandler}
            disabled={userPoint < 100 ? true : false}
          >
            뽑기
          </BtnComponent>
        </Grid>
      </Grid>
      <LotDialog open={open} onClose={modelCloseHandler}></LotDialog>
    </div>
  )
}

export default LotComponent
