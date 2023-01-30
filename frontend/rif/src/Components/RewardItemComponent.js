const RewardItemComponent = (props) => {
  const { badge, hasBadge, onDisplay } = props.badge;

  const NotHasStyle = { opacity: 0.3, filter: "grayscale(100)" };
  const displayStyle = { border: "5px solid #FFD600", borderRadius: "150px" };

  let style = {};
  if (!hasBadge) {
    style = NotHasStyle;
  } else if (onDisplay) {
    style = displayStyle;
  }

  return (
    <div>
      <img
        src={badge.ImgPath}
        alt={badge.id}
        style={style}
        onClick={props.onClick}
        height="100px"
      />
    </div>
  );
};

export default RewardItemComponent;
