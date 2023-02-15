import React, { useEffect, useState } from "react";
import { Grid, Paper } from "@mui/material";
import calLevel from "../API/calLevel";
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";

const rankItemStyle = {
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
};

const RankingItemComponent = (props) => {
  const navigate = useNavigate();

  const rank = props.rankingInfo.rank;
  const member = props.rankingInfo.member;
  const name = member.name;
  const id = member.id;
  const profileImgPath = member.profileImgPath;
  const exp = member.exp;
  const [level, caledExp] = calLevel(exp);

  const [rankImgTag, setRankImgTag] = useState("");
  const reduxId = useSelector((state) => state.auth.authentication.id);

  useEffect(() => {
    if (rank === 1 || rank === 2 || rank === 3) {
      setRankImgTag(
        <img
          style={{
            width: "30px",
            height: "20px",
            margin: "5px",
            ...rankItemStyle,
          }}
          src={`/ranking/${rank}.png`}
          alt="rank-img"
        />
      );
    }
  }, [rank]);

  // uid,profileImgPath,exp
  return (
    <Grid
      container
      style={{
        display: "flex",
        alignItems: "center",
      }}
    >
      <Paper
        style={{
          ...rankItemStyle,
          width: "90%",
          minWidth: "252px",
          margin: "3px 5%",
          justifyContent: "space-between",
        }}
        onClick={() => {
          console.log(reduxId, id);
          console.log(typeof reduxId, typeof id);
          if (reduxId && reduxId === id) {
            navigate("/main");
          } else {
            navigate(`/member/${id}`);
          }
        }}
      >
        {rankImgTag ? (
          rankImgTag
        ) : (
          <div
            style={{
              width: "30px",
              height: "20px",
              margin: "5px",
              ...rankItemStyle,
            }}
          >
            {rank}
          </div>
        )}
        <img
          src={profileImgPath}
          style={{
            ...rankItemStyle,
            height: "43px",
            borderRadius: "50%",
            margin: "3px 0px",
            border: "3px solid #4E9E00",
          }}
          alt=""
        />
        <div
          style={{
            ...rankItemStyle,
            margin: "0 0 0 5px",
            fontSize: "6vw",
          }}
        >
          {name}
        </div>
        <div
          style={{
            width: "45px",
            height: "45px",
            marginRight: "0px",
            position: "relative",
            backgroundRepeat: "no-repeat",
            backgroundImage: `url("/profile/sprout.png")`,
            backgroundSize: "contain",
          }}
          alt=""
        >
          <div
            style={{
              width: "25px",
              height: "25px",
              position: "absolute",
              right: "0px",
              bottom: "0px",
              backgroundRepeat: "no-repeat",
              backgroundImage: `url("/profile/level${level}.png")`,
              backgroundSize: "contain",
              color: "white",
              fontSize: "12px",
              ...rankItemStyle,
            }}
            src="/profile/level.png"
            alt=""
          >
            {level}
          </div>
        </div>
        <div
          style={{
            ...rankItemStyle,
            // margin: "auto 11px auto 0px",
            color: "#4E9E00",
            fontSize: "15px",
            width: "51px",
            textAlign: "center",
          }}
        >
          {level !== 10 && caledExp + "%"}
          {level === 10 && caledExp}
        </div>
      </Paper>
    </Grid>
  );
};

export default RankingItemComponent;
