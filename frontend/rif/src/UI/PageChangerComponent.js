import { ThemeProvider } from "@mui/material/styles";
import KeyboardArrowLeftIcon from "@mui/icons-material/KeyboardArrowLeft";
import theme from "./theme";
import Button from "@mui/material/Button";
import { Link } from "react-router-dom";

const PageChangerComponent = (props) => {
  return (
    <ThemeProvider theme={theme}>
      <Link to={props.to} style={{ textDecoration: "none" }}>
        <Button
          variant="text"
          color="pageChanger"
          style={{ fontFamily: "NanumSquareEB" }}
          className={`${props.className}`}
          onClick={props.onClick}
          startIcon={<KeyboardArrowLeftIcon sx={{ color: "#5C855D" }} />}
          sx={props.sx || { m: "1rem" }}
        >
          {props.children}
        </Button>
      </Link>
    </ThemeProvider>
  );
};

export default PageChangerComponent;
