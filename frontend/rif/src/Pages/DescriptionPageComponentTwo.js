import { Grid, Container } from '@mui/material'
import BtnComponent from '../UI/BtnComponent'
import { Link } from 'react-router-dom'
import PageChangerComponent from '../UI/PageChangerComponent'

const DescriptionPageComponentTwo = (() => {
    return (
            <div>
                <Link to ="/home" style={{textDecoration:'none'}}>
                    <PageChangerComponent> 메인화면 </PageChangerComponent>
                </Link>

                <Grid container className="grid-container" direction="column" alignItems="center" justifyContent="center" sx={{ my : "2rem"}}>
                    <Grid item className="grid-header">
                        <img src="/rif-logo-96.png" alt="rif logo 96" className="rif-logo-96" />
                    </Grid>
                    <Grid item className="grid-body">
                        <div className="rif-stands-for" style={{color:"#5C855D"}}>
                            <h1>사용법</h1>
                        </div>
                    </Grid>
                    <Grid item className="grid-description">
                        <Container className="description" sx={{mx:'auto'}}>
                            <p> 대충 사용하시면 됩니다 ㅎㅎ</p>
                        </Container>
                    </Grid>
                    <Grid item className="grid-buttons">
                        <Link to ="/description/1" style={{textDecoration:'none'}}> <BtnComponent color='secondary'> 이전 : 왜 RIF인가? </BtnComponent> </Link>
                        <br/>
                        <Link to ="/" style={{textDecoration:'none'}}> <BtnComponent> 서비스 시작하기 </BtnComponent> </Link>
                    </Grid>
                </Grid>
            </div>
    )
})

export default DescriptionPageComponentTwo