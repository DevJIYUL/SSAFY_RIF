import { ThemeProvider } from "@mui/material/styles";
import KeyboardArrowLeftIcon from "@mui/icons-material/KeyboardArrowLeft";
import KeyboardArrowRightIcon from "@mui/icons-material/KeyboardArrowRight";
import theme from "./theme";
import Button from "@mui/material/Button";

const BtnComponent = (props) => {
  const iconColor = props.color === "secondary" ? "#7D9E7E" : "#C7C597";

  return (
    <ThemeProvider theme={theme}>
      <Button
        variant={props.variant || "contained"}
        color={props.color || "primary"}
        className={`${props.className}`}
        onClick={props.onClick}
        startIcon={
          (props.arrow === "left") | "Left" ? (
            <KeyboardArrowLeftIcon sx={{ color: iconColor }} />
          ) : (
            ""
          )
        }
        endIcon={
          (props.arrow === "right") | "Right" ? (
            <KeyboardArrowRightIcon sx={{ color: iconColor }} />
          ) : (
            props.endIcon
          )
        }
        sx={props.sx || { m: "1rem" }}
        disabled={props.disabled}
        style={props.style}
      >
        {props.children}
      </Button>
    </ThemeProvider>
  );
};

export default BtnComponent;
