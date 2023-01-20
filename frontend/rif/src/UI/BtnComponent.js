/** type is button html tag type \n
 * className is react className. If you want to write over than two, write like "one two".
 * onClick is function pointer.
 * disabled is boolean
 */
const BtnComponent = (props) => {
  return (
    <button
      type={props.type || "button"}
      className={`${props.className}`}
      onClick={props.onClick}
      disabled={props.disabled}
    >
      {props.children}
    </button>
  )
}

export default BtnComponent
