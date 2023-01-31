import { useState } from "react";
import BadgeInfoComponent from "./BadgeInfoComponent";

const RewardItemComponent = (props) => {
  const { badge, hasBadge, onDisplay } = props.badge;

  const NotHasStyle = { opacity: 0.3, filter: "grayscale(100)" };
  const displayStyle = { border: "5px solid #FFD600", borderRadius: "150px" };

  const [modalOpen, setModalOpen] = useState(false);
  const handleModalOpen = () => {
    // open modal component
    setModalOpen(true);
    console.log("modal opened");
  };

  const handleModalClose = () => {
    // close modal component
    setModalOpen(false);
    console.log("modal closed");
  };

  let style = {};
  if (!hasBadge) {
    style = NotHasStyle;
  } else if (onDisplay) {
    style = displayStyle;
  }

  return (
    <div>
      <BadgeInfoComponent
        open={modalOpen}
        onClose={handleModalClose}
        badge={badge}
        onDisplay={onDisplay}
      ></BadgeInfoComponent>
      <img
        src={badge.imgPath}
        alt={badge.id}
        style={style}
        onClick={handleModalOpen}
        height="75px"
      />
    </div>
  );
};

export default RewardItemComponent;
