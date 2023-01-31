import UserSearchResultElementComponent from "./UserSearchResultElementComponent"

const UserSearchResultBoxComponent = (props) => {
  return (
    <div>
      {props.recentSearchResults.map((recentSearchResult) => (
        <UserSearchResultElementComponent
          key={recentSearchResult.id}
          recentSearchResult={recentSearchResult}
        ></UserSearchResultElementComponent>
      ))}
    </div>
  )
}

export default UserSearchResultBoxComponent
