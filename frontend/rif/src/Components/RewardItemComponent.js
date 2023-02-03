import { useState } from "react"
import RewardInfoComponent from "./RewardInfoComponent"

const renameKeys = (oldObj) => {
  const newKeys = ["reward", "hasReward", "onDisplay"]
  const oldKeys = Object.keys(oldObj)

  const returnObj = {}

  newKeys.forEach((key, keyIndex) => {
    returnObj[key] = oldObj[oldKeys[keyIndex]]
  })

  return returnObj
}

const RewardItemComponent = (props) => {
  const { reward, hasReward, onDisplay } = renameKeys(props.reward)

  const NotHasStyle = { opacity: 0.3, filter: "grayscale(100)" }
  const displayStyle = { border: "5px solid #FFD600", borderRadius: "150px" }

  const [modalOpen, setModalOpen] = useState(false)
  const handleModalOpen = () => {
    // open modal component
    setModalOpen(true)
    console.log("modal opened")
  }

  const handleModalClose = () => {
    // close modal component
    setModalOpen(false)
    console.log("modal closed")
  }

  let style = {}
  if (!hasReward) {
    style = NotHasStyle
  } else if (onDisplay) {
    style = displayStyle
  }

  return (
    <div>
      <RewardInfoComponent
        open={modalOpen}
        onClose={handleModalClose}
        reward={reward}
        onDisplay={onDisplay}
      ></RewardInfoComponent>
      <img
        src={reward.imgPath}
        alt={reward.id}
        style={style}
        onClick={handleModalOpen}
        height="75px"
      />
    </div>
  )
}

export default RewardItemComponent
