import { useSelector } from "react-redux";
import { Grid, Paper } from "@mui/material";

const MainProfileComopnent = () => {
  const userInfo = useSelector((state) => state.user.userInfo);

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
      }}
    >
      <Grid container>
        <Grid
          sx={{
            width: "45%",
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
          }}
        >
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
