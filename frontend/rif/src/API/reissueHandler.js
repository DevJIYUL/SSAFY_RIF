export async function reissueHandler(newToken, dispatch, updateToken) {
  dispatch(updateToken(newToken));
}
