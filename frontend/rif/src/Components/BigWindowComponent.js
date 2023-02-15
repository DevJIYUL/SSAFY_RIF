const BigWindowComponent = (props) => {
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
      {props.width < 350 ? (
        <h1 style={{ backgroundColor: "#5C855D", padding: "20px 50px" }}>
          너무 좁아요! {badEmoji}
        </h1>
      ) : (
        <h1 style={{ backgroundColor: "#5C855D", padding: "20px 50px" }}>
          너무 넓어요! {badEmoji}
        </h1>
      )}

      <h3> RIF 페이지는 모바일 환경에서의 동작을 기준으로 하고 있습니다.</h3>
      <h3>
        화면 크기를 350px 이상, 480px 이하로 조정해주세요 (현재 :
        <span style={{ color: "#A6BB8D" }}>{props.width} </span>
        px)
      </h3>
      <h4 style={{ color: "#5D5E58" }}>
        <strong>"F12"</strong> 자판을 눌러 화면 크기를 조절할 수 있습니다.
      </h4>
    </div>
  );
};

export default BigWindowComponent;
