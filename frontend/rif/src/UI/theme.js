import { createTheme } from '@mui/material/styles'

const theme = createTheme({
    palette: {
        primary: {
            main:"#EAE7B1",
            contrastText: "#5D5E58",
        },
        secondary : {
            main: "#3C6255",
            contrastText: "#FBFBFB",
        },
        pageChanger :{
            main : "#80A181",
            contrastText : "80A181",
        },
    },
    custom:{

    }
})

export default theme