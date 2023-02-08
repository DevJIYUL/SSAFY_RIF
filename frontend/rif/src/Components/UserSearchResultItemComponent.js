import { Grid, Paper, createTheme, ThemeProvider } from "@mui/material";
import calLevel from "../API/calLevel";

const userSearchItemTheme = createTheme({
  palette: {
    primary: {
      main: "#9CB59D",
    },
  },
});

const UserSearchResultItemComponent = (props) => {
  console.log(props);
  // id,name,exp,profileImgPath
  const id = props.recentSearchResult.id;
  const name = props.recentSearchResult.name;
  const exp = props.recentSearchResult.exp;
  let [level, caledExp] = calLevel(exp);
  if (level === 6) {
    level = 10;
  }
  const profileImgPath = props.recentSearchResult.profileImgPath;
  return (
    <ThemeProvider theme={userSearchItemTheme}>
      <Grid container>
        <Paper
          className="grid-container"
          // variant="contained"
          sx={{
            backgroundColor: "#9CB59D",
          }}
          style={{
            display: "flex",
            direction: "row",
            alignItems: "center",
            justifyContent: "center",
            width: "100%",
            minHeight: "40px",
            marginTop: "10px",
          }}
        >
          <Grid>
            <img
              src={`/profile/${profileImgPath}`}
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
          <Grid style={{ margin: "10px" }}>
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
                marginRight: "0px",
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
              <div
                style={{
                  width: "20px",
                  height: "20px",
                  position: "absolute",
                  right: "0px",
                  bottom: "0px",
                  backgroundRepeat: "no-repeat",
                  backgroundImage: `url("/profile/level.png")`,
                  backgroundSize: "contain",
                  color: "white",
                  fontSize: "13px",
                  display: "flex",
                  alignItems: "center",
                  justifyContent: "center",
                  lineHeight: "25px",
                }}
                src={`/profile/level${level}.png`}
                alt=""
              >
                {/* {level} */}
              </div>
            </div>
          </Grid>
        </Paper>
      </Grid>
    </ThemeProvider>
  );
};

export default UserSearchResultItemComponent;
