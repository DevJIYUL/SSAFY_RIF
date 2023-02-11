import { Grid, Container } from "@mui/material";
import BtnComponent from "../UI/BtnComponent";
import { Link, useParams } from "react-router-dom";
import PageChangerComponent from "../UI/PageChangerComponent";
import howToUse from "../howToUse.json";
import { useEffect } from "react";

const UsagePageComponent = () => {
  useEffect(() => {
    window.scrollTo(0, 0);
  });

  const { pageId } = useParams();
  const pageDetail = howToUse[pageId - 1];

  console.log(pageDetail, "pagedetail");

  return (
    <div>
      <PageChangerComponent to="/home"> 메인화면 </PageChangerComponent>
      <Grid
        container
        className="grid-container"
        direction="column"
        alignItems="center"
        justifyContent="center"
        sx={{ m: "2rem", width: "80%" }}
      >
        <Grid item className="grid-header">
          <img
            src="/rif-logo-96.png"
            alt="rif logo 96"
            className="rif-logo-96"
          />
        </Grid>
        <Grid item id="grid-title">
          <div className="rif-stands-for" style={{ color: "#5C855D" }}>
            <h1>{pageDetail.detail.title}</h1>
          </div>
        </Grid>
        {pageDetail.detail.section.map((sectionValue, _) => {
          return (
            <Grid item>
              <Container id={`img - ${sectionValue.sectionId}`}>
                {sectionValue.imagePath && (
                  <img
                    src={`/howToUse/${sectionValue.imagePath}`}
                    alt={sectionValue.imagePath}
                    width="100%"
                  ></img>
                )}
              </Container>
              <Container id={`desc - ${sectionValue.sectionId}`}>
                <p> {sectionValue.description}</p>
              </Container>
            </Grid>
          );
        })}
        <Grid item id="grid-buttons">
          {pageId !== "1" ? (
            <Link
              to={`/usage/${pageId - 1}`}
              style={{ textDecoration: "none" }}
            >
              <BtnComponent color="secondary" arrow="left">
                이전
              </BtnComponent>
            </Link>
          ) : (
            <Link to="/description/" style={{ textDecoration: "none" }}>
              <BtnComponent color="secondary" arrow="left">
                이전
              </BtnComponent>
            </Link>
          )}
          {pageId < howToUse.length && (
            <Link
              to={`/usage/${Number(pageId) + 1}`}
              style={{ textDecoration: "none" }}
            >
              <BtnComponent color="secondary" arrow="right">
                다음
              </BtnComponent>
            </Link>
          )}
        </Grid>
        <Grid item sx={{ display: "flex", justifyContent: "center" }}>
          <Link to="/login" style={{ textDecoration: "none" }}>
            <BtnComponent> 서비스 시작하기 </BtnComponent>
          </Link>
        </Grid>
      </Grid>
    </div>
  );
};

export default UsagePageComponent;
