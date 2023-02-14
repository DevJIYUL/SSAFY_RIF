import { useSelector } from "react-redux";
import { Grid, Paper } from "@mui/material";
import PageChangerComponent from "../UI/PageChangerComponent";
import { useEffect, useState } from "react";

const MainProfileComopnent = (props) => {
  const reduxUserInfo = useSelector((state) => state.user.userInfo);
  const [userInfo, setUserInfo] = useState("");

  console.log(userInfo);
  console.log(props.userInfo);
  useEffect(() => {
    if (props.another) {
      setUserInfo(props.userInfo);
    } else {
      setUserInfo(reduxUserInfo);
    }
  }, [props.userInfo, props.another, reduxUserInfo]);
  return (
    <Paper
      elevation={3}
      sx={{
        border: "3px solid #7DBC2F",
        width: "80%",
        minWidth: "320px",
        height: "auto",
        minHeight: "167px",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        margin: "5% auto",
        borderRadius: "15px",
        color: "#5D5E58",
        position: "relative",
      }}
    >
      {props.another && (
        <PageChangerComponent
          sx={{ marin: "0px", position: "absolute", right: "5px", top: "5px" }}
          to={"/change-profile"}
        >
          프로필 변경
        </PageChangerComponent>
      )}
      <Grid container>
        <Grid
          sx={{
            width: "45%",
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
          }}
        >
          {userInfo.profileImgPath && (
            <img
              src={userInfo.profileImgPath}
              alt=""
              srcSet=""
              style={{
                border: "3px solid #7DBC2F",
                width: "101px",
                borderRadius: "50%",
                // margin: "33px 0px 33px 31px",
                padding: "",
              }}
            />
          )}
        </Grid>
        <Grid
          sx={{
            width: "55%",
            display: "flex",
            justifyContent: "flex-start",
            alignItems: "center",
          }}
        >
          <Grid
            sx={{
              fontFamily: "NanumSquareR",
              fontSize: "20px",
              color: "#5D5E58",
              // height: "106px",
            }}
          >
            <div
              style={{
                fontFamily: "NanumSquareB",
                fontSize: "30px",
                margin: "0px",
                display: "flex",
              }}
            >
              {userInfo.name}
            </div>
            <div style={{ marginTop: "3px" }}>{userInfo.id}</div>
            <div style={{ marginTop: "11px" }}>포인트 : {userInfo.point}</div>
            {/* <div>레벨 : {userInfo.point}</div> */}
          </Grid>
        </Grid>
      </Grid>
    </Paper>
  );
};

export default MainProfileComopnent;
