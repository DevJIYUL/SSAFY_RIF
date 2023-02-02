const LogItemComponent = (props) => {
  return (
    <div ref={props.innerRef}>
      <h2> Log Item Component {props.id}</h2>
      {Object.entries(props.log).map(([key, value]) => {
        return (
          <p key={key}>
            {key} : {value}
          </p>
        )
      })}
    </div>
  )
}

export default LogItemComponent
