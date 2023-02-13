import { Paper } from "@mui/material";

const LogItemComponent = (props) => {
  const {
    exp,
    point,
    recycleOk,
    recycleTotal,
    createdAt,
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
      <p style={{ textAlign: "center", margin: "0" }}>[{createdAt}]</p>
      <div
        style={{
          display: "flex",
          justifyContent: "space-around",
          margin: "5px 0px 0px 0px",
        }}
      >
        <p style={{ margin: "5px 0px 5px 0px" }}>
          <strong>플라스틱</strong> :
          <span style={{ color: "#A6BB8D" }}>{plasticOk}</span> / {plasticTotal}
        </p>
        <p style={{ margin: "5px 0px 5px 0px" }}>
          <strong> 재활용 </strong> :
          <span style={{ color: "#A6BB8D" }}>{recycleOk}</span> / {recycleTotal}
        </p>
      </div>
      <div style={{ display: "flex", justifyContent: "space-around" }}>
        <p style={{ margin: "5px 0px 5px 0px" }}>
          <strong>획득 포인트</strong> :
          <span style={{ color: "#A6BB8D" }}> {point} </span>
        </p>
        <p style={{ margin: "5px 0px 5px 0px" }}>
          <strong>획득 경험치</strong> :
          <span style={{ color: "#A6BB8D" }}> {exp} </span>
        </p>
      </div>
    </Paper>
  );
};

export default LogItemComponent;
