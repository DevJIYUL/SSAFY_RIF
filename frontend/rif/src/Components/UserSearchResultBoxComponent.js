import UserSearchResultItemComponent from "./UserSearchResultItemComponent"

const UserSearchResultBoxComponent = (props) => {
  return (
    <div>
      {props.recentSearchResults.map((recentSearchResult) => (
        <UserSearchResultItemComponent
          key={recentSearchResult.id}
          recentSearchResult={recentSearchResult}
        ></UserSearchResultItemComponent>
      ))}
    </div>
  )
}

export default UserSearchResultBoxComponent
