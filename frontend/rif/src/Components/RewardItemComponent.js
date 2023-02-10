import { useState } from "react";
import RewardInfoComponent from "./RewardInfoComponent";

// show rounded reward Icon
// props : type, reward
const RewardItemComponent = (props) => {
  const { rewardInfo, onDisplay, hasReward } = props.reward;
  const { title, imgPath } = rewardInfo;

  const NotHasStyle = { opacity: 0.3, filter: "grayscale(100)" };
  const displayStyle = {
    filter:
      "drop-shadow(1px 1px 3px #EBDD5D) drop-shadow(-1px -1px 3px #EBDD5D)",
  };

  const [modalOpen, setModalOpen] = useState(false);
  const handleModalOpen = () => {
    // open modal component
    setModalOpen(true);
  };

  const handleModalClose = () => {
    // close modal component
    setModalOpen(false);
  };

  let style = {};
  if (!hasReward) {
    // if the user has no badge
    style = NotHasStyle;
  } else if (onDisplay) {
    style = displayStyle;
  }

  return (
    <div>
      <RewardInfoComponent
        open={modalOpen}
        onClose={handleModalClose}
        reward={props.reward}
        type={props.type}
      ></RewardInfoComponent>
      <img
        src={imgPath}
        alt={title}
        style={style}
        onClick={handleModalOpen}
        height="75px"
      />
    </div>
  );
};

export default RewardItemComponent;
