import { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { userSearchRequestHandler } from "../store/searchResultSlice";
import {
  Paper,
  Button,
  TextField,
  Grid,
  InputAdornment,
  createTheme,
  ThemeProvider,
} from "@mui/material";
import UserSearchResultBoxComponent from "../Components/UserSearchResultBoxComponent";
import PageChangerComponent from "../UI/PageChangerComponent";
import { Search } from "@mui/icons-material";

const theme = createTheme({
  palette: {
    primary: {
      main: "#3C6255",
    },
  },
});

const UserSearchComponent = () => {
  const dispatch = useDispatch();
  const [name, setName] = useState("");
  const recentSearchWord = useSelector(
    (state) => state.search.recentSearchWord
  );
  const recentSearchResults = useSelector(
    (state) => state.search.recentSearchResults
  );
  const inputChangeHandler = (event) => {
    setName(event.target.value);
    console.log(event.target.value);
  };

  const formSubmitHandler = (event) => {
    event.preventDefault();
    if (name === recentSearchWord) {
      console.log("same");
      return;
    }
    dispatch(userSearchRequestHandler(name));
  };

  return (
    <ThemeProvider theme={theme}>
      <PageChangerComponent to="/main">메인 화면</PageChangerComponent>
      <Grid
        container
        style={{
          flexDirection: "column",
          width: "100%",
          alignItems: "center",
        }}
      >
        <Paper
          component="form"
          className="grid-container"
          style={{
            display: "flex",
            direction: "row",
            alignitems: "center",
            justifyContent: "center",
            margin: "10px",
            border: "2px solid #3C6255",
            minWidth: "256px",
            width: "90%",
            // backgroundColor: "#3C6255",
            // borderRadius: "15px",
          }}
          onSubmit={formSubmitHandler}
        >
          <TextField
            id="흠"
            size="small"
            style={{
              margin: "10px",
              width: "auto",
              minHeight: "30px",
              padding: "0px",
            }}
            label="유저이름"
            InputProps={{
              startAdornment: (
                <InputAdornment position="start">
                  <Search />
                </InputAdornment>
              ),
            }}
            // variant="standard"
            value={name}
            onChange={inputChangeHandler}
          ></TextField>
          <Button
            style={{
              height: "38px",
              margin: "10px 10px 10px 0px",
              width: "10%",
              minWidth: "38px",
            }}
            variant="contained"
            type="submit"
          >
            <Search></Search>
          </Button>
        </Paper>
        <Grid
          style={{
            display: "flex",
            alignItems: "center",
            justifyContent: "center",
          }}
        >
          {recentSearchResults && (
            <UserSearchResultBoxComponent
              recentSearchResults={recentSearchResults}
              key="UserSearchResultBoxComponent"
            />
          )}
        </Grid>
      </Grid>
    </ThemeProvider>
  );
};

export default UserSearchComponent;
