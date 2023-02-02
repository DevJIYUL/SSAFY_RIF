const UserSearchResultItemComponent = (props) => {
  return (
    <div>
      {Object.entries(props.recentSearchResult).map(([key, value]) => (
        <p key={props.recentSearchResult.id + key}>
          {key}:{value}
        </p>
      ))}
      <hr />
    </div>
  )
}

export default UserSearchResultItemComponent
