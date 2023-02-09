import { Box } from "@mui/material";
import { ThemeProvider } from "@mui/material/styles";
import KeyboardArrowRightIcon from "@mui/icons-material/KeyboardArrowRight";
import theme from "./theme";
import Button from "@mui/material/Button";
import { Link } from "react-router-dom";

const SectionDetailComponent = (props) => {
  return (
    <ThemeProvider theme={theme}>
      <Link to={props.to} style={{ textDecoration: "none" }}>
        <Button
          variant="text"
          className={`${props.className}`}
          onClick={props.onClick}
          endIcon={<KeyboardArrowRightIcon sx={{ color: "#5C855D" }} />}
          sx={props.sx || { m: "1px", color: "#5D5E58" }}
        >
          {props.children}
        </Button>
      </Link>
    </ThemeProvider>
  );
};

const SectionTitleComponent = (props) => {
  return (
    <Box
      sx={{
        width: "75%",
        backgroundColor: "#9CB59D",
        borderRadius: 2,
        boxShadow: 2,
        p: 0.5,
        px: 2,
        component: "span",
        display: "flex",
        justifyContent: "space-between",
        minHeight: "38.500px",
      }}
    >
      <span
        className="section-title"
        style={{
          color: "#FBFBFB",
          alignItems: "center",
          display: "inline-flex",
        }}
      >
        {props.sectionTitle}
      </span>
      {props.sectionDetail && (
        <SectionDetailComponent to={props.to}>
          {props.sectionDetail}
        </SectionDetailComponent>
      )}
    </Box>
  );
};

export default SectionTitleComponent;
