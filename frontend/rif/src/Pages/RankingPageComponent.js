import { useState, useEffect } from "react"
import { useSelector } from "react-redux"
import getRankingInfoWithMeAPI from "../API/getRankingInfoWithMeAPI"
import getRankingInfoWithoutMeAPI from "../API/getRankingInfoWithoutMeAPI"
import RankingItemComponent from "../Components/RankingItemComponent"

let isInitial = true

const RankingPageComponent = () => {
  const token = useSelector((state) => state.auth.authentication.token)
  const [rankingInfos, setRankingInfos] = useState("")
  const [myRankingInfo, setMyRankingInfo] = useState("")

  useEffect(() => {
    async function sendRequest() {
      if (token) {
        const response = await getRankingInfoWithMeAPI(token)
        setRankingInfos(response.data.members)
        setMyRankingInfo(response.data.me)
        console.log(response)
      } else {
        const response = await getRankingInfoWithoutMeAPI()
        setRankingInfos(response.data.members)
        console.log(response)
      }
    }
    if (isInitial) {
      isInitial = false
      sendRequest()
    } else {
      return
    }
  }, [token])

  return (
    <div>
      <p>RankingPage</p>
      {rankingInfos &&
        rankingInfos.map((rankingInfo) => (
          <RankingItemComponent
            key={`${"ranking" + rankingInfo.rank}`}
            rankingInfo={rankingInfo}
          />
        ))}
      {myRankingInfo && (
        <RankingItemComponent
          key={`${"myRanking" + myRankingInfo.rank}`}
          rankingInfo={myRankingInfo}
        />
      )}
    </div>
  )
}

export default RankingPageComponent
