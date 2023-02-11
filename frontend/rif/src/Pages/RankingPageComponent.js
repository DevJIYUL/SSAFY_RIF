import { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import getRankingInfoWithMeAPI from "../API/getRankingInfoWithMeAPI";
import getRankingInfoWithoutMeAPI from "../API/getRankingInfoWithoutMeAPI";
import RankingItemComponent from "../Components/RankingItemComponent";
import { CircularProgress, Grid } from "@mui/material";
import PageChangerComponent from "../UI/PageChangerComponent";
import { reissueHandler } from "../API/reissueHandler";
import { authActions } from "../store/auth";

let isInitial = true;

const RankingPageComponent = () => {
  const dispatch = useDispatch();

  const token = useSelector((state) => state.auth.authentication.token);
  const id = useSelector((state) => state.auth.authentication.id);

  const [rankingInfos, setRankingInfos] = useState("");
  const [myRankingInfo, setMyRankingInfo] = useState("");

  useEffect(() => {
    async function sendRequest() {
      if (token) {
        const response = await getRankingInfoWithMeAPI(token, id);
        if (response.data.newToken) {
          reissueHandler(
            response.data.newToken,
            dispatch,
            authActions.updateToken
          );
          // dispatch(authActions.updateToken());
        }
        setRankingInfos(response.data.members);
        setMyRankingInfo(response.data.me);
        console.log(response);
      } else {
        const response = await getRankingInfoWithoutMeAPI();
        setRankingInfos(response.data.members);
        console.log(response);
      }
    }
    if (isInitial) {
      sendRequest();
    } else {
      return;
    }
  }, [token, id, dispatch]);

  return (
    <div>
      <PageChangerComponent to="/main">메인 페이지</PageChangerComponent>
      <div
        style={{
          fontFamily: "NanumSquareEB",
          fontSize: "25px",
          textAlign: "center",
          margin: "10px 10px 20px 10px",
        }}
      >
        재활용 잘하는 순위
      </div>
      <Grid container style={{ fontFamily: "NanumSquareEB", fontSize: "25px" }}>
        {rankingInfos ? (
          rankingInfos.map((rankingInfo) => (
            <RankingItemComponent
              key={`ranking${rankingInfo.rank}`}
              rankingInfo={rankingInfo}
            />
          ))
        ) : (
          <CircularProgress color="success" />
        )}
        <hr />
        {myRankingInfo && (
          <RankingItemComponent
            key={`myRanking${myRankingInfo.rank}`}
            rankingInfo={myRankingInfo}
          />
        )}
      </Grid>
    </div>
  );
};

export default RankingPageComponent;
