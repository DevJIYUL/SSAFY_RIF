const RankingItemComponent = (props) => {
  const rank = props.rankingInfo.rank
  const member = props.rankingInfo.member

  return (
    <div>
      <div>{rank}위</div>
      <div>
        {Object.entries(member).map(([key, value]) => (
          <div key={`${rank}` + key}>
            {key} : {value}
          </div>
        ))}
      </div>
      <hr />
    </div>
  )
}

export default RankingItemComponent
