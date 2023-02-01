import PageChangerComponent from "../UI/PageChangerComponent"
import BtnComponent from "../UI/BtnComponent"
import {
  Grid,
  Dialog,
  DialogTitle,
  DialogContentText,
  DialogContent,
} from "@mui/material"
import React, { useEffect } from "react"
import LottoAPI from "../API/LottoAPI"
import { useSelector } from "react-redux"

const LotDialog = (props) => {
  const [badge, setBadge] = React.useState({
    badgeTitle: "로딩 중",
    badgeDesc: "로딩 중",
  })

  useEffect(() => {
    if (props.open === true) {
      // when Dialog opened
      const apiResponse = LottoAPI()
      apiResponse.then((res) => {
        const lotResult = {
          badgeTitle: res.data.badge.title,
          badgeDesc: res.data.badge.description,
        }
        setBadge(lotResult)
      })
    }
    return () => {
      const lotResult = {
        badgeTitle: "로딩 중",
        badgeDesc: "로딩 중",
      }
      setBadge(lotResult)
    }
  }, [props.open])

  return (
    <Dialog onClose={props.onClose} open={props.open} maxWidth={"sm"}>
      <DialogTitle sx={{ textAlign: "center" }}> 뽑기 결과 </DialogTitle>
      <DialogContent>
        <h1 style={{ textAlign: "center" }}> {badge.badgeTitle} </h1>
        <DialogContentText>
          <span style={{ textAlign: "center" }}> {badge.badgeDesc} </span>
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
  const handleOpen = () => {
    setOpen(true)
    console.log("modal opened")
  }
  const handleClose = () => {
    setOpen(false)
    console.log("modal closed")
  }

  let userPoint = useSelector((state) => state.user.userInfo.point)

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
            onClick={handleOpen}
            disabled={userPoint < 100 ? true : false}
          >
            뽑기
          </BtnComponent>
        </Grid>
      </Grid>
      <LotDialog open={open} onClose={handleClose}></LotDialog>
    </div>
  )
}

export default LotComponent
