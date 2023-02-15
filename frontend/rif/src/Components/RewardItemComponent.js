import { useState } from "react";
import RewardInfoComponent from "./RewardInfoComponent";

// show rounded reward Icon
// props : type, reward
const RewardItemComponent = (props) => {
  console.log(props, "props");
  const { rewardInfo, hasReward } = props.reward;
  let onDisplay = props.reward.onDisplay;
  const [tempOnDisplay, setTempOnDisplay] = useState(onDisplay);

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
  } else if (tempOnDisplay) {
    style = displayStyle;
  }

  return (
    <div>
      <RewardInfoComponent
        open={modalOpen}
        onClose={handleModalClose}
        reward={props.reward}
        onDisplay={tempOnDisplay}
        toggler={setTempOnDisplay}
        type={props.type}
        isRef={props.isRef}
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
