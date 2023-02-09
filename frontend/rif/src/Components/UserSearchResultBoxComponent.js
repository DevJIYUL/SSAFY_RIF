import UserSearchResultItemComponent from "./UserSearchResultItemComponent";
import { Grid } from "@mui/material";

const UserSearchResultBoxComponent = (props) => {
  return (
    <Grid style={{ width: "90%", minWidth: "256px" }}>
      {props.recentSearchResults.map((recentSearchResult) => (
        <UserSearchResultItemComponent
          key={recentSearchResult.id}
          recentSearchResult={recentSearchResult}
        ></UserSearchResultItemComponent>
      ))}
    </Grid>
  );
};

export default UserSearchResultBoxComponent;
