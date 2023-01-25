import { ThemeProvider } from '@mui/material/styles'
import KeyboardArrowLeftIcon from '@mui/icons-material/KeyboardArrowLeft';
import theme from './theme'
import Button from '@mui/material/Button';

const PageChangerComponent = ((props) => {

    return (
        <ThemeProvider theme={theme}>
            <Button
                variant="text"
                color="pageChanger"
                className={`${props.className}`}
                onClick={props.onClick}
                startIcon={<KeyboardArrowLeftIcon sx={{color: "#5C855D"}} />}
                sx={props.sx ||{m:'1rem'}}
            >
                {props.children}
            </Button>
        </ThemeProvider>
    )
})

export default PageChangerComponent;