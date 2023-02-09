import { useState } from "react";
import RewardInfoComponent from "./RewardInfoComponent";

const renameKeys = (oldObj) => {
  const newKeys = ["reward", "hasReward", "onDisplay"];
  const oldKeys = Object.keys(oldObj);

  const returnObj = {};

  newKeys.forEach((key, keyIndex) => {
    returnObj[key] = oldObj[oldKeys[keyIndex]];
  });

  return returnObj;
};

const RewardItemComponent = (props) => {
  const { reward, hasReward, onDisplay } = renameKeys(props.reward);

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
    style = NotHasStyle;
  } else if (onDisplay) {
    style = displayStyle;
  }

  return (
    <div>
      <RewardInfoComponent
        open={modalOpen}
        onClose={handleModalClose}
        reward={reward}
        onDisplay={onDisplay}
        type={props.type}
      ></RewardInfoComponent>
      <img
        src={reward.imgPath}
        alt={reward.id}
        style={style}
        onClick={handleModalOpen}
        height="75px"
      />
    </div>
  );
};

export default RewardItemComponent;
