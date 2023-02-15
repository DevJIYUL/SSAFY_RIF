import { useSelector } from "react-redux";
import {
  Grid,
  Paper,
  CircularProgress,
  Box,
  Popover,
  Typography,
  Button,
} from "@mui/material";
import PageChangerComponent from "../UI/PageChangerComponent";
import { useEffect, useState } from "react";
import calLevel from "../API/calLevel";

function CircularProgressWithLabel(props) {
  return (
    <Box sx={{ position: "relative", display: "inline-flex" }}>
      <CircularProgress variant="determinate" {...props} />
      <Box
        sx={{
          top: 0,
          left: 0,
          bottom: 0,
          right: 0,
          position: "absolute",
          display: "flex",
          alignItems: "center",
          justifyContent: "center",
        }}
      >
        {props.children}
      </Box>
    </Box>
  );
}

const MainProfileComopnent = (props) => {
  const [anchorEl, setAnchorEl] = useState(null);

  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  const open = Boolean(anchorEl);
  const id = open ? "simple-popover" : undefined;

  const reduxUserInfo = useSelector((state) => state.user.userInfo);
  const [userInfo, setUserInfo] = useState("");
  const [level, caledExp] = calLevel(userInfo.exp);

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
        margin: `${window.innerWidth * 0.05 - 8}px auto 5% auto`,
        borderRadius: "15px",
        color: "#5D5E58",
        position: "relative",
      }}
    >
      {userInfo ? (
        <>
          {!props.another && (
            <PageChangerComponent
              sx={{
                marin: "0px",
                position: "absolute",
                right: "5px",
                top: "5px",
              }}
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
                <div style={{ marginTop: "11px", fontSize: "16px" }}>
                  포인트 : {userInfo.point}
                </div>
              </Grid>
              <Grid>
                <Button aria-describedby={id} onClick={handleClick}>
                  <CircularProgressWithLabel
                    variant="determinate"
                    color="success"
                    value={caledExp <= 100 ? caledExp : 100}
                    style={{
                      width: "70px",
                      height: "70px",
                      margin: "10px 0px",
                    }}
                  >
                    <div
                      style={{
                        top: "-4px",
                        right: "4px",
                        width: "45px",
                        height: "45px",
                        margin: "0px",
                        position: "relative",
                        backgroundRepeat: "no-repeat",
                        backgroundImage: `url("/profile/sprout.png")`,
                        backgroundSize: "cover",
                      }}
                      alt=""
                    >
                      <img
                        style={{
                          width: "33px",
                          height: "33px",
                          position: "absolute",
                          right: "-10px",
                          bottom: "-8px",
                          color: "white",
                          display: "flex",
                          alignItems: "center",
                          justifyContent: "center",
                          lineHeight: "25px",
                        }}
                        src={`/profile/level${level}.png`}
                        alt=""
                      />
                    </div>
                  </CircularProgressWithLabel>

                  {/* <div>{exp}</div> */}
                </Button>
                <Popover
                  id={id}
                  open={open}
                  anchorEl={anchorEl}
                  onClose={handleClose}
                  anchorOrigin={{
                    vertical: "bottom",
                    horizontal: "center",
                  }}
                  transformOrigin={{
                    vertical: 0,
                    horizontal: 20,
                  }}
                >
                  <Typography sx={{ p: 2 }}>{caledExp}</Typography>
                </Popover>
              </Grid>
            </Grid>
          </Grid>
        </>
      ) : (
        <CircularProgress color="success" style={{ margin: "45%" }} />
      )}
    </Paper>
  );
};

export default MainProfileComopnent;
