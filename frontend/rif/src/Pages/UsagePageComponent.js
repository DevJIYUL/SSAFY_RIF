import { useState } from "react";
import { useTheme } from "@mui/material/styles";
import MobileStepper from "@mui/material/MobileStepper";
import Button from "@mui/material/Button";
import KeyboardArrowLeft from "@mui/icons-material/KeyboardArrowLeft";
import KeyboardArrowRight from "@mui/icons-material/KeyboardArrowRight";
import howToUse from "../howToUse.json";
import SwipeableViews from "react-swipeable-views";
import { Grid, Container } from "@mui/material";
import { useSelector } from "react-redux";
import { createTheme, ThemeProvider } from "@mui/material";
import { Link } from "react-router-dom";
import BtnComponent from "../UI/BtnComponent";

const outerTheme = createTheme({
  palette: {
    primary: {
      main: "#476B48",
    },
  },
});

function UsagePageComponent() {
  const token = useSelector((state) => state.auth.authentication.token);

  const theme = useTheme();
  const [activeStep, setActiveStep] = useState(0);

  const maxSteps = howToUse.length;

  const handleNext = () => {
    setActiveStep((prevActiveStep) => prevActiveStep + 1);
  };

  const handleBack = () => {
    setActiveStep((prevActiveStep) => prevActiveStep - 1);
  };

  const handleStepChange = (step) => {
    setActiveStep(step);
  };

  return (
    <Grid
      container
      className="grid-container"
      direction="column"
      alignItems="center"
      justifyContent="center"
      sx={{ m: "2rem", width: "80%", overflow: "visible" }}
    >
      <Grid item className="grid-header">
        <img src="/rif-logo-96.png" alt="rif logo 96" className="rif-logo-96" />
      </Grid>
      <Grid item id="grid-title">
        <div className="rif-stands-for" style={{ color: "#5C855D" }}>
          <h1>{howToUse[activeStep].title}</h1>
        </div>
      </Grid>
      <Grid item id="grid-item" style={{ width: "100%" }}>
        <SwipeableViews
          axis={theme.direction === "rtl" ? "x-reverse" : "x"}
          index={activeStep}
          onChangeIndex={handleStepChange}
          enableMouseEvents
        >
          {howToUse.map((section, sectionId) => {
            return (
              <Grid item sx={{ width: "100%" }} style={{ lineHeight: "1.6" }}>
                <Container id={`img - ${section.sectionId}`}>
                  {section.imagePath && (
                    <img
                      src={`/howToUse/${section.imagePath}`}
                      alt={section.imagePath}
                      width="100%"
                    ></img>
                  )}
                </Container>
                <Container id={`desc - ${section.sectionId}`}>
                  <p style={{ textAlign: "center" }}> {section.description}</p>
                </Container>
              </Grid>
            );
          })}
        </SwipeableViews>
      </Grid>
      <ThemeProvider theme={outerTheme}>
        <MobileStepper
          steps={maxSteps}
          position="static"
          activeStep={activeStep}
          nextButton={
            <Button onClick={handleNext} disabled={activeStep === maxSteps - 1}>
              다음
              <KeyboardArrowRight />
            </Button>
          }
          backButton={
            <Button onClick={handleBack} disabled={activeStep === 0}>
              <KeyboardArrowLeft />
              이전
            </Button>
          }
        />
      </ThemeProvider>
      {!token && (
        <Link to="/login/" style={{ textDecoration: "none" }}>
          <BtnComponent> 서비스 시작하기 </BtnComponent>
        </Link>
      )}
      {token && (
        <Link to="/main/" style={{ textDecoration: "none" }}>
          <BtnComponent> 서비스 시작하기 </BtnComponent>
        </Link>
      )}
    </Grid>
  );
}

export default UsagePageComponent;
