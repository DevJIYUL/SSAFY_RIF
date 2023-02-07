import { Paper } from "@mui/material";

const LogItemComponent = (props) => {
  const {
    advise_ignored,
    canOk,
    canTotal,
    created_at,
    plasticOk,
    plasticTotal,
  } = props.log;

  return (
    <Paper
      ref={props.innerRef}
      sx={{ m: "1rem", color: "#5D5E58", border: 1, p: "1rem" }}
      elevation={0}
      background="red"
    >
      [{created_at}]
      <p style={{ margin: "5px 0px 5px 0px" }}>
        <strong>플라스틱</strong> :
        <span style={{ color: "#A6BB8D" }}>{plasticOk}</span> / {plasticTotal}
      </p>
      <p style={{ margin: "5px 0px 5px 0px" }}>
        <strong> 재활용 </strong> :
        <span style={{ color: "#A6BB8D" }}>{canOk}</span> / {canTotal}
      </p>
      <p style={{ margin: "5px 0px 5px 0px" }}>
        <strong> 도전 횟수 </strong> :
        <span style={{ color: "#A6BB8D" }}>{advise_ignored + 1}</span> 회
      </p>
    </Paper>
  );
};

export default LogItemComponent;
