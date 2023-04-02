import { Grid, Container } from "@mui/material";
import BtnComponent from "../UI/BtnComponent";
import { Link } from "react-router-dom";
import { useSelector } from "react-redux";

const HomePageComponent = () => {
  const token = useSelector((state) => state.auth.authentication.token);

  return (
    <Grid
      container
      className="grid-container"
      direction="column"
      alignItems="center"
      justifyContent="center"
      sx={{ my: "8rem" }}
    >
      <Grid item className="grid-header">
        <img src="/rif-logo-96.png" alt="rif logo 96" className="rif-logo-96" />
      </Grid>
      <Grid item className="grid-body">
        <div className="rif-stands-for" style={{ color: "#5C855D" }}>
          <h1>
            Recycling
            <br />
            Is
            <br />
            Fun
          </h1>
        </div>
      </Grid>
      <Grid item className="grid-description">
        <Container
          className="description"
          sx={{ mx: "auto", width: "80%", lineHeight: "1.6" }}
        >
          <p>
            분리수거는 재미없고 귀찮은 일이라는 인식을 바꿔주기 위해, 게임을
            도입하고 재미있는 분리수거를 지향하는 프로젝트입니다.
          </p>
        </Container>
      </Grid>
      <Grid item className="grid-buttons">
        <Link to="/description" style={{ textDecoration: "none" }}>
          <BtnComponent> 더 알아보기 </BtnComponent>
        </Link>
        {!token && (
          <Link to="/login" style={{ textDecoration: "none" }}>
            <BtnComponent> 서비스 시작하기 </BtnComponent>
          </Link>
        )}
        {token && (
          <Link to="/main" style={{ textDecoration: "none" }}>
            <BtnComponent> 서비스 시작하기 </BtnComponent>
          </Link>
        )}
      </Grid>
    </Grid>
  );
};

export default HomePageComponent;
