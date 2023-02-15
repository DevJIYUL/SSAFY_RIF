import { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import getRankingInfoWithMeAPI from "../API/getRankingInfoWithMeAPI";
import getRankingInfoWithoutMeAPI from "../API/getRankingInfoWithoutMeAPI";
import RankingItemComponent from "../Components/RankingItemComponent";
import { CircularProgress, Grid } from "@mui/material";
import { authActions } from "../store/auth";
import { useNavigate } from "react-router-dom";

let isInitial = true;

const RankingPageComponent = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const token = useSelector((state) => state.auth.authentication.token);
  const id = useSelector((state) => state.auth.authentication.id);

  const [rankingInfos, setRankingInfos] = useState("");
  const [myRankingInfo, setMyRankingInfo] = useState("");

  useEffect(() => {
    async function sendRequest() {
      if (token) {
        const response = await getRankingInfoWithMeAPI(token, id);
        if (response.newToken) {
          dispatch(authActions.updateToken(response.newToken));
          return;
        }
        if (response.status === 307) {
          dispatch(authActions.logout());
          navigate("/login");
          return;
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
  }, [token, id, dispatch, navigate]);

  return (
    <div>
      <div
        style={{
          fontFamily: "NanumSquareEB",
          fontSize: "25px",
          textAlign: "center",
          margin: "20px 10px 20px 10px",
        }}
      >
        재활용 잘하는 순위
      </div>
      <Grid
        container
        style={{
          fontFamily: "NanumSquareEB",
          fontSize: "25px",
          justifyContent: "center",
        }}
      >
        {rankingInfos ? (
          rankingInfos.map((rankingInfo) => (
            <RankingItemComponent
              key={`ranking${rankingInfo.rank}`}
              rankingInfo={rankingInfo}
            />
          ))
        ) : (
          <CircularProgress color="success" style={{ margin: "45%" }} />
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
