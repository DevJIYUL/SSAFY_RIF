import { Link } from "react-router-dom";
import BtnComponent from "../UI/BtnComponent";
const ErrorPageComponent = () => {
  const badEmoji = ":-(";

  return (
    <div
      style={{
        position: "absolute",
        left: "50%",
        top: "50%",
        transform: "translate(-50%, -50%)",
        width: "100wh",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        flexDirection: "column",
      }}
    >
      <h1 style={{ backgroundColor: "#5C855D", padding: "20px 50px" }}>
        404 Page Error <br />
        <br />
        {badEmoji}
      </h1>
      <h4 style={{ color: "#5D5E58" }}>잘못된 접근입니다.</h4>
      <Link to="/home" style={{ textDecoration: "none" }}>
        <BtnComponent color="secondary"> 홈으로 돌아가기 </BtnComponent>
      </Link>
    </div>
  );
};

export default ErrorPageComponent;
