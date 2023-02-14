import React, { useState, useEffect } from "react";
import { loginHandler } from "../store/auth";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { UIActions } from "../store/UISlice";
import {
  Box,
  TextField,
  Button,
  Grid,
  createTheme,
  ThemeProvider,
  FormHelperText,
} from "@mui/material";
import ErrorIcon from "@mui/icons-material/Error";

const loginTheme = createTheme({
  palette: {
    primary: {
      main: "#357a38",
    },
  },
});
const LoginPageComponent = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const [userInputId, setUserInputId] = useState("");
  const [userInputPassword, setUserInputPassword] = useState("");
  const [errorForm, setErrorForm] = useState(false);
  const status = useSelector((state) => state.ui.notification.status);
  const token = useSelector((state) => state.auth.authentication.token);

  const idChangeHandler = (event) => {
    setUserInputId(event.target.value);
  };
  const passwordChangeHandler = (event) => {
    setUserInputPassword(event.target.value);
  };

  function formSubmitHandler(event) {
    event.preventDefault();
    console.log(userInputId, userInputPassword);
    if (!status) {
      dispatch(loginHandler({ id: userInputId, password: userInputPassword }));
    }
  }

  useEffect(() => {
    try {
      if (token) {
        navigate(-1);
      }
    } catch (e) {
      navigate("/home");
    }
    if (status === "error") {
      setErrorForm(true);
      dispatch(UIActions.resetNofication());
    }
  }, [token, navigate, status, dispatch]);

  let btnMessage;

  if (!status) {
    btnMessage = "로그인";
  } else if (status === "pending") {
    btnMessage = "로그인 중 ..";
  } else if (status === "success") {
    btnMessage = "로그인 성공!";
  } else {
    btnMessage = "로그인 실패";
  }

  return (
    <ThemeProvider theme={loginTheme}>
      <Grid
        container
        className="grid-container"
        direction="column"
        alignItems="center"
        justifyContent="center"
        sx={{ my: "8rem" }}
      >
        <Grid item className="grid-header">
          <img
            src="/rif-logo-96.png"
            alt="rif logo 96"
            className="rif-logo-96"
          />
        </Grid>
        <Grid item className="grid-body" sx={{ mx: 2 }}>
          <Box
            component="form"
            noValidate
            autoComplete="off"
            onSubmit={formSubmitHandler}
          >
            {!errorForm && (
              <>
                <TextField
                  fullWidth
                  id="id"
                  label="아이디"
                  type="number"
                  sx={{ mb: 2 }}
                  defaultValue={userInputId}
                  onChange={idChangeHandler}
                />
                <TextField
                  fullWidth
                  id="password"
                  label="비밀번호"
                  type="password"
                  autoComplete="current-password"
                  sx={{ mb: 2 }}
                  value={userInputPassword}
                  onChange={passwordChangeHandler}
                />
              </>
            )}
            {errorForm && (
              <>
                <TextField
                  fullWidth
                  error
                  id="id"
                  label="아이디"
                  type="number"
                  sx={{ mb: 2 }}
                  defaultValue={userInputId}
                  onChange={idChangeHandler}
                />
                <TextField
                  fullWidth
                  error
                  id="password"
                  label="비밀번호"
                  type="password"
                  autoComplete="current-password"
                  sx={{ mb: 2 }}
                  value={userInputPassword}
                  onChange={passwordChangeHandler}
                />
                <FormHelperText>
                  <ErrorIcon />
                  아이디 또는 비밀번호가 올바르지 않습니다.
                </FormHelperText>
              </>
            )}
            <Button
              fullWidth
              variant="contained"
              type="submit"
              sx={{ height: "56px" }}
            >
              {btnMessage}
            </Button>
          </Box>
        </Grid>
      </Grid>
    </ThemeProvider>
  );
};

export default LoginPageComponent;
