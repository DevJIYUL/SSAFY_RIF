import { Grid, Container } from "@mui/material";
import BtnComponent from "../UI/BtnComponent";
import { Link } from "react-router-dom";

const DescriptionPageComponent = () => {
  return (
    <div>
      <Grid
        container
        className="grid-container"
        direction="column"
        alignItems="center"
        justifyContent="center"
        sx={{ my: "2rem" }}
      >
        <Grid item className="grid-header">
          <img
            src="/rif-logo-96.png"
            alt="rif logo 96"
            className="rif-logo-96"
          />
        </Grid>
        <Grid item className="grid-body">
          <div className="rif-stands-for" style={{ color: "#5C855D" }}>
            <h1>왜 RIF 인가?</h1>
          </div>
        </Grid>
        <Grid
          item
          className="grid-description"
          style={{ width: "80%", lineHeight: "1.6" }}
        >
          <Container className="description" sx={{ mx: "auto" }}>
            <p>사실 우리는 분리수거를 제대로 하고 있지 않고 있습니다.</p>
            <p>
              많은 사람들이 커피샵에서 커피를 사고, 커피를 그대로 플라스틱
              쓰레기통에 버리고는 합니다.
            </p>
            <p>
              그러나, 사실 같은 플라스틱이라도 재활용이 되는 것과 재활용이 되지
              않는 것이 존재한다는 사실을 알고 계셨나요?
            </p>
            <p>
              RIF는 게임을 통해 시민들의 플라스틱 재활용에 대한 올바른 의식을
              함양시키고 더 좋은 세상을 만드는 것에 그 의의를 두고 있습니다.
            </p>
          </Container>
        </Grid>
        <Grid item className="grid-buttons">
          <Link to="/usage" style={{ textDecoration: "none" }}>
            <BtnComponent color="secondary">다음 : 기기 사용법</BtnComponent>
          </Link>
          <br />
          <Link to="/login" style={{ textDecoration: "none" }}>
            <BtnComponent> 서비스 시작하기 </BtnComponent>
          </Link>
        </Grid>
      </Grid>
    </div>
  );
};

export default DescriptionPageComponent;
