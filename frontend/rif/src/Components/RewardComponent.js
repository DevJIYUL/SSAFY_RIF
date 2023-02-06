import RewardItemComponent from "./RewardItemComponent";
import getBadgesAPI from "../API/getBadgesAPI";
import { useState, useEffect } from "react";
import { Grid } from "@mui/material";

const RewardComponent = (props) => {
  // states
  const [badges, setBadges] = useState([]);

  // when mounted
  useEffect(() => {
    const apiResponse = getBadgesAPI("testaccesstoken");
    apiResponse.then((res) => {
      const totalBadges = res.data.totalBadge; // get all badges\

      const tempBadgesHas = [];
      const tempBadgesNotHas = [];

      for (const badgeInfo of totalBadges) {
        if (badgeInfo.hasBadge) {
          tempBadgesHas.push(badgeInfo);
        } else {
          tempBadgesNotHas.push(badgeInfo);
        }
      }

      setBadges(tempBadgesHas.concat(tempBadgesNotHas));
    });
  }, []);

  return (
    <div>
      <Grid
        container
        columns={{ xs: 6, sm: 12 }}
        sx={{ display: "flex", margin: "auto" }}
        spacing={1}
      >
        {badges.map((value, index) => {
          return (
            <Grid
              item
              xs={2}
              sm={4}
              key={index}
              display="flex"
              justifyContent="center"
              alignContent="center"
            >
              <RewardItemComponent badge={value}></RewardItemComponent>
            </Grid>
          );
        })}
      </Grid>
    </div>
  );
};

export default RewardComponent;
