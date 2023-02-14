import {
  Grid,
  Paper,
  createTheme,
  ThemeProvider,
  CircularProgress,
  Box,
  Typography,
} from "@mui/material";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import calLevel from "../API/calLevel";

const userSearchItemTheme = createTheme({
  palette: {
    primary: {
      main: "#9CB59D",
    },
  },
});

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
        <Typography
          variant="caption"
          component="div"
          style={{
            color: "#5D5E58",
            fontSize: "16px",
            fontFamily: "NanumSquareB",
          }}
        >
          {`${Math.round(props.value)}%`}
        </Typography>
      </Box>
    </Box>
  );
}

const UserSearchResultItemComponent = (props) => {
  // id,name,exp,profileImgPath
  const navigate = useNavigate();
  const id = props.recentSearchResult.id;
  const name = props.recentSearchResult.name;
  const exp = props.recentSearchResult.exp;
  const reduxId = useSelector((state) => state.auth.authentication.id);

  let [level, caledExp] = calLevel(exp);
  if (level === 6) {
    level = 10;
  }
  const profileImgPath = props.recentSearchResult.imgPath;
  return (
    <ThemeProvider theme={userSearchItemTheme}>
      <Grid container>
        <Paper
          onClick={() => {
            if (reduxId && reduxId === id) {
              navigate("/main");
            } else {
              navigate(`/member/${id}`);
            }
          }}
          className="grid-container"
          // variant="contained"
          sx={{
            backgroundColor: "#9CB59D",
          }}
          style={{
            display: "flex",
            direction: "row",
            alignItems: "center",
            justifyContent: "space-evenly",
            width: "100%",
            minHeight: "40px",
            marginTop: "10px",
          }}
        >
          <Grid>
            <img
              src={profileImgPath}
              style={{
                height: "40px",
                borderRadius: "50%",
                margin: "3px 0px",
                border: "1px solid #3C6255",
                display: "flex",
                alignItems: "center",
                justifyContent: "center",
              }}
              alt="profile-img"
            />
          </Grid>
          <Grid style={{ margin: "10px 0px 10px 5px" }}>
            <div style={{ fontFamily: "NanumSquareEB", fontSize: "24px" }}>
              {name}
            </div>
            <div>{id}</div>
          </Grid>
          <Grid>
            <div
              style={{
                width: "45px",
                height: "45px",
                margin: "0px",
                position: "relative",
                backgroundRepeat: "no-repeat",
                backgroundImage: `url("/profile/sprout.png")`,
                backgroundSize: "cover",
                // backgroundColor: "#FFFFFF",
                // borderRadius: "50%",
                // border: "1px solid #3C6255",
              }}
              alt=""
            >
              <img
                style={{
                  width: "35px",
                  height: "35px",
                  position: "absolute",
                  right: "-10px",
                  bottom: "-10px",
                  color: "white",
                  fontSize: "13px",
                  display: "flex",
                  alignItems: "center",
                  justifyContent: "center",
                  lineHeight: "25px",
                }}
                src={`/profile/level${level}.png`}
                alt=""
              />
            </div>
          </Grid>
          <Grid>
            <CircularProgressWithLabel
              variant="determinate"
              color="success"
              value={caledExp}
              style={{
                width: "70px",
                height: "70px",
                margin: "10px 0px",
              }}
            ></CircularProgressWithLabel>
          </Grid>
        </Paper>
      </Grid>
    </ThemeProvider>
  );
};

export default UserSearchResultItemComponent;
